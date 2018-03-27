package com.railscon.smsmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {
    private TextView textView;
    private EditText etInterval;
    private CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        etInterval = findViewById(R.id.etInterval);
        checkBox = findViewById(R.id.checkBox);
        setTitle("Setting");


        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

       // textView.setText(message);
    }

    @Override
    public void onBackPressed() {

        Toast.makeText(this,etInterval.getText().toString() + " " +checkBox.isChecked() ,Toast.LENGTH_LONG).show();
        super.onBackPressed();
    }
}
