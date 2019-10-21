package by.htp.jd2.dao;

import by.htp.jd2.dao.impl.SQLDrinkDAO;
import by.htp.jd2.dao.impl.SQLOrdersDAO;
import by.htp.jd2.dao.impl.SQLProductsDAO;
import by.htp.jd2.dao.impl.SQLUserDAO;

public class DaoProvider {
	private static final DaoProvider instance = new DaoProvider();
	
	private final UserDAO userDao = new SQLUserDAO();
	private final ProductsDAO  productsDAO  = new SQLProductsDAO();
	private final DrinkDAO drinkDAO = new SQLDrinkDAO();
	private final OrdersDAO ordersDAO = new SQLOrdersDAO();
	
	private DaoProvider() {}
	
	public static DaoProvider getInstance() {
		return instance;
	}
	
	public UserDAO getUserDao() {
		return userDao;
	}

	public ProductsDAO getProductsDAO() {
		return productsDAO;
	}
	
	public DrinkDAO getDrinkDAO() {
		return drinkDAO;
	}
	
	public OrdersDAO getOrdersDAO() {
		return ordersDAO;
	}
	
}
