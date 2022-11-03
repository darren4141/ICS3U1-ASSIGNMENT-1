import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
`
public class ExtensionApplet extends Applet implements ActionListener{
	static String pressedMessage = "";
	static Color green = new Color(0, 255, 153);
	static final String[] OILS = {"burger oil", "crude oil", "olive oil"};
    static final String[] THINLIQUIDS= {"rainwater", "disinfectant", "saltwater"};
    static final String[] THICKLIQUIDS= {"ink", "sewage waste", "soap"};
    static String[] choices = new String[3];
    
    static int layer = 0;
    static int firstPress;

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
		
		button1 = new Button("--------------------");
		button2 = new Button("--------------------");
		button3 = new Button("--------------------");
		
		button1.addActionListener(this);		
		button2.addActionListener(this);		
		button3.addActionListener(this);		

		add(button1);
		add(button2);
		add(button3);
		
		makeTransparent(button1);
		makeTransparent(button2);
		makeTransparent(button3);
		
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
		
		if(e.getSource() == button1){
			layer++;
			if(layer == 1){
				buttonPressed(OILS, "What type of oil would you like");
				firstPress = 1;
				choices[0] = OILS[0];
				choices[1] = OILS[1];
				choices[2] = OILS[2];
			}else if(layer >= 1){
				pressedMessage = "You chose " + choices[0];
				repaint();
			}

			
		}		
		
		if(e.getSource() == button2){
			layer++;
			if(layer == 1){
				buttonPressed(THINLIQUIDS, "What type of thin liquid would you like");
				firstPress = 2;
				choices[0] = THINLIQUIDS[0];
				choices[1] = THINLIQUIDS[1];
				choices[2] = THINLIQUIDS[2];
			}else if(layer >= 1){
				pressedMessage = "You chose " + choices[1];
				repaint();
			}

		}		
		
		if(e.getSource() == button3){
			layer++;
			if(layer == 1){
				buttonPressed(THICKLIQUIDS, "What type of thick liquid would you like");
				firstPress = 3;
				choices[0] = THICKLIQUIDS[0];
				choices[1] = THICKLIQUIDS[1];
				choices[2] = THICKLIQUIDS[2];				
			}else if(layer >= 1){
				pressedMessage = "You chose " + choices[2];
				repaint();
			}

		}
		
	}
	
	public void buttonPressed(String[] name, String message) {		
		System.out.println(layer);
		button1.setLabel(name[0]);
		button2.setLabel(name[1]);
		button3.setLabel(name[2]);
		pressedMessage = message;
		repaint();
		
	}
	
	public void menuPressed(){
		pressedMessage = "You chose to see the menu";
		repaint();
		makeTransparent(button1);
		makeTransparent(button2);
		makeTransparent(button3);
	}

	public void orderPressed(){
		button1.setLabel("Oil");
		button2.setLabel("Thin Liquids");
		button3.setLabel("Thick Liquids");
		pressedMessage = "What base would you like?";
		makeVisible(button1);
		makeVisible(button2);
		makeVisible(button3);
		repaint();
	}
	
	public void exitPressed(){
		pressedMessage = "You chose to exit";
		repaint();
		layer = 0;
		button1.setLabel("Oil");
		button2.setLabel("Thin Liquids");
		button3.setLabel("Thick Liquids");
		makeTransparent(button1);
		makeTransparent(button2);
		makeTransparent(button3);
	}
	
	Button menu, order, exit;
	Button button1, button2, button3;
    static LinkedList<Button> buttons = new LinkedList<Button>();
	
}