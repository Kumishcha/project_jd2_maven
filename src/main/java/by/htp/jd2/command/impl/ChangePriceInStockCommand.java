package by.htp.jd2.command.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import by.htp.jd2.bean.User;
import by.htp.jd2.command.Command;
import by.htp.jd2.controller.RequestParameterName;
import by.htp.jd2.service.ProductService;
import by.htp.jd2.service.ServiceProvider;
import by.htp.jd2.service.impl.ServiceException;
import by.htp.jd2.service.validation.ValidationServiceException;

public class ChangePriceInStockCommand implements Command {

	private static final ProductService productService = ServiceProvider.getInstance().getProductService();
	private static final Logger log = Logger.getLogger(ChangePriceInStockCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String productName;
		String newPrice;

		User user;

		productName = request.getParameter(RequestParameterName.REQ_PARAM_PRODUCT_NAME);
		newPrice = request.getParameter(RequestParameterName.REQ_PARAM_NEW_PRICE);

		HttpSession session = request.getSession(false);

		try {

			user = (User) session.getAttribute("user");

			if (null == user) {

				response.sendRedirect("controller?command=go-to-index-page&message=!!! Try logging again  !!!");

			} else {

				productService.changePriceOfProducts(productName, newPrice);
				response.sendRedirect("controller?command=go-to-product-details-page&productName=" + productName);
			}
		} catch (ServiceException e) {
			
			log.log(Level.ERROR, "", e);
			response.sendRedirect(
					"controller?command=go-to-error-page");
			
		} catch (ValidationServiceException e) {

			user = (User) session.getAttribute("user");

			if (null == user) {
				
				response.sendRedirect("controller?command=go-to-index-page&message=!!! Try logging again  !!!");
			} else {
				
				response.sendRedirect("controller?command=go-to-product-details-page&productName=" + productName
						+ "&message=!!! Incorrect values entered, try again !!!");
			}
		}

	}

}
