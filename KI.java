import java.util.Random;

public class KI extends AbstractNutzer
{
	static int SchwereGrad;
	Random rn;
	static int[] SchussPossAlt;
	
	public KI(int Schweregrad)
	{
		SchwereGrad = Schweregrad;
		rn = new Random();
		SchussPossAlt = new int[2];
	}
	
	public int[] Schiesse()
	{
		int[] SchussPos = new int[2];

		if(SchwereGrad == 0)
		{
			SchussPos[0] = rn.nextInt() % SpielfeldImpl.Size;
			SchussPos[1] = rn.nextInt() % SpielfeldImpl.Size;
		}
		else if(SchwereGrad == 1)
		{
			
		}
		else if(SchwereGrad == 2)
		{
			
		}
		SchussPossAlt[0] = SchussPos[0];
		SchussPossAlt[1] = SchussPos[1];
		return SchussPos; 
	}
}
