package com.example.designpattern.base;

public class bitwiseOperator {
    public static void main(String[] args) {
        printResult(5,1,5 & 1, "5 & 1 (AND)"); // AND 同位元都為 1 → 結果才是 1

        printResult(5,1,5 | 1, "5 | 1 (OR)"); // OR 同位元有一個為1 結果是 1

        printResult(5,1, 5 ^ 1, "5 ^ 1 (XOR)"); // XOR 不同才是 1，相同是 0

        printResult(5,5, ~5, "~5"); //取反，會補碼轉換（會變成負數）

        printResult(12, 1 , 12 << 2, "12 << 2 (left shift)"); //位元向左移，乘以 2 的 n 次方

        printResult(1024, (int) Math.pow(2,10), 1024>>10, "1024 >> 10 (right shift)"); //位元向右移，除以 2 的 n 次方（保留正負號）

        printResult(1024, (int) Math.pow(2,6) , 1024>>6,  "1024 >> 5 (right shift)");

       printResult(-1024, 1, -1024 >>>2, "-1024 >>>1");  //>>>	無符號右移	-1 >>> 1 = 2147483647	不保留正負號，空位補 0（Java 特有）

    }

    public static void printResult(int org1, int org2, int res, String expression) {

        System.out.println("----------------");
        System.out.println("expression: " + expression);
        System.out.printf("org1: %4s (%d)%n", Integer.toBinaryString(org1), org1);
        System.out.printf("org2: %4s (%d)%n", Integer.toBinaryString(org2), org2);
        System.out.printf("res:  %4s (%d)%n", Integer.toBinaryString(res), res);
        System.out.println("----------------");
    }
}
