
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import configuration.UtilDate;

import java.util.Date;
import java.util.Random;
import java.util.Vector;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Event;
import domain.Sport;
import domain.Team;

public class gertaerakSortuDAWTest {
	// sut:system under test

		static DataAccess sut = new DataAccess();

		// additional operations needed to execute the test
		// static TestDataAccess testDA=new TestDataAccess();

		private Team t1;
		private Team t2;

		private String dep;
	//
		private Date fecha;
	//
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
			Event ev = new Event(desc,fecha,t2,t1);
			dep="Futbol";
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
			} catch (Exception e) {
				System.out.println("Errorea: " + e.getMessage());
				fail();
			}

		}

		@Test
		// sut.gertaerakSortu: Todo bien
		public void test3() {
			try {
				byte[] array = new byte[7]; // length is bounded by 7
			    new Random().nextBytes(array);
			    String generatedString1 = new String(array, Charset.forName("UTF-8"));
			    byte[] array2 = new byte[7]; // length is bounded by 7
			    new Random().nextBytes(array2);
			    String generatedString2 = new String(array, Charset.forName("UTF-8"));
			    
				String desc = generatedString1+"-"+generatedString2;
				boolean emaitza = sut.gertaerakSortu(desc, fecha, dep);

				Vector<Event> eventos = sut.getEvents(fecha);
				boolean a = false;
				Event e = null;
				int i = 0;
				while (!a && i < eventos.size()) {
					if (eventos.get(i).getDescription() == desc) {
						e = eventos.get(i);
						a = true;
					}
					i++;
				}
				sut.gertaeraEzabatu(e);
				assertEquals(emaitza, true);
			} catch (Exception e) {
				System.out.println("Lehenengo error eman du");
				fail();

			}

		}
}
