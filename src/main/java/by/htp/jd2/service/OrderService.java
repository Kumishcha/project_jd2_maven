package by.htp.jd2.service;

import java.util.List;

import by.htp.jd2.bean.Drink;
import by.htp.jd2.bean.DrinkInOrder;
import by.htp.jd2.bean.Order;
import by.htp.jd2.service.impl.ServiceException;
import by.htp.jd2.service.validation.ValidationServiceException;

public interface OrderService {
	
	List<DrinkInOrder> takeDrinkInOrderList(int numberOrder) throws ServiceException;

	int extractLastOrderId(int userId) throws ServiceException;
	
	DrinkInOrder addDrinkToOrder(Drink drink, String typeOfCoffee, int numberOrder) throws ServiceException;
	
	List<DrinkInOrder> addToDrinkInOrderList(List<DrinkInOrder> oldListDrinksInOrder,DrinkInOrder drinkInOrder) throws ServiceException, ValidationServiceException ;
		
	List<DrinkInOrder> changeAmountOfDrink(List<DrinkInOrder> oldListDrinksInOrder, int drinkId, String count1, int numberOrder) throws ServiceException, ValidationServiceException;
			
	List<DrinkInOrder> removeDrinkFromOrder(List<DrinkInOrder> oldListDrinksInOrder, int drinkId, int numberOrder) throws ServiceException;
		
	int takePriceResult(int numberOrder)throws ServiceException;
	
	boolean addThePriceResultInOrder(int priceResult, int numberOrder) throws ServiceException;
	
	boolean addListDrinksInOrderToCart(List<DrinkInOrder> listDrinksInOrder, int numberOrder) throws ServiceException;
	
	Order takeOrder(int numberOrder) throws ServiceException;
	
	List<Order> takeOrdersList (int userId) throws ServiceException;
}
