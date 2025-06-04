package com.java.cntn.dao;

import java.util.List;

import com.java.cntn.model.Menu;
import com.java.cntn.model.Restaurant;

public interface RestaurantDao {
	
	List<Restaurant> showRestaurantDao();
	List<Menu> showMenuDao(int restaurantId);

}