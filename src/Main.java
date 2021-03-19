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
	public static void main(String[] args) {

	}
}
