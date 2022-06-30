package progprak.src.Logik;

import java.awt.*;

public class BattleShip
{
	//--------------- alle Schiffgrößen und Schiffzähler---------------------------------------------------
	public int shipSize;
	public static int shipCount;
	public static int shipCount2;
	public static int shipCount3;
	public static int shipCount4;
	public static int shipCount5;
	public static int shipCount6;
	public String shipModel;
	private int shipOwner;
	//--------------- code von Fabian---------------------------------------------------

	protected int startpoositionX;
	protected int startpoositionY;	
	protected boolean isHorizontal;
	protected int anzahlTreffer;
	protected int SpielfeldSize;

	enum Richtung {Rechts, Unten, Links, Oben };

	public BattleShip(int groesse)
	{
		this.shipSize = groesse;
		this.anzahlTreffer = 0;
	}
	
	public void setStartposition(int intStartX, int intStartY)
	{
		this.startpoositionX = intStartX;
		this.startpoositionY = intStartY;	
	}
	public int getroffen()
	{
		if(!istVersenkt())
		{
			if(++anzahlTreffer == shipSize)
			{
				return 1;
			}		
		}
		return 0;
	}
	public boolean istVersenkt()
	{
		if(anzahlTreffer == shipSize)
		{
			return true;
		}
		return false;
	}
	public int getGroesse()
	{
		return this.shipSize;

	}
	public void setRichtung(boolean intRichtung)
	{
		this.isHorizontal = intRichtung;
	}

	public void setAnzahlTreffer(int Anzahl)
	{
		this.anzahlTreffer = Anzahl;
	}
	
	public String getPlayer2()
	{
		return "Hier sollen Daten vom Zweiten Spieler geladen werden";
	}
	public boolean getRichtung()
	{
		return this.isHorizontal;
	}
	public String getPlayer1Data()
	{
		return "getPLayer1Data";
	}
	public int[] getStartPos()
	{
		int[] Pos = new int[2];
		Pos[0] = this.startpoositionX;
		Pos[1] = this.startpoositionY;
		return Pos;
	}

	public String[] getShipDataAsArray()
	{
		String[] ReturnString = 
		{
				"" + getGroesse(),
				"" + this.startpoositionX, 
				"" + this.startpoositionY,
				"" + this.getRichtung(),
				"" + this.anzahlTreffer, 
				"" + this.getRichtung()
		};
		return ReturnString;
	}
	public String getShipDataAsString()
	{
		String returnString = "";
		String[] ArrString = getShipDataAsArray();
		for(int i = 0; i < ArrString.length; i++)
		{
			returnString += ArrString[i] + " ";
		}
		return returnString;
	}
}
