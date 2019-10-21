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

import by.htp.jd2.bean.Account;
import by.htp.jd2.bean.DrinkInOrder;
import by.htp.jd2.bean.User;
import by.htp.jd2.command.Command;
import by.htp.jd2.controller.JSPPageName;
import by.htp.jd2.service.OrderService;

import by.htp.jd2.service.ServiceProvider;
import by.htp.jd2.service.UserService;
import by.htp.jd2.service.impl.ServiceException;

public class MakeNewOrderCommand implements Command {

	private static final UserService userService = ServiceProvider.getInstance().getUserService();
	private static final OrderService orderService = ServiceProvider.getInstance().getOrderService();
	private static final Logger log = Logger.getLogger(MakeNewOrderCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		int balance;
		int numberOrder;
		
		User user;
		Account account;
		List<DrinkInOrder> listDrinksInOrder;
		
		HttpSession session = request.getSession(false);

		try {
			
			user = (User) session.getAttribute("user");
			numberOrder = (int) session.getAttribute("numberOrder");

			if (null == user) {
				response.sendRedirect("controller?command=go-to-index-page&message=!!! Try logging again  !!!");
			} else {
				account = userService.take(user.getUserId());
				balance = account.getBalance();

				numberOrder = orderService.extractLastOrderId(user.getUserId());
				
				listDrinksInOrder = orderService.takeDrinkInOrderList(numberOrder);

				session.setAttribute("listDrinksInOrder", listDrinksInOrder);
				session.setAttribute("numberOrder", numberOrder);
				
				request.setAttribute("balance", balance);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.CLIENT_AUTH_PAGE);
				dispatcher.forward(request, response);
			}

		} catch (ServiceException e) {

			log.log(Level.ERROR, "", e);
			response.sendRedirect(
					"controller?command=go-to-error-page");
		}

	}

}
