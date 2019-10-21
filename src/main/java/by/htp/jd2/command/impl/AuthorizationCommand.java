package by.htp.jd2.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import by.htp.jd2.bean.Account;
import by.htp.jd2.bean.DrinkInOrder;
import by.htp.jd2.bean.User;
import by.htp.jd2.command.Command;
import by.htp.jd2.controller.RequestParameterName;
import by.htp.jd2.service.OrderService;
import by.htp.jd2.service.ProductService;
import by.htp.jd2.service.ServiceProvider;
import by.htp.jd2.service.UserService;
import by.htp.jd2.service.impl.ServiceException;

public class AuthorizationCommand implements Command {

	private static final UserService userService = ServiceProvider.getInstance().getUserService();
	private static final OrderService orderService = ServiceProvider.getInstance().getOrderService();
	private static final ProductService productService = ServiceProvider.getInstance().getProductService();
	private static final Logger log = Logger.getLogger(AuthorizationCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
//		Enumeration<String> headerNames = request.getHeaderNames();
//				            while(headerNames.hasMoreElements()) {
//				              String headerName = headerNames.nextElement();
//				              System.out.println("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
//				            }

		String login;
		String password;
		int numberOrder;

		User user;
		Account account;
		List<DrinkInOrder> listDrinksInOrder;

		login = request.getParameter(RequestParameterName.REQ_PARAM_LOGIN);
		password = request.getParameter(RequestParameterName.REQ_PARAM_PASS);
	
		HttpSession session = request.getSession(true);
		
		try {
		
			user = userService.authorization(login, password);

			if (user != null) {

				session.setAttribute("user", user);
			
				if (user.getRole().equals("client")) {

					account = userService.take(user.getUserId());

					if (account.isBlocked()) {

						response.sendRedirect(
								"controller?command=go-to-error-page&errorMessage=!!! Account is blocked, call to support 8-029-123-45-67 !!!");

					} else if (!productService.checkQuantityInStock()) {

						response.sendRedirect(
								"controller?command=go-to-error-page&errorMessage=!!! Call to support 8-029-123-45-67 !!!");
					} else {

						numberOrder = orderService.extractLastOrderId(user.getUserId());
						listDrinksInOrder = orderService.takeDrinkInOrderList(numberOrder);

						session.setAttribute("listDrinksInOrder", listDrinksInOrder);
						session.setAttribute("numberOrder", numberOrder);

						response.sendRedirect("controller?command=go-to-client-page");

					}

				} else if (user.getRole().equals("admin")) {
					if (!productService.checkQuantityInStock()) {
						response.sendRedirect(
								"controller?command=go-to-admin-page&errorMessage=!!! Products run out !!!");
					} else {
						response.sendRedirect("controller?command=go-to-admin-page");
					}
				}

			} else {
				response.sendRedirect("controller?command=go-to-index-page&message=!!! Try logging again  !!!");
			}

		} catch (ServiceException e) {

			log.log(Level.ERROR, "", e);
			response.sendRedirect(
					"controller?command=go-to-error-page");
		}

	}
}
