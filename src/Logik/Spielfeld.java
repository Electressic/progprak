package progprak.src.Logik;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


enum Zustand {Wasser, Schiff_Normal, Schiff_Getroffen};
public class Spielfeld
{
	protected Zustand[][] zustandSpielfeld;
	protected BattleShip[][] Ships = new BattleShip[0][0];
	protected BattleShip[] vorhandeneSchiffe = new BattleShip[0];
	public static int SpielfeldSize = 15;

	//--------------- code von Fabian---------------------------------------------------

	public String getFleet()
	{
		if(getSpielfeldSize() >= 20)
		{
			return "ships 3 4 5 6";
		}
		return "ships 2 3 4 5";
	}
	public boolean setzeSchiff(int intStartPosX, int intStartPosY, int intRichtung, int intGroesse)
	{
		int Pruefwert = -1;
		if(intRichtung == 0)
		{
			Pruefwert = intStartPosX + intGroesse;
		}
		else if (intRichtung == 1)
		{
			Pruefwert = intStartPosY - intGroesse;
		}
		else if (intRichtung == 2)
		{
			Pruefwert = intStartPosX - intGroesse;
		}
		else if (intRichtung == 3)
		{
			Pruefwert = intStartPosY + intGroesse;
		}
		if(Pruefwert > getSpielfeldSize()  || Pruefwert < 0)
		{
			return false;
		}
		
		BattleShip ship = new BattleShip(intGroesse, getSpielfeldSize());
		int PosX = intStartPosX;
		int PosY = intStartPosY;
		//(0, 0) liegt unten Links
		for(int i = 0; i < intGroesse; i++)
		{
			Ships[PosX][PosY] = ship;
			if(intRichtung == 0)
			{
				PosX++;
			}
			else if(intRichtung == 1)
			{
				PosY--;
			}
			else if(intRichtung == 2)
			{
				PosX--;
			}
			else if(intRichtung == 3)
			{
				PosY++;
			}
		}
		BattleShip[] temp = new BattleShip[vorhandeneSchiffe.length + 1];
		for (int i = 0; i < vorhandeneSchiffe.length; i++)
		{
			temp[i] = vorhandeneSchiffe[i];
		}
		temp[temp.length - 1] = ship;
		vorhandeneSchiffe = temp;
		return true;
	}
	public Zustand getZustandPos(int x, int y)
	{
		return zustandSpielfeld[x][y];
	}
	public String PruefeSchuss(int x, int y)
	{
		if(zustandSpielfeld[x][y] == Zustand.Wasser)
		{
			return "answer 0";
		}
		else if(zustandSpielfeld[x][y] == Zustand.Schiff_Normal)
		{
			if(WelchesSchiff(x, y).getroffen() == 1) 
			{
				return "answer 2";
			}
			this.zustandSpielfeld[x][y] = Zustand.Schiff_Getroffen;
			return "answer 1";			
		}
		else if(zustandSpielfeld[x][y] == Zustand.Schiff_Getroffen)
		{
			return "answer 0";
		}
		return null;
	}
	public BattleShip WelchesSchiff(int x, int y)
	{
		if(getZustandPos(x, y) == Zustand.Wasser)
		{
			return null;
		}	
		return Ships[x][y];
	}

    public boolean SchiffNormal(Zustand zustand) 
    {        
        if(zustand == Zustand.Schiff_Normal) {
            return true;            
        }    
        return false;
    }
    public boolean SchiffGetroffen(Zustand zustand) 
    {        
        if(zustand == Zustand.Schiff_Getroffen) 
        {
            return true;            
        }    
        return false;
    }
    public boolean Wasser(Zustand zustand) 
    {        
        if(zustand == Zustand.Wasser) 
        {
            return true;            
        }    
        return false;
    }
    public boolean habeVerloren()
    {
    	int counter = 0;
    	for(int i = 0; i < vorhandeneSchiffe.length; i++)
    	{
    		if(vorhandeneSchiffe[i].istVersenkt())
    		{
    			counter++;
    		}
    	}
    	if(counter == vorhandeneSchiffe.length - 1)
    	{
    		return true;
    	}
    	return false;
    }
	public int getSpielfeldSize() 
	{
		return this.SpielfeldSize;
	}
	public void speichern() throws Exception
	{
		System.out.println("speichern....");
		
        PrintWriter pWriter = null;
        String s = "/Users/Admin/Downloads/Neues Textdokument.txt";
        File file1 = new File(s);
        if(!file1.isFile() && !file1.isDirectory())
        {
            try 
            {
            	file1.createNewFile();
            }
        	catch(Exception e)
            {
            	throw new Exception(e.getMessage());
            }
        }
        try 
        {
            pWriter = new PrintWriter(new FileWriter(s));
            pWriter.println();
            for(int i = 0; i < this.SpielfeldSize; i++)
            {
            	for(int y = 0; y < this.SpielfeldSize; y++)
            	{
            		if(zustandSpielfeld[i][y] == Zustand.Wasser)
            		{
            			pWriter.print("W");
            		}
            		else if(zustandSpielfeld[i][y] == Zustand.Schiff_Normal)
            		{
            			pWriter.print("SN");
            		}
            		else if (zustandSpielfeld[i][y] == Zustand.Schiff_Getroffen)
            		{
            			pWriter.print("SG");
            		}
            		pWriter.print(" ");
            	}
            	pWriter.print("\n");
            }
            for(int i = 0; i < vorhandeneSchiffe.length; i++)
            {
            	pWriter.print(vorhandeneSchiffe[i].getShipDataAsString());
            	pWriter.print("\n");
            }
            pWriter.flush();
            pWriter.close();
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        } 
        finally 
        {
        	
        }
		System.out.println("Spiel gespeichert!");
	}
	void SpielLaden(String strFile)
	{
        File doc = new File("C:\\Users\\Admin\\Downloads\\TestDokument.txt");
        
        try 
        {
        	Scanner obj = new Scanner(doc);	
    		int intGroesse;
    		int StartPosX;
    		int StartPosY;
    		int AnzahlTreffer;
    		int rRichtung;
        	int SizeSpielfeld = Integer.parseInt(obj.nextLine());
        	String inhalt;
        	String[] ArrInhalt;
        	
        	for(int i = 0; i < SizeSpielfeld; i++)
        	{
        		inhalt = obj.nextLine();
        		ArrInhalt = inhalt.split(" ");
        		for(int y = 0; y < SizeSpielfeld; y++)
        		{
        			if(ArrInhalt[y].compareTo("W") == 0)
        			{
        				zustandSpielfeld[i][y] = Zustand.Wasser;
        			}
        			else if (ArrInhalt[y].compareTo("SN") == 0)
        			{
        				zustandSpielfeld[i][y] = Zustand.Schiff_Normal;
        			}
        			else if (ArrInhalt[y].compareTo("SG") == 0)
        			{
        				zustandSpielfeld[i][y] = Zustand.Schiff_Getroffen;
        			}
        		}

        		
        		//Hier die Schiffe noch initialisieren
        		while(obj.hasNextLine())
        		{
        			inhalt = obj.nextLine();
        			ArrInhalt = inhalt.split(" ");
            		intGroesse = Integer.parseInt(ArrInhalt[0]);
            		StartPosX = Integer.parseInt(ArrInhalt[1]);
            		StartPosY = Integer.parseInt(ArrInhalt[2]);
            		AnzahlTreffer = Integer.parseInt(ArrInhalt[3]);
            		rRichtung = Integer.parseInt(ArrInhalt[4]);	
        			
        			
        			BattleShip newShip = new BattleShip(intGroesse, SizeSpielfeld);
        			
        			setzeSchiff(StartPosX, StartPosY, rRichtung, intGroesse);
        			
        			newShip.setAnzahlTreffer(AnzahlTreffer);
        			newShip.setRichtung(rRichtung);
        		}
        	}                
        	obj.close();
        }
        catch (Exception e)
        {
        	System.out.println(e.getMessage());
        } 
	}
}
