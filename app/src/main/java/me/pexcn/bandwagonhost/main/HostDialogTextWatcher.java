package me.pexcn.bandwagonhost.main;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by pexcn on 2017-03-04.
 */
@SuppressWarnings("WeakerAccess")
public class HostDialogTextWatcher implements TextWatcher {
    private TextInputLayout mLayout;

    public HostDialogTextWatcher(TextInputLayout layout) {
        this.mLayout = layout;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (mLayout.getError() != null) {
            mLayout.setError(null);
            // mLayout.setErrorEnabled(false);
        }
    }
}
