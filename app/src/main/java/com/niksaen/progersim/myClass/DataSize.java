package com.niksaen.progersim.myClass;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class DataSize {

    SharedPreferences data;

    public DataSize(Activity activity) {
        data = activity.getSharedPreferences("data", Context.MODE_PRIVATE);
    }

    public int getData1() {
        return data.getInt("Data1", 0);
    }

    public void setData1(int value) {
        data.edit().putInt("Data1", value).apply();
    }

    public int getData2() {
        return data.getInt("Data2", 0);
    }

    public void setData2(int value) {
        data.edit().putInt("Data2", value).apply();
    }

    public int getData3() {
        return data.getInt("Data3", 0);
    }

    public void setData3(int value) {
        data.edit().putInt("Data3", value).apply();
    }

    public int getData4() {
        return data.getInt("Data4", 0);
    }

    public void setData4(int value) {
        data.edit().putInt("Data4", value).apply();
    }

    public int getData5() {
        return data.getInt("Data5", 0);
    }

    public void setData5(int value) {
        data.edit().putInt("Data5", value).apply();
    }

    public int getData6() {
        return data.getInt("Data6", 0);
    }

    public void setData6(int value) {
        data.edit().putInt("Data6", value).apply();
    }
}
