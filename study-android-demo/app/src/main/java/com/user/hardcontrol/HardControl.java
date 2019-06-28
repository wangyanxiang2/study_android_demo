package com.user.hardcontrol;

public class HardControl{
    public static native int ledCtl(int which,int status);
    public static native int ledOpen();
    public static native  void ledClose();

    static{
        try {
            System.loadLibrary("hardcontrol");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}