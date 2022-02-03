package com.niksaen.progersim.myClass;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.niksaen.progersim.R;

import java.util.Timer;
import java.util.TimerTask;

public class DebugDialog {

    public static void Error(Context context,String text) {
        final Typeface font = Typeface.createFromAsset(context.getAssets(), "font.ttf");

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.dialog_error, null);

        TextView textView = layout.findViewById(R.id.textView);
        textView.setText(text);
        textView.setTextColor(Color.RED);
        textView.setTypeface(font, Typeface.BOLD);

        builder.setView(layout);
        final AlertDialog dialog=builder.create();
        dialog.show();
        dialog.setCancelable(false);
        final Timer timer = new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.cancel();
                        timer.cancel();
                    }
                });
            }
        };
        timer.schedule(timerTask,2000);
    }
    public static void Info(Context context,String text) {
        final Typeface font = Typeface.createFromAsset(context.getAssets(), "font.ttf");

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.dialog_error, null);

        TextView textView = layout.findViewById(R.id.textView);
        textView.setText(text);
        textView.setTextColor(Color.GREEN);
        textView.setTypeface(font, Typeface.BOLD);

        builder.setView(layout);
        final AlertDialog dialog=builder.create();
        dialog.show();
        dialog.setCancelable(false);
        final Timer timer = new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.cancel();
                        timer.cancel();
                    }
                });
            }
        };
        timer.schedule(timerTask,2000);
    }
}
