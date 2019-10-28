package by.htp.jd2.dao;

import java.util.List;

import by.htp.jd2.bean.Product;
import by.htp.jd2.dao.impl.DAOException;

public interface ProductsDAO {

	/**
	 * 
	 * @param productName as a String
	 * @return an object of type Product
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	Product findProductByName(String productName) throws DAOException;

	/**
	 * 
	 * @return all list an object of type Product
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	List<Product> viewStock() throws DAOException;

	/**
	 * 
	 * @param productName as a String
	 * @param count as a int
	 * @return true, if product quantity has been changed
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	boolean increaseQuantityInStock(String productName, int count) throws DAOException;
	
	/**
	 * 
	 * @param productName as a String 
	 * @param price as a int
	 * @return true, if price has been changed
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	boolean changePriceOfProducts(String productName, int price) throws DAOException;

	/**
	 * 
	 * @param count as a int
	 * @param drinkId as a int
	 * @throws DAOException if SQLException or InterruptedException occurs
	 */
	void changeQuantityOfProducts(int count, int drinkId) throws DAOException;
	
	

}
