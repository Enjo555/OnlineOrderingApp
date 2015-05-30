package com.ceosilvajr.app.onlineorderingapp.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.ceosilvajr.app.onlineorderingapp.R;
import com.ceosilvajr.app.onlineorderingapp.helpers.Constants;
import com.ceosilvajr.app.onlineorderingapp.objects.ShoppingCart;
import com.google.gson.Gson;

/**
 * Created by ceosilvajr on 5/30/15.
 */
public class ShoppingCartManager {

    public static ShoppingCart get(Context context) {
        Gson gson = new Gson();
        Resources res = context.getResources();
        SharedPreferences myPrefs = context.getSharedPreferences(
                res.getString(R.string.package_name), 0);
        String json = myPrefs.getString(Constants.SHOPPING_CART.toString(), "");
        return gson.fromJson(json, ShoppingCart.class);
    }

    public static void save(ShoppingCart shoppingCart, Context context) {
        Gson gson = new Gson();
        Resources res = context.getResources();

        SharedPreferences myPrefs = context.getSharedPreferences(
                res.getString(R.string.package_name), 0);
        SharedPreferences.Editor e = myPrefs.edit();

        String json = gson.toJson(shoppingCart);
        e.putString(Constants.SHOPPING_CART.toString(), json);
        e.apply();

    }

    public static void delete(Context context) {
        Resources res = context.getResources();
        SharedPreferences myPrefs = context.getSharedPreferences(
                res.getString(R.string.package_name), 0);
        SharedPreferences.Editor e = myPrefs.edit();
        e.remove(Constants.SHOPPING_CART.toString());
        e.apply();
    }

}
