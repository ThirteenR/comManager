package com.tl.java;

/**
 * Author: rsq0113
 * Date: 2019-11-19 10:30
 * Description:
 **/
public class Test {
    public static void main(String[] args) {
        ComManager comManager = new ComManager();
        comManager.query();
        comManager.commander();
        UserInterface.printQuit();
    }
}
