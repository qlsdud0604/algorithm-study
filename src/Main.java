import java.io.*;
import java.util.*;

/** 그래프의 각 간선을 정의한 클래스 */
class Edge implements Comparable<Edge> {
	int node01;
	int node02;
	int cost;

	Edge(int node01, int node02, int cost) {
		this.node01 = node01;
		this.node02 = node02;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		if (this.cost > o.cost)
			return 1;
		else if (this.cost == o.cost)
			return 0;
		else
			return -1;
	}

}

public class Main {
	static int[] parent;

	public static void main(String[] args) {
		Edge[] edges = new Edge[9];

		edges[0] = new Edge(0, 3, 4);
		edges[1] = new Edge(0, 1, 6);
		edges[2] = new Edge(1, 2, 5);
		edges[3] = new Edge(1, 3, 3);
		edges[4] = new Edge(1, 4, 7);
		edges[5] = new Edge(1, 5, 8);
		edges[6] = new Edge(2, 5, 8);
		edges[7] = new Edge(3, 4, 9);
		edges[8] = new Edge(4, 5, 11);

		parent = new int[6];

		for (int i = 0; i < parent.length; i++)
			parent[i] = i;

	}

	static int find(int node) {
		if (parent[node] == node)
			return node;

		return parent[node] = find(parent[node]);
	}

	static void union(int node01, int node02) {
		node01 = find(node01);
		node02 = find(node02);

		if (node01 < node02)
			parent[node02] = node01;
		else
			parent[node01] = node02;
	}

	static boolean isSameParent(int node01, int node02) {
		node01 = find(node01);
		node02 = find(node02);

		if (node01 == node02)
			return true;
		else
			return false;
	}
}
