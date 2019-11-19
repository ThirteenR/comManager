package com.tl.java;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Author: rsq0113
 * Date: 2019-11-19 9:46
 * Description:
 **/
public class ComManager {
    private static final String DATA_PATH = "./Resource/data.json";
    private JsonManager jsonManager;

    public ComManager() {
        this.jsonManager = new JsonManager(DATA_PATH);
    }

    public ComManager(JsonManager jsonManager) {
        this.jsonManager = jsonManager;
    }

    public void query() {
        UserInterface.printHead();
        for (AddressList al : jsonManager.getList()) {
            UserInterface.printOne(al);
        }
    }

    public AddressList query(Name name) {
        UserInterface.printHead();
        for (AddressList al : jsonManager.getList()) {
            if (al.getName().equals(name.getName()))
                return al;
        }
        return null;
    }

    public AddressList query(PhoneNum phoneNum) {
        UserInterface.printHead();
        for (AddressList al : jsonManager.getList()) {
            if (al.getPhoneNum().equals(phoneNum.getPhoneNum()))
                return al;
        }
        return null;
    }

    public void toAdd() {
        UserInterface.printAdd();
        Scanner scanner = new Scanner(System.in);
        String content = "";
        int i = 0;
        while (scanner.hasNextLine()) {
            content += scanner.next() + "#";
            if (i == 0) {
                UserInterface.printOrg();
            } else if (i == 1) {
                UserInterface.printPhoneNum();
            } else if (i == 2) {
                UserInterface.printEmail();
            } else {
                break;
            }
            i++;
        }
        add(content);
        System.out.println("添加了一条记录：" + content);
        UserInterface.printLine();
    }

    public void add(String content) {
        String[] split = content.split("#");
        AddressList addressList = new AddressList();
        addressList.setName(split[0]);
        addressList.setOrg(split[1]);
        addressList.setPhoneNum(split[2]);
        addressList.setEmail(split[3]);
        this.jsonManager.addOne(addressList);
        commit();
    }

    public void commit() {
        List<AddressList> list = jsonManager.getList();
        Gson gson = new Gson();
        String s = gson.toJson(list);
        try {
            FileUtils.write(new File(DATA_PATH), s, "UTF-8", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void commander() {
        Scanner scanner = new Scanner(System.in);
        UserInterface.printCommander();
        while (scanner.hasNextLine()) {
            String next = scanner.next();
            switch (next) {
                case "1": {
                    this.toAdd();
                    break;
                }
                case "2": {
                    this.toUpdate();
                    break;
                }
                case "3": {
                    this.toDelete();
                    break;
                }
                case "quit": {
                    return;
                }
                default: {
                    this.toQuery(scanner,next);
                    break;
                }
            }
            UserInterface.printCommander();
        }
    }

    private void toQuery(Scanner sc,String next1) {
        System.out.print("姓名查询(N)电话查询(P)N/P：");
        AddressList query = null;
          while (sc.hasNextLine()){
              String next = sc.next();
              if(next.equals("N")){
                  query = query(new Name(next1));
              }else if(next.equals("P")){
                  query = query(new PhoneNum(next1));
              }else {
                  System.out.println("未知命令："+next);
              }
              if (query==null){
                  System.out.println("没有相关内容");
              }else {
                  UserInterface.printOne(query);
              }
              UserInterface.printLine();
              break;
          }
    }

    private void toDelete() {
        UserInterface.printDelete();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            Name name = new Name(scanner.next());
            delete(name);
            break;
        }
        UserInterface.printLine();
    }

    private void delete(Name name) {
        List<AddressList> list = jsonManager.getList();
        for (AddressList al : list) {
            if (al.getName().equals(name.getName())) {
                list.remove(al);
                break;
            }
        }
        this.commit();
    }

    private void toUpdate() {
        UserInterface.printUpdate();
        Scanner scanner = new Scanner(System.in);
        String nam = "";
        String phoneNu = "";
        AddressList query = null;
        int i = 0;
        while (scanner.hasNextLine()) {
            if (i == 0) {
                Name name = new Name(scanner.next());
                query = query(name);
                nam = query.getName();
                phoneNu = query.getPhoneNum();
                System.out.print("姓名 " + nam + " 修改为：");
            } else if (i == 1) {
                query.setName(scanner.next());
                System.out.print("电话 " + phoneNu + " 修改为：");
            } else if (i == 2) {
                query.setPhoneNum(scanner.next());
            } else {
                break;
            }
            i++;
        }
        this.commit();
        UserInterface.printLine();
    }
}
