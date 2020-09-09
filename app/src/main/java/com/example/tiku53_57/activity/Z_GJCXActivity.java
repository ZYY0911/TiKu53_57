package com.example.tiku53_57.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.tiku53_57.R;
import com.example.tiku53_57.fragment.GJCXFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @LogIn Name zhangyingyu
 * @Create by 张瀛煜 on 2020-08-28 at 15:36
 */
public class Z_GJCXActivity extends BaseActivity {
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tv_one_station)
    TextView tvOneStation;
    @BindView(R.id.tv_two_station)
    TextView tvTwoStation;
    private List<Fragment> fragments;

    @Override
    public int getLayout() {
        return R.layout.gjgl_layout;
    }

    @Override
    public String setTitle() {
        return "1号站台";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        change.setImageResource(R.mipmap.back3);
        fragments = new ArrayList<>();
        fragments.add(new GJCXFragment(1));
        fragments.add(new GJCXFragment(2));
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    tvOneStation.setBackgroundColor(Color.parseColor("#2196F3"));
                    tvOneStation.setTextColor(Color.WHITE);
                    tvTwoStation.setTextColor(Color.BLACK);
                    tvTwoStation.setBackgroundColor(Color.WHITE);
                    title.setText("1号站台");
                } else {
                    tvTwoStation.setBackgroundColor(Color.parseColor("#2196F3"));
                    tvTwoStation.setTextColor(Color.WHITE);
                    tvOneStation.setTextColor(Color.BLACK);
                    tvOneStation.setBackgroundColor(Color.WHITE);
                    title.setText("2号站台");
                }
                ((GJCXFragment) fragments.get(i)).sendMessage();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        ((GJCXFragment) fragments.get(0)).sendMessage();
    }

    @OnClick({R.id.tv_one_station, R.id.tv_two_station})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_one_station:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv_two_station:
                viewPager.setCurrentItem(1);
                break;
        }
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
