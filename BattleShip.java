
public class BattleShip implements InterfaceSpielfeld
{
	int size;
	int startpoosition;
	int Richtung;

	
	public BattleShip(int groesse)
	{
		this.size = groesse;
	}
	
	void setStartposition(int intStart)
	{
		this.startpoosition = intStart;
	}
	
	void setRichtung(int intRichtung)
	{
		this.Richtung = intRichtung;
	}
	
	enum Richtung {Rechts, Unten, Links, Oben };	
	class RichtungAbfrage
	{
		public int getIntRechts()
		{
			return 0;
		}
		public int getIntLinks()
		{
			return 1;
		}
		public int getIntOben()
		{
			return 2;
		}
		public int getIntUnten()
		{
			return 3;
		}
		public String getRichtung0()
		{
			return "Rechts";
		}
		public String getRichtung1()
		{
			return "Links";
		}
		public String getRichtung2()
		{
			return "Oben";
		}
		public String getRichtung3()
		{
			return "Unten";
		}
	}
}
