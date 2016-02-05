import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
	public MyFrame(MyPanel panel, MyPalette palette) {
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(panel);
		add(palette, BorderLayout.EAST);
		pack();
		//setSize(new Dimension(600, 600));
		setLocationRelativeTo(null);
	}
	
}
