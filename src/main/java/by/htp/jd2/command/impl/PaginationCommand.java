package by.htp.jd2.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.jd2.bean.User;
import by.htp.jd2.command.Command;
import by.htp.jd2.controller.JSPPageName;

public class PaginationCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		User user;

		HttpSession session = request.getSession(false);

		user = (User) session.getAttribute("user");

		if (null == user) {
			response.sendRedirect("controller?command=go-to-index-page&message=!!! Try logging again  !!!");

		} else if (user.getRole().equals("client")) {
			response.sendRedirect("controller?command=go-to-index-page&message=!!! Try logging again  !!!");

		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADMIN_VIEW_ALL_USERS);
			dispatcher.forward(request, response);
		}

	}

}
