package by.htp.jd2;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import by.htp.jd2.dao.pool.PoolConnection;
import by.htp.jd2.service.ServiceProvider;
import by.htp.jd2.service.UserService;
import by.htp.jd2.service.impl.ServiceException;
import by.htp.jd2.service.validation.ValidationServiceException;

public class TestServiceException {
	
	private static final UserService userService = ServiceProvider.getInstance().getUserService();
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
	public void exceptionСheck() throws ServiceException {

		String errorMassege = null;
		
		try {
			userService.addAmount(2, "jdtjt");

		} catch (ValidationServiceException e) {
			errorMassege = e.getMessage();
		}
		Assert.assertNotNull(errorMassege);
	}

}
