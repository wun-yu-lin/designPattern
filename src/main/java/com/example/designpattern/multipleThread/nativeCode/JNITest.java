package com.example.designpattern.multipleThread.nativeCode;

public class JNITest {

    static {
        System.loadLibrary("native");
    }

    public static void main(String[] args) {
        new JNITest().sayHello();
    }

    public native void sayHello();
}
