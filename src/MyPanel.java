import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.stream.IntStream;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
	private static MyPanel instance = null;
	
	private MyPanel() {
		super();
		setVisible(true);
		setPreferredSize(new Dimension(820, 620));
	}
	
	public static MyPanel getInstance() {
		if (instance == null) {
			instance = new MyPanel();
		}
			
		return instance;
	}
	
	@Override
	protected void paintComponent(Graphics gr) {
		super.paintComponents(gr);
		Graphics2D g = (Graphics2D)gr;
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		if (Main.n <= 10) {
			g.setColor(Color.YELLOW);
			g.setStroke(new BasicStroke(2));
			for (int i=0; i<Main.n; i++) {
				int j = BruteForce.adj[i];
				g.drawLine(Main.points[i].x-2, Main.points[i].y, Main.points[j].x-2, Main.points[j].y);
			}
			g.drawString("" + BruteForce.distance(null), 10, 10);
		}
		
		g.setColor(Color.BLUE);
		if (Main.greedyActive) {
			g.setStroke(new BasicStroke(2));
			for (int i=0; i<Main.n; i++) {
				int j = Greedy.adj[i];
				//if (j == 0) continue;
				g.drawLine(Main.points[i].x, Main.points[i].y, Main.points[j].x, Main.points[j].y);
			}
			MyPalette.getInstance().l2.setText("" + Greedy.dist);
		}
		g.drawString("" + Greedy.dist, 210, 10);
		
		
		if (Main.active1) {
			g.setColor(Color.RED);
			g.setStroke(new BasicStroke(2));
			for (int i=0; i<Main.n; i++) {
				int j = AntSystem.adj2[i];
				g.drawLine(Main.points[i].x+4, Main.points[i].y+4, Main.points[j].x+4, Main.points[j].y+4);
			}
			//g.drawString("" + AntSystem.curLength, 610, 10);
			
			g.setColor(Color.GREEN);
			g.setStroke(new BasicStroke(2));
			for (int i=0; i<Main.n; i++) {
				int j = AntSystem.adj[i];
				g.drawLine(Main.points[i].x+2, Main.points[i].y+2, Main.points[j].x+2, Main.points[j].y+2);
			}
			//g.drawString("" + AntSystem.minLength, 410, 10);
			MyPalette.getInstance().l3.setText("" + AntSystem.minLength);
		}
		
		
		
		if (Main.active2) {
			System.out.println(":)");
			g.setColor(Color.RED);
			g.setStroke(new BasicStroke(2));
			for (int i=0; i<Main.n; i++) {
				int j = AntSystem2.adj2[i];
				g.drawLine(Main.points[i].x+4, Main.points[i].y+4, Main.points[j].x+4, Main.points[j].y+4);
			}
			//g.drawString("" + AntSystem.curLength, 610, 10);
			
			g.setColor(Color.GREEN);
			g.setStroke(new BasicStroke(2));
			for (int i=0; i<Main.n; i++) {
				int j = AntSystem2.adj[i];
				g.drawLine(Main.points[i].x+2, Main.points[i].y+2, Main.points[j].x+2, Main.points[j].y+2);
			}
			//g.drawString("" + AntSystem.minLength, 410, 10);
			MyPalette.getInstance().l3.setText("" + AntSystem2.minLength);
		}
		
		
		
		g.setColor(Color.BLACK);
		for (int i=0; i<Main.n; i++) {
			g.fillOval(Main.points[i].x-5, Main.points[i].y-5, 10, 10);
			g.drawString(""+(i), Main.points[i].x+5, Main.points[i].y+5);
		}
		
		
		
	}
}
