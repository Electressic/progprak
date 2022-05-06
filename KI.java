import java.util.Random;

public class KI extends AbstractNutzer
{
	protected int SchwereGrad;
	Random rn;
	protected int[] SchussPossAlt = new int[2];
	protected int SpielfeldSize;
	public KI(int Schweregrad, int Spielfeldgroesse)
	{
		SchwereGrad = Schweregrad;
		rn = new Random();
		SchussPossAlt = new int[2];
		this.SpielfeldSize = Spielfeldgroesse;
	}
	
	public int[] Schiesse()
	{


		if(SchwereGrad == 0)
		{
			SchussPossAlt[0] = rn.nextInt() % SpielfeldSize;
			SchussPossAlt[1] = rn.nextInt() % SpielfeldSize;
		}
		else if(SchwereGrad == 1)
		{
			
		}
		else if(SchwereGrad == 2)
		{
			
		}

		return SchussPossAlt; 
	}
}
