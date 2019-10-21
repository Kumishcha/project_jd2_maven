package by.htp.jd2.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import by.htp.jd2.command.Command;


public class NoSuchCommand implements Command {
	
	private static final Logger log = Logger.getLogger(NoSuchCommand.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		log.info("NoSuchCommand");
		response.sendRedirect(
				"controller?command=go-to-error-page");

	}

}
