package me.pexcn.bandwagonhost.main.fragment;

import android.app.Dialog;
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

import me.pexcn.bandwagonhost.R;
import me.pexcn.simpleutils.common.LogUtils;

/**
 * Created by Administrator on 2017-02-19 0019.
 */
public class AddHostDialogFragment extends DialogFragment
        implements View.OnClickListener, DialogInterface.OnClickListener,
        DialogInterface.OnKeyListener {
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
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(R.layout.dialog_add_host)
                .setTitle(getResources().getString(R.string.activity_add_host_title))
                .setNegativeButton(getResources().getString(android.R.string.cancel), null)
                .setPositiveButton(getResources().getString(android.R.string.ok), this)
                .setOnKeyListener(this)
                .create();
        setCancelable(false);
        return dialog;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTitle = (TextInputEditText) view.findViewById(R.id.et_title);
        mVeid = (TextInputEditText) view.findViewById(R.id.et_veid);
        mKey = (TextInputEditText) view.findViewById(R.id.et_key);
        mTitleLayout = (TextInputLayout) view.findViewById(R.id.til_title);
        mVeidLayout = (TextInputLayout) view.findViewById(R.id.til_veid);
        mKeyLayout = (TextInputLayout) view.findViewById(R.id.til_key);
        ((AlertDialog) getDialog()).getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case android.R.id.button1:

                LogUtils.d(String.valueOf(mTitle.length()));

                break;
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}
