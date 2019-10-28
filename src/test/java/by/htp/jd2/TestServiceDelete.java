package by.htp.jd2;

import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import by.htp.jd2.bean.Drink;
import by.htp.jd2.bean.DrinkInOrder;
import by.htp.jd2.bean.User;
import by.htp.jd2.dao.pool.PoolConnection;
import by.htp.jd2.service.DrinkService;
import by.htp.jd2.service.OrderService;
import by.htp.jd2.service.ServiceProvider;
import by.htp.jd2.service.impl.ServiceException;
import by.htp.jd2.service.validation.ValidationServiceException;

public class TestServiceDelete {

	private static final OrderService orderService = ServiceProvider.getInstance().getOrderService();
	private static final DrinkService drinkService = ServiceProvider.getInstance().getDrinkService();
	private static final PoolConnection pc = PoolConnection.getInstance();

	@BeforeClass
	public static void take() {
		pc.makePoolConnection();
	}

	@AfterClass
	public static void close() {
		pc.closeConnectionInPool();
	}

	@Test
	public void remove() throws ServiceException, ValidationServiceException {

		int numberOrder;
		int actualSize;
		int expectedSize;

		List<DrinkInOrder> expected;
		List<DrinkInOrder> actual;
		List<DrinkInOrder> original;
		List<DrinkInOrder> listDrinksInOrder = new ArrayList<>();
		DrinkInOrder newDrinkInOrder;
		DrinkInOrder newDrinkInOrder1;
		DrinkInOrder newDrinkInOrder2;
		Drink drink;
		Drink drink1;
		Drink drink2;

		User user = new User(3, "Ihar", "Akinfeev", "ihar@gmail.com", "client", 6);

		numberOrder = orderService.extractLastOrderId(user.getUserId());

		drink = drinkService.findDrink(1, "espresso", 50, 0, "without");
		drink1 = drinkService.findDrink(1, "cappuccino", 300, 0, "without");
		drink2 = drinkService.findDrink(1, "latte", 300, 0, "without");

		newDrinkInOrder = orderService.addDrinkToOrder(drink, "espresso", numberOrder);
		listDrinksInOrder = orderService.addToDrinkInOrderList(listDrinksInOrder, newDrinkInOrder);
		
		newDrinkInOrder1 = orderService.addDrinkToOrder(drink1, "cappuccino", numberOrder);
		listDrinksInOrder = orderService.addToDrinkInOrderList(listDrinksInOrder, newDrinkInOrder1);
		
		newDrinkInOrder2 = orderService.addDrinkToOrder(drink2, "latte", numberOrder);
		listDrinksInOrder = orderService.addToDrinkInOrderList(listDrinksInOrder, newDrinkInOrder2);
			
		orderService.addListDrinksInOrderToCart(listDrinksInOrder, numberOrder);

		original = orderService.takeDrinkInOrderList(numberOrder);
	
		actual = orderService.removeDrinkFromOrder(original, drink.getDrinkId(), numberOrder);
		actualSize = actual.size();
		
		orderService.addListDrinksInOrderToCart(actual, numberOrder);

		expected = orderService.takeDrinkInOrderList(numberOrder);
		expectedSize = expected.size();
	
		Assert.assertEquals(expectedSize, actualSize);

	}

}
