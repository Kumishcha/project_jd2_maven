package by.htp.jd2.command.impl;

import java.io.IOException;
import java.util.List;

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
import by.htp.jd2.service.ProductService;
import by.htp.jd2.service.ServiceProvider;
import by.htp.jd2.service.impl.ServiceException;

public class ViewAllStockCommand implements Command {

	private static final ProductService productService = ServiceProvider.getInstance().getProductService();
	private static final Logger log = Logger.getLogger(ViewAllStockCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		User user;
		List<Product> products;

		HttpSession session = request.getSession(false);

		try {

			user = (User) session.getAttribute("user");

			if (null == user) {
				response.sendRedirect("controller?command=go-to-index-page&message=!!! Try logging again  !!!");
			} else {
				products = productService.viewStock();

				if (products != null) {

					session.setAttribute("products", products);
				}

				request.setAttribute("products", products);

				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADMIN_VIEW_ALL_PRODUCTS);

				dispatcher.forward(request, response);// auth_user._jspService(request, response)
			}
		} catch (ServiceException e) {

			log.log(Level.ERROR, "", e);
			response.sendRedirect(
					"controller?command=go-to-error-page");

		}

	}

}
