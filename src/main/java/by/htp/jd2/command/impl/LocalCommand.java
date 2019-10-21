package by.htp.jd2.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.jd2.command.Command;
import by.htp.jd2.controller.JSPPageName;

public class LocalCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
	
		request.getSession(true).setAttribute("local", request.getParameter("local"));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPageName.INDEX_PAGE);
		dispatcher.forward(request, response);
	}

}
