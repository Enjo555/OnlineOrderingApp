package com.ceosilvajr.app.onlineorderingapp.objects;

import java.util.List;

/**
 * Created by ceosilvajr on 5/30/15.
 */
public class ShoppingCart {

    private int totalItems;

    private double totalPrice;

    private List<ZaloraItem> zaloraItems;

    public ShoppingCart(List<ZaloraItem> zaloraItems) {
        this.zaloraItems = zaloraItems;
    }

    public int getTotalItems() {
        return getZaloraItems().size();
    }

    public double getTotalPrice() {

        double price = 0;

        for (ZaloraItem zaloraItem : getZaloraItems()) {
            price = price + zaloraItem.getPrice();
        }

        return price;
    }

    public List<ZaloraItem> getZaloraItems() {
        return zaloraItems;
    }
}
