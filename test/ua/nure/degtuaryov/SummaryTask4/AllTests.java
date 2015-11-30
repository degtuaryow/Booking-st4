package ua.nure.degtuaryov.SummaryTask4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ua.nure.degtuaryov.SummaryTask4.db.BenefitTest;
import ua.nure.degtuaryov.SummaryTask4.db.ConstantsTest;
import ua.nure.degtuaryov.SummaryTask4.db.FieldsTest;
import ua.nure.degtuaryov.SummaryTask4.db.RoleTest;
import ua.nure.degtuaryov.SummaryTask4.db.SeatTest;
import ua.nure.degtuaryov.SummaryTask4.db.bean.BookingBeanTest;
import ua.nure.degtuaryov.SummaryTask4.db.bean.RouteStationsBeanTest;
import ua.nure.degtuaryov.SummaryTask4.db.bean.VoyageBeanTest;
import ua.nure.degtuaryov.SummaryTask4.db.dao.derby.DerbyClientDAOTest;
import ua.nure.degtuaryov.SummaryTask4.db.entity.BookingTest;
import ua.nure.degtuaryov.SummaryTask4.db.entity.ClientTest;
import ua.nure.degtuaryov.SummaryTask4.db.entity.EntityTest;
import ua.nure.degtuaryov.SummaryTask4.db.entity.RouteTest;
import ua.nure.degtuaryov.SummaryTask4.db.entity.StationTest;
import ua.nure.degtuaryov.SummaryTask4.db.entity.TrainTest;
import ua.nure.degtuaryov.SummaryTask4.db.entity.TripTest;
import ua.nure.degtuaryov.SummaryTask4.db.entity.VoyageTest;
import ua.nure.degtuaryov.SummaryTask4.utils.PasswordTest;
import ua.nure.degtuaryov.SummaryTask4.web.GetStationsAPITest;
import ua.nure.degtuaryov.SummaryTask4.web.PathTest;
import ua.nure.degtuaryov.SummaryTask4.web.command.CommandContainerTest;
import ua.nure.degtuaryov.SummaryTask4.web.command.ViewDeleteClientPageCommandTest;
import ua.nure.degtuaryov.SummaryTask4.web.filter.CommandAccessFilterTest;
import ua.nure.degtuaryov.SummaryTask4.web.filter.EncodingFilterTest;
import ua.nure.degtuaryov.SummaryTask4.web.listener.ContextListenerTest;

@RunWith(Suite.class)
@SuiteClasses({ BookingTest.class, ClientTest.class, EntityTest.class, RouteTest.class, StationTest.class,
		TrainTest.class, TripTest.class, VoyageTest.class, BenefitTest.class, ConstantsTest.class, FieldsTest.class,
		RoleTest.class, SeatTest.class, ContextListenerTest.class, CommandAccessFilterTest.class,
		EncodingFilterTest.class, PasswordTest.class, BookingBeanTest.class, RouteStationsBeanTest.class,
		PathTest.class, GetStationsAPITest.class, VoyageBeanTest.class, ConstantsTest.class,
		ViewDeleteClientPageCommandTest.class, CommandContainerTest.class })
public class AllTests {

}
