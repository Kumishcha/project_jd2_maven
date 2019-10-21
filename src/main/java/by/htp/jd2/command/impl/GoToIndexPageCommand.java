package by.htp.jd2.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import by.htp.jd2.command.Command;
import by.htp.jd2.controller.JSPPageName;
import by.htp.jd2.controller.RequestParameterName;

public class GoToIndexPageCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String message;

		message = request.getParameter(RequestParameterName.REQ_PARAM_MESSAGE);

		HttpSession session = request.getSession(false);
		
		session.invalidate();

		request.setAttribute("signOutMessage", message);
	
		RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.INDEX_PAGE);
		dispatcher.forward(request, response);
		
		
	}

}
