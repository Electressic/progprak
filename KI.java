
import java.util.Random;

import AbstractNutzer.Zustand;

public class KI extends AbstractNutzer
{
	protected int SchwereGrad;
	Random rn;
	protected int[] SchussPossAlt = new int[2];
	protected Zustand[][] zustandSpielfeldGegner;
	enum Zustand {Wasser, Schiff_Normal, Schiff_Getroffen, Nichts};
	private static int intSpielfeldSize;
	public KI(int Schweregrad, int Spielfeldgroesse)
	{
		SchwereGrad = Schweregrad;
		rn = new Random();
		SchussPossAlt = new int[2];
		for(int i = 0; i < Spielfeldgroesse; i++)
		{
			for(int y = 0; y < Spielfeldgroesse; i++)
			{
				zustandSpielfeldGegner[i][y] = Zustand.Nichts;
			}
		}
		this.intSpielfeldSize = Spielfeldgroesse;
	}
	
	public String Schiesse()
	{
		if(SchwereGrad == 0)
		{
			SchussPossAlt[0] = rn.nextInt() % intSpielfeldSize;
			SchussPossAlt[1] = rn.nextInt() % intSpielfeldSize;
		}
		else if(SchwereGrad == 1)
		{
			//Abfrage im Grid, welches Nachbarfeld schon beschossen wurde 
		}
		else if(SchwereGrad == 2)
		{
			//Abfrage, wo ein Schiff liegt und wenn eins versenkt wurde bewusst daneben schiessen
			int x = rn.nextInt() % intSpielfeldSize;
			int y = rn.nextInt() % intSpielfeldSize;
			
			
			while(zustandSpielfeldGegner[x][y] != Zustand.Nichts)
			{
				x = rn.nextInt() % intSpielfeldSize;
				y = rn.nextInt() % intSpielfeldSize;
			}
			SchussPossAlt[0] = x;
			SchussPossAlt[1] = y;		
		}
		return "shot " + SchussPossAlt[0] + " " + SchussPossAlt[1]; 
	}
	public void AntwortShot(String strAntwort)
	{
		String[] strArrAntwort = strAntwort.split(" ");
		int Answer = Integer.parseInt(strArrAntwort[1]);
		
		if(Answer == 0)
		{
			zustandSpielfeldGegner[SchussPossAlt[0]][SchussPossAlt[1]] = Zustand.Wasser;
		}
		else if(Answer == 1)
		{
			zustandSpielfeldGegner[SchussPossAlt[0]][SchussPossAlt[1]] = Zustand.Schiff_Getroffen;			
		}
		else if(Answer == 2)
		{
			zustandSpielfeldGegner[SchussPossAlt[0]][SchussPossAlt[1]] = Zustand.Schiff_Getroffen;			
		}
	}
}
