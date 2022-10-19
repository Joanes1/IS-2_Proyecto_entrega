package gui;

import java.awt.Color;
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
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import businessLogic.BLFacade;
import domain.Elkarrizketa;
import domain.Message;
import domain.MezuakContainer;
import domain.MezuakRenderer;
import domain.User;

public class MezuakIkusiGUI extends JFrame{
	private BLFacade businessLogic = MainGUI.getBusinessLogic();

	private static final long serialVersionUID = 1L;

	private JLabel lblZureMezua;

	private JLabel lblIgorlea;

	private JLabel lblAsunto;
	private JLabel txtIgorlea;
	private JLabel txtAsunto;
	private JLabel txtTestua;

	private JButton btnClose;

	private JButton btnResponder;
	private Elkarrizketa elkarrizketa;
	private User user;

	private JScrollPane scrollBar;
	private DefaultListModel<MezuakContainer> elkarrizketaLista = new DefaultListModel<MezuakContainer>();

	private JList<MezuakContainer> list;
	
	private List<MezuakContainer> mezuak;
	
	private JFrame thisw;

	private JButton btnIrakurri;
	
	private MezuakContainer mezua;
	
	public MezuakIkusiGUI(Elkarrizketa elk, User u) {
		thisw=this;
		elkarrizketa=businessLogic.findElkarrizketa(elk);
		user=u;
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(500, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MezuakIkusi"));
		
		lblZureMezua = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ZureMezuak"));
		lblZureMezua.setBackground(Color.PINK);
		lblZureMezua.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblZureMezua.setOpaque(true);
		lblZureMezua.setHorizontalAlignment(SwingConstants.CENTER);
		lblZureMezua.setBounds(145, 23, 158, 19);
		getContentPane().add(lblZureMezua);
		
		lblIgorlea = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Igorlea"));
		lblIgorlea.setHorizontalAlignment(SwingConstants.CENTER);
		lblIgorlea.setBackground(Color.PINK);
		lblIgorlea.setOpaque(true);
		lblIgorlea.setBounds(10, 185, 65, 13);
		getContentPane().add(lblIgorlea);
		
		lblAsunto = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Asunto"));
		lblAsunto.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsunto.setOpaque(true);
		lblAsunto.setBackground(Color.PINK);
		lblAsunto.setBounds(10, 214, 65, 13);
		getContentPane().add(lblAsunto);
		
		txtIgorlea = new JLabel();
		txtIgorlea.setBackground(Color.WHITE);
		txtIgorlea.setOpaque(true);
		txtIgorlea.setText(""); 
		txtIgorlea.setBounds(83, 185, 354, 19);
		getContentPane().add(txtIgorlea);
		
		txtAsunto = new JLabel();
		txtAsunto.setBackground(Color.WHITE);
		txtAsunto.setOpaque(true);
		txtAsunto.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		txtAsunto.setBounds(83, 211, 354, 19);
		getContentPane().add(txtAsunto);

		
		txtTestua = new JLabel();
		txtTestua.setBackground(Color.WHITE);
		txtTestua.setOpaque(true);
		txtTestua.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		txtTestua.setBounds(72, 240, 354, 129);
		getContentPane().add(txtTestua);

		
		btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		btnClose.setForeground(Color.WHITE);
		btnClose.setBackground(Color.DARK_GRAY);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
				JFrame a = new SarreraOntziaGUI(u);
				a.setVisible(true);
			}
		});
		btnClose.setBounds(377, 432, 85, 21);
		getContentPane().add(btnClose);
		
		btnIrakurri = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Ver"));
		btnIrakurri.setForeground(Color.DARK_GRAY);
		btnIrakurri.setBackground(Color.PINK);
		btnResponder = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Responder"));
		btnResponder.setBackground(Color.PINK);
		btnResponder.setForeground(Color.DARK_GRAY);
		btnResponder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new MezuakBidaliGUI(user, ((MezuakContainer)list.getSelectedValue()).getMessage().getElkarrizketa(), ((MezuakContainer)list.getSelectedValue()).getMessage().getIgorlea());
				a.setVisible(true);
				thisw.setVisible(false);
			}
		});
		btnResponder.setBounds(10, 432, 85, 21);
		getContentPane().add(btnResponder);
				
		list = new JList<MezuakContainer>();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				mezua = (MezuakContainer)list.getSelectedValue();
				if(mezua!=null && mezua.getMessage().getIgorlea().equals(user)) {
					btnResponder.setEnabled(false);
				}else {
					btnResponder.setEnabled(true);
				}
				btnIrakurri.setEnabled(true);
			}
		});
		
		btnResponder.setEnabled(false);
		btnIrakurri.setEnabled(false);
		list.setBounds(10, 53, 466, 33);
		getContentPane().add(list);
		
		scrollBar = new JScrollPane(list);
		scrollBar.setBounds(57, 46, 380, 129);
		getContentPane().add(scrollBar);
		
		mezuak = businessLogic.mezuakLortu(elkarrizketa);
		elkarrizketaLista.addAll(mezuak);
		list.setModel(elkarrizketaLista);
		
		list.setCellRenderer(new MezuakRenderer(user));
		
		btnIrakurri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtIgorlea.setText(((MezuakContainer)list.getSelectedValue()).getMessage().getIgorlea().getUsername());
				txtAsunto.setText(elkarrizketa.getTitulo());
				txtTestua.setText(((MezuakContainer)list.getSelectedValue()).getMessage().getTestua());
				if(mezua.getMessage()!=null) {
					if(!mezua.getMessage().getIgorlea().getUsername().equals(user.getUsername())) {
						businessLogic.mezuaIkusita(mezua.getMessage());
						list.removeAll();
						elkarrizketaLista.removeAllElements();
						elkarrizketa=businessLogic.findElkarrizketa(elkarrizketa);
						mezuak = businessLogic.mezuakLortu(elkarrizketa);
						elkarrizketaLista.addAll(mezuak);
						list.setCellRenderer(new MezuakRenderer(user));
					}
				}
				btnResponder.setEnabled(false);
			}
		});
		btnIrakurri.setBounds(171, 432, 144, 21);
		getContentPane().add(btnIrakurri);
	}
	
	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
