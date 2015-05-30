package com.ceosilvajr.app.onlineorderingapp.objects;

/**
 * Created by ceosilvajr on 5/30/15.
 */
public class ZaloraItem {

    private String name;
    private double price;
    private String description;
    private int drawableId;

    public ZaloraItem(String name, double price, String description, int drawableId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.drawableId = drawableId;
    }

    public String getName() {
        return name;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
