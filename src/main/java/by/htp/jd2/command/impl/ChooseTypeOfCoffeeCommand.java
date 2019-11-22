package by.htp.jd2.command.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import by.htp.jd2.bean.Account;
import by.htp.jd2.bean.User;
import by.htp.jd2.command.Command;
import by.htp.jd2.controller.JSPPageName;
import by.htp.jd2.controller.RequestParameterName;
import by.htp.jd2.service.ProductService;
import by.htp.jd2.service.ServiceProvider;
import by.htp.jd2.service.UserService;
import by.htp.jd2.service.impl.ServiceException;

public class ChooseTypeOfCoffeeCommand implements Command {

	private static final UserService userService = ServiceProvider.getInstance().getUserService();
	private static final ProductService productService = ServiceProvider.getInstance().getProductService();
	private static final Logger log = Logger.getLogger(ChooseTypeOfCoffeeCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String typeOfCoffee;
		
		double balance;
		
		User user;
		Account account;
		
		typeOfCoffee = request.getParameter(RequestParameterName.REQ_PARAM_TYPE_OF_COFFEE);
		
		HttpSession session = request.getSession(false);

		try {

			user = (User) session.getAttribute("user");
		
			if (user == null) {
				response.sendRedirect("controller?command=go-to-index-page&message=!!! Try logging again !!!");
			} else {

				if (!productService.checkQuantityInStock()) {

					response.sendRedirect(
							"controller?command=go-to-error-page&errorMessage=!!! There are technical problems, please check back later, call to support 8-029-123-45-67 !!!");
				} else {
					
					account = userService.take(user.getUserId());
					balance = account.getBalance();
										
					request.setAttribute("balance", balance);
					request.setAttribute("typeOfCoffee", typeOfCoffee);

					RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.CLIENT_AUTH_PAGE);
					dispatcher.forward(request, response);	
				}
			}

		} catch (ServiceException e) {

			log.log(Level.ERROR, "", e);
			response.sendRedirect(
					"controller?command=go-to-error-page");

		}

	}
}
