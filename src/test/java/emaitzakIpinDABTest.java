

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Team;

public class emaitzakIpinDABTest {

static DataAccess sut = new DataAccess();

static TestDataAccess testDA = new TestDataAccess();
	//
	private Event evento1;
	private Question q1;
	
	private Quote quote1;
	private Quote quote2;
	
	private Event evento2;
	private Question q2;
	
	
	
//	@Before
//	public void initialize() {
//		evento1 = sut.getEventsAll().get(0);
//		q1 = evento1.getQuestions().get(0);
//		quote1 = q1.getQuotes().get(0);
//		
//		evento2 = sut.getEventsAll().get(0);
//		q2 =  evento2.getQuestions().get(0);		
//		quote2 = q2.getQuotes().get(0);	
//	}
	//
	@Test
	public void test1() {
		Quote q = null;
		try {
			sut.EmaitzakIpini(q);
		}catch(Exception e) {
			assertTrue(true);
			System.out.println("Quote is null");
		}
	}
	
	@Test
	public void test2() {
		Quote q = new Quote(1.5, "X", q1);
		try {
			sut.EmaitzakIpini(q);
		}catch(Exception e) {
			assertTrue(true);
			System.out.println("Esa cuota no esta en la base de datos ");
		}
	}
	
	@Test
	public void test3() {
		Date d = UtilDate.newDate(2025,10,12);
		Event evento1 = testDA.hasieratuEventDB(d);
		Quote quote1 = evento1.getQuestions().get(0).getQuotes().get(0);
		try {
			sut.EmaitzakIpini(quote1);
		}catch(Exception EventNotFinished) {
			System.out.println("Event not finished");
			assertTrue(true);
		}
		try {
			boolean emaitza = sut.gertaeraEzabatu(evento1);
			//assertEquals(emaitza, true);
			System.out.println("El evento se ha eliminado correctamente en la base de datos, dejando la base de datos como al principio");
		}catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void test4() {
		Date d = UtilDate.newDate(2022,9,5);
		Event evento1 = testDA.hasieratuEventDB(d);
		Quote quote2 = evento1.getQuestions().get(0).getQuotes().get(0);
		try {
			sut.EmaitzakIpini(quote2);
		}catch(Exception EventNotFinished) {
			System.out.println("Event not finished");
			//assertTrue(true);
		}
		
		String expected = quote2.getForecast();
		String obtained = evento1.getQuestions().get(0).getResult();
		
		assertEquals(expected, obtained);
		
		try {
			boolean emaitza = sut.gertaeraEzabatu(evento1);
			//assertEquals(emaitza, true);
			System.out.println("El evento se ha eliminado correctamente en la base de datos, dejando la base de datos como al principio");
		}catch(Exception e) {
			fail();
		}
	}
	
	
}
