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

public class MiniGame2 {
    Context context;

    int[] digit = {
            0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15
    };

    String[] actions = {"+","-","*"};

    String primer;
    String[] allSolution = new String[1];
    String trueSolution;

    public MiniGame2(Context context){
        this.context = context;
    }

    public void start(String infoText){
        generatePrimerAndTrueSolution();
        initSolution();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog dialog;
        View main = LayoutInflater.from(context).inflate(R.layout.mini_game1,null);
        ProgressBar timer = main.findViewById(R.id.timer);
        timer.setMax(270);
        timer.setProgress(270);

        TextView textForFind = main.findViewById(R.id.forFind);
        textForFind.setTypeface(Typeface.createFromAsset(context.getAssets(),"font.ttf"));
        textForFind.setText(primer);

        RecyclerView keypad = main.findViewById(R.id.keypad);
        Adapter adapter = new Adapter(context,allSolution);
        keypad.setAdapter(adapter);

        builder.setView(main);
        dialog = builder.create();
        dialog.setCancelable(false);

        ItemClickSupport.addTo(keypad).setOnItemClickListener((recyclerView, position, v) -> {
            if(allSolution[position].equals(trueSolution)){
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

    private void initSolution(){
        int i = 1;
        allSolution[0] = String.valueOf(Work.random(-100,1000));
        do {
            String buff = String.valueOf(Work.random(-100,1000));
            if(!Work.contains(allSolution,buff)){
                allSolution = Work.add(allSolution,buff);
                i++;
            }
        }while (i<9);

        allSolution[Work.random(0,8)] = trueSolution;
    }
    private void generatePrimerAndTrueSolution(){
        int digit1 = digit[Work.random(0,14)];
        int digit2 = digit[Work.random(0,14)];
        String action = actions[Work.random(0,2)];
        switch (action){
            case "+":{
                trueSolution = String.valueOf(digit1+digit2);
                break;
            }
            case "-":{
                trueSolution = String.valueOf(digit1-digit2);
                break;
            }
            case "*":{
                trueSolution = String.valueOf(digit1*digit2);
                break;
            }
        }

        primer = digit1+" "+action+" "+digit2+" = ";
    }
}

