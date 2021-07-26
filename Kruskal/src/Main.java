import java.io.*;
import java.util.*;

/** �׷����� �� ������ ������ Ŭ���� */
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
	static int[] parents;

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

		parents = new int[6];

		for (int i = 0; i < parents.length; i++)
			parents[i] = i;
		
		System.out.print(kruskal(edges));

	}

	/** Ư�� ����� �θ� ��带 ��ȯ�ϴ� �޼ҵ� */
	static int find(int node) {
		if (parents[node] == node)
			return node;

		return find(parents[node]);
	}

	/** �� �θ� ��带 ��ġ�� �޼ҵ� */
	static void union(int node01, int node02) {
		node01 = find(node01);
		node02 = find(node02);

		if (node01 < node02)
			parents[node02] = node01;
		else
			parents[node01] = node02;
	}

	/** ���� �θ� ��带 ������ �� Ȯ���ϴ� �޼ҵ� */
	static boolean haveSameParent(int node01, int node02) {
		node01 = find(node01);
		node02 = find(node02);

		if (node01 == node02)
			return true;
		else
			return false;
	}

	/** �ּ� ���д� Ʈ���� ���ϴ� �޼ҵ� */
	static int kruskal(Edge[] edges) {
		Arrays.sort(edges);

		int sum = 0;

		for (int i = 0; i < edges.length; i++) {
			Edge returnEdge = edges[i];

			if (!haveSameParent(returnEdge.node01, returnEdge.node02)) {
				sum += returnEdge.cost;
				union(returnEdge.node01, returnEdge.node02);
			}
		}
		return sum;
	}
}
