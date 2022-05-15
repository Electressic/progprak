

import java.lang.Object;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.LabelUI;
import javax.swing.JLabel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;


public class AusWahlSchiff extends Component
{
	public AusWahlSchiff()
	{

		
		JFrame frameFabian = new JFrame("Auswahl Schiff");
		frameFabian.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameFabian.setSize(700,400);
		
		JLabel label = new JLabel();
		label.setText("Dies ist ein Schiff mit dem Wert 2");
		frameFabian.setLayout(new GridLayout(2,5));
		
		frameFabian.add(new JButton("1"));
		frameFabian.add(new JButton("2"));
		
		frameFabian.add(new JButton("3"));
		frameFabian.add(new JButton("4"));
		frameFabian.add(new JButton("5"));
		frameFabian.add(new JButton("6"));
		
		frameFabian.add(new JButton("7"));
		frameFabian.add(new JButton("8"));
		//frameFabian.add(label);
		
		frameFabian.setVisible(true);
	}
}
