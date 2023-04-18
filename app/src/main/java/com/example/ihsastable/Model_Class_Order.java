package com.example.ihsastable;

import java.util.ArrayList;

public class Model_Class_Order {

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

    private Model_Class_Order(){
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

    public static Model_Class_Order theModel = null;

    public static Model_Class_Order getSingleton(){
        if(theModel == null){
            theModel = new Model_Class_Order();
        }
        return theModel;
    }
}
