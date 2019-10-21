package by.htp.jd2;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import by.htp.jd2.bean.RegistrationInfo;
import by.htp.jd2.bean.User;
import by.htp.jd2.dao.DaoProvider;
import by.htp.jd2.dao.UserDAO;
import by.htp.jd2.dao.impl.DAOException;
import by.htp.jd2.dao.pool.PoolConnection;

public class TestDAOCreate {

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
	public void addNewUser() throws DAOException {

		User user;
		String password = "xxx";
		String newPassword;
		
		newPassword = password + "1";

		RegistrationInfo info = new RegistrationInfo("Ihar", "Akinfeev", "ihar@gmail.com", "zzz", newPassword);
    
		user = userDao.registration(info);

		Assert.assertEquals(user.getName(), info.getName());

	}

}
