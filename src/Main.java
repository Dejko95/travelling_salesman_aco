import java.awt.Point;

import javax.swing.JFrame;

public class Main {
	public static int n = 10;
	public static Point points[];
	public static boolean greedyActive = false;
	public static boolean active1 = false;
	public static boolean active2 = false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		points = new Point[n];
		for (int i=0; i<n; i++) {
			boolean repeated = false;
			do {
				points[i] = new Point(10 +(int)(Math.random() * 800), 20 + (int)(Math.random()*590));
				//points[i] = new Point(10 + i/10 * 50, 20 + i % 10 * 50);
				repeated = false;
				for (int j=0; j<i; j++) if (points[i].equals(points[j])) repeated = true;
				
			} while (repeated);
		}
		
		/*
		points[0] = new Point(4, 1);
		points[1] = new Point(2, 4);
		points[2] = new Point(7, 3);
		points[3] = new Point(10, 2);
		points[4] = new Point(5, 5);
		points[5] = new Point(3, 8);
		points[6] = new Point(2, 10);
		points[7] = new Point(6, 8);
		points[8] = new Point(8, 1);
		points[9] = new Point(12, 6);
		*/
		
		new MyFrame(MyPanel.getInstance(), MyPalette.getInstance());
		
		if (n <= 10) System.out.println(BruteForce.distance(points));
		
		greedyActive = true;
		System.out.println(Greedy.getDistance(points));
		MyPanel.getInstance().repaint();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		greedyActive = false;

		
		
		active1 = true;
		//System.out.println(AntSystem.distance(points));
		for (int i=0; i<n; i++) AntSystem.adj2[i] = i;
		//MyPanel.getInstance().repaint();
		active1 = false;
		
		active2 = true;
		System.out.println("2: " + AntSystem2.distance(points));
		active2 = false;
		
		System.out.println("3: " + AntSystem3.distance(points));
		
		System.out.println("4: " + AntSystem4.distance(points));
		//System.out.println("5: " + AntSystem5.distance(points));
	
	}

}
