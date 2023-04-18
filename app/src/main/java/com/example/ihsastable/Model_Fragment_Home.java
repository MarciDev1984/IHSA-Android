package com.example.ihsastable;

import java.util.ArrayList;

/*
 * This is Show_Schedule_Model
 *
 * Author: Jacob Pinkman
 */

public class Model_Fragment_Home
{
    public static class Show
    {
        private final String showName;
        public Show(String showName)
        {
            this.showName = showName;
        }
        public String getShow()
        {
            return showName;
        }
    }

    private ArrayList<Show> showList;

    private Model_Fragment_Home()
    {
        showList = new ArrayList<Show>();
        loadInitialTasks();
    }

    public void loadInitialTasks() {
        Show item1 = new Show("Butler Equestrian");
        Show item2 = new Show("Albion College Hunt Seat Show I");
        Show item3 = new Show("Albion College Hunt Seat SHow II");
        Show item4 = new Show("St. Andrews Hunter Seat Show");
        Show item5 = new Show("University of Louisville Hunt Seat Saturday Show");
        Show item6 = new Show("Vassar College Qualifying Show ");
        Show item7 = new Show("Scranton IHSA Show");
        Show item8 = new Show("Sewanee Fall Show Saturday");
        Show item9 = new Show("Iowa State Western Show");
        Show item10 = new Show("Alfred University IHSA Western Horse Show 1");
        Show item11 = new Show("Purdue Fall Season Starter Show 1");
        Show item12 = new Show("Alfred University IHSA Western Horse Show II");
        Show item13 = new Show("College of William & Mary");
        Show item14 = new Show("Sewanee Fall Show Sunday");
        Show item15 = new Show("Iowa State Western Show");
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

    public static Model_Fragment_Home theModel = null;
    public static Model_Fragment_Home getSingleton()
    {
        if(theModel == null)
        {
            theModel = new Model_Fragment_Home();
        }
        return theModel;
    }
}
