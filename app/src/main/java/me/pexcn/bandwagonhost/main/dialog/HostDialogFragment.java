/*
 * BandwagonHost - A bandwagonhost.com client for Android
 * Copyright (C) 2016 Xingyu Chen <pexcn97@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.pexcn.bandwagonhost.main.dialog;

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

import me.pexcn.android.utils.io.LogUtils;
import me.pexcn.bandwagonhost.BuildConfig;
import me.pexcn.bandwagonhost.R;
import me.pexcn.bandwagonhost.app.Constants;
import me.pexcn.bandwagonhost.data.local.bean.Host;
import me.pexcn.bandwagonhost.main.arch.MainContract;

/**
 * Created by pexcn on 2017-02-19.
 */
public class HostDialogFragment extends DialogFragment {
    private TextInputEditText mTitle;
    private TextInputEditText mVeid;
    private TextInputEditText mKey;
    private TextInputLayout mLayoutTitle;
    private TextInputLayout mLayoutVeid;
    private TextInputLayout mLayoutKey;

    private Host mHost;
    private AlertDialog mDialog;
    private OnHostListener mListener;
    private MainContract.View mMainView;

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
        mMainView = (MainContract.View) getActivity();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_edit_host,
                (ViewGroup) getActivity().getWindow().getDecorView(), false);
        mTitle = (TextInputEditText) view.findViewById(R.id.et_title);
        mVeid = (TextInputEditText) view.findViewById(R.id.et_veid);
        mKey = (TextInputEditText) view.findViewById(R.id.et_key);
        mLayoutTitle = (TextInputLayout) view.findViewById(R.id.til_title);
        mLayoutVeid = (TextInputLayout) view.findViewById(R.id.til_veid);
        mLayoutKey = (TextInputLayout) view.findViewById(R.id.til_key);

        final Bundle args = getArguments();
        if (args != null) {
            mHost = args.getParcelable(Constants.EXTRA_KEY_HOST);
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
                        ? getString(R.string.dialog_title_add_host)
                        : getString(R.string.dialog_title_update_host))
                .setPositiveButton(args == null
                        ? getString(R.string.dialog_button_ok)
                        : getString(R.string.dialog_button_update), null)
                .setNegativeButton(getString(R.string.dialog_button_cancel), null)
                .setOnKeyListener((dialog, keyCode, event) -> {
                    if (keyCode == KeyEvent.KEYCODE_BACK
                            && event.getAction() == KeyEvent.ACTION_UP && !event.isCanceled()) {
                        dialog.dismiss();
                        return true;
                    }
                    return false;
                }).create();
        mDialog.setOnShowListener(dialog -> {
            mDialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(v -> {
                if (mTitle.length() == 0 || mVeid.length() == 0 || mKey.length() == 0) {
                    fixEditTextInput();
                } else {
                    mHost.title = mTitle.getText().toString();
                    mHost.veid = mVeid.getText().toString();
                    mHost.key = mKey.getText().toString();
                    dispatchProcess(mHost);
                    dialog.dismiss();
                }
            });
            mMainView.hideFab();
        });
        return mDialog;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        mMainView.showFab();
    }

    private void fixEditTextInput() {
        mTitle.addTextChangedListener(new HostDialogTextWatcher(mLayoutTitle));
        mVeid.addTextChangedListener(new HostDialogTextWatcher(mLayoutVeid));
        mKey.addTextChangedListener(new HostDialogTextWatcher(mLayoutKey));

        final String string = getString(R.string.dialog_text_not_be_empty);
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
        if (BuildConfig.DEBUG) {
            LogUtils.d(host.toString());
        }
        if (host.id == 0) {
            mListener.onAdd(host);
        } else {
            mListener.onUpdate(host);
        }
    }

    public interface OnHostListener {
        void onAdd(@NonNull Host host);

        void onUpdate(@NonNull Host host);
    }
}
