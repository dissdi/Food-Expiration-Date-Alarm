package com.example.teamproject03.model;

public class Food {
    private String id;
    private String name;
    private String dueDate;
    private int leftDate;
    private String storageType;
    private String description;
    private int level;
    static final String[] COLOR = {"#DDDDDD", "#FFDBDB", "#FF6363", "#FF0000", "#111111"};
    private String color;

    public Food(String name){
        this.name = name;
    }

    public Food(String id, String name, String leftDate){
        this.id = id;
        this.name = name;
        this.leftDate = Integer.parseInt(leftDate);
        setDescription();
        setColor();
    }

    public Food(String id, String name, String leftDate, String storageType){
        this.id = id;
        this.name = name;
        this.leftDate = Integer.parseInt(leftDate);
        this.storageType = storageType;
        setDescription();
        setColor();
    }


    public void setName(String name)    {
        this.name = name;
    }
    public void setID(String id) { this.id = id; }
    public void setLeftDate(int leftDate){
        this.leftDate = leftDate;
    }
    public void setStorageType(String storageType){
        this.storageType = storageType;
    }
    public void setDescription(){
        if(storageType.equals("냉동")){
            this.description = "안전";
            this.leftDate = 40;
            this.level = 0;
        }
        else if(leftDate>=30) {
            this.description = "안전";
            this.level = 0;
        }
        else if(leftDate>=15) {
            this.description = "유의";
            this.level = 1;
        }
        else if(leftDate>=10) {
            this.description = "주의";
            this.level = 2;
        }
        else if(leftDate>=0) {
            this.description = "위험";
            this.level = 3;
        }
        else {
            this.description = "폐기";
            this.level = 4;
        }
    }

    public void setColor(){
        if(description.equals("안전")) this.color = COLOR[0];
        else if(description.equals("유의")) this.color = COLOR[1];
        else if(description.equals("주의")) this.color = COLOR[2];
        else if(description.equals("위험")) this.color = COLOR[3];
        else this.color = COLOR[4];
    }

    public String getName()         { return name; }
    public Integer getLeftDate()    { return leftDate; }
    public String getID()           { return id; }
    public String getStorageType()  { return storageType; }
    public String getDescription() { return description; }
    public String getColor()        {return color; }
    public String getDate()         { return dueDate; }
    public int getLevel()           { return level; }
}
