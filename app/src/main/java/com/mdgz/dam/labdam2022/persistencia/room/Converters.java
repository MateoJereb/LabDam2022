package com.mdgz.dam.labdam2022.persistencia.room;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.UUID;

public class Converters {
    @TypeConverter
    public static Date dateFromLong(Long value){
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long longFromDate(Date date){
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static String stringFromUUID(UUID id){
        return id.toString();
    }

    @TypeConverter
    public static UUID UUIDFromString(String s){
        return UUID.fromString(s);
    }

    @TypeConverter
    public static Integer intFromBoolean(Boolean b){
        return b ? 1 : 0;
    }

    @TypeConverter
    public static Boolean booleanFromInt(Integer n){
        return n == 1 ? true : false;
    }

    @TypeConverter
    public static String bitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b,Base64.DEFAULT);

        return temp == null ? null : temp;
    }

    @TypeConverter
    public static Bitmap stringToBitmap(String encodedString){
        try{
            byte[] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte,0,encodeByte.length);

            return bitmap == null ? null : bitmap;
        }
        catch (Exception e) { e.printStackTrace(); return null;  }
    }
}
