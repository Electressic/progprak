package progprak.src.Logik;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Spielfeld
{
	enum Zustand {Wasser, Schiff_Normal, Schiff_Getroffen};
	protected Zustand[][] zustandSpielfeld;
	protected BattleShip[][] Ships = new BattleShip[0][0];
	protected BattleShip[] vorhandeneSchiffe = new BattleShip[0];
	public static int SpielfeldSize = 15;

	//--------------- Initialisierung vom Spielfeld---------------------------------------------------
	public Spielfeld(int Size)
	{
		zustandSpielfeld = new Zustand[Size][Size];
		Ships = new BattleShip[Size][Size];
	}

	//--------------- code von Fabian---------------------------------------------------

	public String getFleet()
	{
		if(getSpielfeldSize() >= 20)
		{
			return "ships 3 4 5 6";
		}
		return "ships 2 3 4 5";
	}
	
	//ab hier ist die Neue Funktion wie am 27.06.2022 besprochen (hoffentlich! :D)
	public void InitializeShip(int intSize)
	{
		BattleShip ship = new BattleShip(intSize, getSpielfeldSize());
		BattleShip[] temp = new BattleShip[vorhandeneSchiffe.length + 1];
		for (int i = 0; i < vorhandeneSchiffe.length; i++)
		{
			temp[i] = vorhandeneSchiffe[i];
		}
		temp[temp.length - 1] = ship;
		vorhandeneSchiffe = temp;
	}
	boolean CanShipPlace(int x, int y, BattleShip Ship, boolean isHorizontal)
	{
		int Pruefwert = -1;
		if(isHorizontal)
		{
			Pruefwert = x + Ship.getGroesse();
		}
		else
		{
			Pruefwert = y - Ship.getGroesse();
		}
		if(Pruefwert > SpielfeldSize)
		{
			return false;
		}
		return true;
	}
	public boolean setzeSchiff(int intStartPosX, int intStartPosY, boolean isHorizontal, BattleShip Ship)
	{	
		if(!CanShipPlace(intStartPosX, intStartPosY, Ship, isHorizontal))
		{
			return false;
		}
		Ship.setRichtung(isHorizontal);
		Ship.setStartposition(intStartPosX, intStartPosY);
		int PosX = intStartPosX;
		int PosY = intStartPosY;
		//(0, 0) liegt unten Links
		for(int i = 0; i < Ship.getGroesse(); i++)
		{
			Ships[PosX][PosY] = Ship;
			zustandSpielfeld[PosX][PosY] = Zustand.Schiff_Normal;
			if(isHorizontal)
			{
				PosX++;
			}
			else
			{
				PosY--;
			}
		}

		return true;
	}
	public boolean replaceShip(int x, int y, boolean isHorizontal, BattleShip Ship)
	{
		//Hier wird das alte Schiff gel�scht
		int[] PosAlt = new int[2];
		PosAlt = Ship.getStartPos();
		for(int i = 0; i < Ship.getGroesse(); i++)
		{
			Ships[PosAlt[0]] [PosAlt[1]] = null;
			zustandSpielfeld[PosAlt[0]][PosAlt[1]] = Zustand.Wasser;
			if(Ship.getRichtung())
			{
				PosAlt[0] = PosAlt[0] + 1;
			}
			else
			{
				PosAlt[1] = PosAlt[1] - 1;
			}
		}
		//Hier wird gepr�ft, ob das neue Schiff gesetzt werden kann
		if(!CanShipPlace(x, y, Ship, isHorizontal))
		{
			return false;
		}
		
		//Platzieren des neuen Schiffes
		for(int i = 0; i < Ship.getGroesse(); i++)
		{
			Ships[x] [y] = Ship;
			zustandSpielfeld[x][y] = Zustand.Schiff_Normal;
			if(Ship.getRichtung())
			{
				x++;
			}
			else
			{
				y--;
			}
		}
		return true;
	}
	//Bis hier her sind die neuen Funktionen

	// ------------------------------------------------------------------------

	public void setzeZustand(int x, int y, String strZustand)
	{
		if(strZustand.compareTo("Wasser") == 0)
		{
			zustandSpielfeld[x][y] = Zustand.Wasser;
		}
		else if(strZustand.compareTo("Schiff Normal") == 0)
		{
			zustandSpielfeld[x][y] = Zustand.Schiff_Normal;
		}
		else if(strZustand.compareTo("Schiff getroffen") == 0)
		{
			zustandSpielfeld[x][y] = Zustand.Schiff_Getroffen;
		}
	}
	public String getZustandPos(int x, int y)
	{
		if(zustandSpielfeld[x][y] == Zustand.Wasser)
		{
			return " W ";
		}
		else if(zustandSpielfeld[x][y] == Zustand.Schiff_Normal) {
			return "N";
		}
		else if (zustandSpielfeld[x][y] == Zustand.Schiff_Getroffen)
		{
			return "Hit";
		}
		return null;
	}

	// ------------------------------------------------------------------------
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
		if(getZustandPos(x, y).equals("Wasser"))
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
    		boolean rRichtung;
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
            		rRichtung = Boolean.parseBoolean(ArrInhalt[4]);	
        			
        			
        			InitializeShip(intGroesse);
        			setzeSchiff(StartPosX, StartPosY, rRichtung, vorhandeneSchiffe[vorhandeneSchiffe.length - 1]);
        			
        			vorhandeneSchiffe[vorhandeneSchiffe.length - 1].setAnzahlTreffer(AnzahlTreffer);
        			vorhandeneSchiffe[vorhandeneSchiffe.length - 1].setRichtung(rRichtung);
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
