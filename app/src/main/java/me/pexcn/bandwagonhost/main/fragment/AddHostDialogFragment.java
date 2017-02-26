package me.pexcn.bandwagonhost.main.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.bean.database.Host;

/**
 * Created by pexcn on 2017-02-19.
 */
public class AddHostDialogFragment extends DialogFragment {
    private AlertDialog mDialog;
    private TextInputEditText mTitle;
    private TextInputEditText mVeid;
    private TextInputEditText mKey;
    private TextInputLayout mTitleLayout;
    private TextInputLayout mVeidLayout;
    private TextInputLayout mKeyLayout;
    private AddHostListener mListener;

    public static AddHostDialogFragment newInstance() {
        return new AddHostDialogFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (AddHostListener) getActivity();
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
                .setOnKeyListener((dialog, keyCode, event) -> {
                    if (keyCode == KeyEvent.KEYCODE_BACK
                            && event.getAction() == KeyEvent.ACTION_UP
                            && !event.isCanceled()) {
                        dialog.dismiss();
                        return true;
                    }
                    return false;
                })
                .create();
        mDialog.setOnShowListener(dialog -> mDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(v -> {
            if (mTitle.length() == 0 || mVeid.length() == 0 || mKey.length() == 0) {
                handleAndFixInput();
            } else {
                handleAddHost();
                dialog.dismiss();
            }
        }));
        return mDialog;
    }

    private void handleAndFixInput() {
        mTitle.addTextChangedListener(new AddHostDialogTextWatcher(AddHostDialogTextWatcher.TYPE_TITLE));
        mVeid.addTextChangedListener(new AddHostDialogTextWatcher(AddHostDialogTextWatcher.TYPE_VEID));
        mKey.addTextChangedListener(new AddHostDialogTextWatcher(AddHostDialogTextWatcher.TYPE_KEY));

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
    }

    private void handleAddHost() {
        final Host host = new Host();
        host.title = mTitle.getText().toString();
        host.veid = mVeid.getText().toString();
        host.key = mKey.getText().toString();
        mListener.onAddedHost(host);
    }

    class AddHostDialogTextWatcher implements TextWatcher {
        static final int TYPE_TITLE = 1;
        static final int TYPE_VEID = 2;
        static final int TYPE_KEY = 3;
        private int mType;

        AddHostDialogTextWatcher(int type) {
            this.mType = type;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (mType) {
                case TYPE_TITLE:
                    if (mTitleLayout.getError() != null) {
                        mTitleLayout.setError(null);
                    }
                    break;
                case TYPE_VEID:
                    if (mVeidLayout.getError() != null) {
                        mVeidLayout.setError(null);
                    }
                    break;
                case TYPE_KEY:
                    if (mKeyLayout.getError() != null) {
                        mKeyLayout.setError(null);
                    }
                    break;
            }
        }
    }

    public interface AddHostListener {
        void onAddedHost(Host host);
    }
}
