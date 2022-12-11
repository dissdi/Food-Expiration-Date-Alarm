package com.example.teamproject03.data;

import androidx.recyclerview.widget.RecyclerView;
import com.example.teamproject03.model.Food;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private List<Food> list;
    DatabaseHelper dbHelper;
    DBManager dbManager;

    FoodAdapter(DatabaseHelper dbHelper, DBManager dbManager){
        this.dbHelper = dbHelper;
        this.dbManager = dbManager;
        list = dbManager.getFoodList();
    }

    public void addFood(Food food){
        this.list.add(food);
    }


}
