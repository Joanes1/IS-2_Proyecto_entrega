
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import configuration.UtilDate;

import java.util.Date;
import java.util.Vector;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Event;
import domain.Sport;

public class gertaerakSortuDAWTest {
	// sut:system under test

	static DataAccess sut = new DataAccess();

	// additional operations needed to execute the test
	// static TestDataAccess testDA=new TestDataAccess();

	private Event ev;
	private String dep;
	private Date fecha;
	private String desc;

	@Before
	public void initialize() {
		ev = sut.getEventsAll().get(1);
		dep = ev.getSport().getIzena();
		fecha = ev.getEventDate();
		desc = ev.getDescription();
	}

	@Test
	// sut.gertaerakSortu: No existe el deporte insertado en la base de datos
	public void test1() {
		boolean emaitza = true;
		try {
			String dep1 = "Cricket";
			String desc1 = "PEPE-PACO";
			emaitza = sut.gertaerakSortu(desc1, fecha, dep1);
			assertEquals(emaitza, false);
//			Vector<Event> ebentos = sut.getEvents(fecha);
//			boolean a = false;
//			Event ebento = null;
//			int i = 0;
//			while (!a && i < ebentos.size()) {
//				if (ebentos.get(i).getDescription() == desc1) {
//					ebento = ebentos.get(i);
//					a = true;
//				}
//			}

		} catch (Exception e) {
			fail();

		}

	}

	@Test
	// sut.gertaerakSortu: Ya existe esa descripcion
	public void test2() {
		try {

			boolean emaitza = sut.gertaerakSortu(desc, fecha, dep);
			assertEquals(emaitza, false);
//			Vector<Event> ebentos = sut.getEvents(fecha);
//			boolean a = false;
//			Event ebento = null;
//			int i = 0;
//			while (!a && i < ebentos.size()) {
//				if (ebentos.get(i).getDescription() == desc) {
//					ebento = ebentos.get(i);
//					a = true;
//				}
//			}
//			sut.gertaeraEzabatu(ebento);
		} catch (Exception e) {
			System.out.println("Errorea: " + e.getMessage());
			fail();
		}

	}

	@Test
	// sut.gertaerakSortu: Todo bien
	public void test3() {
		try {

			String desc1 = "Levante-PSG";

			boolean emaitza = sut.gertaerakSortu(desc1, fecha, dep);
			assertEquals(emaitza, true);
			Vector<Event> ebentos = sut.getEvents(fecha);
			boolean a = false;
			Event ebento = null;
			int i = 0;
			while (!a && i < ebentos.size()) {
				if (ebentos.get(i).getDescription() == desc) {
					ebento = ebentos.get(i);
					a = true;
				}
			}
			sut.gertaeraEzabatu(ebento);
		} catch (Exception e) {
			fail();

		}

	}
}
