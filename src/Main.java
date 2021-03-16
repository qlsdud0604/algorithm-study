import java.util.LinkedList;

class Graph {

	/** �׷��� �� ��带 ������ Ŭ���� */
	class Node {
		int data;
		LinkedList<Node> adjacentNodes;
		boolean marked;

		Node(int data) {
			this.data = data;
			adjacentNodes = new LinkedList<Node>();
			this.marked = false;
		}
	}

	Node[] nodes;

	Graph(int size) {
		nodes = new Node[size];

		for (int i = 0; i < size; i++)
			nodes[i] = new Node(i);
	}

	/** ���� ������ ���� ���� ������ �������ִ� �޼ҵ� */
	void addEdge(int index01, int index02) {
		if (!nodes[index01].adjacentNodes.contains(nodes[index02]))
			nodes[index01].adjacentNodes.add(nodes[index02]);

		if (!nodes[index02].adjacentNodes.contains(nodes[index01]))
			nodes[index02].adjacentNodes.add(nodes[index01]);
	}

}

public class Main {

}
