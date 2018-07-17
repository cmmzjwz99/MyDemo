package com.zjwz.cmm.mydemo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zjwz.cmm.mydemo.scan.ScanActivity;
import com.zjwz.cmm.mydemo.view.FeatureView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        ListView listView = (ListView) findViewById(R.id.lv_main);
        myAdapter = new MyAdapter(this, details);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                startActivity(new Intent(this, ScanActivity.class));
                break;
            case 1:
                break;
            default:
                break;
        }
    }

    private class MyAdapter extends ArrayAdapter {

        public MyAdapter(@NonNull Context context, DemoDetails[] demoDetails) {
            super(context, R.layout.feature, R.id.title, demoDetails);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            FeatureView featureView;
            if (convertView != null) {
                featureView = (FeatureView) convertView;
            } else {
                featureView = new FeatureView(getContext());
            }
            DemoDetails demoDetails = (DemoDetails) getItem(position);
            featureView.setTitle(demoDetails.mStr);
            return featureView;
        }
    }


    public class DemoDetails {
        public int mId;
        public String mStr;

        public DemoDetails(int id, String str) {
            this.mId = id;
            this.mStr = str;
        }
    }

    private DemoDetails[] details = {
            new DemoDetails(0, "二维码扫描"),
            new DemoDetails(1, "定位"),
            new DemoDetails(2,"recyle")
    };
}
