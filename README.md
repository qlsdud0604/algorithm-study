# DFS, BFS
-----
## 1. DFS란?
* DFS는 깊이 우선 탐색으로 그래프의 탐색 방법 중 하나이다.   
* 갈 수 있는 만큼 최대한 깊이 가고, 더 이상 갈 곳이 없다면 이전 정점으로 돌아가는 방식으로 그래프를 순회하는 방식이다.   
* 스택 자료구조를 통해 구현할 수 있다.   

## 2. DFS의 그래프 순회 방식
<img src="https://user-images.githubusercontent.com/61148914/111780566-e7609480-88fa-11eb-80c3-17e48ab719c8.png" width="25%">   

## 3. 구현 방법
**1) 그래프의 구성**
```java
class Graph {
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
 }
 ```
 ㆍ 그래프가 가지고 있는 노드의 정보를 클래스로써 정의하였다.   
 ㆍ 각각의 노드는 데이터(data), 인접한 노드들(adjacentNodes), 방문여부 획인 변수(marked)를 가지고 있다.  
 
 **2) 엣지를 구성해주는 메소드**
 ```java
 void addEdge(int index01, int index02) {
	if (!nodes[index01].adjacentNodes.contains(nodes[index02]))
		nodes[index01].adjacentNodes.add(nodes[index02]);

	if (!nodes[index02].adjacentNodes.contains(nodes[index01]))
		nodes[index02].adjacentNodes.add(nodes[index01]);
}
 ```
 ㆍ 엣지를 구성할 두개의 노드를 매개변수로 입력받아 adjacentNodes에 각각의 노드들을 추가해준다.   
 
 **3) 스택 자료구조를 이용한 DFS 알고리즘**
 ```java
 void dfs(int startIndex) {
	Node root = nodes[startIndex];

	Stack<Node> stack = new Stack<Node>();

	stack.add(root);
	root.marked = true;

	while (!stack.isEmpty()) {
		Node returnNode = stack.pop();

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
 ```
 ㆍ 우선, 탐색을 시작할 노드를 스택 자료구조에 삽입한다.   
 ㆍ 스택에서 노드를 꺼내고 꺼낸 노드의 아직 방문하지 않은 인접한 노드들을 스택에 삽입한다.   
 ㆍ 인접한 노드들이 여러개일 경우 노드의 데이터가 작은 순서대로 삽입한다.   
 ㆍ 삽입과정이 마무리되면 꺼낸 노드에 대한 정보를 출력한다.   
 ㆍ 위 과정을 스택에 데이터가 없을때까지 반복한다.   
 
 **4) 재귀 방법을 이용한 DFS 알고리즘**
 ```java
 void dfsRecursion(Node startIndex) {
	if (startIndex == null)
		return;

	startIndex.marked = true;

	System.out.print(startIndex.data + " ");

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
 ```
 ㆍ 탐색을 시작할 노드를 우선 출력한다.   
 ㆍ 시작 노드의 아직 방문하지 않은 인접한 노드들을 순서대로 재귀적으로 메소드를 호출한다.   
 
 -----
## 1. BFS란?
* BFS는 우선 탐색으로 그래프의 탐색 방법 중 하나이다.   
* 시작 정점을 방문한 후 시작 정점에 인접한 모든 정점을 방문한 후 시작 정점에 인접한 모든 정점들을 우선 방문하는 방법이다.
* 큐 자료구조를 통해 구현할 수 있다.   
 
## 2. BFS의 그래프 순회 방식
<img src="https://user-images.githubusercontent.com/61148914/111780762-2b539980-88fb-11eb-869a-6fd5ba1c432a.png" width="25%">   
 
## 3. 구현 방법
**1) 큐 자료구조를 이용한 BFS 알고리즘**
```java
void bfs(int startIndex) {
	Node root = nodes[startIndex];

	Queue<Node> queue = new LinkedList<Node>();

	queue.add(root);
	root.marked = true;

	while (!queue.isEmpty()) {
		Node returnNode = queue.poll();

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
				queue.add(node);
			}
		}
		System.out.print(returnNode.data + " ");
	}
}
```
ㆍ 우선, 탐색을 시작할 노드를 큐 자료구조에 삽입한다.   
ㆍ 큐에서 노드를 꺼내고 꺼낸 노드의 아직 방문하지 않은 인접한 노드들을 큐에 삽입한다.   
ㆍ 인접한 노드들이 여러개일 경우 노드의 데이터가 작은 순서대로 삽입한다.   
ㆍ 삽입과정이 마무리되면 꺼낸 노드에 대한 정보를 출력한다.   
ㆍ 위 과정을 큐에 데이터가 없을때까지 반복한다. 
