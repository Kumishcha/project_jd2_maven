package by.htp.jd2.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import by.htp.jd2.bean.Account;
import by.htp.jd2.bean.DrinkInOrder;
import by.htp.jd2.bean.User;
import by.htp.jd2.service.OrderService;
import by.htp.jd2.service.ProductService;
import by.htp.jd2.service.ServiceProvider;
import by.htp.jd2.service.UserService;
import by.htp.jd2.service.impl.ServiceException;

public class SessionListener implements HttpSessionListener {

	private static final OrderService orderService = ServiceProvider.getInstance().getOrderService();
	private static final UserService userService = ServiceProvider.getInstance().getUserService();
	private static final ProductService productService = ServiceProvider.getInstance().getProductService();
	private static final Logger log = Logger.getLogger(SessionListener.class);

	public void sessionCreated(HttpSessionEvent se) {
		log.debug("sessionCreated");
		//info("sessionCreated");
	}

	@SuppressWarnings("unchecked")
	public void sessionDestroyed(HttpSessionEvent se) {

		List<DrinkInOrder> listDrinksInOrder;
		User user;
		Account account;

		int numberOrder;

		HttpSession session = se.getSession();

		user = (User) session.getAttribute("user");

		if (user != null && user.getRole().equals("client")) {

			try {
				account = userService.take(user.getUserId());

				if (!account.isBlocked()) {

					if (productService.checkQuantityInStock()) {

						listDrinksInOrder = (List<DrinkInOrder>) session.getAttribute("listDrinksInOrder");
						numberOrder = (int) session.getAttribute("numberOrder");

						if (null != listDrinksInOrder) {

							orderService.addListDrinksInOrderToCart(listDrinksInOrder, numberOrder);

						}
					}
				}
			} catch (ServiceException e) {

				log.log(Level.ERROR, "", e);
			}

		}
		log.info("sessionDestroyed");
	}

}
