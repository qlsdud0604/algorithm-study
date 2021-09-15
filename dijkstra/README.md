# 다익스트라 알고리즘

---
## 1. 다익스트라 알고리즘이란?
* 그래프 상의 한 노드에서 다른 모든 노드까지의 최단 거리를 구할 때 유용하게 사용되는 알고리즘
* 노드와 노드 사이의 간선의 가중치가 0 이상의 정수일 때만 사용이 가능 
</br>

---
## 2. 다익스트라 알고리즘의 동작과정
### 1단계

<img src="https://user-images.githubusercontent.com/61148914/133361236-f816d1f3-3b52-4a38-b247-f5abc49fd51f.png" width="35%">

|노드|&nbsp;&nbsp; 1 &nbsp;&nbsp;|&nbsp;&nbsp; 2 &nbsp;&nbsp;|&nbsp;&nbsp; 3 &nbsp;&nbsp;|&nbsp;&nbsp; 4 &nbsp;&nbsp;|&nbsp;&nbsp; 5 &nbsp;&nbsp;|&nbsp;&nbsp; 6 &nbsp;&nbsp;|
|---|---|---|---|---|---|---|
|**거리**|&nbsp;&nbsp; 0 &nbsp;&nbsp;|&nbsp;&nbsp; 2 &nbsp;&nbsp;|&nbsp;&nbsp; 5 &nbsp;&nbsp;|&nbsp;&nbsp; 1 &nbsp;&nbsp;|&nbsp; INF &nbsp;|&nbsp; INF &nbsp;|

ㆍ 출발 노드인 1을 기준으로 인접한 노드인 2, 3, 4 노드에 대한 최단 거리 테이블을 만들어 준다.   
ㆍ 인접하지 않은 노드인 경우 무한으로 표시한다.   
</br>

### 2단계

<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbwtdzE%2FbtqYE6Cy82U%2Fr2YxEKCHiiet7s2JLkkN8K%2Fimg.png" width="35%">

|노드|&nbsp;&nbsp; 1 &nbsp;&nbsp;|&nbsp;&nbsp; 2 &nbsp;&nbsp;|&nbsp;&nbsp; 3 &nbsp;&nbsp;|&nbsp;&nbsp; 4 &nbsp;&nbsp;|&nbsp;&nbsp; 5 &nbsp;&nbsp;|&nbsp;&nbsp; 6 &nbsp;&nbsp;|
|---|---|---|---|---|---|---|
|**거리**|&nbsp;&nbsp; 0 &nbsp;&nbsp;|&nbsp;&nbsp; 2 &nbsp;&nbsp;|&nbsp;&nbsp; 1+3=4 &nbsp;&nbsp;|&nbsp;&nbsp; 1 &nbsp;&nbsp;|&nbsp; 1+1=2 &nbsp;|&nbsp; INF &nbsp;|

ㆍ 노드 1과 인접한 노드들 중 최단 거리가 가장 짧은 노드인 4번으로 진행한다.   
ㆍ 노드 4와 인접한 노드는 3, 5 노드 이고, 이 노드 까지의 최단 거리와 기존의 최단 거리 테이블의 값과 비교하여 더 짧은 거리로 갱신해준다.   
</br>

### 3단계

<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F1zQuK%2FbtqYZ5IaBQb%2FnuEk1J8Yz8KdyFWOWoOHI1%2Fimg.png" width="35%">

|노드|&nbsp;&nbsp; 1 &nbsp;&nbsp;|&nbsp;&nbsp; 2 &nbsp;&nbsp;|&nbsp;&nbsp; 3 &nbsp;&nbsp;|&nbsp;&nbsp; 4 &nbsp;&nbsp;|&nbsp;&nbsp; 5 &nbsp;&nbsp;|&nbsp;&nbsp; 6 &nbsp;&nbsp;|
|---|---|---|---|---|---|---|
|**거리**|&nbsp;&nbsp; 0 &nbsp;&nbsp;|&nbsp;&nbsp; 2 &nbsp;&nbsp;|&nbsp;&nbsp; 4 &nbsp;&nbsp;|&nbsp;&nbsp; 1 &nbsp;&nbsp;|&nbsp;&nbsp; 2 &nbsp;&nbsp;|&nbsp; INF &nbsp;|

ㆍ 다음은 방문하지 않은 노드들 중 가장 짧은 노드인 2번 노드로 진행한다.   
ㆍ 노드 2와 인접한 노드는 3, 4 노드 이고, 이 노드 까지의 최단 거리와 기존의 최단 거리 테이블의 값과 비교하여 더 짧은 거리로 갱신해준다.   
ㆍ 기존의 최단 거리 테이블의 값이 더 적으므로 갱신할 필요가 없다.   
</br>

### 4단계

<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fbu5bPR%2FbtqYE8f4Fd5%2FVIht2T9TQw3zK4EARKbolK%2Fimg.png" width="35%">

|노드|&nbsp;&nbsp; 1 &nbsp;&nbsp;|&nbsp;&nbsp; 2 &nbsp;&nbsp;|&nbsp;&nbsp; 3 &nbsp;&nbsp;|&nbsp;&nbsp; 4 &nbsp;&nbsp;|&nbsp;&nbsp; 5 &nbsp;&nbsp;|&nbsp;&nbsp; 6 &nbsp;&nbsp;|
|---|---|---|---|---|---|---|
|**거리**|&nbsp;&nbsp; 0 &nbsp;&nbsp;|&nbsp;&nbsp; 2 &nbsp;&nbsp;|&nbsp;&nbsp; 2+1=3 &nbsp;&nbsp;|&nbsp;&nbsp; 1 &nbsp;&nbsp;|&nbsp;&nbsp; 2 &nbsp;&nbsp;|&nbsp; 2+2=4 &nbsp;|

ㆍ 다음은 방문하지 않은 노드들 중 가장 짧은 노드인 5번 노드를 선택하고, 최단 거리 테이블 갱신 과정을 반복한다.   
</br>

### 5단계
ㆍ 다음으로는 3번 노드를 선택하고, 3번 노드의 인접 노드인 6번 노드의 거리가 기존의 최단 거리보다 크므로 갱신하지 않는다.   
</br>

### 6단계
ㆍ 마지막으로, 6번 노드는 인접한 노드가 없기 때문에 알고리즘이 종료된다.   
</br>

---
## 3. 구현 방법
**1) 노드에 대한 정보**
<details>
    <summary><b>코드 보기</b></summary>
	
```java
static class Node implements Comparable<Node> {
	int number;
	int weight;

	Node(int number, int weight) {
		this.number = number;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {
		return this.weight - o.weight;
	}
}
```
</details>
  
ㆍ 노드에 대한 정보를 정의한 코드이다.   
ㆍ 각 노드는 번호와 가중치를 가지고 있으며, 우선순위 큐의 활용을 위해 가중치의 오름차순으로 compareTo( ) 메서드를 재정의하였다.   
</br>

**2) 다익스트라 알고리즘의 사전 준비**
<details>
    <summary><b>코드 보기</b></summary>
	
```java
static ArrayList<Node>[] arr;
static int[] distance;
static boolean[] visited;
```
</details>
  
ㆍ 다익스트라 알고리즘을 구현하기 전에 필요한   
ㆍ arr는 각 노드들의 연결관계를 나타내는 변수이며, ArrayList의 자료구조로 선언하였다.   
ㆍ distance는 시작 노드에서 각 노드까지의 최단 거리를 저장하는 배열이다.   
ㆍ visited는 각 노드의 방문여부를 저장하는 배열이다.   
</br>

**3) 우선순위 큐를 이용한 다익스트라 알고리즘의 구현**
<details>
    <summary><b>코드 보기</b></summary>
	
```java
static void dijkstra(int start) {
	PriorityQueue<Node> queue = new PriorityQueue<>();

	Arrays.fill(distance, Integer.MAX_VALUE);

	distance[start] = 0;
	queue.add(new Node(start, 0));

	while (!queue.isEmpty()) {
		Node current = queue.poll();

		if (visited[current.number] == false)
			visited[current.number] = true;
		else
			continue;

		for (int i = 0; i < arr[current.number].size(); i++) {
			Node next = arr[current.number].get(i);

			if (distance[current.number] + next.weight < distance[next.number]) {
				distance[next.number] = distance[current.number] + next.weight;

				queue.add(new Node(next.number, distance[next.number]));
			}
		}
	}
}
```
</details>
  
ㆍ 우선순위 큐를 선언한 후, distance[ ] 배열을 정수의 최대치로 초기화한다.   
ㆍ 그 후, 시작 노드의 최단거리를 0으로 초기화를 하고, 시작 노드를 우선순위 큐에 삽입한다.   
ㆍ 우선순위 큐에서 가장 낮은 가중치를 가진 노드를 꺼내어 방문 여부를 확인한다.   
ㆍ 방문하지 않은 노드라면, 해당 노드와 인접한 노드들의 가중치와 기존의 가중치를 비교하여 더 낮은 값이라면 새로운 값으로 갱신한다.   
ㆍ 위 과정을 우선순위 큐가 빈 상태일 때까지 반복한다.   
</br>
