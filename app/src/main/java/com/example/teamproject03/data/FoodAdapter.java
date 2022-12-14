package com.example.teamproject03.data;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.teamproject03.R;
import com.example.teamproject03.model.Food;
import org.jetbrains.annotations.NotNull;
import java.util.*;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private ArrayList<Food> list = new ArrayList<>();
    DBHelper dbHelper;
    DBManager dbManager;

    public FoodAdapter(DBHelper dbHelper, DBManager dbManager){
        this.dbHelper = dbHelper;
        this.dbManager = dbManager;
        this.list.addAll(dbManager.getFoodList());
    }

    @NotNull
    @Override
    public FoodAdapter.FoodViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new FoodViewHolder(inflater.inflate(R.layout.food_button_basic, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FoodAdapter.FoodViewHolder viewHolder, int position) {
        Food f = list.get(position);
        viewHolder.setFood(f);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addFoods(ArrayList<Food>list){
        this.list.addAll(list);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static public class FoodViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameText;
        private final TextView dateText;

        FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.name);
            dateText = itemView.findViewById(R.id.limit);
        }

        public void setFood(Food food){
            nameText.setText(food.getName());
            dateText.setText(food.getDate());
        }
    }

}
