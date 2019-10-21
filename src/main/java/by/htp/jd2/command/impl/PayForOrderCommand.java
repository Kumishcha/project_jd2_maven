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
import by.htp.jd2.bean.Order;
import by.htp.jd2.bean.User;
import by.htp.jd2.command.Command;
import by.htp.jd2.controller.JSPPageName;
import by.htp.jd2.service.OrderService;
import by.htp.jd2.service.ServiceProvider;
import by.htp.jd2.service.UserService;
import by.htp.jd2.service.impl.ServiceException;

public class PayForOrderCommand implements Command {

	private static final OrderService orderService = ServiceProvider.getInstance().getOrderService();
	private static final UserService userService = ServiceProvider.getInstance().getUserService();
	private static final Logger log = Logger.getLogger(PayForOrderCommand .class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String notEnoughMoney;
		
		int numberOrder;
		int balance;
		int priceResult;

		User user;
		Order order;
		Account account;

		HttpSession session = request.getSession(false);
		
		try {

			user = (User) session.getAttribute("user");
			numberOrder = (int) session.getAttribute("numberOrder");

			if (null == user) {
				response.sendRedirect("controller?command=go-to-index-page&message=!!! Try logging again  !!!");
			} else {

				priceResult = orderService.takePriceResult(numberOrder);
				account = userService.take(user.getUserId());
				balance = account.getBalance();

				if (balance < priceResult) {

					notEnoughMoney = "!!! Insufficient funds !!!";

					userService.orderPayment(user.getUserId(), priceResult, numberOrder);

					order = orderService.takeOrder(numberOrder);

					request.setAttribute("notEnoughMoney", notEnoughMoney);
					request.setAttribute("balance", balance);
					request.setAttribute("order", order);

					RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.PAYMENT_PAGE);
					dispatcher.forward(request, response);

				} else {

					userService.orderPayment(user.getUserId(), priceResult, numberOrder);

					order = orderService.takeOrder(numberOrder);

					request.setAttribute("balance", balance);
					request.setAttribute("numberOrder", numberOrder);
					request.setAttribute("order", order);
					
					session.removeAttribute("listDrinksInOrder");

					RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.PAYMENT_PAGE);
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
