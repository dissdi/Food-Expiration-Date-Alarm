//package com.example.teamproject03.data;
//
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import com.example.teamproject03.model.Food;
//import org.jetbrains.annotations.NotNull;
//
//import java.util.List;
//
//public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
//    private List<Food> list;
//    DBHelper dbHelper;
//    DBManager dbManager;
//
//    public FoodAdapter(DBHelper dbHelper, DBManager dbManager){
//        this.dbHelper = dbHelper;
//        this.dbManager = dbManager;
//        list = dbManager.getFoodList();
//    }
//
//    public void addFood(Food food){
//        this.list.add(food);
//    }
//
//
//    @NonNull
//    @NotNull
//    @Override
//    public FoodAdapter.FoodViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
////        switch ()
//             return new FoodViewHolder();
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull @NotNull FoodAdapter.FoodViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    static public class FoodViewHolder extends RecyclerView.ViewHolder {
//
//        public FoodViewHolder(){
//            super();
//
//        }
//
//        public void setFood(Food food){
//
//        }
//    }
//}
