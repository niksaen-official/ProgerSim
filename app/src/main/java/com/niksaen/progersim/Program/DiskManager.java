package com.niksaen.progersim.Program;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niksaen.progersim.R;
import com.niksaen.progersim.myClass.Custom;
import com.niksaen.progersim.myClass.DataSize;
import com.niksaen.progersim.myClass.LoadData;

import java.util.HashMap;

public class DiskManager {
    Activity activity;
    LoadData loadData = new LoadData();
    DataSize dataSize;

    public DiskManager(Activity act) {
        activity = act;
        dataSize = new DataSize(activity);
        loadData.setActivity(activity);
    }

    public void start() {
        dialog();
    }

    HashMap<String,String> words;
    void dialog() {
        words = new Gson().fromJson(new Custom(activity).getStringInAssets(activity,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.disk_manager_dialog, null);
        Typeface typeface = Typeface.createFromAsset(activity.getAssets(), "font.ttf");
        TextView
                title = layout.findViewById(R.id.title),
                text1 = layout.findViewById(R.id.text1),
                text2 = layout.findViewById(R.id.text2),
                text3 = layout.findViewById(R.id.text3),
                text4 = layout.findViewById(R.id.text4),
                text5 = layout.findViewById(R.id.text5),
                text6 = layout.findViewById(R.id.text6);
        title.setTypeface(typeface, Typeface.BOLD);
        title.setText(words.get("Disk manager"));

        if(!loadData.getYouData1().equals("")) {
            text1 = setDiskSize(text1, 1);
            text1.setVisibility(View.VISIBLE);
        }
        if(!loadData.getYouData2().equals("")) {
            text2 = setDiskSize(text2, 2);
            text2.setVisibility(View.VISIBLE);
        }
        if(!loadData.getYouData3().equals("")) {
            text3 = setDiskSize(text3, 3);
            text3.setVisibility(View.VISIBLE);
        }
        if (!loadData.getYouData4().equals("")){
            text4 = setDiskSize(text4, 4);
            text4.setVisibility(View.VISIBLE);
        }
        if(!loadData.getYouData5().equals("")) {
            text5 = setDiskSize(text5, 5);
            text5.setVisibility(View.VISIBLE);
        }
        if(!loadData.getYouData6().equals("")) {
            text6 = setDiskSize(text6, 6);
            text6.setVisibility(View.VISIBLE);
        }


        builder.setView(layout);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        Button close = layout.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    TextView setDiskSize(TextView textView, int diskID) {
        int[] diskSize = {dataSize.getData1(), dataSize.getData2(), dataSize.getData3(), dataSize.getData4(), dataSize.getData5(), dataSize.getData6()};
        int disk = 0;
        String diskSizeAll = "";
        String diskName;
        String[] DATA = {loadData.getYouData1(), loadData.getYouData2(), loadData.getYouData3(), loadData.getYouData4(), loadData.getYouData5(), loadData.getYouData6()};
        if (!DATA[diskID - 1].equals("")) {
            switch (DATA[diskID - 1]) {
                case "ZShark 128S-2500": {
                    diskSizeAll = "128Gb";
                    disk = diskSize[diskID - 1];
                    break;
                }
                case "ZShark 256S-4000": {
                    diskSizeAll = "256Gb";
                    disk = diskSize[diskID - 1];
                    break;
                }
                case "Offside 512Gb-2500": {
                    diskSizeAll = "512Gb";
                    disk = diskSize[diskID - 1];
                    break;
                }
                case "Offside 1Tb-5000": {
                    diskSizeAll = "1204Gb";
                    disk = diskSize[diskID - 1];
                    break;
                }
            }
        } else {
            textView.setVisibility(View.GONE);
        }
        Typeface typeface = Typeface.createFromAsset(activity.getAssets(), "font.ttf");
        textView.setText("DATA " + diskID + ": \n" + DATA[diskID - 1] + "\n" + disk + "/" + diskSizeAll);
        textView.setTypeface(typeface, Typeface.BOLD);
        return textView;
    }
}
