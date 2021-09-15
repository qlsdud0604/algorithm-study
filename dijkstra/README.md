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
