package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.Transaction;
import domain.User;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MugimenduakBistaratuGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private User user;
	private JLabel lblMugimenduakBistaratu;
	private DefaultListModel<Transaction> apustuaEgin = new DefaultListModel<Transaction>();
	private BLFacade businessLogic = MainGUI.getBusinessLogic();
	
	private JList<Transaction> list;

	private JScrollPane scrollBar;

	private JScrollPane scrollBar_1;

	private JList<Transaction> list_1;
	private DefaultListModel<Transaction> apustuaEzabatu = new DefaultListModel<Transaction>();
	private JList<Transaction> list_2;
	private DefaultListModel<Transaction> diruaSartu = new DefaultListModel<Transaction>();
	private JList<Transaction> list_3;
	private DefaultListModel<Transaction> apustuaIrabazi = new DefaultListModel<Transaction>();
	private JScrollPane scrollBar_2;
	private JScrollPane scrollBar_3;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;

	private JLabel lblNewLabel;
	private JButton btnNewButton;
	
	public MugimenduakBistaratuGUI(User u) {
		user = u; 
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void jbInit() throws Exception {
		
		
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(3000, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MugimenduakBistaratu"));
		
		lblMugimenduakBistaratu = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Mugimenduak"));
		lblMugimenduakBistaratu.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblMugimenduakBistaratu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMugimenduakBistaratu.setBounds(662, 10, 242, 57);
		getContentPane().add(lblMugimenduakBistaratu);
		
		list = new JList<Transaction>();
		list.setModel(diruaSartu);
		list.setBounds(0, 0, 419, 150);
		getContentPane().add(list);
		
		list_1 = new JList();
		list_1.setModel(apustuaEgin);
		list_1.setBounds(645, 217, 173, -100);
		getContentPane().add(list_1);
		
		list_2 = new JList();
		list_2.setModel(apustuaEzabatu);
		list_2.setBounds(1063, 101, 1, 1);
		getContentPane().add(list_2);
		
		list_3 = new JList();
		list_3.setModel(apustuaIrabazi);
		list_3.setBounds(1063, 101, 1, 1);
		getContentPane().add(list_3);
		
		for(Transaction l : businessLogic.findTransakzioak(user)) {
			if(l.getMota().compareTo("DiruaSartu")==0) {
				diruaSartu.addElement(l);
			}else if(l.getMota().compareTo("ApustuaEgin")==0){
				apustuaEgin.addElement(l);
			}else if(l.getMota().compareTo("ApustuaEzabatu")==0){
				apustuaEzabatu.addElement(l);
			}else if(l.getMota().compareTo("ApustuaIrabazi")==0) {
				apustuaIrabazi.addElement(l);
			}
		}
		list.setBackground(Color.green);
		list_1.setBackground(Color.red);
		list_2.setBackground(Color.green);
		list_3.setBackground(Color.green);
		scrollBar = new JScrollPane(list);
		scrollBar.setBounds(26, 89, 340, 150);
		getContentPane().add(scrollBar);
		
		
		
		scrollBar_1 = new JScrollPane(list_1);
		scrollBar_1.setBounds(397, 89, 338, 150);
		getContentPane().add(scrollBar_1);
		
		
		
		scrollBar_2 = new JScrollPane(list_2);
		scrollBar_2.setBounds(767, 89, 338, 150);
		getContentPane().add(scrollBar_2);
		
		scrollBar_3 = new JScrollPane(list_3);
		scrollBar_3.setBounds(1141, 89, 347, 150);
		getContentPane().add(scrollBar_3);
		
		lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SarDirMug"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(27, 74, 339, 13);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ApEginMug"));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(397, 77, 338, 13);
		getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ApEzabMug"));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(767, 74, 338, 13);
		getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Saldoa"));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(585, 358, 132, 13);
		getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel(businessLogic.saldoaBistaratu(user));
		lblNewLabel_4.setBounds(719, 359, 102, 13);
		getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ApIrabMug"));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_5.setBounds(1141, 74, 347, 13);
		getContentPane().add(lblNewLabel_5);
		
		btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});
		btnNewButton.setBounds(1342, 403, 132, 36);
		getContentPane().add(btnNewButton);
		
	}
	
	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

}
