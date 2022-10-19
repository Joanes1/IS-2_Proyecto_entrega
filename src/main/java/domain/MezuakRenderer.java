package domain;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import businessLogic.BLFacade;
import gui.MainGUI;

public class MezuakRenderer extends JLabel implements ListCellRenderer<MezuakContainer>{

	private BLFacade businessLogic = MainGUI.getBusinessLogic();
	private User user; 
	 public MezuakRenderer(User u) {
		  setOpaque(true);
		  user=u;
	    }
	 
	    @Override
	    public Component getListCellRendererComponent(JList<? extends MezuakContainer> list, MezuakContainer mezua, int index,
	            boolean isSelected, boolean cellHasFocus) {
	 
	    	setText(mezua.toString());
	    	String code = "m1"; 
	    	
	    	 ImageIcon imageIcon = new ImageIcon(".\\src/main/resources\\data\\"+mezua.getMessage().isIrakurrita()+".png"); // load the image to a imageIcon
		     Image image = imageIcon.getImage(); // transform it 
		     Image newimg = image.getScaledInstance(25, 25, Image.SCALE_SMOOTH); // scale it the smooth way  
		     imageIcon = new ImageIcon(newimg);
	         setIcon(imageIcon);
	         
	           	 if (mezua.getMessage().isIrakurrita() == false && !isSelected) {
	 	             setBackground(list.getSelectionBackground().PINK);
	 	           	 setForeground(list.getSelectionForeground().BLACK);
	 	            
	 	         } else {
	 	        	 if(mezua.getMessage().isIrakurrita()) {
	 	        		setBackground(list.getBackground());
	 		            setForeground(list.getForeground());
	 	        	}
	 	        	 if(isSelected) {
	 	        		setBackground(list.getSelectionBackground().gray);
	 		            setForeground(list.getSelectionForeground().white);
	 	        	}
	 	            
	 	        }
	        return this;
	    }
	    
}
