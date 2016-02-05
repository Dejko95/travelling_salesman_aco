import java.awt.Point;
import java.util.ArrayList;
import java.util.Timer;

public class AntSystem5 {
	public static double alpha = 1, beta = 4;
	public static double p = 0.1;
	public static double Q0 = 0.95;
	public static int n;
	public static int popSize = Main.n;
	public static double t[][], h[][];
	public static int path[][];
	public static double len[];
	public static Point pts[];
	public static double minLength = 9999999;
	public static double curLength = 9999999;
	public static int[] adj = new int[Main.n];
	public static int[] adj2 = new int[Main.n];
	public static double T0;
	public static double E;
	
	private static void processAnts() {
		boolean mark[][] = new boolean[popSize][n];
		for (int i=0; i<popSize; i++)
			for (int j=0; j<n; j++) mark[i][j] = false;
		
		int x[] = new int[popSize];
		for (int i=0; i<popSize; i++) 
			x[i] = (int)(Math.random() * n);
		for (int k=0; k<popSize; k++)
			path[k][0] = x[k];
		
		for (int i=0; i<n-1; i++) {
			for (int k=0; k<popSize; k++) {
				
			}
		}
		
		
		for (int i=0; i<n-1; i++) {
		for (int k=0; k<popSize; k++) {
			mark[k][x[k]] = true;
			double p[] = new double[n];
			double total = 0;
			int next = -1;
			int best = -1;

			
			for (int j=0; j<n; j++) {
				if (mark[k][j]) continue;
				p[j] = Math.pow(t[x[k]][j], alpha) * Math.pow(h[x[k]][j], beta);
				total += p[j];
				if (best == -1 || p[best] < p[j]) best = j;
			}
			
			double q = Math.random();
			if (q < Q0) {
				next = best;
			}
			else {
				
				double rnd = Math.random();
				//int next = -1;
				for (int j=0; j<n; j++) {
					if (mark[k][j]) continue;
					if (rnd <= p[j] / total) {
						next = j;
						break;
					}
					else rnd -= p[j] / total;
				}
				if (next == -1) {
					for (int j=0; j<n; j++) {
						if (!mark[k][j]) {
							next = j;
							break;
						}
					}
				}
			}
			t[x[k]][next] = (1 - E) * t[x[k]][next] + E * T0;
			x[k] = next;
			path[k][i+1] = x[k];
		}
		}	
		/****************/
		int xx;
		for (int k=0; k<popSize; k++)  {
			len[k] = 0;
			for (int i=0; i<n; i++) {
				xx = path[k][i];
				int y = path[k][(i+1) % n];
				len[k] += pts[xx].distance(pts[y]);
			}
			curLength = len[k];
			if (len[k] < minLength) {
				minLength = len[k];
				for (int i=0; i<n; i++) {
					xx = path[k][i];
					int y = path[k][(i+1) % n];
					adj[xx] = y;
					adj2[i] = i;
				}
				/*
				MyPanel.getInstance().repaint();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//for (int i=0; i<n; i++) System.out.print(path[k][i] + "_");
				//System.out.println();
				 */
				System.out.println(minLength);
			}
			for (int i=0; i<n; i++) {
				xx = path[k][i];
				int y = path[k][(i+1) % n];
				adj2[xx] = y;
			}
			/*
			MyPanel.getInstance().repaint();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
		}
	}
	
	public static double distance(Point points[]) {
		n = points.length;
		t = new double[n][n];
		h = new double[n][n];
		path = new int[popSize][n];
		len = new double[popSize];
		pts = points;
		T0 = 1 / Greedy2.getDistance(points) / n;
		E = 1 / (n * Greedy2.getDistance(points));
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				t[i][j] = T0;
				h[i][j] = 1/ points[i].distance(points[j]);
			}
		}
		
		int iter = 50;
		double tmin = 1000000, tmax = -1000000;
		while (iter-- > 0) {
			processAnts();
			
			
			for (int i=0; i<n; i++) {
				int j = adj[i];
				t[i][j] = (1-p) * t[i][j] + 1/minLength;
				
			}
			
		}
		
		return minLength;
	}
}
