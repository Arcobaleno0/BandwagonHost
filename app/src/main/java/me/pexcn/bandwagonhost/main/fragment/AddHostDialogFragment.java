package me.pexcn.bandwagonhost.main.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import me.pexcn.bandwagonhost.R;

/**
 * Created by pexcn on 2017-02-19.
 */
public class AddHostDialogFragment extends DialogFragment
        implements DialogInterface.OnKeyListener {
    private AlertDialog mDialog;
    private TextInputEditText mTitle;
    private TextInputEditText mVeid;
    private TextInputEditText mKey;
    private TextInputLayout mTitleLayout;
    private TextInputLayout mVeidLayout;
    private TextInputLayout mKeyLayout;

    public static AddHostDialogFragment newInstance() {
        return new AddHostDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setCancelable(false);
        final View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_host,
                (ViewGroup) getActivity().getWindow().getDecorView(), false);
        mTitle = (TextInputEditText) view.findViewById(R.id.et_title);
        mVeid = (TextInputEditText) view.findViewById(R.id.et_veid);
        mKey = (TextInputEditText) view.findViewById(R.id.et_key);
        mTitleLayout = (TextInputLayout) view.findViewById(R.id.til_title);
        mVeidLayout = (TextInputLayout) view.findViewById(R.id.til_veid);
        mKeyLayout = (TextInputLayout) view.findViewById(R.id.til_key);
        mDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(getResources().getString(R.string.activity_add_host_title))
                .setNegativeButton(getResources().getString(android.R.string.cancel), null)
                .setPositiveButton(getResources().getString(android.R.string.ok), null)
                .setOnKeyListener(this)
                .create();
        mDialog.setOnShowListener(dialog -> mDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(v -> {
            String string = getResources().getString(R.string.dialog_text_not_be_empty);
            if (mTitle.length() == 0) {
                mTitleLayout.setError(mTitleLayout.getHint() + " " + string);
            } else {
                mTitleLayout.setErrorEnabled(false);
            }
            if (mVeid.length() == 0) {
                mVeidLayout.setError(mVeidLayout.getHint() + " " + string);
            } else {
                mVeidLayout.setErrorEnabled(false);
            }
            if (mKey.length() == 0) {
                mKeyLayout.setError(mKeyLayout.getHint() + " " + string);
            } else {
                mKeyLayout.setErrorEnabled(false);
            }
        }));
        return mDialog;
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_UP
                && !event.isCanceled()) {
            dialog.cancel();
            return true;
        }
        return false;
    }
}
