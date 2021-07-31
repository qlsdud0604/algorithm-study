# 크루스칼 알고리즘

---
## 1. 크루스칼 알고리즘이란?
* 가장 적은 비용으로 모든 노드를 연결하기 위해 사용되는 알고리즘이다.   
</br>

---
## 2. 크루스칼 알라즘의 동작 과정
<img src="https://user-images.githubusercontent.com/61148914/112142522-0a9c8400-8c1a-11eb-8fb4-25d486a0dcc9.png" width="25%">   

* 위 그래프는 7개의 노드와 11개의 엣지로 이루어져있다.   
</br>

#### 알고리즘의 동작과정
1. 그래프를 구성하고 있는 엣지를 비용을 기준으로 오름차순 정렬을 한다.     
2. 비용이 작은 엣지부터 정렬된 순서대로 그래프를 구성한다.   
3. 그래프를 구성할 때 사이클을 형성하는 지 확인한다.   
4. 사이클을 형성하는 경우 해당 그래프에 엣지를 포함시키 지 않는다.   
</br>

-----
## 3. 구현 방법
**1) 엣지의 구성**
<details>
    <summary><b>코드 보기</b></summary>
	
```java
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
```
</details>
	
ㆍ 그래프를 구성하는 엣지의 정보를 클래스로써 정의하였다.   
ㆍ 각각의 엣지는 두 개의 노드(node01, node02), 엣지를 구성할 때의 비용(cost)을 가지고 있다.   
ㆍ 비용을 기준으로 엣지를 오름차순으로 정렬하기 위하여 CompareTo( ) 메서드를 재정의 하였다.   
</br>

**2) Union-Find 알고리즘**
<details>
    <summary><b>코드 보기</b></summary>
	
```java
static int find(int node) {
	if (parents[node] == node)
		return node;

	return find(parents[node]);
}
```
</details>

ㆍ 특정 노드의 부모 노드를 반환하는 메소드이다.

<details>
    <summary><b>코드 보기</b></summary>

```java
static void union(int node01, int node02) {
	node01 = find(node01);
	node02 = find(node02);

	if (node01 < node02)
		parents[node02] = node01;
	else
		parents[node01] = node02;
}
```
</details>
ㆍ 각 부모 노드를 합치는 메소드이다.   

<details>
    <summary><b>코드 보기</b></summary>
	
```java
static boolean haveSameParent(int node01, int node02) {
	node01 = find(node01);
	node02 = find(node02);

	if (node01 == node02)
		return true;
	else
		return false;
}
```
</details>
	
ㆍ 같은 부모 노드를 가지는 지(사이클을 형성하는 지) 확인하는 메소드이다.   
</br>

**3) 크루스칼 알고리즘**
<details>
    <summary><b>코드 보기</b></summary>
	
```java
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
```
</details>
	
ㆍ 그래프를 구성하고 있는 엣지를 오름차순으로 정렬한다.   
ㆍ 작은 비용을 가지고 있는 엣지를 차례대로 사이클을 형성하는 지 확인한다.   
ㆍ 사이클을 형성하고 있지 않다면, 해당 엣지의 비용을 더해준다.   
</br>

-----
## 4. 참고 문헌 및 자료
* https://m.blog.naver.com/ndb796/221230994142   
</br>
