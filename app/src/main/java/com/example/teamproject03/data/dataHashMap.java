package com.example.teamproject03.data;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class dataHashMap {
    private HashMap<String, Integer> resultHashMap = new HashMap<>();
    private ArrayList<String> arrayList;

    HashMap<String, Integer> setHashMap = new HashMap<String, Integer>(){{
        put("우유",14 + 50);
        put("귤", 90);
        put("요구르트" , 30);
        put("삼겹살", 3);
        put("요거트", 7);
    }};

    dataHashMap(ArrayList<String> arrayList) {
        // 우리가 가져온 arrayList
        this.arrayList = arrayList;
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



}
