package domain;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import businessLogic.BLFacade;
import gui.MainGUI;

public class TeamRenderer extends JLabel implements ListCellRenderer<Team>{

	private BLFacade businessLogic = MainGUI.getBusinessLogic();

	 public TeamRenderer() {
		  setOpaque(true);
	    }
	 
	    @Override
	    public Component getListCellRendererComponent(JList<? extends Team> list, Team team, int index,
	            boolean isSelected, boolean cellHasFocus) {
	 
	    	  	
	    	 ImageIcon imageIcon = new ImageIcon(".\\src/main/resources\\Equipos\\"+team.getIzena()+".png"); // load the image to a imageIcon
		     Image image = imageIcon.getImage(); // transform it 
		     Image newimg = image.getScaledInstance(90, 90, Image.SCALE_SMOOTH); // scale it the smooth way  
		     imageIcon = new ImageIcon(newimg);
	         setIcon(imageIcon);
	         setText(team.getIzena());
	         if(isSelected) {
	        		setBackground(list.getSelectionBackground().GRAY);
		            setForeground(list.getSelectionForeground().WHITE);
	        }else {
	        	setBackground(list.getSelectionBackground());
	            setForeground(list.getSelectionForeground());
	        }
	         
	        return this;
	    }
	    
}