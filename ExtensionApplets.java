import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.LinkedList;


public class ExtensionApplet extends Applet implements ActionListener{
	static DecimalFormat moneyFormat = new DecimalFormat(".00");
    static LinkedList<String> orders = new LinkedList<String>();
    static LinkedList<Double> orderPrices = new LinkedList<Double>();
	static String pressedMessage = "";
	static String flavourMessage = "";
	static String toppingMessage = "";
	static String sizeMessage = "";
	static String continueMessage = "";
	static String receiptHeader = "Your receipt:";
	static String receipt = "";
	static String receiptPrice = "";
	static String finalMessage = "";
	static Color green = new Color(0, 255, 153);
	static final String[] OILS = {"Burger oil", "Crude oil", "Olive oil"};
    static final double [] OILPRICES = {17, 25, 18};

    static final String[] THINLIQUIDS= {"Rainwater", "Disinfectant", "Saltwater"};
    static final double [] THINLIQUIDPRICES = {6, 12, 8};
    
    static final String[] THICKLIQUIDS= {"Ink", "Sewage waste", "Soap"};
    static final double [] THICKLIQUIDPRICES = {20, 1, 10};

    final String[] FLAVOURS = {"Cheese", "Ketchup", "Aluminium", "Pickle", "Spoiled milk", "Mold"};
    final double [] FLAVOURPRICES = {7, 2, 4, 3, 7, 1};
    
    final String[] TOPPINGS = {"Wood chips", "Tylenol", "Assorted cubed meat", "Cucumber", "Chalk", "None"};
    final double [] TOPPINGPRICES = {2, 10, 5, 3, 3, 0};
    
    final String[] SIZES = {"Needle's worth of", "Tablespoon of", "Bathtub full of"};
    final double[] SIZEPRICES = {0.5, 1, 100};
    
    static String[] choices = new String[3];
    static double [] prices = new double[3];
    
    static int layer = 0;
    static int firstPress;
    static double total = 0;
    static int[] toppingAmount = new int[6];
    static int numToppings = 0;
    static boolean doneChoosingToppings = false;
    static int spacing;
    
    static final double TAX = 0.13;
    static double hst;
    static String hstPrint = "";
    static double subtotal;
    static String subtotalPrint = "";
    static double finalTotal;
    static String finalTotalPrint = "";
    
    static String baseTea = "";
    

    
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

		button1.setBounds(50, 100, 120, 30);
		button2.setBounds(175, 100, 120, 30);
		button3.setBounds(300, 100, 120, 30);

		flavour1 = new Button("Cheese");
		flavour2 = new Button("Ketchup");
		flavour3 = new Button("Aluminium");
		flavour4 = new Button("Pickle");
		flavour5 = new Button("Spoiled milk");
		flavour6 = new Button("Mold");
		flavour1.addActionListener(this);
		flavour2.addActionListener(this);
		flavour3.addActionListener(this);
		flavour4.addActionListener(this);
		flavour5.addActionListener(this);
		flavour6.addActionListener(this);
		
		flavour1.setBounds(50, 170, 120, 30);
		flavour2.setBounds(175, 170, 120, 30);
		flavour3.setBounds(300, 170, 120, 30);
		flavour4.setBounds(425, 170, 120, 30);
		flavour5.setBounds(550, 170, 120, 30);
		flavour6.setBounds(675, 170, 120, 30);
		
		topping1 = new Button("Wood chips");
		topping2 = new Button("Tylenol");
		topping3 = new Button("Cubed meat");
		topping4 = new Button("Cucumber");
		topping5 = new Button("Chalk");
		topping6 = new Button("No more toppings");
		topping1.addActionListener(this);
		topping2.addActionListener(this);
		topping3.addActionListener(this);
		topping4.addActionListener(this);
		topping5.addActionListener(this);
		topping6.addActionListener(this);
		topping1.setBounds(50, 250, 120, 30);
		topping2.setBounds(175, 250, 120, 30);
		topping3.setBounds(300, 250, 120, 30);
		topping4.setBounds(425, 250, 120, 30);
		topping5.setBounds(550, 250, 120, 30);
		topping6.setBounds(675, 250, 120, 30);

		size1 = new Button("A needle's worth");
		size2 = new Button("A tablespoon");
		size3 = new Button("A bathtub's worth");
		size1.addActionListener(this);
		size2.addActionListener(this);
		size3.addActionListener(this);
		size1.setBounds(50, 330, 120, 30);
		size2.setBounds(175, 330, 120, 30);
		size3.setBounds(300, 330, 120, 30);

		keepOrdering = new Button("Yes");
		stopOrdering = new Button("No");
		keepOrdering.addActionListener(this);
		stopOrdering.addActionListener(this);
		keepOrdering.setBounds(50, 410, 120, 30);
		stopOrdering.setBounds(175, 410, 120, 30);

	}
	
	final String SHOPNAME = "Darren's delicious bubble tea";
	
	public void paint(Graphics g){
		Color black = new Color(0,0,0);
		g.setColor(black);
		g.drawString(SHOPNAME, 40, 20);
		g.drawString("Your order so far: " + baseTea, 40, 40);
		g.drawString(pressedMessage, 40, 80);
		g.drawString("TOTAL: $"+moneyFormat.format(total), 40, 60);
		g.drawString(flavourMessage, 40, 160);
		g.drawString(toppingMessage, 40, 240);
		g.drawString(sizeMessage, 40, 320);
		g.drawString(continueMessage, 40, 400);
		g.drawString(receiptHeader, 40, 460);

		for(int i = 0; i < orders.size(); i++){
			g.drawString(printReceipt(i), 40, 480 + (i*40));
			g.drawString(printReceiptPrice(i), 40, 500 + (i*40));
			spacing = i*40;
		}
		g.drawString(finalMessage, 40, 540 + spacing);
		g.drawString(subtotalPrint, 40, 560 + spacing);
		g.drawString(hstPrint, 40, 580 + spacing);
		g.drawString(finalTotalPrint, 40, 600 + spacing);


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
				prices[0] = OILPRICES[0];
				prices[1] = OILPRICES[1];
				prices[2] = OILPRICES[2];
			}else if(layer == 2){
				pressedMessage = "You chose " + choices[0];
				total += prices[0];
				baseTea = baseTea + choices[0] + " tea";
				repaint();
				makeFlavourButtons();
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
				prices[0] = THINLIQUIDPRICES[0];
				prices[1] = THINLIQUIDPRICES[1];
				prices[2] = THINLIQUIDPRICES[2];
			}else if(layer == 2){
				pressedMessage = "You chose " + choices[1];
				total += prices[1];
				baseTea = baseTea + choices[1] + " tea";
				repaint();
				makeFlavourButtons();
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
				prices[0] = THICKLIQUIDPRICES[0];
				prices[1] = THICKLIQUIDPRICES[1];
				prices[2] = THICKLIQUIDPRICES[2];
			}else if(layer == 2){
				pressedMessage = "You chose " + choices[2];
				total += prices[2];
				baseTea = baseTea + choices[2] + " tea";
				repaint();
				makeFlavourButtons();
			}

		}
		
		if(e.getSource() == flavour1 && layer == 2){
			layer++;
			flavourMessage = "You chose "+FLAVOURS[0]+"!";
			total += FLAVOURPRICES[0];
			baseTea = FLAVOURS[0] + " " + baseTea;
			makeToppingButtons();
			repaint();
		}
		
		if(e.getSource() == flavour2 && layer == 2){
			layer++;
			flavourMessage = "You chose "+FLAVOURS[1]+"!";
			total += FLAVOURPRICES[1];
			baseTea = FLAVOURS[1] + " " + baseTea;
			makeToppingButtons();
			repaint();
		}
		
		if(e.getSource() == flavour3 && layer == 2){
			layer++;
			flavourMessage = "You chose "+FLAVOURS[2]+"!";
			total += FLAVOURPRICES[2];
			baseTea = FLAVOURS[2] + " " + baseTea;
			makeToppingButtons();
			repaint();
		}	
		
		if(e.getSource() == flavour4 && layer == 2){
			layer++;
			flavourMessage = "You chose "+FLAVOURS[3]+"!";
			total += FLAVOURPRICES[3];
			baseTea = FLAVOURS[3] + " " + baseTea;
			makeToppingButtons();
			repaint();
		}
		
		if(e.getSource() == flavour5 && layer == 2){
			layer++;
			flavourMessage = "You chose "+FLAVOURS[4]+"!";
			total += FLAVOURPRICES[4];
			baseTea = FLAVOURS[4] + " " + baseTea;
			makeToppingButtons();
			repaint();
		}
		
		if(e.getSource() == flavour6 && layer == 2){
			layer++;
			flavourMessage = "You chose "+FLAVOURS[5]+"!";
			total += FLAVOURPRICES[5];
			baseTea = FLAVOURS[5] + " " + baseTea;
			makeToppingButtons();
			repaint();
		}
		
		if(e.getSource() == topping1 && toppingAmount[0] == 0 && !doneChoosingToppings){
			toppingAmount[0]++;
			numToppings++;
			toppingMessage = "You chose "+TOPPINGS[0]+"! <select none to stop>";
			total += TOPPINGPRICES[0];
			if(numToppings == 1){
				baseTea = baseTea + " with " + TOPPINGS[0];
			}else{
				baseTea = baseTea + ", " + TOPPINGS[0];
			}
			repaint();
		}
		
		if(e.getSource() == topping2 && toppingAmount[1] == 0 && !doneChoosingToppings){
			toppingAmount[1]++;
			numToppings++;
			toppingMessage = "You chose "+TOPPINGS[1]+"! <select none to stop>";
			total += TOPPINGPRICES[1];
			if(numToppings == 1){
				baseTea = baseTea + " with " + TOPPINGS[1];
			}else{
				baseTea = baseTea + ", " + TOPPINGS[1];
			}
			repaint();
		}
		
		if(e.getSource() == topping3 && toppingAmount[2] == 0 && !doneChoosingToppings){
			toppingAmount[2]++;
			numToppings++;
			toppingMessage = "You chose "+TOPPINGS[2]+"! <select none to stop>";
			total += TOPPINGPRICES[2];
			if(numToppings == 1){
				baseTea = baseTea + " with " + TOPPINGS[2];
			}else{
				baseTea = baseTea + ", " + TOPPINGS[2];
			}
			repaint();
		}
		
		if(e.getSource() == topping4 && toppingAmount[3] == 0 && !doneChoosingToppings){
			toppingAmount[3]++;
			numToppings++;
			toppingMessage = "You chose "+TOPPINGS[3]+"! <select none to stop>";
			total += TOPPINGPRICES[3];
			if(numToppings == 1){
				baseTea = baseTea + " with " + TOPPINGS[3];
			}else{
				baseTea = baseTea + ", " + TOPPINGS[3];
			}
			repaint();
		}
		
		if(e.getSource() == topping5 && toppingAmount[4] == 0 && !doneChoosingToppings){
			toppingAmount[4]++;
			numToppings++;
			toppingMessage = "You chose "+TOPPINGS[4]+"! <select none to stop>";
			total += TOPPINGPRICES[4];
			if(numToppings == 1){
				baseTea = baseTea + " with " + TOPPINGS[4];
			}else{
				baseTea = baseTea + ", " + TOPPINGS[4];
			}
			repaint();
		}
		
		if(e.getSource() == topping6 && toppingAmount[5] == 0 && !doneChoosingToppings){//DONE CHOOSING TOPPINGS
			toppingAmount[5]++;
			numToppings++;
			toppingMessage = "You're done choosing toppings?";
			doneChoosingToppings=true;
			makeSizeButtons();
			repaint();
		}
		
		if(e.getSource() == size1 && layer == 3){
			layer++;
			baseTea = "A " + SIZES[0] + " " + baseTea;
			total *= SIZEPRICES[0];
			orders.add(baseTea);
			orderPrices.add(total);
			sizeMessage = "You chose to get a " +SIZES[0] + " of your drink!";
			makeContinueButtons();
			repaint();
		}
		
		if(e.getSource() == size2 && layer == 3){
			layer++;
			baseTea = "A " + SIZES[1] + " " + baseTea;
			total *= SIZEPRICES[1];
			orders.add(baseTea);
			orderPrices.add(total);
			sizeMessage = "You chose to get a " +SIZES[1] + " of your drink!";
			makeContinueButtons();
			repaint();
		}
		
		if(e.getSource() == size3 && layer == 3){
			layer++;
			baseTea = "A " + SIZES[2] + " " + baseTea;
			total *= SIZEPRICES[2];
			orders.add(baseTea);
			orderPrices.add(total);
			sizeMessage = "You chose to get a " +SIZES[2] + " of your drink!";
			makeContinueButtons();
			repaint();
		}

		if(e.getSource() == keepOrdering){
			baseTea = "";
			total = 0;
			layer = 0;
			for(int i = 0; i < toppingAmount.length; i++) {
				toppingAmount[i] = 0;
			}
			numToppings = 0;
			doneChoosingToppings = false;
			pressedMessage = "What base would you like?";
			flavourMessage = "";
			toppingMessage = "";
			sizeMessage = "";
			continueMessage = "";

			button1.setLabel("Oil");
			button2.setLabel("Thin Liquids");
			button3.setLabel("Thick Liquids");
			flavour1.setVisible(false);
			flavour2.setVisible(false);
			flavour3.setVisible(false);
			flavour4.setVisible(false);
			flavour5.setVisible(false);
			flavour6.setVisible(false);
			
			topping1.setVisible(false);
			topping2.setVisible(false);
			topping3.setVisible(false);
			topping4.setVisible(false);
			topping5.setVisible(false);
			topping6.setVisible(false);
			
			size1.setVisible(false);
			size2.setVisible(false);
			size3.setVisible(false);
			
			keepOrdering.setVisible(false);
			stopOrdering.setVisible(false);
			repaint();
		}
		
		if(e.getSource() == stopOrdering){
			finalString();
			repaint();
		}
	}
	
	public void buttonPressed(String[] name, String message) {		
		button1.setLabel(name[0]);
		button2.setLabel(name[1]);
		button3.setLabel(name[2]);
		pressedMessage = message;
		repaint();
		
	}
	
	public void menuPressed(){
		pressedMessage = "You chose to see the menu";
		repaint();
	}

	public void orderPressed(){
		button1.setLabel("Oil");
		button2.setLabel("Thin Liquids");
		button3.setLabel("Thick Liquids");
		pressedMessage = "What base would you like?";
		add(button1);
		add(button2);
		add(button3);
		repaint();
	}
	
	public void exitPressed(){
		pressedMessage = "You chose to exit";
		repaint();
		layer = 0;
		button1.setLabel("Oil");
		button2.setLabel("Thin Liquids");
		button3.setLabel("Thick Liquids");
	}
	
	public void makeFlavourButtons(){
		flavourMessage = "What flavour would you like?";
		add(flavour1);
		add(flavour2);
		add(flavour3);
		add(flavour4);
		add(flavour5);
		add(flavour6);
		flavour1.setVisible(true);
		flavour2.setVisible(true);
		flavour3.setVisible(true);
		flavour4.setVisible(true);
		flavour5.setVisible(true);
		flavour6.setVisible(true);
	}
	
	public void makeToppingButtons(){
		toppingMessage = "What toppings would you like? <select none to stop>";
		add(topping1);
		add(topping2);
		add(topping3);
		add(topping4);
		add(topping5);
		add(topping6);
		topping1.setVisible(true);
		topping2.setVisible(true);
		topping3.setVisible(true);
		topping4.setVisible(true);
		topping5.setVisible(true);
		topping6.setVisible(true);
	}
	
	public void makeSizeButtons(){
		add(size1);
		add(size2);
		add(size3);
		size1.setVisible(true);
		size2.setVisible(true);
		size3.setVisible(true);

		sizeMessage = "What size would you like?";
	}
	
	public void makeContinueButtons(){
		add(keepOrdering);
		add(stopOrdering);		
		keepOrdering.setVisible(true);
		stopOrdering.setVisible(true);
		continueMessage = "Would you like to keep ordering teas?";
	}
	
	
	public String printReceipt(int index){
		return orders.get(index);
	}
	
	public String printReceiptPrice(int index){
		return ">> $" + moneyFormat.format(orderPrices.get(index));
	}
	
	public void finalString(){
		for(double v:orderPrices)subtotal += v;
		hst = subtotal * TAX;
		finalTotal = hst + subtotal;

		finalMessage = "FINAL COST";
		subtotalPrint = "SUBTOTAL: $" + moneyFormat.format(subtotal);
		hstPrint = "HST: $" + moneyFormat.format(hst);
		finalTotalPrint = "TOTAL: $" + moneyFormat.format(finalTotal);

	}
	
	Button menu, order, exit;
	Button button1, button2, button3;
	Button flavour1, flavour2, flavour3, flavour4, flavour5, flavour6;
	Button topping1, topping2, topping3, topping4, topping5, topping6;
	Button size1, size2, size3;
	Button keepOrdering, stopOrdering;
	
}