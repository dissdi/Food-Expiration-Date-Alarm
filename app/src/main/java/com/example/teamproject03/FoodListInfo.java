package com.example.teamproject03;
import java.util.*;
import com.example.teamproject03.model.Food;

public class FoodListInfo {
    private static FoodListInfo instance;
    private ArrayList<Food> foodList;
    private FoodListInfo(){}

    public static FoodListInfo getInstance(){
        if (instance == null){
            synchronized (FoodListInfo.class){
                instance = new FoodListInfo();
            }
        }
        return instance;
    }

    public void setFoodList(ArrayList<Food> list){
        foodList = list;
    }

    public ArrayList<Food> getFoodList() {
        return foodList;
    }

}
