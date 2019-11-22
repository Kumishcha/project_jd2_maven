package by.htp.jd2.service;

import java.util.List;

import by.htp.jd2.bean.Drink;
import by.htp.jd2.bean.DrinkInOrder;
import by.htp.jd2.bean.Order;
import by.htp.jd2.service.impl.ServiceException;
import by.htp.jd2.service.validation.ValidationServiceException;

public interface OrderService {
	
	/**
	 * 
	 * @param numberOrder as a int
	 * @return list an object of type DrinkInOrder from the database
	 * @throws ServiceException if DAOException occurs
	 */
	List<DrinkInOrder> takeDrinkInOrderList(int numberOrder) throws ServiceException;

	/**
	 * 
	 * @param userId as a int
	 * @return numberOrder
	 * @throws ServiceException if DAOException occurs
	 */
	int extractLastOrderId(int userId) throws ServiceException;
	
	/**
	 * 
	 * @param drink as a Drink
	 * @param typeOfCoffee as a String
	 * @param numberOrder as a int
	 * @return an object of type DrinkInOrder
	 * @throws ServiceException if DAOException occurs
	 */
	DrinkInOrder addDrinkToOrder(Drink drink, String typeOfCoffee, int numberOrder) throws ServiceException;
	
	/**
	 * 
	 * @param oldListDrinksInOrder as a list an object of type DrinkInOrder
	 * @param drinkInOrder as a DrinkInOrder
	 * @return a list an object of type DrinkInOrder
	 * @throws ServiceException if DAOException occurs
	 * @throws ValidationServiceException if incorrect data arrives
	 */
	List<DrinkInOrder> addToDrinkInOrderList(List<DrinkInOrder> oldListDrinksInOrder,DrinkInOrder drinkInOrder) throws ServiceException, ValidationServiceException ;
		
	/**
	 * 
	 * @param oldListDrinksInOrder as a list an object of type DrinkInOrder
	 * @param drinkId as a int
	 * @param count1 as a String
	 * @param numberOrder as a int
	 * @return a list an object of type DrinkInOrder
	 * @throws ServiceException if DAOException occurs
	 * @throws ValidationServiceException if incorrect data arrives
	 */
	List<DrinkInOrder> changeAmountOfDrink(List<DrinkInOrder> oldListDrinksInOrder, int drinkId, String count1, int numberOrder) throws ServiceException, ValidationServiceException;
			
	/**
	 * 
	 * @param oldListDrinksInOrder as a list an object of type DrinkInOrder
	 * @param drinkId as a int
	 * @param numberOrder as a int
	 * @return a list an object of type DrinkInOrder
	 * @throws ServiceException if DAOException occurs
	 */
	List<DrinkInOrder> removeDrinkFromOrder(List<DrinkInOrder> oldListDrinksInOrder, int drinkId, int numberOrder) throws ServiceException;
		
	/**
	 * 
	 * @param numberOrder as a int
	 * @return a result price
	 * @throws ServiceException if DAOException occurs
	 */
	double takePriceResult(int numberOrder)throws ServiceException;
	
	/**
	 * 
	 * @param priceResult as a double
	 * @param numberOrder as a int
	 * @return true, if the result price is filled to the database successfully
	 * @throws ServiceException if DAOException occurs
	 */
	boolean addThePriceResultInOrder(double priceResult, int numberOrder) throws ServiceException;
	
	/**
	 * 
	 * @param listDrinksInOrder as a list an object of type DrinkInOrder
	 * @param numberOrder as a int
	 * @return true, if the database is filled successfully
	 * @throws ServiceException if DAOException occurs
	 */
	boolean addListDrinksInOrderToCart(List<DrinkInOrder> listDrinksInOrder, int numberOrder) throws ServiceException;
	
	/**
	 * 
	 * @param numberOrder as a int
	 * @return new order
	 * @throws ServiceException if DAOException occurs
	 */
	Order takeOrder(int numberOrder) throws ServiceException;
	
	/**
	 * 
	 * @param userId as a int
	 * @return all user's list an object of type Order
	 * @throws ServiceException if DAOException occurs
	 */
	List<Order> takeOrdersList (int userId) throws ServiceException;
}
