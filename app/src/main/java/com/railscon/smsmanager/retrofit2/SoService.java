package com.railscon.smsmanager.retrofit2;

import com.railscon.smsmanager.SmsMessage;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SoService {
    @GET("get-phones")
    Call<SmsMessage[]> getPhoneRecords();

}
