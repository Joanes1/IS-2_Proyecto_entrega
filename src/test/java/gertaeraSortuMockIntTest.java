
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
//

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
//

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
//


import org.mockito.Mockito;
//

import org.mockito.junit.MockitoJUnitRunner;
//

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Event;
import domain.Team;
//

//
@RunWith(MockitoJUnitRunner.class)
public class gertaeraSortuMockIntTest {
	DataAccess dataAccess = Mockito.mock(DataAccess.class);
	
//
//	@InjectMocks
	BLFacade sut = new BLFacadeImplementation(dataAccess);

//

	private Team t1;
	private Team t2;
	private Event ev;
	private String dep;
	private Date fecha;
	private String desc;
//
	@Before
	public void initialize() {
		t1 = new Team("Athletic");
		t2 =new Team("Athletico");
		SimpleDateFormat dsf = new SimpleDateFormat("dd/MM/yyyy");
		fecha=null;
		try {
			fecha= dsf.parse("12/12/2022");
		}catch(ParseException e) {
			e.printStackTrace();
		}
		desc = "Atletico-Athletic";
		ev = new Event(desc,fecha,t2,t1);
		dep="Futbol";
	}
	
	@Test		//El evento se crea correctamente, los datos son correctos y la descripcion no existe en otro evento
	public void test1() {

		try {
			String desc = "Paco-Alejandro";
			Mockito.when(dataAccess.gertaerakSortu(desc, fecha, dep)).thenReturn(true);
			boolean emaitza = sut.gertaerakSortu(desc, fecha, dep);

			Vector<Event> eventos = sut.getEvents(fecha);
			boolean a = false;
			Event e = null;
			int i = 0;
			if(eventos!=null) {
				while (!a && i < eventos.size()) {
					if (eventos.get(i).getDescription() == desc) {
						e = eventos.get(i);
						a = true;
					}
					i++;
				}
			}
			sut.gertaeraEzabatu(e);
			assertEquals(emaitza, true);
		} catch (Exception e) {
			System.out.println("Lehenengo mockitok error eman du");
			fail();

		}
	}

	@Test // El metodo da error porque la fecha es anterior a hoy
	public void test2() {
		boolean b = true;
		try {
			String dep = "Futbol";
			String desc = "Levante-PSG";

			Calendar today = Calendar.getInstance();
			int month = today.get(Calendar.MONTH);
			int year = today.get(Calendar.YEAR);
			if (month == 12) {
				month = 0;
				year += 1;
			}
			fecha = UtilDate.newDate(year - 6, month, 17);

			b = sut.gertaerakSortu(desc, fecha, dep);

			Vector<Event> eventos = sut.getEvents(fecha);
			boolean a = false;
			Event evento = null;
			int i = 0;
			while (!a && i < eventos.size()) {
				if (eventos.get(i).getDescription() == desc) {
					evento = eventos.get(i);
					a = true;
				}
				i++;
			}
			sut.gertaeraEzabatu(evento);
		} catch (Exception e) {
			b = false;
		}
		assertEquals(b, false);
	}

	@Test
	public void test3() {
		boolean b = true;
		try {
			String dep = "Futbol";
			String desc = "Levante contra PSG";
			Mockito.when(dataAccess.gertaerakSortu(desc, fecha, dep)).thenReturn(false);
			b=sut.gertaerakSortu(desc, fecha, dep);

					
			
		} catch (Exception e) {
			b = true;

		}
		assertEquals(b, false);
	}
//
//	
//}
}
