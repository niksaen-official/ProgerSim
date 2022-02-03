package com.niksaen.progersim.myClass;

import android.app.Activity;

import java.util.Arrays;
import java.util.Random;

public class Work {
    Activity activity;

    public Work(Activity activity) {
        this.activity = activity;
    }

    /**
     * конвертация массива в строку
     */
    public String arrayToString(String[] array) {
        String res = "";
        for (int i = 0; i < array.length; i++) {
            res += array[i] + ",";
        }
        return res;
    }

    /**
     * проверка массива на пустоту, если массив пуст возвращает true
     */
    public boolean arrayIsEmpty(String[] array) {
        boolean isEmpty = true;
        int countAll = array.length - 1;
        int countEmpty = array.length - 1;
        for (String item : array) {
            if (!item.equals("")) {
                countEmpty--;
            }
        }
        if (countAll != countEmpty) {
            isEmpty = false;
        }
        return isEmpty;
    }

    public static int random(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    // добавление элемента в массив
    public static String[] add(String[] arr, String str){
        String[] stringsBuff = new String[arr.length+1];
        for (int i = 0;i<stringsBuff.length; i++) {
            if(i == stringsBuff.length-1){
                stringsBuff[i] = str;
            }else {
                stringsBuff[i] = arr[i];
            }
        }
        return stringsBuff;
    }

    //удаление из массива пустых значений
    public static String[] filter(String[] str){
        String[] strings = new String[0];
        for (String s:str) {
            if(!s.equals("")){
                strings = add(strings,s);
            }
        }
        return strings;
    }

    public static boolean contains(String[] arr,String str){
        for (String s: arr) {
            if(s.equals(str)) return true;
        }
        return false;
    }
}
