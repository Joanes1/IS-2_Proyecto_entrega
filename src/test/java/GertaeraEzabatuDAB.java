
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Event;
import domain.Team;

public class GertaeraEzabatuDAB {

	// sut:system under test
	static DataAccess sut = new DataAccess();

	// additional operations needed to execute the test
	static TestDataAccess testDA = new TestDataAccess();

	private Event ev;
	private boolean emaitza;
	Calendar today = Calendar.getInstance();
	int month = today.get(Calendar.MONTH);
	int year = today.get(Calendar.YEAR);
	private Date date;

	@Test // evento null
	public void test1() {
		assertThrows(Exception.class, () -> sut.gertaeraEzabatu(null));
	}

	@Test // evento existente en la BD
	public void test2() {
		ev = sut.getEventsAll().get(1);
		emaitza = sut.gertaeraEzabatu(ev);
		assertEquals(emaitza, true);
	}

	@Test // evento no existente en la BD
	public void test3() {
		Team team1 = new Team("Atletico");
		Team team2 = new Team("Federer");
		month += 1;
		if (month == 12) {
			month = 0;
			year += 1;
		}
		date = UtilDate.newDate(year + 30, month, 17);
		ev = new Event(1, "Atletico-Federer", date, team1, team2);;
		emaitza = sut.gertaeraEzabatu(ev);
		assertEquals(emaitza, false);
	}

}
