import java.io.*;
import java.util.*;

class Graph {
	/** 그래프의 각 노드에 대한 정보를 정의 */
	class Node {
		int data;
		LinkedList<Node> adjacentNodes;
		int indegree;

		Node(int data) {
			this.data = data;
			adjacentNodes = new LinkedList<Node>();
			this.indegree = 0;
		}
	}

	Node[] nodes;

	Graph(int size) {
		nodes = new Node[size];

		for (int i = 0; i < size; i++)
			nodes[i] = new Node(i);
	}

	/** 두 노드간의 엣지를 추가하는 메소드 */
	void addEdge(int index01, int index02) {
		if (!nodes[index01].adjacentNodes.contains(nodes[index02]))
			nodes[index01].adjacentNodes.add(nodes[index02]);

		nodes[index02].indegree++;
	}

	/** 위상 정렬 메소드 */
	void topologicalSort() {
		Queue<Node> queue = new LinkedList<Node>();

		for (int i = 0; i < nodes.length; i++)
			if (nodes[i].indegree == 0)
				queue.add(nodes[i]);

		while (!queue.isEmpty()) {
			Node returnNode = queue.poll();
			System.out.print(returnNode.data + " ");

			for (Node node : returnNode.adjacentNodes) {
				node.indegree--;

				if (node.indegree == 0)
					queue.add(node);
			}
		}
	}
}

public class Main {
	public static void main(String[] args) {
		Graph graph = new Graph(7);

		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 4);
		graph.addEdge(1, 3);
		graph.addEdge(2, 3);
		graph.addEdge(2, 6);
		graph.addEdge(4, 3);
		graph.addEdge(4, 5);
		graph.addEdge(3, 5);

		graph.topologicalSort();
	}
}
