package com.kartik.barcode;

/**
 * Created by Kartik on 03-10-2016.
 */

public class chatclass {
    String email;
    String message;

    chatclass(){}
    chatclass(String email,String message)
    {
        this.email = email;
        this.message = message;
    }
    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }

}
