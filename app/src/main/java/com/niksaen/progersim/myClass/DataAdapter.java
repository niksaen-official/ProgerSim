package com.niksaen.progersim.myClass;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niksaen.progersim.shops.BookShop;
import com.niksaen.progersim.shops.PcShopActivity;
import com.niksaen.progersim.shops.PetsShopActivity;
import com.niksaen.progersim.shops.ProgramShopActivity;
import com.niksaen.progersim.R;
import com.niksaen.progersim.shops.WallpaperShop;
import com.niksaen.progersim.shops.pcShop.CoolerActivity;
import com.niksaen.progersim.shops.pcShop.CpuActivity;
import com.niksaen.progersim.shops.pcShop.DataActivity;
import com.niksaen.progersim.shops.pcShop.GpuActivity;
import com.niksaen.progersim.shops.pcShop.MoboActivity;
import com.niksaen.progersim.shops.pcShop.PsuActivity;
import com.niksaen.progersim.shops.pcShop.RamActivity;

import java.util.HashMap;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<ViewData> dataList;
    private Context context;
    private HashMap<String,String> words;

    Activity activity;

    public DataAdapter(Context context, List<ViewData> dataList){
        this.dataList = dataList;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        LoadData loadData = new LoadData();
        loadData.setActivity((Activity) context);
        words = new Gson().fromJson(new Custom((Activity) context).getStringInAssets((Activity) context,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataAdapter.ViewHolder holder, int position) {
        ViewData data = dataList.get(position);
        final String text = data.getText();
        final String title = data.getTitle();
        final int image = data.getImageId();
        final int price = data.getPrice();
        holder.imageView.setImageResource(data.getImageId());
        holder.textView.setText(title.replace("Книга",words.get("Book")).replace("по",words.get("on")));

        holder.textView.setTypeface(Typeface.createFromAsset(context.getAssets(), "font.ttf"), Typeface.BOLD);

        final String TYPE = data.getTYPE();
        holder.itemView.setOnClickListener(v -> {
            holder.textView.setTextColor(Color.BLUE);
            switch (TYPE) {
                case "MOBO":
                    activity = new MoboActivity();
                    ((MoboActivity) activity).CustomDialog(context, title, text, price, image);
                    break;
                case "CPU":
                    activity = new CpuActivity();
                    ((CpuActivity) activity).CustomDialog(context, title, text, price, image);
                    break;
                case "RAM":
                    activity = new RamActivity();
                    ((RamActivity) activity).CustomDialog(context, title, text, price, image);
                    break;
                case "COOLER":
                    activity = new CoolerActivity();
                    ((CoolerActivity) activity).CustomDialog(context, title, text, price, image);
                    break;
                case "GPU":
                    activity = new GpuActivity();
                    ((GpuActivity) activity).CustomDialog(context, title, text, price, image);
                    break;
                case "DATA":
                    activity = new DataActivity();
                    ((DataActivity) activity).CustomDialog(context, title, text, price, image);
                    break;
                case "PSU":
                    activity = new PsuActivity();
                    ((PsuActivity) activity).CustomDialog(context, title, text, price, image);
                    break;
                case "BOOK":
                    activity = new BookShop();
                    ((BookShop) activity).CustomDialog(context, title, text, price, image);
                    break;
                case "program":
                    activity = new ProgramShopActivity();
                    ((ProgramShopActivity) activity).CustomDialog(context, title, text, price, image);
                    break;
                case "pc":
                    activity = new PcShopActivity();
                    ((PcShopActivity) activity).CustomDialog(context, title, text, price, image);
                    break;
                case "wallpaper":
                    activity = new WallpaperShop();
                    ((WallpaperShop) activity).CustomDialog(context, title, text, price, image);
                    break;
                case "pets":
                    activity = new PetsShopActivity();
                    ((PetsShopActivity) activity).CustomDialog(context, title, text, price, image);
                    break;
            }
            holder.textView.setTextColor(Color.WHITE);
        });

    }

    @Override
    public int getItemCount(){
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView textView;

        ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.image);
            textView = view.findViewById(R.id.text);
        }
    }

}
