package com.mr_gao.bottombar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mr_g on 16/4/21.
 */
public class BottomBarView extends RelativeLayout {
    private static final float SCALEDEFALT = 1;
    private int currentItem = 0;
    private int[] imageResources;
    private int[] colorResources;
    private int inactive_color;
    private int active_color;

    public void setOnSelectChanged(SelectChangedListener selectChanged) {
        this.selectChanged = selectChanged;
    }

    private int bg_clolor;
    private int bb_active_tvsize;
    private int bb_inactive_tvsize;
    private String[] textlist;
    private float SCALELEVEL;
    private Context context;
    private List<View> viewList = new ArrayList<>();
    private List<BottomNavigationItem> bottomlistlist;
    private SelectChangedListener selectChanged;
    public interface SelectChangedListener {
        void changeTo(int index);
    }

    public BottomBarView(Context context) {
        this(context, null);

    }


    public BottomBarView(Context context, AttributeSet attrs) {

        this(context, attrs, 0);
    }

    public BottomBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView(attrs);


    }


    /**
     * @param attrs 属性集
     */
    private void initView(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomBarView);

        bg_clolor = typedArray.getColor(R.styleable.BottomBarView_bb_bg_color, getResources().getColor(R.color.white));
        bb_inactive_tvsize = typedArray.getDimensionPixelSize(R.styleable.BottomBarView_bb_inactive_tvsize, 12);
        active_color = typedArray.getColor(R.styleable.BottomBarView_bb_active_color, getResources().getColor(R.color.black));
        inactive_color = typedArray.getColor(R.styleable.BottomBarView_bb_inactive_color, getResources().getColor(R.color.black));
        SCALELEVEL = typedArray.getFloat(R.styleable.BottomBarView_bb_scalelevel, (float) 1.2);

        typedArray.recycle();
    }


    public void setUpWithView(String[] textlist) {
        setUpWithView(null, textlist);
    }

    public void setUpWithView(int[] imageResources, String[] textlist) {
        setUpWithView(null, imageResources, textlist);
    }

    public void setUpWithView(int[] colorResources, int[] imageResources, String[] textlist) {
        this.textlist = textlist;
        this.colorResources = colorResources;
        this.imageResources = imageResources;

    }

    private void addTab(BottomNavigationItem bottomNavigationItem) {
        bottomlistlist.add(bottomNavigationItem);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        FrameLayout container = new FrameLayout(context);
        LinearLayout items = new LinearLayout(context);
        items.setOrientation(LinearLayout.HORIZONTAL);
        items.setBackground(new ColorDrawable(getResources().getColor(R.color.white)));
        container.setBackgroundColor(bg_clolor);
        ViewGroup.LayoutParams containerparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        for (int i = 0; i < textlist.length; i++) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            LinearLayout item = new LinearLayout(context);
            item.setOrientation(LinearLayout.HORIZONTAL);
            final int index = i;
            View view = inflater.inflate(com.mr_gao.bottombar.R.layout.bottom_navigation, this, false);
            ImageView icon = (ImageView) view.findViewById(com.mr_gao.bottombar.R.id.tab_item_icon);
            TextView title = (TextView) view.findViewById(com.mr_gao.bottombar.R.id.tab_item_title);
            if (i == 0) {
                title.setTextColor(active_color);
                icon.setColorFilter(active_color);
                icon.setScaleX(SCALELEVEL);
                icon.setScaleY(SCALELEVEL);
                title.setText(textlist[i]);
                title.setTextSize(bb_active_tvsize);
                icon.setImageResource(imageResources[i]);
            } else {
                title.setTextColor(inactive_color);
                icon.setColorFilter(inactive_color);
                icon.setScaleX(SCALEDEFALT);
                icon.setScaleY(SCALEDEFALT);
                title.setText(textlist[i]);
                title.setTextSize(bb_inactive_tvsize);
                icon.setImageResource(imageResources[i]);
            }

            viewList.add(view);
            if (i == currentItem) {
                icon.setScaleX(SCALELEVEL);
                icon.setScaleY(SCALELEVEL);
            }


            LayoutParams itemParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            item.addView(view, itemParams);
            LayoutParams item_params = new LayoutParams(
                    getWidth() / textlist.length, ViewGroup.LayoutParams.MATCH_PARENT);
            items.addView(item, item_params);
            view.setBackground(new ColorDrawable(bg_clolor));
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBottomNavigationItemClick(index);
                }


            });
        }

        container.addView(items);

        View line = new View(context);
        line.setBackgroundColor(getResources().getColor(R.color.black));
        RelativeLayout.LayoutParams line_params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        line_params.addRule(ALIGN_PARENT_TOP);
        addView(line, line_params);


        addView(container, containerparams);


    }


    private void onBottomNavigationItemClick(int index) {
        if (currentItem == index) {
            return;
        }

        for (int i = 0; i < textlist.length; i++) {
            View view = viewList.get(i);
            ImageView icon = (ImageView) view.findViewById(com.mr_gao.bottombar.R.id.tab_item_icon);
            TextView title = (TextView) view.findViewById(com.mr_gao.bottombar.R.id.tab_item_title);
            if (index == i) {
                title.setTextColor(active_color);
                icon.setColorFilter(active_color);
                scaleView(title, SCALELEVEL);
                scaleView(icon, SCALELEVEL);
                title.setTextSize(bb_active_tvsize);
            } else if (currentItem == i) {
                title.setTextColor(inactive_color);
                icon.setColorFilter(inactive_color);
                scaleView(title, SCALEDEFALT);
                scaleView(icon, SCALELEVEL);
                icon.setScaleX(SCALEDEFALT);
                icon.setScaleY(SCALEDEFALT);
                title.setTextSize(bb_inactive_tvsize);
            }

        }
        currentItem = index;
        if (selectChanged!=null){
            selectChanged.changeTo(index);
        }
    }

    private void scaleView(View view, float scalelevel) {
        view.setScaleX(scalelevel);
        view.setScaleY(scalelevel);

    }
}