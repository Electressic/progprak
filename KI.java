package test;
import java.util.Random;

public class KI extends Spielfeld
{
	protected int SchwereGrad;
	Random rn;
	protected int[] SchussPossAlt = new int[2];
	public KI(int Schweregrad, int Spielfeldgroesse)
	{
		super(Spielfeldgroesse);
		SchwereGrad = Schweregrad;
		rn = new Random();
		SchussPossAlt = new int[2];
	}
	
	public int[] Schiesse()
	{
		if(SchwereGrad == 0)
		{
			SchussPossAlt[0] = rn.nextInt() % super.getSpielfeldSize();
			SchussPossAlt[1] = rn.nextInt() % super.getSpielfeldSize();
		}
		else if(SchwereGrad == 1)
		{
			//Abfrage im Grid, welches Nachbarfeld schon beschossen wurde 
		}
		else if(SchwereGrad == 2)
		{
			//Abfrage, wo ein Schiff liegt und wenn eins versenkt wurde bewusst daneben schiessen
			int x = rn.nextInt() % super.getSpielfeldSize();
			int y = rn.nextInt() % super.getSpielfeldSize();
			
			while(super.SchiffNormal(super.getZustandPos(x, y)))
			{
				x = rn.nextInt() % super.getSpielfeldSize();
				y = rn.nextInt() % super.getSpielfeldSize();
			}
			SchussPossAlt[0] = x;
			SchussPossAlt[1] = y;		
		}
		return SchussPossAlt; 
	}
}
