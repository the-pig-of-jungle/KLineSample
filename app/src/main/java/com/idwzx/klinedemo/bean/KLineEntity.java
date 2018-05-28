package com.idwzx.klinedemo.bean;

public class KLineEntity {

    public String getDatetime() {
        return Date;
    }

   
    public float getOpenPrice() {
        return Open / 10000 ;
    }

   
    public float getHighPrice() {
        return High / 10000;
    }

   
    public float getLowPrice() {
        return Low / 10000;
    }

   
    public float getClosePrice() {
        return Close / 10000;
    }

   
    public float getMA5Price() {
        return MA5Price / 10000;
    }

   
    public float getMA10Price() {
        return MA10Price;
    }

   
    public float getMA20Price() {
        return MA20Price;
    }

   
    public float getChangeHandRatio() {
        return HandChangeRatio;
    }

   
    public float getDea() {
        return dea;
    }

   
    public float getDif() {
        return dif;
    }

   
    public float getMacd() {
        return macd;
    }

   
    public float getK() {
        return k;
    }

   
    public float getD() {
        return d;
    }

   
    public float getJ() {
        return j;
    }

   
    public float getRsi1() {
        return rsi1;
    }

   
    public float getRsi2() {
        return rsi2;
    }

   
    public float getRsi3() {
        return rsi3;
    }

   
    public float getUp() {
        return up;
    }

   
    public float getMb() {
        return mb;
    }

   
    public float getDn() {
        return dn;
    }

   
    public float getVolume() {
        return Volume;
    }

   
    public float getAmount() {
        return Amount;
    }

   
    public float getRisingFalling() {
        return Change;
    }

   
    public float getRisingFallingRange() {
        return ChangeRange;
    }

   
    public float getPreClose() {
        return PreClose;
    }

    float PreClose;
    public void setPreClose(float preClose){
        PreClose = preClose;
    }

   
    public float getMA5Volume() {
        return MA5Volume;
    }

   
    public float getMA10Volume() {
        return MA10Volume;
    }


    public String Date;
    public float Open;
    public float High;
    public float Low;
    public float Close;
    public float Volume;

    public float MA5Price;

    public float MA10Price;

    public float MA20Price;

    public float dea;

    public float dif;

    public float macd;

    public float k;

    public float d;

    public float j;

    public float rsi1;

    public float rsi2;

    public float rsi3;

    public float up;

    public float mb;

    public float dn;

    public float MA5Volume;

    public float MA10Volume;


    public float Amount;
    public float Change;
    public float ChangeRange;

    public float HandChangeRatio;

    public float CCI;

    public float getHandChangeRatio() {
        return HandChangeRatio;
    }

    public void setHandChangeRatio(float handChangeRatio) {
        HandChangeRatio = handChangeRatio;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setAmount(float amount) {
        Amount = amount;
    }

    public float getChange() {
        return Change;
    }

    public void setChange(float change) {
        Change = change;
    }

    public float getChangeRange() {
        return ChangeRange;
    }

    public void setChangeRange(float changeRange) {
        ChangeRange = changeRange;
    }

   
    public float getCCI() {
        return CCI;
    }

    public float OBV;
   
    public float getOBV() {
        return OBV;
    }

    public float SAR;
   
    public float getSAR() {
        return SAR;
    }
}
