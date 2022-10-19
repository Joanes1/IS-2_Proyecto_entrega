package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.Admin;
import domain.Registered;
import domain.User;

public class LoginGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane= null;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JLabel izenaSartu = new JLabel(); 
	private JLabel passSartu = new JLabel(); 
	private BLFacade businessLogic = MainGUI.getBusinessLogic();
	private JFrame thisw;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton;

	public LoginGUI()
	{
		thisw=this;
		getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("LoginTitle"));
		
		txtUsername = new JTextField();
		txtUsername.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsername.setFont(new Font("Times New Roman", Font.BOLD, 20));
		//txtUsername.setText(ResourceBundle.getBundle("Etiquetas").getString("Username")); //$NON-NLS-1$ //$NON-NLS-2$
		txtUsername.setBounds(262, 134, 224, 51);
		txtUsername.setColumns(10);
		getContentPane().add(txtUsername);
		
		
		txtPassword = new JPasswordField();
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		//txtPassword.setText(ResourceBundle.getBundle("Etiquetas").getString("Password")); //$NON-NLS-1$ //$NON-NLS-2$
		txtPassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtPassword.setColumns(10);
		txtPassword.setBounds(262, 228, 224, 51);
		getContentPane().add(txtPassword);
		
		izenaSartu = new JLabel("");
		izenaSartu.setVisible(false);
		izenaSartu.setHorizontalAlignment(SwingConstants.CENTER);
		izenaSartu.setBounds(110, 111, 540, 13);
		getContentPane().add(izenaSartu);
		
		passSartu = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$
		passSartu.setVisible(false);
		passSartu.setHorizontalAlignment(SwingConstants.CENTER);
		passSartu.setBounds(262, 205, 224, 13);
		getContentPane().add(passSartu);
		
		lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		lblNewLabel.setBounds(10, 56, 666, 51);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Username"));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBounds(24, 143, 209, 30);
		getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password"));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(24, 233, 209, 40);
		getContentPane().add(lblNewLabel_2);
		
		btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String izena = txtUsername.getText().trim();
				String password = txtPassword.getText().trim();  
				
				if(izena.isEmpty()) {
					izenaSartu.setVisible(true);
					izenaSartu.setText(ResourceBundle.getBundle("Etiquetas").getString("UsernameError1"));					
				}else if(password.isEmpty()) {
					passSartu.setVisible(true);
					passSartu.setText(ResourceBundle.getBundle("Etiquetas").getString("PasswordError")); 
					
				}else{
					izenaSartu.setVisible(false);
					passSartu.setVisible(false);
					User u = businessLogic.isLogin(izena, password);
					if(u != null) {
						if(u instanceof Admin) {
							JFrame adm= new AdminGUI(u);
							adm.setVisible(true);
							thisw.setVisible(false);
						}else if(u instanceof Registered) {
							JFrame reg= new RegisteredGUI(u);
							reg.setVisible(true);
							thisw.setVisible(false);
						}
					}else { 
						izenaSartu.setVisible(true);
						izenaSartu.setText(ResourceBundle.getBundle("Etiquetas").getString("UsernameError2"));
					}
					
				}				
			}
		});
		btnNewButton.setBounds(262, 331, 182, 58);
		getContentPane().add(btnNewButton);	
	}
}
