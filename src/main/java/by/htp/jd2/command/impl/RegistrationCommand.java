 package by.htp.jd2.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import by.htp.jd2.bean.DrinkInOrder;
import by.htp.jd2.bean.User;
import by.htp.jd2.command.Command;
import by.htp.jd2.controller.JSPPageName;
import by.htp.jd2.controller.RequestParameterName;
import by.htp.jd2.service.OrderService;
import by.htp.jd2.service.ServiceProvider;
import by.htp.jd2.service.UserService;
import by.htp.jd2.service.impl.ServiceException;
import by.htp.jd2.service.validation.ValidationServiceException;

public class RegistrationCommand implements Command {

	private static final UserService userService = ServiceProvider.getInstance().getUserService();
	private static final OrderService orderService = ServiceProvider.getInstance().getOrderService();
	private static final Logger log = Logger.getLogger(RegistrationCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String name;
		String surname;
		String email;
		String login;
		String password;
		String password2;
		String messageWrongPassword;
		String messageLoginAlreadyExists;
		String messageErrorEmailAndPasswordFormat;

		name = request.getParameter(RequestParameterName.REQ_PARAM_NAME);
		surname = request.getParameter(RequestParameterName.REQ_PARAM_SURNAME);
		email = request.getParameter(RequestParameterName.REQ_PARAM_EMAIL);
		login = request.getParameter(RequestParameterName.REQ_PARAM_LOGIN);
		password = request.getParameter(RequestParameterName.REQ_PARAM_PASS);
		password2 = request.getParameter(RequestParameterName.REQ_PARAM_PASS2);

		User user;
		List<DrinkInOrder> listDrinksInOrder;
		int numberOrder = 0;

		try {
			user = userService.findUserByLogin(login);

			if (null != user) {

				messageLoginAlreadyExists = "!!! Such login already exists !!!";

				request.setAttribute("messageLoginAlreadyExists", messageLoginAlreadyExists);

				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.REGISTRATION_PAGE);
				dispatcher.forward(request, response);

			} else if (!password.equals(password2)) {

				messageWrongPassword = "!!! Password entered incorrectly !!!";

				request.setAttribute("messageWrongPassword", messageWrongPassword);

				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.REGISTRATION_PAGE);
				dispatcher.forward(request, response);

			} else {

				user = userService.registration(name, surname, email, login, password);
				HttpSession session = request.getSession(true);
				if (user != null) {
					
					session.setAttribute("user", user);
				}

				if (user.getRole().equals("client")) {
					numberOrder = orderService.extractLastOrderId(user.getUserId());
					listDrinksInOrder = new ArrayList<>();
					
					session.setAttribute("listDrinksInOrder", listDrinksInOrder);
					session.setAttribute("numberOrder", numberOrder);
				}

				response.sendRedirect("controller?command=go-to-client-page" + "&message=Congratulations, you are successfully registered!");

			}
		} catch (ServiceException e) {

			log.log(Level.ERROR, "", e);
			response.sendRedirect(
					"controller?command=go-to-error-page");

		} catch (ValidationServiceException e) {

			messageErrorEmailAndPasswordFormat = "!!! Incorrect email or password format !!!";

			request.setAttribute("messageErrorEmailAndPasswordFormat", messageErrorEmailAndPasswordFormat);

			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.REGISTRATION_PAGE);
			dispatcher.forward(request, response);
		}
	}

}
