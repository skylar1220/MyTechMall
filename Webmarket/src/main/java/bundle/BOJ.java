package bundle;

import java.util.*;
import java.io.*;


class Node{
	public int x ;
	public int y ;
	
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
}

public class BOJ {
	
//	public static boolean [][] visited = new boolean [25][25];
//	public static String [][] sgraph = new String [25][25];
	public static int [][] graph = new int [1000][1000];
	public static int [] dx = {-1,1,0,0}; // 상, 하, 좌, 우 
	public static int [] dy = {0,0, -1,1 };
	public static int M ;
	public static int N ;
	public static ArrayList<Integer> dangi = new ArrayList<Integer>();
	public static int cnt =0 ;
	public static int day =0 ;
	
	public static boolean bfs(int x, int y) {
		int noCnt = 0 ;
		
		Queue<Node> q = new LinkedList<Node>();
		Node node = q.poll();
		x = node.x;
		y = node.y;
		if(graph[x][y]!=1) return false;
		
		for (int i = 0; i < 4; i++) {
			if(x+dx[i] <0 || y+dy[i]  <0 || x+dx[i] >= M || y+dy[i] >= N) noCnt++ ;
			else if ( graph[x+dx[i]][y+dy[i]] == -1  ) noCnt ++ ;
			if(noCnt == 4) {
				System.out.println(-1);
				break;
			}
			graph[x+dx[i]][y+dy[i]] = 1 ;
			q.offer(new Node( x+dx[i] , y+dy[i]));
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws Exception, IOException {
		int oneCnt = 0 ;
		int minusCnt = 0 ;
		int zeroCnt = 0 ;
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer( br.readLine() , " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M ; i++) {
			st = new StringTokenizer( br.readLine() , " ");
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if(graph[i][j]== 0) zeroCnt ++;
				if(graph[i][j]== -1) minusCnt ++;
				if(graph[i][j]== 1) oneCnt ++;
				
				if(bfs(i,j) == true) {
					day ++ ;
				}
				
				if(zeroCnt == N*M ) System.out.println(-1);
				else if(minusCnt== N*M ) System.out.println(0);
				else if(oneCnt+minusCnt == N*M ) System.out.println(0);
				
			}
		}
		System.out.println(day);
		
		
		
	}

}
