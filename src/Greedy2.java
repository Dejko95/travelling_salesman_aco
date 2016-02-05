import java.awt.Point;

public class Greedy2 {
	public static double dist = -1;
	
	public static int[] adj;
	
	private static double distance(Point points[]) {
		double dist = 0;
		int n = points.length;
		boolean mark[] = new boolean[n];
		for (int i=0; i<n; i++) mark[i] = false;
		adj = new int[n];
		
		System.out.println("Start");
		int i = 0;
		for (int k=1; k<n; k++) {
			int next = i+1;
			adj[i] = next;
			dist += points[i].distance(points[next]);
			i = next;
		}
		adj[i] = 0;
		dist += points[i].distance(points[0]);
		
		return dist;
	}
	
	public static double getDistance(Point[] points) {
		if (dist == -1) dist = distance(points);
		
		return dist;
	}
	
}
