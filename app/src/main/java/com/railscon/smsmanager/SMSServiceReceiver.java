package com.railscon.smsmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.railscon.smsmanager.Services.SmsService;

public class SMSServiceReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Intent i = new Intent(context, SmsService.class);
        context.startService(i);

    }
}
