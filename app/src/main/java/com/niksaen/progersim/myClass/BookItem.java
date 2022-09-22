package com.niksaen.progersim.myClass;

public class BookItem {
    public String name;
    public String text;
    public int image;
    public int timeLearn;
    public int expLearn;

    public BookItem(String name, String text, int image, int timeLearn) {
        if (name == null) {
            name = "false";
        }
        this.name = name;
        this.text = text;
        this.image = image;
        this.timeLearn = timeLearn;
        expLearn = timeLearn;
    }

    public BookItem(String type) {
        if (name == null) {
            name = "false";
        }
        this.name = type;
        this.text = "";
        this.image = 0;
        this.timeLearn = 0;
        expLearn = 0;
    }
}