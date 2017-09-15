package com.swust.zj.util;

import java.util.UUID;

/**
 * Created by ZhouJie on 2017/9/6.
 */
public class IDUtil {    public static void main(String[] args){
    System.out.println(UUID.randomUUID().toString());
}
    public static String generateUUID(){
        return UUID.randomUUID().toString();
    }

}
