package by.htp.jd2.dao;

import by.htp.jd2.bean.Drink;
import by.htp.jd2.dao.impl.DAOException;

public interface DrinkDAO {

	/**
	 * 
	 * @param fortress as a int
	 * @param typeOfCoffee as a String
	 * @param volumeOfDrink as a int
	 * @param countOfSugar as a int
	 * @param typeOfSyrup as a String
	 * @return an object Drink drink
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	Drink find(int fortress, String typeOfCoffee, int volumeOfDrink, int countOfSugar, String typeOfSyrup)
			throws DAOException;
	
	/**
	 * 
	 * @param drinkId as a int
	 * @return the price of the drink
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	double drinkPrice(int drinkId) throws DAOException;
	
}
