package com.railscon.smsmanager.Helper;

import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Created by maxazure on 2018/3/25.
 */

public class SmsHelper {

    public static void sendSMS(String phoneNumber, String message) {
        // 获取短信管理器
        android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
        Log.d("maxazure",phoneNumber);
        // 拆分短信内容（手机短信长度限制）
        List<String> divideContents = smsManager.divideMessage(message);
        for (String text : divideContents) {
            smsManager.sendTextMessage(phoneNumber, null, text, null, null);
        }
    }

}
