package Logik;

import java.io.*;
import java.util.*;
import java.util.List;


public class Spielfeld
{
	protected int key;
	public static int SpielfeldSize = 31;
	protected String strAbfrageString = "";
	String [][] aiShots = new String[getSpielfeldSize()][getSpielfeldSize()];
	String [][] playerShots = new String[getSpielfeldSize()][getSpielfeldSize()];
	public Random rn = new Random();
	private static int[][] directions = new int[][]{{-1,-1}, {-1,0}, {-1,1},  {0,1}, {1,1},  {1,0},  {1,-1},  {0, -1}};
	public static List<Integer> res;
	// Gamestate Schiffe setzen oder Battle
	public String gameState;
	//which turn it is
	boolean isPlayerTurn;

	//--------------- Initialisierung vom Spielfeld---------------------------------------------------
	public Spielfeld() {
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
	public void setPlayerTurn(boolean playerTurn) {
		isPlayerTurn = playerTurn;
	}
	public boolean getPlayerTurn() {
		if (isPlayerTurn == true) {
			return true;
		}
		return false;
	}
	// getted, setted und resetted den Key um ihn mit der Map zu vergleichen
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
	// Speichern und Laden was noch geupdated werden muss
	public void speichern(String filename, Ship ship) throws Exception
	{
		try{
			File savefile = new File(filename);
			FileOutputStream fos = new FileOutputStream(savefile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			System.out.println(ship.getFleet());
			System.out.println(filename);
			oos.writeObject(ship.getFleet());
			oos.writeObject(getSpielfeldSize());
			oos.writeObject(getPlayerTurn());
			oos.writeObject(getAiShots());
			oos.writeObject(getPlayerShots());
			oos.flush();
			oos.close();
			fos.close();
		} catch(Exception e){}
	}
	public void laden(String strFile)
	{
		try {
			File toRead = new File(strFile);
			FileInputStream fis = new FileInputStream(toRead);
			ObjectInputStream ois = new ObjectInputStream(fis);
			HashMap<String,String> mapInFile= (HashMap<String,String>)ois.readObject();
			int sizeInFile = (int)ois.readObject();
			boolean playerTurnInFile = (boolean)ois.readObject();
			String[][] playerShotsInFile = (String[][]) ois.readObject();
			String[][] aiShotsInFile = (String[][]) ois.readObject();
			System.out.println(mapInFile);
			System.out.println(sizeInFile);
			System.out.println(playerTurnInFile);
			for (int i = 0; i < getSpielfeldSize(); i++){
				System.out.println(Arrays.toString((playerShotsInFile)[i]));
				System.out.println((aiShotsInFile)[i]);
			}

			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	// initialisiert beide Spielfelder
	public void initFeld (Ship ship) {
		//ship.setShipFleet(Spielfeld.getSpielfeldSize(),Spielfeld.getSpielfeldSize());
		for (int col = 0; col < getSpielfeldSize(); col++) {
			for (int row = 0; row < getSpielfeldSize(); row++) {
				ship.getShipFleet()[col][row] = -1;
			}
		}
	}
	public void initEnemyFeld(Ship ship) {
		for (int col = 0; col < getSpielfeldSize(); col++) {
			for (int row = 0; row < getSpielfeldSize(); row++) {
				ship.getEnemyShipFleet()[col][row] = -1;
			}
		}
	}
	public void displayFeld(Ship ship) {
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
				System.out.print(ship.getShipFleet()[row][col] + " ");
			}
			System.out.println();
		}
	}
	public void displayEnemyFeld(Ship ship) {
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
				System.out.print(ship.getShipFleet()[row][col] + " ");
			}
			System.out.println();
		}
	}

	// spoeichert die Nachbarn vom Click in ein Array
	public static void getSurroundings(int[][] matrix, int x, int y){ //java.util.List<Integer> getSurroundings(int[][] matrix, int x, int y){

		for (int[] direction : directions) {
			int cx = x + direction[0];
			int cy = y + direction[1];
			if(cy >=0 && cy < matrix.length)
				if(cx >= 0 && cx < matrix[cy].length)
					res.add(matrix[cy][cx]);
		}
		//return res;
	}
	//getted das Nachbararray
	public List<Integer> getListDirection () {
		return res;
	}
	//wird aufgerufen um die Schiffe zu platzieren
	public void placeShips(int row, int col, Ship ship) {
		int size;
		for (int i = 0; i < 1; i++)
		{
			key = getKey();
			size = ship.getFleet().get(key);
			for (int j = 0; j < size; j++)
			{
					if(ship.getRichtung() == true) {
						ship.getShipFleet()[row +j][col] = key;
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
						ship.getShipFleet()[row][col + j] = key;
					}
			}
		}
	}
	//wird aufgerufen um die Gegnerschiffe zu platzieren
	public void placeEnemyShips(int row, int col, Ship ship) {
		int size;
		for (int i = 0; i < 1; i++)
		{
			key = getKey();
			size = ship.getEnemyFleet().get(key);
			for (int j = 0; j < size; j++)
			{
				if(ship.getRichtung() == true) {
					ship.getShipFleet()[row + j][col] = key;
				} else {
					ship.getShipFleet()[row][col + j] = key;
				}
			}
		}displayEnemyFeld(ship);
	}
	//wird aufgerufen um auf das Gegnerspielfeld yu schiessen
	public void shoot(int y, int x, Ship ship) {
		int temp;
		if (ship.getShipFleet()[x][y] > 0) {
			temp = ship.getEnemyFleet().get(ship.getShipFleet()[x][y]);
			ship.getEnemyFleet().replace(ship.getShipFleet()[x][y], --temp);
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
	// moch kein Funktion theoretisch multiplayer?
	public void enemyShoot (int y, int x, Ship ship) {
		int temp;

		if (ship.getShipFleet()[x][y] > 0) {
			temp = ship.getFleet().get(ship.getShipFleet()[x][y]);
			System.out.println("test2: " + temp);
			ship.getFleet().replace(ship.getShipFleet()[x][y], --temp);
			if (temp <= 0) {
				setString("shipsunk");
				ship.getShipFleet()[x][y] = 0;
				return;
			}
			setString("shiphit");
			ship.getShipFleet()[x][y] = 0;
			return;
		}
		setString("miss");
	}
	//wird aufgerufen von ki um auf ein random feld zu schiessen
	public void aiShoot(int x, int y, Ship ship) {
		while(aiShots[x][y].equals("0") == false)
		{
			x = rn.nextInt(getSpielfeldSize());
			y = rn.nextInt(getSpielfeldSize());
		}
		int temp;
		if (ship.getShipFleet()[x][y]> 0) {
			temp = ship.getFleet().get(ship.getShipFleet()[x][y]);
			ship.getFleet().replace(ship.getShipFleet()[x][y], --temp);
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
	//setter und Getter um hit,miss,versunken zu bekommen
	private void setString(String strText)
	{
		this.strAbfrageString = strText;
	}
	public String getString()
	{
		return this.strAbfrageString;
	}
	public String[][] getAiShots() {
		return aiShots;
	}
	public String[][] getPlayerShots() {
		return playerShots;
	}
}
