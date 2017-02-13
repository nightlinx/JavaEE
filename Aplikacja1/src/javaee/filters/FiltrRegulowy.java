package javaee.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FiltrRegulowy implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		String name = arg0.getParameter("name");
		if (name != null && name.equals("jan")){
			System.out.println("dobre imie");
			arg2.doFilter(arg0, arg1);	//dzieki temu Filtr może przekazac żądania do Servletu
		}
		else{
			System.out.println("zle imie");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
