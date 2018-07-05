package com.cicd.cicd.io;

import java.io.*;

/**
 * Created by zhuran on 2018/7/4 0004
 */
public class IO {
    public static void main(String[] args) throws IOException {
        OutputStream out = new FileOutputStream("F:\\cicd\\src\\main\\resources\\config.txt");
        String s = "Hello world";
        byte[] a = s.getBytes();
        out.write(a);
        out.close();
        InputStream in = new FileInputStream("F:\\cicd\\src\\main\\resources\\config.txt");
        byte[] b = new byte[in.available()];
        in.read(b);
        in.close();
        for(byte c : b){
            System.out.print((char)c);
        }
    }
}
