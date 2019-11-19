package com.tl.java;

/**
 * Author: rsq0113
 * Date: 2019-11-19 10:34
 * Description:
 **/
public class UserInterface {
    public static void printHead(){
        System.out.println("*************************************************************");
        System.out.println("*                        通讯录                              *");
        System.out.println("*************************************************************");
    }
    public static void printOne(AddressList ad){
        System.out.println("|姓名："+ad.getName()+"\t|单位："+ad.getOrg()+"\t|电话号："+ad.getPhoneNum()+"\t|Email："+ad.getEmail()+"|");
    }
    public static void printAdd(){
        System.out.println("********************添加通信录*******************");
        System.out.print("姓名：");
    }
    public static void printUpdate(){
        System.out.println("********************添加通信录*******************");
        System.out.print("修改姓名：");
    }
    public static void printDelete(){
        System.out.println("********************添加通信录*******************");
        System.out.print("删除姓名：");
    }
    public static void printOrg(){
        System.out.print("单位：");
    }
    public static void printPhoneNum(){
        System.out.print("电话：");
    }
    public static void printEmail(){
        System.out.print("邮箱：");
    }
    public static void printCommander(){
        System.out.print("选择操作1、添加；2、修改；3、删除;quit、退出：");
    }
    public static void printQuit(){
        System.out.println("***********************已退出退出通讯录***********************");
    }
    public static void printLine(){
        System.out.println("************************************************************");
    }

}
