# 위상 정렬

-----
## 1. 위상 정렬이란?
* 순서가 정해져 있는 작업을 차례로 수행해야 할 때 그 순서를 결정해주기 위해 사용하는 알고리즘이다.
* 큐, 스택 자료구조를 이용하여 구현이 가능하다.

-----
## 2. 위상 정렬의 처리 방식
<img src="https://user-images.githubusercontent.com/61148914/111860920-ef1d4900-898d-11eb-97ff-6194b8947e6d.png" width="50%">

-----
## 3. 구현 방법
**1) 그래프의 구성**
```java
class Graph {
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
}  
```
ㆍ 그래프가 가지고 있는 노드의 정보를 클래스로써 정의하였다.   
ㆍ 각각의 노드는 데이터(data), 인접한 노드들(adjacentNodes), 진입 차수(indegree)를 가지고 있다.  

**2) 엣지를 구성해주는 메소드**
```java
void addEdge(int index01, int index02) {
	if (!nodes[index01].adjacentNodes.contains(nodes[index02]))
		nodes[index01].adjacentNodes.add(nodes[index02]);

	nodes[index02].indegree++;
}
 ```
ㆍ index01, index02의 관계를 가지는 두 노드를 매개변수로 입력받는다.   
ㆍ index01 노드의 adjacentNodes에 index02 노드를 추가해준다.   
ㆍ 추가로 index02 노드의 진입 차수를 1만큼 증가시킨다.   

**3) 큐 자료구조를 이용한 위상 정렬 알고리즘**
```java
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
```
ㆍ 우선, 큐 자료구조에 진입 차수가 0인 노드들을 삽입한다.   
ㆍ 큐에서 노드를 꺼낸 후 해당 노드의 데이터를 출력한다.   
ㆍ 큐에서 꺼낸 노드의 인접한 노드들의 진입 차수를 1만큼 감소시킨다.   
ㆍ 진입 차수가 0인 인접한 노드들을 큐에 삽입한다.   
ㆍ 위 과정을 큐에 데이터가 없을때까지 반복한다.   
