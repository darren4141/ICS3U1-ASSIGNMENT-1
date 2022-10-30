import java.io.*;
import java.text.DecimalFormat;


public class ExtensionArraysAndFunctions{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static DecimalFormat moneyFormat = new DecimalFormat(".00");

    static final String PHONENUM = "647-BBL-TEA";
    static final String SHOPNAME = "Darren's delicious bubble tea";
    static double hst, tipAmount, total; 
    static double totalPrice = 0;
    static String reciept = "";
    static double drinkPrice = 0;


    static boolean chosen;
    static int choice;
    static String name;

    static String baseTea;
    static String stringAdd="";

    static double tipPercentage = 0;



    public static void main(String[]args) throws IOException{

        //GENERAL VARIABLES
        final double TAX = 0.13;
        int numOfToppings;
        int tipChoice;
        String stringToppings = "";

        int mainChoice;

        //MENU CHOICE VARIABLES
        int typeBaseChoice;

        //DECLARE OIL GROUP VARIABLES
        final String [] OILMENU = {"Burger oil", "Crude oil", "Olive oil"};
        final String [] OILS = {"burger oil", "crude oil", "olive oil"};
        String [] OILMESSAGES = {
            "We cooked an entire burger and threw it out just to gather this oil",
            "This one is my personal favourite of the three",
            "This stuff is so expensive nowadays"
        };
        final double [] OILPRICES = {17, 25, 18};
        final String OILMAINMESSAGE = "You want an oil drink? Okay.\nWhat type of oil base would you like?";

        //DECLARE THIN LIQUID GROUP VARIABLES
        final String[] THINLIQUIDMENU= {"Rainwater", "Disinfectant", "Saltwater"};
        final String[] THINLIQUIDS= {"rainwater", "disinfectant", "saltwater"};
        String[] THINLIQUIDMESSAGES = {
            "This option saves us money",
            "I hope you're not planning on drinking this",
            "What is wrong with you "+name
        };
        final double [] THINLIQUIDPRICES = {6, 12, 8};
        final String THINLIQUIDMAINMESSAGE = "On a diet today "+name+"?\nWhat type of thin liquid would you like?";
        
        //DECLARE THICK LIQUID GROUP VARIABLES
        final String[] THICKLIQUIDMENU= {"Ink", "Sewage waste", "Soap"};
        final String[] THICKLIQUIDS= {"ink", "sewage waste", "soap"};
        String[] THICKLIQUIDMESSAGES = {
            "I heard this stuff will make your insides black",
            "Did you know they pay us to take this stuff?",
            "Just because it smells good doesn't mean it'll taste good"
        };
        final double [] THICKLIQUIDPRICES = {20, 1, 10};
        final String THICKLIQUIDMAINMESSAGE = "Thick liquids are better for your throat.\nWhat type of thick liquid would you like "+name+"?";
        
        //DECLARE FLAVOUR GROUP VARIABLES 
        final String[] FLAVOURMENU = {"Cheese", "Ketchup", "Aluminium", "Pickle", "Sriracha", "Spoiled milk", "Mold", "Fish"};
        final String[] FLAVOURS = {"cheese", "ketchup", "aluminium", "pickle", "sriracha", "spoiled milk", "mold", "fish"};
        String[] FLAVOURMESSAGES = {
            "I guess "+name+" is one of those people will eat cheese with anything...",
            "I wonder if you actually like this stuff",
            "This is good if you like the taste of day-old gum",
            "These are actually homemade!",
            "Hey everyone, "+name+" good spice tolerance! Suprising. No one cares.",
            "Alright "+name+" i'm gonna need you to sign this waiver",
            "We grow this in the back",
            "Lucky you "+name+"! I caught this one this morning"
        };
        final double [] FLAVOURPRICES = {7, 2, 4, 3, 5, 7, 1, 6};
        final String FLAVOURMAINMESSAGE = name+", guess what time it is. It's time to choose from our delicious flavours!\nWhat flavour would you like?";

        //DECLARE TOPPING GROUP VARIABLES
        final String[] TOPPINGMENU = {"Wood chips", "Tylenol", "Assorted cubed meat", "Cucumber", "Chalk"};
        final String[] TOPPINGS = {"wood chips", "Tylenol", "assorted cubed meat", "cucumber", "chalk"};
        String[] TOPPINGMESSAGES = {
            "Be careful of blisters inside of your throat",
            "Bad headache "+name+"?",
            "You don't need this much protien "+name,
            "This one is actually pretty normal",
            "I like the texture of chalk",
        };
        final double [] TOPPINGPRICES = {2, 10, 5, 3, 3};
        final String TOPPINGMAINMESSAGE = "";

        //DECLARE SIZE GROUP VARIABLES
        final String[] SIZEMENU = {"Needle's worth (3mL)(needle included)", "Tablespoon(measuring cup included)", "Bathtub's worth (180L)(Bathtub included)"};
        final String[] SIZES = {"A needle's worth of", "A tablespoon of", "A bathtub full of"};
        String[] SIZEMESSAGES = {
            "Please don't inject this",
            "Here is your measuring cup",
            "Feeding a whole party are we "+name+"?"
        };
        final double[] SIZEPRICES = {0.5, 1, 100};
        final String SIZEMAINMESSAGE = "Almost done "+name+"!\nWhat size would you like today?\nFinal cost is multiplied based on size";

        //WELCOME
        System.out.println("Welcome to "+SHOPNAME+"!");
        System.out.println("What is your name?");
        name = br.readLine();

		System.out.println("Welcome "+name+"! What would you like today?");
		System.out.println("1. Menu\n2. Order\n3. Exit");
		mainChoice = Integer.parseInt(br.readLine()); //input choice

        while(mainChoice != 3){
            switch(mainChoice){
                case 1:
                    printMenu();
                    break;
                case 2:
                    do{
                        chosen = true;
                        System.out.println("-----------------------------------------------");
                        System.out.println("What type of liquid would you like today "+name+"?");//UFP
                        System.out.println("1. Oil\n2. Thin liquid\n3. Thick liquid");
                        typeBaseChoice = Integer.parseInt(br.readLine());//input option for which type of liquid they would like
            
                        switch(typeBaseChoice){
                            case 1:
                                baseTea = orderMenu(3, OILS, OILMENU, OILMESSAGES, OILPRICES, OILMAINMESSAGE, false);
                                break;
                            case 2:
                                baseTea = orderMenu(3, THINLIQUIDS, THINLIQUIDMENU, THINLIQUIDMESSAGES, THINLIQUIDPRICES, THINLIQUIDMAINMESSAGE, false);
                                break;
                            case 3:
                                baseTea = orderMenu(3, THICKLIQUIDS, THICKLIQUIDMENU, THICKLIQUIDMESSAGES, THICKLIQUIDPRICES, THICKLIQUIDMAINMESSAGE, false);
                                break;
                            default:
                                System.out.println("Listen here "+name+", stop messing around and select a valid option before I kick you out of the store");//UFP if they don't select a menu option
                                chosen = false;
                        }
                    }while(!chosen);

                    baseTea = orderMenu(8, FLAVOURS, FLAVOURMENU, FLAVOURMESSAGES, FLAVOURPRICES, FLAVOURMAINMESSAGE, false) + " " + baseTea;

                    System.out.println("-----------------------------------------------");
					System.out.println("How many toppings would you like "+name+"? <max 5>");
                    numOfToppings = Integer.parseInt(br.readLine());
                    for(int i = 1; i <= numOfToppings; i++){
                        if(i == 1){//Selection for propper grammar
                            stringToppings = stringToppings + orderMenu(5, TOPPINGS, TOPPINGMENU, TOPPINGMESSAGES, TOPPINGPRICES, TOPPINGMAINMESSAGE, false);
                        }else if(i != numOfToppings){
                            stringToppings = stringToppings +", " + orderMenu(5, TOPPINGS, TOPPINGMENU, TOPPINGMESSAGES, TOPPINGPRICES, TOPPINGMAINMESSAGE, false);
                        }else{
                            stringToppings = stringToppings + ", and " + orderMenu(5, TOPPINGS, TOPPINGMENU, TOPPINGMESSAGES, TOPPINGPRICES, TOPPINGMAINMESSAGE, false);
                        }
                    }
                    if(numOfToppings != 0) baseTea = baseTea + " with " + stringToppings; //update our baseTea variable, use the word "with" to make it sound better 
                    //if statement for grammar

                    baseTea = orderMenu(3, SIZES, SIZEMENU, SIZEMESSAGES, SIZEPRICES, SIZEMAINMESSAGE, true) +" "+ baseTea;
                    break;
                case 3:
                    System.out.println("Goodbye "+name+"! We expect to see you again!");
                    break;

                default:
                    System.out.println("Listen here "+name+", stop messing around and select a valid option before I kick you out of the store");//UFP if they don't select a menu option


            }

            System.out.println("-----------------------------------------------");
            System.out.println(name+", you ordered a: " + baseTea);//UFP
            System.out.println("Your total cost is: $" + moneyFormat.format(drinkPrice));//UFP

            if(mainChoice == 2){//we dont want to update the reciept if they looked at the menu or decided to exit
                reciept = reciept + name + "'s order: \n" +baseTea + "\n>>$" + moneyFormat.format(drinkPrice) + "\n";//data from this order is stored in reciept variable
            }
            totalPrice += drinkPrice;

            name = "";
            baseTea = "";
            drinkPrice = 0;
            mainChoice=0;
            typeBaseChoice=0;

            System.out.println("Welcome to "+SHOPNAME+"!");
            System.out.println("What is your name?");
            name = br.readLine();

            System.out.println("Welcome "+name+"! What would you like today?");
            System.out.println("1. Menu\n2. Order\n3. Exit");
            mainChoice = Integer.parseInt(br.readLine()); //input choice
        }

        System.out.println("That's everything for today "+name+"?");//goodbye message
        System.out.println("You feel the cashier's stare burn into you...");//the Ontario special
        System.out.println("Go ahead and tip "+name+"!");//the Ontario special
        System.out.println("TIP: ");
        System.out.println("1. Good. (10%)");
        System.out.println("2. Great! (15%)");
        System.out.println("3. Amazing! (20%)");
        System.out.println("4. Wonderful! (25%)");
        System.out.println("5. Choose custom amount");
        tipChoice = Integer.parseInt(br.readLine());//input tip choice option

        tip(tipChoice);

        //calculate tax, tip, total
        hst = totalPrice * TAX;
        tipAmount = totalPrice * tipPercentage;
        total = totalPrice + hst + tipAmount;

        printReceipt();
    }

    static String orderMenu(int length, String[] options, String[] menu, String[] messages, double[] prices, String greeting, boolean isMultiplier) throws IOException{
        do{
            chosen = true;
            System.out.println("-----------------------------------------------");
            System.out.println(greeting);//UFP

            for(int i = 0; i <length; i++){
                if(!isMultiplier){
                    System.out.println((i+1)+". "+menu[i]+"\t+$"+moneyFormat.format(prices[i]));
                }else{
                    System.out.println((i+1)+". "+menu[i]+"\tx"+moneyFormat.format(prices[i]));
                }
            }

            choice = Integer.parseInt(br.readLine());

            switch(choice){
                case 1:
                    if(length >= 1){
                        System.out.println(messages[0]);
                        stringAdd = options[0];
                        if(!isMultiplier){
                            drinkPrice += prices[0];
                        }else{
                            drinkPrice *= prices[0];
                        }
                        break;
                    }
                case 2:
                    if(length >= 2){
                        System.out.println(messages[1]);
                        stringAdd = options[1];
                        if(!isMultiplier){
                            drinkPrice += prices[1];
                        }else{
                            drinkPrice *= prices[1];
                        }                        
                        break;
                    }                
                case 3:
                    if(length >= 3){
                        System.out.println(messages[2]);
                        stringAdd = options[2];
                        if(!isMultiplier){
                            drinkPrice += prices[2];
                        }else{
                            drinkPrice *= prices[2];
                        }
                        break;
                    }    
                case 4:
                    if(length >= 4){
                        System.out.println(messages[3]);
                        stringAdd = options[3];
                        if(!isMultiplier){
                            drinkPrice += prices[3];
                        }else{
                            drinkPrice *= prices[3];
                        }
                        break;
                    }    
                case 5:
                    if(length >= 5){
                        System.out.println(messages[4]);
                        stringAdd = options[4];
                        if(!isMultiplier){
                            drinkPrice += prices[4];
                        }else{
                            drinkPrice *= prices[4];
                        }
                        break;
                    }   
                case 6:
                    if(length >= 6){
                        System.out.println(messages[5]);
                        stringAdd = options[5];
                        if(!isMultiplier){
                            drinkPrice += prices[5];
                        }else{
                            drinkPrice *= prices[5];
                        }
                        break;
                    }   
                case 7:
                    if(length >= 7){
                        System.out.println(messages[6]);
                        stringAdd = options[6];
                        if(!isMultiplier){
                            drinkPrice += prices[6];
                        }else{
                            drinkPrice *= prices[6];
                        }
                        break;
                    }    
                case 8:
                    if(length >= 8){
                        System.out.println(messages[7]);
                        stringAdd = options[7];
                        if(!isMultiplier){
                            drinkPrice += prices[7];
                        }else{
                            drinkPrice *= prices[7];
                        }
                        break;
                    }     
                default:
                    System.out.println("Listen here "+name+", stop messing around and select a valid option before I kick you out of the store");//UFP if they don't select a menu option
                    chosen = false;
                }
        
            }while(!chosen);
            return stringAdd;
    }

    static void tip(int tipChoice) throws IOException{
        do{
            chosen = true;
            switch(tipChoice){//switch case for tip amount choice
                case 1:
                    tipPercentage = 0.1;//set tip percentage based on choice
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
                    System.out.println("How much would you like to tip? Not zero right "+name+"? I have a family to feed");
                    System.out.print("<don't add a % sign>");
                    tipPercentage = Integer.parseInt(br.readLine());//let them choose their percentage
                    tipPercentage /= 100;//convert percentage to decimal for operation
                    break;
                default:
                    System.out.println("Listen here "+name+", stop messing around and select a valid option before I kick you out of the store");//UFP if they don't select a menu option
                    chosen = false;
            }//end of tip choice switch
        }while(!chosen);

    }

    static void printMenu(){
        System.out.println("menu");
        /* 
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
        */
    }
    
    static void printReceipt(){
        System.out.println("\n===========================================================================");
        System.out.println(SHOPNAME);
        System.out.println("\"Delicious Teas!\"");
        System.out.println("201 Town Centre Blvd, Markham, ON");
        System.out.println("Tel: "+PHONENUM);
        System.out.println("===========================================================================");
		System.out.println(reciept);//our compounded variable containing all orders
        System.out.println("===========================================================================");
        System.out.println("Subtotal: $"+moneyFormat.format(totalPrice));//format to two decimal places
        System.out.println("HST: $"+moneyFormat.format(hst));
        System.out.println("Tip: $"+moneyFormat.format(tipAmount));
        System.out.println("Total: $"+moneyFormat.format(total));
        System.out.println("===========================================================================");
    }
}