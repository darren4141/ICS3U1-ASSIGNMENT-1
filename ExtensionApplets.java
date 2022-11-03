//====================================================================================================================================================================
//"Bubble Tea" Program - Applet extension
//Darren Liu
//November 3rd, 2022
//Java 4.6.1
//====================================================================================================================================================================
//Problem definition:   Help the sales staff determine the price of a bubble tea order, including multiple similar or different items based on the customer's
//                      specifications. The program must ask for the customer's name, type of tea, type of topping, number of teas, flavours, and toppings.
//                      Output a reciept at the end including all order information and price calculations.
 
//Extension:            Solve the program using Applets
 
//Input:                Button clicks to decide options
 
//Output:               Receipt including all of the orders, all of the prices, tax calculations
//                      User friendly prompts (you ordered: _____)
//                      Buttons with all of the options of toppings, flavours, etc with prices
 
//====================================================================================================================================================================
//List of Variables:	orders, orderprices: LinkedLists that store the orders and their prices to be printed in the receipt

//						any String that ends in "Message": a String variable to be filled when the message should "pop-up" they are all UFP's
//						the "message" Strings are meant to be constantly updated throughout the program, alongside the repaint() method to change the text on the screen

//						receiptHeader, receipt, receiptPrice: Strings that store reciept values, to be updated at the end of the program to simulate "pop-up" messages

//						green: a Color type variable, the background color

//						(item)S: An array that stores the name of the elements in a certain category.
//						(item)PRICES = An array containing all of the prices of elements in a category. Some prices are meant to be added on and others are meant to be multipliers.

//						choices, prices: String and double arrays used for the first button option, where three buttons (button1, button2, button3) are used for multiple purposes

//						layer: A counter variable used for the multi-purpose buttons

//						total: an int variable that stores the total price of an item

//						toppingAvailable: a boolean array that stores whether or not a topping has been added yet so that duplicates are not added

//						numToppings: an int variable that stores the number of toppings, mainly used for grammar

//						doneChoosingToppings: a boolean that dictates the condition that moves on from topping selection

//						spacing: a, int variable that stores the amount of extra space generated by the receipt and adds it to text lines below it to avoid overlapping

//						TAX: a final int that stores the tax percentage

//						hst: an int that stores the cost of tax
//						hstPrint: a String that stores the output of the cost of hst. It needs to be a seperate variable as g.drawstring() requires String parameters

//						subtotal: an int that stores the subtotal (before tax)
//						subtotalPrint: a String that stores the output of the subtotal. It needs to be a seperate variable as g.drawstring() requires String parameters

//						finalTotal: an int that stores the total (subtotal + hst)
//						finalTotalPrint:  a String that stores the output of the final total. It needs to be a seperate variable as g.drawstring() requires String parameters

//						baseTea: a String that stores the name of the tea ordered

//						SHOPNAME: the name of the shop stored in a final variable for spontaneous name changes
//====================================================================================================================================================================

//import libraries for Applets, action events, decimal formatting, and LinkedLists
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.LinkedList;


public class ExtensionApplet extends Applet implements ActionListener{//start of Applet class
	static DecimalFormat moneyFormat = new DecimalFormat(".00");//declare new DecimalFormat object for outputting money
	
	//INITIALIZE OUR COLOUR PALETTE
	static Color pastelBlue = new Color(152,186,213);
	static Color tintedGrey = new Color(198,211,227);
	static Color navyBlue = new Color(48,70,116);
	static Color skyBlue = new Color(178,203,222);
	static Color grey = new Color(216,225,232);
	static Color black = new Color(0,0,0);

	//VARIABLES EXPLAINED AT THE TOP
    static LinkedList<String> orders = new LinkedList<String>();
    static LinkedList<Double> orderPrices = new LinkedList<Double>();
    
	final String SHOPNAME = "Darren's delicious bubble tea";

	static String pressedMessage = "";
	static String flavourMessage = "";
	static String toppingMessage = "";
	static String sizeMessage = "";
	static String continueMessage = "";
	static String finalMessage = "";

	static String receiptHeader = "Your receipt:";
	static String receipt = "";
	static String receiptPrice = "";
	
	final String[] OILS = {"Burger oil", "Crude oil", "Olive oil"};
    final double [] OILPRICES = {17, 25, 18};

    final String[] THINLIQUIDS= {"Rainwater", "Disinfectant", "Saltwater"};
    final double [] THINLIQUIDPRICES = {6, 12, 8};
    
    final String[] THICKLIQUIDS= {"Ink", "Sewage waste", "Soap"};
    final double [] THICKLIQUIDPRICES = {20, 1, 10};

    final String[] FLAVOURS = {"Cheese", "Ketchup", "Aluminium", "Pickle", "Spoiled milk", "Mold"};
    final double [] FLAVOURPRICES = {7, 2, 4, 3, 7, 1};
    
    final String[] TOPPINGS = {"Wood chips", "Tylenol", "Assorted cubed meat", "Cucumber", "Chalk", "None"};
    final double [] TOPPINGPRICES = {2, 10, 5, 3, 3, 0};
    
    final String[] SIZES = {"Needle's worth", "Tablespoon", "Bathtub full"};
    final double[] SIZEPRICES = {0.5, 1, 100};
    
    static String[] choices = new String[3];
    static double [] prices = new double[3];
        
    static int numToppings = 0;
    static boolean[] toppingAvailable = {true, true, true, true, true, true};
    static boolean doneChoosingToppings = false;
    
    static double total = 0;
    static final double TAX = 0.13;
    static double hst;
    static String hstPrint = "";
    static double subtotal;
    static String subtotalPrint = "";
    static double finalTotal;
    static String finalTotalPrint = "";
    
    static String baseTea = "";
    
    static int spacing;
    static int layer = 0;
    
    //DECLARE BUTTONS FOR LATER USE
	Button order, exit;
	Button button1, button2, button3;
	Button flavour1, flavour2, flavour3, flavour4, flavour5, flavour6;
	Button topping1, topping2, topping3, topping4, topping5, topping6;
	Button size1, size2, size3;
	Button keepOrdering, stopOrdering;
    
	public void init(){//initialization applet class
		resize(1500, 5000);//resize window
		setBackground(pastelBlue);//set background color to our premade "green" variable
		order = new Button("Order");//declare new button
		add(order);//add button to the screen
		order.addActionListener(this);//make it so that button is "listening" for actions
		exit = new Button("Exit");
		add(exit);
		exit.addActionListener(this);
		
		button1 = new Button("--------------------");//declare a new button and set "----" as the button text to make the size larger
		button2 = new Button("--------------------");
		button3 = new Button("--------------------");
		
		button1.addActionListener(this);		
		button2.addActionListener(this);		
		button3.addActionListener(this);		

		button1.setBounds(50, 100, 180, 30);//set the location and size of the button: parameters (x value, y-value, width, height)
		button2.setBounds(250, 100, 180, 30);//SPACED EVENLY
		button3.setBounds(450, 100, 180, 30);

		flavour1 = new Button(FLAVOURS[0] + ": $" +moneyFormat.format(FLAVOURPRICES[0]));//new button, set name as the flavour name and the price, used arrays to make it simpler
		flavour2 = new Button(FLAVOURS[1] + ": $" +moneyFormat.format(FLAVOURPRICES[1]));
		flavour3 = new Button(FLAVOURS[2] + ": $" +moneyFormat.format(FLAVOURPRICES[2]));
		flavour4 = new Button(FLAVOURS[3] + ": $" +moneyFormat.format(FLAVOURPRICES[3]));
		flavour5 = new Button(FLAVOURS[4] + ": $" +moneyFormat.format(FLAVOURPRICES[4]));
		flavour6 = new Button(FLAVOURS[5] + ": $" +moneyFormat.format(FLAVOURPRICES[5]));
		flavour1.addActionListener(this);
		flavour2.addActionListener(this);
		flavour3.addActionListener(this);
		flavour4.addActionListener(this);
		flavour5.addActionListener(this);
		flavour6.addActionListener(this);
		
		flavour1.setBounds(50, 170, 180, 30);//evenly spaced
		flavour2.setBounds(250, 170, 180, 30);
		flavour3.setBounds(450, 170, 180, 30);
		flavour4.setBounds(650, 170, 180, 30);
		flavour5.setBounds(850, 170, 180, 30);
		flavour6.setBounds(1050, 170, 180, 30);
		
		topping1 = new Button(TOPPINGS[0] + ": $" +moneyFormat.format(TOPPINGPRICES[0]));//same idea as flavour buttons
		topping2 = new Button(TOPPINGS[1] + ": $" +moneyFormat.format(TOPPINGPRICES[1]));
		topping3 = new Button(TOPPINGS[2] + ": $" +moneyFormat.format(TOPPINGPRICES[2]));
		topping4 = new Button(TOPPINGS[3] + ": $" +moneyFormat.format(TOPPINGPRICES[3]));
		topping5 = new Button(TOPPINGS[4] + ": $" +moneyFormat.format(TOPPINGPRICES[4]));
		topping6 = new Button(TOPPINGS[5]);
		topping1.addActionListener(this);
		topping2.addActionListener(this);
		topping3.addActionListener(this);
		topping4.addActionListener(this);
		topping5.addActionListener(this);
		topping6.addActionListener(this);
		topping1.setBounds(50, 250, 180, 30);//evenly spaced
		topping2.setBounds(250, 250, 180, 30);
		topping3.setBounds(450, 250, 180, 30);
		topping4.setBounds(650, 250, 180, 30);
		topping5.setBounds(850, 250, 180, 30);
		topping6.setBounds(1050, 250, 180, 30);

		size1 = new Button(SIZES[0] + ": x"+moneyFormat.format(SIZEPRICES[0]));//same idea but instead this time we have "x" symbols to show multiplication
		size2 = new Button(SIZES[1] + ": x"+moneyFormat.format(SIZEPRICES[1]));
		size3 = new Button(SIZES[2] + ": x"+moneyFormat.format(SIZEPRICES[2]));
		size1.addActionListener(this);
		size2.addActionListener(this);
		size3.addActionListener(this);
		size1.setBounds(50, 330, 180, 30);//evenly spaced
		size2.setBounds(250, 330, 180, 30);
		size3.setBounds(450, 330, 180, 30);

		keepOrdering = new Button("Yes");//buttons at the end for if they want to keep ordering or not
		stopOrdering = new Button("No");
		keepOrdering.addActionListener(this);
		stopOrdering.addActionListener(this);
		keepOrdering.setBounds(50, 410, 180, 30);//evenly spaced
		stopOrdering.setBounds(250, 410, 180, 30);

	}
		
	public void paint(Graphics g){//GRAPHICS CLASS: NEW THINGS ON THE SCREEN CAN ONLY BE PRINTED HERE
		//since things can only be printed here, all messages must be stored in variables and sent to the paint method
		//the repaint() method will rerun the paint method, meaning that all variable names will be updated
		//using this, we can change text on the screen or even make text pop-up by renaming a variable, then calling repaint()
		//text can be popped up by renaming a "" variable
		g.setColor(navyBlue);//set cursor color
		g.drawString(SHOPNAME, 40, 20);//print shop name
		g.drawString("Your order so far: " + baseTea, 40, 40);//print order, which is meant to be updated using repaint()
		g.drawString(pressedMessage, 40, 80);//meant to be updated using repaint()
		g.drawString("TOTAL: $"+moneyFormat.format(total), 40, 60);//meant to be updated using repaint()
		g.drawString(flavourMessage, 40, 160);//meant to be updated using repaint()
		g.drawString(toppingMessage, 40, 240);//meant to be updated using repaint()
		g.drawString(sizeMessage, 40, 320);//meant to be updated using repaint()
		g.drawString(continueMessage, 40, 400);//meant to be updated using repaint()
		g.drawString(receiptHeader, 40, 460);//meant to be updated using repaint()

		for(int i = 0; i < orders.size(); i++){//iterate through the amount of orders
			g.drawString(printReceipt(i), 40, 480 + (i*40));//output the ordered item
			g.drawString(printReceiptPrice(i), 40, 500 + (i*40));//output the ordered item's price
			//i*40 is used as each time we run through the loop, there is more printed, so we need to print under that
			spacing = i*40;//our total spacing is stored in a variable for lines underneath
		}
		g.drawString(finalMessage, 40, 540 + spacing);//using spacing to avoid overlap
		g.drawString(subtotalPrint, 40, 560 + spacing);
		g.drawString(hstPrint, 40, 580 + spacing);
		g.drawString(finalTotalPrint, 40, 600 + spacing);


	}
	
	public void actionPerformed(ActionEvent e){//ACTION PERFORMED METHOD, BASICALLY JUST IF STATEMENTS THAT TRIGGER WHEN BUTTON IS PRESSED
		if(e.getSource() == order){//when order button is pressed, run orderPressed method
			orderPressed();
		}
		if(e.getSource() == exit){//when exit button is pressed, run exitPressed method
			exitPressed();
		}
		
		if(e.getSource() == button1){//when button1 is pressed
			layer++;
			if(layer == 1){//layer 1 is when it displays (Oils, thinliquids, thickliquids)
				buttonPressed(OILS, "What type of oil would you like");//when button1 is pressed, we run the buttonPressed method with parameters of our OILS options array and a prompt
				choices[0] = OILS[0];//set choices to items on OILS menu
				choices[1] = OILS[1];
				choices[2] = OILS[2];
				prices[0] = OILPRICES[0];//set prices to costs on OILS menu
				prices[1] = OILPRICES[1];
				prices[2] = OILPRICES[2];
			}else if(layer == 2){//layer 2 is when it displays different types of oils/thick liquids/thin liquids
				pressedMessage = "You chose " + choices[0];//this is why we needed the choices array, button1 can represent 3 different bases which is why we store our options in the choices array
				total += prices[0];//increment total
				baseTea = baseTea + choices[0] + " tea";//update baseTea variable
				repaint();//repaint to update messages on screen
				makeFlavourButtons();//run makeFlavourButtons method, which generates the next set of buttons
			}
			
		}		
		
		if(e.getSource() == button2){//THIS ENTIRE IF STATEMENT IS THE SAME AS THE LAST EXCEPT DIFFERENT VARIABLES ARE USED
			layer++;
			if(layer == 1){
				buttonPressed(THINLIQUIDS, "What type of thin liquid would you like");
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
		
		if(e.getSource() == button3){//THIS ENTIRE IF STATEMENT IS THE SAME AS THE LAST EXCEPT DIFFERENT VARIABLES ARE USED
			layer++;
			if(layer == 1){
				buttonPressed(THICKLIQUIDS, "What type of thick liquid would you like");
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
		
		if(e.getSource() == flavour1 && layer == 2){//check for flavour1 button being pressed AND layer being 2, to avoid pressing flavour buttons multiple times
			layer++;//increment layer to lock user out of pressing other flavour buttons
			flavourMessage = "You chose "+FLAVOURS[0]+"!";//UFP, stored in a variable to be updated on the screen using repaint()
			total += FLAVOURPRICES[0];//increment total
			baseTea = FLAVOURS[0] + " " + baseTea;//modify baseTea variable with chosen variable
			makeToppingButtons();//generate the next set of buttons
			repaint();//update screen
		}
		
		if(e.getSource() == flavour2 && layer == 2){//SAME AS OTHER FLAVOUR BUTTON SELECTIONS
			layer++;
			flavourMessage = "You chose "+FLAVOURS[1]+"!";
			total += FLAVOURPRICES[1];
			baseTea = FLAVOURS[1] + " " + baseTea;
			makeToppingButtons();
			repaint();
		}
		
		if(e.getSource() == flavour3 && layer == 2){//SAME AS OTHER FLAVOUR BUTTON SELECTIONS
			layer++;
			flavourMessage = "You chose "+FLAVOURS[2]+"!";
			total += FLAVOURPRICES[2];
			baseTea = FLAVOURS[2] + " " + baseTea;
			makeToppingButtons();
			repaint();
		}	
		
		if(e.getSource() == flavour4 && layer == 2){//SAME AS OTHER FLAVOUR BUTTON SELECTIONS
			layer++;
			flavourMessage = "You chose "+FLAVOURS[3]+"!";
			total += FLAVOURPRICES[3];
			baseTea = FLAVOURS[3] + " " + baseTea;
			makeToppingButtons();
			repaint();
		}
		
		if(e.getSource() == flavour5 && layer == 2){//SAME AS OTHER FLAVOUR BUTTON SELECTIONS
			layer++;
			flavourMessage = "You chose "+FLAVOURS[4]+"!";
			total += FLAVOURPRICES[4];
			baseTea = FLAVOURS[4] + " " + baseTea;
			makeToppingButtons();
			repaint();
		}
		
		if(e.getSource() == flavour6 && layer == 2){//SAME AS OTHER FLAVOUR BUTTON SELECTIONS
			layer++;
			flavourMessage = "You chose "+FLAVOURS[5]+"!";
			total += FLAVOURPRICES[5];
			baseTea = FLAVOURS[5] + " " + baseTea;
			makeToppingButtons();
			repaint();
		}
		
		//check if topping 1 button is pressed
		//check if topping has been picked before
		//check if we decided to stop picking toppings
		if(e.getSource() == topping1 && toppingAvailable[0] != false && !doneChoosingToppings){
			toppingAvailable[0] = false;//mark this toppings as chosen
			numToppings++;//increment number of toppings
			toppingMessage = "You chose "+TOPPINGS[0]+"! <select none to stop>";//UFP to be updated on screen with repaint()
			total += TOPPINGPRICES[0];//increment total price
			if(numToppings == 1){//selection for grammar
				baseTea = baseTea + " with " + TOPPINGS[0];
			}else{
				baseTea = baseTea + ", " + TOPPINGS[0];
			}
			repaint();//update screen
		}
		
		if(e.getSource() == topping2 && toppingAvailable[1] != false && !doneChoosingToppings){//SAME AS OTHER TOPPING BUTTON SELECTIONS
			toppingAvailable[1] = false;
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
		
		if(e.getSource() == topping3 && toppingAvailable[2] != false && !doneChoosingToppings){//SAME AS OTHER TOPPING BUTTON SELECTIONS
			toppingAvailable[2] = false;
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
		
		if(e.getSource() == topping4 && toppingAvailable[3] != false && !doneChoosingToppings){//SAME AS OTHER TOPPING BUTTON SELECTIONS
			toppingAvailable[3] = false;
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
		
		if(e.getSource() == topping5 && toppingAvailable[4] != false && !doneChoosingToppings){//SAME AS OTHER TOPPING BUTTON SELECTIONS
			toppingAvailable[4] = false;
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
		
		if(e.getSource() == topping6 && toppingAvailable[5] != false && !doneChoosingToppings){//DONE CHOOSING TOPPINGS
			toppingAvailable[5] = false;
			numToppings++;
			toppingMessage = "You're done choosing toppings?";//UFP to be updated on screen with repaint()
			doneChoosingToppings=true;//lock out all topping button onpress selection
			makeSizeButtons();//generate next set of buttons
			repaint();//update screen
		}
		
		if(e.getSource() == size1 && layer == 3){//check if size button is pressed and if layer is 3 to prevent duplicate presses
			layer++;//increment layer to lock out other size button on press selection
			baseTea = "A " + SIZES[0] + " of " + baseTea;//update name
			total *= SIZEPRICES[0];//update total cost - multiply it this time
			orders.add(baseTea);//add basetea to linkedlist of all of our orders
			orderPrices.add(total);//add price to linkedlist of all of our prices
			sizeMessage = "You chose to get a " +SIZES[0] + "worth of your drink!";//UFP to be updated on screen with repaint()
			makeContinueButtons();//make next set of buttons
			repaint();
		}
		
		if(e.getSource() == size2 && layer == 3){//SAME AS OTHER SIZE BUTTON SELECTIONS
			layer++;
			baseTea = "A " + SIZES[1] + " of " + baseTea;
			total *= SIZEPRICES[1];
			orders.add(baseTea);
			orderPrices.add(total);
			sizeMessage = "You chose to get a " +SIZES[1] + " of your drink!";
			makeContinueButtons();
			repaint();
		}
		
		if(e.getSource() == size3 && layer == 3){//SAME AS OTHER SIZE BUTTON SELECTIONS
			layer++;
			baseTea = "A " + SIZES[2] + " of " + baseTea;
			total *= SIZEPRICES[2];
			orders.add(baseTea);
			orderPrices.add(total);
			sizeMessage = "You chose to get a " +SIZES[2] + " of your drink!";
			makeContinueButtons();
			repaint();
		}

		if(e.getSource() == keepOrdering){//if keep ordering button is pressed
			//RESET ALL VARIABLES
			baseTea = "";
			total = 0;
			layer = 0;
			for(int i = 0; i < toppingAvailable.length; i++) {//for loop to reset array
				toppingAvailable[i] = true;
			}
			numToppings = 0;
			doneChoosingToppings = false;
			pressedMessage = "What base would you like?";
			flavourMessage = "";
			toppingMessage = "";
			sizeMessage = "";
			continueMessage = "";
			
			//SET LABEL OF BUTTON 1 BACK TO ORIGINAL
			button1.setLabel("Oil");
			button2.setLabel("Thin Liquids");
			button3.setLabel("Thick Liquids");
			
			//HIDE ALL BUTTONS EXCEPT FOR THE TYPE OF BASE BUTTONS (button1, button2, button3)
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
			repaint();//update screen
		}
		
		if(e.getSource() == stopOrdering){//stop ordering button is pressed, we run finalString() method 
			finalString();
			repaint();//update screen
		}
	}//end of actionEvent method
	
	public void buttonPressed(String[] name, String message) {// this method is for the dual-purpose buttons to change their labels and messages based on how many times they are pressed
		button1.setLabel(name[0]);//set names
		button2.setLabel(name[1]);
		button3.setLabel(name[2]);
		
		pressedMessage = message;//UFP to be updated on screen with repaint()
		repaint();//update screen
		
	}

	public void orderPressed(){//order button is pressed
		button1.setLabel("Oil");//change label of button (it was previously "----")
		button2.setLabel("Thin Liquids");
		button3.setLabel("Thick Liquids");
		pressedMessage = "What base would you like?";//UFP to be updated on screen with repaint()
		add(button1);//add buttons to the screen
		add(button2);
		add(button3);
		
		//SET BUTTON COLOURS
		button1.setBackground(grey);
		button2.setBackground(grey);
		button3.setBackground(grey);
		
		repaint();//update screen
	}
	
	public void exitPressed(){//exit button is pressed
		pressedMessage = "You chose to exit";//UFP to be updated on screen with repaint()
		
		//RESET ALL VARIABLES
		baseTea = "";
		total = 0;
		layer = 0;
		for(int i = 0; i < toppingAvailable.length; i++) {//use a loop to reset array
			toppingAvailable[i] = true;
		}
		numToppings = 0;
		doneChoosingToppings = false;
		pressedMessage = "What base would you like?";
		flavourMessage = "";
		toppingMessage = "";
		sizeMessage = "";
		continueMessage = "";
		hst = 0;
		subtotal = 0;
		finalTotal = 0;
		finalMessage = "";
		subtotalPrint = "";
		hstPrint = "";
		finalTotalPrint = "";
		orders.clear();
		orderPrices.clear();
		
		//reset these buttons back to their default names
		button1.setLabel("Oil");
		button2.setLabel("Thin Liquids");
		button3.setLabel("Thick Liquids");
		
		//hide all buttons other than the base choice ones (button1, button2, button3)
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
		
		repaint();//update screen
	}//end of exitPressed method
	
	public void makeFlavourButtons(){//MAKE THE FLAVOUR BUTTONS
		flavourMessage = "What flavour would you like?";//UFP to be updated on screen with repaint()
		
		//ADD ALL BUTTONS
		add(flavour1);
		add(flavour2);
		add(flavour3);
		add(flavour4);
		add(flavour5);
		add(flavour6);
		
		//MAKE THEM ALL VISIBLE
		flavour1.setVisible(true);
		flavour2.setVisible(true);
		flavour3.setVisible(true);
		flavour4.setVisible(true);
		flavour5.setVisible(true);
		flavour6.setVisible(true);
		
		//SET BUTTON COLOURS
		flavour1.setBackground(grey);
		flavour2.setBackground(grey);
		flavour3.setBackground(grey);
		flavour4.setBackground(grey);
		flavour5.setBackground(grey);
		flavour6.setBackground(grey);
		
	}
	
	public void makeToppingButtons(){//MAKE ALL OF THE TOPPINGS BUTTONS
		toppingMessage = "What toppings would you like? <select none to stop>";//UFP to be updated on screen with repaint()
		
		//ADD ALL BUTTONS
		add(topping1);
		add(topping2);
		add(topping3);
		add(topping4);
		add(topping5);
		add(topping6);
		
		//MAKE THEM ALL VISIBLE
		topping1.setVisible(true);
		topping2.setVisible(true);
		topping3.setVisible(true);
		topping4.setVisible(true);
		topping5.setVisible(true);
		topping6.setVisible(true);
		
		//SET BUTTON COLOURS
		topping1.setBackground(grey);
		topping2.setBackground(grey);
		topping3.setBackground(grey);
		topping4.setBackground(grey);
		topping5.setBackground(grey);
		topping6.setBackground(grey);

	}
	
	public void makeSizeButtons(){//MAKE ALL OF THE SIZE BUTTONS
		
		//ADD ALL BUTTONS
		add(size1);
		add(size2);
		add(size3);
		
		//MAKE THEM ALL VISIBLE
		size1.setVisible(true);
		size2.setVisible(true);
		size3.setVisible(true);
		
		//SET BUTTON COLOURS
		size1.setBackground(grey);
		size2.setBackground(grey);
		size3.setBackground(grey);

		sizeMessage = "What size would you like?";//UFP to be updated on screen with repaint()
	}
	
	public void makeContinueButtons(){//MAKE ALL OF THE CONTINUE BUTTONS
		
		//ADD ALL BUTTONS
		add(keepOrdering);
		add(stopOrdering);		
		
		//MAKE THEM ALL VISIBLE
		keepOrdering.setVisible(true);
		stopOrdering.setVisible(true);
		
		//SET BUTTON COLOURS
		keepOrdering.setBackground(grey);
		stopOrdering.setBackground(grey);
		
		continueMessage = "Would you like to keep ordering teas?";//UFP to be updated on screen with repaint()
	}
	
	public String printReceipt(int index){//PRINT ORDERS WITH PARAMETER INDEX, MEANT TO BE RUN MULTIPLE TIMES
		return orders.get(index);//return string type for g.drawString, formatted to two decimal places
	}
	
	public String printReceiptPrice(int index){//PRINT PRICES WITH PARAMETER INDEX, MEANT TO BE RUN MULTIPLES TIMES RIGHT AFTER printReceipt()
		return ">> $" + moneyFormat.format(orderPrices.get(index));//return string type for g.drawString, formatted to two decimal places
	}
	
	public void finalString(){//final cost
		for(double v:orderPrices)subtotal += v;//add up orderPrices linkedList to subtotal
		hst = subtotal * TAX;//calculate tax amount
		finalTotal = hst + subtotal;//calculate final total (subtotal + tax)

		finalMessage = "FINAL COST";//UFP to be updated on screen with repaint()
		subtotalPrint = "SUBTOTAL: $" + moneyFormat.format(subtotal);//convert the doubles to strings to be put into g.drawString
		hstPrint = "HST: $" + moneyFormat.format(hst);//convert the doubles to strings to be put into g.drawString
		finalTotalPrint = "TOTAL: $" + moneyFormat.format(finalTotal);//convert the doubles to strings to be put into g.drawString

	}//end of finalString method
	
}//end of class