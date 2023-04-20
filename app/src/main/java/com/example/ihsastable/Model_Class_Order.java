package com.example.ihsastable;

import java.util.ArrayList;

public class Model_Class_Order {

    private final ArrayList<Order> orderList;

    public static class Order{
        private final String thisOrder;
        private final String thisHorse;

        public Order(final String someOrder, final String someHorse){
            thisOrder = someOrder;
            thisHorse = someHorse;
        }
        public String getOrder(){
            return this.thisOrder;
        }
        public String getHorse(){
            return this.thisHorse;
        }
    }

    private Model_Class_Order(){
        this.orderList = new ArrayList<Order>();
        this.loadOrder();
    }

    public void loadOrder(){
        this.orderList.add(new Order("Marci Devaughn", "Chip"));
        this.orderList.add(new Order("Kevin Harris", "Dustin"));
        this.orderList.add(new Order("Fisher Reese", "Felix"));
        this.orderList.add(new Order("Kooper Young", "Fred"));
        this.orderList.add(new Order("Jacob Pickman", "Henry"));
        this.orderList.add(new Order("Christopher Burke", "Hooker"));
        this.orderList.add(new Order("Gabriel Mura", "Sanchez"));
        this.orderList.add(new Order("Daniel Omole", "Sheldon"));
    }



    public ArrayList<Order> getOrderArray(){
        return this.orderList;
    }

    public static Model_Class_Order theModel;

    public static Model_Class_Order getSingleton(){
        if(Model_Class_Order.theModel == null){
            Model_Class_Order.theModel = new Model_Class_Order();
        }
        return Model_Class_Order.theModel;
    }
}
