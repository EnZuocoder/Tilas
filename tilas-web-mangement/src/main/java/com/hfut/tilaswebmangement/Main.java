package com.hfut.tilaswebmangement;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        List<Map<String , Object>>list=new ArrayList<>();
        HashMap<String ,Object>map=new HashMap<>();
        map.put("pos","教研主管");
        map.put("nums",5);
        list.add(map);
        list.stream().map(datamap->datamap.get("pos")).toList();


    }
}
