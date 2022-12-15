package com.example.teamproject03.data;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    @Override
    public int getItemViewType(int position){
        switch(list.get(position).getLevel()){
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
            default:
                return 4;
        }
    }

    @NotNull
    @Override
    public FoodAdapter.FoodViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:
                LinearLayout foodButtonLV0
                        = (LinearLayout) LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.food_button_basic, parent, false);
                return new FoodViewHolder(foodButtonLV0);
            case 1:
                LinearLayout foodButtonLV1
                        = (LinearLayout) LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.food_button_careful, parent, false);
                return new FoodViewHolder(foodButtonLV1);
            case 2:
                LinearLayout foodButtonLV2
                        = (LinearLayout) LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.food_button_caution, parent, false);
                return new FoodViewHolder(foodButtonLV2);
            case 3:
                LinearLayout foodButtonLV3
                        = (LinearLayout) LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.food_button_danger, parent, false);
                return new FoodViewHolder(foodButtonLV3);
            case 4:
                LinearLayout foodButtonLV4
                        = (LinearLayout) LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.food_button_rotten, parent, false);
                return new FoodViewHolder(foodButtonLV4);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FoodAdapter.FoodViewHolder viewHolder,
                                 @SuppressLint("RecyclerView") int position) {
        Food f = list.get(position);
        switch(list.get(position).getLevel()) {
            case 0:
                viewHolder.setFood(f);
                viewHolder.iconDelete.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dbManager.delete(f.getID());
                                list.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, list.size());
                            }
                        }
                );
            case 1:
                viewHolder.setFood(f);
                viewHolder.iconDelete.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dbManager.delete(f.getID());
                                list.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, list.size());
                            }
                        }
                );
            case 2:
                viewHolder.setFood(f);
                viewHolder.iconDelete.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dbManager.delete(f.getID());
                                list.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, list.size());
                            }
                        }
                );
            case 3:
                viewHolder.setFood(f);
                viewHolder.iconDelete.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dbManager.delete(f.getID());
                                list.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, list.size());
                            }
                        }
                );
            case 4:
                viewHolder.setFood(f);
                viewHolder.iconDelete.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dbManager.delete(f.getID());
                                list.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, list.size());
                            }
                        }
                );
        }
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
        private LinearLayout foodButton;
        private TextView nameText;
        private TextView dateText;
        private ImageView iconDelete;
        FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodButton = itemView.findViewById(R.id.foodButton);
            nameText = itemView.findViewById(R.id.name);
            dateText = itemView.findViewById(R.id.limit);
            iconDelete = itemView.findViewById(R.id.imageDelete);
        }

        public void setFood(Food food){
            nameText.setText(food.getName());
            dateText.setText(food.getDate());
        }
    }

}
