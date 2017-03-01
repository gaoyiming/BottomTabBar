package com.example.mr_g.bottombar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.mr_gao.bottombar.BottomBarView;
import com.mr_gao.bottombar.BottomTabView;
import com.mr_gao.bottombar.ItemClickListener;
import com.mr_gao.bottombar.RepeatClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomTabView bottom_tab = (BottomTabView) findViewById(R.id.bottom_tab);
//        BottomBarView bottombar = (BottomBarView) findViewById(R.id.bottombar);
        String[]  s={ "首页","活动","购物车","我的"};

        int[] image = { R.drawable.github_circle,R.drawable.github_circle, R.drawable.ic_favorite_black_24dp,
                 R.drawable.github_circle};
        bottom_tab.setData(image,s);
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
