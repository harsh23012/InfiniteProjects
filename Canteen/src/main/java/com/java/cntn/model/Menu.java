package com.java.cntn.model;

import java.io.Serializable;

public class Menu implements Serializable {
	private int menuId;
	private String itemName;
	private double price;
	private double rating;
	private Restaurant restaurant;
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public Menu(int menuId, String itemName, double price, double rating, Restaurant restaurant) {
		super();
		this.menuId = menuId;
		this.itemName = itemName;
		this.price = price;
		this.rating = rating;
		this.restaurant = restaurant;
	}
	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}
}
