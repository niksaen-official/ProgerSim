package com.niksaen.progersim.Program;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niksaen.progersim.R;
import com.niksaen.progersim.myClass.Custom;
import com.niksaen.progersim.myClass.LoadData;
import com.niksaen.progersim.myClass.PcSpecification;
import com.niksaen.progersim.myClass.Work;

import java.util.ArrayList;
import java.util.HashMap;

public class MyPc {

    Activity activity;
    HashMap<String, String> MOBO, CPU, RAM1, RAM2, RAM3, RAM4, GPU, GPUI, GPU2, PSU;
    ArrayList<String> moboSpecifications = new ArrayList<>(),
            cpuSpecification = new ArrayList<>(),
            ramSpecifications = new ArrayList<>(),
            gpuSpecification = new ArrayList<>(),
            dataSpecification = new ArrayList<>(),
            psuSpecification = new ArrayList<>();

    //classes
    PcSpecification pcSpecification;
    LoadData loadData = new LoadData();

    public MyPc(Activity activity1) {
        activity = activity1;
        loadData.setActivity(activity);
        pcSpecification = new PcSpecification(loadData, activity);
        MOBO = pcSpecification.MOBO;
        CPU = pcSpecification.CPU;
        RAM1 = pcSpecification.RAM1;
        RAM2 = pcSpecification.RAM2;
        RAM3 = pcSpecification.RAM3;
        RAM4 = pcSpecification.RAM4;
        GPU = pcSpecification.GPU;
        GPUI = pcSpecification.GPUI;
        GPU2 = pcSpecification.GPU2;
        PSU = pcSpecification.PSU;
    }

    HashMap<String,String> words;
    public void start() {
        words = new Gson().fromJson(new Custom(activity).getStringInAssets(activity,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());

        setMoboSpecifications();
        setCpuSpecification();
        setRamSpecifications();
        setGpuSpecification();
        setDataSpecification();
        setPsuSpecification();
        setDialog();
    }

    void setMoboSpecifications() {
        moboSpecifications.add(words.get("Model")+": " + loadData.getYouMobo());
        moboSpecifications.add(words.get("Socket")+": " + MOBO.get("Сокет"));
        moboSpecifications.add(words.get("RAM characteristics")+":");
        moboSpecifications.add(words.get("Memory type")+": " + MOBO.get("Тип памяти"));
        moboSpecifications.add(words.get("Number of memory slots")+": " + MOBO.get("Слотов памяти"));
        moboSpecifications.add(words.get("Number of channels")+": " + MOBO.get("Каналов"));
        moboSpecifications.add(words.get("Minimum frequency")+": " + MOBO.get("Мин частота"));
        moboSpecifications.add(words.get("Maximum frequency")+": " + MOBO.get("Макс частота"));
        moboSpecifications.add(words.get("Maximum volume")+": " + MOBO.get("Макс объем"));
        moboSpecifications.add(words.get("Ports")+" SATA: " + MOBO.get("Портов Sata"));
    }

    void setCpuSpecification() {
        cpuSpecification.add(words.get("Model")+": " + loadData.getYouCpu());
        cpuSpecification.add(words.get("Socket")+": " + CPU.get("Сокет"));
        cpuSpecification.add(words.get("Number of cores")+": " + CPU.get("Ядер"));
        cpuSpecification.add(words.get("Number of threads")+": " + CPU.get("Потоков"));
        cpuSpecification.add(words.get("Cache")+": " + CPU.get("Кэш"));
        cpuSpecification.add(words.get("Frequency")+": " + CPU.get("Частота") + "MHz");
        cpuSpecification.add(words.get("Overclocking capability")+": " + CPU.get("Разгон"));
        cpuSpecification.add(words.get("RAM characteristics")+":");
        cpuSpecification.add(words.get("Memory type")+": " + CPU.get("Тип памяти"));
        cpuSpecification.add(words.get("Maximum volume")+": " + CPU.get("Макс объём"));
        cpuSpecification.add(words.get("Minimum frequency")+": " + CPU.get("Мин частота"));
        cpuSpecification.add(words.get("Maximum frequency")+": " + CPU.get("Макс частота"));
        cpuSpecification.add(words.get("Number of channels")+": " + CPU.get("Каналов"));
        cpuSpecification.add(words.get("Heat release")+": " + CPU.get("TDP") + "W");
        cpuSpecification.add(words.get("Integrated graphics core")+": " + CPU.get("Графическое ядро"));
        if (CPU.get("Графическое ядро").equals("+")) {
            gpuSpecification.add(words.get("Integrated graphics core")+": ");
            gpuSpecification.add(words.get("Model")+": " + GPUI.get("Графический процессор(Integrated)"));
            gpuSpecification.add(words.get("Frequency")+": " + GPUI.get("Частота(Integrated)"));
        }
    }

    void setRamSpecifications() {
        if (!loadData.getYouRam1().equals("")) {
            ramSpecifications.add(words.get("RAM characteristics")+" ("+words.get("Slot")+" 1):" );
            ramSpecifications.add(words.get("Model")+": "+loadData.getYouRam1());
            ramSpecifications.add(words.get("Memory type")+": " + RAM1.get("Тип"));
            ramSpecifications.add(words.get("Volume")+": " + RAM1.get("Объём") + "Gb");
            ramSpecifications.add(words.get("Frequency")+": " + RAM1.get("Частота")+"MHz");
            ramSpecifications.add(words.get("Throughput")+": " + RAM1.get("Пропускная способность")+"PC");
        }
        if (!loadData.getYouRam2().equals("")) {
            ramSpecifications.add(words.get("RAM characteristics")+" ("+words.get("Slot")+" 2): ");
            ramSpecifications.add(words.get("Model")+": "+loadData.getYouRam2());
            ramSpecifications.add(words.get("Memory type")+": " + RAM2.get("Тип"));
            ramSpecifications.add(words.get("Volume")+": " + RAM2.get("Объём") + "Gb");
            ramSpecifications.add(words.get("Frequency")+": " + RAM2.get("Частота")+"MHz");
            ramSpecifications.add(words.get("Throughput")+": " + RAM2.get("Пропускная способность")+"PC");
        }
        if (!loadData.getYouRam3().equals("")) {
            ramSpecifications.add(words.get("RAM characteristics")+" ("+words.get("Slot")+" 3): ");
            ramSpecifications.add(words.get("Model")+": "+loadData.getYouRam3());
            ramSpecifications.add(words.get("Memory type")+": " + RAM3.get("Тип"));
            ramSpecifications.add(words.get("Volume")+": " + RAM3.get("Объём") + "Gb");
            ramSpecifications.add(words.get("Frequency")+": " + RAM3.get("Частота")+"MHz");
            ramSpecifications.add(words.get("Throughput")+": " + RAM3.get("Пропускная способность")+"PC");
        }
        if (!loadData.getYouRam4().equals("")) {
            ramSpecifications.add(words.get("RAM characteristics")+" ("+words.get("Slot")+" 4): " );
            ramSpecifications.add(words.get("Model")+": "+loadData.getYouRam4());
            ramSpecifications.add(words.get("Memory type")+": " + RAM4.get("Тип"));
            ramSpecifications.add(words.get("Volume")+": " + RAM4.get("Объём") + "Gb");
            ramSpecifications.add(words.get("Frequency")+": " + RAM4.get("Частота")+"MHz");
            ramSpecifications.add(words.get("Throughput")+": " + RAM4.get("Пропускная способность")+"PC");
        }
    }

    void setGpuSpecification() {
        if (!loadData.getYouGpu().equals("")) {
            gpuSpecification.add(words.get("Model")+": " + loadData.getYouGpu());
            gpuSpecification.add(words.get("GPU")+": " + GPU.get("Графический процессор"));
            gpuSpecification.add(words.get("Number of video chips")+": " + GPU.get("Видеочипов"));
            gpuSpecification.add(words.get("Frequency")+": " + GPU.get("Частота"));
            gpuSpecification.add(words.get("Video memory")+":");
            gpuSpecification.add(words.get("Volume")+": " + GPU.get("Объём")+"Gb");
            gpuSpecification.add(words.get("Memory type")+": " + GPU.get("Тип"));
            gpuSpecification.add(words.get("Throughput")+": " + GPU.get("Пропускная способность"));
        }
        if (!loadData.getYouGpu2().equals("")) {
            gpuSpecification.add(words.get("Model")+" ("+words.get("Slot")+" 2): " + loadData.getYouGpu2());
            gpuSpecification.add(words.get("GPU")+": " + GPU2.get("Графический процессор"));
            gpuSpecification.add(words.get("Number of video chips")+": " + GPU2.get("Видеочипов"));
            gpuSpecification.add(words.get("Frequency")+": " + GPU2.get("Частота"));
            gpuSpecification.add(words.get("Video memory")+":");
            gpuSpecification.add(words.get("Volume")+": " + GPU2.get("Объём")+"Gb");
            gpuSpecification.add(words.get("Memory type")+": " + GPU2.get("Тип"));
            gpuSpecification.add(words.get("Throughput")+": " + GPU2.get("Пропускная способность"));
        }
    }

    void setDataSpecification() {
        String[] DATA = {loadData.getYouData1(), loadData.getYouData2(), loadData.getYouData3(), loadData.getYouData4(), loadData.getYouData5(), loadData.getYouData6()};
        for (int i = 0; i < DATA.length; i++) {
            if (!DATA[i].equals("")) {
                dataSpecification.add(words.get("Storage device")+" ("+words.get("Slot") + (i + 1) + "):" );
                dataSpecification.add(words.get("Model")+DATA[i]);
                switch (DATA[i]) {
                    case "ZShark 128S-2500": {
                        dataSpecification.add(words.get("Type")+": SSD");
                        dataSpecification.add(words.get("Volume")+": 128Gb");
                        break;
                    }
                    case "ZShark 256S-4000": {
                        dataSpecification.add(words.get("Type")+": SSD");
                        dataSpecification.add(words.get("Volume")+": 256Gb");
                        break;
                    }
                    case "Offside 512Gb-2500": {
                        dataSpecification.add(words.get("Type")+": HDD");
                        dataSpecification.add(words.get("Volume")+": 512Gb");
                        break;
                    }
                    case "Offside 1Tb-5000": {
                        dataSpecification.add(words.get("Type")+": HDD");
                        dataSpecification.add(words.get("Volume")+": 1024Gb");
                        break;
                    }
                }
            } else continue;
        }
    }

    void setPsuSpecification() {
        psuSpecification.add(words.get("Power supply")+": " + loadData.getYouPsu());
        psuSpecification.add(words.get("Power")+": " + PSU.get("Мощность"));
    }

    ArrayAdapter<String> setAdapter(ArrayList<String> arrayList, final String text) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, R.layout.spinner2, arrayList) {
            final Typeface font = Typeface.createFromAsset(activity.getAssets(), "font.ttf");

            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setText(text);
                ((TextView) v).setTypeface(font, Typeface.BOLD);
                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                ((TextView) v).setTypeface(font, Typeface.BOLD);
                return v;
            }
        };
        return adapter;
    }

    void setDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.mypc_program, null);
        final Typeface font = Typeface.createFromAsset(activity.getAssets(), "font.ttf");

        TextView title = layout.findViewById(R.id.title);
        Button close = layout.findViewById(R.id.close);
        Spinner mobo = layout.findViewById(R.id.mobo),
                cpu = layout.findViewById(R.id.cpu),
                ram = layout.findViewById(R.id.ram),
                gpu = layout.findViewById(R.id.gpu),
                data = layout.findViewById(R.id.data),
                psu = layout.findViewById(R.id.psu);

        //style
        mobo.setAdapter(setAdapter(moboSpecifications, words.get("Motherboard specifications")));
        cpu.setAdapter(setAdapter(cpuSpecification, words.get("Processor specifications")));
        ram.setAdapter(setAdapter(ramSpecifications, words.get("RAM characteristics")));
        gpu.setAdapter(setAdapter(gpuSpecification, words.get("Graphics card specifications")));
        data.setAdapter(setAdapter(dataSpecification, words.get("Drive characteristics")));
        psu.setAdapter(setAdapter(psuSpecification, words.get("Power supply characteristics")));
        title.setText(words.get("My computer"));
        title.setTypeface(font, Typeface.BOLD);

        builder.setView(layout);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moboSpecifications.clear();
                cpuSpecification.clear();
                ramSpecifications.clear();
                gpuSpecification.clear();
                dataSpecification.clear();
                psuSpecification.clear();
                alertDialog.dismiss();
            }
        });
    }
}
