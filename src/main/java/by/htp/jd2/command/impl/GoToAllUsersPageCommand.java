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
import by.htp.jd2.bean.User;
import by.htp.jd2.command.Command;
import by.htp.jd2.controller.JSPPageName;

import by.htp.jd2.service.ServiceProvider;
import by.htp.jd2.service.UserService;
import by.htp.jd2.service.impl.ServiceException;

public class GoToAllUsersPageCommand implements Command {

	private static final UserService userService = ServiceProvider.getInstance().getUserService();
	private static final Logger log = Logger.getLogger(GoToAllUsersPageCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		User user;
		List<InfoAboutUserAndAccount> users;

		HttpSession session = request.getSession(false);

		try {

			user = (User) session.getAttribute("user");

			if (null == user) {
				response.sendRedirect("controller?command=go-to-index-page&message=!!! Try logging again  !!!");
			} else {
				users = userService.viewAllUsers();

				for (InfoAboutUserAndAccount info : users) {
					request.setAttribute("info", info);
				}

				if (users != null) {
					request.setAttribute("users", users);
				}

				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADMIN_VIEW_ALL_USERS);
				dispatcher.forward(request, response);
			}
		} catch (ServiceException e) {

			log.log(Level.ERROR, "", e);
			response.sendRedirect(
					"controller?command=go-to-error-page");
		}
	}
}
