package progprak.src.Logik;
import progprak.src.UI.UI;
import java.util.Random;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class Spielfeld
{
	protected int key;
	public Ship ship = new Ship();
	enum Zustand {Wasser, Schiff, Schiffhit, Miss};
	protected Zustand[][] zustandSpielfeld;
	protected Zustand[][] zustandEnemySpielfeld;

	public static int SpielfeldSize = 31;
	protected String strAbfrageString = "";
	String [][] aiShots = new String[getSpielfeldSize()][getSpielfeldSize()];
	String [][] playerShots = new String[getSpielfeldSize()][getSpielfeldSize()];
	public Random rn = new Random();
	Color statusColor;

	//--------------- Initialisierung vom Spielfeld---------------------------------------------------
	public Spielfeld (int Size) {
		zustandSpielfeld = new Zustand[Size][Size];
		zustandEnemySpielfeld = new Zustand[Size][Size];
		for(int i = 0; i < aiShots[0].length; i++)
		{
			for(int j = 0; j < aiShots[1].length; j++)
			{
				aiShots[i][j] = "0";
				playerShots[i][j] = "0";
			}
		}
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

	public int getKey() {
		return key;
	}

	public int setKey(int value) {
		this.key += value;
		return key;
	}
	public int resetKey(int value) {
		this.key = value;
		return key;
	}
	public Color getStatusColor() {
		return statusColor;
	}

	public Color setStatusColor(int row, int col, Color color) {
		this.statusColor = color;
		return color;
	}
	// Speichern und Laden was noch geupdated werden muss
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
            //for(int i = 0; i < vorhandeneSchiffe.length; i++)
            //{
            	//pWriter.print(vorhandeneSchiffe[i].getShipDataAsString());
            	pWriter.print("\n");
            //}
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
        			
        			
        			//InitializeShip(intGroesse);
        			//setzeSchiff(StartPosX, StartPosY, rRichtung, vorhandeneSchiffe[vorhandeneSchiffe.length - 1]);

        			
        			//vorhandeneSchiffe[vorhandeneSchiffe.length - 1].setAnzahlTreffer(AnzahlTreffer);
        			//vorhandeneSchiffe[vorhandeneSchiffe.length - 1].setRichtung(rRichtung);
        		}
        	}                
        	obj.close();
        }
        catch (Exception e)
        {
        	System.out.println(e.getMessage());
        } 
	}
	//----------Funktionen final für Feld initialisieren + Feld darstellen + Schiffe setzen + Schiffe schießen----------------------------
	public void initFeld () {
		for (int col = 0; col < getSpielfeldSize(); col++) {
			for (int row = 0; row < getSpielfeldSize(); row++) {
				setZustandSpielfeld(row, col, -1);
			}
		}
	}
	public void initEnemyFeld () {
		for (int col = 0; col < getSpielfeldSize(); col++) {
			for (int row = 0; row < getSpielfeldSize(); row++) {
				setZustandEnemySpielfeld(row, col, -1);
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
	public void placeShips (int row, int col) {
		int size;
		for (int i = 0; i < 1; i++)
		{
			key = getKey();
			size = ship.getFleet().get(key);
			for (int j = 0; j < size; j++)
			{
					if(ship.getRichtung() == true) {
						setZustandSpielfeld(row +j , col,key);
						/*
						setZustandSpielfeld(row +j , col,key);
						setZustandSpielfeld(row -1 , col,-3);
						setZustandSpielfeld(row -1 , col +1,-3);
						setZustandSpielfeld(row -1 , col -1,-3);
						setZustandSpielfeld(row +j + 1 , col,-3);
						setZustandSpielfeld(row +j + 1 , col+1,-3);
						setZustandSpielfeld(row +j + 1 , col-1,-3);
						setZustandSpielfeld(row +j , col + 1,-3);
						setZustandSpielfeld(row +j , col -1,-3);
						setStatusColor(row + j, col, Color.black);
						 */
					} else {
						setZustandSpielfeld(row, col + j,key);
					}
			}
		}displayFeld();
	}
	public void placeEnemyShips (int row, int col) {
		int size;
		for (int i = 0; i < 1; i++)
		{
			key = getKey();
			size = ship.getEnemyFleet().get(key);
			for (int j = 0; j < size; j++)
			{
				if(ship.getRichtung() == true) {
					setZustandEnemySpielfeld(row +j , col,key);
						/*
						setZustandSpielfeld(row +j , col,key);
						setZustandSpielfeld(row -1 , col,-3);
						setZustandSpielfeld(row -1 , col +1,-3);
						setZustandSpielfeld(row -1 , col -1,-3);
						setZustandSpielfeld(row +j + 1 , col,-3);
						setZustandSpielfeld(row +j + 1 , col+1,-3);
						setZustandSpielfeld(row +j + 1 , col-1,-3);
						setZustandSpielfeld(row +j , col + 1,-3);
						setZustandSpielfeld(row +j , col -1,-3);
						setStatusColor(row + j, col, Color.black);
						 */
				} else {
					setZustandEnemySpielfeld(row, col + j,key);
				}
			}
		}displayEnemyFeld();
	}
	public void shoot (int y, int x) {
		int temp;
		if (getZustandEnemySpielfeld(x, y) > 0) {
			temp = ship.enemyFleet.get(getZustandEnemySpielfeld(x,y));
			ship.enemyFleet.replace(getZustandEnemySpielfeld(x,y), --temp);
			if (temp <= 0) {
				setString("shipsunk");
				playerShots[x][y] = "shipsunk";
				return;
			}
			setString("shiphit");
			playerShots[x][y] = "shiphit";
			return;
		}
		setString("miss");
		playerShots[x][y] = "miss";
	}
	public void enemyShoot (int y, int x) {
		int temp;

		if (getZustandSpielfeld(x, y) > 0) {
			temp = ship.fleet.get(getZustandSpielfeld(x,y));
			System.out.println("test2: " + temp);
			ship.fleet.replace(getZustandSpielfeld(x,y), --temp);
			if (temp <= 0) {
				setString("shipsunk");
				setZustandSpielfeld(x,y,0);
				return;
			}
			setString("shiphit");
			setZustandSpielfeld(x,y,0);
			return;
		}
		setString("miss");
	}
	public void aiShoot(int x, int y) {
		while(aiShots[x][y].equals("0") == false)
		{
			x = rn.nextInt() % getSpielfeldSize();
			y = rn.nextInt() % getSpielfeldSize();
		}
		int temp;
		if (getZustandSpielfeld(x, y) > 0) {
			temp = ship.fleet.get(getZustandSpielfeld(x,y));
			ship.fleet.replace(getZustandSpielfeld(x,y), --temp);
			if (temp <= 0) {
				setString("shipsunk");
				return;
			}
			setString("shiphit");
			aiShots[x][y] = "shiphit";
			return;
		}
		setString("miss");
	}
	private void setString(String strText)
	{
		this.strAbfrageString = strText;
	}
	public String getString()
	{
		return this.strAbfrageString;
	}
	/*
	// Methode um die Nachbarfelder zu pr�fen
	public void checkArea (final Object neighbours) {
		for (int i = 0; i < neighbourStorage.length; i++) {
			neighbourStorage[i] = null;
		}

		int index = 0;
		for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
			for (int colOffset = -1; colOffset <= 1; colOffset++) {
				// Make sure that we don't count ourselves
				if (rowOffset == 0 && colOffset == 0) {
					continue;
				}
				int rowValue = row + rowOffset;
				int colValue = col + colOffset;

				if (rowValue < 0 || rowValue >= Spielfeld.getSpielfeldSize()
						|| colValue < 0 || colValue >= Spielfeld.getSpielfeldSize()) {
					continue;
				}

				//neighbours[index++] = getZustandSpielfeld(rowValue, colValue);
			}
		}
	}
	//Nicht sicher ob bisher funktioniert so wie gedacht! soll die Area dann am ende aufdecken
	public void updateArea () {
		for (Object neighbour : neighbourStorage) {
			if (neighbour == null) {
				break;
			}
			if (neighbour.equals("-2")) {
				value++;
			}
		}
	}

	 */
}
