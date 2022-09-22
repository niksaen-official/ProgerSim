package com.niksaen.progersim.miniGame;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.niksaen.progersim.MainActivity;
import com.niksaen.progersim.MainPage;
import com.niksaen.progersim.R;
import com.niksaen.progersim.myClass.Custom;
import com.niksaen.progersim.myClass.DebugDialog;
import com.niksaen.progersim.myClass.ItemClickSupport;
import com.niksaen.progersim.myClass.LoadData;
import com.niksaen.progersim.myClass.Work;

import java.util.Timer;

public class MiniGame1 {
    Context context;

    String[] words = {
            "if", "else", "switch",
            "case", "i++", "++i",
            "c--", ",", ";",
            ":", "=", "bool", "date",
            "video", "text", "host",
            "port", "int", "char", "str",
            "RU","{}","[]","()","EN","timer",
            "//","||","or","<",">","and","&&",
            "/**","*","+","-","=","<=",">=","@",
            "arr[i]","for","true","while","false",
            "get()","set()","stop()","play()",".","do"
    };
    String[] wordsForPlay = new String[1];
    String wordForFind;

    public MiniGame1(Context context){
        this.context = context;
    }

    public void start(String infoText){
        initWordsForPlay();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog dialog;
        View main = LayoutInflater.from(context).inflate(R.layout.mini_game1,null);
        ProgressBar timer = main.findViewById(R.id.timer);

        TextView textForFind = main.findViewById(R.id.forFind);
        textForFind.setTypeface(Typeface.createFromAsset(context.getAssets(),"font.ttf"));
        wordForFind = wordsForPlay[Work.random(0,8)];
        textForFind.setText(wordForFind);

        RecyclerView keypad = main.findViewById(R.id.keypad);
        Adapter adapter = new Adapter(context,wordsForPlay);
        keypad.setAdapter(adapter);

        builder.setView(main);
        dialog = builder.create();
        dialog.setCancelable(false);

        ItemClickSupport.addTo(keypad).setOnItemClickListener((recyclerView, position, v) -> {
            if(wordsForPlay[position].equals(wordForFind)){
                Custom.Info(context,infoText);
                MainPage.money += 200;
                MainPage.setViewData();
                dialog.dismiss();
            }else{
                dialog.dismiss();
            }
        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                timer.setProgress(timer.getProgress()-1);
                if(timer.getProgress() == 0){
                    dialog.dismiss();
                }
                handler.postDelayed(this,10);
            }
        },10);

        dialog.show();
    }

    private void initWordsForPlay(){
        int i = 1;
        wordsForPlay[0] = words[Work.random(0,words.length-1)];
        do {
            String buff = words[Work.random(0,words.length-1)];
            if(!Work.contains(wordsForPlay,buff)){
                wordsForPlay = Work.add(wordsForPlay,buff);
                i++;
            }
        }while (i<9);
    }
}
