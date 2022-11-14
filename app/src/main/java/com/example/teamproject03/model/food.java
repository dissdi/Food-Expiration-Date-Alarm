package com.example.teamproject03.model;

public class food {
    private String name;
    private int buyDate;
    private int curDate;
    private int dueDate;
    private int leftDate;
    private String storageType;
    private int code;
    private String description;
    private int level;
    static final String[] COLOR = {"#DDDDDD", "#FFDBDB", "#FF6363", "#FF0000", "#111111"};
    private String color;
    public food(String name, int leftDate, int buyDate, int dueDate, String storageType){
        this.name = name;
        this.buyDate = buyDate;
        this.curDate = buyDate; // change later
        this.dueDate = dueDate;
        this.leftDate = dueDate-curDate;
        this.storageType = storageType;
        setDescription();
        setColor();
    }

    public void setName(String name)    {
        this.name = name;
    }
    public void setBuyDate(int buyDate) {
        this.buyDate = buyDate;
    }
    public void setLeftDate(int leftDate){
        this.leftDate = leftDate;
    }
    public void setCurDate(int curDate){
        this.curDate = curDate;
    }
    public void setDueDate(int dueDate){
        this.dueDate =  dueDate;
    }
    public void setStorageType(String storageType){
        this.storageType = storageType;
    }
    public void setCode(int hash) { this.code = hash; }
    public void setDescription(){
        leftDate = dueDate - curDate;
        if(leftDate>=20) {
            this.description = "안전";
            this.level = 0;
        }
        else if(leftDate>=10) {
            this.description = "유의";
            this.level = 1;
        }
        else if(leftDate>=5) {
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
    public Integer getCurDate()     { return curDate; }
    public Integer getDueDate()     { return dueDate; }
    public Integer getBuyDate()     { return buyDate; }
    public String getStorageType()  { return storageType; }
    public Integer getHashCode()    { return code; }
    public String getDescription()  { return description; }
    public String getColor()        {return color; }
    public int getLevel()           { return level; }
}
