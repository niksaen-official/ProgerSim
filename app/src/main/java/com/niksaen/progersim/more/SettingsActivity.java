package com.niksaen.progersim.more;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niksaen.progersim.MainActivity;
import com.niksaen.progersim.MainPage;
import com.niksaen.progersim.R;
import com.niksaen.progersim.myClass.BuyingDialog;
import com.niksaen.progersim.myClass.ChangeLanguage;
import com.niksaen.progersim.myClass.Custom;
import com.niksaen.progersim.myClass.LoadData;
import com.niksaen.progersim.myClass.PcIronAdapter;
import com.niksaen.progersim.myClass.RecyclerAdapter;

import java.util.HashMap;

public class SettingsActivity extends AppCompatActivity {

    private TextView title,changeNik,changeAvatar,resetGame,changeLanguage,politic,fieldActivity;
    LoadData loadData = new LoadData();
    HashMap<String,String> words;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        loadData.setActivity(this);
        initView();style();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //translation
        words = new Gson().fromJson(new Custom(this).getStringInAssets(this,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());
        title.setText(words.get("Settings"));
        changeNik.setText(words.get("Change nickname"));
        changeAvatar.setText(words.get("Change avatar"));
        changeLanguage.setText(words.get("Change language"));
        politic.setText(words.get("Privacy Policy"));
        resetGame.setText(words.get("Start a new game"));
        fieldActivity.setText(words.get("Change field of activity"));

        changeLanguage.setOnClickListener(v -> {
            ChangeLanguage changeLanguage1 = new ChangeLanguage(this);
            changeLanguage1.show();
        });

        changeAvatar.setOnClickListener(v -> changeAvatar());
        changeNik.setOnClickListener(v -> changeNik());
        fieldActivity.setOnClickListener(v->changeFieldActivity());
        resetGame.setOnClickListener(v -> {
            BuyingDialog dialog = new BuyingDialog(this,loadData,this);
            dialog.onClickListener = v1 -> {
                loadData.reset();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
            };
            dialog.confirmation(words.get("Do you want to start a new game?"),words.get("Data reset"));
        });

        politic.setOnClickListener(v -> {
            Intent intent = new Intent(this,PoliticActivity.class);
            startActivity(intent);
        });
    }
    private void initView(){
        title = findViewById(R.id.title);
        changeNik = findViewById(R.id.сhange_of_nickname);
        changeAvatar = findViewById(R.id.сhange_of_avatar);
        resetGame = findViewById(R.id.reset_game);
        changeLanguage = findViewById(R.id.change_language);
        politic = findViewById(R.id.politic);
        fieldActivity = findViewById(R.id.fieldActivity);
    }
    private void style(){
        Typeface typeface = Typeface.createFromAsset(getAssets(),"font.ttf");
        title.setTypeface(typeface,Typeface.BOLD);
        changeLanguage.setTypeface(typeface);
        changeNik.setTypeface(typeface);
        resetGame.setTypeface(typeface);
        changeAvatar.setTypeface(typeface);
        politic.setTypeface(typeface);
        fieldActivity.setTypeface(typeface);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent i = new Intent(this, MainPage.class);
        startActivity(i);
        finish();
    }

    AlertDialog alertDialog;
    public void changeAvatar(){
        RecyclerAdapter adapter = new RecyclerAdapter(this,loadData.getImage());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundResource(R.color.backgroundColor2);
        layout.setPadding(16,16,16,16);

        TextView title = new TextView(this);
        title.setText(words.get("Choose an avatar"));
        title.setTextSize(35);
        title.setPadding(0,0,0,16);
        title.setTextColor(Color.parseColor("#FFFFFF"));
        title.setTypeface(Typeface.createFromAsset(this.getAssets(),"font.ttf"),Typeface.BOLD);

        Button save = new Button(this);
        save.setText(words.get("Will apply"));
        save.setTextSize(35);
        save.setTextColor(Color.parseColor("#FFFFFF"));
        save.setTypeface(Typeface.createFromAsset(this.getAssets(),"font.ttf"),Typeface.BOLD);
        save.setBackgroundResource(R.color.backgroundColor2);
        save.setOnClickListener(v -> {
            alertDialog.dismiss();
            BuyingDialog dialog = new BuyingDialog(SettingsActivity.this,loadData,SettingsActivity.this);
            dialog.onClickListener = v1 -> {
                if(loadData.getMoney()>1000) {
                    loadData.setMoney(loadData.getMoney()-1000);
                    loadData.setImage(adapter.getCurrentAvatar());
                }
                else{
                    new Custom(getParent()).ErrorDialog(words.get("You don't have enough money"));
                }
                dialog.dismiss();
            };
            dialog.confirmation(words.get("Change avatar Cost: 1000"));
        });

        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        layout.addView(title,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(recyclerView,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(save,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        builder.setView(layout);
        alertDialog = builder.create();
        alertDialog.show();
    }
    public void changeNik(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundResource(R.color.backgroundColor2);
        layout.setPadding(16,16,16,16);

        TextView title = new TextView(this);
        title.setText(words.get("Change nickname"));
        title.setTextSize(35);
        title.setPadding(0,0,0,16);
        title.setTextColor(Color.parseColor("#FFFFFF"));
        title.setTypeface(Typeface.createFromAsset(this.getAssets(),"font.ttf"),Typeface.BOLD);

        EditText editText = new EditText(this);
        editText.setText(loadData.getPlayerName());
        editText.setHint(words.get("Enter a new nickname"));
        editText.setHintTextColor(Color.parseColor("#FFFFFF"));
        editText.setTextSize(35);
        editText.setPadding(0,0,0,16);
        editText.setTextColor(Color.parseColor("#FFFFFF"));
        editText.setTypeface(Typeface.createFromAsset(this.getAssets(),"font.ttf"),Typeface.BOLD);

        Button save = new Button(this);
        save.setText(words.get("Will apply"));
        save.setTextSize(35);
        save.setBackgroundResource(R.color.backgroundColor3);
        save.setTextColor(Color.parseColor("#FFFFFF"));
        save.setTypeface(Typeface.createFromAsset(this.getAssets(),"font.ttf"),Typeface.BOLD);
        save.setOnClickListener(v -> {
            alertDialog.dismiss();
            BuyingDialog dialog = new BuyingDialog(SettingsActivity.this,loadData,SettingsActivity.this);
            dialog.onClickListener = v1 -> {
                if(loadData.getMoney()>1000) {
                    loadData.setMoney(loadData.getMoney()-1000);
                    loadData.setPlayerName(editText.getText().toString());
                }
                else{
                    new Custom(getParent()).ErrorDialog(words.get("You don't have enough money"));
                }
                dialog.dismiss();
            };
            dialog.confirmation(words.get("Change of nickname Cost: 1000"));
        });
        layout.addView(editText,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(save,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        builder.setView(layout);
        alertDialog = builder.create();
        alertDialog.show();
    }
    public void changeFieldActivity(){
        String[] array = {
                "",
                "Разработка игр",
                "Разработка Desktop-приложений",
                "Разработка Android-приложений",
                "Frontend",
                "Backend",
                "Fullstack",
                "Хакер"};
        String[] arrayUI = new String[]{
                words.get("Select a field of activity:"),
                words.get("Game development"),
                words.get("Desktop Application Development"),
                words.get("Android Application Development"),
                "Frontend",
                "Backend",
                "Fullstack",
                words.get("Hacker")};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundResource(R.color.backgroundColor2);
        layout.setPadding(16,16,16,16);

        TextView title = new TextView(this);
        title.setText(words.get("Change field of activity"));
        title.setTextSize(35);
        title.setPadding(0,0,0,16);
        title.setTextColor(Color.parseColor("#FFFFFF"));
        title.setTypeface(Typeface.createFromAsset(this.getAssets(),"font.ttf"),Typeface.BOLD);

        Spinner spinner = new Spinner(SettingsActivity.this);
        spinner.setAdapter(new PcIronAdapter(this,0,arrayUI));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0)
                    loadData.setProfile(array[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button save = new Button(this);
        save.setText(words.get("Will apply"));
        save.setTextSize(35);
        save.setBackgroundResource(R.color.backgroundColor3);
        save.setTextColor(Color.parseColor("#FFFFFF"));
        save.setTypeface(Typeface.createFromAsset(this.getAssets(),"font.ttf"),Typeface.BOLD);

        layout.addView(title,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(spinner,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(save,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        builder.setView(layout);
        alertDialog = builder.create();
        alertDialog.show();
    }
}