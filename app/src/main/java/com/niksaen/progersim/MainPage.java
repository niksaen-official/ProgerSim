package com.niksaen.progersim;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niksaen.progersim.miniGame.MiniGame1;
import com.niksaen.progersim.miniGame.MiniGame2;
import com.niksaen.progersim.more.HelpActivity;
import com.niksaen.progersim.more.MoreActivity;
import com.niksaen.progersim.more.NewsActivity;
import com.niksaen.progersim.more.SandboxActivity;
import com.niksaen.progersim.more.SettingsActivity;
import com.niksaen.progersim.myClass.Congratulation;
import com.niksaen.progersim.myClass.Custom;
import com.niksaen.progersim.myClass.CustomDialog;
import com.niksaen.progersim.myClass.DebugDialog;
import com.niksaen.progersim.myClass.LoadData;
import com.niksaen.progersim.myClass.Work;
import com.niksaen.progersim.myClass.tutorial.TutorialDialog;
import com.niksaen.progersim.shops.MainShop;

import java.util.HashMap;

public class MainPage extends Activity {

    //переменные
    private String[] settings;
    static String level;
    public static float money;
    static float exp;
    private String[] _skill = {};
    private String skill;
    private String buff_skill = "";
    static float energy, bonusLevel;

    //view
    private Button button1;
    private Button button2;
    @SuppressLint("StaticFieldLeak")
    static TextView textView, moneyView, energyView, levelView, expView;
    @SuppressLint("StaticFieldLeak")
    static ImageView imageView,pet;
    private Spinner spinner;
    private LinearLayout dialogForLearn,background;
    private Button skillButton;
    private ListView skillList;

    //дополнительные компоненты
    private Typeface font;
    private Custom custom = new Custom(this);
    @SuppressLint("StaticFieldLeak")
    public static LoadData loadData = new LoadData();
    public HashMap<String,String> words;
    private boolean useMusic = false;

    MediaPlayer mediaPlayer;
    MediaPlayer mediaPlayerForMusic;
    int[] musicList = {
            R.raw.track1,
            R.raw.track2
    };
    Handler handler = new Handler();
    Runnable runnable;

    private View buff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        font = Typeface.createFromAsset(getAssets(), "font.ttf");
        //translation
        loadData.setActivity(this);
        words = new Gson().fromJson(new Custom(this).getStringInAssets(this,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());

        ConstraintLayout layout = findViewById(R.id.main);
        custom.MainPageAnim(layout);
        mediaPlayer = MediaPlayer.create(this, R.raw.programing);
        mediaPlayer.setVolume((float) 0.15, (float) 0.15);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        initView();
        getSaveData();
        viewStyle();
        setViewData();
        setBonusSkills();

        if(!loadData.getTutorial()) {
            new TutorialDialog(this, loadData).show(TutorialDialog.page);
        }
        if (loadData.getLastVersion() != 14) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.update_info_dialog, null);
            TextView
                    title = view.findViewById(R.id.title),
                    text = view.findViewById(R.id.text),
                    exit = view.findViewById(R.id.exit);

            title.setTypeface(font, Typeface.BOLD);
            text.setTypeface(font);
            exit.setTypeface(font, Typeface.BOLD);

            text.setText(new Custom(this).getStringInAssets(this,"textFile/"+loadData.getLanguage()+"/update_info.txt"));

            builder.setView(view);
            final AlertDialog dialog = builder.create();
            exit.setOnClickListener(v -> {
                dialog.dismiss();
                loadData.setLastVersion(14);
            });
            dialog.show();
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                startIntent(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        skillList.setOnItemClickListener((parent, view, position, id) -> {
            buff_skill = _skill[position];
            if(buff != null){
                buff.setBackgroundResource(R.color.backgroundColor1);
            }
            view.setBackgroundResource(R.color.backgroundColor2);
            buff = view;
            if (buff_skill.equals(words.get("Distribution of leaflets")) || buff_skill.equals(words.get("Work as a courier")) || buff_skill.equals(words.get("Pizza delivery"))) {
                button1.setText(words.get("Work"));
            } else {
                button1.setText(words.get("Code"));
            }
            skillList.setVisibility(View.GONE);
        });

        skillButton.setOnClickListener(v -> {
            if(skillList.getVisibility() == View.GONE){
                skillList.setVisibility(View.VISIBLE);
            }else{
                skillList.setVisibility(View.GONE);
            }
        });
        if(loadData.getLearning()){
            DialogForLearn();
            dialogForLearn.setVisibility(View.VISIBLE);
        }
    }

    void initView() {
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        imageView = findViewById(R.id.avatar);
        textView = findViewById(R.id.nikName);
        spinner = findViewById(R.id.settings);
        skillButton = findViewById(R.id.buttonSkill);
        skillList = findViewById(R.id.skillList);
        moneyView = findViewById(R.id.moneyView);
        energyView = findViewById(R.id.energyView);
        levelView = findViewById(R.id.textView14);
        expView = findViewById(R.id.textView13);
        dialogForLearn = findViewById(R.id.dialog_for_learn);
        background = findViewById(R.id.background);
        pet = findViewById(R.id.pets);
    }
    void viewStyle() {
        button1.setTypeface(font, Typeface.BOLD);
        button2.setTypeface(font, Typeface.BOLD);
        textView.setTypeface(font, Typeface.BOLD);
        textView.setTextColor(Color.parseColor(loadData.getNikColor()));
        moneyView.setTypeface(font, Typeface.BOLD);
        energyView.setTypeface(font, Typeface.BOLD);
        levelView.setTypeface(font, Typeface.BOLD);
        expView.setTypeface(font, Typeface.BOLD);

        background.setBackgroundResource(loadData.getBackground());
        pet.setImageResource(loadData.getPets());

        settings = new String[]{
                words.get("Menu:"),
                words.get("News"),
                words.get("Help"),
                words.get("About me"),
                words.get("Sandbox mode"),
                words.get("Settings")
        };

        button2.setText(words.get("Shops"));

        custom.CustomSpinner(this, spinner, settings, "settings");
        custom.CustomListview(this, skillList, _skill);
        skillButton.setTextSize(35f);
        skillButton.setText(words.get("Skill"));
        skillButton.setTypeface(font,Typeface.BOLD);
    }
    public static void setViewData() {
        imageView.setImageResource(loadData.getImage());
        textView.setText(loadData.getPlayerName());

        moneyView.setText("    " + (int) money);
        energyView.setText("    " + (int) energy);
        levelView.setText(level);
        expView.setText("    " + (int) exp);
    }
    void getSaveData() {
        money = loadData.getMoney();
        exp = loadData.getExp();
        level = loadData.getLevel();
        switch (level) {
            case "Junior 2": {
                bonusLevel = (float) 1.1;
                break;
            }
            case "Junior 3": {
                bonusLevel = (float) 1.2;
                break;
            }
            case "Middle 1": {
                bonusLevel = (float) 1.5;
                break;
            }
            case "Middle 2": {
                bonusLevel = (float) 1.6;
                break;
            }
            case "Middle 3": {
                bonusLevel = (float) 1.7;
                break;
            }
            case "Senior 1": {
                bonusLevel = (float) 2;
                break;
            }
            case "Senior 2": {
                bonusLevel = (float) 2.5;
                break;
            }
            case "Senior 3": {
                bonusLevel = (float) 3;
                break;
            }
        }
        energy = loadData.getEnergy();
        setSkill();
        _skill = skill.split(",");
    }
    void setData(){
        loadData.setTimeLearnCurrent(progressLearn);
        loadData.setMoney(money);
        loadData.setEnergy(energy);
        loadData.setExp(exp);
        loadData.setLevel(level);
    }
    @Override
    public void onStop() {
        super.onStop();
        if(mediaPlayerForMusic != null) {
            mediaPlayerForMusic.stop();
        }
        mediaPlayer.stop();
        custom.MainPageAnimEnd();
        setData();
    }
    @Override
    public void onBackPressed(){
        CustomDialog dialog = new CustomDialog(MainPage.this);
        dialog.setTitle(words.get("Confirm exit"));
        dialog.setMessage(words.get("Do you want to quit the game?"));
        dialog.setCancelable(false);
        dialog.setButtonOkText(words.get("Exit"));
        dialog.setButtonCancelText(words.get("Continue"));
        dialog.setButtonCancelVisible(true);
        dialog.setButtonOkVisible(true);
        dialog.setButtonOkOnClickListener(v -> { onStop();finish(); });
        dialog.setButtonCancelOnClickListener(v ->dialog.dismiss());
        dialog.create();
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        onStop();
        super.onDestroy();
    }

    //обработка нажатий каких либо вью
    public void radioActivate(View view){
        if(!useMusic) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    if(mediaPlayerForMusic!=null && mediaPlayerForMusic.isPlaying()){
                        if(mediaPlayerForMusic.getCurrentPosition() >= mediaPlayerForMusic.getDuration()){
                            mediaPlayer.stop();
                            mediaPlayerForMusic = MediaPlayer.create(MainPage.this,musicList[Work.random(0,musicList.length-1)]);
                            mediaPlayerForMusic.start();
                        }
                        handler.postDelayed(this,500);
                    }
                }
            };
            ((ImageView) view).setImageResource(R.drawable.radio_state_on);
            mediaPlayerForMusic = MediaPlayer.create(this,musicList[Work.random(0,musicList.length-1)]);
            mediaPlayerForMusic.setVolume(0.11f,0.11f);
            mediaPlayerForMusic.start();
        }
        else {
            ((ImageView) view).setImageResource(R.drawable.radio_state_off);
            mediaPlayerForMusic.stop();
        }
        useMusic = !useMusic;
    }
    public void button1Click(View v) {
        /* Первый индекс это заработок
         *  Второй индекс это опыт
         *  Третий индек это энергия*/
        float bonusLevel = 1.5f;
        custom.ViewAnim(this, v, R.drawable.button2, R.drawable.button1);

        Double[] bonuses = bonusSkills.get(buff_skill);

        if (energy >= 40 && buff_skill.equals(words.get("Hack the Pentagon")) && !loadData.getHacked()) {
            int chance = Work.random(0, 1);
            Congratulation congratulation = new Congratulation(this, loadData);
            if (chance == 1) {
                congratulation.isWinner(this);
            } else {
                congratulation.isLose(this);
            }
        }

        if (energy > 0 && bonuses != null && energy-bonuses[2]>=0) {
            if (button1.getText().toString().equals(words.get("Code"))) {
                if(Work.random(0,40) == 1){
                    if(Work.random(1,2) == 1) {
                        new MiniGame2(this).start(words.get("Money: +200"));
                    }else {
                        new MiniGame1(this).start(words.get("Money: +200"));
                    }
                }
            }
            money += bonuses[0] * bonusLevel;
            exp += bonuses[1] * bonusLevel;
            energy -= bonuses[2];
                if(exp >= 500){
                    level = "Junior 2";
                    bonusLevel = (float) 1.6;
                }
                if(exp >= 1000) {
                    level = "Junior 3";
                    bonusLevel = (float) 1.7;
                }
                if(exp >= 1500) {
                    level = "Middle 1";
                    bonusLevel = (float) 2;
                }
                if(exp >= 2000) {
                    level = "Middle 2";
                    bonusLevel = (float) 2.3;
                }
                if(exp >= 2500) {
                    level = "Middle 3";
                    bonusLevel = (float) 2.6;
                }
                if(exp >= 3000) {
                    level = "Senior 1";
                    bonusLevel = (float) 3;
                }
                if(exp >= 3500) {
                    level = "Senior 2";
                    bonusLevel = (float) 3.5;
                }
                if(exp >= 4000) {
                    level = "Senior 3";
                    bonusLevel = (float) 4;
                }
            setViewData();
        } else {
            button1.setVisibility(View.GONE);
        }

    }
    public void button2Click(View v) {
        custom.ViewAnim(this, v, R.drawable.button2, R.drawable.button1);
        Intent i = new Intent(this, MainShop.class);
        onStop();
        startActivity(i);
        finish();
    }
    public void libraryOpen(View v) {
        onStop();
        Intent i = new Intent(this, LibraryActivity.class);
        startActivity(i);
        finish();
    }
    public void PcMenuOpen(View v) {
        onStop();
        Intent i = new Intent(this, PcMenu.class);
        startActivity(i);
        finish();
    }
    void startIntent(int pos) {
        Intent i;
        if (pos == 1) {
            i = new Intent(this, NewsActivity.class);
            startActivity(i);
            onStop();
            finish();
        }
        else if (pos == 2) {
            i = new Intent(this, HelpActivity.class);
            startActivity(i);
            onStop();
            finish();
        }
        else if (pos == 3) {
            i = new Intent(this, MoreActivity.class);
            startActivity(i);
            onStop();
            finish();
        }
        else if(pos == 4){
            if(loadData.getSandboxModeOpen()) {
                i = new Intent(this, SandboxActivity.class);
                startActivity(i);
                onStop();
                finish();
            }else {
                CustomDialog dialog = new CustomDialog(MainPage.this);
                dialog.setTitle(words.get("Open mode"));
                dialog.setMessage(words.get("The cost of opening the sandbox mode is: 5000 money"));
                dialog.setCancelable(false);
                dialog.setButtonOkText(words.get("Buy"));
                dialog.setButtonCancelText(words.get("Cancel"));
                dialog.setButtonCancelVisible(true);
                dialog.setButtonOkVisible(true);
                dialog.setButtonOkOnClickListener(v -> {
                    if(money>=5000) {
                        money = money - 5000;
                        moneyView.setText("    " + (int) money);
                        dialog.dismiss();
                        loadData.setSandboxModeOpen(true);
                        Intent intent = new Intent(MainPage.this, SandboxActivity.class);
                        startActivity(intent);
                        onStop();
                        finish();
                    }
                    else{
                        DebugDialog.Error(MainPage.this,words.get("You don't have enough money"));
                        dialog.dismiss();
                    }
                });
                dialog.setButtonCancelOnClickListener(v ->dialog.dismiss());
                dialog.create();
                dialog.show();
            }
        }
        else if(pos == 5){
            i = new Intent(this, SettingsActivity.class);
            startActivity(i);
            onStop();
            finish();
        }
    }

    void setSkill() {
        skill = "";
        String languages = loadData.getLanguages().replace("[", "").replace("]", "");
        String programs = loadData.getDiskContent1() + loadData.getDiskContent2() + loadData.getDiskContent3() + loadData.getDiskContent4() + loadData.getDiskContent5() + loadData.getDiskContent6();

        if (programs.contains("WinForms App Creator")) {
            if (languages.contains("Книга по WinForms")) {
                skill += words.get("Desktop Application Development on the")+" WinForms,";
            }
        }
        if(programs.contains("wxWidgets IDE")) {
            if (languages.contains("Книга по wxWidgets")) {
                skill += words.get("Desktop Application Development on the") + " wxWidgets,";
            }
        }

        if (languages.contains("Книга uniC#")) {
            if (programs.contains("uniC#")) {
                skill += words.get("Game development on")+" uniC#,";
            }
        }
        if (languages.contains("Книга C# Game LLC")) {
            if (programs.contains("C# Game LLC")) {
                skill += words.get("Game development on")+" C# Game LLC,";
            }
        }
        if (languages.contains("Книга CPPame")) {
            if (programs.contains("CPPame")) {
                skill += words.get("Game development on")+" CPPame,";
            }
        }
        if (languages.contains("Книга tauCPP Game")) {
            if (programs.contains("tauCPP Game")) {
                skill += words.get("Game development on")+" tauCPP Game,";
            }
        }
        if (languages.contains("Книга PythoAme")) {
            if (programs.contains("PythoAme")) {
                skill += words.get("Game development on")+" PythoAme,";
            }
        }
        if (languages.contains("Книга blaGame Engine")) {
            if (programs.contains("blaGame Engine")) {
                skill += words.get("Game development on")+" blaGame Engine,";
            }
        }
        if (languages.contains("Книга caJS Engine")) {
            if (programs.contains("caJS Engine")) {
                skill += words.get("Game development on")+" caJS Engine,";
            }
        }
        if (languages.contains("Книга JS Engn")) {
            if (programs.contains("JS Engn")) {
                skill += words.get("Game development on")+" JS Engn,";
            }
        }
        if (languages.contains("Книга C# WPF")) {
            if (programs.contains("BioWPF")) {
                skill += words.get("Desktop Application Development on the")+" C# WPF,";
            }
        }
        if (languages.contains("Книга Qt")) {
            if (programs.contains("C++ Qt ISE")) {
                skill += words.get("Desktop Application Development on the")+" Qt,";
            }
        }
        if (languages.contains("Книга JavaFX")) {
            if (programs.contains("JaaFXID")) {
                skill += words.get("Application development on")+" JavaFX,";
            }
        }
        if (languages.contains("Книга Kotlin")) {
            if (programs.contains("Kotlin IDE")) {
                skill += words.get("Application development on")+" Kotlin,";
            }
        }
        if (languages.contains("Книга Android API")) {
            if (programs.contains("Android IDEA")) {
                skill += words.get("Application development on")+" Android API,";
            }
        }
        if (languages.contains("Книга HTML")) {
            if (programs.contains("FlaWEB IDE") || programs.contains("Notepad")) {
                skill += words.get("Website layout")+",";
            }
        }
        if (languages.contains("Книга CSS")) {
            if (programs.contains("FlaWEB IDE") || programs.contains("Notepad")) {
                skill += words.get("Website development with design")+",";
            }
        }
        if (languages.contains("Книга PHP")) {
            if (programs.contains("Free BACK IDEA")) {
                skill += words.get("Writing the server side in")+" PHP,";
            }
        }
        if (languages.contains("Книга Node.js")) {
            if (programs.contains("Free BACK IDEA")) {
                skill += words.get("Writing the server side in")+" Node.js,";
            }
        }
        if (languages.contains("Книга MySQL")) {
            if (programs.contains("Local Host")) {
                skill += words.get("Database creation")+",";
            }
        }
        if (languages.contains("Книга JavaGame")) {
            if (programs.contains("JavaGame")) {
                skill += words.get("Game development on")+" JavaGame,";
            }
        }
        if (languages.contains("Книга LuaME")) {
            if (programs.contains("LuaME")) {
                skill += words.get("Game development on")+" LuaME,";
            }
        }

        if (loadData.getProfile().equals("Хакер")) {
            if (programs.contains("Virtual Studio")) {
                if (languages.contains("Книга по C")) {
                    skill += words.get("Hack with")+" С,";
                }
                if (languages.contains("Книга C++")) {
                    skill += words.get("Hack with")+" C++,";
                }
                if (languages.contains("Книга Python")) {
                    skill += words.get("Hack with")+" Python,";
                }
            }
            if (programs.contains("Java IDEA")) {
                if (languages.contains("Книга Java")) {
                    skill += words.get("Hack with")+" Java,";
                }
            }
            if (programs.contains("Ruby IDEA")) {
                if (languages.contains("Книга Ruby")) {
                    skill += words.get("Hack with")+" Ruby";
                }
            }
            final boolean b = languages.contains("Книга по C") || languages.contains("Книга C++") || languages.contains("Книга Python") || languages.contains("Книга Java") || languages.contains("Книга Ruby") || languages.contains("Книга JavaScript");
            if (programs.contains("Hacking IDE")) {
                if (b) {
                    skill += words.get("Hack with")+" Hacking IDE,";
                }
            }
            if (programs.contains("Hacking IDE") && level.equals("Senior 3")) {
                if (b) {
                    skill += words.get("Hack the Pentagon")+",";
                }
            }
        }
        if (programs.contains("Virtual Studio")) {
            if (languages.contains("Книга по C")) {
                skill += words.get("Programing on")+" C,";
            }
            if (languages.contains("Книга C++")) {
                skill += words.get("Programing on")+" C++,";
            }
            if (languages.contains("Книга Python")) {
                skill += words.get("Programing on")+" Python,";
            }
            if (languages.contains("Книга C#")) {
                skill += words.get("Programing on")+" C#,";
            }
        }
        if (programs.contains("Java IDEA")) {
            if (languages.contains("Книга Java")) {
                skill += words.get("Programing on")+" Java,";
            }
        }
        if (programs.contains("Ruby IDEA")) {
            if (languages.contains("Книга Ruby")) {
                skill += words.get("Programing on")+" Ruby,";
            }
        }
        if (programs.contains("Virtual Studio")) {
            if (languages.contains("Книга по WinForms")) {
                skill += words.get("Desktop Application Development on the")+" WinForms,";
            }
            if (languages.contains("Книга по wxWidgets")) {
                skill += words.get("Desktop Application Development on the")+" wxWidgets,";
            }
        }
        skill += words.get("Pizza delivery")+","+words.get("Work as a courier")+","+words.get("Distribution of leaflets");
    }
    /**
     * Первый индекс это заработок
     * Второй индекс это бонус опыта
     * Третий индекс это бонус энергии
     */
    HashMap<String, Double[]> bonusSkills = new HashMap<>();
    void setBonusSkills() {
        bonusSkills.put(words.get("Distribution of leaflets"), new Double[]{4d, 0d, 2d});
        bonusSkills.put(words.get("Work as a courier"), new Double[]{6d, 0d, 3d});
        bonusSkills.put(words.get("Pizza delivery"), new Double[]{6d, 0d, 3d});
        bonusSkills.put(words.get("Programing on")+" C", new Double[]{4d, 2d, 2.5d});
        bonusSkills.put(words.get("Programing on")+" C++", new Double[]{3d, 1.5, 2d});
        bonusSkills.put(words.get("Programing on")+" Python", new Double[]{1d, 1d, 0.5d});
        bonusSkills.put(words.get("Programing on")+" C#", new Double[]{1d, 1.5, 1d});
        bonusSkills.put(words.get("Programing on")+" Java", new Double[]{2d, 1.5, 1d});
        bonusSkills.put(words.get("Programing on")+" Ruby", new Double[]{1.5, 1.5, 1d});
        bonusSkills.put(words.get("Game development on")+" uniC#", new Double[]{2.0, 1.0, 1.0});
        bonusSkills.put(words.get("Game development on")+" C# Game LLC", new Double[]{4.5, 1.7, 1.5});
        bonusSkills.put(words.get("Game development on")+" CPPame", new Double[]{2.6, 1.3, 1.9});
        bonusSkills.put(words.get("Game development on")+" tauCPP Game", new Double[]{7.0, 2.9, 1.9});
        bonusSkills.put(words.get("Game development on")+" PythoAme", new Double[]{2.0, 0.9, 0.75});
        bonusSkills.put(words.get("Game development on")+" blaGame Engine", new Double[]{10.0, 4.0, 3.0});
        bonusSkills.put(words.get("Game development on")+" caJS Engine", new Double[]{3.0, 1.5, 1.2});
        bonusSkills.put(words.get("Game development on")+" JS Engn", new Double[]{5.0, 1.3, 1.7});
        bonusSkills.put(words.get("Desktop Application Development on the")+" C# WPF", new Double[]{4.0, 1.5, 1.3});
        bonusSkills.put(words.get("Desktop Application Development on the")+" Qt", new Double[]{3.7, 1.8, 1.9});
        bonusSkills.put(words.get("Application development on")+" JavaFX", new Double[]{5.0, 0.9, 1.6});
        bonusSkills.put(words.get("Application development on")+" Kotlin", new Double[]{3.0, 1.2, 0.7});
        bonusSkills.put(words.get("Application development on")+" Android API", new Double[]{3.5, 1.8, 1.4});
        bonusSkills.put(words.get("Website layout"), new Double[]{1.0, 1.5, 0.5});
        bonusSkills.put(words.get("Website development with design"), new Double[]{2.6, 0.85, 1.5});
        bonusSkills.put(words.get("Writing the server side in")+" PHP", new Double[]{6.0, 1.4, 1.5});
        bonusSkills.put(words.get("Writing the server side in")+" Node.js", new Double[]{6.0, 1.2, 1.3});
        bonusSkills.put(words.get("Database creation"), new Double[]{15.0, 1.4, 1.6});
        bonusSkills.put(words.get("Game development on")+" JavaGame", new Double[]{10.0, 3.0, 1.2});
        bonusSkills.put(words.get("Game development on")+" LuaME", new Double[]{12.0, 3.0, 1.4});
        bonusSkills.put(words.get("Hack with")+" С", new Double[]{10d, 2d, 3d});
        bonusSkills.put(words.get("Hack with")+" С++", new Double[]{10d, 2d, 3d});
        bonusSkills.put(words.get("Hack with")+" Python", new Double[]{10d, 2d, 3d});
        bonusSkills.put(words.get("Hack with")+" Java", new Double[]{10d, 2d, 3d});
        bonusSkills.put(words.get("Hack with")+" Ruby", new Double[]{10d, 2d, 3d});
        bonusSkills.put(words.get("Desktop Application Development on the")+" WinForms", new Double[]{14d, 3d, 4d});
        bonusSkills.put(words.get("Desktop Application Development on the")+" wxWidgets", new Double[]{27d, 4d, 6d});
    }

    int progressLearn;
    private void DialogForLearn(){
        progressLearn = loadData.getTimeLearnCurrent();
        TextView textView = findViewById(R.id.text);
        ImageView imageView = findViewById(R.id.icon);
        ProgressBar progress = findViewById(R.id.progressLearn);
        textView.setTypeface(font);
        imageView.setImageResource(loadData.getBookIconForLearn());
        textView.setText(words.get("In progress:") +loadData.getBookForLearn().replace("Книга",words.get("Books")) +"...");
        progress.setMax(loadData.getTimeLearn());
        progress.setProgress(progressLearn);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressLearn++;
                progress.setProgress(progressLearn);
                if(progressLearn >= loadData.getTimeLearn()){
                    progressLearn = 0;
                    dialogForLearn.setVisibility(View.GONE);
                    loadData.setLearning(false);
                    loadData.setLanguages(loadData.getLanguages()+loadData.getBookForLearn()+",");
                    loadData.setBookForLearn("");
                    loadData.setBookIconForLearn(0);
                    loadData.setTimeLearn(0);
                    loadData.setTimeLearnCurrent(0);
                    setSkill();
                    custom.CustomListview(MainPage.this, skillList, _skill);
                }
                else{
                    handler.postDelayed(this,45);
                }
            }
        },50);
    }
}