package ua.nure.degtuaryov.SummaryTask4.web.filter;

import org.junit.BeforeClass;
import org.junit.Test;

public class CommandAccessFilterTest {
	
	static CommandAccessFilter aft;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		aft = new CommandAccessFilter();
	}

	@Test
	public void testDestroy() {
		aft.destroy();
	}
}
