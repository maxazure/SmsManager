package com.railscon.smsmanager;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.railscon.smsmanager.Helper.SmsHelper;
import com.railscon.smsmanager.Services.SmsService;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.railscon.smsmanager.MESSAGE";

    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final int REQUEST_CODE_ASK_PERMISSIONS = 123;
        PackageManager pm = getPackageManager();
        boolean permission = (PackageManager.PERMISSION_GRANTED ==
                pm.checkPermission("android.permission.READ_SMS", "com.railscon.smsmanager"));
        if (permission) {

        }else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{"android.permission.READ_SMS"}, REQUEST_CODE_ASK_PERMISSIONS);
        }




        tv1 = findViewById(R.id.tv1);
    }

    public void onClickSetting(View view) {


        Intent intent = new Intent(this, SettingActivity.class);

        String message = "I'm in.";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }

    public void onClickStart(View view) {


        Intent intent = new Intent(this, SmsService.class);
        startService(intent);
//        String txt = "Hi Jay, a friendly reminder your appointment will be at 7 pm on 24 mar. If you need to make any changes to this appointment please CALL Jim on 088 999-7777. This is an automated message, please do not reply via text.";
//        SmsHelper.sendSMS("5556",txt);
    }

}
