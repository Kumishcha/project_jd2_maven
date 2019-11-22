package by.htp.jd2.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

//import org.apache.log4j.Level;
//import org.apache.log4j.Logger;
import by.htp.jd2.bean.Account;
import by.htp.jd2.bean.User;
import by.htp.jd2.command.Command;
import by.htp.jd2.controller.JSPPageName;
import by.htp.jd2.controller.RequestParameterName;
import by.htp.jd2.service.ServiceProvider;
import by.htp.jd2.service.UserService;
import by.htp.jd2.service.impl.ServiceException;
import by.htp.jd2.service.validation.ValidationServiceException;

public class AddAmountCommand implements Command {

	private static final UserService userService = ServiceProvider.getInstance().getUserService();
	private static final Logger log = Logger.getLogger(AddAmountCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String count1;
		String incorrectCountFormat;

		double balance;

		User user;
		Account account;

		count1 = request.getParameter(RequestParameterName.REQ_PARAM_ACCOUNT_COUNT);
		
		HttpSession session = request.getSession(false);

		try {
		
			user = (User) session.getAttribute("user");
		
			if (null == user) {
				response.sendRedirect("controller?command=go-to-index-page&message=!!! Try logging again  !!!");
			} else {

				userService.addAmount(user.getUserId(), count1);

				response.sendRedirect("controller?command=go-to-client-page");
			}
		} catch (ServiceException e) {

			log.log(Level.ERROR, "???", e);
			response.sendRedirect(
					"controller?command=go-to-error-page&errorMessage=!!! Something went wrong, check back later !!!");

		} catch (ValidationServiceException e) {
					
			user = (User) session.getAttribute("user");
			
			
						
			try {

				account = userService.take(user.getUserId());
				balance = account.getBalance();

				incorrectCountFormat = "!!! Enter the correct value !!!";

				request.setAttribute("incorrectCountFormat", incorrectCountFormat);
				request.setAttribute("balance", balance);

				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.USER_ACCOUNT);
				dispatcher.forward(request, response);

			} catch (ServiceException e1) {

				log.log(Level.ERROR, "", e);
				response.sendRedirect(
						"controller?command=go-to-error-page");

			}

		}
	}

}
