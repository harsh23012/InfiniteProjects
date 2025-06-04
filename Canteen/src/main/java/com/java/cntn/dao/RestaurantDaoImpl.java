package com.java.cntn.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.java.cntn.model.Menu;
import com.java.cntn.model.Restaurant;
import com.java.cntn.util.SessionHelper;

public class RestaurantDaoImpl implements RestaurantDao{
	SessionFactory sf;
	Session session;
	@Override
	public List<Restaurant> showRestaurantDao() {
		sf = SessionHelper.getConnection();
		session = sf.openSession();
		Query query = session.createQuery("FROM Restaurant");
		List<Restaurant> restaurantList = query.list();
		
		return restaurantList;
	}
	
	@Override
	public List<Menu> showMenuDao(int restaurantId) {
		sf = SessionHelper.getConnection();
		session = sf.openSession();
		Query query = session.createQuery("FROM Menu m where m.meuId = "+ restaurantId);
		List<Menu> menuList = query.list();
		
		return menuList;
	}

}
