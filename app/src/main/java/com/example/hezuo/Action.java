package com.example.hezuo;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Action extends AppCompatActivity implements View.OnClickListener
{
    Spinner xiaochehao;
    String[] mId={"1","2","3"};
    String ID;/*选中后的小车ID*/
    String time;/*当前时间*/
    String a;
    Button xiaochekai,xiaocheguan,chaxun,chongzhi;
    TextView textView1;
    EditText editText;
    final String START = "Start";
    final String STOP = "Stop";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);


        LitePal.getDatabase();/*创建数据库*/


        Button buttontiao = findViewById(R.id.tiao);
        buttontiao.setOnClickListener(this);


        xiaochekai = findViewById(R.id.xiaochekai);
        xiaochekai.setOnClickListener(this);
        xiaocheguan = findViewById(R.id.xiaocheguan);
        xiaocheguan.setOnClickListener(this);
        chaxun = findViewById(R.id.chaxun);
        chaxun.setOnClickListener(this);
        chongzhi = findViewById(R.id.chongzhi);
        chongzhi.setOnClickListener(this);
        editText = findViewById(R.id.shurujiner);
        textView1 = findViewById(R.id.shengyvjiner);
        xiaochehao = findViewById(R.id.xuanzhexiaoche);
        xiaochehao.setAdapter(new ArrayAdapter<>(Action.this,android.R.layout.simple_list_item_1,mId));
        xiaochehao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ID = mId[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.xiaochekai:
                Lianjie.kongzhi(ID,START);
                break;
            case R.id.xiaocheguan:
                Lianjie.kongzhi(ID,STOP);
                break;

            case R.id.chaxun:
                new Thread(new Runnable() {
                    String gai;
                    @Override
                    public void run() {
                        gai = Lianjie.chaXun(ID);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView1.setText(gai);
                            }
                        });
                    }
                }).start();
                break;

            case R.id.chongzhi:
                a = editText.getText().toString();
                jinggao(a);
                break;


            case R.id.tiao:
                Intent intent = new Intent(Action.this,Activity8.class);
                startActivity(intent);
                break;
        }

    }
    @SuppressLint("ResourceType")
    private void jinggao(final String s){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        time = simpleDateFormat.format(date);

        AlertDialog.Builder dialog = new AlertDialog.Builder(Action.this);
        dialog.setTitle(R.drawable.ic_launcher_background);
        dialog.setTitle("充值：");
        dialog.setMessage("在"+time+"您要充值"+s+"元？");
        dialog.setCancelable(false);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /*将数据保存到数据库里*/
                ShuJv sj = new ShuJv(ID,s,time);
                sj.save();/*将数据保存到 自己创建的数据库里*/
                Lianjie.chongZhi(ID,s);
            }
        });
        dialog.setNeutralButton("忽略", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog.show();

    }
}
