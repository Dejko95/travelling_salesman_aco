import java.awt.Point;
import java.util.ArrayList;
import java.util.Timer;

public class AntSystem {
	public static double alpha = 1, beta = 5;
	public static double p = 0.5;
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
	
	public static void processAnt(int k) {
		
		boolean mark[] = new boolean[n];
		for (int i=0; i<n; i++) mark[i] = false;
		
		int x = (int)(Math.random() * n);
		path[k][0] = x;
		
		for (int i=0; i<n-1; i++) {
			mark[x] = true;
			double p[] = new double[n];
			double total = 0;
			int next = -1;
			
			for (int j=0; j<n; j++) {
				if (mark[j]) continue;
				p[j] = Math.pow(t[x][j], alpha) * Math.pow(h[x][j], beta);
				total += p[j];
				//if (next == -1 || p[next] < p[j]) next = j;
			}
			
			
			double rnd = Math.random();
			//int next = -1;
			for (int j=0; j<n; j++) {
				if (mark[j]) continue;
				if (rnd <= p[j] / total) {
					next = j;
					break;
				}
				else rnd -= p[j] / total;
			}
			if (next == -1) {
				for (int j=0; j<n; j++) {
					if (!mark[j]) {
						next = j;
						break;
					}
				}
			}
			
			x = next;
			path[k][i+1] = x;
		}
		
		len[k] = 0;
		for (int i=0; i<n; i++) {
			x = path[k][i];
			int y = path[k][(i+1) % n];
			len[k] += pts[x].distance(pts[y]);
		}
		curLength = len[k];
		if (len[k] < minLength) {
			minLength = len[k];
			for (int i=0; i<n; i++) {
				x = path[k][i];
				int y = path[k][(i+1) % n];
				adj[x] = y;
				adj2[i] = i;
			}
			MyPanel.getInstance().repaint();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//for (int i=0; i<n; i++) System.out.print(path[k][i] + "_");
			//System.out.println();
			System.out.println(minLength);
		}
		for (int i=0; i<n; i++) {
			x = path[k][i];
			int y = path[k][(i+1) % n];
			adj2[x] = y;
		}
		MyPanel.getInstance().repaint();
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static double distance(Point points[]) {
		n = points.length;
		t = new double[n][n];
		h = new double[n][n];
		path = new int[popSize][n];
		len = new double[popSize];
		pts = points;
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				t[i][j] = popSize / Greedy2.getDistance(points);
				h[i][j] = 1/ points[i].distance(points[j]);
			}
		}
		
		int iter = 50;
		double tmin = 1000000, tmax = -1000000;
		while (iter-- > 0) {
			for (int k=0; k<popSize; k++) {
				processAnt(k);
			}
			for (int i=0; i<n; i++) {
				for (int j=0; j<n; j++) {
					t[i][j] = (1-p) * t[i][j];
					if (t[i][j] < tmin) tmin = t[i][j];
				}
			}
			int best = 0;
			for (int k=0; k<popSize; k++) {
				if (len[k] < len[best]) best = k;
			}
			for (int k=0; k<popSize; k++) {
				for (int i=0; i<n; i++) {
					int x = path[k][i];
					int y = path[k][(i+1) % n];
					t[x][y] += 1/len[k];
					if (t[x][y] > tmax) tmax = t[x][y];
					//if (k == best) t[x][y] += 5/len[k];
				}
			}
			for (int i=0; i<n; i++) {
				int j = adj[i];
				//t[i][j] += 2.5/minLength;
			}
			//System.out.println(tmin + " : " + tmax);
		}
		
		return minLength;
	}
}
