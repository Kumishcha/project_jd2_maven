package by.htp.jd2.service;

import by.htp.jd2.service.impl.DrinkServiceImpl;
import by.htp.jd2.service.impl.OrderServiceImpl;
import by.htp.jd2.service.impl.ProductServiceImpl;
import by.htp.jd2.service.impl.UserServiceImpl;

public class ServiceProvider {
	private static final ServiceProvider instance = new ServiceProvider();

	private UserService userService = new UserServiceImpl();
	private ProductService productService = new ProductServiceImpl();
	private DrinkService drinkService = new DrinkServiceImpl();
	private OrderService orderService = new OrderServiceImpl();

	private ServiceProvider() {
	}

	public static ServiceProvider getInstance() {
		return instance;
	}

	public UserService getUserService() {
		return userService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public DrinkService getDrinkService() {
		return drinkService;
	}

	public OrderService getOrderService() {
		return orderService;
	}
}
