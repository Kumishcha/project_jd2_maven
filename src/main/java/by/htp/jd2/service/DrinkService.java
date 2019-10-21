package by.htp.jd2.service;

import by.htp.jd2.bean.Drink;
import by.htp.jd2.service.impl.ServiceException;
import by.htp.jd2.service.validation.ValidationServiceException;


public interface DrinkService {

	Drink findDrink(int fortress, String typeOfCoffee, int volumeOfDrink, int countOfSugar, String typeOfSyrup)
			throws ServiceException, ValidationServiceException;

	int drinkPrice(Drink drink) throws ServiceException;

}
