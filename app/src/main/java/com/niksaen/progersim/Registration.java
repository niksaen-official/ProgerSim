package com.niksaen.progersim;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niksaen.progersim.more.PoliticActivity;
import com.niksaen.progersim.myClass.BuyingDialog;
import com.niksaen.progersim.myClass.Custom;
import com.niksaen.progersim.myClass.LoadData;
import com.niksaen.progersim.myClass.PcIronAdapter;

import java.util.HashMap;

public class Registration extends Activity {

    //Переменные
    boolean politicSuccess = false;
    String playerName;
    String profil;
    int image = 0;
    String OS = null;
    String[] array = {
            "",
            "Разработка игр",
            "Разработка Desktop-приложений",
            "Разработка Android-приложений",
            "Frontend",
            "Backend",
            "Fullstack",
            "Хакер"};
    String[] arrayUI;

    //View
    TextView title, text1;
    ImageView image1, image2, image3, image4;
    EditText inputPlayerName;
    Button save;
    Spinner changeProfil, changeOs;

    //дополнтельные компоненты
    Typeface font;
    Custom custom = new Custom(this);
    SharedPreferences preferences;
    LoadData loadData = new LoadData();
    HashMap<String,String> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        font = Typeface.createFromAsset(getAssets(), "font.ttf");
        loadData.setActivity(this);
        words = new Gson().fromJson(new Custom(this).getStringInAssets(this,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());

        PoliticDialog();

        preferences = getSharedPreferences("playerData", MODE_PRIVATE);

        initView();
        viewStyle();
    }
    void initView(){
        title=findViewById(R.id.title);
        text1=findViewById(R.id.text1);

        image1=findViewById(R.id.imageView1);
        image2=findViewById(R.id.imageView2);
        image3=findViewById(R.id.imageView3);
        image4=findViewById(R.id.imageView4);

        inputPlayerName=findViewById(R.id.inputPlayerName);

        save=findViewById(R.id.button);
        changeProfil = findViewById(R.id.changeProfil);
        changeOs = findViewById(R.id.changeOs);
    }
    void viewStyle(){
        title.setTypeface(font,Typeface.BOLD);
        title.setText(words.get("Character Creation"));

        text1.setTypeface(font,Typeface.BOLD);
        text1.setText(words.get("Choose an avatar:"));

        inputPlayerName.setTypeface(font,Typeface.BOLD);
        inputPlayerName.setHint(words.get("Enter your nickname:"));

        save.setTypeface(font, Typeface.BOLD);
        save.setText(words.get("Create character"));

        arrayUI = new String[]{
                words.get("Select a field of activity:"),
                words.get("Game development"),
                words.get("Desktop Application Development"),
                words.get("Android Application Development"),
                "Frontend",
                "Backend",
                "Fullstack",
                words.get("Hacker")};
        changeProfil.setAdapter(new PcIronAdapter(this,0,arrayUI));
        changeProfil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    profil = null;
                } else {
                    profil = array[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] array2 = {
                words.get("Select your operating system:"),
                "Arch Linux",
                "Linux",
                "Windows",
                "MacOS"};
        custom.CustomSpinner(this, changeOs, array2, "");
        changeOs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    OS = null;
                } else {
                    OS = array2[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void image1_OnClick(View v){
        image=R.drawable.avatar1;
        image1.setBackgroundColor(Color.parseColor("#ffffff"));
        image2.setBackgroundColor(Color.TRANSPARENT);
        image3.setBackgroundColor(Color.TRANSPARENT);
        image4.setBackgroundColor(Color.TRANSPARENT);
    }
    public void image2_OnClick(View v){
        image=R.drawable.avatar2;
        image1.setBackgroundColor(Color.TRANSPARENT);
        image2.setBackgroundColor(Color.parseColor("#ffffff"));
        image3.setBackgroundColor(Color.TRANSPARENT);
        image4.setBackgroundColor(Color.TRANSPARENT);
    }
    public void image3_OnClick(View v){
        image=R.drawable.avatar3;
        image1.setBackgroundColor(Color.TRANSPARENT);
        image2.setBackgroundColor(Color.TRANSPARENT);
        image3.setBackgroundColor(Color.parseColor("#ffffff"));
        image4.setBackgroundColor(Color.TRANSPARENT);
    }
    public void image4_OnClick(View v){
        image=R.drawable.avatar4;
        image1.setBackgroundColor(Color.TRANSPARENT);
        image2.setBackgroundColor(Color.TRANSPARENT);
        image3.setBackgroundColor(Color.TRANSPARENT);
        image4.setBackgroundColor(Color.parseColor("#ffffff"));
    }
    public void onClickSave(View v){
        custom.ViewAnim(this,v,R.drawable.button2,R.drawable.button1);
        playerName=inputPlayerName.getText().toString();
        if (image > 0 && !(playerName.equals("")) && profil != null && OS != null && politicSuccess) {
            preferences.edit().putString("playerName", playerName).apply();
            preferences.edit().putInt("image", image).apply();
            preferences.edit().putString("profil", profil).apply();
            preferences.edit().putBoolean("reg", true).apply();
            Intent intent = new Intent(this, MainPage.class);
            startActivity(intent);
            finish();
        } else {
            custom.ErrorDialog(words.get("You have not completed all the fields"));
        }
    }

    void PoliticDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View layout = layoutInflater.inflate(R.layout.politic_dialog, null);
        TextView
                title = layout.findViewById(R.id.title),
                main = layout.findViewById(R.id.main),
                href = layout.findViewById(R.id.href);
        CheckBox
                checkBox = layout.findViewById(R.id.checkbox),
                checkBox2 = layout.findViewById(R.id.checkbox2);

        Typeface font = Typeface.createFromAsset(getAssets(), "font.ttf");

        //translation
        title.setText(words.get("Privacy Policy"));
        main.setText(words.get("By playing this game you have read and accepted the terms of the privacy policy"));
        href.setText(words.get("Familiarize"));
        checkBox.setText(words.get("I accept"));
        checkBox2.setText(words.get("I do not accept"));

        title.setTypeface(font, Typeface.BOLD);
        main.setTypeface(font, Typeface.NORMAL);
        href.setTypeface(font, Typeface.BOLD);
        checkBox.setTypeface(font);
        checkBox2.setTypeface(font);

        href.setOnClickListener(v -> {
            Intent a = new Intent(getBaseContext(), PoliticActivity.class);
            startActivity(a);
        });
        builder.setView(layout);

        final AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();


        checkBox.setOnClickListener(v -> {
            politicSuccess = true;
            dialog.dismiss();
        });
    }
}