package me.pexcn.bandwagonhost.main;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.data.local.Host;

/**
 * Created by pexcn on 2017-02-19.
 */
public class HostDialogFragment extends DialogFragment {
    public static final String ARGS_HOST = "host";

    private TextInputEditText mTitle;
    private TextInputEditText mVeid;
    private TextInputEditText mKey;
    private TextInputLayout mLayoutTitle;
    private TextInputLayout mLayoutVeid;
    private TextInputLayout mLayoutKey;

    private Host mHost;
    private AlertDialog mDialog;
    private OnHostListener mListener;

    public HostDialogFragment() {
        this.setCancelable(false);
    }

    public static HostDialogFragment newInstance(@Nullable Bundle args) {
        final HostDialogFragment fragment = new HostDialogFragment();
        if (args != null) {
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (OnHostListener) getActivity();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_host,
                (ViewGroup) getActivity().getWindow().getDecorView(), false);
        mTitle = (TextInputEditText) view.findViewById(R.id.et_title);
        mVeid = (TextInputEditText) view.findViewById(R.id.et_veid);
        mKey = (TextInputEditText) view.findViewById(R.id.et_key);
        mLayoutTitle = (TextInputLayout) view.findViewById(R.id.til_title);
        mLayoutVeid = (TextInputLayout) view.findViewById(R.id.til_veid);
        mLayoutKey = (TextInputLayout) view.findViewById(R.id.til_key);

        final Bundle args = getArguments();
        if (args != null) {
            mHost = args.getParcelable(ARGS_HOST);
            if (mHost != null) {
                mTitle.setText(mHost.title);
                mVeid.setText(mHost.veid);
                mKey.setText(mHost.key);
                mTitle.setSelection(mHost.title.length());
            }
        } else {
            mHost = new Host();
        }

        mDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(args == null
                        ? getResources().getString(R.string.dialog_title_add_host)
                        : getResources().getString(R.string.dialog_title_update_host))
                .setPositiveButton(args == null
                        ? getResources().getString(R.string.dialog_button_ok)
                        : getResources().getString(R.string.dialog_button_update), null)
                .setNegativeButton(getResources().getString(R.string.dialog_button_cancel), null)
                .setOnKeyListener((dialog, keyCode, event) -> {
                    if (keyCode == KeyEvent.KEYCODE_BACK
                            && event.getAction() == KeyEvent.ACTION_UP && !event.isCanceled()) {
                        dialog.dismiss();
                        return true;
                    }
                    return false;
                }).create();

        mDialog.setOnShowListener(dialog -> mDialog.getButton(DialogInterface.BUTTON_POSITIVE)
                .setOnClickListener(v -> {
                    if (mTitle.length() == 0 || mVeid.length() == 0 || mKey.length() == 0) {
                        fixEditTextInput();
                    } else {
                        mHost.title = mTitle.getText().toString();
                        mHost.veid = mVeid.getText().toString();
                        mHost.key = mKey.getText().toString();
                        dispatchProcess(mHost);
                        dialog.dismiss();
                    }
                }));
        return mDialog;
    }

    private void fixEditTextInput() {
        mTitle.addTextChangedListener(new HostDialogTextWatcher(mLayoutTitle));
        mVeid.addTextChangedListener(new HostDialogTextWatcher(mLayoutVeid));
        mKey.addTextChangedListener(new HostDialogTextWatcher(mLayoutKey));

        final String string = getResources().getString(R.string.dialog_text_not_be_empty);
        if (mTitle.length() == 0) {
            mLayoutTitle.setError(mLayoutTitle.getHint() + " " + string);
        } else {
            mLayoutTitle.setErrorEnabled(false);
        }
        if (mVeid.length() == 0) {
            mLayoutVeid.setError(mLayoutVeid.getHint() + " " + string);
        } else {
            mLayoutVeid.setErrorEnabled(false);
        }
        if (mKey.length() == 0) {
            mLayoutKey.setError(mLayoutKey.getHint() + " " + string);
        } else {
            mLayoutKey.setErrorEnabled(false);
        }
    }

    private void dispatchProcess(@NonNull Host host) {
        if (host.id == 0) {
            mListener.onAddHost(host);
        } else {
            mListener.onUpdateHost(host);
        }
    }

    interface OnHostListener {
        void onAddHost(@NonNull Host host);

        void onUpdateHost(@NonNull Host host);
    }
}
