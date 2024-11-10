package com.example.designpattern.multipleThread.happensBefore;

public class FinalizePrinciple {

    int x = 0;

    private void updateX (int x) {
        this.x = x;
    }

    //Finalize 在 java 9 之後已經 deprecate 了
    protected void finalize() throws Throwable {
        System.out.println("finalize 方法 x = " + x);
    }


    public static void main(String[] args) {
        FinalizePrinciple finalizePrinciple = new FinalizePrinciple();
        finalizePrinciple.updateX(10);
        finalizePrinciple = null;
        //這邊無法直接保證 gc 會立刻回收 finalizePrinciple
        System.gc();
    }

}
