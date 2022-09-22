package com.niksaen.progersim.shops;


import android.annotation.SuppressLint;
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

public class BookShop extends Activity {

    //переменны
    static float money;
    static String book;
    List<ViewData> bookList = new ArrayList<>();


    //view
    ImageView avatar;
    TextView nikView;
    static TextView moneyView;
    String profil;

    //дополнительные компоненты
    LoadData loadData = new LoadData();
    Custom custom = new Custom(this);
    Typeface font;
    HashMap<String,String> words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_shop);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        font = Typeface.createFromAsset(getAssets(), "font.ttf");
        loadData.setActivity(this);

        getData();
        setBookList();
        initView();
        viewStyle();
        setDataView();

        RecyclerView recyclerView = findViewById(R.id.list);
        DataAdapter adapter = new DataAdapter(this, bookList);
        recyclerView.setAdapter(adapter);
    }

    void setBookList() {
        switch (loadData.getProfile()) {
            case "Разработка игр": {
                bookList.add(new ViewData("Книга по C", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book27.txt"), 1100, R.drawable.book_c, "BOOK"));
                bookList.add(new ViewData("Книга Ruby", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book28.txt"), 2000, R.drawable.book_ruby, "BOOK"));
                bookList.add(new ViewData("Книга Java", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book3.txt"), 1200, R.drawable.book_java, "BOOK"));
                bookList.add(new ViewData("Книга C#", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book1.txt"), 800, R.drawable.book_cs, "BOOK"));
                bookList.add(new ViewData("Книга C++", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book2.txt"), 1000, R.drawable.book_cpp, "BOOK"));
                bookList.add(new ViewData("Книга Python", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book4.txt"), 800, R.drawable.book_python, "BOOK"));
                bookList.add(new ViewData("Книга JavaScript", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book10.txt"), 900, R.drawable.book_js, "BOOK"));
                bookList.add(new ViewData("Книга uniC#", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book11.txt"), 1540, R.drawable.book_unics, "BOOK"));
                bookList.add(new ViewData("Книга C# Game LLC", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book12.txt"), 3000, R.drawable.book_csgamellc, "BOOK"));
                bookList.add(new ViewData("Книга CPPame", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book13.txt"), 1200, R.drawable.book_cppame, "BOOK"));
                bookList.add(new ViewData("Книга tauCPP Game", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book14.txt"), 5000, R.drawable.book_taucppgame, "BOOK"));
                bookList.add(new ViewData("Книга PythoAme", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book15.txt"), 1500, R.drawable.book_puthoame, "BOOK"));
                bookList.add(new ViewData("Книга blaGame Engine", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book16.txt"), 8000, R.drawable.book_blagameengine, "BOOK"));
                bookList.add(new ViewData("Книга caJS Engine", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book17.txt"), 1350, R.drawable.book_cajsengine, "BOOK"));
                bookList.add(new ViewData("Книга JS Engn", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book18.txt"), 3000, R.drawable.book_jsengn, "BOOK"));
                bookList.add(new ViewData("Книга JavaGame", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book24.txt"), 2000, R.drawable.book_javagame, "BOOK"));
                bookList.add(new ViewData("Книга Lua", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book25.txt"), 2000, R.drawable.book_lua, "BOOK"));
                bookList.add(new ViewData("Книга LuaME", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book26.txt"), 1500, R.drawable.book_luame, "BOOK"));
                break;
            }
            case "Разработка Desktop-приложений": {
                bookList.add(new ViewData("Книга Python", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book4.txt"), 800, R.drawable.book_python, "BOOK"));
                bookList.add(new ViewData("Книга по C", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book27.txt"), 1100, R.drawable.book_c, "BOOK"));
                bookList.add(new ViewData("Книга Ruby", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book28.txt"), 2000, R.drawable.book_ruby, "BOOK"));
                bookList.add(new ViewData("Книга C#", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book1.txt"), 800, R.drawable.book_cs, "BOOK"));
                bookList.add(new ViewData("Книга C++", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book2.txt"), 1000, R.drawable.book_cpp, "BOOK"));
                bookList.add(new ViewData("Книга Java", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book3.txt"), 1200, R.drawable.book_java, "BOOK"));
                bookList.add(new ViewData("Книга JavaFX", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book19.txt"), 1200, R.drawable.book_javafx, "BOOK"));
                bookList.add(new ViewData("Книга Qt", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book20.txt"), 1560, R.drawable.book_qt, "BOOK"));
                bookList.add(new ViewData("Книга C# WPF", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book21.txt"), 1800, R.drawable.book_wpf, "BOOK"));
                bookList.add(new ViewData("Книга по WinForms", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book29.txt"), 1700, R.drawable.book_winforms, "BOOK"));
                bookList.add(new ViewData("Книга по wxWidgets", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book30.txt"), 2500, R.drawable.book_wxwidgets, "BOOK"));
                break;
            }
            case "Разработка Android-приложений": {
                bookList.add(new ViewData("Книга Java", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book3.txt"), 1200, R.drawable.book_java, "BOOK"));
                bookList.add(new ViewData("Книга Android API", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book22.txt"), 2000, R.drawable.book_androidapi, "BOOK"));
                bookList.add(new ViewData("Книга JavaFX", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book19.txt"), 1200, R.drawable.book_javafx, "BOOK"));
                bookList.add(new ViewData("Книга Kotlin", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book23.txt"), 1600, R.drawable.book_kotlin, "BOOK"));
                break;
            }
            case "Frontend": {
                bookList.add(new ViewData("Книга HTML", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book8.txt"), 500, R.drawable.book_html, "BOOK"));
                bookList.add(new ViewData("Книга CSS", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book9.txt"), 750, R.drawable.book_css, "BOOK"));
                bookList.add(new ViewData("Книга JavaScript", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book10.txt"), 900, R.drawable.book_js, "BOOK"));
                break;
            }
            case "Backend": {
                bookList.add(new ViewData("Книга PHP", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book5.txt"), 900, R.drawable.book_php, "BOOK"));
                bookList.add(new ViewData("Книга Node.js", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book6.txt"), 900, R.drawable.book_nodejs, "BOOK"));
                bookList.add(new ViewData("Книга MySQL", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book7.txt"), 1200, R.drawable.book_mysql, "BOOK"));
                break;
            }
            case "Fullstack": {
                bookList.add(new ViewData("Книга HTML", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book8.txt"), 500, R.drawable.book_html, "BOOK"));
                bookList.add(new ViewData("Книга CSS", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book9.txt"), 750, R.drawable.book_css, "BOOK"));
                bookList.add(new ViewData("Книга JavaScript", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book10.txt"), 900, R.drawable.book_js, "BOOK"));
                bookList.add(new ViewData("Книга PHP", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book5.txt"), 900, R.drawable.book_php, "BOOK"));
                bookList.add(new ViewData("Книга Node.js", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book6.txt"), 900, R.drawable.book_nodejs, "BOOK"));
                bookList.add(new ViewData("Книга MySQL", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book7.txt"), 1200, R.drawable.book_mysql, "BOOK"));
            }
            case "Хакер": {
                bookList.add(new ViewData("Книга JavaScript", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book10.txt"), 900, R.drawable.book_js, "BOOK"));
                bookList.add(new ViewData("Книга Java", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book3.txt"), 1200, R.drawable.book_java, "BOOK"));
                bookList.add(new ViewData("Книга C++", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book2.txt"), 1000, R.drawable.book_cpp, "BOOK"));
                bookList.add(new ViewData("Книга Python", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book4.txt"), 800, R.drawable.book_python, "BOOK"));
                bookList.add(new ViewData("Книга по C", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book27.txt"), 1100, R.drawable.book_c, "BOOK"));
                bookList.add(new ViewData("Книга Ruby", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book28.txt"), 2000, R.drawable.book_ruby, "BOOK"));
            }
        }
    }

    void getData() {
        money = loadData.getMoney();
        book = loadData.getBook();
        profil = loadData.getProfile();
    }

    void initView() {
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

    @Override
    public void onStop() {
        super.onStop();
        loadData.setMoney(money);
        loadData.setBook(book);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, MainShop.class);
        startActivity(i);
        finish();
    }

    @SuppressLint("DefaultLocale")
    static void setDataView() {
        moneyView.setText(String.format("    %d", (int) money));
    }

    public void CustomDialog(final Context context, final String title, String text, final int price, int image) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), "font.ttf");
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog, null);
        LoadData loadData = new LoadData(context);
        words = new Gson().fromJson(new Custom((Activity) context).getStringInAssets((Activity) context,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());

        ImageView imageView = layout.findViewById(R.id.image);
        imageView.setImageResource(image);
        TextView _title = layout.findViewById(R.id.title);
        TextView _text = layout.findViewById(R.id.text);
        final TextView link1 = layout.findViewById(R.id.link1);
        final TextView link2 = layout.findViewById(R.id.link2);

        _title.setText(title.replace("Книга",words.get("Book")));
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
        link1.setOnClickListener(v -> {
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

        });
        link2.setOnClickListener(v -> {
            link2.setTextColor(Color.BLUE);
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(() -> {
                        link2.setTextColor(Color.parseColor("#ffffff"));
                        BookShop.buy(price, title);
                        dialog.cancel();
                    });
                }
            };
            timer.schedule(timerTask, 200);
        });
    }

    static void buy(int price, String title){
        if (money >= price) {
            money -= price;
            book += (title + ",");
            setDataView();
        }
    }
}