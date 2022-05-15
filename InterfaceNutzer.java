package test;

interface InterfaceNutzer 
{
	enum Zustand {Wasser, Schiff_Normal, Schiff_Getroffen};
	Zustand ZustandFeld(int x, int y);
}
