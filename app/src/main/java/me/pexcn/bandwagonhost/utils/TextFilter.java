package me.pexcn.bandwagonhost.utils;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

/**
 * Created by pexcn on 2016-07-01.
 */
public class TextFilter implements InputFilter {
    private EditText mEditText;
    private Context mContext;

    public TextFilter() {
    }

    public TextFilter(EditText edittext) {
        this.mEditText = edittext;
    }

    public TextFilter(EditText edittext, Context context) {
        this.mEditText = edittext;
        this.mContext = context;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        for (int i = start; i < end; i++) {
            switch (source.charAt(i)) {
                case ' ':
                case '*':
                case '/':
                case '"':
                case '…':
                case '\'':
                case '\\':
                    mEditText.setError("特殊字符不允许输入");
                    return "";
            }
            switch (Character.getType(source.charAt(i))) {
                case Character.SURROGATE:
                case Character.OTHER_SYMBOL:
                case Character.PRIVATE_USE:
                    mEditText.setError("Emoji 表情不允许输入");
                    return "";
            }
        }
        return null;
    }
}
