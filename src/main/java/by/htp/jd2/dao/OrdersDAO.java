package by.htp.jd2.dao;

import java.util.List;
import by.htp.jd2.bean.DrinkInOrder;
import by.htp.jd2.bean.Order;
import by.htp.jd2.dao.impl.DAOException;

public interface OrdersDAO {

	List<DrinkInOrder> takeDrinkInOrderList(int numberOrder) throws DAOException;

	boolean addDrinkToOrderToCart(List<DrinkInOrder> listDrinksInOrder, int numberOrder) throws DAOException;

	Order findOrderIdByUserId(int userId) throws DAOException;

	int createNewOrderAndExtractLastOrderId(int userId) throws DAOException;

	int calculateThePriceResult(int numberOrder) throws DAOException;

	boolean addThePriceResultInOrder(int priceResult, int numberOrder) throws DAOException;

	Order takeOrder(int numberOrder) throws DAOException;
	
	List<Order> takeOrdersList (int userId) throws DAOException;
}
