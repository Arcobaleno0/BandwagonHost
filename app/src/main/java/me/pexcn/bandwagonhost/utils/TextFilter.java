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
 *
 */

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
