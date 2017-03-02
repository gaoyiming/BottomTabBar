package com.mr_gao.bottombar;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
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
    private ItemClickListener click;
    private ArrayList<RadioButton> radioButtons = new ArrayList<>();
    private ArrayList<NumberItem> numberItems = new ArrayList<>();
    private RepeatClickListener repeatClick;
    private int currentCheckIndex;
    private float scale;
    private float fontScale;
    private Context context;
    private FragmentTransaction fragmentTransaction;
        private ArrayList<Fragment> fragments;
    private int FragmentResId;
    private FragmentManager fragmentManager;
    private ViewPager viewpager;

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
        this.context = context;
        fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        scale = getContext().getResources().getDisplayMetrics().density;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.bottom_navigater, this, true);
        number_group = (LinearLayout) view.findViewById(R.id.number_group);
        radio_group = (RadioGroup) view.findViewById(R.id.radio_group);
        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(!radio_group.getChildAt(checkedId-1).isPressed()){
                    currentCheckIndex = checkedId;
                    return;
                }
                click.click(checkedId);


                if(fragmentManager!=null){

                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(FragmentResId, fragments.get(checkedId - 1));

                    fragmentTransaction.commitAllowingStateLoss();
                }

            }
        });
        TypedArray typedArray = context.obtainStyledAttributes(R.styleable.ActionBar);


    }

    public void setData(int[] drawableres, String[] textres) {


    }

    public void setItemClickListener(ItemClickListener click) {
        this.click = click;

    }

    public void setRepeatClickListener(RepeatClickListener repeatClick) {
        this.repeatClick = repeatClick;
    }

    public void setNumber(int index, int number) {
        NumberItem numberItem = numberItems.get(index - 1);
        numberItem.setTextNum(number);
    }

    public int dp2px(float dipValue) {

        return (int) (dipValue * scale + 0.5f);
    }

    public void setData(int[] images, String[] texts, ArrayList<Fragment> fragments) {

    }

    public void setDataFragments(FragmentActivity activity, int[] drawableres, String[] texts, ArrayList<Fragment> fragments, int FragmentResId) {
        this.fragments = fragments;
        this.FragmentResId = FragmentResId;

        //  fragmentManager = activity.getSupportFragmentManager();

        baseSet(drawableres, texts);

    }

    private void baseSet(int[] drawableres, String[] texts) {
        if (drawableres.length != texts.length)
            throw new RuntimeException(" icon数量和text数量不匹配");

        for (int i = 0; i < drawableres.length; i++) {
            final int index = i;

            Drawable drawable = getContext().getResources().getDrawable(drawableres[i]);
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setTag(index);
            radioButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    RadioButton radioButton= (RadioButton) v;
                    if(viewpager!=null){
                        viewpager.setCurrentItem(index);
                    }
                    if (currentCheckIndex == index + 1) {
                        repeatClick.repeatClick(index + 1);
                    }
                    currentCheckIndex = index + 1;

                }
            });
            radioButton.setButtonDrawable(null);
            drawable.setBounds(0, 0, dp2px(24), dp2px(24));
            NumberItem numberItem = new NumberItem(getContext());
            radioButton.setCompoundDrawables(null, drawable, null, null);
            radioButton.setText(texts[i]);
            radioButton.setTextSize(10);
            radioButton.setTextColor(Color.GRAY);
            radioButton.setGravity(Gravity.CENTER_HORIZONTAL);
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(0, RadioGroup.LayoutParams.MATCH_PARENT, 1.0f);
            layoutParams.setMargins(0, dp2px(10), 0, 0);
            radioButton.setLayoutParams(layoutParams);
            numberItem.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f));
            radioButtons.add(radioButton);
            numberItems.add(numberItem);
            radio_group.addView(radioButton);
            number_group.addView(numberItem);
        }
    }

    public void serDataViewPager(Activity activity, int[] image, String[] texts, ViewPager viewpager) {
        this.viewpager=viewpager;
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
Log.e("position",position+"");
               radio_group.check(position+1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        baseSet(image, texts);
    }
}
