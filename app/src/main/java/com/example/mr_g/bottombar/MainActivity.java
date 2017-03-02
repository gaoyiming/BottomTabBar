package com.example.mr_g.bottombar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.mr_gao.bottombar.BottomBarView;
import com.mr_gao.bottombar.BottomNavigaterView;
import com.mr_gao.bottombar.BottomTabView;
import com.mr_gao.bottombar.ItemClickListener;
import com.mr_gao.bottombar.RepeatClickListener;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigaterView bottom_tab = (BottomNavigaterView) findViewById(R.id.bottom_tab);
        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
//        BottomBarView bottombar = (BottomBarView) findViewById(R.id.bottombar);
        String[]  s={ "首页","活动","购物车","我的"};

        int[] image = { R.drawable.selector_button_presell,R.drawable.selector_button_active, R.drawable.selector_button_cart,
                 R.drawable.selector_button_mine};
        ArrayList<Fragment> fragments = new ArrayList<>();
        BlankFragment blankFragment = new BlankFragment();
        BlankFragmentTwo blankFragmenttwo = new BlankFragmentTwo();
        BlankFragmentThree blankFragmentthree = new BlankFragmentThree();
        BlankFragmentFour blankFragmentfour = new BlankFragmentFour();
        fragments.add(blankFragment);
        fragments.add(blankFragmenttwo);
        fragments.add(blankFragmentthree);
        fragments.add(blankFragmentfour);

       // bottom_tab.setDataFragments(this,image,s,fragments,R.id.framelayout);
        viewpager.setAdapter(new MyAdapter(getSupportFragmentManager(),fragments));
        bottom_tab.serDataViewPager(this,image,s,viewpager);
        bottom_tab.setItemClickListener(new ItemClickListener() {
            @Override
            public void click(int i) {
                Log.e("click",""+i);
            }
        });
        bottom_tab.setRepeatClickListener(new RepeatClickListener() {
            @Override
            public void repeatClick(int currentitem) {
                Log.e("repeatClick",""+currentitem);
            }
        });
       bottom_tab.setNumber(3,88);
//        assert bottombar != null;
//        bottombar.setOnSelectChanged(new BottomBarView.SelectChangedListener() {
//            @Override
//            public void changeTo(int index) {
//                Log.e("inder",index+"");
//            }
//        });
//        bottombar.setUpWithView(image,s);
    }
}
