package com.example.ihsastable;

import java.util.ArrayList;

public class riderModel {

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

    private riderModel(){
        orderList = new ArrayList<Order>();
        loadOrder();
    }

    public void loadOrder(){
        orderList.add(new Order("Rider 1", "Horse A"));
        orderList.add(new Order("Rider 2", "Horse B"));
        orderList.add(new Order("Rider 3", "Horse C"));
        orderList.add(new Order("Rider 4", "Horse D"));
        orderList.add(new Order("Rider 5", "Horse E"));
        orderList.add(new Order("Rider 6", "Horse F"));
        orderList.add(new Order("Rider 7", "Horse G"));
        orderList.add(new Order("Rider 8", "Horse H"));
    }



    public ArrayList<Order> getOrderArray(){
        return orderList;
    }

    public static riderModel theModel = null;

    public static riderModel getSingleton(){
        if(theModel == null){
            theModel = new riderModel();
        }
        return theModel;
    }
}
