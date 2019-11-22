package by.htp.jd2.dao;

import java.util.List;
import by.htp.jd2.bean.DrinkInOrder;
import by.htp.jd2.bean.Order;
import by.htp.jd2.dao.impl.DAOException;

public interface OrdersDAO {

	/**
	 * 
	 * @param numberOrder as a int
	 * @return list an object of type DrinkInOrder from the database
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	List<DrinkInOrder> takeDrinkInOrderList(int numberOrder) throws DAOException;

	/**
	 * 
	 * @param listDrinksInOrder as a list an object of type DrinkInOrder
	 * @param numberOrder as a int
	 * @return true, if the database is filled successfully
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	boolean addDrinkToOrderToCart(List<DrinkInOrder> listDrinksInOrder, int numberOrder) throws DAOException;

	/**
	 * 
	 * @param userId as a int 
	 * @return user's order
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	Order findOrderIdByUserId(int userId) throws DAOException;

	/**
	 * 
	 * @param userId as a int 
	 * @return numberOrder
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	int createNewOrderAndExtractLastOrderId(int userId) throws DAOException;

	/**
	 * 
	 * @param numberOrder as a int 
	 * @return  the result price
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	double calculateThePriceResult(int numberOrder) throws DAOException;

	/**
	 * 
	 * @param priceResult as a double 
	 * @param numberOrder as a int 
	 * @return true, if the result price is filled to the database successfully
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	boolean addThePriceResultInOrder(double priceResult, int numberOrder) throws DAOException;

	/**
	 * 
	 * @param numberOrder as a int 
	 * @return new order
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	Order takeOrder(int numberOrder) throws DAOException;
	
	/**
	 * 
	 * @param userId as a int 
	 * @return all user's list an object of type Order
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	List<Order> takeOrdersList (int userId) throws DAOException;
}
