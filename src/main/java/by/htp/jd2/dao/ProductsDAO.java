package by.htp.jd2.dao;

import java.util.List;

import by.htp.jd2.bean.Product;
import by.htp.jd2.dao.impl.DAOException;

public interface ProductsDAO {

	Product findProductByName(String productName) throws DAOException;

	List<Product> viewStock() throws DAOException;

	boolean increaseQuantityInStock(String productName, int count) throws DAOException;
	
	boolean changePriceOfProducts(String productName, int price) throws DAOException;

	void changeQuantityOfProducts(int count, int drinkId) throws DAOException;
	
	

}
