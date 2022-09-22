package com.niksaen.progersim.Program;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niksaen.progersim.R;
import com.niksaen.progersim.myClass.Custom;
import com.niksaen.progersim.myClass.DataSize;
import com.niksaen.progersim.myClass.LoadData;
import com.niksaen.progersim.myClass.PcSpecification;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class ProgrammUninstal {
    HashMap<String, Integer> programSize = new HashMap<>();

    //variables
    Object[] disk = new Object[]{0, ""};
    String[] list = new String[0];
    String program = "";
    int position_buff = 0, diskID;

    //classes
    Typeface typeface;
    int width = Typeface.BOLD;
    PcSpecification pcSpecification;
    LoadData loadData = new LoadData();
    Activity activity;
    AlertDialog dialog;
    DataSize dataSize;
    HashMap<String,String> words;

    void setProgramSize() {
        programSize.put("Ruby IDEA",8);
        programSize.put("Java IDEA",12);
        programSize.put("Virtual Studio",12);
        programSize.put("uniC#",30);
        programSize.put("C# GameLLC",50);
        programSize.put("CPPame",20);
        programSize.put("tauCPP Game",80);
        programSize.put("PythoAme",10);
        programSize.put("blaGameEngine",100);
        programSize.put("caJS Engine",20);
        programSize.put("JS Engn",60);
        programSize.put("JavaGame",150);
        programSize.put("LuaME",6);
        programSize.put("BioWPF",10);
        programSize.put("JaaFXID",20);
        programSize.put("C++ Qt ISE",11);
        programSize.put("WinForms App Creator",13);
        programSize.put("wxWidgets IDE",8);
        programSize.put("Kotlin IDE",20);
        programSize.put("Android IDEA",15);
        programSize.put("FlaWEB IDE",30);
        programSize.put("Local Host",16);
        programSize.put("Free BACK IDEA",18);
        programSize.put("Notepad",1);
    }

    public ProgrammUninstal(Activity act) {
        activity = act;
        typeface = Typeface.createFromAsset(((Context) activity).getAssets(), "font.ttf");
        loadData.setActivity(activity);
        dataSize = new DataSize(activity);
        pcSpecification = new PcSpecification(loadData, activity);
        words = new Gson().fromJson(new Custom(activity).getStringInAssets(activity,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());

    }

    public void start() {
        changeDisk();
        setProgramSize();
    }

    void changeDisk() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.dialog_disk_change, null);

        final TextView title = layout.findViewById(R.id.title),
                disk1 = layout.findViewById(R.id.disk1),
                disk2 = layout.findViewById(R.id.disk2),
                disk3 = layout.findViewById(R.id.disk3),
                disk4 = layout.findViewById(R.id.disk4),
                disk5 = layout.findViewById(R.id.disk5),
                disk6 = layout.findViewById(R.id.disk6);

        LinearLayout
                linearLayout1 = layout.findViewById(R.id.linearLayout1),
                linearLayout2 = layout.findViewById(R.id.linearLayout2),
                linearLayout3 = layout.findViewById(R.id.linearLayout3),
                linearLayout4 = layout.findViewById(R.id.linearLayout4),
                linearLayout5 = layout.findViewById(R.id.linearLayout5),
                linearLayout6 = layout.findViewById(R.id.linearLayout6);

        if(loadData.getYouData1().equals("")){
            linearLayout1.setVisibility(View.GONE);
        }
        if(loadData.getYouData2().equals("")){
            linearLayout2.setVisibility(View.GONE);
        }
        if(loadData.getYouData3().equals("")){
            linearLayout3.setVisibility(View.GONE);
        }
        if(loadData.getYouData4().equals("")){
            linearLayout4.setVisibility(View.GONE);
        }
        if(loadData.getYouData5().equals("")){
            linearLayout5.setVisibility(View.GONE);
        }
        if(loadData.getYouData6().equals("")){
            linearLayout6.setVisibility(View.GONE);
        }

        //style
        title.setTypeface(typeface, width);
        disk1.setTypeface(typeface, width);
        disk2.setTypeface(typeface, width);
        disk3.setTypeface(typeface, width);
        disk4.setTypeface(typeface, width);
        disk5.setTypeface(typeface, width);
        disk6.setTypeface(typeface, width);

        //translation
        title.setText(words.get("Select drive"));
        disk1.setText(words.get("Disk")+" 1");
        disk2.setText(words.get("Disk")+" 2");
        disk3.setText(words.get("Disk")+" 3");
        disk4.setText(words.get("Disk")+" 4");
        disk5.setText(words.get("Disk")+" 5");
        disk6.setText(words.get("Disk")+" 6");

        //onClickAction
        disk1.setOnClickListener(v -> {
            dialog.dismiss();
            pcSpecification = new PcSpecification(loadData, activity);
            disk[0] = pcSpecification.DATA1.get("Объём");
            disk[1] = pcSpecification.DATA1.get("Тип");
            list = loadData.getDiskContent1().replace("[", "").replace("]", "").split(",");
            diskID = 1;
            changeProgram();
        });
        disk2.setOnClickListener(v -> {
            dialog.dismiss();
            pcSpecification = new PcSpecification(loadData, activity);
            disk[0] = pcSpecification.DATA2.get("Объём");
            disk[1] = pcSpecification.DATA2.get("Тип");
            list = loadData.getDiskContent2().replace("[", "").replace("]", "").split(",");
            diskID = 2;
            changeProgram();
        });
        disk3.setOnClickListener(v -> {
            dialog.dismiss();
            pcSpecification = new PcSpecification(loadData, activity);
            disk[0] = pcSpecification.DATA3.get("Объём");
            disk[1] = pcSpecification.DATA3.get("Тип");
            list = loadData.getDiskContent3().replace("[", "").replace("]", "").split(",");
            diskID = 3;
            changeProgram();
        });
        disk4.setOnClickListener(v -> {
            dialog.dismiss();
            pcSpecification = new PcSpecification(loadData, activity);
            disk[0] = pcSpecification.DATA4.get("Объём");
            disk[1] = pcSpecification.DATA4.get("Тип");
            list = loadData.getDiskContent4().replace("[", "").replace("]", "").split(",");
            diskID = 4;
            changeProgram();
        });
        disk5.setOnClickListener(v -> {
            dialog.dismiss();
            pcSpecification = new PcSpecification(loadData, activity);
            disk[0] = pcSpecification.DATA5.get("Объём");
            disk[1] = pcSpecification.DATA5.get("Тип");
            list = loadData.getDiskContent5().replace("[", "").replace("]", "").split(",");
            diskID = 5;
            changeProgram();
        });
        disk6.setOnClickListener(v -> {
            dialog.dismiss();
            pcSpecification = new PcSpecification(loadData, activity);
            disk[0] = pcSpecification.DATA6.get("Объём");
            disk[1] = pcSpecification.DATA6.get("Тип");
            list = loadData.getDiskContent6().replace("[", "").replace("]", "").split(",");
            diskID = 6;
            changeProgram();
        });

        Button close = layout.findViewById(R.id.close);
        close.setOnClickListener(v -> dialog.dismiss());

        builder.setView(layout);
        dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
    }

    void changeProgram() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LoadData loadData = new LoadData();
        loadData.setActivity(activity);
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.dialog_program_change, null);

        TextView title = layout.findViewById(R.id.title);
        ListView listView = layout.findViewById(R.id.listView);

        //adapters
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(activity, R.layout.item_for_program, list) {
            final Typeface font = Typeface.createFromAsset(activity.getAssets(), "font.ttf");

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTypeface(font, Typeface.BOLD);
                return v;
            }
        };
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                if(list[position].startsWith(" ")) {
                    program = list[position].replaceFirst(" ", "");
                }
                else{
                    program = list[position];
                }
                if (program.equals("")) {
                    dialog.dismiss();
                } else {
                    position_buff = position;
                    politicDialog();
                }
            }
        };

        //style
        title.setTypeface(typeface, width);
        listView.setAdapter(stringArrayAdapter);
        listView.setOnItemClickListener(onItemClickListener);
        //translation
        title.setText(words.get("Select a program"));

        Button close = layout.findViewById(R.id.close);
        close.setOnClickListener(v -> dialog.dismiss());

        builder.setView(layout);
        dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
    }

    void politicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.dialog_politic, null);

        TextView title = layout.findViewById(R.id.title),
                text = layout.findViewById(R.id.text);
        final CheckBox checkBox1 = layout.findViewById(R.id.checkBox1),
                checkBox2 = layout.findViewById(R.id.checkBox2);
        Button button = layout.findViewById(R.id.button);

        //style
        title.setTypeface(typeface, width);
        text.setTypeface(typeface, width);
        checkBox1.setTypeface(typeface, width);
        checkBox2.setTypeface(typeface, width);
        button.setTypeface(typeface, width);

        checkBox1.setText("Да");
        checkBox2.setText("Нет");
        button.setText("Продолжить");

        //translation
        title.setText(words.get("The confirmation"));
        text.setText(words.get("Are you sure you want to remove this program?"));
        checkBox1.setText(words.get("Yes"));
        checkBox2.setText(words.get("Not"));
        button.setText(words.get("Continue"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox1.isChecked()) {
                    dialog.dismiss();
                    uninstallation();
                } else {
                    dialog.dismiss();
                    disk[0] = 0;
                    disk[1] = "";
                    position_buff = 0;
                    program = "";
                }
            }
        });

        Button close = layout.findViewById(R.id.close);
        close.setOnClickListener(v -> dialog.dismiss());

        builder.setView(layout);
        dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
    }

    void uninstallation() {
        final Timer timer = new Timer();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.dialog_install, null);

        final TextView title = layout.findViewById(R.id.title),
                progress = layout.findViewById(R.id.progress),
                status = layout.findViewById(R.id.status);
        final ProgressBar progressBar = layout.findViewById(R.id.progressBar);
        final Button end = layout.findViewById(R.id.end);

        //adapters
        View.OnClickListener onClickListener1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                dialog.dismiss();
                disk[0] = "0";
                disk[1] = "";
                position_buff = 0;
                program = "";
            }
        };

        final View.OnClickListener onClickListener2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        };

        //style
        title.setTypeface(typeface, width);
        progress.setTypeface(typeface, width);
        status.setTypeface(typeface);
        end.setTypeface(typeface, width);
        end.setText(words.get("Cancel"));
        title.setText(words.get("Removal"));
        end.setOnClickListener(onClickListener1);

        //variables
        final int[] percent = {0};
        final String[] statusArray = new String[15];
        Arrays.fill(statusArray, "");

        //logic
        final Runnable logic = new Runnable() {
            @Override
            public void run() {
                switch (percent[0]) {
                    case 2: {
                        statusArray[0] = words.get("Removing additional files ...");
                        break;
                    }
                    case 19: {
                        statusArray[1] = words.get("Additional files removed");
                        break;
                    }
                    case 20: {
                        statusArray[2] = words.get("Removing the program ...");
                        break;
                    }
                    case 95: {
                        statusArray[3] = words.get("The program has been removed");
                        break;
                    }
                    case 97: {
                        statusArray[4] = words.get("Clearing the cache ...");
                        break;
                    }
                    case 99: {
                        statusArray[5] = words.get("The cache is cleared");
                        break;
                    }

                    case 100: {
                        statusArray[14] = words.get("Program removal completed successfully");
                        end.setText(words.get("Complete"));
                        end.setOnClickListener(onClickListener2);
                        uninstall();
                        timer.cancel();
                        break;
                    }
                }

                progressBar.setProgress(percent[0]);
                progress.setText(words.get("Removal completed on: ") + percent[0] + "%");
                status.setText(Arrays.toString(statusArray).replace("[", "").replace("]", "").replace(",", "\n"));
                percent[0]++;
            }
        };

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(logic);
            }
        };
        if (String.valueOf(disk[1]).equals("SSD")) {
            timer.scheduleAtFixedRate(timerTask, 0, programSize.get(program) * 7);
        } else {
            timer.scheduleAtFixedRate(timerTask, 0, programSize.get(program) * 11);
        }

        Button close = layout.findViewById(R.id.close);
        close.setOnClickListener(v -> dialog.dismiss());

        builder.setView(layout);
        dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
    }

    void uninstall() {
        list[position_buff] = "";
        String diskContent = Arrays.toString(list).replace("[", "").replace("]", "");
        switch (diskID) {
            case 1: {
                loadData.setDiskContent1(diskContent);
                int buff = dataSize.getData1();
                dataSize.setData1(buff + programSize.get(program));
                break;
            }
            case 2: {
                loadData.setDiskContent2(diskContent);
                int buff = dataSize.getData2();
                dataSize.setData2(buff + programSize.get(program));
                break;
            }
            case 3: {
                loadData.setDiskContent3(diskContent);
                int buff = dataSize.getData3();
                dataSize.setData3(buff + programSize.get(program));
                break;
            }
            case 4: {
                loadData.setDiskContent4(diskContent);
                int buff = dataSize.getData4();
                dataSize.setData4(buff + programSize.get(program));
                break;
            }
            case 5: {
                loadData.setDiskContent5(diskContent);
                int buff = dataSize.getData5();
                dataSize.setData5(buff + programSize.get(program));
                break;
            }
            case 6: {
                loadData.setDiskContent6(diskContent);
                int buff = dataSize.getData6();
                dataSize.setData6(buff + programSize.get(program));
                break;
            }
        }
    }
}
