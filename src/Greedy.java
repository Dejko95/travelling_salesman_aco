import java.awt.Point;

public class Greedy {
	public static double dist = -1;
	
	public static int[] adj = new int[Main.n];
	
	private static double distance(Point points[]) {
		dist = 0;
		int n = points.length;
		boolean mark[] = new boolean[n];
		for (int i=0; i<n; i++) mark[i] = false;
		
		System.out.println("Start");
		int i = 0;
		for (int k=1; k<n; k++) {
			mark[i] = true;
			int next = -1;
			for (int j=0; j<n; j++) {
				if (mark[j]) continue;
				if (next == -1 || points[i].distance(points[j]) < points[i].distance(points[next])) next = j;
			}
			//System.out.println(i + " " + next);
			adj[i] = next;
			dist += points[i].distance(points[next]);
			i = next;
			MyPanel.getInstance().repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		adj[i] = 0;
		dist += points[i].distance(points[0]);
		
		return dist;
	}
	
	public static double getDistance(Point[] points) {
		if (dist == -1) {
			for (int i=0; i<Main.n; i++) adj[i] = i;
			dist = 0;
			dist = distance(points);
		}
		
		return dist;
	}
	
}
