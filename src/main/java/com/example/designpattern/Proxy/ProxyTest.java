package com.example.designpattern.Proxy;

public class ProxyTest {

    //抽象
    interface Subject {
        void Request();
    }

    //真实
    class RealSubject implements Subject {
        public void Request() {
            System.out.println("訪問真實方法...");
        }
    }

    //代理
    class Proxy implements Subject {
        private RealSubject realSubject;

        public void Request() {
            if (realSubject == null) {
                realSubject = new RealSubject();
            }
            preRequest();
            realSubject.Request();
            postRequest();
        }

        public void preRequest() {
            System.out.println("在訪問真實方法之前的預處理");
        }

        public void postRequest() {
            System.out.println("訪問真實主題之後的後續處理。");
        }
    }

    public static void main(String[] args) {
        ProxyTest proxyTest = new ProxyTest();
        Proxy proxy = proxyTest.new Proxy();
        proxy.Request();
    }


}
