package com.kjde1033.bqgnmgt.utils;

public class Test {
        public static void main(String[] args) {
            TransferTest1 test = new TransferTest1();
            int a = 5;
            int b = 10;
            test.swap(a, b);
            System.out.println("交换结束后，变量a的值是" + a + "；变量b的值是" + b);
        }

}
 class TransferTest1 {
    public void swap(int a, int b) {
        int tmp = a;
        a = b;
        b = tmp;
        System.out.println("swap方法里，a的值是" + a + "；b的值是" + b);
    }
}
