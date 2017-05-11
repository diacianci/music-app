package com.example.diaci.tunami;

/**
 * Created by diaci on 4/22/2017.
 */

public class Tuning {

    String tuningName;
    String highEString;
    String bString;
    String gString;
    String dString;
    String aString;
    String lowEString;

    public Tuning (String name, String highE, String b, String g, String d, String a, String lowE)
    {
        tuningName = name;
        highEString = highE;
        bString = b;
        gString = g;
        dString = d;
        aString = a;
        lowEString = lowE;

    }

    public void setAllStrings(String highE, String b, String g, String d, String a, String lowE)
    {
        highEString = highE;
        bString = b;
        gString = g;
        dString = d;
        aString = a;
        lowEString = lowE;
    }

    public String getTuningName() { return tuningName; }

    public void setTuningName(String name) { this.tuningName = name; }

    public String getHighEString() {
        return highEString;
    }

    public void setHighEString(String highEString) {
        this.highEString = highEString;
    }

    public String getbString() {
        return bString;
    }

    public void setbString(String bString) {
        this.bString = bString;
    }

    public String getgString() {
        return gString;
    }

    public void setgString(String gString) {
        this.gString = gString;
    }

    public String getdString() {
        return dString;
    }

    public void setdString(String dString) {
        this.dString = dString;
    }

    public String getaString() {
        return aString;
    }

    public void setaString(String aString) {
        this.aString = aString;
    }

    public String getLowEString() {
        return lowEString;
    }

    public void setLowEString(String lowEString) {
        this.lowEString = lowEString;
    }


    public String toString()
    {
        return lowEString + " " + aString + " " + dString + " " + gString + " " + bString + " " + highEString;
    }
}
