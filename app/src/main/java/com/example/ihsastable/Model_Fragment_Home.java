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
        public Show(final String showName)
        {
            this.showName = showName;
        }
        public String getShow()
        {
            return this.showName;
        }
    }

    private final ArrayList<Show> showList;

    private Model_Fragment_Home()
    {
        this.showList = new ArrayList<Show>();
        this.loadInitialTasks();
    }

    public void loadInitialTasks() {
        final Show item1 = new Show("Butler Equestrian");
        final Show item2 = new Show("Albion College Hunt Seat Show I");
        final Show item3 = new Show("Albion College Hunt Seat SHow II");
        final Show item4 = new Show("St. Andrews Hunter Seat Show");
        final Show item5 = new Show("University of Louisville Hunt Seat Saturday Show");
        final Show item6 = new Show("Vassar College Qualifying Show ");
        final Show item7 = new Show("Scranton IHSA Show");
        final Show item8 = new Show("Sewanee Fall Show Saturday");
        final Show item9 = new Show("Iowa State Western Show");
        final Show item10 = new Show("Alfred University IHSA Western Horse Show 1");
        final Show item11 = new Show("Purdue Fall Season Starter Show 1");
        final Show item12 = new Show("Alfred University IHSA Western Horse Show II");
        final Show item13 = new Show("College of William & Mary");
        final Show item14 = new Show("Sewanee Fall Show Sunday");
        final Show item15 = new Show("Iowa State Western Show");
        this.showList.add(item1);
        this.showList.add(item2);
        this.showList.add(item3);
        this.showList.add(item4);
        this.showList.add(item5);
        this.showList.add(item6);
        this.showList.add(item7);
        this.showList.add(item8);
        this.showList.add(item9);
        this.showList.add(item10);
        this.showList.add(item11);
        this.showList.add(item12);
        this.showList.add(item13);
        this.showList.add(item14);
        this.showList.add(item15);
    }

    public ArrayList<Show> getTaskArray()
    {
        return this.showList;
    }

    public static Model_Fragment_Home theModel;
    public static Model_Fragment_Home getSingleton()
    {
        if(Model_Fragment_Home.theModel == null)
        {
            Model_Fragment_Home.theModel = new Model_Fragment_Home();
        }
        return Model_Fragment_Home.theModel;
    }
}
