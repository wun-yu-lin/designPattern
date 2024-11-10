package com.example.designpattern.multipleThread.happensBefore;

public class FinalizePrinciple2 implements AutoCloseable{

    private int x;

    private void updateX (int x) {
        this.x = x;
    }
    @Override
    public void close() throws Exception {
        System.out.println("AutoClose 方法 x = " + x);
    }


    public static void main(String[] args) {
        try (FinalizePrinciple2 finalizePrinciple = new FinalizePrinciple2()) {
            finalizePrinciple.updateX(10);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
