package by.htp.jd2.custom.tag;

import java.util.Calendar;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class MyTagHandler extends TagSupport {

	private static final long serialVersionUID = 1L;

	public int doStartTag() throws JspException {
		
		JspWriter out = pageContext.getOut();
		try {
			out.print(Calendar.getInstance().getTime());
		} catch (Exception e) {
			//log
		}
		return SKIP_BODY;
	}

}