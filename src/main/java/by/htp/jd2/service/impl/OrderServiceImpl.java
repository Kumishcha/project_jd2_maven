package by.htp.jd2.service.impl;

import java.util.Iterator;
import java.util.List;
import by.htp.jd2.bean.Drink;
import by.htp.jd2.bean.DrinkInOrder;
import by.htp.jd2.bean.Order;
import by.htp.jd2.dao.DaoProvider;
import by.htp.jd2.dao.DrinkDAO;
import by.htp.jd2.dao.OrdersDAO;
import by.htp.jd2.dao.impl.DAOException;
import by.htp.jd2.service.OrderService;
import by.htp.jd2.service.validation.UserDataValidator;
import by.htp.jd2.service.validation.ValidationServiceException;

public class OrderServiceImpl implements OrderService {

	private static final UserDataValidator validator = UserDataValidator.getInstance();
	private static final OrdersDAO ordersDAO = DaoProvider.getInstance().getOrdersDAO();
	private static final DrinkDAO drinkDAO = DaoProvider.getInstance().getDrinkDAO();

	@Override
	public List<DrinkInOrder> takeDrinkInOrderList(int numberOrder) throws ServiceException {

		List<DrinkInOrder> listDrinksInOrder;

		try {
			listDrinksInOrder = ordersDAO.takeDrinkInOrderList(numberOrder);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return listDrinksInOrder;
	}

	@Override
	public DrinkInOrder addDrinkToOrder(Drink drink, String typeOfCoffee, int numberOrder) throws ServiceException {

		int price;

		DrinkInOrder newDrinkInOrder = new DrinkInOrder();

		try {

			price = drinkDAO.drinkPrice(drink.getDrinkId());

			newDrinkInOrder.setDrinkId(drink.getDrinkId());
			newDrinkInOrder.setPrice(price);
			newDrinkInOrder.setTypeOfCoffee(typeOfCoffee);
			newDrinkInOrder.setCount(1);
			newDrinkInOrder.setNumberOrder(numberOrder);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return newDrinkInOrder;
	}

	@Override
	public List<DrinkInOrder> addToDrinkInOrderList(List<DrinkInOrder> oldListDrinksInOrder,
			DrinkInOrder newDrinkInOrder) throws ServiceException, ValidationServiceException {

		int maxCount = 3;

		if (oldListDrinksInOrder.isEmpty()) {
			oldListDrinksInOrder.add(newDrinkInOrder);
		} else {

			if (oldListDrinksInOrder.size() == 3) {
				throw new ValidationServiceException();
			} else {

				for (DrinkInOrder drinkInOrder : oldListDrinksInOrder) {
					if (drinkInOrder.getDrinkId() == newDrinkInOrder.getDrinkId()) {
						if (drinkInOrder.getCount() < maxCount) {
							drinkInOrder.setCount(drinkInOrder.getCount() + 1);
						} else {
							throw new ValidationServiceException();
						}
					} else {
						oldListDrinksInOrder.add(newDrinkInOrder);
						break;
					}
				}
			}
		}
		return oldListDrinksInOrder;
	}

	@Override
	public int extractLastOrderId(int userId) throws ServiceException {

		Order newOrder;
		int numberOrder;

		try {

			newOrder = ordersDAO.findOrderIdByUserId(userId);
			if (newOrder == null) {
				numberOrder = ordersDAO.createNewOrderAndExtractLastOrderId(userId);

			} else {
				numberOrder = newOrder.getNumberOrder();
			}

		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return numberOrder;
	}

	@Override
	public List<DrinkInOrder> changeAmountOfDrink(List<DrinkInOrder> listDrinksInOrder, int drinkId, String count1,
			int numberOrder) throws ServiceException, ValidationServiceException {

		int count;

		if (!validator.checkAmountOfDrink(count1)) {
			throw new ValidationServiceException("Value exceeds 3");
		}

		count = Integer.parseInt(count1);
		if (count == 0) {

			Iterator<DrinkInOrder> iter = listDrinksInOrder.iterator();

			while (iter.hasNext()) {
				if (drinkId == iter.next().getDrinkId()) {
					iter.remove();
				}
			}
		} else {
			for (DrinkInOrder drinkInOrder : listDrinksInOrder) {
				if (drinkInOrder.getDrinkId() == drinkId) {
					drinkInOrder.setCount(count);
				}
			}
		}
		return listDrinksInOrder;
	}

	@Override
	public List<DrinkInOrder> removeDrinkFromOrder(List<DrinkInOrder> listDrinksInOrder, int drinkId, int numberOrder)
			throws ServiceException {

		Iterator<DrinkInOrder> iter = listDrinksInOrder.iterator();

		while (iter.hasNext()) {
			if (drinkId == iter.next().getDrinkId()) {
				iter.remove();
			}
		}

		return listDrinksInOrder;
	}

	@Override
	public int takePriceResult(int numberOrder) throws ServiceException {

		int priceResult;

		try {
			priceResult = ordersDAO.calculateThePriceResult(numberOrder);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return priceResult;
	}

	@Override
	public boolean addThePriceResultInOrder(int priceResult, int numberOrder) throws ServiceException {

		try {

			if (!ordersDAO.addThePriceResultInOrder(priceResult, numberOrder)) {
				return false;
			}

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return true;
	}

	@Override
	public Order takeOrder(int numberOrder) throws ServiceException {

		Order newOrder;

		try {
			newOrder = ordersDAO.takeOrder(numberOrder);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return newOrder;
	}

	@Override
	public boolean addListDrinksInOrderToCart(List<DrinkInOrder> listDrinksInOrder, int numberOrder)
			throws ServiceException {

		try {

			if (!ordersDAO.addDrinkToOrderToCart(listDrinksInOrder, numberOrder)) {
				return false;
			}

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return true;
	}

	@Override
	public List<Order> takeOrdersList(int userId) throws ServiceException {

		List<Order> orders;

		try {
			orders = ordersDAO.takeOrdersList(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return orders;
	}

}
