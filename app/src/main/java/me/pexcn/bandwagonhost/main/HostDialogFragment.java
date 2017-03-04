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
    public static final String ARGS_ID = "id";
    public static final String ARGS_TITLE = "title";
    public static final String ARGS_VEID = "veid";
    public static final String ARGS_KEY = "key";

    private int mId;
    private String mTitle;
    private String mVeid;
    private String mKey;
    private TextInputEditText mTitleEditText;
    private TextInputEditText mVeidEditText;
    private TextInputEditText mKeyEditText;
    private TextInputLayout mTitleLayout;
    private TextInputLayout mVeidLayout;
    private TextInputLayout mKeyLayout;

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
        mTitleEditText = (TextInputEditText) view.findViewById(R.id.et_title);
        mVeidEditText = (TextInputEditText) view.findViewById(R.id.et_veid);
        mKeyEditText = (TextInputEditText) view.findViewById(R.id.et_key);
        mTitleLayout = (TextInputLayout) view.findViewById(R.id.til_title);
        mVeidLayout = (TextInputLayout) view.findViewById(R.id.til_veid);
        mKeyLayout = (TextInputLayout) view.findViewById(R.id.til_key);

        final Bundle args = getArguments();
        if (args != null) {
            mId = args.getInt(ARGS_ID);
            mTitle = args.getString(ARGS_TITLE);
            mVeid = args.getString(ARGS_VEID);
            mKey = args.getString(ARGS_KEY);
            mTitleEditText.setText(mTitle);
            mVeidEditText.setText(mVeid);
            mKeyEditText.setText(mKey);
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
                })
                .create();
        mDialog.setOnShowListener(dialog -> mDialog.getButton(DialogInterface.BUTTON_POSITIVE)
                .setOnClickListener(v -> {
                    if (args == null) {
                        mTitle = mTitleEditText.getText().toString();
                        mVeid = mVeidEditText.getText().toString();
                        mKey = mKeyEditText.getText().toString();
                    }
                    if (mTitle.length() == 0 || mVeid.length() == 0 || mKey.length() == 0) {
                        fixEditTextInput();
                    } else {
                        dispatchProcess(args);
                        dialog.dismiss();
                    }
                }));
        return mDialog;
    }

    private void fixEditTextInput() {
        mTitleEditText.addTextChangedListener(new HostDialogTextWatcher(mTitleLayout));
        mVeidEditText.addTextChangedListener(new HostDialogTextWatcher(mVeidLayout));
        mKeyEditText.addTextChangedListener(new HostDialogTextWatcher(mKeyLayout));

        final String string = getResources().getString(R.string.dialog_text_not_be_empty);
        if (mTitleEditText.length() == 0) {
            mTitleLayout.setError(mTitleLayout.getHint() + " " + string);
        } else {
            mTitleLayout.setErrorEnabled(false);
        }
        if (mVeidEditText.length() == 0) {
            mVeidLayout.setError(mVeidLayout.getHint() + " " + string);
        } else {
            mVeidLayout.setErrorEnabled(false);
        }
        if (mKeyEditText.length() == 0) {
            mKeyLayout.setError(mKeyLayout.getHint() + " " + string);
        } else {
            mKeyLayout.setErrorEnabled(false);
        }
    }

    private void dispatchProcess(@Nullable Bundle args) {
        final Host host = new Host();
        if (args != null) {
            host.id = mId;
        }
        host.title = mTitle;
        host.veid = mVeid;
        host.key = mKey;
        mListener.onHost(host);
    }

    interface OnHostListener {
        void onHost(@NonNull Host host);
    }
}
