package com.niksaen.progersim.myClass;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niksaen.progersim.MainActivity;
import com.niksaen.progersim.MainPage;
import com.niksaen.progersim.R;

import java.util.HashMap;

public class Congratulation {

    static Activity activity;
    LoadData loadData;
    SharedPreferences sharedPreferences;
    HashMap<String,String> words;
    String[] arrayProfile = {
            "",
            "Разработка игр",
            "Разработка Desktop-приложений",
            "Разработка Android-приложений",
            "Frontend",
            "Backend",
            "Fullstack",
            "Хакер"};

    public Congratulation(Activity activity1, LoadData loadData1) {
        activity = activity1;
        loadData = loadData1;
        sharedPreferences = activity.getSharedPreferences("playerData", Context.MODE_PRIVATE);
        loadData.setActivity(activity1);
        words = new Gson().fromJson(new Custom(activity1).getStringInAssets(activity1,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());
    }

    static void customSpinner(Spinner spinner, String[] array) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, R.layout.for_spinner, array) {
            final Typeface font = Typeface.createFromAsset(activity.getAssets(), "font.ttf");

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTypeface(font, Typeface.BOLD);
                ((TextView) v).setTextSize(32f);
                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                ((TextView) v).setTextSize(32f);
                ((TextView) v).setTypeface(font, Typeface.BOLD);
                return v;
            }
        };
        spinner.setAdapter(adapter);
    }

    static void customSpinnerColor(Spinner spinner, final String[] array) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, R.layout.for_spinner, array) {
            final Typeface font = Typeface.createFromAsset(activity.getAssets(), "font.ttf");

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                try {
                    ((TextView) v).setTextColor(Color.parseColor(array[position]));
                } catch (Exception ignored) {
                }
                ((TextView) v).setTextSize(32f);
                ((TextView) v).setTypeface(font, Typeface.BOLD);
                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                ((TextView) v).setTypeface(font, Typeface.BOLD);
                ((TextView) v).setTextSize(32f);
                try {
                    ((TextView) v).setTextColor(Color.parseColor(array[position]));
                } catch (Exception ignored) {
                }
                return v;
            }
        };
        spinner.setAdapter(adapter);
    }

    public void isWinner(final Context context) {

        Typeface font = Typeface.createFromAsset(context.getAssets(), "font.ttf");
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View layout = inflater.inflate(R.layout.congratulation, null);

        TextView textView = layout.findViewById(R.id.text);
        Button button = layout.findViewById(R.id.button);
        Spinner spinnerColor = layout.findViewById(R.id.spinner1),
                spinnerProfile = layout.findViewById(R.id.spinner2);

        textView.setTypeface(font, Typeface.BOLD);
        button.setTypeface(font, Typeface.BOLD);

        builder.setView(layout);
        final AlertDialog dialog = builder.create();

        //translation
        textView.setText(words.get("Congratulations on your successful hacking into the Pentagon. Now choose one of three awards."));
        button.setText(words.get("Money +20 000"));
        String[] arrayColor = new String[]{
                words.get("Change nickname color to:"),
                "#0D47A1",
                "#4A148C",
                "#004D40",
                "#B71C1C",
                "#F57F17"
        };
        String[] arrayProfileUi = new String[]{
                words.get("Select a field of activity:"),
                words.get("Game development"),
                words.get("Desktop Application Development"),
                words.get("Android Application Development"),
                "Frontend",
                "Backend",
                "Fullstack",
                words.get("Hacker")};

        customSpinnerColor(spinnerColor,arrayColor);
        customSpinner(spinnerProfile,arrayProfileUi);

        spinnerColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    loadData.setNikColor(arrayColor[position]);
                    dialog.dismiss();
                    loadData.setHacked(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerProfile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    loadData.setProfile(arrayProfile[position]);
                    dialog.dismiss();
                    loadData.setHacked(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData.setHacked(true);
                MainPage.money += 20000;
                MainPage.setViewData();
                dialog.dismiss();

            }
        });

        dialog.show();
    }

    public void isLose(final Context context) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), "font.ttf");
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View layout = inflater.inflate(R.layout.is_lose, null);

        TextView textView = layout.findViewById(R.id.text);
        Button button = layout.findViewById(R.id.button);

        textView.setTypeface(font, Typeface.BOLD);
        button.setTypeface(font, Typeface.BOLD);

        //translation
        textView.setText(words.get("Well, you didn't manage to break into the Pentagon. And you got caught, but don't despair. Next time you will definitely succeed, but for now, go to jail."));
        button.setText(words.get("Ok"));

        builder.setView(layout);
        final AlertDialog dialog = builder.create();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData.reset();
                dialog.dismiss();
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
        });

        dialog.show();
    }
}
