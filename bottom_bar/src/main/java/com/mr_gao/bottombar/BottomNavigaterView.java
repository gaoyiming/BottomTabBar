package com.mr_gao.bottombar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * @ClassName: BottomNavigaterView
 * @Description: ${TODO}
 * @author: Mr_gao
 * @date: 2017/3/1 20:47
 */
public class BottomNavigaterView extends RelativeLayout {

    private LinearLayout number_group;
    private RadioGroup radio_group;

    public BottomNavigaterView(Context context) {
        super(context);
        init(context);
    }


    public BottomNavigaterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BottomNavigaterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.bottom_navigater, this, true);
        number_group = (LinearLayout) view.findViewById(R.id.number_group);
        radio_group = (RadioGroup) view.findViewById(R.id.radio_group);

    }

    public void setData(int[] drawableres, String[] textres) {
        Log.e("setdata", "sad");
        if (drawableres.length != textres.length)
            throw new RuntimeException(" icon数量和text数量不匹配");
//        LinearLayout.LayoutParams containerparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        ArrayList<RadioButton> radioButtons = new ArrayList<>();
//        ArrayList<NumberItem> numberItems = new ArrayList<>();
        for (int i = 0; i < drawableres.length; i++) {
            final int index = i;

            Drawable drawable = getContext().getResources().getDrawable(drawableres[i]);
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setButtonDrawable(null);
            drawable.setBounds(0, 0, 84,84);
            NumberItem numberItem = new NumberItem(getContext());
            radioButton.setCompoundDrawables(null, drawable, null, null);
            radioButton.setText(textres[i]);
            radioButton.setTextSize(10);
            radioButton.setCompoundDrawablePadding(15);
            radioButton.setTextColor(Color.GRAY);
            radioButton.setGravity(Gravity.CENTER_HORIZONTAL);
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(0, RadioGroup.LayoutParams.MATCH_PARENT, 1.0f);
            layoutParams.setMargins(0,20,0,0);
            radioButton.setLayoutParams(layoutParams);
            numberItem.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f));
            radio_group.addView(radioButton);
             number_group.addView(numberItem);
        }


    }
}
