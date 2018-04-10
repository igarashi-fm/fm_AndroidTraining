package com.hogehoge.fmandroidtraining;

import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Bundle;
import android.os.Handler;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import android.widget.*;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    //数量
    public  int stack = 0;
    static ArrayList data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*数量表示*/
        //テスト用初期値
        stack = 1000;
        TextView textView = findViewById(R.id.text_view1);
        textView.setText("数量" + castNumberForString(stack));

        /*　[ + ]ボタンの処理*/
        findViewById(R.id.plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stack < 9999) {
                    stack++;
                }
                TextView textView = findViewById(R.id.text_view1);
                textView.setText("数量" + castNumberForString(stack));
            }
        });
        /*　[ - ]ボタンの処理*/
        findViewById(R.id.minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stack > 0) {
                    stack--;
                }
                TextView textView = findViewById(R.id.text_view1);
                textView.setText("数量" + castNumberForString(stack));
            }
        });

        /* 時刻表示 */
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //時刻を表示するTextView
                TextView dateText = findViewById(R.id.time1);
                dateText.setText(getNowTime());
                //1秒間隔で実行
                handler.postDelayed(this,1000);
            }
        };
        handler.post(runnable);


         /* [ 追加 ]ボタンの処理 */
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addListView();
            }
        });

        /*[ クリア ]*/
        /*[ 選択された合計数量 ]*/
    }



    @Override
    protected void onResume(){
        super.onResume();

    }



    /*
    　文字列にキャストして通貨表示するopを付けて返す関数
    　引数:int
    　戻り値:String(3桁目と4桁目の間ににカンマが入る)
    */
    private String castNumberForString(int num){
        String numStr = String.format("%,d",num);
        return numStr;
    }
    /*
    　ボタン作成関数 引数で与えられたテキストのボタンを作る。
    　引数:String
    　戻り値:button
    */
    private Button createButton(String text) {
        Button button = new Button(this);
        button.setText(text);
        return button;
    }
    /*
    　チェックボックス作成関数 引数で与えられたチェックボックスを作る。
    　引数:String
    　戻り値:checkbox
     */
    private CheckBox createCheckBox(String text){
        CheckBox checkBox = new CheckBox(this);
        checkBox.setText(text);
        return checkBox;
    }

    private String getNowTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("kk':'mm':'ss");
        return sdf.format(date);
    }

    private void addTableRow(){
        TableLayout table = findViewById(R.id.listView);

        TableRow tableRow = new TableRow(this);
        TableLayout.LayoutParams lp = new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        table.addView(tableRow);
        //layout.addView(createCheckBox(sdf.format(date) + " " + castNumberForString(stack)));
        tableRow.addView(createCheckBox(castNumberForString(stack)));
        tableRow.addView(createButton("削除"));
    }

    private void addListView(){
        data.add(getNowTime());

        // リスト項目とListViewを対応付けるArrayAdapterを用意する
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

}
