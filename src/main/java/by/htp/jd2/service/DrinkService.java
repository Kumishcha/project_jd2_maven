package by.htp.jd2.service;

import by.htp.jd2.bean.Drink;
import by.htp.jd2.service.impl.ServiceException;
import by.htp.jd2.service.validation.ValidationServiceException;


public interface DrinkService {

	/**
	 * 
	 * @param fortress as a int
	 * @param typeOfCoffee as a String 
	 * @param volumeOfDrink as a int
	 * @param countOfSugar as a int
	 * @param typeOfSyrup as a String 
	 * @return an object of type Drink
	 * @throws ServiceException if DAOException occurs
	 * @throws ValidationServiceException if incorrect data arrives
	 */
	Drink findDrink(int fortress, String typeOfCoffee, int volumeOfDrink, int countOfSugar, String typeOfSyrup)
			throws ServiceException, ValidationServiceException;

	/**
	 * 
	 * @param drink as a Drink 
	 * @return the price of the drink
	 * @throws ServiceException if DAOException occurs
	 */
	double drinkPrice(Drink drink) throws ServiceException;

}
