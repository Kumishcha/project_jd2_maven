package by.htp.jd2.service.impl;

import java.util.List;

import by.htp.jd2.bean.Product;
import by.htp.jd2.dao.DaoProvider;
import by.htp.jd2.dao.ProductsDAO;
import by.htp.jd2.dao.impl.DAOException;
import by.htp.jd2.service.ProductService;

import by.htp.jd2.service.validation.UserDataValidator;
import by.htp.jd2.service.validation.ValidationServiceException;

public class ProductServiceImpl implements ProductService {

	private static final UserDataValidator validator = UserDataValidator.getInstance();
	private static final ProductsDAO productsDAO = DaoProvider.getInstance().getProductsDAO();

	@Override
	public List<Product> viewStock() throws ServiceException {

		List<Product> products;

		try {
			products = productsDAO.viewStock();
			
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return products;
	}
	
	@Override
	public boolean checkQuantityInStock() throws ServiceException {

		List<Product> products;
		
		try {
			
			products = productsDAO.viewStock();
			for(Product product : products) {
				if(product.getProductName().equals("coffee") && product.getQuantityInStock() < 90) {
					return false;
				}else if(product.getProductName().equals("milk") && product.getQuantityInStock() < 800) {
					return false;
				} else if(product.getProductName().equals("sugar") && product.getQuantityInStock() < 20) {
					return false;
				}else if(product.getProductName().equals("coconut syrup") && product.getQuantityInStock() < 100) {
					return false;
				}else if(product.getProductName().equals("cherry syrup") && product.getQuantityInStock() < 100) {
					return false;
				}else if(product.getProductName().equals("water") && product.getQuantityInStock() < 1000) {
					return false;
				}
			}
			
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return true;
	}
	
	@Override
	public Product findProductByName(String productName) throws ServiceException {

		Product product;

		try {
			product = productsDAO.findProductByName(productName);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return product;
	}

	@Override
	public boolean increaseQuantityInStock(String productName, String productCount) throws ServiceException, ValidationServiceException {
		
		int count;
		
		if (!validator.checkCountOfProduct(productCount)){
		
			throw new ValidationServiceException();
		}
		
		try {
	
			count = Integer.parseInt(productCount);
			productsDAO.increaseQuantityInStock(productName, count);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return true;
	}

	@Override
	public boolean changePriceOfProducts(String productName, String newPrice) throws ServiceException, ValidationServiceException {

		int price;
		
		if (!validator.checkPrice(newPrice)){
			throw new ValidationServiceException();
		}
		try {
			price = Integer.parseInt(newPrice);
			productsDAO.changePriceOfProducts(productName, price);
			
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return true;
	}

	

	

}
