import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Before;
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
import exceptions.EventNotFinished;
public class emaitzakIpiniMockIntTest {
	@Mock
	DataAccess dataAccess;
	
	@InjectMocks
	BLFacadeImplementation sut;
	

	
	@Test
	public void test1(){
		Event ev = hasieratuEventInDB();
		try {
			Mockito.doThrow(new EventNotFinished()).when(dataAccess).EmaitzakIpini(ev.getQuestions().get(0).getQuotes().get(0));
		}catch(Exception e ) {
			assertTrue(true);
		}
		
	}
	
//	@Test
//	public void test2() {
//		Event ev = hasieratuEventInDB();
//		try {
//			
//			dataAccess.EmaitzakIpini(ev.getQuestions().get(0).getQuotes().get(1));
//			
//			Mockito.when(ev.getQuestions().get(0).getQuotes().get(1).getForecast().equals(ev.getQuestions().get(0).getResult())).thenReturn(true);
//		} catch (Exception e) {
//			fail();
//		}
//		
//		
//		
//	}
	
	private Event hasieratuEventInDB() {
		Team team1 = new Team("Hondarribia");
		Team team2 = new Team("Bidasoa");
		Event ev1 = new Event("Hondarribia-Bidasoa", UtilDate.newDate(2025, 10, 11), team1, team2);

		Sport sp1 = new Sport("Rubgi");
		sp1.addEvent(ev1);
		ev1.setSport(sp1);

		Registered reg1 = new Registered("k", "123", 1234);
		Question q1;
		q1 = ev1.addQuestion("Emaitza?", 1);
		Quote quote1 = q1.addQuote(1.3, "1", q1);
		Quote quote2 = q1.addQuote(1.3, "X", q1);
		ApustuAnitza apA1 = new ApustuAnitza(reg1, 5.0);
		Apustua ap1 = new Apustua(apA1, quote1);
		apA1.addApustua(ap1);
		quote1.addApustua(ap1);
		ap1.eguneratuApustuKant(sp1);
		//ev1.getQuestions().get(0).setResult("Hondarribia");

		return ev1;
	}
	
	
}

