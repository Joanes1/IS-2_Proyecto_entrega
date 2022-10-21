
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import configuration.ConfigXML;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Event;
import domain.KirolEstatistikak;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.Sport;
import domain.Team;

public class TestDataAccess {
	protected EntityManager db;
	protected EntityManagerFactory emf;

	ConfigXML c = ConfigXML.getInstance();

	public TestDataAccess() {

		System.out.println("Creating TestDataAccess instance");

		open();

	}

	public void open() {

		System.out.println("Opening TestDataAccess instance ");

		String fileName = c.getDbFilename();

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":" + c.getDatabasePort() + "/" + fileName, properties);

			db = emf.createEntityManager();
		}

	}

	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}

	public void addSport(String spo) {
		System.out.println(">> DataAccessTest: addSport");
		db.getTransaction().begin();
		Sport a = new Sport(spo);
		db.persist(a);
		db.getTransaction().commit();
	}

	public boolean removeEvent(Event ev) {
		System.out.println(">> DataAccessTest: removeEvent");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e != null) {
			db.getTransaction().begin();
			db.remove(e);
			db.getTransaction().commit();
			return true;
		} else
			return false;
	}

	public Event addEventWithQuestion(String desc, Date d, String question, float qty) {
		System.out.println(">> DataAccessTest: addEvent");
		Event ev = null;
		db.getTransaction().begin();
		try {
			String[] taldeak = desc.split("-");
			ev = new Event(desc, d, new Team(taldeak[0]), new Team(taldeak[1]));
			ev.addQuestion(question, qty);
			db.persist(ev);
			db.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ev;
	}

	public boolean existQuestion(Event ev, Question q) {
		System.out.println(">> DataAccessTest: existQuestion");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e != null) {
			return e.DoesQuestionExists(q.getQuestion());
		} else
			return false;

	}

	// PRUEBAK EGITEKO DIRA,
	// AGIAN BORRATZEKO------------------------------------------------------
	Team team1;
	Team team2;
	Event ev1;
	Question q1;
	Quote quote1;
	Sport sp1;
	Registered reg1;
	ApustuAnitza apA1;
	Apustua ap1;
	KirolEstatistikak ke1;

	public Event hasieratuEventDB(Date date) {
		System.out.println(">> DataAccessTest: addEvent");
		Event ev = null;
		String desc = "Hondarribia-Bidasoa";
		String question = "Emaitza?";
		int qty = 1;
		db.getTransaction().begin();
		try {
			String[] taldeak = desc.split("-");
			ev = new Event(desc, date, new Team(taldeak[0]), new Team(taldeak[1]));
			Question q = ev.addQuestion(question, qty);
			Quote quo = q.addQuote(2.0, "fore1", q);
			q.setResult(quo.getForecast());
			db.persist(ev);
			db.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ev;
	}
}
