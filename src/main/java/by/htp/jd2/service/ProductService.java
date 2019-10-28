package by.htp.jd2.service;

import java.util.List;

import by.htp.jd2.bean.Product;
import by.htp.jd2.service.impl.ServiceException;
import by.htp.jd2.service.validation.ValidationServiceException;


public interface ProductService {

	/**
	 * 
	 * @return all list an object of type Product
	 * @throws ServiceException if DAOException occurs
	 */
	List<Product> viewStock() throws ServiceException;

	/**
	 * 
	 * @return true, if there are enough products in the stock
	 * @throws ServiceException if DAOException occurs
	 */
	boolean checkQuantityInStock() throws ServiceException;
	
	/**
	 * 
	 * @param productName as a String
	 * @return an object of type Product
	 * @throws ServiceException if DAOException occurs
	 */
	Product findProductByName(String productName) throws ServiceException;

	/**
	 * 
	 * @param productName as a String
	 * @param productCount as a String
	 * @return true, if product quantity has been changed
	 * @throws ServiceException if DAOException occurs
	 * @throws ValidationServiceException if incorrect data arrives
	 */
	boolean increaseQuantityInStock(String productName, String productCount) throws ServiceException, ValidationServiceException;
	
	/**
	 * 
	 * @param productName as a String
	 * @param newPrice as a String
	 * @return true, if price has been changed
	 * @throws ServiceException if DAOException occurs
	 * @throws ValidationServiceException if incorrect data arrives
	 */
	boolean changePriceOfProducts(String productName, String newPrice) throws ServiceException, ValidationServiceException;
	
	
}
