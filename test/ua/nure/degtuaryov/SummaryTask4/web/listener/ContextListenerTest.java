package ua.nure.degtuaryov.SummaryTask4.web.listener;

import javax.servlet.ServletContextEvent;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

public class ContextListenerTest {

	static ContextListener  cl;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cl = new ContextListener();
	}

	@Test
	public void testContextDestroyed() {
		
		ServletContextEvent contextEvent = Mockito.mock(ServletContextEvent.class);
		cl.contextDestroyed(contextEvent);
	}

}
