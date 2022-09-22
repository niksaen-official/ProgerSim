package com.niksaen.progersim.myClass;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class PopupListView {
    private Context context;
    public PopupListView(Context context){this.context = context; }

    private String Title;
    public void setTitle(String str){Title = str;}

    private ArrayAdapter<String> adapter;
    public void setAdapter(ArrayAdapter<String> adapter) { this.adapter = adapter; }

    private AlertDialog alertDialog;

    //listeners
    private AdapterView.OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) { this.onItemClickListener = onItemClickListener; }

    private DialogInterface.OnCancelListener onCancelListener;
    public void setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) { this.onCancelListener = onCancelListener; }

    public void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LinearLayout layout = new LinearLayout(context);
        TextView title = new TextView(context);
        ListView listView = new ListView(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.parseColor("#1e1e1e"));
        layout.addView(title, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(listView, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setPadding(16,16,16,16);

        title.setTextSize(35);
        title.setPadding(0,0,0,16);
        title.setTextColor(Color.parseColor("#FFFFFF"));
        title.setTypeface(Typeface.createFromAsset(context.getAssets(),"font.ttf"),Typeface.BOLD);
        if(Title != null){
            title.setText(Title);
        }else{
            title.setVisibility(View.GONE);
        }

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener);

        builder.setView(layout);
        alertDialog = builder.create();
        alertDialog.setOnCancelListener(onCancelListener);
        alertDialog.show();
    }
    public void update(){ adapter.notifyDataSetChanged(); }
    public void close(){ alertDialog.dismiss(); }
}
