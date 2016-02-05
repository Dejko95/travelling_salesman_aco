import java.awt.Point;

public class BruteForce {
	private static double minDist = 9999999;
	public static int[] adj = new int[Main.n];
	
	public static void next_permutation(int p[]) {
		for (int a = p.length - 2; a >= 0; --a) {
			if (p[a] < p[a + 1]) {
				for (int b = p.length - 1;; --b) {
					if (p[b] > p[a]) {
						int t = p[a];
						p[a] = p[b];
						p[b] = t;
						for (++a, b = p.length - 1; a < b; ++a, --b) {
							t = p[a];
							p[a] = p[b];
							p[b] = t;
						}
						return;
					}
				}
			}
		}
	}
	
	public static double distance(Point points[]) {
		if (points == null) return minDist;
		int n = points.length;
		int perm[] = new int[n];
		for (int i=0; i<n; i++) perm[i] = i;
		
		int total = 1;
		for (int i=1; i<=n; i++) total *= i;
		
		
		while (total-- > 0) {
			double dist = 0;
			for (int k=0; k<n; k++) {
				int i = perm[k];
				int j = perm[(k+1) % n];
				dist += points[i].distance(points[j]);
			}
			if (dist < minDist) {
				minDist = dist;
				for (int i=0; i<n; i++) adj[perm[i]] = perm[(i+1) % n];
			}
			
			next_permutation(perm);
		}
		
		return minDist;
	}
}
