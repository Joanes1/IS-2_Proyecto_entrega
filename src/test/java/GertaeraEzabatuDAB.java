
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import org.junit.Test;

import configuration.UtilDate;
import dataAccess.DataAccess;
import dataAccess.DiruaSartuParameter;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Event;
import domain.KirolEstatistikak;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.Sport;
import domain.Team;
import domain.User;
import exceptions.ApustuaAlreadyExist;
import exceptions.QuestionAlreadyExist;
import exceptions.QuoteAlreadyExist;

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
	private Date date = hasieratuDate();

	@Test // evento null
	public void test1() {
		assertThrows(Exception.class, () -> sut.gertaeraEzabatu(null));
	}

	@Test // evento existente en la BD
	public void test2() {
		ev = testDA.hasieratuEventDB(date);
		emaitza = sut.gertaeraEzabatu(ev);
		assertEquals(emaitza, true);
	}

	@Test // evento no existente en la BD
	public void test3() {
		ev = new Event(1000, "Team1-Team2", date, new Team("Team1"), new Team("Team2"));
		assertThrows(Exception.class, () -> sut.gertaeraEzabatu(ev));
		// gertaeraEzabatu metodoan ez da errorea tratatzen gertaera DBan ez dagoenean
	}

	private Date hasieratuDate() {
		month += 1;
		if (month == 12) {
			month = 0;
			year += 1;
		}

		year += 100; // urte berezia DBkoengandik bereizteko

		date = UtilDate.newDate(year, month, 1);
		return date;
	}
}
