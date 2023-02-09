package com.example.ihsastable;

import java.util.ArrayList;

public class showModel {

    public static class Show{
        private String showname;

        public Show(String showname) {
            this.showname = showname;
        }

        public String getShow() {
            return showname;
        }

    }

    private ArrayList<Show> showList;

    private showModel (){
        showList = new ArrayList<Show>();
        loadInitialTasks();
    }

    public void loadInitialTasks() {
        Show item1 = new Show("H");
        showList.add(item1);
        Show item2 = new Show("O");
        showList.add(item2);
        Show item3 = new Show("R");
        showList.add(item3);
        Show item4 = new Show("S");
        showList.add(item4);
        Show item5 = new Show("E");
        showList.add(item5);
        Show item6 = new Show("God Country");
        showList.add(item6);

    }

    public ArrayList<Show> getTaskArray() {
        return showList;
    }

    public static showModel theModel = null;
    public static showModel getSingleton(){
        if(theModel == null){
            theModel = new showModel();
        }
        return theModel;
    }

}
