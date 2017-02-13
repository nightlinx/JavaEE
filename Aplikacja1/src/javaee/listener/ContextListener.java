package javaee.listener;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Koniec aplikacji: " + new Date());
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Start aplikacji: " + new Date());
	}

}
