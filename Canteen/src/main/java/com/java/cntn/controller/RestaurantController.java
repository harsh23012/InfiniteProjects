package com.java.cntn.controller;

import java.io.Serializable;
import java.util.List;

import com.java.cntn.dao.RestaurantDao;
import com.java.cntn.dao.RestaurantDaoImpl;
import com.java.cntn.model.Restaurant;

public class RestaurantController {
	private RestaurantDao restaurantDao;
	private Restaurant restaurant;
	public RestaurantDao getRestaurantDao() {
		return restaurantDao;
	}
	public void setRestaurantDao(RestaurantDao restaurantDao) {
		this.restaurantDao = restaurantDao;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	public List<Restaurant> showRestaurant(){
		return restaurantDao.showRestaurantDao();
	}
	

}
