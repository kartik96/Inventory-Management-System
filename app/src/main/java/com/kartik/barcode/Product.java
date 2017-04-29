package com.kartik.barcode;

/**
 * Created by Kartik on 11-07-2016.
 */
public class Product
{
    String Barcode;
    String Pname;
    String Date;
    String Time;
    String Discription;

    public Product()
    {

    }

    public Product(String Barcode1 ,String Pname1,String Date1,String Time1,String Discription1)
    {
        this.Barcode=Barcode1;
        this.Pname=Pname1;
        this.Date=Date1;
        this.Time=Time1;
        this.Discription=Discription1;
    }

   /* public String getBarcode() {
        return Barcode;
    }

    public String getPname() {
        return Pname;
    }

    public String getDate() {
        return Date;
    }

    public String getTime() {
        return Time;
    }

    public String getDiscription() {
        return Discription;
    }*/
}