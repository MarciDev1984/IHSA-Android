package com.example.ihsastable;

import java.util.ArrayList;

public class showModel
{
    public static class Show
    {
        private String showname;
        public Show(String showname)
        {
            this.showname = showname;
        }
        public String getShow()
        {
            return showname;
        }
    }

    private ArrayList<Show> showList;

    private showModel ()
    {
        showList = new ArrayList<Show>();
        loadInitialTasks();
    }

    public void loadInitialTasks() {
        Show item1 = new Show("Show 1");
        Show item2 = new Show("Show 2");
        Show item3 = new Show("Show 3");
        Show item4 = new Show("Show 4");
        Show item5 = new Show("Show 5");
        Show item6 = new Show("Show 6");
        Show item7 = new Show("Show 7");
        Show item8 = new Show("Show 8");
        Show item9 = new Show("Show 9");
        Show item10 = new Show("Show 10");
        Show item11 = new Show("Show 11");
        Show item12 = new Show("Show 12");
        Show item13 = new Show("Show 13");
        Show item14 = new Show("Show 14");
        Show item15 = new Show("Show 15");
        showList.add(item1);
        showList.add(item2);
        showList.add(item3);
        showList.add(item4);
        showList.add(item5);
        showList.add(item6);
        showList.add(item7);
        showList.add(item8);
        showList.add(item9);
        showList.add(item10);
        showList.add(item11);
        showList.add(item12);
        showList.add(item13);
        showList.add(item14);
        showList.add(item15);
    }

    public ArrayList<Show> getTaskArray()
    {
        return showList;
    }

    public static showModel theModel = null;
    public static showModel getSingleton()
    {
        if(theModel == null)
        {
            theModel = new showModel();
        }
        return theModel;
    }
}
