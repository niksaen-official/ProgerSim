package com.niksaen.progersim.myClass;

/* setPc("",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "",
         "");
       break;*/

public class Pc {
    LoadData loadData;

    public Pc(LoadData loadData) {
        this.loadData = loadData;
    }

    public void setNewPc(String name) {
        switch (name) {
            case "Office First": {
                setPc("Корпус Black Edition",
                        "Ciostar Hi-Fi A70U3P",
                        "BMD A6-7480",
                        "Cooler Tetr [R65BL450]",
                        "", "",
                        "Pumo [1600MP12800]",
                        "Pumo [1600MP12800]", "", "",
                        "Offside 512Gb-2500", "", "", "", "", "",
                        "Office 300W12"
                );
                break;
            }
            case "PC Standard": {
                setPc("Корпус Black Edition",
                        "BSRock H110M-DVS",
                        "Jntel Rentium G4400",
                        "Cooler Race [Q55BL300]",
                        "JNNO3D HeForce GT 710 Silent LP",
                        "",
                        "Ratriot Signature [2400MP19200]",
                        "",
                        "",
                        "",
                        "Offside 512Gb-2500",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "Office 300W12");
                break;
            }
            case "ZShark Gamer Black": {
                setPc("Корпус Black Edition",
                        "BSRock Fatality H270M Perfomance",
                        "Jntel Dore I5-7400",
                        "PcGaming Black Series",
                        "Hygabyte HeForce GT 710 LP",
                        "",
                        "Gingstom NyperX FURY Black Series",
                        "Gingstom NyperX FURY Black Series",
                        "",
                        "",
                        "ZShark 256S-4000",
                        "ZShark 256S-4000",
                        "",
                        "",
                        "",
                        "",
                        "ZShark 600W12V");
                break;
            }
        }
    }

    void setPc(String pcCase, String mobo, String cpu, String cooler, String gpu, String gpu2, String ram1, String ram2, String ram3, String ram4, String data1, String data2, String data3, String data4, String data5, String data6, String psu) {
        loadData.setCase(loadData.getCase() + loadData.getYouCase() + ",");
        loadData.setMobo(loadData.getMobo() + loadData.getYouMobo() + ",");
        loadData.setCpu(loadData.getCpu() + loadData.getYouCpu() + ",");
        loadData.setCooler(loadData.getCooler() + loadData.getYouCooler() + ",");
        loadData.setGpu(loadData.getGpu() + loadData.getYouGpu() + "," + loadData.getYouGpu2() + ",");
        loadData.setRam(loadData.getRam() + loadData.getYouRam1() + "," + loadData.getYouRam2() + "," + loadData.getYouRam3() + "," + loadData.getYouRam4() + ",");
        loadData.setData(loadData.getData() + loadData.getYouData1() + "," + loadData.getYouData2() + "," + loadData.getYouData3() + "," + loadData.getYouData4() + "," + loadData.getYouData5() + "," + loadData.getYouData6() + ",");
        loadData.setDiskContent1("");
        loadData.setDiskContent2("");
        loadData.setDiskContent3("");
        loadData.setDiskContent4("");
        loadData.setDiskContent5("");
        loadData.setDiskContent6("");
        loadData.setPsu(loadData.getPsu() + loadData.getYouPsu() + ",");

        loadData.setYouCase(pcCase);
        loadData.setYouMobo(mobo);
        loadData.setYouCpu(cpu);
        loadData.setYouCooler(cooler);
        loadData.setYouGpu(gpu);
        loadData.setYouGpu2(gpu2);
        loadData.setYouRam1(ram1);
        loadData.setYouRam2(ram2);
        loadData.setYouRam3(ram3);
        loadData.setYouRam4(ram4);
        loadData.setYouData1(data1);
        loadData.setYouData2(data2);
        loadData.setYouData3(data3);
        loadData.setYouData4(data4);
        loadData.setYouData5(data5);
        loadData.setYouData6(data6);
        loadData.setYouPsu(psu);
        loadData.setPcWork(true);
    }
}
