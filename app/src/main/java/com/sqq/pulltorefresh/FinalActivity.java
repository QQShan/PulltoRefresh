package com.sqq.pulltorefresh;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.TextView;

import com.sqq.pulltorefresh.RefreshView.OnLoadListener;
import com.sqq.pulltorefresh.sqqrecyclerview.SqqRecyclerview;
import com.sqq.pulltorefresh.sqqrecyclerview.BaseSqqAdapter;
import com.sqq.pulltorefresh.sqqrecyclerview.BaseSqqViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 */
public class FinalActivity extends AppCompatActivity {
    List<String> stt = new ArrayList<>();
    String[] st={"第一条数据","sdfas","sdfas","sdfas","sdfas","sdfas","sdfas","sdfas"
            ,"sdfas","sdfas","sdfas","sdfas"
            ,"sdfas","sdfas","sdfas","sdfas"
            ,"sdfas","sdfas","sdfas","sdfas"
            ,"sdfas","sdfas","sdfas","sdfas"
            ,"sdfas","sdfas","sdfas","sdfas"
            ,"sdfas","sdfas","sdfas","sdfas"/*
            ,"sdfas","sdfas","sdfas","sdfas"
            ,"sdfas","sdfas","sdfas","sdfas"
            ,"sdfas","sdfas","sdfas","sdfas"
            ,"sdfas","sdfas","sdfas","sdfas"*/
            ,"sdfas","sdfas","sdfas","最后一条数据"};

    SqqRecyclerview sqqRecyclerview;
    BaseSqqAdapter bs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        for (int i = 0; i < st.length; i++) {
            stt.add(st[i]);
        }

        sqqRecyclerview = (SqqRecyclerview) findViewById(R.id.sqq);
        //sqqRecyclerview.setLayoutManager(new GridLayoutManager(this, 2));
        /**
         * 刷新数据像现在这样写，而不用notifyDataSetChanged
         * 使用瀑布流的时候会有问题
         */
        /*sqqRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));*/
        sqqRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        bs = new BaseSqqAdapter() {
            @Override
            protected void onBindView(BaseSqqViewHolder holder, int position) {
                TextView tv = holder.getView(R.id.text);
                tv.setText(stt.get(position));
            }

            @Override
            protected int getLayoutID() {
                return R.layout.textview;
            }

            @Override
            public int getItemCount() {
                return stt.size();
            }

        };

        sqqRecyclerview.setAdapter(bs);

        sqqRecyclerview.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        /*stt.add(0, "sqwsagd");*/

                        bs = new BaseSqqAdapter() {
                            @Override
                            protected void onBindView(BaseSqqViewHolder holder, int position) {
                                Log.d("holder", "ssss" + position);
                                TextView tv = holder.getView(R.id.text);
                                tv.setText(stt.get(position));
                            }

                            @Override
                            protected int getLayoutID() {
                                return R.layout.textview;
                            }

                            @Override
                            public int getItemCount() {
                                return stt.size();
                            }

                        };

                        sqqRecyclerview.setAdapter(bs);
                        bs.notifyDataSetChanged();
                        sqqRecyclerview.setRefreshing(false);
                    }
                }, 5000);
            }
        });
        sqqRecyclerview.setOnLoadListener(new OnLoadListener() {
            @Override
            public void OnLoadListener() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //stt.add("sqq");
                        //bs.notifyDataSetChanged();
                        sqqRecyclerview.loadError();
                        //sqqRecyclerview.endLoadRefresh();
                    }
                }, 5000);
            }
        });
    }
}
