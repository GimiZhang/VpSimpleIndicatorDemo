package com.dfhe.vpsimpleindicatordemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private List<String> mTitles = Arrays.asList("首页","娱乐","个人");
    private VpSimpleIndicator mIndicator;
    private ViewPager mViewPager;
    private List<VpSimpleFragment> mContents = new ArrayList<>();
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private FragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initViews();
        initDatas();
        mViewPager.setAdapter(mAdapter);

        //监听ViewPager滑动页面的改变
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //让三角形也跟着改变
                mIndicator.scroll(position,positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                //当前页面被选中的时候，改变选中页面的字体颜色
                if(position == 0){
                    setFocusTextColor(tv1,tv2,tv3);
                }else if(position == 1){
                    setFocusTextColor(tv2,tv1,tv3);
                }else{
                    setFocusTextColor(tv3,tv2,tv1);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setFocusTextColor(TextView tvFocus, TextView tvOther1, TextView tvOther2) {
        tvFocus.setTextColor(Color.BLUE);
        tvOther1.setTextColor(Color.parseColor("#CCFFFFFF"));
        tvOther2.setTextColor(Color.parseColor("#CCFFFFFF"));
    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        for(String title : mTitles){
            VpSimpleFragment fragment =VpSimpleFragment.newInstance(title);
            mContents.add(fragment);
        }
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        mIndicator = (VpSimpleIndicator) findViewById(R.id.id_indicator);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        tv1 = (TextView) findViewById(R.id.id_tv1);
        tv2 = (TextView) findViewById(R.id.id_tv2);
        tv3 = (TextView) findViewById(R.id.id_tv3);
        tv1.setTextColor(Color.BLUE);//默认选中首页
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mContents.get(position);
            }

            @Override
            public int getCount() {
                return mContents.size();
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_tv1:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.id_tv2:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.id_tv3:
                mViewPager.setCurrentItem(2);
                break;
        }
    }
}
