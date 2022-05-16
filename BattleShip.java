

public class BattleShip extends AbstractSpielfeld
{
	protected int size;
	protected int startpoositionX;
	protected int startpoositionY;	
	protected int Richtung;
	protected int anzahlTreffer;
	
	public BattleShip(int groesse, int intSize)
	{
		//Ist das So? Dies muss ueberprüft werden!!!!
		this.size = groesse;
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
			if(++anzahlTreffer == size)
			{
				return 1;
			}		
		}
		return 0;
	}
	public boolean istVersenkt()
	{
		if(anzahlTreffer == size)
		{
			return true;
		}
		return false;
	}
	public int getGroesse()
	{
		return this.size;
	}
	public void setRichtung(int intRichtung)
	{
		this.Richtung = intRichtung;
	}
	public int getRichtung()
	{
		return this.Richtung;
	}
	
	public String getPlayer2()
	{
		return "Hier sollen Daten vom Zweiten Spieler geladen werden";
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

	
	enum Richtung {Rechts, Unten, Links, Oben };		
	static class RichtungAbfrage
	{
		public int getIntRechts()
		{
			return 0;
		}
		public int getIntUnten()
		{
			return 1;
		}
		public int getIntLinks()
		{
			return 2;
		}
		public int getIntOben()
		{
			return 3;
		}
		public String getRichtung0()
		{
			return "Rechts";
		}
		
		public String getRichtung1()
		{
			return "Unten";
		}
		
		public String getRichtung2()
		{
			return "Links";
		}
		
		public String getRichtung3()
		{
			return "Oben";
		}
	}

	@Override
	int getSpielfeldSize() 
	{
		return super.getSpielfeldSize(); 
	}
}
