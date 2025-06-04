package com.java.cntn;

import java.util.List;

import com.java.cntn.controller.RestaurantController;
import com.java.cntn.model.Restaurant;

public class MyResource {
public static void main(String[] args) {
	List<Restaurant> resLists= new RestaurantController().showRestaurant();
	for (Restaurant restaurant : resLists) {
		System.out.println(restaurant);
	}
}
   
}
