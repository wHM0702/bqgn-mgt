package com.kjde1033.bqgnmgt.utils;

import com.kjde1033.bqgnmgt.model.entity.User;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class CommonUtils {

    Map<Integer,String> userTokenList = new HashMap<>();

    public void saveToken(int uid,String token){
        userTokenList.put(uid,token);
    }

    public String getToken(int uid){
        return userTokenList.get(uid);
    }

    public static int getId(){
        Date date = new Date();
        //1970到现在的毫秒数
        Long ts = date.getTime();
        String str = ts.toString();
        int size = str.length();
        str = str.substring(size-4);
        int rand = (int)(Math.random()*1000);
        str = rand+str;
        return new Integer(str);
    }

    public String generateToken(User user){
        Date date = new Date();
        //1970到现在的毫秒数
        Long ts = date.getTime();
        String str = ts.toString();

        int size = str.length();
        str = str.substring(size-6);

        StringBuilder builder = new StringBuilder();
        builder.append(user.getType()+"-");
        builder.append(str);
        builder.append("-");

        int rand = (int)(Math.random()*1000);
        builder.append(rand);
        builder.append("-");
        builder.append(user.getUserId());

        return builder.toString();
    }

    //时间类型
    public String DateToYMD(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(date);
        return str;
    }

    public Date StrToDate(String str){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(str.substring(0,10));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
