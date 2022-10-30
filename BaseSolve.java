//====================================================================================================================================================================
//Bubble Tea Program
//Darren Liu
//October 28th, 2022
//Java (ADD VERSION NUMBER)
//====================================================================================================================================================================
//Problem definition: 	Help the sales staff determine the price of a bubble tea order, including multiple similar or different items based on the customer's
//						specifications. The program must ask for the customer's name, type of tea, type of topping, number of teas, flavours, and toppings

//Input: Name, Type of tea, flavour of tea, topping choice, size choice

//Output: Receipt including all of the orders, user friendly prompts (Welcome, what would you like?), menu with all options and prices

//====================================================================================================================================================================
//List of variables: 	any double constant with 'PRICE' at the end = the constant price of such item. The base cost comes from TEAPRICE, MILKTEAPRICE, and BLENDPRICE. 
// 						all other 'PRICE' variables have lower numbers as they are intended to be modifiers to the base price, which is why some of them are 0
// 						any int nonconstant with 'Choice' at the end is a variable used for switch/case operations
//						any String nonconstant starting with 'string' and ending with 'choice' is a String variable used to store the type of choice the user makes
//						in order to display it later on the reciept
//						drinkPrice = a double variable used to store the accumulative price of the drink as the user chooses more things about their tea
//						baseTea = a 
//						SHOPNAME = the name of the shop, stored in a constant in case of any spontaneous rebrands
//						a String variable used to store the name of the order, it is added onto throughout the program
//====================================================================================================================================================================


import java.io.*;
import java.text.DecimalFormat;
public class BaseSolve {
	
	public static void main (String[]args) throws IOException{//start of main method
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//declare BufferedReader object named br
		DecimalFormat moneyFormat = new DecimalFormat(".00");//declare DecimalFormat object named moneyFormat, meant for formatting money, it will format to two decimal places
		
		//variables explained at the top
        boolean chosen;

		int mainChoice=0, liquidChoice, typeThinLiquidChoice, typeThickLiquidChoice, toppingChoice, marbleColourChoice, flavourChoice, sizeChoice, typeOilChoice; 
		int numOfToppings;
        int tipChoice;

		double drinkPrice = 0;
        double totalPrice = 0;
        double tipPercentage = 0;
		double hst, tipAmount, total; 

		String stringToppingChoice = "", stringSizeChoice = "", stringFlavourChoice="", stringTypeBaseLiquidChoice="";
		String stringToppings = "";
        String baseTea = "";
		String reciept = "";

		final String SHOPNAME = "Darren's delicious bubble tea";
        final String PHONENUM = "647-BBL-TEA";
        String special = "SPOILED MILK";
        int specialNumber = 6;
		final double BURGEROILPRICE = 17, CRUDEOILPRICE = 25, OLIVEOILPRICE = 18;
        final double RAINWATERPRICE = 6, DISINFECTANTPRICE = 12, SALTWATERPRICE = 8;
        final double INKPRICE = 20, SEWAGEWASTEPRICE = 1, SOAPPRICE = 10;
        final double TAX = 0.13;

		final double CHEESEPRICE = 7, KETCHUPPRICE = 2, ALUMINIUMPRICE = 4, PICKLEPRICE = 3, SRIRACHAPRICE = 5, SPOILEDMILKPRICE = 7, MOLDPRICE = 0, FISHPRICE = 6;
		final double WOODCHIPPRICE = 2, TYLENOLPRICE = 10, CHALKPRICE = 3, ASSORTEDCUBEDMEATPRICE = 5, CUCUMBERPRICE = 3, MARBLEPRICE = 6;
		final double SIZENEEDLEPRICE = 0.5, SIZETABLESPOONPRICE = 1, SIZEBATHTUBPRICE = 100;
		
		
		//WELCOME
        System.out.println("Welcome to "+SHOPNAME+"!");

		System.out.println("What would you like today!");
        System.out.println("The special of the day today is: "+special);
		System.out.println("1. Menu\n2. Order\n3. Exit");
		mainChoice = Integer.parseInt(br.readLine()); //input choice
		
		while(mainChoice != 3){//repeat program, meaning take more orders of bubble tea, until the user says to stop
			
			switch(mainChoice){//switch case of choice that was just inputted
				case 1://chose to view menu
					
					//MENU - DISPLAYS ALL FLAVOURS AND PRICES
					System.out.println("MENU");
					System.out.println("\nTYPE:");
					System.out.println(".-------------------------------------------------------.");
					System.out.println("|1. Oil\t\t\tBurger oil\t|+$"+moneyFormat.format(BURGEROILPRICE)+"\t|");
					System.out.println("|\t\t\tOlive oil\t|+$"+moneyFormat.format(OLIVEOILPRICE)+"\t|");
					System.out.println("|\t\t\tCrude oil\t|+$"+moneyFormat.format(CRUDEOILPRICE)+"\t|");
					System.out.println("|-------------------------------------------------------|");
					System.out.println("|2. Thin Liquids\tRainwater\t|+$"+moneyFormat.format(RAINWATERPRICE)+"\t\t|");
					System.out.println("|\t\t\tDisinfectant\t|+$"+moneyFormat.format(DISINFECTANTPRICE)+"\t|");
					System.out.println("|\t\t\tSaltwater\t|+$"+moneyFormat.format(SALTWATERPRICE)+"\t\t|");
					System.out.println("|-------------------------------------------------------|");
					System.out.println("|3. Thick liquids\tInk\t\t|+$"+moneyFormat.format(INKPRICE)+"\t|");
					System.out.println("|\t\t\tSewage waste\t|+$"+moneyFormat.format(SEWAGEWASTEPRICE)+"\t\t|");
					System.out.println("|\t\t\tSoap\t\t|+$"+moneyFormat.format(SOAPPRICE)+"\t|");
                    System.out.println("'-------------------------------------------------------'");
					
					System.out.println("\nFLAVOURS:");
                    System.out.println(special+" is 50% off today!");
					System.out.println(".---------------------------------------.");
					System.out.println("|1. Cheese\t\t\t|+$" + moneyFormat.format(CHEESEPRICE)+"\t|");
					System.out.println("|2. Ketchup\t\t\t|+$" + moneyFormat.format(KETCHUPPRICE)+"\t|");
					System.out.println("|3. Aluminium\t\t\t|+$" + moneyFormat.format(ALUMINIUMPRICE)+"\t|");
					System.out.println("|4. Pickle\t\t\t|+$" + moneyFormat.format(PICKLEPRICE)+"\t|");
					System.out.println("|5. Sriracha\t\t\t|+$" + moneyFormat.format(SRIRACHAPRICE)+"\t|");
					System.out.println("|6. Spoiled milk\t\t|+$" + moneyFormat.format(SPOILEDMILKPRICE)+"\t|");
					System.out.println("|7. Mold\t\t\t|+$" + moneyFormat.format(MOLDPRICE)+"\t|");
					System.out.println("|8. Fish\t\t\t|+$" + moneyFormat.format(FISHPRICE)+"\t|");
					System.out.println("'---------------------------------------'");

					System.out.println("\nTOPPINGS:");
					System.out.println(".---------------------------------------.");
					System.out.println("|1. Wood chips\t\t\t|+$" + moneyFormat.format(WOODCHIPPRICE)+"\t|");
					System.out.println("|2. Tylenol\t\t\t|+$" + moneyFormat.format(TYLENOLPRICE)+"|");
					System.out.println("|3. Assorted cubed meat\t\t|+$" + moneyFormat.format(ASSORTEDCUBEDMEATPRICE)+"\t|");
					System.out.println("|4. Cucumber\t\t\t|+$" + moneyFormat.format(CUCUMBERPRICE)+"\t|");
					System.out.println("|5. Chalk\t\t\t|+$" + moneyFormat.format(CHALKPRICE)+"\t|");
					System.out.println("|6. Marbles\t\t\t|+$" + moneyFormat.format(MARBLEPRICE)+"\t|");
                    System.out.println("|\tRed\t\t\t\t|");
					System.out.println("|\tYellow\t\t\t\t|");
					System.out.println("|\tBlue\t\t\t\t|");
					System.out.println("|\tMulticoloured\t\t\t|");
					System.out.println("'---------------------------------------'");
					
					System.out.println("\nSIZES: Final cost is multiplied based on size");
					System.out.println(".-------------------------------------------------------.");
					System.out.println("|1. Needle's worth (3mL)(needle included)\t|x" + SIZENEEDLEPRICE+"\t|");
					System.out.println("|2. Tablespoon\t\t\t\t\t|x" + SIZETABLESPOONPRICE+"\t|");
					System.out.println("|3. Bathtub's worth (180L)(Bathtub included)\t|x" + SIZEBATHTUBPRICE+"\t|");

					System.out.println("'-------------------------------------------------------'");
					
					break;//break to avoid doing the default case
					
				case 2://CHOSE TO ORDER
                    chosen = true;
                    do{//for when the default case is triggered, selection will run again. If a valid option is chosen, this will run once
                        chosen = true;
                        System.out.println("-----------------------------------------------");
                        System.out.println("What type of liquid would you like today?");//UFP
                        System.out.println("1. Oil\n2. Thin liquid\n3. Thick liquid");
                        liquidChoice = Integer.parseInt(br.readLine());//input option for which type of liquid they would like
                        switch(liquidChoice){//switch case of type of liquid
                            
                            case 1://CHOSE OIL
                                chosen = true;
                                do{
                                    chosen = true;
                                    System.out.println("-----------------------------------------------");
                                    System.out.println("You want an oil drink? Okay.");//UFP
                                    System.out.println("What type of oil base would you like?");//UFP
                                    System.out.println("1. Burger oil\t+"+moneyFormat.format(BURGEROILPRICE));
                                    System.out.println("2. Olive oil\t+"+moneyFormat.format(OLIVEOILPRICE));
                                    System.out.println("3. Crude oil\t+"+moneyFormat.format(CRUDEOILPRICE));
                                    typeOilChoice = Integer.parseInt(br.readLine());//input choice of type of oil
                                    
                                    switch(typeOilChoice){//switch case of type of oil

                                        case 0://this case gets triggered when the previous order included oil but the current one bypassed this entire switch by choosing something else
                                            break;
                                        case 1://CHOSE BURGER OIL
                                            System.out.println("We cooked an entire burger and threw it out just to gather this oil");
                                            stringTypeBaseLiquidChoice = "Burger oil";//assign string value based on choice so we can add it to our "baseTea" String variable
                                            drinkPrice += BURGEROILPRICE;//increment our price by the extra fee for black tea
                                            break;
                                            
                                        case 2://CHOSE OLIVE OIL
                                            System.out.println("This one is my personal favourite of the three");
                                            stringTypeBaseLiquidChoice = "Olive oil";
                                            drinkPrice += OLIVEOILPRICE;
                                            break;
                                            
                                        case 3://CHOSE CRUDE OIL
                                            System.out.println("This stuff is so expensive nowadays");
                                            stringTypeBaseLiquidChoice = "Crude oil";
                                            drinkPrice += CRUDEOILPRICE ;
                                            break;
                                            
                                        default:
                                            System.out.println("Stop messing around and select a valid option before I kick you out of the store");//UFP if they don't select a menu option
                                            chosen = false;
                                    }
                                }while(!chosen);
                                break;
                                
                            case 2://CHOSE THIN LIQUID
                                chosen = true;
                                do{
                                    chosen = true;
                                    System.out.println("-----------------------------------------------");
                                    System.out.println("On a diet today?");
                                    System.out.println("What type of thin liquid would you like today?");//UFP
                                    System.out.println("1. Rainwater\t+"+moneyFormat.format(RAINWATERPRICE));
                                    System.out.println("2. Disinfectant\t+"+moneyFormat.format(DISINFECTANTPRICE));
                                    System.out.println("3. Saltwater\t+"+moneyFormat.format(SALTWATERPRICE));
                                    typeThinLiquidChoice = Integer.parseInt(br.readLine());//input menu choice
                                    
                                        switch(typeThinLiquidChoice){//switch case for thin liquid choice

                                            case 0://this case gets triggered when the previous order included thin liquid but the current one bypassed this entire switch by choosing something else
                                                break;

                                            case 1://CHOSE RAINWATER
                                                System.out.println("This option saves us money");
                                                stringTypeBaseLiquidChoice = "rainwater";//for "baseTea" variable
                                                drinkPrice += RAINWATERPRICE;//increment for our final price
                                                break;
                                                
                                            case 2://CHOSE DISINFECTANT
                                                System.out.println("I hope you're not planning on drinking this");
                                                stringTypeBaseLiquidChoice = "disinfectant";
                                                drinkPrice += DISINFECTANTPRICE;
                                                break;
                                                
                                            case 3://CHOSE SALTWATER
                                                System.out.println("What is wrong with you?");
                                                stringTypeBaseLiquidChoice = "saltwater";
                                                drinkPrice += SALTWATERPRICE ;
                                                break;
                                                
                                            default:
                                                System.out.println("Stop messing around and select a valid option before I kick you out of the store");//UFP if they don't select a menu option
                                                chosen = false;
                                    }//end of type of tea switch case
                                }while(!chosen);

                                break;

                            case 3://CHOSE THICK LIQUID
                                chosen = true;
                                do{
                                    chosen = true;
                                    System.out.println("-----------------------------------------------");
                                    System.out.println("Make sure you take a thicker straw for these thick liquids!");
                                    System.out.println("What type of thick liquid would you like?");//UFP
                                    System.out.println("1. Ink\t\t+"+moneyFormat.format(INKPRICE));
                                    System.out.println("2. Sewage waste\t+"+moneyFormat.format(SEWAGEWASTEPRICE));
                                    System.out.println("3. Soap\t\t+"+moneyFormat.format(SOAPPRICE));
                                    typeThickLiquidChoice = Integer.parseInt(br.readLine());//input choice of type of milk
                                    
                                        switch(typeThickLiquidChoice){//switch case for thick liquid choice

                                            case 0://this case gets triggered when the previous order included thick liquid but the current one bypassed this entire switch by choosing something else
                                                break;

                                            case 1://CHOSE INK
                                                System.out.println("I heard this stuff will make your insides black");
                                                stringTypeBaseLiquidChoice = "Ink";
                                                drinkPrice += INKPRICE;
                                                break;
                                                
                                            case 2://CHOSE SEWAGEWASTE
                                                System.out.println("Did you know they pay us to take this stuff?");
                                                stringTypeBaseLiquidChoice = "Sewage waste";
                                                drinkPrice += SEWAGEWASTEPRICE;
                                                break;
                                                
                                            case 3://CHOSE SOAP
                                                System.out.println("Just because it smells good doesn't mean it'll taste good");
                                                stringTypeBaseLiquidChoice = "Soap";
                                                drinkPrice += SOAPPRICE;
                                                break;

                                            default:
                                            System.out.println("Stop messing around and select a valid option before I kick you out of the store");//UFP if they don't select a menu option
                                            chosen = false;
                                        }//end of thick liquid switch case
                                }while(!chosen);

                                break;

                                default:
                                    System.out.println("Stop messing around and select a valid option before I kick you out of the store");//UFP if they don't select a menu option
                                    chosen = false;
                        }//Done choosing type of tea

                    }while(!chosen);

                    baseTea = stringTypeBaseLiquidChoice;//now baseTea contains type of tea + the choice

					
					//CHOOSE FLAVOUR

                    chosen = true;
                    do{
                        chosen = true;
                        System.out.println("-----------------------------------------------");
                        System.out.println("It's time to choose from our delicious flavours!");
                        System.out.println("What flavour would you like?");//UFP
                        System.out.println(special+" is 50% off today!");
                        System.out.println("1. Cheese\t+" + moneyFormat.format(CHEESEPRICE));
                        System.out.println("2. Ketchup\t+" + moneyFormat.format(KETCHUPPRICE));
                        System.out.println("3. Aluminium\t+" + moneyFormat.format(ALUMINIUMPRICE));
                        System.out.println("4. Pickle\t+" + moneyFormat.format(PICKLEPRICE));
                        System.out.println("5. Sriracha\t+" + moneyFormat.format(SRIRACHAPRICE));
                        System.out.println("6. Spoiled milk\t+" + moneyFormat.format(SPOILEDMILKPRICE));
                        System.out.println("7. Mold\t\t+" + moneyFormat.format(MOLDPRICE));
                        System.out.println("8. Fish\t\t+" + moneyFormat.format(FISHPRICE));
                        flavourChoice = Integer.parseInt(br.readLine());//input menu choice of flavour
                        
                        switch(flavourChoice){//switch case for menu choice of flavour
                        
                            case 1:// CHOSE CHEESE
                                System.out.println("Some people will eat cheese with anything...");
                                stringFlavourChoice = "Cheese";
                                if(specialNumber == 1) {
                                    System.out.println(special+ " is 50% off today!");
                                    drinkPrice += (CHEESEPRICE)/2;
                                }else{
                                    drinkPrice += CHEESEPRICE;
                                }
                                
                            case 2://CHOSE KETCHUP
                                System.out.println("I wonder if you actually like this stuff");
                                stringFlavourChoice = "Ketchup";
                                if(specialNumber == 2) {
                                    System.out.println(special+ " is 50% off today!");
                                    drinkPrice += (KETCHUPPRICE)/2;
                                }else{
                                    drinkPrice += KETCHUPPRICE;
                                }
                                
                            case 3://CHOSE ALUMINIUM
                                System.out.println("This is good if you like the taste of day-old gum");
                                stringFlavourChoice = "Aluminium";
                                if(specialNumber == 3) {
                                    System.out.println(special+ " is 50% off today!");
                                    drinkPrice += (ALUMINIUMPRICE)/2;
                                }else{
                                    drinkPrice += ALUMINIUMPRICE;
                                }
                                break;
                                
                            case 4://CHOSE PICKLE
                                System.out.println("These are actually homemade!");
                                stringFlavourChoice = "Pickle";
                                if(specialNumber == 4) {
                                    System.out.println(special+ " is 50% off today!");
                                    drinkPrice += (PICKLEPRICE)/2;
                                }else{
                                    drinkPrice += PICKLEPRICE;
                                }
                                break;
                                
                            case 5://CHOSE SRIRACHA
                                System.out.println("Okay we get it, you have good spice tolerance");
                                stringFlavourChoice = "Sriracha";
                                if(specialNumber == 5) {
                                    System.out.println(special+ " is 50% off today!");
                                    drinkPrice += (SRIRACHAPRICE)/2;
                                }else{
                                    drinkPrice += SRIRACHAPRICE;
                                }
                                break;
                                
                            case 6://CHOSE SPOILED MILK
                                System.out.println("Please sign this waiver");
                                stringFlavourChoice = "Spoiled milk";
                                if(specialNumber == 6) {
                                    System.out.println(special+ " is 50% off today!");
                                    drinkPrice += (SPOILEDMILKPRICE)/2;
                                }else{
                                    drinkPrice += SPOILEDMILKPRICE;
                                }
                                break;
                                
                            case 7://CHOSE MOLD
                                System.out.println("We grow this in the back");
                                stringFlavourChoice = "Mold";
                                if(specialNumber == 7) {
                                    System.out.println(special+ " is 50% off today!");
                                    drinkPrice += (MOLDPRICE)/2;
                                }else{
                                    drinkPrice += MOLDPRICE;
                                }
                                
                            case 8://CHOSE FISH
                                System.out.println("Lucky you! I caught this one this morning");
                                stringFlavourChoice = "Fish";
                                if(specialNumber == 7) {
                                    System.out.println(special+ " is 50% off today!");
                                    drinkPrice += (FISHPRICE)/2;
                                }else{
                                    drinkPrice += FISHPRICE;
                                }
                                
                            default:
                            System.out.println("Stop messing around and select a valid option before I kick you out of the store");//UFP if they don't select a menu option
                            chosen = false;                        
                        }//end of flavour switch case
                    }while(!chosen);

					baseTea = stringFlavourChoice + " " + baseTea;//update baseTea with new flavour choice

					
					//CHOOSE TOPPINGS
                    System.out.println("-----------------------------------------------");
					System.out.println("How many toppings would you like? <max 5>");//UFP
                    numOfToppings = Integer.parseInt(br.readLine());
                    
                    for(int i = 1; i <= numOfToppings; i++){
                        System.out.println("-----------------------------------------------");
                        System.out.println("Choose a topping");
                        System.out.println("1. Wood chips\t\t+" + moneyFormat.format(WOODCHIPPRICE));
                        System.out.println("2. Tylenol\t\t+" + moneyFormat.format(TYLENOLPRICE));
                        System.out.println("3. Assorted cubed meat\t+" + moneyFormat.format(ASSORTEDCUBEDMEATPRICE));
                        System.out.println("4. Cucumber\t\t+" + moneyFormat.format(CUCUMBERPRICE));
                        System.out.println("5. Chalk\t\t+" + moneyFormat.format(CHALKPRICE));
                        System.out.println("6. Marbles\t\t+" + moneyFormat.format(MARBLEPRICE));
                        toppingChoice = Integer.parseInt(br.readLine());//input topping menu choice
                        
                        switch(toppingChoice){//switch case for topping menu choice
                        
                            case 0://this case gets triggered when the previous order included toppings but the current one bypassed this entire switch by choosing no toppings
                                break;

                            case 1://CHOSE WOOD CHIPS
                                System.out.println("Be careful of blisters inside of your throat");
                                stringToppingChoice = "wood chips";//meant for updating the baseTea variable
                                drinkPrice += WOODCHIPPRICE;//increment price
                                break;
                                
                            case 2://CHOSE TYLENOL
                                System.out.println("Bad headache?");
                                stringToppingChoice = "Tylenol";
                                drinkPrice += TYLENOLPRICE;
                                break;
                                
                            case 3://CHOSE ASSORTED CUBED MEAT
                                System.out.println("You don't need this much protien");
                                stringToppingChoice = "assorted cubed meat";
                                drinkPrice += ASSORTEDCUBEDMEATPRICE;
                                break;
                                
                            case 4://CHOSE CUCUMBER
                                System.out.println("This one is actually pretty normal, so we're gonna give you only the seeds");
                                stringToppingChoice = "cucumber";
                                drinkPrice += CUCUMBERPRICE;
                                break;
                                
                            case 5://CHOSE CHALK
                                System.out.println("I like the texture of chalk");
                                stringToppingChoice = "chalk";
                                drinkPrice += CHALKPRICE;
                                break;
                                
                            case 6://CHOSE MARBLES
                                drinkPrice += MARBLEPRICE;//increment before the switch case, as all marbles cost the same
    
                                System.out.println("Which colour marbles would you like?");//UFP
                                System.out.println("1. Red\n2. Blue\n3. Yellow\n4. Multicoloured");
                                marbleColourChoice = Integer.parseInt(br.readLine());//input menu choice
                                
                                switch(marbleColourChoice){//switch case for menu choice
                                
                                    case 1:// CHOSE RED
                                        System.out.println("Red is also my favourite colour!");
                                        stringToppingChoice = "red marbles";
                                        break;
                                        
                                    case 2:// CHOSE BLUE
                                        System.out.println("Blue is also my favourite colour!");
                                        stringToppingChoice = "blue marbles";
                                        break;
                                        
                                    case 3:// CHOSE YELLOW
                                        System.out.println("Yellow is also my favourite colour!");
                                        stringToppingChoice = "yellow marbles";
                                        break;
                                        
                                    case 4://CHOSE MULTICOLOURED
                                        System.out.println("I'm also indecisive!");
                                        stringToppingChoice = "multicoloured marbles";
                                        break;
                                        
                                    default:
                                        System.out.println("Stop messing around and select a valid option before I kick you out of the store");//UFP if they don't select a menu option
                                        i--;//in invalid option is entered, the counted loop will run again to account for missing topping
                                }//end of EXPLODING PEARL switch case
                                
                                break;
                                
                            default:
                                System.out.println("Stop messing around and select a valid option before I kick you out of the store");//UFP if they don't select a menu option
                                i--;//in invalid option is entered, the counted loop will run again to account for missing topping

                        }//end of TOPPINGS switch case

                        if(i == 1){//Selection for propper grammar
                            stringToppings = stringToppings + stringToppingChoice;
                        }else if(i != numOfToppings){
                            stringToppings = stringToppings +", " + stringToppingChoice;
                        }else{
                            stringToppings = stringToppings + ", and " + stringToppingChoice;
                        }

                    }//END OF COUNTED LOOP
                    if(numOfToppings != 0) baseTea = baseTea + " with " + stringToppings; //update our baseTea variable, use the word "with" to make it sound better 
                    //if statement for grammar


					//CHOOSING SIZE
                    chosen = true;
                    do{
                        chosen = true;
                        System.out.println("-----------------------------------------------");
                        System.out.println("Almost done!");
                        System.out.println("What size would you like today?");//UFP
                        System.out.println("Final cost is multiplied based on size");
                        System.out.println("1. Needle's worth (3mL)(needle included)\tx" + moneyFormat.format(SIZENEEDLEPRICE));
                        System.out.println("2. Tablespoon(measuring cup included)\t\tx" + moneyFormat.format(SIZETABLESPOONPRICE));
                        System.out.println("3. Bathtub's worth (180L)(Bathtub included)\tx" + moneyFormat.format(SIZEBATHTUBPRICE));
                        sizeChoice = Integer.parseInt(br.readLine());//menu choice for size
                        
                        switch(sizeChoice){//switch case for menu choice of size
                        
                        case 1://CHOSE NEEDLE'S WORTH
                            System.out.println("Please don't inject this");
                            stringSizeChoice = "A needle's worth of";//to add to our baseTea variable
                            drinkPrice *= SIZENEEDLEPRICE;//multiply price by size modifier
                            break;
                            
                        case 2://CHOSE TABLESPOON
                            System.out.println("Here is your measuring cup");
                            stringSizeChoice = "A tablespoon of";
                            drinkPrice *= SIZETABLESPOONPRICE;
                            break;
                            
                        case 3://CHOSE BATHTUB
                            System.out.println("Feeding a whole party are we?");
                            stringSizeChoice = "A bathtub full of";
                            drinkPrice += SIZEBATHTUBPRICE;
                            break;
                            
                        default:
                            System.out.println("Please select a valid option");//UFP if they don't select a menu option
                            chosen = false;
                        }//end of switch case
                        
                        baseTea = stringSizeChoice + " " + baseTea;//update base tea with size chosen
                    }while(!chosen);


                    System.out.println("-----------------------------------------------");
					System.out.println("You ordered a: " + baseTea);//UFP
					System.out.println("Your total cost is: $" + moneyFormat.format(drinkPrice));//UFP
					
					break;
					
										
				case 3://USER CHOOSES TO EXIT
					System.out.println("Goodbye");
					break;
					
				default:
                System.out.println("Stop messing around and select a valid option before I kick you out of the store");//UFP if they don't select a menu option
				
			}
			
            if(mainChoice ==2){//we dont want to update the reciept if they looked at the menu or decided to exit
                reciept = reciept + baseTea + "\n>>$" + moneyFormat.format(drinkPrice) + "\n";//data from this order is stored in reciept variable
            }
            totalPrice += drinkPrice;

            //SET ALL VARIABLES TO 0 OR EMPTY SO THAT DATA IS NOT CARRIED OVER BETWEEN ORDERS
            baseTea = "";
            drinkPrice = 0;
            stringToppingChoice = "";
            stringToppings ="";
            stringSizeChoice = "";
            stringFlavourChoice="";
            stringTypeBaseLiquidChoice="";
            mainChoice=0;
            liquidChoice = 0;
            typeThinLiquidChoice = 0;
            typeThickLiquidChoice = 0;
            toppingChoice = 0;
            marbleColourChoice = 0;
            flavourChoice = 0;
            sizeChoice = 0;
            typeOilChoice = 0;
			
			System.out.println("Welcome to "+SHOPNAME+"! What would you like today!");//rerun welcome prompt before while loop ends
			System.out.println("1. Menu\n2. Order a drink\n3. Exit");
			mainChoice = Integer.parseInt(br.readLine());//input main choice

		}
		
		System.out.println("That's everything for today?");//goodbye message

        System.out.println("You feel the cashier's stare burn into you...");//the Ontario special
        System.out.println("TIP: ");
        System.out.println("1. Good. (10%)");
        System.out.println("2. Great! (15%)");
        System.out.println("3. Amazing! (20%)");
        System.out.println("4. Wonderful! (25%)");
        System.out.println("5. Choose custom amount");
        tipChoice = Integer.parseInt(br.readLine());

        switch(tipChoice){//switch case for tip amount choice

            case 1:
                tipPercentage = 0.1;
                break;
            case 2:
                tipPercentage = 0.15;
                break;
            case 3:
                tipPercentage = 0.2;
                break;
            case 4:
                tipPercentage = 0.25;
                break;
            case 5:
                System.out.print("How much would you like to tip? <don't add a % sign>");
                tipPercentage = Integer.parseInt(br.readLine());
                tipPercentage /= 100;
                break;
            default:
                System.out.println("Stop messing around and select a valid option before I kick you out of the store");//UFP if they don't select a menu option
        }//end of tip choice switch

        //calculate tax, tip, total
        hst = totalPrice * TAX;
        tipAmount = totalPrice * tipPercentage;
        total = totalPrice + hst + tipAmount;


        //receipt
        System.out.println("\n===========================================================================");
        System.out.println(SHOPNAME);
        System.out.println("\"Delicious Teas!\"");
        System.out.println("201 Town Centre Blvd, Markham, ON");
        System.out.println("Tel: "+PHONENUM);
        System.out.println("===========================================================================");
		System.out.println(reciept);//our compounded variable containing all orders
        System.out.println("===========================================================================");
        System.out.println("Subtotal total: $"+moneyFormat.format(totalPrice));
        System.out.println("HST: $"+moneyFormat.format(hst));
        System.out.println("Tip: $"+moneyFormat.format(tipAmount));
        System.out.println("Total: $"+moneyFormat.format(total));
        System.out.println("===========================================================================");

	}//end of main
	
}//end of class