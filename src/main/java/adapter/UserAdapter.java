package adapter;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import businessLogic.BLFacade;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Registered;
import domain.User;
import gui.MainGUI;

public class UserAdapter extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnNames = { "Event", "Question", "Event Date", "Bet(€)" };
	private Vector<ApustuAnitza> apuestas;

	public UserAdapter(User user) {
		apuestas = ((Registered) user).getApustuAnitzak();
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		int size;
		if (apuestas == null) {
			size = 0;
		} else {
			size = apuestas.size();
		}
		return size;
	}

	public Object getValueAt(int row, int col) {
		Object obj = null;
		for (int row2 = 0; row2 < apuestas.get(row).getApustuak().size(); row2++) {
			if (col == 0) {
				obj = apuestas.get(row).getApustuak().get(row2).getKuota().getQuestion().getEvent();
			} else if (col == 1) {
				obj = apuestas.get(row).getApustuak().get(row2).getKuota().getQuestion();
			} else if (col == 2) {
				obj = apuestas.get(row).getData();
			} else if (col == 3) {
				obj = apuestas.get(row).getBalioa();
			}
		}
		return obj;

	}

	public String getColumnName(int col) {
		return columnNames[col];
	}
}
