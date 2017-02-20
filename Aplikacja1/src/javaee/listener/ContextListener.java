package javaee.listener;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Koniec aplikacji: " + new Date());
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Start aplikacji: " + new Date());
		// dynamiczna decyzja o zmianie servletu. W tym miejscu można dodać
		// jakis warunek
		Dynamic servlet = arg0.getServletContext().addServlet("Lista", "javaee.servlets.ListaUzytkownikowServlet");
		servlet.addMapping("/users");
		// a dla Filtru : arg0.getServletContext().getListener("");
	}

}
