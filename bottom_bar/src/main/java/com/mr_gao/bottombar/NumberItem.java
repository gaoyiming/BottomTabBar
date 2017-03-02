package com.mr_gao.bottombar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @ClassName: NumberItem
 * @Description: ${TODO}
 * @author: Mr_gao
 * @date: 2017/3/1 20:58
 */
public class NumberItem extends RelativeLayout{

    private TextView tab_number;
    private int MAXNUM=99;

    public NumberItem(Context context) {
        super(context);
        init(context);
    }

    public NumberItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NumberItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.number_navigater, this, true);

        tab_number = (TextView) view.findViewById(R.id.tab_number);
    }
    public void setTextNum(int text_num){
       if(text_num<1 ) {
           tab_number.setVisibility(GONE);
           return;
       }
        tab_number.setVisibility(VISIBLE);
        tab_number.setText(text_num+"");
        if(text_num>MAXNUM)
        tab_number.setText(text_num+"+");
    }
}
