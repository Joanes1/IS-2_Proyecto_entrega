package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import businessLogic.BLFacade;
import domain.Elkarrizketa;
import domain.ElkarrizketaContainer;
import domain.ElkarrizketaRenderer;
import domain.User;

public class SarreraOntziaGUI extends JFrame implements ListCellRenderer{
	private BLFacade businessLogic = MainGUI.getBusinessLogic();

	private static final long serialVersionUID = 1L;
	

	private JList<ElkarrizketaContainer> list;
	private DefaultListModel<ElkarrizketaContainer> elkList = new DefaultListModel<ElkarrizketaContainer>();
	private JScrollPane scrollBar;
	private JLabel lblElkarrizketak;

	private JButton btnClose;
	private JButton btnVer;
	private JButton btnRedactar;
	private User user;
	private List<ElkarrizketaContainer> elkarrizketak;
	private JFrame thisw;
	
	public SarreraOntziaGUI(User u) {
		thisw=this;
		user=u;
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(650, 300));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("SarreraOntzia"));
		
		JLabel lblZureMezuak = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ZureMezuak"));
		lblZureMezuak.setBackground(Color.PINK);
		lblZureMezuak.setOpaque(true);
		lblZureMezuak.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblZureMezuak.setHorizontalAlignment(SwingConstants.CENTER);
		lblZureMezuak.setBounds(115, 22, 378, 13);
		getContentPane().add(lblZureMezuak);
		elkarrizketak=businessLogic.elkarrizketakLortu(user);
		
		elkList.addAll(elkarrizketak);
		
		list = new JList<ElkarrizketaContainer>();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btnVer.setEnabled(true);
			}
		});
		
		list.setModel(elkList);
		list.setBounds(36, 114, 31, -26);
		getContentPane().add(list);
		
		scrollBar = new JScrollPane(list);
		scrollBar.setBounds(168, 67, 258, 140);
		getContentPane().add(scrollBar);
		
		lblElkarrizketak = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Jasotakoak")); //$NON-NLS-1$ //$NON-NLS-2$
		lblElkarrizketak.setBackground(Color.PINK);
		lblElkarrizketak.setOpaque(true);
		lblElkarrizketak.setHorizontalAlignment(SwingConstants.CENTER);
		lblElkarrizketak.setBounds(168, 44, 258, 13);
		getContentPane().add(lblElkarrizketak);
		
		btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		btnClose.setForeground(Color.WHITE);
		btnClose.setBackground(Color.DARK_GRAY);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});
		btnClose.setBounds(20, 20, 85, 21);
		getContentPane().add(btnClose);
		
		btnVer = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Ver")); //$NON-NLS-1$ //$NON-NLS-2$
		btnVer.setForeground(Color.DARK_GRAY);
		btnVer.setBackground(Color.PINK);
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new MezuakIkusiGUI(((ElkarrizketaContainer)list.getSelectedValue()).getElka(), user);
				list.removeAll();
				elkList.removeAllElements();
				elkarrizketak=businessLogic.elkarrizketakLortu(user);
				elkList.addAll(elkarrizketak);
				list.setCellRenderer((ListCellRenderer<? super ElkarrizketaContainer>) new ElkarrizketaRenderer(user));
				btnVer.setEnabled(false);
				a.setVisible(true);
				thisw.setVisible(false);
			}
		});
		btnVer.setBounds(226, 218, 151, 21);
		getContentPane().add(btnVer);
		
		btnRedactar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Redactar")); //$NON-NLS-1$ //$NON-NLS-2$
		btnRedactar.setBackground(Color.PINK);
		btnRedactar.setForeground(Color.DARK_GRAY);
		btnRedactar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new MezuakBidaliGUI(user, null, null);
				a.setVisible(true);
				thisw.setVisible(false);
			}
		});
		btnRedactar.setBounds(515, 20, 85, 21);
		getContentPane().add(btnRedactar);
		
		
		if(list.getSelectedValue()==null) {
			btnVer.setEnabled(false);
		}
		if(elkList!=null) {
			list.setCellRenderer((ListCellRenderer<? super ElkarrizketaContainer>) new ElkarrizketaRenderer(user));
		}
	}
	
	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		// TODO Auto-generated method stub
		return null;
	}
}
