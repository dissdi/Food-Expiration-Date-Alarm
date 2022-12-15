package com.example.teamproject03.data;

import com.example.teamproject03.model.Food;

import java.util.ArrayList;
import java.util.HashMap;

public class DataHashMap {
    private HashMap<String, Integer> resultHashMap = new HashMap<>();
    private ArrayList<String> arrayList;

    HashMap<String, Integer> setHashMap = new HashMap<String, Integer>(){{
        put("우유",10);
        put("milk", 10);
        put("귤", 20);
        put("요구르트" , 10);
        put("바나나", 15);
        put("삼겹살", 3);
        put("요거트", 7);
        put("양파", 15);
        put("깻잎", 50);
        put("장아찌", 50);
        put("브로커리", 7);
        put("브로콜리", 7);
    }};

    public DataHashMap(ArrayList<String> arrayList) {
        // 우리가 가져온 arrayList
        this.arrayList = arrayList;
    }

    public DataHashMap() {
        this.arrayList = new ArrayList<>();
    }

    public void setArrayList(ArrayList<String> list){
        arrayList.addAll(list);
    }

    public HashMap<String, Integer> setting() {

        for (String item : arrayList) {
            for (String key : setHashMap.keySet()) {
                if (item.contains(key))
                    resultHashMap.put(item, setHashMap.get(key));
            }
        }

        return resultHashMap;
    }

    public int getLeftDate(String name){
        for(String key : setHashMap.keySet()) {
            if( name.contains(key)) return setHashMap.get(key);
        }
        return 10; // 없을 경우
    }
}
