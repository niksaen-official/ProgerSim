package com.niksaen.progersim.myClass;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niksaen.progersim.LibraryActivity;
import com.niksaen.progersim.R;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class LibraryAdapter extends ArrayAdapter {
    private List data;
    private Context context;
    LoadData loadData = new LoadData();
    Custom custom;
    HashMap<String,String> words;

    public LibraryAdapter(Context context, List data) {
        super(context, R.layout.item);
        this.data = data;
        this.context = context;
        loadData.setActivity((Activity) context);
        custom = new Custom((Activity) context);
        words = new Gson().fromJson(new Custom((Activity) context).getStringInAssets((Activity) context,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());

    }

    @Override
    public int getCount() {
        // возвращаем количество элементов списка
        return data.size();
    }

    @Override
    public BookItem getItem(int position) {
        // получение одного элемента по индексу
        return (BookItem) data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Объект для каждой из записей
        final BookItem bookItem = (BookItem) data.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // создаём View и указываем наполнить её из R.layout.item, т.е. из нашего item.xml

        Typeface font = Typeface.createFromAsset(context.getAssets(), "font.ttf");

        View layout = inflater.inflate(R.layout.item_lib, null);
        final TextView title = layout.findViewById(R.id.title),
                status = layout.findViewById(R.id.status);
        ImageView imageView = layout.findViewById(R.id.imageView);

        title.setTypeface(font, Typeface.BOLD);
        status.setTypeface(font, Typeface.BOLD);

        if (bookItem.name.equals("false")) {
            layout.setClickable(false);
            title.setText(words.get("You haven't bought any books"));
            title.setGravity(1);
            status.setVisibility(View.GONE);
            imageView.setVisibility(View.GONE);
        } else {
            title.setText(bookItem.name.replace("Книга",words.get("Book")));
            imageView.setImageResource(bookItem.image);

            if(loadData.getLearning()){
                status.setVisibility(View.GONE);
            }
            else if (loadData.getLanguages().contains(bookItem.name)) {
                status.setVisibility(View.VISIBLE);
                status.setText(words.get("Explored"));
                status.setClickable(false);
            }
            else {
                status.setText(words.get("Explore"));
                status.setOnClickListener(v -> {
                    BookValid valid = new BookValid((Activity) context);
                    if (valid.checkForLearn(bookItem.name)) {
                        status.setClickable(false);
                        TimerTask task = new TimerTask() {
                            @Override
                            public void run() {
                                ((Activity) context).runOnUiThread(() -> {
                                    if(!loadData.getLearning() && !loadData.getLanguages().contains(bookItem.name)) {
                                        status.setText(words.get("Study..."));
                                        loadData.setTimeLearn(bookItem.timeLearn);
                                        loadData.setBookIconForLearn(bookItem.image);
                                        loadData.setBookForLearn(bookItem.name);
                                        loadData.setLearning(true);
                                        status.setClickable(false);
                                    }
                                });
                            }
                        };
                        Timer timer = new Timer();
                        timer.schedule(task, 0, bookItem.timeLearn * 10);
                    } else {
                        custom.ErrorDialog(words.get("You don't have enough skills"));
                    }
                });
            }
            layout.setOnClickListener(v -> LibraryActivity.ViewDialog(context, bookItem.name, bookItem.text, bookItem.image));
        }
        return layout;
    }
}