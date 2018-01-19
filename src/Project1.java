import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Project1 {

	public static void main(String[] args) {
	
		// Setup EZ graphics system.
		EZ.initialize(800,600);  // PIXEL picture element 

				
		EZ.setBackgroundColor(new Color(0,0,0)); 

		// Load image of track and store as "raceTrack"
		EZImage raceTrack = EZ.addImage("racetrackbackground.png", 400, 300);
		// Load image of racer one and store as "s76"
		EZImage s76 = EZ.addImage("s76.png", 75, 100);
		// Load image of racer one and store as "dva"
		EZImage dva = EZ.addImage("dva.png", 75, 300);
		// Load image of racer one and store as "pharah"
		EZImage pharah = EZ.addImage("pharah.png", 75, 500);

		EZSound dvalead = EZ.addSound("DvaLead.wav");
		EZSound dvawin = EZ.addSound("DvaWin.wav");
		EZSound s76lead = EZ.addSound("S76Lead.wav");
		EZSound s76win = EZ.addSound("S76Win.wav");
		EZSound phrhlead = EZ.addSound("PharahLead.wav");
		EZSound phrhwin = EZ.addSound("PharahWin.wav");

		
		// Make a random number generator and store it in a variable called randomGenerator
		Random randomGenerator;
		randomGenerator = new Random();
		
		// Make a random number from 0 to 6 and store it in the variable randomS76Speed
		int randomS76Speed = randomGenerator.nextInt(5)+1;
				
		// Make a random number from 0 to 6 and store it in the variable randomPharahSpeed
		int randomPharahSpeed = randomGenerator.nextInt(5)+1;
		
		
		// Declare an integer called s76X and assign to 75
		int s76X = 75;
		// Declare an integer called s76X and assign to 100
		int s76Y = 100;
				
		// Declare an integer called phrhX and assign to 75
		int phrhX = 75;
		// Declare an integer called s76X and assign to 500
		int phrhY = 500;
		
		boolean APressed = false;
		
		EZText textbox= EZ.addText(400, 50,"GO!", Color.white, 30);
		
		boolean game = true; //If true, game runs
		boolean dvaW = false; //If true, Dva wins
		boolean s76W = false; //If true, Soldier76 wins
		boolean phrhW = false; //If true, Pharah wins
		
		int dvai = 0; //Used for if Dva is ahead
		int s76i = 0; //Used for if Soldier 76 is ahead
		int phrhi = 0; //Used for if Pharah is ahead

		int dvaX = dva.getXCenter(); //Locate Dva X position
		
		int dva1; //Distance between Dva and Soldier 76
		int dva2; //Distance between Dva and Pharah
		int s761; //Distance between Soldier 76 and Dva
		int s762; //Distance between Soldier 76 and Pharah
		int phrh1; //Distance between Pharah and Dva
		int phrh2; //Distance between Pharah and Soldier 76

	
		while (game == true) 
		{
			
			if (EZInteraction.isKeyDown(KeyEvent.VK_A)){
				APressed = true;
			}
			// If I press both A and S
			if ((EZInteraction.isKeyDown(KeyEvent.VK_S))&&(APressed == true)) {
				// Then make dva moves forward
				dva.moveForward(50);
				APressed = false;
			}
				
			s76.translateTo(s76X, s76Y); // Set the position of s76
			
			s76X= s76X+(randomS76Speed);
			
			
			pharah.translateTo(phrhX, phrhY); // Set the position of pharah
			
			phrhX= phrhX+randomPharahSpeed;
			
			
			dvaX = dva.getXCenter();
			
			dva1 = dvaX - s76X; //Distance between Dva and Soldier 76
			dva2 = dvaX - phrhX; //Distance between Dva and Pharah
			s761 = s76X - dvaX; //Distance between Soldier 76 and Dva
			s762 = s76X - phrhX; //Distance between Soldier 76 and Pharah
			phrh1 = phrhX - dvaX; //Distance between Pharah and Dva
			phrh2 = phrhX - s76X; //Distance between Pharah and Soldier 76
			
			//If Dva is ahead of Soldier 76 and Pharah
			if ((dva1 > 50) && (dva2 > 50)){
				//Print Dva is in the lead
				textbox.setMsg("DVA IS IN THE LEAD");
				dvai++;
				s76i = 0;
				phrhi = 0;
					if (dvai == 1){
						//Announce Dva is in the lead
						dvalead.play();
						dvai++;
					}
			}	
			
			//If Soldier 76 is ahead of Dva and Pharah
			if ((s761 > 50) && (s762 > 50)){
				//Print Soldier 76 is in the lead
				textbox.setMsg("SOLDIER 76 IS IN THE LEAD");
				s76i++;
				dvai = 0;
				phrhi = 0;
					if (s76i == 1){
						//Announce Soldier 76 is in the lead
						s76lead.play();
						s76i++;
					}
			}
			
			//If Pharah is ahead of Dva and Soldier 76
			if ((phrh1 > 50) && (phrh2 > 50)){
				//Print Pharah is in the lead
				textbox.setMsg("PHARAH IS IN THE LEAD");
				phrhi++;
				dvai = 0;
				s76i = 0;
					if (phrhi == 1){
						//Announce Pharah is in the lead
						phrhlead.play();
						phrhi++;
					}
			}
			
			//If Dva reaches the end
			if (dvaX >= 700){
				//Dva wins
				dvaW = true;
				//Game ends
				game = false;
			}
			
			//If Soldier 76 reaches the end
			if (s76X >= 700){
				//Soldier 76 wins
				s76W = true;
				//Game ends
				game = false;
			}
			
			//If Pharah reaches the end
			if (phrhX >= 700){
				//Pharah wins
				phrhW = true;
				//Game ends
				game = false;
			}
			
			// Make sure to do this or else the graphics won't refresh
			EZ.refreshScreen();

		}
		//If Dva wins
		if (dvaW == true){
			//Print and announce Dva wins
			textbox.setMsg("DVA WINS!");
			dvawin.play();
		}
		//If Soldier 76 wins
		if (s76W == true){
			//Print and announce Soldier 76 wins
			textbox.setMsg("SOLDIER 76 WINS!");
			s76win.play();
		}
		//If Pharah wins
		if (phrhW == true){
			//Print and announce Pharah wins
			textbox.setMsg("PHARAH WINS!");
			phrhwin.play();
		}

		
	}
}
