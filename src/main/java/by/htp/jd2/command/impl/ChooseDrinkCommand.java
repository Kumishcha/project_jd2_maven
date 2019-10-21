package by.htp.jd2.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import by.htp.jd2.bean.Drink;
import by.htp.jd2.bean.DrinkInOrder;
import by.htp.jd2.bean.User;
import by.htp.jd2.command.Command;
import by.htp.jd2.controller.RequestParameterName;
import by.htp.jd2.service.DrinkService;
import by.htp.jd2.service.OrderService;
import by.htp.jd2.service.ServiceProvider;
import by.htp.jd2.service.impl.ServiceException;
import by.htp.jd2.service.validation.ValidationServiceException;

public class ChooseDrinkCommand implements Command {

	private static final DrinkService drinkService = ServiceProvider.getInstance().getDrinkService();
	private static final OrderService orderService = ServiceProvider.getInstance().getOrderService();
	private static final Logger log = Logger.getLogger(ChooseDrinkCommand.class);

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String fortressCoffee;
		String countOfSugar1;
		String typeOfCoffee;
		String volume;
		String typeOfSyrup;

		int fortress;
		int volumeOfDrink;
		int countOfSugar;
		int numberOrder;

		User user;
		Drink drink;
		DrinkInOrder newDrinkInOrder;
		List<DrinkInOrder> listDrinksInOrder;

		fortressCoffee = request.getParameter(RequestParameterName.REQ_PARAM_FORTRESS);
		typeOfCoffee = request.getParameter(RequestParameterName.REQ_PARAM_TYPE_OF_COFFEE);
		volume = request.getParameter(RequestParameterName.REQ_PARAM_VOLUME);
		countOfSugar1 = request.getParameter(RequestParameterName.REQ_PARAM_COUNT_OF_SUGAR);
		typeOfSyrup = request.getParameter(RequestParameterName.REQ_PARAM_TYPE_OF_SYRUP);

		HttpSession session = request.getSession(false);

		try {

			if (fortressCoffee == null || typeOfCoffee == null || volume == null || countOfSugar1 == null
					|| typeOfSyrup == null) {

				user = (User) session.getAttribute("user");

				if (null == user) {
					response.sendRedirect("controller?command=go-to-index-page&message=!!! Try logging again !!!");
				} else {
					if (fortressCoffee == null) {
						fortress = 0;
					} else {
						fortress = Integer.parseInt(fortressCoffee);
					}
					if (volume == null) {
						volumeOfDrink = 0;
					} else {
						volumeOfDrink = Integer.parseInt(volume);
					}
					if (countOfSugar1 == null) {
						countOfSugar = 0;
					} else {
						countOfSugar = Integer.parseInt(countOfSugar1);
					}

					response.sendRedirect(
							"controller?command=go-to-client-page" + "&message=!!! Not enough data, try again !!!");
				}
			} else {

				fortress = Integer.parseInt(fortressCoffee);
				volumeOfDrink = Integer.parseInt(volume);
				countOfSugar = Integer.parseInt(countOfSugar1);

				user = (User) session.getAttribute("user");
				numberOrder = (int) session.getAttribute("numberOrder");

				if (null == user) {
					response.sendRedirect("controller?command=go-to-index-page");
				} else {

					drink = drinkService.findDrink(fortress, typeOfCoffee, volumeOfDrink, countOfSugar, typeOfSyrup);

					newDrinkInOrder = orderService.addDrinkToOrder(drink, typeOfCoffee, numberOrder);

					listDrinksInOrder = (List<DrinkInOrder>) session.getAttribute("listDrinksInOrder");
					
					listDrinksInOrder = orderService.addToDrinkInOrderList(listDrinksInOrder, newDrinkInOrder);

					session.setAttribute("listDrinksInOrder", listDrinksInOrder);

					response.sendRedirect("controller?command=go-to-client-page");
				}
			}
		} catch (ServiceException e) {

			log.log(Level.ERROR, "", e);
			response.sendRedirect(
					"controller?command=go-to-error-page");

		} catch (ValidationServiceException e) {

			response.sendRedirect(
					"controller?command=go-to-client-page" + "&messageCountOfDrink=!!! max 3 drink !!!");
		}
	}
}
