import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyPalette extends JPanel {
	private static MyPalette instance = null;
	public JLabel l1 = new JLabel("?", JLabel.CENTER);
	public JLabel l2 = new JLabel("?", JLabel.CENTER);
	public JLabel l3 = new JLabel("?", JLabel.CENTER);
	public JLabel l4 = new JLabel("?", JLabel.CENTER);
	public JLabel l5 = new JLabel("?", JLabel.CENTER);
	public JLabel l6 = new JLabel("?", JLabel.CENTER);
	private MyPalette() {
		super();
		setVisible(true);
		setPreferredSize(new Dimension(220, 620));
	
		setLayout(new GridLayout(12, 1));
		add(new JLabel("Brute force", JLabel.CENTER));
		add(l1);
		add(new JLabel("Greedy", JLabel.CENTER));
		add(l2);
		add(new JLabel("Ant System", JLabel.CENTER));
		add(l3);
		add(new JLabel("Elitist Ant System", JLabel.CENTER));
		add(l4);
		add(new JLabel("Ranked Based", JLabel.CENTER));
		add(l5);
		add(new JLabel("Min-Max", JLabel.CENTER));
		add(l6);
	}
	public static MyPalette getInstance() {
		if (instance == null) {
			instance = new MyPalette();
		}
			
		return instance;
	}
}
