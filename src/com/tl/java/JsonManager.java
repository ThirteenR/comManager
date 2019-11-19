package com.tl.java;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Author: rsq0113
 * Date: 2019-11-19 10:14
 * Description:
 **/
public class JsonManager {
    private List<AddressList> list;

    public JsonManager(String path) {
        File file = new File(path);
        String jsonString = "";
        FileReader fileReader = null;
        BufferedReader br = null;
        try {
            fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);
            String tmp = "";
            while ((tmp = br.readLine()) != null) {
                jsonString += tmp;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<AddressList>>() {
        }.getType();
        this.list = gson.fromJson(jsonString, type);
    }

    public List<AddressList> getList() {
        return list;
    }

    public void setList(List<AddressList> list) {
        this.list = list;
    }
    public void addOne(AddressList addressList){
        this.list.add(addressList);
    }
}
