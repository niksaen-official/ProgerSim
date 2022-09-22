package com.niksaen.progersim.myClass;

public class ViewData {
    private String title;
    private int imageId;
    private String text;
    private int price;
    private String TYPE;

    public ViewData(String title,String text,int price,int imageId,String TYPE){
        this.title=title;
        this.imageId=imageId;
        this.text = text;
        this.price = price;
        this.TYPE = TYPE;
    }

    public String getTitle(){return this.title;}
    public void setTitle(String title){this.title = title;}

    public String getText(){return this.text;}
    public void setText(String text){this.text=text;}

    public int getPrice(){return this.price;}
    public void setPrice(int price){ this.price=price;}

    public String getTYPE(){return this.TYPE;}

    public int getImageId(){return this.imageId;}
    public void setImageId(int imageId){this.imageId = imageId;}
}
