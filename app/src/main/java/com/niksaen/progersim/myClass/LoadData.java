package com.niksaen.progersim.myClass;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.niksaen.progersim.R;

public class LoadData {
    SharedPreferences sharedPreferences;

    public LoadData(){}
    public LoadData(Activity activity){
        sharedPreferences = activity.getSharedPreferences("playerData", Context.MODE_PRIVATE);
    }
    public LoadData(Context activity){
        sharedPreferences = activity.getSharedPreferences("playerData", Context.MODE_PRIVATE);
    }

    public void setActivity(Activity activity) {
        sharedPreferences = activity.getSharedPreferences("playerData", Context.MODE_PRIVATE);
    }
    public int getPets(){return sharedPreferences.getInt("Pets",0);}
    public void setPets(int Pets){sharedPreferences.edit().putInt("Pets",Pets).apply();}
    public int getBackground(){return sharedPreferences.getInt("Background",R.drawable.background_0);}
    public void setBackground(int backgroundResource){sharedPreferences.edit().putInt("Background",backgroundResource).apply();}

    public boolean getSandboxModeOpen(){ return sharedPreferences.getBoolean("sandboxMode",false); }
    public void setSandboxModeOpen(boolean value){sharedPreferences.edit().putBoolean("sandboxMode",value).apply();}
    public boolean getLearning(){return sharedPreferences.getBoolean("isLearning",false);}
    public void setLearning(boolean isLearning){sharedPreferences.edit().putBoolean("isLearning",isLearning).apply();}

    public void setBookIconForLearn(int resourceIconId){sharedPreferences.edit().putInt("bookIcon",resourceIconId).apply();}
    public int getBookIconForLearn(){return sharedPreferences.getInt("bookIcon",-1);}
    public String getBookForLearn(){return sharedPreferences.getString("bookName", "");}
    public void setBookForLearn(String bookName){sharedPreferences.edit().putString("bookName", bookName).apply(); }
    public void setTimeLearn(int timeSec){sharedPreferences.edit().putInt("time",timeSec).apply();}
    public int getTimeLearn(){return sharedPreferences.getInt("time",-1);}
    public void setTimeLearnCurrent(int timeSec){sharedPreferences.edit().putInt("time2",timeSec).apply();}
    public int getTimeLearnCurrent(){return sharedPreferences.getInt("time2",0);}

    public String getLanguage(){return sharedPreferences.getString("Lang","change");}
    public void setLanguage(String language){sharedPreferences.edit().putString("Lang",language).apply();}

    public void reset() {
        setSandboxModeOpen(false);
        sharedPreferences.edit().putBoolean("reg", false).apply();
        setBookForLearn("");
        setBookIconForLearn(0);
        setTimeLearnCurrent(0);
        setTimeLearn(-1);
        setBook("");
        setCase("");
        setCooler("");
        setCpu("");
        setData("");
        setDiskContent1("");
        setDiskContent2("");
        setDiskContent3("");
        setDiskContent4("");
        setDiskContent5("");
        setDiskContent6("");
        setEnergy(80f);
        setExp(0);
        setGpu("");
        setHacked(false);
        setLanguages("");
        setLevel("Junior 1");
        setMobo("");
        setMoney(18000);
        setNikColor("#FFFFFF");
        setPcWork(false);
        setProfile("");
        setProgramList("");
        setPsu("");
        setRam("");
        setYouCase("");
        setYouCooler("");
        setYouCpu("");
        setYouData1("");
        setYouData2("");
        setYouData3("");
        setYouData4("");
        setYouData5("");
        setYouData6("");
        setYouGpu("");
        setYouGpu2("");
        setYouMobo("");
        setYouPsu("");
        setYouRam1("");
        setYouRam2("");
        setYouRam3("");
        setYouRam4("");
    }

    public float getMoney() { return sharedPreferences.getFloat("money", 18000); }
    public void setMoney(float value) { sharedPreferences.edit().putFloat("money", value).apply(); }

    public boolean getTutorial(){
        return sharedPreferences.getBoolean("Tutorial",false);
    }
    public void setTutorial(boolean value){ sharedPreferences.edit().putBoolean("Tutorial",value).apply(); }

    public void setHacked(boolean value) { sharedPreferences.edit().putBoolean("hack", value).apply(); }
    public boolean getHacked() { return sharedPreferences.getBoolean("hack", false); }

    public String getNikColor() { return sharedPreferences.getString("color", "#FFFFFF"); }
    public void setNikColor(String color) { sharedPreferences.edit().putString("color", color).apply(); }

    public void setProfile(String v) { sharedPreferences.edit().putString("profil", v).apply(); }
    public String getProfile() { return sharedPreferences.getString("profil", ""); }

    public int getLastVersion() { return sharedPreferences.getInt("VERSION", 1); }
    public void setLastVersion(int value) { sharedPreferences.edit().putInt("VERSION", value).apply(); }

    public boolean getPcWork() { return sharedPreferences.getBoolean("pcWork", false); }
    public void setPcWork(boolean value) { sharedPreferences.edit().putBoolean("pcWork", value).apply(); }

    public int getImage() { return sharedPreferences.getInt("image", R.drawable.avatar1); }
    public void setImage(int avatar){sharedPreferences.edit().putInt("image",avatar).apply();}

    public String getPlayerName() { return sharedPreferences.getString("playerName", "Player"); }
    public void setPlayerName(String nik){ sharedPreferences.edit().putString("playerName",nik).apply();}

    public float getEnergy() { return sharedPreferences.getFloat("energy", 80); }
    public void setEnergy(float value) { sharedPreferences.edit().putFloat("energy", value).apply(); }

    public String getLevel(){ return sharedPreferences.getString("level","Junior 1"); }
    public void setLevel(String value){ sharedPreferences.edit().putString("level",value).apply(); }

    public float getExp(){ return sharedPreferences.getFloat("exp",0); }
    public void setExp(float value){ sharedPreferences.edit().putFloat("exp",value).apply(); }

    public String getBook() { return sharedPreferences.getString("book", ""); }
    public void setBook(String value) { sharedPreferences.edit().putString("book", value).apply(); }

    public String getLanguages() { return sharedPreferences.getString("languages", ""); }
    public void setLanguages(String value) { sharedPreferences.edit().putString("languages", value).apply(); }

    public String getProgramList() { return sharedPreferences.getString("programList", ""); }
    public void setProgramList(String value) { sharedPreferences.edit().putString("programList", value.replace("[", "").replace("]", "")).apply(); }

    public String getDiskContent1() { return sharedPreferences.getString("diskContent1", ""); }
    public void setDiskContent1(String value) { sharedPreferences.edit().putString("diskContent1", value).apply(); }
    public String getDiskContent2() { return sharedPreferences.getString("diskContent2", ""); }

    public void setDiskContent2(String value) { sharedPreferences.edit().putString("diskContent2", value).apply(); }
    public String getDiskContent3() { return sharedPreferences.getString("diskContent3", ""); }
    public void setDiskContent3(String value) { sharedPreferences.edit().putString("diskContent3", value).apply(); }

    public String getDiskContent4() {
        return sharedPreferences.getString("diskContent4", "");
    }
    public void setDiskContent4(String value) { sharedPreferences.edit().putString("diskContent4", value).apply(); }

    public String getDiskContent5() { return sharedPreferences.getString("diskContent5", ""); }
    public void setDiskContent5(String value) { sharedPreferences.edit().putString("diskContent5", value).apply(); }

    public String getDiskContent6() { return sharedPreferences.getString("diskContent6", ""); }
    public void setDiskContent6(String value) { sharedPreferences.edit().putString("diskContent6", value).apply(); }

    //пк комплектующие
    public String getCase() { return sharedPreferences.getString("case", ""); }
    public void setCase(String value) { sharedPreferences.edit().putString("case", value).apply(); }

    public String getMobo() { return sharedPreferences.getString("mobo", ""); }
    public void setMobo(String value) { sharedPreferences.edit().putString("mobo", value).apply(); }

    public String getCpu() { return sharedPreferences.getString("cpu", ""); }
    public void setCpu(String value) { sharedPreferences.edit().putString("cpu", value).apply(); }

    public String getRam() { return sharedPreferences.getString("ram", ""); }
    public void setRam(String value) { sharedPreferences.edit().putString("ram", value).apply(); }

    public String getCooler() { return sharedPreferences.getString("cooler", ""); }
    public void setCooler(String value) { sharedPreferences.edit().putString("cooler", value).apply(); }

    public String getGpu() { return sharedPreferences.getString("gpu", ""); }
    public void setGpu(String value) { sharedPreferences.edit().putString("gpu", value).apply();}

    public String getData() { return sharedPreferences.getString("data", ""); }
    public void setData(String value) { sharedPreferences.edit().putString("data", value).apply(); }

    public String getPsu() { return sharedPreferences.getString("psu", ""); }
    public void setPsu(String value) { sharedPreferences.edit().putString("psu", value).apply(); }

    //установленные комплектующие пк
    public String getYouCase() { return sharedPreferences.getString("caseYou", ""); }
    public void setYouCase(String value) { sharedPreferences.edit().putString("caseYou", value).apply(); }

    public String getYouMobo() { return sharedPreferences.getString("moboYou", ""); }
    public void setYouMobo(String value) { sharedPreferences.edit().putString("moboYou", value).apply(); }

    public String getYouCpu() { return sharedPreferences.getString("cpuYou", ""); }
    public void setYouCpu(String value) { sharedPreferences.edit().putString("cpuYou", value).apply(); }

    public String getYouRam1() { return sharedPreferences.getString("ramYou1", ""); }
    public void setYouRam1(String value) { sharedPreferences.edit().putString("ramYou1", value).apply(); }

    public String getYouRam2() { return sharedPreferences.getString("ramYou2", ""); }
    public void setYouRam2(String value) { sharedPreferences.edit().putString("ramYou2", value).apply(); }

    public String getYouRam3() { return sharedPreferences.getString("ramYou3", ""); }
    public void setYouRam3(String value) { sharedPreferences.edit().putString("ramYou3", value).apply(); }

    public String getYouRam4() { return sharedPreferences.getString("ramYou4", ""); }
    public void setYouRam4(String value) { sharedPreferences.edit().putString("ramYou4", value).apply(); }

    public String getYouCooler() { return sharedPreferences.getString("coolerYou", ""); }
    public void setYouCooler(String value) { sharedPreferences.edit().putString("coolerYou", value).apply(); }

    public String getYouGpu() { return sharedPreferences.getString("gpuYou", ""); }
    public void setYouGpu(String value) { sharedPreferences.edit().putString("gpuYou", value).apply(); }

    public String getYouGpu2() { return sharedPreferences.getString("gpuYou2", ""); }
    public void setYouGpu2(String value) { sharedPreferences.edit().putString("gpuYou2", value).apply(); }

    public String getYouData1() { return sharedPreferences.getString("dataYou1", ""); }
    public void setYouData1(String value) { sharedPreferences.edit().putString("dataYou1", value).apply(); }

    public String getYouData2() { return sharedPreferences.getString("dataYou2", ""); }
    public void setYouData2(String value) { sharedPreferences.edit().putString("dataYou2", value).apply(); }

    public String getYouData3() { return sharedPreferences.getString("dataYou3", ""); }
    public void setYouData3(String value) { sharedPreferences.edit().putString("dataYou3", value).apply(); }

    public String getYouData4() { return sharedPreferences.getString("dataYou4", ""); }
    public void setYouData4(String value) { sharedPreferences.edit().putString("dataYou4", value).apply(); }

    public String getYouData5() { return sharedPreferences.getString("dataYou5", ""); }
    public void setYouData5(String value) { sharedPreferences.edit().putString("dataYou5", value).apply(); }

    public String getYouData6() { return sharedPreferences.getString("dataYou6", ""); }
    public void setYouData6(String value) { sharedPreferences.edit().putString("dataYou6", value).apply(); }

    public String getYouPsu() { return sharedPreferences.getString("psuYou", ""); }
    public void setYouPsu(String value) { sharedPreferences.edit().putString("psuYou", value).apply(); }
}