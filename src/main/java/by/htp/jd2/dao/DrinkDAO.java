package by.htp.jd2.dao;

import by.htp.jd2.bean.Drink;
import by.htp.jd2.dao.impl.DAOException;

public interface DrinkDAO {

	Drink find(int fortress, String typeOfCoffee, int volumeOfDrink, int countOfSugar, String typeOfSyrup)
			throws DAOException;
	
	int drinkPrice(int drinkId) throws DAOException;
	
}
