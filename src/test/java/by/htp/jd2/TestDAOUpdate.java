package by.htp.jd2;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import by.htp.jd2.bean.User;
import by.htp.jd2.dao.DaoProvider;
import by.htp.jd2.dao.UserDAO;
import by.htp.jd2.dao.impl.DAOException;
import by.htp.jd2.dao.pool.PoolConnection;

public class TestDAOUpdate {
	
	private static final UserDAO userDao = DaoProvider.getInstance().getUserDao();
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
	public void changeRole() throws DAOException {

		User user;
		boolean expected;
		
		user = new User(3, "Маша", "Попова", "m@gmail.com", "client", 2);

		expected = userDao.changeRole(user.getUserId());
		
		Assert.assertTrue(expected);

	}

}
