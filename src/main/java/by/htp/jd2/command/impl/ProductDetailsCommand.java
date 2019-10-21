package by.htp.jd2.command.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import by.htp.jd2.bean.Product;
import by.htp.jd2.bean.User;
import by.htp.jd2.command.Command;
import by.htp.jd2.controller.JSPPageName;
import by.htp.jd2.controller.RequestParameterName;
import by.htp.jd2.service.ProductService;

import by.htp.jd2.service.ServiceProvider;
import by.htp.jd2.service.impl.ServiceException;

public class ProductDetailsCommand implements Command{
	
	private static final ProductService productService = ServiceProvider.getInstance().getProductService();
	private static final Logger log = Logger.getLogger( ProductDetailsCommand.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String productName;
		
		User user;
		Product product;
		
		productName = request.getParameter(RequestParameterName.REQ_PARAM_PRODUCT_NAME);
		
		HttpSession session = request.getSession(false);
		
		try {

			user = (User) session.getAttribute("user");

			if (null == user) {
				response.sendRedirect("controller?command=go-to-index-page&message=!!! Try logging again  !!!");
			} else {
				product = productService.findProductByName(productName);

				request.setAttribute("product", product);

				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADMIN_PRODUCT_DETAILS);

				dispatcher.forward(request, response);
			}
		} catch (ServiceException e) {

			log.log(Level.ERROR, "", e);
			response.sendRedirect(
					"controller?command=go-to-error-page");
			
		}
		
	}

}
