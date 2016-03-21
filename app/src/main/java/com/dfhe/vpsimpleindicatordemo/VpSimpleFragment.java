package com.dfhe.vpsimpleindicatordemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 项目名称：VpSimpleIndicatorDemo
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2016/3/21 10:20
 * 修改人：Administrator
 * 修改时间：2016/3/21 10:20
 * 修改备注：
 *
 * @param
 */
public class VpSimpleFragment extends Fragment {

    private String mTitle;
    private static final String BUNDLE_TITLE = "title";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if(bundle != null){
            mTitle = bundle.getString(BUNDLE_TITLE);
        }

        TextView tv = new TextView(getActivity());
        tv.setText(mTitle);
        tv.setTextColor(Color.parseColor("#000000"));
        tv.setGravity(Gravity.CENTER);

        return tv;
    }

    /**
     * 对外界提供设置标题的方法
     * @param title
     * @return
     */
    public static VpSimpleFragment newInstance(String title){
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE,title);

        VpSimpleFragment fragment = new VpSimpleFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
