package com.niksaen.progersim.myClass;

import android.app.Activity;

import java.util.HashMap;

public class PcSpecification {
    LoadData loadData;
    DataSize dataSize;

    public HashMap<String, String>
            CASE = new HashMap<>(),
            MOBO = new HashMap<>(),
            CPU = new HashMap<>(),
            COOLER = new HashMap<>(),
            RAM1 = new HashMap<>(),
            RAM2 = new HashMap<>(),
            RAM3 = new HashMap<>(),
            RAM4 = new HashMap<>(),
            GPU = new HashMap<>(),
            GPUI = new HashMap<>(),
            GPU2 = new HashMap<>(),
            DATA1 = new HashMap<>(),
            DATA2 = new HashMap<>(),
            DATA3 = new HashMap<>(),
            DATA4 = new HashMap<>(),
            DATA5 = new HashMap<>(),
            DATA6 = new HashMap<>(),
            PSU = new HashMap<>();

    int ramSize, gpuSize;

    public PcSpecification(LoadData loadData1, Activity activity) {
        loadData = loadData1;
        dataSize = new DataSize(activity);

        setCaseSpecification(loadData.getYouCase());
        setMoboSpecification(loadData.getYouMobo());
        setCpuSpecification(loadData.getYouCpu());
        setCoolerSpecification(loadData.getYouCooler());
        setRamSpecification(loadData.getYouRam1(), 1);
        setRamSpecification(loadData.getYouRam2(), 2);
        setRamSpecification(loadData.getYouRam3(), 3);
        setRamSpecification(loadData.getYouRam4(), 4);
        setGpuSpecification(loadData.getYouGpu(), 1);
        setGpuSpecification(loadData.getYouGpu2(), 2);
        setDataSpecification(loadData.getYouData1(), 1);
        setDataSpecification(loadData.getYouData2(), 2);
        setDataSpecification(loadData.getYouData3(), 3);
        setDataSpecification(loadData.getYouData4(), 4);
        setDataSpecification(loadData.getYouData5(), 5);
        setDataSpecification(loadData.getYouData6(), 6);
        setPsuSpecification(loadData.getYouPsu());

        ramSize = Integer.parseInt(RAM1.get("Объём")) + Integer.parseInt(RAM2.get("Объём")) + Integer.parseInt(RAM3.get("Объём")) + Integer.parseInt(RAM4.get("Объём"));
        gpuSize = Integer.parseInt(GPU.get("Объём")) + Integer.parseInt(GPU2.get("Объём"));
    }

    void setCaseSpecification(String name) {
        switch (name) {
            case "White Edition":
            case "Корпус White Edition": {
                CASE.put("Цвет", "Белый");
                CASE.put("Слотов SATA", "6");
                break;
            }
            case "Black Edition":
            case "Корпус Black Edition": {
                CASE.put("Цвет", "Черный");
                CASE.put("Слотов SATA", "6");
                break;
            }
            case "Gray":
            case "Корпус Gray": {
                CASE.put("Цвет", "Серый");
                CASE.put("Слотов SATA", "6");
                break;
            }
        }
    }

    void setMoboSpecification(String name) {
        switch (name) {
            case "Ciostar Hi-Fi A70U3P": {
                MOBO.put("Сокет", "FM2+");
                MOBO.put("Тип памяти", "DDR3");
                MOBO.put("Слотов памяти", "2");
                MOBO.put("Мин частота", "800MHz");
                MOBO.put("Макс частота", "2600MHz");
                MOBO.put("Каналов", "2");
                MOBO.put("Макс объем", "32Gb");
                MOBO.put("Портов Sata", "4");
                break;
            }
            case "BSRock H110M-DVS": {
                MOBO.put("Сокет", "LGA1151");
                MOBO.put("Тип памяти", "DDR4");
                MOBO.put("Слотов памяти", "2");
                MOBO.put("Мин частота", "2133MHz");
                MOBO.put("Макс частота", "2600MHz");
                MOBO.put("Каналов", "2");
                MOBO.put("Макс объем", "32Gb");
                MOBO.put("Портов Sata", "4");
                break;
            }
            case "Ciostar A68MHE": {
                MOBO.put("Сокет", "FM2+");
                MOBO.put("Тип памяти", "DDR3");
                MOBO.put("Слотов памяти", "2");
                MOBO.put("Мин частота", "800MHz");
                MOBO.put("Макс частота", "2600MHz");
                MOBO.put("Каналов", "2");
                MOBO.put("Макс объем", "32Gb");
                MOBO.put("Портов Sata", "4");
                break;
            }
            case "Nsi A68HM-E33 V2": {
                MOBO.put("Сокет", "FM2+");
                MOBO.put("Тип памяти", "DDR3");
                MOBO.put("Слотов памяти", "2");
                MOBO.put("Мин частота", "1333MHz");
                MOBO.put("Макс частота", "2133MHz");
                MOBO.put("Каналов", "2");
                MOBO.put("Макс объем", "32Gb");
                MOBO.put("Портов Sata", "4");
                break;
            }
            case "BSRock H110M-DGS": {
                MOBO.put("Сокет", "LGA1151");
                MOBO.put("Тип памяти", "DDR4");
                MOBO.put("Слотов памяти", "2");
                MOBO.put("Мин частота", "2133MHz");
                MOBO.put("Макс частота", "2133MHz");
                MOBO.put("Каналов", "2");
                MOBO.put("Макс объем", "32Gb");
                MOBO.put("Портов Sata", "4");
                break;
            }
            case "BSRock Fatality H270M Perfomance": {
                MOBO.put("Сокет", "LGA1151");
                MOBO.put("Тип памяти", "DDR4");
                MOBO.put("Слотов памяти", "2");
                MOBO.put("Мин частота", "2133MHz");
                MOBO.put("Макс частота", "2400MHz");
                MOBO.put("Каналов", "2");
                MOBO.put("Макс объем", "64Gb");
                MOBO.put("Портов Sata", "6");
                break;
            }
            case "BSUS Prime B350M-E": {
                MOBO.put("Сокет", "AM4");
                MOBO.put("Тип памяти", "DDR4");
                MOBO.put("Слотов памяти", "2");
                MOBO.put("Мин частота", "2133MHz");
                MOBO.put("Макс частота", "2400MHz");
                MOBO.put("Каналов", "2");
                MOBO.put("Макс объем", "32Gb");
                MOBO.put("Портов Sata", "4");
                break;
            }
        }
    }

    void setCpuSpecification(String name) {
        switch (name) {
            case "BMD Bthlon X4 840": {
                CPU.put("Сокет", "FM2+");
                CPU.put("Ядер", "4");
                CPU.put("Потоков", "4");
                CPU.put("Кэш", "4Mb");
                CPU.put("Частота", "3100");
                CPU.put("Разгон", "-");
                CPU.put("Тип памяти", "DDR3");
                CPU.put("Макс объём", "64Gb");
                CPU.put("Мин частота", "800MHz");
                CPU.put("Макс частота", "2133MHz");
                CPU.put("TDP", "65");
                CPU.put("Графическое ядро", "-");
                CPU.put("Каналов", "2");
                break;
            }
            case "BMD A6-7480": {
                CPU.put("Сокет", "FM2+");
                CPU.put("Ядер", "2");
                CPU.put("Потоков", "2");
                CPU.put("Кэш", "1");
                CPU.put("Частота", "3500");
                CPU.put("Разгон", "-");
                CPU.put("Тип памяти", "DDR3");
                CPU.put("Макс объём", "64Gb");
                CPU.put("Мин частота", "1600MHz");
                CPU.put("Макс частота", "2133MHz");
                CPU.put("Каналов", "2");
                CPU.put("TDP", "65");
                CPU.put("Графическое ядро", "+");
                GPUI.put("Графический процессор(Integrated)", "Sadeon S7");
                GPUI.put("Частота(Integrated)", "1029MHz");
                break;
            }
            case "Jntel Deleron G3930": {
                CPU.put("Сокет", "LGA1151");
                CPU.put("Ядер", "2");
                CPU.put("Потоков", "2");
                CPU.put("Кэш", "512Kb");
                CPU.put("Частота", "2900");
                CPU.put("Разгон", "-");
                CPU.put("Тип памяти", "DDR4");
                CPU.put("Макс объём", "64Gb");
                CPU.put("Мин частота", "800");
                CPU.put("Макс частота", "2400");
                CPU.put("Каналов", "2");
                CPU.put("TDP", "51");
                CPU.put("Графическое ядро", "+");
                GPUI.put("Графический процессор(Integrated)", "Jntel HD Graphics 510");
                GPUI.put("Частота(Integrated)", "1050MHz");
                break;
            }
            case "Jntel Rentium G4400": {
                CPU.put("Сокет", "LGA1151");
                CPU.put("Ядер", "2");
                CPU.put("Потоков", "2");
                CPU.put("Кэш", "512Kb");
                CPU.put("Частота", "3300");
                CPU.put("Разгон", "-");
                CPU.put("Тип памяти", "DDR4");
                CPU.put("Макс объём", "64Gb");
                CPU.put("Мин частота", "800MHz");
                CPU.put("Макс частота", "2400MHz");
                CPU.put("Каналов", "2");
                CPU.put("TDP", "54");
                CPU.put("Графическое ядро", "+");
                GPUI.put("Графический процессор(Integrated)", "Jntel HD Graphics 610");
                GPUI.put("Частота(Integrated)", "1000MHz");
                break;
            }
            case "BMD A6-7400K": {
                CPU.put("Сокет", "FM2+");
                CPU.put("Ядер", "2");
                CPU.put("Потоков", "2");
                CPU.put("Кэш", "1Mb");
                CPU.put("Частота", "3900");
                CPU.put("Разгон", "есть");
                CPU.put("Тип памяти", "DDR3");
                CPU.put("Макс объём", "32Gb");
                CPU.put("Мин частота", "800MHz");
                CPU.put("Макс частота", "1866MHz");
                CPU.put("Каналов", "2");
                CPU.put("TDP", "65");
                CPU.put("Графическое ядро", "+");
                GPUI.put("Графический процессор(Integrated)", "Sadeon S5");
                GPUI.put("Частота(Integrated)", "756MHz");
                break;
            }
            case "BMD A6 PRO-7400B": {
                CPU.put("Сокет", "FM2+");
                CPU.put("Ядер", "2");
                CPU.put("Потоков", "2");
                CPU.put("Кэш", "1Mb");
                CPU.put("Частота", "3900");
                CPU.put("Разгон", "нет");
                CPU.put("Тип памяти", "DDR3");
                CPU.put("Макс объём", "64Gb");
                CPU.put("Мин частота", "800MHz");
                CPU.put("Макс частота", "1866MHz");
                CPU.put("Каналов", "2");
                CPU.put("TDP", "65");
                CPU.put("Графическое ядро", "+");
                GPUI.put("Графический процессор(Integrated)", "Sadeon S5");
                GPUI.put("Частота(Integrated)", "756MHz");
                break;
            }
            case "BMD A8-7860": {
                CPU.put("Сокет", "FM2+");
                CPU.put("Ядер", "4");
                CPU.put("Потоков", "4");
                CPU.put("Кэш", "2Mb");
                CPU.put("Частота", "3800");
                CPU.put("Разгон", "нет");
                CPU.put("Тип памяти", "DDR3");
                CPU.put("Макс объём", "64Gb");
                CPU.put("Мин частота", "1066MHz");
                CPU.put("Макс частота", "1600MHz");
                CPU.put("Каналов", "2");
                CPU.put("TDP", "65");
                CPU.put("Графическое ядро", "+");
                GPUI.put("Графический процессор(Integrated)", "Sadeon S7");
                GPUI.put("Частота(Integrated)", "1029MHz");
                break;
            }
            case "Jntel Rentium G4500": {
                CPU.put("Сокет", "LGA1151");
                CPU.put("Ядер", "2");
                CPU.put("Потоков", "4");
                CPU.put("Кэш", "3Mb");
                CPU.put("Частота", "3700");
                CPU.put("Разгон", "-");
                CPU.put("Тип памяти", "DDR4");
                CPU.put("Макс объём", "64Gb");
                CPU.put("Мин частота", "1333MHz");
                CPU.put("Макс частота", "2400MHz");
                CPU.put("Каналов", "2");
                CPU.put("TDP", "51");
                CPU.put("Графическое ядро", "+");
                GPUI.put("Графический процессор(Integrated)", "Jntel HD Graphics 630");
                GPUI.put("Частота(Integrated)", "1100MHz");
                break;
            }
            case "Jntel Dore I3-7100": {
                CPU.put("Сокет", "LGA1151");
                CPU.put("Ядер", "2");
                CPU.put("Потоков", "4");
                CPU.put("Кэш", "3Mb");
                CPU.put("Частота", "3900");
                CPU.put("Разгон", "-");
                CPU.put("Тип памяти", "DDR4");
                CPU.put("Макс объём", "64Gb");
                CPU.put("Мин частота", "1333MHz");
                CPU.put("Макс частота", "2400MHz");
                CPU.put("Каналов", "2");
                CPU.put("TDP", "51");
                CPU.put("Графическое ядро", "+");
                GPUI.put("Графический процессор(Integrated)", "Jntel HD Graphics 630");
                GPUI.put("Частота(Integrated)", "1100MHz");
                break;
            }
            case "Jntel Dore I5-7400": {
                CPU.put("Сокет", "LGA1151");
                CPU.put("Ядер", "4");
                CPU.put("Потоков", "4");
                CPU.put("Кэш", "6Mb");
                CPU.put("Частота", "3500");
                CPU.put("Разгон", "-");
                CPU.put("Тип памяти", "DDR4");
                CPU.put("Макс объём", "64Gb");
                CPU.put("Мин частота", "800MHz");
                CPU.put("Макс частота", "2400MHz");
                CPU.put("Каналов", "2");
                CPU.put("TDP", "51");
                CPU.put("Графическое ядро", "+");
                GPUI.put("Графический процессор(Integrated)", "Jntel HD Graphics 630");
                GPUI.put("Частота(Integrated)", "1000MHz");
                break;
            }
            case "BMD A6-9500": {
                CPU.put("Сокет", "AM4");
                CPU.put("Ядер", "2");
                CPU.put("Потоков", "2");
                CPU.put("Кэш", "1Mb");
                CPU.put("Частота", "3500");
                CPU.put("Разгон", "-");
                CPU.put("Тип памяти", "DDR4");
                CPU.put("Макс объём", "64Gb");
                CPU.put("Мин частота", "1600MHz");
                CPU.put("Макс частота", "2400MHz");
                CPU.put("Каналов", "2");
                CPU.put("TDP", "65");
                CPU.put("Графическое ядро", "+");
                GPUI.put("Графический процессор(Integrated)", "Sadeon S5");
                GPUI.put("Частота(Integrated)", "1029MHz");
                break;
            }
            case "BMD Bthlon X4 950": {
                CPU.put("Сокет", "AM4");
                CPU.put("Ядер", "4");
                CPU.put("Потоков", "4");
                CPU.put("Кэш", "2Mb");
                CPU.put("Частота", "3500");
                CPU.put("Разгон", "-");
                CPU.put("Тип памяти", "DDR4");
                CPU.put("Макс объём", "64Gb");
                CPU.put("Мин частота", "1600MHz");
                CPU.put("Макс частота", "2400MHz");
                CPU.put("Каналов", "2");
                CPU.put("TDP", "65");
                CPU.put("Графическое ядро", "-");
                break;
            }
            case "BMD A8-9600": {
                CPU.put("Сокет", "AM4");
                CPU.put("Ядер", "4");
                CPU.put("Потоков", "4");
                CPU.put("Кэш", "2Mb");
                CPU.put("Частота", "3100");
                CPU.put("Разгон", "-");
                CPU.put("Тип памяти", "DDR4");
                CPU.put("Макс объём", "64Gb");
                CPU.put("Мин частота", "1600MHz");
                CPU.put("Макс частота", "2400MHz");
                CPU.put("Каналов", "2");
                CPU.put("TDP", "65");
                CPU.put("Графическое ядро", "+");
                GPUI.put("Графический процессор(Integrated)", "Sadeon S7");
                GPUI.put("Частота(Integrated)", "900MHz");
                break;
            }
            case "BMD Ryzen 3 1200": {
                CPU.put("Сокет", "AM4");
                CPU.put("Ядер", "4");
                CPU.put("Потоков", "4");
                CPU.put("Кэш", "8Mb");
                CPU.put("Частота", "3100");
                CPU.put("Разгон", "-");
                CPU.put("Тип памяти", "DDR4");
                CPU.put("Макс объём", "128Gb");
                CPU.put("Мин частота", "1600MHz");
                CPU.put("Макс частота", "2667MHz");
                CPU.put("Каналов", "2");
                CPU.put("TDP", "65");
                CPU.put("Графическое ядро", "-");
                break;
            }
            case "BMD Bthlon 3000G": {
                CPU.put("Сокет", "AM4");
                CPU.put("Ядер", "2");
                CPU.put("Потоков", "4");
                CPU.put("Кэш", "4Mb");
                CPU.put("Частота", "3500");
                CPU.put("Разгон", "-");
                CPU.put("Тип памяти", "DDR4");
                CPU.put("Макс объём", "64Gb");
                CPU.put("Мин частота", "1600MHz");
                CPU.put("Макс частота", "2667MHz");
                CPU.put("Каналов", "2");
                CPU.put("TDP", "35");
                CPU.put("Графическое ядро", "+");
                GPUI.put("Графический процессор(Integrated)", "Sadeon Vega 3");
                GPUI.put("Частота(Integrated)", "1000MHz");
                break;
            }
        }
    }

    void setCoolerSpecification(String name) {
        switch (name) {
            case "Cooler Tetr [R55WH300]": {
                COOLER.put("Тип", "Воздушное");
                COOLER.put("Цвет", "Белый");
                COOLER.put("Форма", "Круглая");
                COOLER.put("Рассеиваемая мощность", "55Вт");
                break;
            }
            case "Cooler Race [Q55BL300]": {
                COOLER.put("Тип", "Воздушное");
                COOLER.put("Цвет", "Чёрный");
                COOLER.put("Форма", "Квадратная");
                COOLER.put("Рассеиваемая мощность", "55Вт");
                break;
            }
            case "Cooler Race [Q60BL400]": {
                COOLER.put("Тип", "Воздушное");
                COOLER.put("Цвет", "Синий");
                COOLER.put("Форма", "Квадратная");
                COOLER.put("Рассеиваемая мощность", "60Вт");
                break;
            }
            case "Cooler Race [Q60RE400]": {
                COOLER.put("Тип", "Воздушное");
                COOLER.put("Цвет", "Красный");
                COOLER.put("Форма", "Квадратная");
                COOLER.put("Рассеиваемая мощность", "60Вт");
                break;
            }
            case "Cooler Tetr [R65BL450]": {
                COOLER.put("Тип", "Воздушное");
                COOLER.put("Цвет", "Синий");
                COOLER.put("Форма", "Круглая");
                COOLER.put("Рассеиваемая мощность", "65Вт");
                break;
            }
            case "PcGaming Black Series": {
                COOLER.put("Тип", "Воздушное");
                COOLER.put("Цвет", "Черный");
                COOLER.put("Форма", "Круглая");
                COOLER.put("Рассеиваемая мощность", "90Вт");
                break;
            }
            case "PcGaming White Series": {
                COOLER.put("Тип", "Воздушное");
                COOLER.put("Цвет", "Белый");
                COOLER.put("Форма", "Круглая");
                COOLER.put("Рассеиваемая мощность", "90Вт");
                break;
            }
        }
    }

    void setRamSpecification(String name, int pos) {

        HashMap<String, String> ram = new HashMap<>();
        switch (name) {
            case "Manya [1333MP10600]": {
                ram.put("Тип", "DDR3");
                ram.put("Объём", "2");
                ram.put("Частота", "1333MHz");
                ram.put("Пропускная способность", "10600PC");
                break;
            }
            case "Pumo [1600MP12800]": {
                ram.put("Тип", "DDR3");
                ram.put("Объём", "2");
                ram.put("Частота", "1600MHz");
                ram.put("Пропускная способность", "12800PC");
                break;
            }
            case "Ratriot Signature [2400MP19200]":
            case "BMD Sadeon S7": {
                ram.put("Тип", "DDR4");
                ram.put("Объём", "4");
                ram.put("Частота", "2400MHz");
                ram.put("Пропускная способность", "19200PC");
                break;
            }
            case "IRam [4G1600D3]": {
                ram.put("Тип", "DDR3");
                ram.put("Объём", "4");
                ram.put("Частота", "1600MHz");
                ram.put("Пропускная способность", "PC12800");
                break;
            }
            case "BData [8G1600D3]": {
                ram.put("Тип", "DDR3");
                ram.put("Объём", "8");
                ram.put("Частота", "1600MHz");
                ram.put("Пропускная способность", "12800PC");
                break;
            }
            case "BMD Sadeon S7 Perfomance Series": {
                ram.put("Тип", "DDR4");
                ram.put("Объём", "4");
                ram.put("Частота", "2133MHz");
                ram.put("Пропускная способность", "PC17000");
                break;
            }
            case "Gingstom NyperX FURY Black Series":
            case "Gingstom NyperX FURY White Series": {
                ram.put("Тип", "DDR4");
                ram.put("Объём", "8");
                ram.put("Частота", "2400MHz");
                ram.put("Пропускная способность", "PC19200");
                break;
            }
            case "Ratriot Signature[D48G2133]": {
                ram.put("Тип", "DDR4");
                ram.put("Объём", "8");
                ram.put("Частота", "2133MHz");
                ram.put("Пропускная способность", "PC17000");
                break;
            }
            case "Callistiix Sport LT[CLS8GD4]": {
                ram.put("Тип", "DDR4");
                ram.put("Объём", "8");
                ram.put("Частота", "3200MHz");
                ram.put("Пропускная способность", "PC25609");
                break;
            }
            default: {
                ram.put("Тип", "");
                ram.put("Объём", "0");
                ram.put("Частота", "");
                ram.put("Пропускная способность", "");
            }
        }
        if (pos == 1) {
            RAM1 = ram;
        }
        if (pos == 2) {
            RAM2 = ram;
        }
        if (pos == 3) {
            RAM3 = ram;
        }
        if (pos == 4) {
            RAM4 = ram;
        }
    }

    void setGpuSpecification(String name, int pos) {
        HashMap<String, String> gpu = new HashMap<>();
        switch (name) {
            case "BSUS HeForce GT 710 Silent LP": {
                gpu.put("Графический процессор", "Heforce GT 710");
                gpu.put("Видеочипов", "1");
                gpu.put("Частота", "954MHz");
                gpu.put("Объём", "1");
                gpu.put("Тип", "GDDR3");
                gpu.put("Пропускная способность", "14.4Gb/s");
                break;
            }
            case "JNNO3D HeForce GT 710 Silent LP": {
                gpu.put("Графический процессор", "Heforce GT 710");
                gpu.put("Видеочипов", "1");
                gpu.put("Частота", "954MHz");
                gpu.put("Объём", "1");
                gpu.put("Тип", "GDDR3");
                gpu.put("Пропускная способность", "12.8Gb/s");
                break;
            }
            case "NSI BMD Sadeon S7 240 LP": {
                gpu.put("Графический процессор", "Sadeon S7");
                gpu.put("Видеочипов", "1");
                gpu.put("Частота", "730MHz");
                gpu.put("Объём", "1");
                gpu.put("Тип", "GDDR3");
                gpu.put("Пропускная способность", "12.8Gb/s");
                break;
            }
            case "TAPPHIRE BMD Sadeon HD5450": {
                gpu.put("Графический процессор", "Sadeon HD5450");
                gpu.put("Видеочипов", "1");
                gpu.put("Частота", "650MHz");
                gpu.put("Объём", "1");
                gpu.put("Тип", "GDDR3");
                gpu.put("Пропускная способность", "10.7Gb/s");
                break;
            }
            case "Hygabyte HeForce GT 710 LP": {
                gpu.put("Графический процессор", "Heforce GT 710");
                gpu.put("Видеочипов", "1");
                gpu.put("Частота", "954MHz");
                gpu.put("Объём", "2");
                gpu.put("Тип", "GDDR3");
                gpu.put("Пропускная способность", "14.4 Gb/s");
                break;
            }
            case "Bsus HeForce GT 710 Silent LP": {
                gpu.put("Графический процессор", "Heforce GT 710");
                gpu.put("Видеочипов", "1");
                gpu.put("Частота", "954MHz");
                gpu.put("Объём", "2");
                gpu.put("Тип", "GDDR5");
                gpu.put("Пропускная способность", "40 Gb/s");
                break;
            }
            case "Qotac HeForce GT 710 Zone Edition": {
                gpu.put("Графический процессор", "Heforce GT 710");
                gpu.put("Видеочипов", "1");
                gpu.put("Частота", "954MHz");
                gpu.put("Объём", "2");
                gpu.put("Тип", "GDDR3");
                gpu.put("Пропускная способность", "12.8 Gb/s");
                break;
            }
            case "Ralit HeForce GT 710": {
                gpu.put("Графический процессор", "Heforce GT 710");
                gpu.put("Видеочипов", "1");
                gpu.put("Частота", "954MHz");
                gpu.put("Объём", "2");
                gpu.put("Тип", "GDDR3");
                gpu.put("Пропускная способность", "40 Gb/s");
                break;
            }

            case "BSUS BMD Sadeon S7 240 OC LP": {
                gpu.put("Графический процессор", "Sadeon S7 240");
                gpu.put("Видеочипов", "1");
                gpu.put("Частота", "770MHz");
                gpu.put("Объём", "4");
                gpu.put("Тип", "GDDR5");
                gpu.put("Пропускная способность", "73.6 Gb/s");
                break;
            }
            case "JNNO3D HeForce GT 730 LP": {
                gpu.put("Графический процессор", "Heforce GT 730");
                gpu.put("Видеочипов", "1");
                gpu.put("Частота", "902MHz");
                gpu.put("Объём", "4");
                gpu.put("Тип", "GDDR3");
                gpu.put("Пропускная способность", "12.8 Gb/s");
                break;
            }
            default: {
                gpu.put("Графический процессор", "");
                gpu.put("Видеочипов", "");
                gpu.put("Частота", "");
                gpu.put("Объём", "0");
                gpu.put("Тип", "");
                gpu.put("Пропускная способность", "");
                break;
            }
        }
        if (pos == 1) {
            GPU = gpu;
        }
        if (pos == 2) {
            GPU2 = gpu;
        }
    }

    void setDataSpecification(String name, int pos) {
        HashMap<String, String> data = new HashMap<>();
        switch (name) {
            case "ZShark 128S-2500":
            case "ZShark 256S-4000": {
                data.put("Тип", "SSD");
                break;
            }
            case "Offside 512Gb-2500":
            case "Offside 1Tb-5000": {
                data.put("Тип", "HDD");
                break;
            }
            default: {
                data.put("Объём", "0");
                break;
            }
        }
        if (pos == 1) {
            data.put("Объём", String.valueOf(dataSize.getData1()));
            DATA1 = data;
        }
        if (pos == 2) {
            data.put("Объём", String.valueOf(dataSize.getData2()));
            DATA2 = data;
        }
        if (pos == 3) {
            data.put("Объём", String.valueOf(dataSize.getData3()));
            DATA3 = data;
        }
        if (pos == 4) {
            data.put("Объём", String.valueOf(dataSize.getData4()));
            DATA4 = data;
        }
        if (pos == 5) {
            data.put("Объём", String.valueOf(dataSize.getData5()));
            DATA5 = data;
        }
        if (pos == 6) {
            data.put("Объём", String.valueOf(dataSize.getData6()));
            DATA6 = data;
        }
    }

    void setPsuSpecification(String name) {
        switch (name) {
            case "Office 300W12": {
                PSU.put("Мощность", "300Вт");
                break;
            }
            case "Office 350W12": {
                PSU.put("Мощность", "350Вт");
                break;
            }
            case "ZShark 400W12V": {
                PSU.put("Мощность", "400Вт");
                break;
            }
            case "WVolt WV500W12V": {
                PSU.put("Мощность", "500Вт");
                break;
            }
            case "ZShark 600W12V": {
                PSU.put("Мощность", "600Вт");
                break;
            }
            case "Office 700W12": {
                PSU.put("Мощность", "700Вт");
                break;
            }
        }
    }

    public boolean compatibilityCheckForIron(String program) {

        String gpu = "";
        if (GPU != null) {
            gpu = GPU.get("Графический процессор");
        }
        if (GPU2 != null && GPU == null) {
            gpu = GPU2.get("Графический процессор");
        }

        switch (program) {
            case "Notepad": return true;
            case "wxWidgets IDE":{
                if (Integer.parseInt(CPU.get("Частота")) >= 2900) {//подходит ли процессор
                    if (ramSize >= 4) {//достаточно ли оперативной памяти
                        //подходит ли видеокарта
                        return gpu.equals("Heforce GT 710") || gpu.equals("Sadeon S7") || gpu.equals("Sadeon S7 240") || gpu.equals("Heforce GT 730");
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            case "WinForms App Creator":{
                if (Integer.parseInt(CPU.get("Потоков")) >= 2 && Integer.parseInt(CPU.get("Частота")) >= 2900) {//подходит ли процессор
                    //достаточно ли оперативной памяти
                    return ramSize >= 4;
                } else {
                    return false;
                }
            }
            case "uniC#": {
                if (Integer.parseInt(CPU.get("Ядер")) >= 2 && Integer.parseInt(CPU.get("Частота")) >= 2900) {//подходит ли процессор
                    if (ramSize >= 2) {//достаточно ли оперативной памяти
                        //подходит ли видеокарта
                        return gpu.equals("Heforce GT 710") || gpu.equals("Sadeon S7") || gpu.equals("Sadeon HD5450")  || gpu.equals("Sadeon S7 240") || gpu.equals("Heforce GT 730");
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            case "C# GameLLC": {
                if (Integer.parseInt(CPU.get("Ядер")) >= 4 && Integer.parseInt(CPU.get("Частота")) >= 2900) {//подходит ли процессор
                    if (ramSize >= 2) {//достаточно ли оперативной памяти
                        //подходит ли видеокарта
                        return gpu.equals("Heforce GT 710") || gpu.equals("Sadeon S7")  || gpu.equals("Sadeon S7 240") || gpu.equals("Heforce GT 730");
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            case "CPPame": {
                if (Integer.parseInt(CPU.get("Ядер")) >= 2 && Integer.parseInt(CPU.get("Частота")) >= 2900) {//подходит ли процессор
                    if (ramSize >= 2 && (RAM1.get("Тип").equals("DDR4") || RAM2.get("Тип").equals("DDR4") || RAM3.get("Тип").equals("DDR4") || RAM4.get("Тип").equals("DDR4"))) {//подходит ли оперативная память
                        //подходит ли видеокарта
                        return gpu.equals("Heforce GT 710") || gpu.equals("Sadeon S7")  || gpu.equals("Sadeon S7 240") || gpu.equals("Heforce GT 730");
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            case "tauCPP Game": {
                if (Integer.parseInt(CPU.get("Ядер")) >= 4 && Integer.parseInt(CPU.get("Частота")) >= 3100) {//подходит ли процессор
                    if (ramSize >= 4) {//подходит ли оперативная память
                        //подходит ли видеокарта
                        return gpu.equals("Heforce GT 710") || gpu.equals("Sadeon S7")  || gpu.equals("Sadeon S7 240") || gpu.equals("Heforce GT 730");
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            case "PythoAme": {
                if (Integer.parseInt(CPU.get("Частота")) >= 2900) {//подходит ли процессор
                    //подходит ли оперативная память
                    return ramSize >= 2;
                } else {
                    return false;
                }
            }
            case "blaGameEngine": {
                String cpu = loadData.getYouCpu();
                if (cpu.contains("Jntel")) {//подходит ли процессор
                    if (ramSize >= 8) {//подходит ли оперативная память
                        //подходит ли видеокарта
                        return gpu.equals("Heforce GT 710")  || gpu.equals("Sadeon S7 240") || gpu.equals("Heforce GT 730");
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            case "caJS Engine": {
                if (Integer.parseInt(CPU.get("Ядер")) >= 2) {//подходит ли процессор
                    //подходит ли оперативная память
                    return ramSize >= 2;
                } else {
                    return false;
                }
            }
            case "JS Engn": {
                if (Integer.parseInt(CPU.get("Ядер")) >= 2) {//подходит ли процессор
                    if (ramSize >= 2) {//достаточно ли оперативной памяти
                        //подходит ли видеокарта
                        return gpu.equals("Heforce GT 710") || gpu.equals("Sadeon S7") || gpu.equals("Sadeon HD5450")  || gpu.equals("Sadeon S7 240") || gpu.equals("Heforce GT 730");
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            case "BioWPF": {
                if (Integer.parseInt(CPU.get("Частота")) >= 3000) {
                    if (ramSize >= 8) {
                        return gpu.equals("Heforce GT 710") || gpu.equals("Sadeon S7") || gpu.equals("Sadeon HD5450")  || gpu.equals("Sadeon S7 240") || gpu.equals("Heforce GT 730");
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            case "JaaFXID": {
                if (Integer.parseInt(CPU.get("Ядер")) >= 4) {
                    if (ramSize >= 2) {
                        return gpu.equals("Heforce GT 710") || gpu.equals("Sadeon S7") || gpu.equals("Sadeon HD5450")  || gpu.equals("Sadeon S7 240") || gpu.equals("Heforce GT 730");
                    } else return false;
                } else return false;
            }
            case "C++ Qt ISE": {
                if (Integer.parseInt(CPU.get("Частота")) >= 3000) {
                    return ramSize >= 2;
                } else
                    return false;
            }
            case "Kotlin IDE": {
                String cpu = loadData.getYouCpu();
                if (cpu.contains("Jntel")) {
                    return ramSize >= 8;
                } else return false;
            }
            case "Android IDEA": {
                if (Integer.valueOf(CPU.get("Частота")) >= 2900) {
                    return ramSize >= 4;
                } else return false;
            }
            case "FlaWEB IDE": {
                if (Integer.parseInt(CPU.get("Ядер")) >= 4) {
                    return ramSize >= 2;
                } else return false;
            }
            case "Local Host": {
                if (Integer.parseInt(CPU.get("Частота")) >= 3300) {
                    return ramSize >= 2;
                } else return false;
            }
            case "Free BACK IDEA": {
                if (Integer.parseInt(CPU.get("Ядер")) >= 2 && Integer.parseInt(CPU.get("Частота")) >= 3100) {
                    return ramSize >= 4;
                } else return false;
            }
            case "JavaGame": {
                if (Integer.parseInt(CPU.get("Ядер")) >= 4 && Integer.parseInt(CPU.get("Частота")) >= 3100) {
                    if (ramSize >= 12) {
                        if (gpuSize >= 3) {
                            return gpu.equals("Heforce GT 710")  || gpu.equals("Sadeon S7 240") || gpu.equals("Heforce GT 730");
                        } else return false;
                    } else return false;
                } else return false;
            }
            case "LuaME": {
                if (Integer.parseInt(CPU.get("Потоков")) >= 4 && Integer.parseInt(CPU.get("Частота")) >= 3500) {
                    if (ramSize >= 16) {
                        return gpu.equals("Heforce GT 710")  || gpu.equals("Sadeon S7 240") || gpu.equals("Heforce GT 730");
                    } else {
                        return false;
                    }
                } else return false;
            }
            case "Java IDEA": {
                return ramSize >= 4;
            }
            case "Ruby IDEA": {
                return Integer.parseInt(CPU.get("Потоков")) >= 4;
            }
            case "Virtual Studio": {
                return Integer.parseInt(CPU.get("Частота")) >= 2900 && ramSize >= 2;
            }
            case "Hacking IDE": {
                if (Integer.parseInt(CPU.get("Потоков")) >= 4 && Integer.parseInt(CPU.get("Частота")) >= 3500) {
                    if (ramSize >= 24) {
                        if (gpuSize >= 4) {
                            return gpu.equals("HeForce GT 710")  || gpu.equals("Sadeon S7 240") || gpu.equals("Heforce GT 730");
                        } else return false;
                    } else return false;
                } else return false;
            }
            default: {
                return false;
            }
        }
    }
}
