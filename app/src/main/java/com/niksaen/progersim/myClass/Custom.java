package com.niksaen.progersim.myClass;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.niksaen.progersim.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public class Custom {
    static Activity activity;

    public Custom(Activity activity) {
        Custom.activity = activity;
    }

    /**
     * используется для создания анимаций при клике по View
     */
    public void ViewAnim(final Activity activity, final View v, int image1, final int image2) {
        v.setBackgroundResource(image1);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        v.setBackgroundResource(image2);
                    }
                });
            }
        };
        timer.schedule(timerTask,300);
    }

    /**используется для анимирования текста как ссылки*/
    public void textLinkAnim(final Activity activity,final TextView textView){
        textView.setTextColor(Color.parseColor("#FF160FE4"));
        Timer timer = new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setTextColor(Color.parseColor("#Ffffff"));
                    }
                });
            }
        };
        timer.schedule(timerTask,300);
    }

    public static Drawable getImage(Context context,String filePath){
        try {
            // получаем входной поток
            InputStream ims = context.getAssets().open(filePath);
            // загружаем как Drawable
            return Drawable.createFromStream(ims, null);
        }
        catch(IOException ex) { return null;}

    }

    public static String getString(Context context,String path){
        byte[] buffer =null;
        InputStream inputStream;
        try {
            inputStream = context.getAssets().open(path);
            int size = inputStream.available();
            buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
        } catch (Exception e) {
        }
        String text = new String(buffer);
        return text;
    }

    /**используется для изменения Spinner
     *если есть необходимость скрыть текст при закрытом спиннере
     * использовать тип "settings"
     * для установки своего текста использовать тип "text:текст"*/
    public void CustomSpinner(final Activity activity, Spinner spinner, String[] array,final String type){
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(activity, R.layout.for_spinner,array) {
            final Typeface font = Typeface.createFromAsset(activity.getAssets(),"font.ttf");
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                if (type.equals("settings")) {
                    ((TextView) v).setText("");
                    v.setBackgroundResource(R.drawable.ic_more_vert_white_24dp);
                } else if (type.contains("text:")) {
                    ((TextView) v).setTypeface(font, Typeface.BOLD);
                    ((TextView) v).setText(type.substring(5));
                } else {
                    ((TextView) v).setTypeface(font, Typeface.BOLD);
                }
                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                ((TextView) v).setTypeface(font, Typeface.BOLD);
                return v;
            }
        };
        spinner.setAdapter(adapter);
    }
    public void CustomListview(final Activity activity, ListView listView, String[] array){
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(activity, R.layout.for_spinner,array) {
            final Typeface font = Typeface.createFromAsset(activity.getAssets(),"font.ttf");
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTypeface(font, Typeface.BOLD);
                ((TextView) v).setTextSize(30f);
                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                ((TextView) v).setTypeface(font, Typeface.BOLD);
                ((TextView) v).setTextSize(30f);
                return v;
            }
        };
        listView.setAdapter(adapter);
    }
    public void CustomSpinnerPc(final Activity activity, Spinner spinner, final String[] array, final String text) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, R.layout.for_spinner, array) {
            final Typeface font = Typeface.createFromAsset(activity.getAssets(), "font.ttf");

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setText(text);
                ((TextView) v).setTypeface(font, Typeface.BOLD);
                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                ((TextView) v).setTypeface(font, Typeface.BOLD);
                return v;
            }
        };
        spinner.setAdapter(adapter);
    }

    /**
     * используется для вывода об ошибках игрока спустя время сам закрывается
     */
    public void ErrorDialog(String text) {
        final Typeface font = Typeface.createFromAsset(activity.getAssets(), "font.ttf");

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
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
                activity.runOnUiThread(new Runnable() {
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

    public void timer(final Activity activity, final Runnable runnable, final int delay){
        final Timer timer = new Timer();
        final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(runnable);
            }
        };
        timer.schedule(timerTask, delay);
    }

    /**используется для получения текста из файлов assets**/
    public String getStringInAssets(Activity activity,String path){
        byte[] buffer =null;
        InputStream inputStream;
        try {
            inputStream = activity.getAssets().open(path);
            int size = inputStream.available();
            buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
        } catch (Exception e) {
        }
        String text = new String(buffer);
        return text;
    }
    public static void Info(Context context, String text) {
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

    int[] arrayImage = new int[]{
            R.drawable.frame0,
            R.drawable.frame1,
            R.drawable.frame2,
            R.drawable.frame3,
            R.drawable.frame4,
            R.drawable.frame5,
            R.drawable.frame6,
            R.drawable.frame7,
            R.drawable.frame8,
            R.drawable.frame9,
            R.drawable.frame10,
            R.drawable.frame11,
            R.drawable.frame12,
            R.drawable.frame13,
            R.drawable.frame14,
            R.drawable.frame15,
            R.drawable.frame16,
            R.drawable.frame17,
            R.drawable.frame18,
            R.drawable.frame19,
            R.drawable.frame20,
            R.drawable.frame21,
            R.drawable.frame22,
            R.drawable.frame23
    };

    Timer timer;

    public void MainPageAnim(final View layout) {
        final int[] index = {0};
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        index[0]++;
                        if (index[0] > 23) {
                            index[0] = 0;
                        }
                        layout.setBackgroundResource(arrayImage[index[0]]);
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 400);
    }

    public void MainPageAnimEnd() {
        timer.cancel();
    }
}
