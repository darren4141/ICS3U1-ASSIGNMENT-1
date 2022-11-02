import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class ExtensionApplet extends Applet implements ActionListener{
	static String pressedMessage = "";
	static Color green = new Color(0, 255, 153);

	public void init(){
		resize(1000, 5000);
		setBackground(green);
		menu = new Button("Menu");
		add(menu);
		menu.addActionListener(this);
		order = new Button("Order");
		add(order);
		order.addActionListener(this);		
		exit = new Button("Exit");
		add(exit);
		exit.addActionListener(this);
		
		oil = new Button("Oil");
		thinLiquid = new Button("Thin Liquid");
		thickLiquid = new Button("Thick Liquid");
		oil.addActionListener(this);		
		thinLiquid.addActionListener(this);		
		thickLiquid.addActionListener(this);		

		add(oil);
		add(thinLiquid);
		add(thickLiquid);
		
		makeTransparent(oil);
		makeTransparent(thinLiquid);
		makeTransparent(thickLiquid);

		
	}
	
	public void makeTransparent(Button button){
		
		button.setBackground(green);
		button.setForeground(green);
	}
	
	public void makeVisible(Button button){
		
		button.setBackground(Color.lightGray);
		button.setForeground(Color.black);
	}
	
	final String SHOPNAME = "Darren's delicious bubble tea";
	
	public void paint(Graphics g){
		Color black = new Color(0,0,0);
		g.setColor(black);
		g.drawString(SHOPNAME, 40, 40);
		g.drawString(pressedMessage, 40, 80);


	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == menu){
			menuPressed();
			
		}
		if(e.getSource() == order){
			orderPressed();
		}
		if(e.getSource() == exit){
			exitPressed();
		}
		
		if(e.getSource() == oil){
			oilPressed();
			System.out.println("Oil button pressed");
		}		
		
		if(e.getSource() == thinLiquid){
			thinLiquidPressed();
		}		
		
		if(e.getSource() == thickLiquid){
			thickLiquidPressed();
		}
		
	}
	
	public void menuPressed(){
		pressedMessage = "You chose to see the menu";
		repaint();
		makeTransparent(oil);
		makeTransparent(thinLiquid);
		makeTransparent(thickLiquid);
	}


	public void orderPressed(){
		pressedMessage = "What base would you like?";
		makeVisible(oil);
		makeVisible(thinLiquid);
		makeVisible(thickLiquid);
		repaint();
	}
	
	public void exitPressed(){
		pressedMessage = "You chose to exit";
		repaint();
		makeTransparent(oil);
		makeTransparent(thinLiquid);
		makeTransparent(thickLiquid);
	}
	
	public void oilPressed(){
		pressedMessage = "What type of oil would you like?";
		repaint();
	}
	public void thinLiquidPressed(){
		pressedMessage = "What type of thin liquid would you like?";
		repaint();

	}
	public void thickLiquidPressed(){
		pressedMessage = "What type of thick liquid would you like?";
		repaint();

	}
	Button menu, order, exit;
	Button oil, thinLiquid, thickLiquid;



	
}


