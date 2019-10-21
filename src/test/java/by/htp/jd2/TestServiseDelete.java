package by.htp.jd2;

import java.util.List;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import by.htp.jd2.bean.DrinkInOrder;
import by.htp.jd2.dao.pool.PoolConnection;
import by.htp.jd2.service.OrderService;
import by.htp.jd2.service.ServiceProvider;
import by.htp.jd2.service.impl.ServiceException;

public class TestServiseDelete {

	private static final OrderService orderService = ServiceProvider.getInstance().getOrderService();
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
	public void remove() throws ServiceException {

		int numberOrder;
		boolean check = false;
		int originalSize;
		int actualSize;
		int expectedSize;

		List<DrinkInOrder> expected;
		List<DrinkInOrder> actual;
		List<DrinkInOrder> original;

		numberOrder = orderService.extractLastOrderId(2);

		original = orderService.takeDrinkInOrderList(numberOrder);
		originalSize = original.size();

		actual = orderService.removeDrinkFromOrder(original, 73, numberOrder);
		actualSize = actual.size();

//		if (originalSize == actualSize) {
//			Assert.assertTrue(check);
//		}

		orderService.addListDrinksInOrderToCart(actual, numberOrder);

		expected = orderService.takeDrinkInOrderList(numberOrder);
		expectedSize = expected.size();
	
		Assert.assertEquals(expectedSize, actualSize);

	}

}
