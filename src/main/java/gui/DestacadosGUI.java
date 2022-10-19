package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.Registered;
import domain.Team;
import domain.User;

public class DestacadosGUI extends JFrame{
	private BLFacade businessLogic = MainGUI.getBusinessLogic();

	private static final long serialVersionUID = 1L;

	private JLabel lblTitle;
	private JFrame thisw;

	private JButton btnPopular;

	private JButton btnClose;

	private JButton btnSugerencias;
	private User user;
	private JButton btnSeguir;

	private JButton btnEquipo;
	
	private User r;

	public DestacadosGUI(User u) {
		user=u;
		r= businessLogic.findUser(user);
		getContentPane().setBackground(Color.WHITE);
		thisw=this;
		getContentPane().setLayout(null);
		this.setSize(new Dimension(1200, 350));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Destacados"));
		
		lblTitle = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Destacados"));
		lblTitle.setBackground(Color.PINK);
		lblTitle.setOpaque(true);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setBounds(0, 22, 1176, 39);
		getContentPane().add(lblTitle);
		
		btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClose.setBackground(Color.DARK_GRAY);
		btnClose.setForeground(Color.WHITE);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});
		btnClose.setBounds(38, 229, 190, 58);
		getContentPane().add(btnClose);
		
		btnPopular = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Popular"));
		btnPopular.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnPopular.setForeground(Color.DARK_GRAY);
		btnPopular.setBackground(Color.PINK);
		btnPopular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a =new PopularGUI(user);
				a.setVisible(true);
				thisw.setVisible(false);
			}
		});
		btnPopular.setBounds(38, 95, 317, 98);
		getContentPane().add(btnPopular);
		
		btnSugerencias = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Sugerencia"));
		btnSugerencias.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSugerencias.setForeground(Color.DARK_GRAY);
		btnSugerencias.setBackground(Color.PINK);
		btnSugerencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a =new SugerenciasGUI(user);
				a.setVisible(true);
				thisw.setVisible(false);
			}
		});
		btnSugerencias.setBounds(412, 95, 353, 98);
		getContentPane().add(btnSugerencias);
		
		btnEquipo = new JButton();
		btnEquipo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a=new TaldeaApostatuGUI(r);
				a.setVisible(true);
				thisw.setVisible(false);
			}
		});
		btnEquipo.setForeground(Color.DARK_GRAY);
		btnEquipo.setBackground(Color.PINK);
		btnEquipo.setBounds(828, 95, 332, 98);
		getContentPane().add(btnEquipo);
		
		btnSeguir = new JButton(); //$NON-NLS-1$ //$NON-NLS-2$
		btnSeguir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSeguir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(businessLogic.findTeam(r)==null) {
					btnEquipo.setEnabled(true);
					JFrame a =new TaldeaJarraituGUI(r);
					a.setVisible(true);
					thisw.setVisible(false);
				}else {
					btnEquipo.setEnabled(false);
					businessLogic.ezJarraituTaldea(r);
					btnEquipo.setText(ResourceBundle.getBundle("Etiquetas").getString("AukTeam"));
					btnSeguir.setText(ResourceBundle.getBundle("Etiquetas").getString("Seguir"));
					btnEquipo.setIcon(null);
					thisw.setVisible(false);
					JFrame a =new DestacadosGUI(r);
					a.setVisible(true);
				}
			}
		});
		btnSeguir.setForeground(Color.DARK_GRAY);
		btnSeguir.setBackground(Color.PINK);
		btnSeguir.setBounds(977, 229, 183, 58);
		getContentPane().add(btnSeguir);
		
		if(businessLogic.findTeam(r)==null) {
			btnSeguir.setText(ResourceBundle.getBundle("Etiquetas").getString("Seguir"));
			btnEquipo.setText(ResourceBundle.getBundle("Etiquetas").getString("AukTeam"));
			btnEquipo.setEnabled(false);
		}else {
			btnSeguir.setText(ResourceBundle.getBundle("Etiquetas").getString("NoSeguir"));
			btnEquipo.setEnabled(true);
			Team t=businessLogic.findTeam(r);
			ImageIcon imageIcon = new ImageIcon(".\\src/main/resources\\Equipos\\"+t.getIzena()+".png"); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
		    Image newimg = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // scale it the smooth way  
		    imageIcon = new ImageIcon(newimg);
	        btnEquipo.setIcon(imageIcon);
		}
	}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
