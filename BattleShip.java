package progprak;
public class BattleShip
{
	protected static int battleshipSize;
	protected static int battleshipCount;
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
		//Ist das So? Dies muss ueberprüft werden!!!!
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
