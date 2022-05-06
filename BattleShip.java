
public class BattleShip implements InterfaceSpielfeld
{
	protected int size;
	protected int startpoosition;
	protected int Richtung;
	protected int anzahlTreffer;
	
	public BattleShip(int groesse)
	{
		this.size = groesse;
		this.anzahlTreffer = 0;
	}
	
	public void setStartposition(int intStart)
	{
		this.startpoosition = intStart;
	}
	public int getroffen()
	{
		//Bei 1 ist das Schiff versenkt,
		//Bei 0 noch nicht
		if(++anzahlTreffer == size)
		{
			return 1;
		}
		return 0;
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
	
	public String getPlayer1()
	{
		return "Hier sollen Daten vom Zweiten Spieler geladen werden";
	}
	
	public void player2turn()
	{
		System.out.println("Spieler 2 ist am Zug");
	}
	
	public void showScreen()
	{
		System.out.println("Hallo, hier werden die Daten angezeigt");
	}
	
	public void showScreen(String strText)
	{
		System.out.println(strText);
	}
	
	public void player1Turn()
	{
		System.out.println("player1Turn");
	}
	
	public String getPlayer2Data()
	{
		return "getPlayer2Data";
	}
	
	public String getFleet()
	{
		System.out.println("Bekomme Flotte");
		return "bekomme Flotte";
	}
	
	
	
	enum Richtung {Rechts, Unten, Links, Oben };	
	class RichtungAbfrage
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
}
