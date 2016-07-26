
public class Simulator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean isNight=false;
		// time initialization
		double bodyHeat=Math.random()*15+85;
		double dehydration=Math.random()*20 +70;
		double hungry=Math.random()*10+70;
		double tired=Math.random()*20+50;
		double[] properties={12, 0, bodyHeat, dehydration, hungry, tired, 0};
		boolean isAlive=true;
		// properties initialization
		int woodNumber=0;
		int bottleOfWater=3;
		int chocolateNumber=5;
		int fruitNumber=0;
		int tinderNumber=0;
		int ropeNumber=0;
		int rawMeatNumber=0;
		int cookedMeatNumber=0;
		boolean isHouse=true;
		boolean isSpear=false;
		// number of items initialization
		int action=0;
		/////////////////////////////////INITIALIZATION///////////////////////
		while(isAlive){
			System.out.print("This is the " + properties[0] + " of the " + properties[1] + " day. ");
			if(isNight){
				System.out.println("Night!");
			}
			else{
				System.out.println("Day time!");
			}
			System.out.println("Your body Heat is: " + properties[2] + ";		Your dehydration is: " + properties[3] + ";");
			System.out.println("Your hungry is: " + properties[4] + ";		Your tired is: " + properties[5] + ";");
			if(isHouse){
				System.out.print("You have a shelter! ");
			}
			else{
				System.out.print("You have no shelter! ");
			}
			System.out.println("And you have " + properties[6] + " hours of fire!");
			System.out.println("You have " + woodNumber + " woods, " + bottleOfWater + " bottles of water, " + chocolateNumber + " chocolates, "
					+ fruitNumber +" fruits, " + tinderNumber + " tinder, " + ropeNumber + " rope, " + rawMeatNumber +" raw meat, and " + cookedMeatNumber + " cooked meat!");
			if(isSpear){
				System.out.println("You have a spear!!");
			}
			else{
				System.out.println("You have no spear!!");
			}
			//Show player all the properties.
			String menu="";
			while(!menu.equalsIgnoreCase("m")){
				System.out.println("Press M and then Enter to go to the menu!");
				menu=TextIO.getlnString();
			}
			System.out.println("1. Sleep for a while.");
			System.out.println("2. Sleep in the shelter for a long time.");
			System.out.println("3. Look for and pick up something useful.");
			System.out.println("4. Look for rope and wood.");
			System.out.println("5. Consume something.");
			System.out.println("6. Fire.");
			System.out.println("7. Build a shelter.");
			System.out.println("8. Navigate, and move to another place!");
			System.out.println("9. Hunt small animals.");
			//Show the Menu of different actions.
			action=TextIO.getlnInt();
			//Get the action the player want
			if(properties[5]<1 && (action!=1 && action!=2 && action!=5) && isHouse){
				action=2;
			}
			else if((properties[5]<1 && (action!=1 && action!=2 && action!=5)) || (action==2 && !isHouse)){
				action=1;
			}
			if(action==1){
				properties[5]+=20;
				timePassing(properties, 1, true);
				if(properties[6]<1){
					properties[2]=properties[2]+30*properties[6];
				}
				else{
					properties[2]=properties[2]+30*1;
				}
			}
			else if(action==2){
				properties[5]=100;
				timePassing(properties, 6, false);
				if(properties[6]<6){
					properties[2]=properties[2]+30*properties[6];
				}
				else{
					properties[2]=properties[2]+30*6;
				}
			}
			else if(action==3){
				if(!isNight){
					fruitNumber+=(int)(Math.random()*5);
					tinderNumber+=(int)(Math.random()*3);
				}
				else{
					fruitNumber+=(int)(Math.random()*5/2);
					tinderNumber+=(int)(Math.random()*3/2);
				}
				properties[3]-=10;
				properties[4]-=5;
				properties[5]-=10;
				if(properties[2]>=30 && properties[4]>=20){
					timePassing(properties, 1, true);
				}
				else{
					timePassing(properties, 2, true);
				}
			}
			else if(action==4){
				if(!isNight){
					woodNumber+=(int)(Math.random()*5);
					ropeNumber+=(int)(Math.random()*3);
				}
				else{
					woodNumber+=(int)(Math.random()*5/2);
					ropeNumber+=(int)(Math.random()*3/2);
				}
				properties[3]-=10;
				properties[4]-=5;
				properties[5]-=10;
				if(properties[2]>=30 && properties[4]>=20){
					timePassing(properties, 1, true);
				}
				else{
					timePassing(properties, 2, true);
				}
			}
			else if(action==5){
				System.out.println("What do you want to consume?");
				System.out.println("1. water	2. chocolate	3. fruit	4. raw meat		5. cooked meat");
				int consume=TextIO.getlnInt();
				System.out.println("How many do you want to consume?");
				int number=TextIO.getlnInt();
				if(consume==1 && bottleOfWater>=number){
					bottleOfWater-=number;
					properties[3]=properties[3]+(20*number);
				}
				else if(consume==2 && chocolateNumber>=number){
					chocolateNumber-=number;
					properties[4]=properties[4]+10*number;
					properties[5]=properties[5]+5*number;
				}
				else if(consume==3 && fruitNumber>=number){
					fruitNumber-=number;
					properties[3]=properties[3]+10*number;
					properties[4]=properties[4]+5*number;
					properties[5]=properties[5]+2*number;
				}
				else if(consume==4 && rawMeatNumber>=number){
					rawMeatNumber-=number;
					properties[3]=properties[3]+4*number;
					properties[4]=properties[4]+15*number;
					properties[5]=properties[5]+3*number;
				}
				else if(consume==5 && cookedMeatNumber>=number){
					cookedMeatNumber-=number;
					properties[3]=properties[3]+2*number;
					properties[4]=properties[4]+25*number;
					properties[5]=properties[5]+5*number;
				}
			}
			else if(action==6){
				System.out.println("What do you want to do to the fire");
				System.out.println("1. Set Fire		2. Add wood		3. Cook meat");
				int fire=TextIO.getlnInt();
				if(fire==1 && woodNumber>0 && tinderNumber>0){
					woodNumber-=1;
					tinderNumber-=1;
					properties[6]=1;
					properties[5]-=5;
				}
				else if(fire==2 && properties[6]>0 && woodNumber>0){
					System.out.println("How many wood do you want to add?");
					int numberAdd=TextIO.getlnInt();
					if(woodNumber>=numberAdd){
						properties[6]=properties[6]+2*numberAdd;
						woodNumber-=numberAdd;
					}
					else{
						System.out.println("You only have " + woodNumber + " wood. You cannot add " + numberAdd + " wood!!");
					}
				}
				else if(fire==3 && properties[6]>0 && rawMeatNumber>0){
					System.out.println("How many meat do you want to cook? (1-5)");
					int cookNumber=TextIO.getlnInt();
					if(cookNumber<=rawMeatNumber){
						rawMeatNumber-=cookNumber;
						cookedMeatNumber+=cookNumber;
						properties[5]+=20;
						timePassing(properties, 1, true);
						properties[2]=properties[2]+30*1;
					}
					else{
						System.out.println("You don't have that much meat. Please try again!");
					}
				}
			}
			else if(action==7){
				System.out.println("What do you want to build? 1. Shelter	2. Spear");
				int build=TextIO.getlnInt();
				if(build==1){
					if(isHouse){
						System.out.println("You have already had a shelter!");
					}
					else if(woodNumber>=5 && ropeNumber>=3){
						woodNumber-=5;
						ropeNumber-=3;
						isHouse=true;
						properties[3]-=15;
						properties[5]-=15;
						properties[4]-=10;
						timePassing(properties, 1, true);
					}
				}
				else{
					if(isSpear){
						System.out.println("You have already had a spear! You don't need to build a new one!");
					}
					else{
						woodNumber-=3;
						isSpear=true;
						properties[5]-=10;
						timePassing(properties, 1, true);
					}
				}
			}
			else if(action==8){
				isHouse=false;
				properties[3]-=30;
				properties[5]-=30;
				properties[4]-=30;
				if(isNight){
					fruitNumber+=2;
					tinderNumber+=1;
				}
				else{
					fruitNumber+=3;
					tinderNumber+=2;
				}
				timePassing(properties, 6, false);
			}
			else if(action==9){
				if(isSpear){
					if(Math.random()>0.7){
						rawMeatNumber+=4;
						System.out.println("You have got 4 raw meat.");
					}
					else if(Math.random()>0.5){
						isSpear=false;
						System.out.println("Sorry, you didn't get any meat, and you have also lost your spear.");
					}
					else{
						System.out.println("Sorry, you didn't get any meat, but you still have your spear.");
					}
				}
				else{
					if(Math.random()>0.8){
						rawMeatNumber+=4;
						System.out.println("You have got 4 raw meat.");
					}
					else if(Math.random()>0.5){
						rawMeatNumber+=2;
						System.out.println("You have got 2 raw meat.");
					}
					else{
						System.out.println("Sorry, you didn't get any meat.");
					}
				}
				properties[3]-=15;
				properties[4]-=15;
				properties[5]-=15;
				timePassing(properties, 3, true);
			}
			//Different actions has different effects.
			if(properties[0]>=21 || properties[0]<=6){
				isNight=true;
			}
			else{
				isNight=false;
			}
			// Hungry > 20
			
			// Hungry < 20
			if(properties[2]>100){
				properties[2]=100;
			}
			if(properties[3]>100){
				properties[3]=100;
			}
			if(properties[4]>100){
				properties[4]=100;
			}
			if(properties[5]>100){
				properties[5]=100;
			}
			if(properties[5]<=0){
				properties[5]=0;
			}
			// If some properties is larger than 100, then just 100
			//Every time this always happens
			if(properties[2]<1 || properties[3]<1 || properties[4]<1){
				isAlive=false;
				System.out.println("You died from hypothermia!!!!!!!!!! You have lived " + properties[1] + " days, " + properties[0] +" hours.");
			}
			else if(properties[3]<1){
				isAlive=false;
				System.out.println("You died from dehydration!!!!!!!!!! You have lived " + properties[1] + " days, " + properties[0] +" hours.");
			}
			else if(properties[4]<1){
				isAlive=false;
				System.out.println("You died from out of energy!!!!!!!!!! You have lived " + properties[1] + " days, " + properties[0] +" hours.");
			}
			//is the player still alive?
		}
		
	}
	
	public static double[] timePassing(double[] properties, int hours, boolean isNormal){
		for(int i=0; i<hours; i++){
			properties[0]+=1;
			if(properties[0]==24){
				properties[1]+=1;
				properties[0]=0;
			}
			if(properties[6]>0){
				properties[6]-=hours;
			}
			if(properties[6]<0){
				properties[6]=0;
			}
			if(isNormal){
				if(properties[0]>=21 || properties[0]<=6){
					properties[2]=(properties[2]-10)*0.85;
					properties[3]=properties[3]-(2+(properties[3]-2)*0.97)/2;
				}
				else{
					properties[3]=(properties[3]-2)*0.97;
				}
				properties[4]=(properties[4]-1)*0.96;
			}
		}
		return properties;
	}

}
