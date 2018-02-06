package club.longyi.filter;

import javax.servlet.*;
import java.io.IOException;

public class CaracterEncodingFilter implements Filter {

	private String charset = "UTF-8";
	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {

		arg0.setCharacterEncoding(charset);
		arg1.setCharacterEncoding(charset);
		arg2.doFilter(arg0, arg1);
		
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String a = filterConfig.getInitParameter("encoding");
		if(a!=null) {
			this.charset = a;
		}
	}

}
