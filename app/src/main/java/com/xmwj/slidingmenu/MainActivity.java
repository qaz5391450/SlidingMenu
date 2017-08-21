package com.xmwj.slidingmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        init();
    }

    private void init() {
        final List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("置顶");
        }
        MyAdapter myAdapter = new MyAdapter(data, this);
        myAdapter.setOnMenuClickListener(new MyAdapter.OnMenuClickListener() {
            @Override
            public void onClick(int position, boolean top) {
                data.set(position, top ? "取消置顶" : "置顶");
            }
        });
        mRecyclerView.setAdapter(myAdapter);
    }
}
