package progprak.src.Logik;
import progprak.src.UI.UI;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Scanner;


public class Spielfeld
{
	public Ship ship = new Ship();
	enum Zustand {Wasser, Schiff, Schiffhit, Miss};
	protected Zustand[][] zustandSpielfeld;
	protected Zustand[][] zustandEnemySpielfeld;
	protected BattleShip[][] Ships = new BattleShip[0][0];
	protected BattleShip[] vorhandeneSchiffe;// = new BattleShip[0];
	public static int SpielfeldSize = 10;

	//--------------- Initialisierung vom Spielfeld---------------------------------------------------
	public Spielfeld (int Size) {
		zustandSpielfeld = new Zustand[Size][Size];
		zustandEnemySpielfeld = new Zustand[Size][Size];
		Ships = new BattleShip[Size][Size];
	}
	//Setter und Getter for BoardSize
	public static int getSpielfeldSize() {
		return SpielfeldSize;
	}
	public static int setSpielfeldSize(int spielfeldSize) {
		return SpielfeldSize = spielfeldSize;
	}
	//Setter und Getter for Player1 Zustand
	public void setZustandSpielfeld(int x, int y, int z) {
		ship.shipFleet[x][y] = z;
	}
	public int getZustandSpielfeld(int x, int y)
	{
		return ship.shipFleet[x][y];
	}
	//Setter und Getter for Player2 Zustand
	public void setZustandEnemySpielfeld(int x, int y, int z) {
		ship.enemyShipFleet[x][y] = z;
	}
	public int getZustandEnemySpielfeld(int x, int y) {
		return ship.enemyShipFleet[x][y];
	}
	// Speichern und Laden was noch geupdated werden muss
	/*
	public void speichern() throws Exception
	{
		System.out.println("speichern....");
		
        PrintWriter pWriter = null;
        String s = "/Users/Admin/Downloads/Neues Textdokument.txt";
        File file1 = new File(s);
        if(!file1.isFile() && !file1.isDirectory())
        {
            try 
            {
            	file1.createNewFile();
            }
        	catch(Exception e)
            {
            	throw new Exception(e.getMessage());
            }
        }
        try 
        {
            pWriter = new PrintWriter(new FileWriter(s));
            pWriter.println();
            for(int i = 0; i < this.SpielfeldSize; i++)
            {
            	for(int y = 0; y < this.SpielfeldSize; y++)
            	{
            		if(zustandSpielfeld[i][y] == Zustand.Wasser)
            		{
            			pWriter.print("W");
            		}
            		else if(zustandSpielfeld[i][y] == Zustand.Schiff)
            		{
            			pWriter.print("SN");
            		}
            		else if (zustandSpielfeld[i][y] == Zustand.Schiffhit)
            		{
            			pWriter.print("SG");
            		}
            		pWriter.print(" ");
            	}
            	pWriter.print("\n");
            }
            for(int i = 0; i < vorhandeneSchiffe.length; i++)
            {
            	pWriter.print(vorhandeneSchiffe[i].getShipDataAsString());
            	pWriter.print("\n");
            }
            pWriter.flush();
            pWriter.close();
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        } 
        finally 
        {
        	
        }
		System.out.println("Spiel gespeichert!");
	}
	void SpielLaden(String strFile)
	{
        File doc = new File("C:\\Users\\Admin\\Downloads\\TestDokument.txt");
        
        try 
        {
        	Scanner obj = new Scanner(doc);	
    		int intGroesse;
    		int StartPosX;
    		int StartPosY;
    		int AnzahlTreffer;
    		boolean rRichtung;
        	int SizeSpielfeld = Integer.parseInt(obj.nextLine());
        	String inhalt;
        	String[] ArrInhalt;
        	
        	for(int i = 0; i < SizeSpielfeld; i++)
        	{
        		inhalt = obj.nextLine();
        		ArrInhalt = inhalt.split(" ");
        		for(int y = 0; y < SizeSpielfeld; y++)
        		{
        			if(ArrInhalt[y].compareTo("W") == 0)
        			{
        				zustandSpielfeld[i][y] = Zustand.Wasser;
        			}
        			else if (ArrInhalt[y].compareTo("SN") == 0)
        			{
        				zustandSpielfeld[i][y] = Zustand.Schiff;
        			}
        			else if (ArrInhalt[y].compareTo("SG") == 0)
        			{
        				zustandSpielfeld[i][y] = Zustand.Schiffhit;
        			}
        		}

        		
        		//Hier die Schiffe noch initialisieren
        		while(obj.hasNextLine())
        		{
        			inhalt = obj.nextLine();
        			ArrInhalt = inhalt.split(" ");
            		intGroesse = Integer.parseInt(ArrInhalt[0]);
            		StartPosX = Integer.parseInt(ArrInhalt[1]);
            		StartPosY = Integer.parseInt(ArrInhalt[2]);
            		AnzahlTreffer = Integer.parseInt(ArrInhalt[3]);
            		rRichtung = Boolean.parseBoolean(ArrInhalt[4]);	
        			
        			
        			InitializeShip(intGroesse);
        			setzeSchiff(StartPosX, StartPosY, rRichtung, vorhandeneSchiffe[vorhandeneSchiffe.length - 1]);
        			
        			vorhandeneSchiffe[vorhandeneSchiffe.length - 1].setAnzahlTreffer(AnzahlTreffer);
        			vorhandeneSchiffe[vorhandeneSchiffe.length - 1].setRichtung(rRichtung);
        		}
        	}                
        	obj.close();
        }
        catch (Exception e)
        {
        	System.out.println(e.getMessage());
        } 
	}
	*/
	//----------Funktionen final für Feld initialisieren + Feld darstellen + Schiffe setzen + Schiffe schießen----------------------------
	public void initFeld () {
		for (int col = 0; col < getSpielfeldSize(); col++) {
			for (int row = 0; row < getSpielfeldSize(); row++) {
				setZustandSpielfeld(row, col, 20);
			}
		}
	}
	public void initEnemyFeld () {
		for (int col = 0; col < getSpielfeldSize(); col++) {
			for (int row = 0; row < getSpielfeldSize(); row++) {
				setZustandEnemySpielfeld(row, col, 20);
			}
		}
	}
	public void displayFeld() {
		System.out.print(" PL1");
		for (int row = 0; row < getSpielfeldSize(); row++) {
			if(row < 10)
			{
				System.out.print(" 0" + row);
			}
			else
				System.out.print(" " + row);
		}
		System.out.println(" ");
		for (int col = 0; col < getSpielfeldSize(); col++) {
			if(col < 10)
			{
				System.out.print(" 0" + col + " |");
			}
			else
				System.out.print(" " + col + " |");
			for (int row = 0; row < getSpielfeldSize(); row++) {
				System.out.print(getZustandSpielfeld(row,col ) + " ");
			}
			System.out.println();
		}
	}
	public void displayEnemyFeld() {
		System.out.print(" PL2");
		for (int row = 0; row < getSpielfeldSize(); row++) {
			if(row < 10)
			{
				System.out.print(" 0" + row);
			}
			else
				System.out.print(" " + row);
		}
		System.out.println(" ");
		for (int col = 0; col < getSpielfeldSize(); col++) {
			if(col < 10)
			{
				System.out.print(" 0" + col + " |");
			}
			else
				System.out.print(" " + col + " |");
			for (int row = 0; row < getSpielfeldSize(); row++) {
				System.out.print(getZustandEnemySpielfeld(row,col) + " ");
			}
			System.out.println();
		}
	}
	public void placeShips(ActionEvent e) {
		int k= 0;
		String[] coordinate = e.getActionCommand().split(",");
		int x = Integer.parseInt(coordinate[0]);
		int y = Integer.parseInt(coordinate[1]);
		for (int i= 0; i < ship.getAnzahlderSchiffe().length; i++) {
			for (int j= 0; j < ship.getAnzahlderSchiffe()[i]; j++) {
				if (ship.getAnzahlderSchiffe()[i] == 0) {
					break;
				} else {
					k++;
					for (int row = 0; row < getSpielfeldSize(); row++) {
						for (int col = 0; col < getSpielfeldSize(); col++) {
							for (int z = 0; z < i + 2; z++) {
								if (row == x && col == y) {
									if (ship.getRichtung() == true) {
										setZustandSpielfeld(row + z, col, k);
									} else {
										setZustandSpielfeld(row, col + z, k);
									}
								}
							}
						}
					}displayFeld();
				}
			}
		}
	}
	public void placeEnemyShips() {
		System.out.println("Test");
		int temp = 0;
		int key = 0;
		Scanner myObj = new Scanner(System.in);
		System.out.println("Enter Ship Koordinaten: ");
		for(int i = 0; i <ship.getAnzahlderSchiffe().length; i++)
		{
			for (int j = 0; j < ship.getAnzahlderSchiffe()[i]; j++) {
				key++;
				System.out.println("X Koordinate: ");
				int x = myObj.nextInt();  // Read user input
				System.out.println("Y Koordinate: ");
				int y = myObj.nextInt();  // Read user input
				boolean richtung = ship.setRichtung(myObj.nextBoolean());
				System.out.println("Koordinate Ship:" + x +"/" + y);
				for (int row = 0; row < getSpielfeldSize(); row++) {
					for (int col = 0; col < getSpielfeldSize(); col++) {
						for (int z = 0; z < i +2; z++) {
							if (row == x && col == y) {
								if (ship.getRichtung() == true) {
									setZustandSpielfeld(row + z, col, key);
								} else {
									setZustandSpielfeld(row, col + z, key);
								}
							}
						}
					}
				}
			}
		}
		displayFeld();
	}
	public void shoot () {
		Scanner shoot = new Scanner(System.in);
		int temp;
		System.out.println("Shootx: ");
		int shootx = shoot.nextInt();
		System.out.println("Shooty: ");
		int shooty = shoot.nextInt();
		for (int row = 0; row < getSpielfeldSize(); row++) {
			for (int col = 0; col < getSpielfeldSize(); col++) {
				// Wert von Button (i/j)
				if (row == shootx && col == shooty) {
					temp = ship.fleet.get(ship.getShipFleet()[row][col]);
					ship.fleet.replace(ship.getShipFleet()[row][col], --temp);
					System.out.println(ship.fleet.get(ship.getShipFleet()[row][col]));
				}
			}
		}
		displayFeld();
	}
}
