package com.stockkeeper.Controller.helpers;

public class Encrypt {

    public static String encrypt(String key, String msg){
        StringBuilder encrypted = new StringBuilder();
        for(int i =0; i < msg.length(); i++){
            int keyC =  key.codePointAt(i % key.length());
            int msgC = msg.codePointAt(i);
            encrypted.append((char)((msgC+keyC) % 127));
        }
        return encrypted.toString();
    }

    public static String decrypt(String key, String encrypted){
        StringBuilder msg = new StringBuilder();
        for(int i =0; i < encrypted.length(); i++){
            int keyC =  key.codePointAt(i % key.length());
            int encC = encrypted.codePointAt(i);

            if (encC-keyC > 0)
            msg.append((char)((encC-keyC) % 127));
            else
                msg.append((char)((encC-keyC) + 127));

        }
        return msg.toString();
    }

}
