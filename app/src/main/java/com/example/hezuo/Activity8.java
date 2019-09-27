package com.example.hezuo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class Activity8 extends AppCompatActivity {
    Spinner lishi;
    String[] li = {"时间升序","时间降序"};
    String pai;/*用户选中的排序方式*/
    List<ShuJv> mList;
    List<ShuJv> daoZhi = new ArrayList<>();
    LinearLayout bujv;
    Button buttoncha;
    ShuJv ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_8);

        bujv = findViewById(R.id.bujv);

        lishi = findViewById(R.id.lishipaixv);
        lishi.setAdapter(new ArrayAdapter<>(Activity8.this,android.R.layout.simple_list_item_1,li));
        lishi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pai = li[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mList = DataSupport.findAll(ShuJv.class);/*查询数据库里的数据 存放到ArrayList*/
        daoZhi = new ArrayList<>();//12 1 11 2 10
        for (int k=mList.size()-1;k>=0;k--){
            daoZhi.add(mList.get(k));
        }
        buttoncha = findViewById(R.id.lishibutton);
        buttoncha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lishijilu();
            }
        });
    }


    private void lishijilu(){

        bujv.removeAllViews();
        for (int i=0;i<mList.size();i++){
            LinearLayout linearLayout = new LinearLayout(Activity8.this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            for (int j=0;j<4;j++){
                TextView textView = new TextView(Activity8.this);
                textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1));
                textView.setGravity(Gravity.CENTER);/*内容居中*/
                textView.setTextColor(Color.BLACK);
                if(pai.equals("时间升序")){
                    ll = mList.get(i);//1 2 3 4
                }else if(pai.equals("时间降序")){
                    ll = daoZhi.get(i);//12 11 10 9
                }

                switch (j){
                    case 0:
                        textView.setText(ll.getId()+"");
                        break;
                    case 1:
                        textView.setText(ll.getCarId());
                        break;
                    case 2:
                        textView.setText(ll.getMoney()+"元");
                        break;
                    case 3:
                        textView.setText(ll.getTime());
                        break;
                }
                linearLayout.addView(textView);
                TextView kuang = new TextView(Activity8.this);
                kuang.setLayoutParams(new LinearLayout.LayoutParams(2, ViewGroup.LayoutParams.MATCH_PARENT));
                kuang.setBackgroundColor(Color.BLACK);/*设置背景颜色*/
                linearLayout.addView(kuang);

            }
            bujv.addView(linearLayout);
            TextView textViewheng = new TextView(Activity8.this);
            textViewheng.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2));
            textViewheng.setBackgroundColor(Color.BLACK);/*设置背景颜色*/
            bujv.addView(textViewheng);

        }
    }
}
