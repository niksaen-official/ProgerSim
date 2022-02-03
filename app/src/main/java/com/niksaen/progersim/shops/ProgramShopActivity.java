package com.niksaen.progersim.shops;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niksaen.progersim.R;
import com.niksaen.progersim.myClass.Custom;
import com.niksaen.progersim.myClass.DataAdapter;
import com.niksaen.progersim.myClass.LoadData;
import com.niksaen.progersim.myClass.ViewData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ProgramShopActivity extends Activity {

    //переменны
    static float money;
    List<ViewData> programmList = new ArrayList<>();
    static String programm;

    //view
    ImageView avatar;
    TextView nikView;
    static TextView moneyView;

    //дополнительные компоненты
    Typeface font;
    LoadData loadData = new LoadData();
    Custom custom = new Custom(this);
    HashMap<String,String> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_shop);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        font = Typeface.createFromAsset(getAssets(),"font.ttf");
        loadData.setActivity(this);

        RecyclerView recyclerView = findViewById(R.id.list);
        DataAdapter adapter = new DataAdapter(this,programmList);
        recyclerView.setAdapter(adapter);

        getData();
        initView();
        viewStyle();
        setProgrammList();
        setDataView();
    }

    void getData(){
        money = loadData.getMoney();
        programm = loadData.getProgramList();
    }

    void initView(){
        avatar = findViewById(R.id.avatar);
        nikView = findViewById(R.id.nikName);
        moneyView = findViewById(R.id.moneyView);
    }

    void viewStyle() {
        nikView.setTypeface(font, Typeface.BOLD);
        nikView.setTextColor(Color.parseColor(loadData.getNikColor()));
        moneyView.setTypeface(font, Typeface.BOLD);
        avatar.setImageResource(loadData.getImage());
        nikView.setText(loadData.getPlayerName());
    }

    void setProgrammList() {
        switch (loadData.getProfile()) {
            case "Разработка игр": {
                programmList.add(new ViewData("Ruby IDEA", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program21.txt"), 2400, R.drawable.ruby_idea, "program"));
                programmList.add(new ViewData("Java IDEA", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program20.txt"), 2000, R.drawable.java_ide, "program"));
                programmList.add(new ViewData("Virtual Studio", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program19.txt"), 2500, R.drawable.virtual_ide, "program"));
                programmList.add(new ViewData("uniC#", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program1.txt"), 1500, R.drawable.unics, "program"));
                programmList.add(new ViewData("C# GameLLC", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program2.txt"), 3500, R.drawable.csgamellc, "program"));
                programmList.add(new ViewData("CPPame", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program3.txt"), 2500, R.drawable.cppame, "program"));
                programmList.add(new ViewData("tauCPP Game", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program4.txt"), 6700, R.drawable.taucppgame, "program"));
                programmList.add(new ViewData("PythoAme", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program5.txt"), 4500, R.drawable.pythoame, "program"));
                programmList.add(new ViewData("blaGameEngine", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program6.txt"), 9500, R.drawable.blagameengine, "program"));
                programmList.add(new ViewData("caJS Engine", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program7.txt"), 1500, R.drawable.cajsengine, "program"));
                programmList.add(new ViewData("JS Engn", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program8.txt"), 4500, R.drawable.jsengn, "program"));
                programmList.add(new ViewData("JavaGame", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program17.txt"), 3000, R.drawable.jsengn, "program"));
                programmList.add(new ViewData("LuaME", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program18.txt"), 5000, R.drawable.luame, "program"));
                break;
            }
            case "Разработка Desktop-приложений": {
                programmList.add(new ViewData("Ruby IDEA", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program21.txt"), 2400, R.drawable.ruby_idea, "program"));
                programmList.add(new ViewData("Java IDEA", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program20.txt"), 2000, R.drawable.java_ide, "program"));
                programmList.add(new ViewData("Virtual Studio", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program19.txt"), 2500, R.drawable.virtual_ide, "program"));
                programmList.add(new ViewData("BioWPF", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program9.txt"), 3000, R.drawable.biowpf, "program"));
                programmList.add(new ViewData("JaaFXID", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program10.txt"), 2000, R.drawable.jaafxid, "program"));
                programmList.add(new ViewData("C++ Qt ISE", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program11.txt"), 2500, R.drawable.cppqtise, "program"));
                programmList.add(new ViewData("WinForms App Creator",custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program23.txt"),1500,R.drawable.wfac_logo,"program"));
                programmList.add(new ViewData("wxWidgets IDE",custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program24.txt"),1600,R.drawable.wxide_logo,"program"));
                break;
            }
            case "Разработка Android-приложений": {
                programmList.add(new ViewData("Kotlin IDE", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program12.txt"), 3500, R.drawable.kotlinide, "program"));
                programmList.add(new ViewData("JaaFXID", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program10.txt"), 2000, R.drawable.jaafxid, "program"));
                programmList.add(new ViewData("Android IDEA", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program13.txt"), 3300, R.drawable.biowpf, "program"));
                break;
            }
            case "Frontend": {
                programmList.add(new ViewData("Notepad",custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program25.txt"),0,R.drawable.notepad,"program"));
                programmList.add(new ViewData("FlaWEB IDE", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program14.txt"), 5000, R.drawable.flawebide, "program"));
                break;
            }
            case "Backend": {
                programmList.add(new ViewData("Local Host", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program15.txt"), 6000, R.drawable.local_server, "program"));
                programmList.add(new ViewData("Free BACK IDEA", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program16.txt"), 4000, R.drawable.freebackidea, "program"));
                break;
            }
            case "Fullstack": {
                programmList.add(new ViewData("Notepad",custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program25.txt"),0,R.drawable.notepad,"program"));
                programmList.add(new ViewData("FlaWEB IDE", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program14.txt"), 5000, R.drawable.flawebide, "program"));
                programmList.add(new ViewData("Local Host", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program15.txt"), 6000, R.drawable.local_server, "program"));
                programmList.add(new ViewData("Free BACK IDEA", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program16.txt"), 4000, R.drawable.freebackidea, "program"));
                break;
            }
            case "Хакер": {
                programmList.add(new ViewData("Java IDEA", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program20.txt"), 2000, R.drawable.java_ide, "program"));
                programmList.add(new ViewData("Virtual Studio", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program19.txt"), 2500, R.drawable.virtual_ide, "program"));
                programmList.add(new ViewData("Ruby IDEA", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program21.txt"), 2400, R.drawable.ruby_idea, "program"));
                programmList.add(new ViewData("Hacking IDE", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/program/program22.txt"), 8000, R.drawable.hacking_ide, "program"));
                break;
            }
        }
    }

    static void setDataView() {
        moneyView.setText("    " + (int) money);
    }

    @Override
    public void onStop() {
        super.onStop();
        loadData.setMoney(money);
        loadData.setProgramList(programm);
    }

    @Override
    protected void onDestroy() {
        onStop();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        onStop();
        super.onPause();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, MainShop.class);
        startActivity(i);
        finish();
    }

    public void CustomDialog(final Context context, final String title, String text, final int price, int image) {

        Typeface font = Typeface.createFromAsset(context.getAssets(), "font.ttf");
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog, null);
        words = new Gson().fromJson(new Custom((Activity) context).getStringInAssets((Activity) context,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());

        ImageView imageView = layout.findViewById(R.id.image);
        imageView.setImageResource(image);
        TextView _title = layout.findViewById(R.id.title);
        TextView _text = layout.findViewById(R.id.text);
        final TextView link1 = layout.findViewById(R.id.link1);
        final TextView link2 = layout.findViewById(R.id.link2);

        _title.setText(title);
        _text.setText(text);

        _title.setTypeface(font, Typeface.BOLD);
        _text.setTypeface(font);
        link1.setTypeface(font, Typeface.BOLD);
        link2.setTypeface(font, Typeface.BOLD);
        link1.setText(words.get("Exit"));
        link2.setText(words.get("Buy"));

        builder.setView(layout);
        final AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);

        //обработка нажатий
        link1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                link1.setTextColor(Color.BLUE);
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                link1.setTextColor(Color.parseColor("#ffffff"));
                                dialog.cancel();
                            }
                        });
                    }
                };
                timer.schedule(timerTask, 200);

            }
        });
        link2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                link2.setTextColor(Color.BLUE);
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                link2.setTextColor(Color.parseColor("#ffffff"));
                                if (money >= price) {
                                    money = money - price;
                                    programm += (title + ",");
                                    dialog.cancel();
                                    setDataView();
                                }
                            }
                        });
                    }
                };
                timer.schedule(timerTask, 200);
            }
        });
    }

}