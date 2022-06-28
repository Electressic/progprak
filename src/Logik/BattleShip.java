package progprak.src.Logik;

import java.awt.*;

public class BattleShip
{
	//--------------- alle Schiffgrößen und Schiffzähler---------------------------------------------------
	public static int shipSize;
	public static int shipCount;
	public static int shipCount2;
	public static int shipCount3;
	public static int shipCount4;
	public static int shipCount5;
	public static int shipCount6;
	public String shipModel;
	private int shipOwner;
	private Color shipColor;
	//--------------- code von Fabian---------------------------------------------------

	protected int startpoositionX;
	protected int startpoositionY;	
	protected int Richtung;
	protected int anzahlTreffer;
	protected int SpielfeldSize;
	Richtung r_richtung;
	BattleShip[] vorhandeSchiffe = new BattleShip[0];


	enum Richtung {Rechts, Unten, Links, Oben };

	public BattleShip(int groesse, int intSize)
	{
		switch (groesse) {
			case 2:
				shipSize = 2;
				this.shipColor = Color.red;
				break;
			case 3:
				shipSize = 3;
				this.shipColor = Color.red;
				break;
			case 4:
				shipSize = 4;
				this.shipColor = Color.red;
				break;
			case 5:
				shipSize = 5;
				this.shipColor = Color.red;
				break;
			case 6:
				shipSize = 6;
				this.shipColor = Color.red;
				break;
		}
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
	public void setRichtung(int intRichtung)
	{
		this.Richtung = intRichtung;
	}
	public void setRichtung(Richtung r_richtung)
	{
		this.r_richtung = r_richtung;
	}
	public void setAnzahlTreffer(int Anzahl)
	{
		this.anzahlTreffer = Anzahl;
	}
	
	public String getPlayer2()
	{
		return "Hier sollen Daten vom Zweiten Spieler geladen werden";
	}
	public int getRichtung()
	{
		return this.Richtung;
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
				"" + this.anzahlTreffer, 
				"" + this.getRichtung()
		};
		return ReturnString;
	}
	public String getShipDataAsString()
	{
		String ReturnString = 
					getGroesse() + " "  + 
					this.startpoositionX + " " + 
					this.startpoositionY  + " " + 
					this.anzahlTreffer + " " + 
					this.istVersenkt() +	" " + 
					this.getRichtung() + " ";
		
		return ReturnString;
	}
}
