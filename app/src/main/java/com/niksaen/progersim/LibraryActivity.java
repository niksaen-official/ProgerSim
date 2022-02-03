package com.niksaen.progersim;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niksaen.progersim.myClass.BookItem;
import com.niksaen.progersim.myClass.Custom;
import com.niksaen.progersim.myClass.LibraryAdapter;
import com.niksaen.progersim.myClass.LoadData;

import java.util.ArrayList;
import java.util.HashMap;

public class LibraryActivity extends Activity {

    Custom custom = new Custom(this);
    LoadData loadData = new LoadData();

    ArrayList<BookItem> bookItemList = new ArrayList<>();
    static HashMap<String,String> words;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        loadData.setActivity(this);
        words = new Gson().fromJson(new Custom(this).getStringInAssets(this,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());

        if (loadData.getBook().equals("")) {
            bookItemList.add(new BookItem("false"));
        } else {
            setBookItemMap();
            setBookItemList();
        }

        // Ищем ListView
        ListView list = findViewById(R.id.list);
        LibraryAdapter listAdapter = new LibraryAdapter(this, bookItemList);
        list.setAdapter(listAdapter);

        ImageView avatar = findViewById(R.id.avatar);
        TextView textView = findViewById(R.id.nikName);
        textView.setTypeface(Typeface.createFromAsset(getAssets(), "font.ttf"), Typeface.BOLD);
        textView.setText(loadData.getPlayerName());
        textView.setTextColor(Color.parseColor(loadData.getNikColor()));
        avatar.setImageResource(loadData.getImage());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
        finish();
    }

    HashMap<String, BookItem> bookItemMap = new HashMap<>();

    void setBookItemMap() {
        bookItemMap.put("Книга Java", new BookItem("Книга Java", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book3.txt"), R.drawable.book_java, 1200));
        bookItemMap.put("Книга C#", new BookItem("Книга C#", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book1.txt"), R.drawable.book_cs, 800));
        bookItemMap.put("Книга C++", new BookItem("Книга C++", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book2.txt"), R.drawable.book_cpp, 1000));
        bookItemMap.put("Книга Python", new BookItem("Книга Python", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book4.txt"), R.drawable.book_python, 800));
        bookItemMap.put("Книга JavaScript", new BookItem("Книга JavaScript", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book10.txt"), R.drawable.book_js, 900));
        bookItemMap.put("Книга uniC#", new BookItem("Книга uniC#", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book11.txt"), R.drawable.book_unics, 1540));
        bookItemMap.put("Книга C# Game LLC", new BookItem("Книга C# Game LLC", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book12.txt"), R.drawable.book_csgamellc, 3000));
        bookItemMap.put("Книга CPPame", new BookItem("Книга CPPame", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book13.txt"), R.drawable.book_cppame, 1200));
        bookItemMap.put("Книга tauCPP Game", new BookItem("Книга tauCPP Game", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book14.txt"), R.drawable.book_taucppgame, 5000));
        bookItemMap.put("Книга PythoAme", new BookItem("Книга PythoAme", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book15.txt"), R.drawable.book_puthoame, 1500));
        bookItemMap.put("Книга blaGame Engine", new BookItem("Книга blaGame Engine", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book16.txt"), R.drawable.book_blagameengine, 8000));
        bookItemMap.put("Книга caJS Engine", new BookItem("Книга caJS Engine", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book17.txt"), R.drawable.book_cajsengine, 1350));
        bookItemMap.put("Книга JS Engn", new BookItem("Книга JS Engn", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book18.txt"), R.drawable.book_jsengn, 3000));
        bookItemMap.put("Книга C# WPF", new BookItem("Книга C# WPF", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book21.txt"), R.drawable.book_wpf, 1800));
        bookItemMap.put("Книга Qt", new BookItem("Книга Qt", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book20.txt"), R.drawable.book_qt, 1560));
        bookItemMap.put("Книга JavaFX", new BookItem("Книга JavaFX", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book19.txt"), R.drawable.book_javafx, 1200));
        bookItemMap.put("Книга Kotlin", new BookItem("Книга Kotlin", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book23.txt"), R.drawable.book_kotlin, 1600));
        bookItemMap.put("Книга Android API", new BookItem("Книга Android API", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book22.txt"), R.drawable.book_androidapi, 2000));
        bookItemMap.put("Книга HTML", new BookItem("Книга HTML", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book8.txt"), R.drawable.book_html, 500));
        bookItemMap.put("Книга CSS", new BookItem("Книга CSS", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book9.txt"), R.drawable.book_css, 750));
        bookItemMap.put("Книга PHP", new BookItem("Книга PHP", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book5.txt"), R.drawable.book_php, 900));
        bookItemMap.put("Книга Node.js", new BookItem("Книга Node.js", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book6.txt"), R.drawable.book_nodejs, 900));
        bookItemMap.put("Книга MySQL", new BookItem("Книга MySQL", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book7.txt"), R.drawable.book_mysql, 1200));
        bookItemMap.put("Книга JavaGame", new BookItem("Книга JavaGame", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book24.txt"), R.drawable.book_javagame, 2000));
        bookItemMap.put("Книга Lua", new BookItem("Книга Lua", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book25.txt"), R.drawable.book_lua, 2000));
        bookItemMap.put("Книга LuaME", new BookItem("Книга LuaME", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book26.txt"), R.drawable.book_luame, 1500));
        bookItemMap.put("Книга по C", new BookItem("Книга по C", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book27.txt"), R.drawable.book_c, 1100));
        bookItemMap.put("Книга Ruby", new BookItem("Книга Ruby", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book28.txt"), R.drawable.book_ruby, 2000));
        bookItemMap.put("Книга по WinForms", new BookItem("Книга по WinForms", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book29.txt"), R.drawable.book_winforms, 1700));
        bookItemMap.put("Книга по wxWidgets", new BookItem("Книга по wxWidgets", custom.getStringInAssets(this, "textFile/"+loadData.getLanguage()+"/book/book30.txt"), R.drawable.book_wxwidgets, 2500));

    }

    void setBookItemList() {
        String[] books = loadData.getBook().replace("]", "").replace("[", "").split(",");
        for (String book : books) {
            bookItemList.add(bookItemMap.get(book));
        }
    }

    public static void ViewDialog(Context context, String title, String text, int image) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), "font.ttf");
        int width = Typeface.BOLD;
        final AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layout = layoutInflater.inflate(R.layout.dialog, null);
        TextView
                titleView = layout.findViewById(R.id.title),
                textView = layout.findViewById(R.id.text),
                link1 = layout.findViewById(R.id.link1),
                link2 = layout.findViewById(R.id.link2);
        ImageView imageView = layout.findViewById(R.id.image);

        titleView.setTypeface(font, width);
        textView.setTypeface(font);
        link1.setTypeface(font, width);
        link2.setVisibility(View.GONE);

        titleView.setText(title.replace("Книга",words.get("Book")));
        textView.setText(text);
        imageView.setImageResource(image);

        builder.setView(layout);
        dialog = builder.create();

        link1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}