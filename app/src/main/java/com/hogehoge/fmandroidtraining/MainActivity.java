package com.hogehoge.fmandroidtraining;




import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.os.Bundle;
import android.os.Handler;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.widget.*;
import android.view.View;



public class MainActivity extends AppCompatActivity {


    static  int stack = 0;  //数量
    static String comment = ""; //コメント
    static ArrayList data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_main);

        /*数量表示*/
        stack = 1000;        //テスト用初期値
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


        /* [コメント]フィールド */
        final EditText editText = findViewById(R.id.comment1);
        //入力した文字数で時刻や追加ボタンがずれていくので大きさを固定する。
        editText.setWidth(editText.getWidth());
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                comment = editable.toString();
            }
        });

        //背景を押したときにソフトウェアキーボードを隠す
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });




         /* [ 追加 ]ボタンの処理 */
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addListView(stack,comment);
            }
        });

        /*[ クリア ]*/
        /*[ 選択された合計数量 ]*/



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

    /*現在時刻を取得してhh:mm:ss形式で返す*/
    private String getNowTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("kk':'mm':'ss");
        return sdf.format(date);
    }



    private void addListView(int stack,String comment){
        data.add(getNowTime() + " " + castNumberForString(stack) + " " + comment);

        // リスト項目とListViewを対応付けるArrayAdapterを用意する
       // ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);


        ListView listView = findViewById(R.id.listView);
        //listView.setAdapter(adapter);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}


