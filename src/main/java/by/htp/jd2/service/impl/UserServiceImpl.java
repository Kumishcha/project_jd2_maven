package by.htp.jd2.service.impl;

import java.util.List;

import by.htp.jd2.bean.Account;
import by.htp.jd2.bean.DrinkInOrder;
import by.htp.jd2.bean.InfoAboutUserAndAccount;
import by.htp.jd2.bean.RegistrationInfo;
import by.htp.jd2.bean.User;
import by.htp.jd2.dao.DaoProvider;
import by.htp.jd2.dao.OrdersDAO;
import by.htp.jd2.dao.ProductsDAO;
import by.htp.jd2.dao.UserDAO;
import by.htp.jd2.dao.impl.DAOException;

import by.htp.jd2.service.UserService;
import by.htp.jd2.service.validation.UserDataValidator;
import by.htp.jd2.service.validation.ValidationServiceException;

public class UserServiceImpl implements UserService {

	private static final UserDataValidator validator = UserDataValidator.getInstance();
	private static final UserDAO userDao = DaoProvider.getInstance().getUserDao();
	private static final OrdersDAO ordersDAO = DaoProvider.getInstance().getOrdersDAO();
	private static final ProductsDAO productsDAO = DaoProvider.getInstance().getProductsDAO();

	@Override
	public User findUserByLogin(String login) throws ServiceException {

		User user;
		try {
			user = userDao.findUserByLogin(login);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return user;
	}

	@Override
	public User authorization(String login, String password) throws ServiceException {

		User user;
		String newPassword;

		try {
			newPassword = hashPassword(password);
			user = userDao.authorization(login, newPassword);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return user;
	}

	@Override
	public User registration(String name, String surname, String email, String login, String password)
			throws ServiceException, ValidationServiceException {

		String newPassword;
		User user;

		if (!validator.check(email, password)) {

			throw new ValidationServiceException();

		}

		newPassword = hashPassword(password);
		RegistrationInfo info = new RegistrationInfo(name, surname, email, login, newPassword);

		try {

			user = userDao.registration(info);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return user;
	}

	@Override
	public InfoAboutUserAndAccount takeInfo(int idUser) throws ServiceException {

		InfoAboutUserAndAccount info;

		try {
			info = userDao.takeInfo(idUser);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return info;
	}

	@Override
	public List<InfoAboutUserAndAccount> viewAllUsers() throws ServiceException {

		List<InfoAboutUserAndAccount> users;

		try {
			users = userDao.viewAllUsers();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return users;
	}

	@Override
	public boolean changeRole(int idUser) throws ServiceException {

		try {
			return userDao.changeRole(idUser);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean isBlockedAccount(int idUser) throws ServiceException {
		try {
			return userDao.isBlockedAccount(idUser);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean isUnblockedAccount(int idUser) throws ServiceException {
		try {
			return userDao.isUnblockedAccount(idUser);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Account take(int idUser) throws ServiceException {
		Account account;

		try {
			account = userDao.take(idUser);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return account;
	}

	@Override
	public Account addAmount(int idUser, String count1) throws ServiceException, ValidationServiceException {

		if (!validator.check(count1)) {
			throw new ValidationServiceException("ValidationServiceException");
		}

		Account account;
		int count;

		try {
			count = Integer.parseInt(count1);
			account = userDao.addAmount(idUser, count);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return account;
	}

	@Override
	public boolean orderPayment(int idUser, int priceResult, int numberOrder) throws ServiceException {

		boolean add;
		List<DrinkInOrder> listDrinksInOrder;

		try {

			add = userDao.orderPayment(idUser, priceResult, numberOrder);
			if (add == false) {
				return false;
			}
			listDrinksInOrder = ordersDAO.takeDrinkInOrderList(numberOrder);

			for (DrinkInOrder drinkInOrder : listDrinksInOrder) {

				productsDAO.changeQuantityOfProducts(drinkInOrder.getCount(), drinkInOrder.getDrinkId());

			}

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return true;
	}

	private String hashPassword(String password) {

		String newPassword = password + "1";
		return newPassword;

	}

}
