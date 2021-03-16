import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Stack;

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

	/** ���� �켱 Ž�� �޼ҵ� */
	void dfs(int startIndex) {
		Node root = nodes[startIndex];

		Stack<Node> stack = new Stack<Node>();

		stack.add(root);
		root.marked = true;

		while (!stack.isEmpty()) {
			Node returnNode = stack.pop();

			/** ������ ������ �������� ��� ����� �����Ͱ� ���� �ͺ��� �湮 */
			Collections.sort(returnNode.adjacentNodes, new Comparator<Node>() {

				@Override
				public int compare(Node o1, Node o2) {
					if (o1.data > o2.data)
						return 1;
					else
						return -1;
				}
			});

			for (Node node : returnNode.adjacentNodes) {
				if (node.marked == false) {
					node.marked = true;
					stack.add(node);
				}
			}
			System.out.print(returnNode.data + " ");
		}
	}
	
	void dfsRecursion(int startIndex) {
		Node root = nodes[startIndex];

		dfsRecursion(root);
	}
	
	/** ��� ����� �̿��� ���� �켱 Ž�� �޼ҵ� */
	void dfsRecursion(Node startIndex) {
		if (startIndex == null)
			return;

		startIndex.marked = true;

		System.out.print(startIndex.data + " ");

		/** ������ ������ �������� ��� ����� �����Ͱ� ���� �ͺ��� �湮 */
		Collections.sort(startIndex.adjacentNodes, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				if (o1.data > o2.data)
					return 1;
				else
					return -1;
			}
		});

		for (Node node : startIndex.adjacentNodes) {
			if (node.marked == false)
				dfsRecursion(node);
		}
	}

}

public class Main {
	public static void main(String[] args) {

		Graph g = new Graph(4);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 3);
		g.addEdge(1, 3);
		g.addEdge(2, 3);

		g.dfsRecursion(0);
	}
}
