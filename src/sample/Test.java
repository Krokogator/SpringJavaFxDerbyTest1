package sample;

import com.google.gson.*;

public class Test {
    public void addSetting(String name){
        Gson gson = new Gson();
        String json = gson.toJson(new Setting("Test1"));

        System.out.println(json);

        System.out.println(gson.fromJson(json, Setting.class).name);


    }
}
