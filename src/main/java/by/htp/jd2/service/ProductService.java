package by.htp.jd2.service;

import java.util.List;

import by.htp.jd2.bean.Product;
import by.htp.jd2.service.impl.ServiceException;
import by.htp.jd2.service.validation.ValidationServiceException;


public interface ProductService {

	List<Product> viewStock() throws ServiceException;

	boolean checkQuantityInStock() throws ServiceException;
	
	Product findProductByName(String productName) throws ServiceException;

	boolean increaseQuantityInStock(String productName, String productCount) throws ServiceException, ValidationServiceException;
	
	boolean changePriceOfProducts(String productName, String newPrice) throws ServiceException, ValidationServiceException;
	
	
}
