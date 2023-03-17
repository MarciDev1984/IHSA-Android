package com.example.ihsastable;

import java.util.ArrayList;

public class Rider_Schedule_Model {

    private ArrayList<Order> orderList;

    public static class Order{
        private String thisOrder;
        private String thisHorse;

        public Order(String someOrder, String someHorse){
            this.thisOrder = someOrder;
            this.thisHorse = someHorse;
        }
        public String getOrder(){
            return thisOrder;
        }
        public String getHorse(){
            return thisHorse;
        }
    }

    private Rider_Schedule_Model(){
        orderList = new ArrayList<Order>();
        loadOrder();
    }

    public void loadOrder(){
        orderList.add(new Order("Marci Devaughn", "Chip"));
        orderList.add(new Order("Kevin Harris", "Dustin"));
        orderList.add(new Order("Fisher Reese", "Felix"));
        orderList.add(new Order("Kooper Young", "Fred"));
        orderList.add(new Order("Jacob Pickman", "Henry"));
        orderList.add(new Order("Christopher Burke", "Hooker"));
        orderList.add(new Order("Gabriel Mura", "Sanchez"));
        orderList.add(new Order("Daniel Omole", "Sheldon"));
    }



    public ArrayList<Order> getOrderArray(){
        return orderList;
    }

    public static Rider_Schedule_Model theModel = null;

    public static Rider_Schedule_Model getSingleton(){
        if(theModel == null){
            theModel = new Rider_Schedule_Model();
        }
        return theModel;
    }
}
