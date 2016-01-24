package com.example.sony.multitypelistview;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by sony on 2016-01-22.
 */
public class ItemView extends FrameLayout{
    public ItemView(Context context) {
        super(context);
        init();
    }
    TextView titleView;
    ItemData data;
    public void init(){
        inflate(getContext(),R.layout.view_item,this);
        titleView = (TextView)findViewById(R.id.text_title);
    }

    public void setTitle(ItemData data){
        this.data = data;
        titleView.setText(data.title);
    }
}
