
import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import domain.Team;

public class GertaeraEzabatuDAW {

	// sut:system under test
	static DataAccess sut = new DataAccess();

	// additional operations needed to execute the test 
	static TestDataAccess testDA = new TestDataAccess();

	private Event ev;
	private boolean emaitza;
	private Team team1, team2;

	Calendar today = Calendar.getInstance();
	int month = today.get(Calendar.MONTH);
	int year = today.get(Calendar.YEAR);
	private Date date;

	@Before
	public void init() {

		month += 1;
		if (month == 12) {
			month = 0;
			year += 1;
		}
		date = UtilDate.newDate(year - 6, month, 17);

		team1 = new Team("Atletico");
		team2 = new Team("Betis");
//		ev = new Event(1, "Atletico-Athletic", date, team1, team2);

//		evento = sut.findEvent(null);
//		System.console(ev);
//		sut.gertaerakSortu("Atletico-Betis", date, "Futbol");
//		ev = sut.getEvents(date).get(0);
	}

	@Test // evento sin questions
	public void test1() {
		try {
			sut.gertaerakSortu("Atletico-Betis", date, "Futbol");
			ev = sut.getEvents(date).get(0);
			emaitza = sut.gertaeraEzabatu(ev);
			assertEquals(emaitza, true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test // evento con un solo question nulo
	public void test2() {
		try {
			sut.gertaerakSortu("Atletico-Betis", date, "Futbol");
			ev = sut.getEvents(date).get(0);
			ev.addQuestion(null, 0);
			emaitza = sut.gertaeraEzabatu(ev);
			assertEquals(emaitza, false);
			testDA.removeEvent(ev);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test // evento con cualquier question nulo
	public void test3() {
		try {
			ev = sut.getEventsAll().get(0);
			ev.getQuestions();
			ev.addQuestion(null, 0);
			emaitza = sut.gertaeraEzabatu(ev);
			assertEquals(emaitza, false);
			testDA.removeEvent(ev);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	
	
	@Test // evento con apuesta
	public void test7() {
		try {
			ev = sut.getEventsAll().get(0);
			emaitza = sut.gertaeraEzabatu(ev);
			assertEquals(emaitza, true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
