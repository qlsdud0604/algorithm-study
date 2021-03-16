import java.util.LinkedList;

class Graph {

	/** 그래프 내 노드를 정의한 클래스 */
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

	/** 서로 인접한 노드들 간에 엣지를 구성해주는 메소드 */
	void addEdge(int index01, int index02) {
		if (!nodes[index01].adjacentNodes.contains(nodes[index02]))
			nodes[index01].adjacentNodes.add(nodes[index02]);

		if (!nodes[index02].adjacentNodes.contains(nodes[index01]))
			nodes[index02].adjacentNodes.add(nodes[index01]);
	}

}

public class Main {

}
