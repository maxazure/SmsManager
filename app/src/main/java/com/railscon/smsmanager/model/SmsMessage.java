package com.railscon.smsmanager.model;

/**
 * Created by maxazure on 26/03/2018.
 */

public class SmsMessage {
    private Integer _id;
    private String phone;
    private String messageBody;
    private boolean flag;

    public Integer getId(){
        return _id;
    }
    public void setId(Integer id){
        this._id = id;
    }

    public String getNumber(){
        return phone;
    }
    public void setNumber(String number){
        this.phone = number;
    }

    public String getMessageBody(){
        return messageBody;
    }
    public void setMessageBody(String messageBody){
        this.messageBody = messageBody;
    }

    public boolean getFlag(){
        return flag;
    }
    public void setFlag(boolean flag){
        this.flag = flag;
    }
}
