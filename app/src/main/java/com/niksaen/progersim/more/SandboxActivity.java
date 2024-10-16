package com.niksaen.progersim.more;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.niksaen.progersim.MainPage;
import com.niksaen.progersim.R;
import com.niksaen.progersim.myClass.Custom;
import com.niksaen.progersim.myClass.LoadData;
import com.niksaen.progersim.myClass.PcIronAdapter;
import com.niksaen.progersim.myClass.PopupListView;

import java.util.HashMap;

public class SandboxActivity extends AppCompatActivity {

    //variables
    String socket;
    String ramType;

    int tdp;
    int minFrequency, maxFrequency, maxMemorySize;
    int maxData;
    int ramSlotCount = 2;
    int pciSlotCount = 1;

    int minPsu = 0;

    String typeForInstalled, nameForInstalled;
    int buffPosition;

    boolean caseInstalled, moboInstalled, cpuInstalled, coolerInstalled, ramInstalled, gpuInstalled, dataInstalled, psuInstalled;
    String[] caseArray, moboArray, cpuArray, coolerArray, ramArray, gpuArray, dataArray, psuArray;

    //view
    TextView nikView;
    ImageView avatar;
    TextView cpuTextView, moboTextView, caseTextView, coolerTextView, ramTextView, dataTextView, gpuTextView, psuTextView;
    RelativeLayout caseImage;
    RelativeLayout moboImage;
    LinearLayout cpuImage, coolerImage, ram1Image, ram2Image, ram3Image, ram4Image, gpuImage, gpu2Image, data1Image, data2Image, data3Image, data4Image, data5Image, data6Image, psuImage;

    TextView text;
    Button button1, button2, button3, button4, button5, button6;

    //дополнительные компоненты
    Activity parent = this;
    Typeface font;
    Custom custom = new Custom(this);
    LoadData loadData = new LoadData();
    HashMap<String,String> words;
    PopupListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_iron);
        loadData.setActivity(this);
        listView = new PopupListView(this);
        words = new Gson().fromJson(new Custom(this).getStringInAssets(this,"language/"+loadData.getLanguage()+".json"),new TypeToken<HashMap<String,String>>(){}.getType());

        initView();
        setViewStyle();
        logic();
        setList();
        initAdapters();

        avatar.setImageResource(loadData.getImage());
        nikView.setText(loadData.getPlayerName());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        font = Typeface.createFromAsset(getAssets(), "font.ttf");
    }

    private void setList(){
        coolerArray = new String[]{
                "Cooler Tetr [R55WH300]",
                "Cooler Race [Q55BL300]",
                "Cooler Race [Q60BL400]",
                "Cooler Race [Q60RE400]",
                "Cooler Tetr [R65BL450]",
                "PcGaming Black Series",
                "PcGaming White Series"
        };
        cpuArray = new String[]{
                "BMD Bthlon X4 840",
                "BMD A6-7480",
                "Jntel Deleron G3930",
                "Jntel Rentium G4400",
                "BMD A6-7400K",
                "BMD A6 PRO-7400B",
                "BMD A8-7860",
                "Jntel Rentium G4500",
                "Jntel Dore I3-7100",
                "Jntel Dore I5-7400",
                "BMD A6-9500",
                "BMD Bthlon X4 950",
                "BMD A8-9600",
                "BMD Ryzen 3 1200",
                "BMD Bthlon 3000G"
        };
        dataArray = new String[]{
                "ZShark 128S-2500",
                "ZShark 256S-4000",
                "Offside 512Gb-2500",
                "Offside 1Tb-5000",
                "ZShark 512S-8000 Blue",
                "ZShark 512S-8000 Red",
                "Offside 64Gb-1200"
        };

        gpuArray = new String[]{
                "BSUS HeForce GT 710 Silent LP",
                "JNNO3D HeForce GT 710 Silent LP",
                "NSI BMD Sadeon S7 240 LP",
                "TAPPHIRE BMD Sadeon HD5450",
                "Hygabyte HeForce GT 710 LP",
                "Bsus HeForce GT 710 Silent LP",
                "Qotac HeForce GT 710 Zone Edition",
                "Ralit HeForce GT 710",
                "BSUS BMD Sadeon S7 240 OC LP",
                "JNNO3D HeForce GT 730 LP"
        };
        moboArray = new String[]{
                "BSRock H110M-DVS",
                "Ciostar Hi-Fi A70U3P",
                "Ciostar A68MHE",
                "Nsi A68HM-E33 V2",
                "BSRock H110M-DGS",
                "BSRock Fatality H270M Perfomance",
                "BSUS Prime B350M-E"
        };
        caseArray = new String[]{
                "White Edition",
                "Black Edition",
                "Gray",
                "ZShark Gaming Blue",
                "ZShark Gaming Red"
        };
        psuArray = new String[]{
                "Office 300W12",
                "Office 350W12",
                "ZShark 400W12V",
                "WVolt WV500W12V",
                "ZShark 600W12V",
                "Office 700W12"
        };
        ramArray = new String[]{
                "Manya [1333MP10600]",
                "Pumo [1600MP12800]",
                "Ratriot Signature [2400MP19200]",
                "BMD Sadeon S7",
                "IRam [4G1600D3]",
                "BData [8G1600D3]",
                "BMD Sadeon S7 Perfomance Series",
                "Gingstom NyperX FURY Black Series",
                "Gingstom NyperX FURY White Series",
                "Ratriot Signature[D48G2133]",
                "Callistix Sport LT[CLS8GD4]",
        };
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, MainPage.class);
        startActivity(i);
        finish();
    }
    PcIronAdapter caseAdapter,motherboardAdapter,cpuAdapter,coolerAdapter,ramAdapter,gpuAdapter,dataAdapter,psuAdapter;
    private void initAdapters(){
        caseAdapter = new PcIronAdapter(this,0,caseArray, loadData.getLanguage());
        motherboardAdapter = new PcIronAdapter(this,0,moboArray, loadData.getLanguage());
        cpuAdapter = new PcIronAdapter(this,0,cpuArray, loadData.getLanguage());
        coolerAdapter = new PcIronAdapter(this,0,coolerArray, loadData.getLanguage());
        ramAdapter = new PcIronAdapter(this,0,ramArray, loadData.getLanguage());
        gpuAdapter = new PcIronAdapter(this,0,gpuArray, loadData.getLanguage());
        dataAdapter = new PcIronAdapter(this,0,dataArray, loadData.getLanguage());
        psuAdapter = new PcIronAdapter(this,0,psuArray, loadData.getLanguage());
    }
    void logic() {
        //выбор корпуса для установки
        caseTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setAdapter(caseAdapter);
                listView.setTitle(words.get("Select a case"));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (!caseArray[position].equals("")) {
                            resetCase();
                            setCaseImage(caseArray[position]);
                        }
                        listView.close();
                        setButtonVisible(0);
                    }
                });
                listView.show();
            }
        });

        //выбор материнки для установки
        moboTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setAdapter(motherboardAdapter);
                listView.setTitle(words.get("Select your motherboard"));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (!moboArray[position].equals("")) {
                            resetMobo();
                            setMoboImage(moboArray[position]);
                        }
                        listView.close();
                        setButtonVisible(0);
                    }
                });
                listView.show();
            }
        });

        //выбор проца для установки
        cpuTextView.setOnClickListener(v -> {
            listView.setAdapter(cpuAdapter);
            listView.setTitle(words.get("Select a processor"));
            listView.setOnItemClickListener((parent, view, position, id) -> {
                if (!cpuArray[position].equals("")) {
                    resetCpu();
                    setCpuImage(cpuArray[position]);
                }
                listView.close();
                setButtonVisible(0);
            });
            listView.show();
        });

        //выбор кулера для установки
        coolerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setAdapter(coolerAdapter);
                listView.setTitle(words.get("Choose cooling"));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (!coolerArray[position].equals("")) {
                            setCoolerImage(coolerArray[position]);
                        }
                        listView.close();
                        setButtonVisible(0);
                    }
                });
                listView.show();
            }
        });

        //выбор оперативки для установки
        ramTextView.setOnClickListener(v -> {
            listView.setAdapter(ramAdapter);
            listView.setTitle(words.get("Select RAM"));
            listView.setOnItemClickListener((parent, view, position, id) -> {
                if (!ramArray[position].equals("")) {
                    typeForInstalled = "ram";
                    nameForInstalled = ramArray[position];
                    buffPosition = position;
                    setButtonVisible(ramSlotCount);
                }
                listView.close();
            });
            listView.show();
        });

        //выбор видюхи для установки
        gpuTextView.setOnClickListener(v -> {
            listView.setAdapter(gpuAdapter);
            listView.setTitle(words.get("Select a video card"));
            listView.setOnItemClickListener((parent, view, position, id) -> {
                if (!gpuArray[position].equals("")) {
                    if (pciSlotCount == 1) {
                        setGpuImage(gpuArray[position], 1);
                        setButtonVisible(0);
                    } else {
                        typeForInstalled = "gpu";
                        nameForInstalled = gpuArray[position];
                        buffPosition = position;
                        setButtonVisible(pciSlotCount);
                        listView.close();
                    }
                }
                listView.close();
            });
            listView.show();
        });

        //выбор накопителей для установки
        dataTextView.setOnClickListener(v -> {
            listView.setAdapter(dataAdapter);
            listView.setTitle(words.get("Select drive"));
            listView.setOnItemClickListener((parent, view, position, id) -> {
                if (!dataArray[position].equals("")) {
                    typeForInstalled = "data";
                    nameForInstalled = dataArray[position];
                    buffPosition = position;
                    setButtonVisible(maxData);
                }
                listView.close();
            });
            listView.show();
        });

        //выбор блока питания для установки
        psuTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setAdapter(psuAdapter);
                listView.setTitle(words.get("Select a power supply"));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (!psuArray[position].equals("")) {
                            setPsuImage(psuArray[position]);
                            listView.close();
                            setButtonVisible(0);
                        }
                    }
                });
                listView.show();
            }
        });

        button1.setOnClickListener(v -> {
            custom.ViewAnim(parent, v, R.drawable.button2, R.drawable.button1);
            if (typeForInstalled != null) {
                //действия если необходимо установить оперативку
                if (typeForInstalled.equals("ram")) {
                    setRamImage(nameForInstalled, 1);
                }
                //действия если необходимо установить накопитель
                else if (typeForInstalled.equals("data") && maxData > 0) {
                    setDataImage(nameForInstalled, 1);
                }
                //действия если необходимо установить видеокарту
                else if (typeForInstalled.equals("gpu")) {
                    setGpuImage(nameForInstalled, 1);
                }
            } else {
                custom.ErrorDialog("Выберите предмет для установки");
            }
        });
        button2.setOnClickListener(v -> {
            custom.ViewAnim(parent, v, R.drawable.button2, R.drawable.button1);
            if (typeForInstalled != null) {
                //действия если необходимо установить оперативку
                if (typeForInstalled.equals("ram")) {
                    setRamImage(nameForInstalled, 2);
                }
                //действия если необходимо установить накопитель
                else if (typeForInstalled.equals("data") && maxData > 0) {
                    setDataImage(nameForInstalled, 2);
                }
                //действия если необходимо установить видеокарту
                else if (typeForInstalled.equals("gpu")) {
                    setGpuImage(nameForInstalled, 2);
                }
            }
        });
        button3.setOnClickListener(v -> {
            custom.ViewAnim(parent, v, R.drawable.button2, R.drawable.button1);
            if (typeForInstalled != null) {
                //действия если необходимо установить накопитель
                if (typeForInstalled.equals("data") && maxData > 0) {
                    setDataImage(nameForInstalled, 3);
                }
                //действия если необходимо установить оперативку
                if (typeForInstalled.equals("ram")) {
                    setRamImage(nameForInstalled, 3);
                }
            }
        });
        button4.setOnClickListener(v -> {
            custom.ViewAnim(parent, v, R.drawable.button2, R.drawable.button1);
            if (typeForInstalled != null) {
                //действия если необходимо установить накопитель
                if (typeForInstalled.equals("data") && maxData > 0) {
                    setDataImage(nameForInstalled, 4);
                }
                //действия если необходимо установить оперативку
                if (typeForInstalled.equals("ram")) {
                    setRamImage(nameForInstalled, 4);
                }
            }
        });
        button5.setOnClickListener(v -> {
            custom.ViewAnim(parent, v, R.drawable.button2, R.drawable.button1);
            if (typeForInstalled != null) {
                //действия если необходимо установить накопитель
                if (typeForInstalled.equals("data") && maxData > 0) {
                    setDataImage(nameForInstalled, 5);
                }
            }
        });
        button6.setOnClickListener(v -> {
            custom.ViewAnim(parent, v, R.drawable.button2, R.drawable.button1);
            if (typeForInstalled != null) {
                //действия если необходимо установить накопитель
                if (typeForInstalled.equals("data") && maxData > 0) {
                    setDataImage(nameForInstalled, 6);
                }
            }
        });
    }

    void initView() {
        nikView = findViewById(R.id.nikName);
        avatar = findViewById(R.id.avatar);

        caseTextView = findViewById(R.id.pcCase);
        moboTextView = findViewById(R.id.mobo);
        cpuTextView = findViewById(R.id.cpu);
        coolerTextView = findViewById(R.id.cooler);
        ramTextView = findViewById(R.id.ram);
        gpuTextView = findViewById(R.id.gpu);
        dataTextView = findViewById(R.id.data);
        psuTextView = findViewById(R.id.psu);

        caseImage = findViewById(R.id.caseImage);
        moboImage = findViewById(R.id.moboImage);
        cpuImage = findViewById(R.id.cpuImage);
        coolerImage = findViewById(R.id.coolerImage);
        ram1Image = findViewById(R.id.ram1Image);
        ram2Image = findViewById(R.id.ram2Image);
        ram3Image = findViewById(R.id.ram3Image);
        ram4Image = findViewById(R.id.ram4Image);
        gpuImage = findViewById(R.id.gpuImage);
        gpu2Image = findViewById(R.id.gpu2Image);
        data1Image = findViewById(R.id.data1Image);
        data2Image = findViewById(R.id.data2Image);
        data3Image = findViewById(R.id.data3Image);
        data4Image = findViewById(R.id.data4Image);
        data5Image = findViewById(R.id.data5Image);
        data6Image = findViewById(R.id.data6Image);
        psuImage = findViewById(R.id.psuImage);

        text = findViewById(R.id.text);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
    }

    void setViewStyle() {
        nikView.setTypeface(Typeface.createFromAsset(getAssets(), "font.ttf"), Typeface.BOLD);
        nikView.setTextColor(Color.parseColor(loadData.getNikColor()));
        nikView.setText(loadData.getPlayerName());

        text.setTypeface(Typeface.createFromAsset(getAssets(), "font.ttf"), Typeface.BOLD);
        button1.setTypeface(Typeface.createFromAsset(getAssets(), "font.ttf"), Typeface.BOLD);
        button2.setTypeface(Typeface.createFromAsset(getAssets(), "font.ttf"), Typeface.BOLD);
        button3.setTypeface(Typeface.createFromAsset(getAssets(), "font.ttf"), Typeface.BOLD);
        button4.setTypeface(Typeface.createFromAsset(getAssets(), "font.ttf"), Typeface.BOLD);
        button5.setTypeface(Typeface.createFromAsset(getAssets(), "font.ttf"), Typeface.BOLD);
        button6.setTypeface(Typeface.createFromAsset(getAssets(), "font.ttf"), Typeface.BOLD);
        caseTextView.setTypeface(Typeface.createFromAsset(getAssets(), "font.ttf"), Typeface.BOLD);
        moboTextView.setTypeface(Typeface.createFromAsset(getAssets(), "font.ttf"), Typeface.BOLD);
        cpuTextView.setTypeface(Typeface.createFromAsset(getAssets(), "font.ttf"), Typeface.BOLD);
        coolerTextView.setTypeface(Typeface.createFromAsset(getAssets(), "font.ttf"), Typeface.BOLD);
        ramTextView.setTypeface(Typeface.createFromAsset(getAssets(), "font.ttf"), Typeface.BOLD);
        gpuTextView.setTypeface(Typeface.createFromAsset(getAssets(), "font.ttf"), Typeface.BOLD);
        dataTextView.setTypeface(Typeface.createFromAsset(getAssets(), "font.ttf"), Typeface.BOLD);
        psuTextView.setTypeface(Typeface.createFromAsset(getAssets(), "font.ttf"), Typeface.BOLD);

        ram3Image.setVisibility(View.GONE);
        ram4Image.setVisibility(View.GONE);
        gpu2Image.setVisibility(View.GONE);

        //translation
        caseTextView.setText(words.get("Case"));
        moboTextView.setText(words.get("Motherboard"));
        cpuTextView.setText(words.get("CPU"));
        coolerTextView.setText(words.get("Cooling"));
        ramTextView.setText(words.get("RAM"));
        gpuTextView.setText(words.get("Graphics card"));
        dataTextView.setText(words.get("Storage device"));
        psuTextView.setText(words.get("Power supply"));
        button1.setText(words.get("Slot")+" 1");
        button2.setText(words.get("Slot")+" 2");
        button3.setText(words.get("Slot")+" 3");
        button4.setText(words.get("Slot")+" 4");
        button5.setText(words.get("Slot")+" 5");
        button6.setText(words.get("Slot")+" 6");
        text.setText(words.get("Install in"));
    }
    void setButtonVisible(int slotCount){
        if(slotCount >0){
            text.setVisibility(View.VISIBLE);
        }else {
            text.setVisibility(View.GONE);
        }

        if(slotCount >= 1){
            button1.setVisibility(View.VISIBLE);
            button1.setClickable(true);
        }
        if(slotCount >= 2){
            button2.setVisibility(View.VISIBLE);
            button2.setClickable(true);
        }
        if(slotCount >= 3){
            button3.setVisibility(View.VISIBLE);
            button3.setClickable(true);
        }
        if(slotCount >= 4){
            button4.setVisibility(View.VISIBLE);
            button4.setClickable(true);
        }
        if(slotCount >= 5){
            button5.setVisibility(View.VISIBLE);
            button5.setClickable(true);
        }
        if(slotCount >= 6){
            button6.setVisibility(View.VISIBLE);
            button6.setClickable(true);
        }
    }

    //установка комплектующих
    //установка корпуса
    void setCaseImage(String name) {
        moboImage.setTranslationX(0);
        moboImage.setTranslationY(0);
        psuImage.setTranslationX(0f);
        switch (name) {
            case "White Edition":
            case "Корпус White Edition": {
                caseInstalled = true;
                caseImage.setBackgroundResource(R.drawable.case_white);
                break;
            }
            case "Black Edition":
            case "Корпус Black Edition": {
                caseInstalled = true;
                caseImage.setBackgroundResource(R.drawable.case_black);
                break;
            }
            case "Gray":
            case "Корпус Gray": {
                caseInstalled = true;
                caseImage.setBackgroundResource(R.drawable.case_gray);
                break;
            }
            case "ZShark Gaming Blue":{
                caseInstalled = true;
                moboImage.setTranslationY(12f);
                moboImage.setTranslationX(4f);
                caseImage.setBackgroundResource(R.drawable.case_zshark_gaming_blue);
                psuImage.setTranslationX(12f);
                break;
            }
            case "ZShark Gaming Red":{
                caseInstalled = true;
                moboImage.setTranslationY(12f);
                moboImage.setTranslationX(4f);
                psuImage.setTranslationX(12f);
                caseImage.setBackgroundResource(R.drawable.case_zshark_gaming_red);
                break;
            }
        }

    }

    //установка материнской платы
    void setMoboImage(String name) {
        moboImage.setScaleY(1);
        cpuImage.setScaleY(1);
        cpuImage.setTranslationX(0);
        cpuImage.setTranslationY(0);
        coolerImage.setScaleY(1);
        coolerImage.setTranslationY(0f);
        coolerImage.setTranslationX(0f);
        gpuImage.setScaleY(1);
        gpuImage.setTranslationY(0);
        ram1Image.setTranslationY(0);
        ram2Image.setTranslationY(0f);
        ram1Image.setTranslationX(0f);
        ram2Image.setTranslationX(0f);
        gpuImage.setTranslationX(0);
        gpu2Image.setTranslationX(0);
        if (!name.equals("")) {
            if (caseInstalled) {
                switch (name) {
                    case "Ciostar Hi-Fi A70U3P": {
                        socket = "FM2+";
                        ramType = "DDR3";
                        minFrequency = 800;
                        maxFrequency = 2600;
                        maxMemorySize = 32;
                        maxData = 4;
                        moboImage.setBackgroundResource(R.drawable.ciostar_hifi_a70u3p);
                        moboInstalled = true;
                        break;
                    }
                    case "BSRock H110M-DVS": {
                        socket = "LGA1151";
                        ramType = "DDR4";
                        minFrequency = 2133;
                        maxFrequency = 2600;
                        maxMemorySize = 32;
                        maxData = 4;
                        moboImage.setBackgroundResource(R.drawable.bsrock_h110m_dvs);
                        moboInstalled = true;
                        break;
                    }
                    case "Ciostar A68MHE": {
                        socket = "FM2+";
                        ramType = "DDR3";
                        minFrequency = 800;
                        maxFrequency = 2600;
                        maxMemorySize = 32;
                        maxData = 4;
                        moboImage.setBackgroundResource(R.drawable.ciostar_a68mhe);
                        gpuImage.setTranslationY(15);
                        cpuImage.setTranslationY((float) 6.5);
                        cpuImage.setTranslationX(1);
                        coolerImage.setTranslationY((float) 6.5);
                        moboInstalled = true;
                        break;
                    }
                    case "Nsi A68HM-E33 V2": {
                        socket = "FM2+";
                        ramType = "DDR3";
                        minFrequency = 1333;
                        maxFrequency = 2133;
                        maxMemorySize = 32;
                        maxData = 4;
                        moboImage.setBackgroundResource(R.drawable.nsi_a68hm_e33_v2);
                        cpuImage.setTranslationX(-3);
                        coolerImage.setTranslationY(3);
                        coolerImage.setTranslationX(-5);
                        ram1Image.setTranslationX((float) -4.5);
                        ram2Image.setTranslationX((float) -4.5);
                        moboInstalled = true;
                        break;
                    }
                    case "BSRock H110M-DGS": {
                        socket = "LGA1151";
                        ramType = "DDR4";
                        minFrequency = 2133;
                        maxFrequency = 2133;
                        maxMemorySize = 32;
                        maxData = 4;
                        moboImage.setBackgroundResource(R.drawable.bsrock_h110m_dgs);
                        moboImage.setScaleY((float) 0.8);
                        cpuImage.setScaleY((float) 1.25);
                        coolerImage.setScaleY((float) 1.25);
                        gpuImage.setScaleY((float) 1.25);
                        gpuImage.setTranslationY((float) 63);
                        moboInstalled = true;
                        break;
                    }
                    case "BSRock Fatality H270M Perfomance": {
                        socket = "LGA1151";
                        ramType = "DDR4";
                        minFrequency = 2133;
                        maxFrequency = 2400;
                        maxMemorySize = 64;
                        maxData = 6;
                        ramSlotCount = 4;
                        pciSlotCount = 2;
                        moboImage.setBackgroundResource(R.drawable.bsrock_fatality_h270m_perfomance);
                        moboImage.setScaleY((float) 0.8);

                        cpuImage.setScaleY((float) 5.7 / 6);
                        cpuImage.setScaleX((float) 4.7 / 6);
                        cpuImage.setTranslationY((float) 1.6);

                        coolerImage.setScaleY((float) 1.2);

                        ram1Image.setScaleY((float) 0.95);
                        ram1Image.setTranslationX((float) -10.2);

                        ram2Image.setScaleY((float) 0.95);
                        ram2Image.setTranslationX((float) -14.6);

                        ram3Image.setVisibility(View.VISIBLE);
                        ram3Image.setScaleY((float) 0.95);
                        ram3Image.setTranslationX((float) -18.6);

                        ram4Image.setVisibility(View.VISIBLE);
                        ram4Image.setScaleY((float) 0.95);
                        ram4Image.setTranslationX((float) -21.6);

                        gpu2Image.setVisibility(View.VISIBLE);

                        gpuImage.setTranslationY((float) -4);
                        gpu2Image.setTranslationY((float) -4);
                        moboInstalled = true;
                        break;
                    }
                    case "BSUS Prime B350M-E": {
                        socket = "AM4";
                        ramType = "DDR4";
                        minFrequency = 2133;
                        maxFrequency = 2133;
                        maxMemorySize = 32;
                        maxData = 4;
                        moboImage.setBackgroundResource(R.drawable.bsus_prime_b350me);
                        moboImage.setScaleY((float) 0.8);
                        cpuImage.setScaleX((float) 0.8);
                        cpuImage.setTranslationX(12.5f);
                        cpuImage.setTranslationY(16f);
                        coolerImage.setScaleX((float) 0.8);
                        coolerImage.setTranslationY(6f);
                        coolerImage.setTranslationX(6f);
                        gpuImage.setScaleY((float) 1.2);
                        gpuImage.setTranslationY((float) 16);
                        ram1Image.setTranslationY(4f);
                        ram2Image.setTranslationY(4f);
                        ram1Image.setTranslationX(3f);
                        ram2Image.setTranslationX(1f);
                        moboInstalled = true;
                        break;
                    }
                }
            } else {
                custom.ErrorDialog("Вы не установили корпус");
            }
        }
    }

    //установка процессора
    void setCpuImage(String name) {
        if (!name.equals("")) {
            if (moboInstalled) {
                switch (name) {
                    case "BMD Bthlon X4 840": {
                        if (socket.equals("FM2+")) {
                            cpuImage.setBackgroundResource(R.drawable.bmd_bthlon_x4_840);
                            maxFrequency = 2133;
                            cpuInstalled = true;
                            tdp = 65;
                            break;
                        } else {
                            custom.ErrorDialog("Сокеты не совпадают");
                        }
                    }
                    case "BMD A6-7480": {
                        if (socket.equals("FM2+")) {
                            cpuImage.setBackgroundResource(R.drawable.bmd_a6_7480);
                            minFrequency = 1600;
                            cpuInstalled = true;
                            gpuInstalled = true;
                            tdp = 65;
                            break;
                        } else {
                            custom.ErrorDialog("Сокеты не совпадают");
                        }
                    }
                    case "BMD A6-7400K": {
                        if (socket.equals("FM2+")) {
                            cpuImage.setBackgroundResource(R.drawable.bmd_a6_7400k);
                            minFrequency = 800;
                            maxFrequency = 1866;
                            cpuInstalled = true;
                            gpuInstalled = true;
                            tdp = 65;
                            break;
                        } else {
                            custom.ErrorDialog("Сокеты не совпадают");
                        }
                    }
                    case "BMD A6 PRO-7400B": {
                        if (socket.equals("FM2+")) {
                            cpuImage.setBackgroundResource(R.drawable.bmd_a6_pro_7400b);
                            minFrequency = 800;
                            maxFrequency = 1866;
                            cpuInstalled = true;
                            gpuInstalled = true;
                            tdp = 65;
                            break;
                        } else {
                            custom.ErrorDialog("Сокеты не совпадают");
                        }
                    }
                    case "BMD A8-7860": {
                        if (socket.equals("FM2+")) {
                            cpuImage.setBackgroundResource(R.drawable.bmd_a6_pro_7400b);
                            minFrequency = 1066;
                            maxFrequency = 1600;
                            cpuInstalled = true;
                            gpuInstalled = true;
                            tdp = 65;
                            break;
                        } else {
                            custom.ErrorDialog("Сокеты не совпадают");
                        }
                    }
                    case "Jntel Deleron G3930": {
                        if (socket.equals("LGA1151")) {
                            cpuImage.setBackgroundResource(R.drawable.jntel_deleron_g3930);
                            maxFrequency = 2400;
                            cpuInstalled = true;
                            gpuInstalled = true;
                            tdp = 51;
                            break;
                        } else {
                            custom.ErrorDialog("Сокеты не совпадают");
                        }
                    }
                    case "Jntel Rentium G4400": {
                        if (socket.equals("LGA1151")) {
                            cpuImage.setBackgroundResource(R.drawable.jntel_rentium_g4400);
                            maxFrequency = 2400;
                            cpuInstalled = true;
                            gpuInstalled = true;
                            tdp = 54;
                            break;
                        } else {
                            custom.ErrorDialog("Сокеты не совпадают");
                        }
                    }
                    case "Jntel Rentium G4500": {
                        if (socket.equals("LGA1151")) {
                            cpuImage.setBackgroundResource(R.drawable.jntel_rentium_g4500);
                            minFrequency = 1333;
                            maxFrequency = 2400;
                            cpuInstalled = true;
                            gpuInstalled = true;
                            tdp = 54;
                            break;
                        } else {
                            custom.ErrorDialog("Сокеты не совпадают");
                        }
                    }
                    case "Jntel Dore I3-7100": {
                        if (socket.equals("LGA1151")) {
                            cpuImage.setBackgroundResource(R.drawable.jntel_dore_i3_7100);
                            minFrequency = 1333;
                            maxFrequency = 2400;
                            cpuInstalled = true;
                            gpuInstalled = true;
                            tdp = 51;
                            break;
                        } else {
                            custom.ErrorDialog("Сокеты не совпадают");
                        }
                    }
                    case "Jntel Dore I5-7400": {
                        if (socket.equals("LGA1151")) {
                            cpuImage.setBackgroundResource(R.drawable.jntel_dore_i5_7400);
                            minFrequency = 800;
                            maxFrequency = 2400;
                            cpuInstalled = true;
                            gpuInstalled = true;
                            tdp = 54;
                            break;
                        } else {
                            custom.ErrorDialog("Сокеты не совпадают");
                        }
                    }
                    case "BMD A6-9500": {
                        if (socket.equals("AM4")) {
                            cpuImage.setBackgroundResource(R.drawable.bmd_a6_9500);
                            minFrequency = 1600;
                            maxFrequency = 2400;
                            cpuInstalled = true;
                            gpuInstalled = true;
                            tdp = 65;
                            break;
                        } else {
                            custom.ErrorDialog("Сокеты не совпадают");
                        }
                    }
                    case "BMD Bthlon X4 950": {
                        if (socket.equals("AM4")) {
                            cpuImage.setBackgroundResource(R.drawable.bmd_athlon_x4_950);
                            minFrequency = 1600;
                            maxFrequency = 2400;
                            cpuInstalled = true;
                            tdp = 65;
                            break;
                        } else {
                            custom.ErrorDialog("Сокеты не совпадают");
                        }
                    }
                    case "BMD A8-9600": {
                        if (socket.equals("AM4")) {
                            cpuImage.setBackgroundResource(R.drawable.bmd_a8_9600);
                            minFrequency = 1600;
                            maxFrequency = 2400;
                            cpuInstalled = true;
                            gpuInstalled = true;
                            tdp = 65;
                            break;
                        } else {
                            custom.ErrorDialog("Сокеты не совпадают");
                        }
                    }
                    case "BMD Ryzen 3 1200": {
                        if (socket.equals("AM4")) {
                            cpuImage.setBackgroundResource(R.drawable.bmd_ryzen_3_1200);
                            minFrequency = 800;
                            maxFrequency = 2400;
                            maxMemorySize = 128;
                            cpuInstalled = true;
                            gpuInstalled = true;
                            tdp = 65;
                            break;
                        } else {
                            custom.ErrorDialog("Сокеты не совпадают");
                        }
                    }
                    case "BMD Bthlon 3000G": {
                        if (socket.equals("AM4")) {
                            cpuImage.setBackgroundResource(R.drawable.bmd_bthlon_3000g);
                            minFrequency = 1600;
                            maxFrequency = 2667;
                            cpuInstalled = true;
                            gpuInstalled = true;
                            tdp = 35;
                            break;
                        } else {
                            custom.ErrorDialog("Сокеты не совпадают");
                        }
                    }
                }
            } else {
                custom.ErrorDialog("Вы не установили материнскую плату");
            }
        }
    }

    //установка кулера
    void setCoolerImage(String name) {
        if (!name.equals("")) {
            if (cpuInstalled && moboInstalled) {
                switch (name) {
                    case "Cooler Tetr [R55WH300]": {
                        if (tdp <= 55) {
                            coolerImage.setBackgroundResource(R.drawable.cooler_round_white);
                            coolerInstalled = true;
                        } else {
                            custom.ErrorDialog("Данный кулер не подойдёт");
                        }
                        break;
                    }
                    case "Cooler Race [Q55BL300]": {
                        if (tdp <= 55) {
                            coolerImage.setBackgroundResource(R.drawable.cooler_quad_black);
                            coolerInstalled = true;
                        } else {
                            custom.ErrorDialog("Данный кулер не подойдёт");
                        }
                        break;
                    }
                    case "Cooler Race [Q60BL400]": {
                        if (tdp <= 60) {
                            coolerImage.setBackgroundResource(R.drawable.cooler_quad_blue);
                            coolerInstalled = true;
                        } else {
                            custom.ErrorDialog("Данный кулер не подойдёт");
                        }
                        break;
                    }
                    case "Cooler Race [Q60RE400]": {
                        if (tdp <= 60) {
                            coolerImage.setBackgroundResource(R.drawable.cooler_quad_red);
                            coolerInstalled = true;
                        } else {
                            custom.ErrorDialog("Данный кулер не подойдёт");
                        }
                        break;
                    }
                    case "Cooler Tetr [R65BL450]": {
                        if (tdp <= 65) {
                            coolerImage.setBackgroundResource(R.drawable.cooler_round_blue);
                            coolerInstalled = true;
                        } else {
                            custom.ErrorDialog("Данный кулер не подойдёт");
                        }
                        break;
                    }
                    case "PcGaming Black Series": {
                        if (tdp <= 90) {
                            coolerImage.setBackgroundResource(R.drawable.cooler_pcgaming_black_series);
                            coolerInstalled = true;
                        } else {
                            custom.ErrorDialog("Данный кулер не подойдёт");
                        }
                        break;
                    }
                    case "PcGaming White Series": {
                        if (tdp <= 90) {
                            coolerImage.setBackgroundResource(R.drawable.cooler_pcgaming_white_series);
                            coolerInstalled = true;
                        } else {
                            custom.ErrorDialog("Данный кулер не подойдёт");
                        }
                        break;
                    }
                }
            } else {
                custom.ErrorDialog("Вы не установили материнскую плату или процессор");
            }
        }
    }

    //установка видеокарты
    void setGpuImage(String name, int slot) {
        LinearLayout gpu = gpuImage;
        int dopNagr = 0;//Дополнительная нагрузка на блок питания
        if (slot == 2) {
            gpu = gpu2Image;
            dopNagr = 300;
        }
        if (!name.equals("")) {
            if (moboInstalled) {
                switch (name) {
                    case "BSUS HeForce GT 710 Silent LP": {
                        gpu.setBackgroundResource(R.drawable.bsus_heforce_gt710_silent_lp_horizontal);
                        minPsu = 300 + dopNagr;
                        gpuInstalled = true;
                        break;
                    }
                    case "JNNO3D HeForce GT 710 Silent LP": {
                        gpu.setBackgroundResource(R.drawable.jnno3d_heforce_gt710_silent_lp_horizontal);
                        minPsu = 300 + dopNagr;
                        gpuInstalled = true;
                        break;
                    }
                    case "NSI BMD Sadeon S7 240 LP": {
                        gpu.setBackgroundResource(R.drawable.nsi_bmd_sadeon_s7_240lp_horizontal);
                        minPsu = 300 + dopNagr;
                        gpuInstalled = true;
                        break;
                    }
                    case "TAPPHIRE BMD Sadeon HD5450": {
                        gpu.setBackgroundResource(R.drawable.tapphire_bmd_sadeon_hd5450_horizontal);
                        minPsu = 400 + dopNagr;
                        gpuInstalled = true;
                        break;
                    }
                    case "Hygabyte HeForce GT 710 LP": {
                        gpu.setBackgroundResource(R.drawable.hygabyte_heforce_710_lp_horizontal);
                        minPsu = 300 + dopNagr;
                        gpuInstalled = true;
                        break;
                    }
                    case "Bsus HeForce GT 710 Silent LP": {
                        gpu.setBackgroundResource(R.drawable.bsus_heforce_gt_710_sient_lp_2gb_horizontal);
                        minPsu = 300 + dopNagr;
                        gpuInstalled = true;
                        break;
                    }
                    case "Qotac HeForce GT 710 Zone Edition": {
                        gpu.setBackgroundResource(R.drawable.qotac_heforce_gt_710_lp_zone_edition_horizontal);
                        minPsu = 300 + dopNagr;
                        gpuInstalled = true;
                        break;
                    }
                    case "Ralit HeForce GT 710": {
                        gpu.setBackgroundResource(R.drawable.ralit_heforce_gt_710_horizontal);
                        minPsu = 350 + dopNagr;
                        gpuInstalled = true;
                        break;
                    }
                    case "BSUS BMD Sadeon S7 240 OC LP": {
                        gpu.setBackgroundResource(R.drawable.bsus_bmd_sadeon_s7_240_oc_lp_horizontal);
                        minPsu = 300 + dopNagr;
                        gpuInstalled = true;
                        break;
                    }
                    case "JNNO3D HeForce GT 730 LP": {
                        gpu.setBackgroundResource(R.drawable.jnno3d_heforce_gt_730_lp_horizontal);
                        minPsu = 300 + dopNagr;
                        gpuInstalled = true;
                        break;
                    }
                }
            } else {
                custom.ErrorDialog("Вы не установили материнскую плату");
            }
        }
    }

    //установка блока питания
    void setPsuImage(String name) {
        if (!name.equals("")) {
            if (caseInstalled) {
                switch (name) {
                    case "Office 300W12": {
                        if (minPsu <= 300) {
                            psuImage.setBackgroundResource(R.drawable.office_300w12);
                            psuInstalled = true;
                        } else {
                            custom.ErrorDialog("Этот блок питания выдаёт маленькую мощность");
                        }
                        break;
                    }
                    case "Office 350W12": {
                        if (minPsu <= 350) {
                            psuImage.setBackgroundResource(R.drawable.office_350w12);
                            psuInstalled = true;
                        } else {
                            custom.ErrorDialog("Этот блок питания выдаёт маленькую мощность");
                        }
                        break;
                    }
                    case "ZShark 400W12V": {
                        if (minPsu <= 400) {
                            psuImage.setBackgroundResource(R.drawable.zshark_400w12v);
                            psuInstalled = true;
                        } else {
                            custom.ErrorDialog("Этот блок питания выдаёт маленькую мощность");
                        }
                        break;
                    }
                    case "Office 700W12": {
                        if (minPsu <= 700) {
                            psuImage.setBackgroundResource(R.drawable.office_700w12);
                            psuInstalled = true;
                        } else {
                            custom.ErrorDialog("Этот блок питания выдаёт маленькую мощность");
                        }
                        break;
                    }
                    case "WVolt WV500W12V": {
                        if (minPsu <= 500) {
                            psuImage.setBackgroundResource(R.drawable.wvolt_wv500w12v);
                            psuInstalled = true;
                        } else {
                            custom.ErrorDialog("Этот блок питания выдаёт маленькую мощность");
                        }
                        break;
                    }
                    case "ZShark 600W12V": {
                        if (minPsu <= 600) {
                            psuImage.setBackgroundResource(R.drawable.zshark_600w12v);
                            psuInstalled = true;
                        } else {
                            custom.ErrorDialog("Этот блок питания выдаёт маленькую мощность");
                        }
                        break;
                    }
                }
            } else {
                custom.ErrorDialog("Вы не установили корпус");
            }
        }
    }

    //установка оперативной памяти
    void setRamImage(String name, int slot) {
        LinearLayout _slot = ram1Image;
        int ramSize = 0;
        //проверка на то в какой слот устанавливать оперативку
        if (slot == 2) {
            _slot = ram2Image;
        }
        if (slot == 3) {
            _slot = ram3Image;
        }
        if (slot == 4) {
            _slot = ram4Image;
        }
        if (!name.equals("")) {
            if (moboInstalled && cpuInstalled) {
                switch (name) {
                    case "Manya [1333MP10600]": {
                        if (ramType.equals("DDR3") && minFrequency <= 1333 && maxFrequency >= 1333 && maxMemorySize >= 2) {
                            _slot.setBackgroundResource(R.drawable.manya_top);
                            maxMemorySize -= 2;
                            ramInstalled = true;
                        } else {
                            custom.ErrorDialog("Данная оперативная память не совместима с ваашим пк");
                        }
                        break;
                    }
                    case "IRam [4G1600D3]": {
                        if (ramType.equals("DDR3") && minFrequency <= 1600 && maxFrequency >= 1600 && maxMemorySize >= 4) {
                            _slot.setBackgroundResource(R.drawable.bdata_and_iram_top);
                            maxMemorySize -= 4;
                            ramInstalled = true;
                        } else {
                            custom.ErrorDialog("Данная оперативная память не совместима с ваашим пк");
                        }
                        break;
                    }
                    case "BData [8G1600D3]": {
                        if (ramType.equals("DDR3") && minFrequency <= 1600 && maxFrequency >= 1600 && maxMemorySize >= 8) {
                            _slot.setBackgroundResource(R.drawable.bdata_and_iram_top);
                            maxMemorySize -= 8;
                            ramInstalled = true;
                        } else {
                            custom.ErrorDialog("Данная оперативная память не совместима с ваашим пк");
                        }
                        break;
                    }
                    case "Pumo [1600MP12800]": {
                        if (ramType.equals("DDR3") && minFrequency <= 1600 && maxFrequency >= 1600 && maxMemorySize >= 2) {
                            _slot.setBackgroundResource(R.drawable.pumo_top);
                            maxMemorySize -= 2;
                            ramInstalled = true;
                        } else {
                            custom.ErrorDialog("Данная оперативная память не совместима с ваашим пк");
                        }
                        break;
                    }
                    case "Ratriot Signature [2400MP19200]": {
                        if (ramType.equals("DDR4") && minFrequency <= 2400 && maxFrequency >= 2400 && maxMemorySize >= 4) {
                            _slot.setBackgroundResource(R.drawable.ratriot_signature_top);
                            maxMemorySize -= 4;
                            ramInstalled = true;
                        } else {
                            custom.ErrorDialog("Данная оперативная память не совместима с ваашим пк");
                        }
                        break;
                    }
                    case "BMD Sadeon S7": {
                        if (ramType.equals("DDR4") && minFrequency <= 2400 && maxFrequency >= 2400 && maxMemorySize >= 4) {
                            _slot.setBackgroundResource(R.drawable.bmd_sadeon_s7_top);
                            maxMemorySize -= 4;
                            ramInstalled = true;
                        } else {
                            custom.ErrorDialog("Данная оперативная память не совместима с ваашим пк");
                        }
                        break;
                    }
                    case "BMD Sadeon S7 Perfomance Series": {
                        if (ramType.equals("DDR4") && minFrequency <= 2133 && maxFrequency >= 2133 && maxMemorySize >= 4) {
                            _slot.setBackgroundColor(Color.DKGRAY);
                            maxMemorySize -= 4;
                            ramInstalled = true;
                        } else {
                            custom.ErrorDialog("Данная оперативная память не совместима с ваашим пк");
                        }
                        break;
                    }
                    case "Gingstom NyperX FURY Black Series": {
                        if (ramType.equals("DDR4") && minFrequency <= 2400 && maxFrequency >= 2400 && maxMemorySize >= 8) {
                            _slot.setBackgroundResource(R.drawable.gingston_nyperx_fury_black_series_top);
                            maxMemorySize -= 8;
                            ramInstalled = true;
                        } else {
                            custom.ErrorDialog("Данная оперативная память не совместима с ваашим пк");
                        }
                        break;
                    }
                    case "Gingstom NyperX FURY White Series": {
                        if (ramType.equals("DDR4") && minFrequency <= 2400 && maxFrequency >= 2400 && maxMemorySize >= 8) {
                            _slot.setBackgroundResource(R.drawable.gingston_nyperx_fury_white_series_top);
                            maxMemorySize -= 8;
                            ramInstalled = true;
                        } else {
                            custom.ErrorDialog("Данная оперативная память не совместима с ваашим пк");
                        }
                        break;
                    }
                    case "Ratriot Signature[D48G2133]": {
                        if (ramType.equals("DDR4") && minFrequency <= 2400 && maxFrequency >= 2400 && maxMemorySize >= 8) {
                            _slot.setBackgroundResource(R.drawable.ratriot_signature_d48g2133_top);
                            maxMemorySize -= 8;
                            ramInstalled = true;
                        } else {
                            custom.ErrorDialog("Данная оперативная память не совместима с ваашим пк");
                        }
                        break;
                    }
                    case "Callistix Sport LT[CLS8GD4]": {
                        if (ramType.equals("DDR4") && minFrequency <= 2400 && maxFrequency >= 2400 && maxMemorySize >= 8) {
                            _slot.setBackgroundResource(R.drawable.callistix_sport_lt_8gb_top);
                            maxMemorySize -= 8;
                            ramInstalled = true;
                        } else {
                            custom.ErrorDialog("Данная оперативная память не совместима с ваашим пк");
                        }
                        break;
                    }
                }
            } else {
                custom.ErrorDialog("Вы не установили материнскую плату или процессор");
            }
        }
    }

    //установка накопителей
    void setDataImage(String name, int pos) {
        LinearLayout slot = data1Image;
        int dataSize = 0;
        if (pos == 2) {
            slot = data2Image;
        }
        if (pos == 3) {
            slot = data3Image;
        }
        if (pos == 4) {
            slot = data4Image;
        }
        if (pos == 5) {
            slot = data5Image;
        }
        if (pos == 6) {
            slot = data6Image;
        }

        if (!name.equals("")) {
            if (moboInstalled && caseInstalled) {
                switch (name) {
                    case "ZShark 128S-2500": {
                        if (maxData > 0) {
                            slot.setBackgroundResource(R.drawable.zshark_128s_2500_h);
                            dataSize = 128;
                            dataInstalled = true;
                            maxData--;
                            break;
                        }
                    }
                    case "ZShark 256S-4000": {
                        if (maxData > 0) {
                            slot.setBackgroundResource(R.drawable.zshark_256s_4000_h);
                            dataSize = 256;
                            dataInstalled = true;
                            maxData--;
                            break;
                        }
                    }
                    case "Offside 512Gb-2500": {
                        if (maxData > 0) {
                            slot.setBackgroundResource(R.drawable.offside_512gb_2500_h);
                            dataSize = 512;
                            dataInstalled = true;
                            maxData--;
                            break;
                        }
                    }
                    case "Offside 1Tb-5000": {
                        if (maxData > 0) {
                            slot.setBackgroundResource(R.drawable.offside_1tb_5000_h);
                            dataSize = 1024;
                            dataInstalled = true;
                            maxData--;
                            break;
                        }
                    }
                    case "ZShark 512S-8000 Blue": {
                        if (maxData > 0) {
                            slot.setBackgroundResource(R.drawable.zshark_512s_8000_h_blue);
                            dataSize = 512;
                            dataInstalled = true;
                            maxData--;
                            break;
                        }
                    }
                    case "ZShark 512S-8000 Red": {
                        if (maxData > 0) {
                            slot.setBackgroundResource(R.drawable.zshark_512s_8000_h_r);
                            dataSize = 512;
                            dataInstalled = true;
                            maxData--;
                            break;
                        }
                    }
                    case "Offside 64Gb-1200SD": {
                        if (maxData > 0) {
                            slot.setBackgroundResource(R.drawable.offside_64_1200sd_h);
                            dataSize = 64;
                            dataInstalled = true;
                            maxData--;
                            break;
                        }
                    }
                }
            } else {
                custom.ErrorDialog("Вы не установили материнскую плату или корпус");
            }
        }
    }

    void resetCase(){
        moboInstalled = false;
        cpuInstalled = false;
        coolerInstalled = false;
        ramInstalled = false;
        gpuInstalled = false;
        dataInstalled = false;
        psuInstalled = false;
        moboImage.setBackgroundResource(0);
        cpuImage.setBackgroundResource(0);
        coolerImage.setBackgroundResource(0);
        ram1Image.setBackgroundResource(0);
        ram2Image.setBackgroundResource(0);
        ram3Image.setBackgroundResource(0);
        ram4Image.setBackgroundResource(0);
        gpuImage.setBackgroundResource(0);
        gpu2Image.setBackgroundResource(0);
        data1Image.setBackgroundResource(0);
        data2Image.setBackgroundResource(0);
        data3Image.setBackgroundResource(0);
        data4Image.setBackgroundResource(0);
        data5Image.setBackgroundResource(0);
        data6Image.setBackgroundResource(0);
        psuImage.setBackgroundResource(0);
    }
    void resetMobo(){
        moboInstalled = false;
        cpuInstalled = false;
        coolerInstalled = false;
        ramInstalled = false;
        gpuInstalled = false;
        moboImage.setBackgroundResource(0);
        cpuImage.setBackgroundResource(0);
        coolerImage.setBackgroundResource(0);
        ram1Image.setBackgroundResource(0);
        ram2Image.setBackgroundResource(0);
        ram3Image.setBackgroundResource(0);
        ram4Image.setBackgroundResource(0);
        gpuImage.setBackgroundResource(0);
        gpu2Image.setBackgroundResource(0);
    }
    void resetCpu(){
        cpuInstalled = false;
        coolerInstalled = false;
        cpuImage.setBackgroundResource(0);
        coolerImage.setBackgroundResource(0);
    }
}