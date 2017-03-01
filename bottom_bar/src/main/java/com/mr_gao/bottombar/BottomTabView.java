package com.mr_gao.bottombar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by Mr_g on 2017/2/28.
 */

public class BottomTabView extends LinearLayout {
    private int bg_clolor;
    private int bb_active_tvsize;
    private int bb_inactive_tvsize;
    private String[] textlist;
    private float SCALELEVEL;
    private Context context;
    private int inactive_color;
    private int active_color;

    private ItemClickListener itemClickListener;
    private RepeatClickListener repeatClickListener;
    ArrayList<TabItem> tabItems = new ArrayList<>();

    public BottomTabView(Context context) {
        super(context);
    }

    public BottomTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomBarView);

        bg_clolor = typedArray.getColor(R.styleable.BottomBarView_bb_bg_color, getResources().getColor(R.color.white));
        bb_inactive_tvsize = typedArray.getDimensionPixelSize(R.styleable.BottomBarView_bb_inactive_tvsize, 12);
        active_color = typedArray.getColor(R.styleable.BottomBarView_bb_active_color, getResources().getColor(R.color.black));
        inactive_color = typedArray.getColor(R.styleable.BottomBarView_bb_inactive_color, getResources().getColor(R.color.black));
        SCALELEVEL = typedArray.getFloat(R.styleable.BottomBarView_bb_scalelevel, (float) 1.2);

        typedArray.recycle();
    }


    public void setData(int[] drawableres, String[] textres) {
        Log.e("setData", "setData");

        if (drawableres.length != textres.length)
            throw new RuntimeException(" icon数量和text数量不匹配");
        LinearLayout.LayoutParams containerparams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        for (int i = 0; i < drawableres.length; i++) {
            final int index = i;
            TabItem tabItem = new TabItem(getContext(), drawableres[i], textres[i]);

            tabItem.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f));
            if (i % 2 == 0) {
                tabItem.setBackgroundColor(Color.BLACK);
            } else {
                tabItem.setBackgroundColor(Color.WHITE);
            }
            tabItems.add(tabItem);
            addView(tabItem);
            tabItem.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.click(index);
                    setItemChecked(tabItems, index);
                }
            });
        }


    }

    private void setItemChecked(ArrayList<TabItem> tabItems, int currentitem) {
        int LastCheckNum=-1;
        for (int i = 0; i < tabItems.size(); i++) {
            if(LastCheckNum==currentitem){
                repeatClickListener.repeatClick(currentitem);
            }
            if (i == currentitem) {

                tabItems.get(i).setChecked(true);
            } else {
                tabItems.get(i).setChecked(false);
            }
            LastCheckNum=currentitem;

        }
    }

    public void setItemClickListener(@NonNull ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("onMeasure", "onMeasure");

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("onSizeChanged", "onSizeChanged");

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.e("onLayout", "onLayout");

    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        Log.e("onDraw", "onDraw");

    }

    public void setRepeatClickListener(RepeatClickListener click) {
        this.repeatClickListener=click;
    }
    //    public void setData(){
//
//    }
//    public void setData(){
//
//    }
}
