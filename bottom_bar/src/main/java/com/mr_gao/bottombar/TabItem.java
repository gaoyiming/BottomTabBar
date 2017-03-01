package com.mr_gao.bottombar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Mr_g on 2017/2/28.
 */

public class TabItem extends RelativeLayout {

    private RelativeLayout tab_item;
    private ImageView tab_item_icon;
    private TextView tab_item_title;
    private TextView tab_number;
    private boolean isChecked;

    public TabItem(Context context) {
        super(context);
        init( context);

    }



    public TabItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init( context);
    }

    public TabItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init( context);
    }
    public TabItem(Context context,int drawable,String text) {
        super(context);
        init( context);
        tab_item_icon.setBackground(getResources().getDrawable(drawable));
        tab_item_title.setText(text);


    }
    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.bottombaritem_tab, this, true);
        tab_item = (RelativeLayout)view.findViewById(R.id.tab_item);
        tab_item_icon = (ImageView) view.findViewById(R.id.tab_item_icon);
        tab_item_title = (TextView) view.findViewById(R.id.tab_item_title);
        tab_number = (TextView) view.findViewById(R.id.tab_number);

    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public void setNumber(int num) {
        tab_number.setText(num+"");
    }
}
