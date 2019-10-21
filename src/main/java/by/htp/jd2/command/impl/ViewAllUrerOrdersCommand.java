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

import by.htp.jd2.bean.InfoAboutUserAndAccount;
import by.htp.jd2.bean.Order;
import by.htp.jd2.bean.User;
import by.htp.jd2.command.Command;
import by.htp.jd2.controller.JSPPageName;
import by.htp.jd2.controller.RequestParameterName;
import by.htp.jd2.service.OrderService;
import by.htp.jd2.service.ServiceProvider;
import by.htp.jd2.service.UserService;
import by.htp.jd2.service.impl.ServiceException;

public class ViewAllUrerOrdersCommand implements Command {

	private static final OrderService orderService = ServiceProvider.getInstance().getOrderService();
	private static final UserService userService = ServiceProvider.getInstance().getUserService();
	private static final Logger log = Logger.getLogger(ViewAllUrerOrdersCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String id;
		String messageOrders;
		int idUser;

		User user;
		InfoAboutUserAndAccount info;
		List<Order> orders;

		id = request.getParameter(RequestParameterName.REQ_PARAM_USER_ID);
		idUser = Integer.parseInt(id);

		HttpSession session = request.getSession(false);

		try {

			user = (User) session.getAttribute("user");

			if (null == user) {
				response.sendRedirect("controller?command=go-to-index-page&message=!!! Try logging again !!!");
			} else {
				
				orders = orderService.takeOrdersList(idUser);
				
				if(orders.isEmpty()) {
					messageOrders = "This user hasn't had orders yet";
					request.setAttribute("messageOrders", messageOrders);
				}
				
				info = userService.takeInfo(idUser);

				request.setAttribute("info", info);
				request.setAttribute("orders", orders);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADMIN_USER_DETAILS);
				dispatcher.forward(request, response);
			}
		} catch (ServiceException e) {

			log.log(Level.ERROR, "", e);
			response.sendRedirect(
					"controller?command=go-to-error-page");
		}

	}

}
