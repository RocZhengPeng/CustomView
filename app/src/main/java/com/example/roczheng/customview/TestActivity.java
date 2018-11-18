package com.example.roczheng.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private ListView listViewOne;
    private ListView listViewTow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        listViewOne = (ListView) findViewById(R.id.lv_one);
        listViewTow = (ListView) findViewById(R.id.lv_tow);
        List<String> listOne = new ArrayList<>();
        List<String> listTow = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            listOne.add("测试" + i);
            listTow.add("哈哈" + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, listOne);
        listViewOne.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, listTow);
        listViewTow.setAdapter(adapter2);
    }
}
