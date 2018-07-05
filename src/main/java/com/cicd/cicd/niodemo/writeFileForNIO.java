package com.cicd.cicd.niodemo;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zhuran on 2018/7/5 0005
 */
public class writeFileForNIO {
    public static void fileStrem(){
        try {
            FileOutputStream fos = new FileOutputStream("F:\\cicd\\src\\main\\resources\\config.txt");
            FileChannel flc = fos.getChannel();
            flc.write(ByteBuffer.wrap(" a b c ".getBytes()));
            flc.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void randomFile(){
        try {
            RandomAccessFile raf = new RandomAccessFile("F:\\cicd\\src\\main\\resources\\config.txt","rws");
            FileChannel fcl = raf.getChannel();
            fcl.position(fcl.size());
            fcl.write(ByteBuffer.wrap(" d e f ".getBytes()));
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void inputStream(){
        FileInputStream fs = null;
        FileChannel fc;
        try {
            fs = new FileInputStream("F:\\cicd\\src\\main\\resources\\config.txt");
            fc = fs.getChannel();
            ByteBuffer buff = ByteBuffer.allocate(1024);
            fc.read(buff);
            buff.flip();
            while (buff.hasRemaining()){
                System.out.print((char) buff.get());
            }
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        writeFileForNIO.fileStrem();
        writeFileForNIO.inputStream();
        writeFileForNIO.randomFile();
        writeFileForNIO.inputStream();
    }
}
