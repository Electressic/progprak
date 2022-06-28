package progprak.src.Logik;
public class BattleShip
{
	//--------------- alle Schiffgrößen und Schiffzähler---------------------------------------------------
	public static int battleshipSize;
	public static int battleshipSize2 = 2;
	public static int battleshipSize3 = 3;
	public static int battleshipSize4 = 4;
	public static int battleshipSize5 = 5;
	public static int battleshipSize6 = 6;
	public static int battleshipCount2;
	public static int battleshipCount3;
	public static int battleshipCount4;
	public static int battleshipCount5;
	public static int battleshipCount6;
	//--------------- code von Fabian---------------------------------------------------

	protected int startpoositionX;
	protected int startpoositionY;	
	protected boolean isHorizontal;
	protected int anzahlTreffer;
	protected int SpielfeldSize;

	BattleShip[] vorhandeSchiffe = new BattleShip[0];
	enum Richtung {Rechts, Unten, Links, Oben };

	public BattleShip(int groesse, int intSize)
	{
		//Ist das So? Dies muss ueberpr�ft werden!!!!
		this.battleshipSize = groesse;
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
			if(++anzahlTreffer == battleshipSize)
			{
				return 1;
			}		
		}
		return 0;
	}
	public boolean istVersenkt()
	{
		if(anzahlTreffer == battleshipSize)
		{
			return true;
		}
		return false;
	}
	public int getGroesse()
	{
		return this.battleshipSize;
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
