package com.itheima.oschina.emoji;

import android.text.Editable;

/**
 * 
 * @author kymjs (http://www.kymjs.com)
 */
public interface OnSendClickListener {
    void onClickSendButton(Editable str);

    void onClickFlagButton();
}
