package com.railscon.smsmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.railscon.smsmanager.MESSAGE";

    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.tv1);
    }

    public void onClickSetting(View view) {


        Intent intent = new Intent(this, SettingActivity.class);

        String message = "fahdfasdf";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }

    public void onClickStart(View view) {

    }
}
