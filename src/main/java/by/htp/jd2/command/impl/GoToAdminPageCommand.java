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
import by.htp.jd2.controller.RequestParameterName;

public class GoToAdminPageCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		User user;
		String errorMessage;
		
		errorMessage = request.getParameter(RequestParameterName.REQ_PARAM_ERROR_MESSAGE);
		
		HttpSession session = request.getSession(false);
		user = (User) session.getAttribute("user");

		if (null == user) {
			response.sendRedirect("controller?command=go-to-index-page&message=!!! Try logging again  !!!");
		} else {
			
			request.setAttribute("errorMessage", errorMessage);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.ADMIN_AUTH_PAGE);
			dispatcher.forward(request, response);
		}

	}

}
