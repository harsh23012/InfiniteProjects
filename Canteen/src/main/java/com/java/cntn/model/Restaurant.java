package com.java.cntn.model;

import java.io.Serializable;
import java.util.List;

public class Restaurant implements Serializable {
	
	private int restaurantId;
	private String restaurantName;
	private String city;
	private double rating;
	private FoodType foodType;
	private List<Menu> menuList;
	
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public FoodType getFoodType() {
		return foodType;
	}
	public void setFoodType(FoodType flag) {
		this.foodType = flag;
	}
	public List<Menu> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName + ", city=" + city
				+ ", rating=" + rating + ", FoodType=" + foodType + ", menuList=" + menuList + "]";
	}
	public Restaurant(int restaurantId, String restaurantName, String city, double rating, FoodType foodType,
			List<Menu> menuList) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.city = city;
		this.rating = rating;
		this.foodType = foodType;
		this.menuList = menuList;
	}
	public Restaurant() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
