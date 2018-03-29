package com.railscon.smsmanager.Services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import com.railscon.smsmanager.Helper.TimeHelper;
import com.railscon.smsmanager.SMSServiceReceiver;
import com.railscon.smsmanager.model.SmsMessage;
import com.railscon.smsmanager.retrofit2.RetrofitClient;
import com.railscon.smsmanager.retrofit2.SoService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.railscon.smsmanager.Helper.SmsHelper.sendSMS;

public class SmsService extends Service {

    private List<SmsMessage> smsList;
    private String lastNumber="";

    public String getLastnumber(){
        return lastNumber;
    }

    public void setLastNumber(String num){
        this.lastNumber = num;
    }
    public SmsService() {
       // initList();
    }

    private void initList(){
        smsList = new ArrayList<SmsMessage>();
        for(int i=0; i<10; i++){
            SmsMessage sms = new SmsMessage();
            sms.setMessageBody("hi Jay this is " + Integer.toString(i));
            sms.setNumber("5556");
            smsList.add(sms);
        }

    }

    @Override
    public void onCreate() {
        System.out.println("onCreate invoke");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int interval = 1*1000*60;
        long triggerAtTime = SystemClock.elapsedRealtime() + interval;
        Intent i = new Intent(this, SMSServiceReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);

        getPhones();

        return super.onStartCommand(intent, flags, startId);

    }


    private void getPhones() {

        Retrofit retrofit = RetrofitClient.getClient();
        SoService service = retrofit.create(SoService.class);

        Log.d("maxazure","getphones");
        Call<SmsMessage[]> call = service.getPhoneRecords();
        call.enqueue(new Callback<SmsMessage[]>() {
                         @Override
                         public void onResponse(Call<SmsMessage[]> call, final Response<SmsMessage[]> response) {


                             Log.d("maxazure",Integer.toString(response.body().length));

                             new Thread(new Runnable() {
                                 @Override
                                 public void run() {
                                     for (SmsMessage smsMessage : response.body()) {

                                         try {
                                             if(!getLastnumber().equals(smsMessage.getNumber())){
                                                 sendSMS(smsMessage.getNumber(),smsMessage.getMessageBody()+ TimeHelper.getNowTime());
                                                 setLastNumber(smsMessage.getNumber());
                                                 Log.d("maxazure",smsMessage.getNumber()+ TimeHelper.getNowTime());
                                                 Thread.sleep(5000);
                                             }


                                         } catch (InterruptedException e) {
                                             e.printStackTrace();
                                         }
                                     }
                                 }
                             }).run();

                         }

                         @Override
                         public void onFailure(Call<SmsMessage[]> call, Throwable t) {

                             Log.d("maxazure","get Failure");
                             Log.d("maxazure",t.getMessage());
                         }

                     });


    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        // The service is no longer used and is being destroyed
        //
    }

}
