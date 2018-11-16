package com.example.roczheng.customview;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.roczheng.customview.view.BadViewPage;
import com.example.roczheng.customview.view.FixListView;

import java.util.ArrayList;
import java.util.List;

public class ScrollConflictActivity extends AppCompatActivity {

    private BadViewPage mViewPager;
    private List<View> mViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_conflict);
        initViews();
    }

    protected void initViews() {
        mViewPager = (BadViewPage) findViewById(R.id.viewpager);
        mViews = new ArrayList<>();
        initData(true);
    }

    protected void initData(final boolean isListView) {
        for (int i = 0; i < 4; i++) {
            //初始化mViews列表
            View view;
            if (isListView) {
                FixListView listView = new FixListView(this);
                ArrayList<String> datas = new ArrayList<>();
                for (int j = 0; j < 20; j++) {
                    datas.add("data" + j);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);
                //设置适配器
                listView.setAdapter(adapter);
                view = listView;
            } else {
                TextView textView = new TextView(this);
                textView.setTextColor(Color.BLACK);
                textView.setTextSize(28f);
                textView.setGravity(Gravity.CENTER);
                textView.setClickable(true);
                textView.setText("哈哈哈" + i);
                view = textView;
            }
            mViews.add(view);
        }
        mViewPager.setAdapter(new BasePagerAdapter());

    }

    class BasePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViews.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViews.get(position));
            return mViews.get(position);
        }
    }
}
