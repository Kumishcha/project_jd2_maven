package by.htp.jd2.service.impl;

import by.htp.jd2.bean.Drink;
import by.htp.jd2.dao.DaoProvider;
import by.htp.jd2.dao.DrinkDAO;
import by.htp.jd2.dao.impl.DAOException;
import by.htp.jd2.dao.pool.PoolConnection;
import by.htp.jd2.service.DrinkService;
import by.htp.jd2.service.validation.UserDataValidator;
import by.htp.jd2.service.validation.ValidationServiceException;

public class DrinkServiceImpl implements DrinkService {

	private static final UserDataValidator validator = UserDataValidator.getInstance();
	private static final DrinkDAO drinkDAO = DaoProvider.getInstance().getDrinkDAO();


	@Override
	public Drink findDrink(int fortress, String typeOfCoffee, int volumeOfDrink, int countOfSugar, String typeOfSyrup)
			throws ServiceException, ValidationServiceException {

		Drink drink;
		
		if (!validator.check(typeOfCoffee, volumeOfDrink)) {

			throw new ValidationServiceException();
		}

		try {
			drink = drinkDAO.find(fortress, typeOfCoffee, volumeOfDrink, countOfSugar, typeOfSyrup);

			if (drink == null) {
				throw new ServiceException();
			}

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return drink;
	}

	@Override
	public int drinkPrice(Drink drink) throws ServiceException {

		int drinkId;
		int price;

		try {

			drinkId = drink.getDrinkId();
			price = drinkDAO.drinkPrice(drinkId);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return price;
	}

}
