
package com.zjwz.cmm.mydemo.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zjwz.cmm.mydemo.R;


public class FeatureView extends FrameLayout {

    public FeatureView(Context context) {
        super(context);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.feature, this);
    }

    public synchronized void setTitleId(int titleId) {
        ((TextView) (findViewById(R.id.title))).setText(titleId);
    }

    public synchronized void setTitleId(int titleId, boolean issub) {
        String title = this.getResources().getString(titleId);
        if (issub) {
            ((TextView) (findViewById(R.id.title))).setText("         " + title);
        } else {
            ((TextView) (findViewById(R.id.title))).setText(title);
        }

    }

    public void setTitle(String str){
        ((TextView) findViewById(R.id.title)).setText(str);
    }

}
