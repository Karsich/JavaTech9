package com.example.demo;

import java.util.concurrent.atomic.AtomicInteger;

public class ShoppingItem {
    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private Long id;
    private String name;
    private boolean purchased;

    public ShoppingItem(){
        this.id = (long)AUTO_ID.getAndIncrement();
    }

    public static void initAutoId(int id) {
        AUTO_ID.set(id);
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId(){
        return id;
    }

    public void setPurchased(boolean huh){
        purchased=huh;
    }
    public boolean getPurchased() {
        return purchased;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
}