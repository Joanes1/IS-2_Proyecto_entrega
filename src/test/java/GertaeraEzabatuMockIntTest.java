import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import businessLogic.BLFacadeImplementation;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.Sport;
import domain.Team;

@RunWith(MockitoJUnitRunner.class)
public class GertaeraEzabatuMockIntTest {

	@Mock
	DataAccess dataAcces;
	@InjectMocks
	BLFacadeImplementation sut;

	private boolean emaitza;
	private Calendar today;
	private int year;
	private int month;
	private int day;
	private Date date = dataEzarri();
	Event ev;

	@Test // evento null
	public void test1() {
		try {
			ev = null;

			ArgumentCaptor<Event> evCaptor = ArgumentCaptor.forClass(Event.class);
			Mockito.doReturn(false).when(dataAcces).gertaeraEzabatu(ev);

			emaitza = sut.gertaeraEzabatu(ev);

			assertFalse(emaitza);

			Mockito.verify(dataAcces, Mockito.times(1)).gertaeraEzabatu(evCaptor.capture());
			assertEquals(ev, evCaptor.getValue());

		} catch (Exception e) {
			fail();
		}
	}

	@Test // evento existente en la BD
	public void test2() {
		try {

			ev = hasieratuEventInDB();

			ArgumentCaptor<Event> evCaptor = ArgumentCaptor.forClass(Event.class);
			Mockito.doReturn(true).when(dataAcces).gertaeraEzabatu(ev);

			emaitza = sut.gertaeraEzabatu(ev);
			assertTrue(emaitza);

			Mockito.verify(dataAcces, Mockito.times(1)).gertaeraEzabatu(evCaptor.capture());
			assertEquals(ev, evCaptor.getValue());

		} catch (Exception e) {
			fail();
		}
	}

	@Test // evento no existente en la BD 
	public void test3() {
		try {
			ev = hasieratuEventNotInDB();

			ArgumentCaptor<Event> evCaptor = ArgumentCaptor.forClass(Event.class);
			Mockito.doReturn(false).when(dataAcces).gertaeraEzabatu(ev);

			emaitza = sut.gertaeraEzabatu(ev);
			assertFalse(emaitza);

			Mockito.verify(dataAcces, Mockito.times(1)).gertaeraEzabatu(evCaptor.capture());
			assertEquals(ev, evCaptor.getValue());

		} catch (Exception e) {
			fail();
		}
	}

	private Event hasieratuEventNotInDB() {
		Team team1 = new Team("Team1");
		Team team2 = new Team("Team2");

		Event event = new Event("Team1-Team2", date, team1, team2);
		Question q1 = event.addQuestion("Emaitza?", 1);

		return event;
	}

	private Event hasieratuEventInDB() {
		Team team1 = new Team("Hondarribia");
		Team team2 = new Team("Bidasoa");
		Event ev1 = new Event("Hondarribia-Bidasoa", date, team1, team2);

		Sport sp1 = new Sport("Rubgi");
		sp1.addEvent(ev1);
		ev1.setSport(sp1);

		Registered reg1 = new Registered("k", "123", 1234);
		Question q1;
		q1 = ev1.addQuestion("Emaitza?", 1);
		Quote quote1 = q1.addQuote(1.3, "1", q1);
		ApustuAnitza apA1 = new ApustuAnitza(reg1, 5.0);
		Apustua ap1 = new Apustua(apA1, quote1);
		apA1.addApustua(ap1);
		quote1.addApustua(ap1);
		ap1.eguneratuApustuKant(sp1);
		ev1.getQuestions().get(0).setResult("Hondarribia");

		return ev1;
	}

	private Date dataEzarri() {
		today = Calendar.getInstance();
		month = today.get(Calendar.MONTH);
		year = today.get(Calendar.YEAR);
		day = 1;
		month += 1;
		if (month == 12) {
			month = 0;
			year += 1;
		}
		return UtilDate.newDate(year, month, day);
	}
}
