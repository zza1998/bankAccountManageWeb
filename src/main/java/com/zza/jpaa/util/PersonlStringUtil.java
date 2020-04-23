package com.zza.jpaa.util;

import java.util.Random;

public class PersonlStringUtil {

    public static String makeRandomString(Integer num){
        StringBuilder sb = new StringBuilder();
        Random r = new Random();

        for (int i = 0; i < num; i++) {
            int ran = r.nextInt(100)%26;
            char var2 = (char)('A'+ran);
            sb.append(var2);
        }
        System.out.println(sb.toString());
        return sb.toString();
    }


}
