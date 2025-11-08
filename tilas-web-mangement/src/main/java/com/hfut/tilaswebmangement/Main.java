package com.hfut.tilaswebmangement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String str = "apple bpple banana orange banana";
        Pattern p=Pattern.compile("b\\w+");
        Matcher m=p.matcher(str);
        int count=0;
        while (m.find()){
            count++;
            System.out.println(m.group(0));
        }
        System.out.println(count);
    }
}
