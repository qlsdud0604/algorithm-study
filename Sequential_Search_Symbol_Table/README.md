# 연결 리스트를 이용한 순차 검색

---
## 1. Symbol Table이란?
* (키, 값) 쌍이 모인 자료구조   
* 특정 키와 그 키에 해당되는 값의 쌍을 삽입할 수 있다.   
* 키가 주어질 때, 관련된 값을 검색할 수 있다.   
</br>

-----
## 2. 연결 리스트를 이용한 Symbol Table의 동작 방법 ##
<img src="https://user-images.githubusercontent.com/61148914/83242659-bd0ce180-a1d7-11ea-9254-f4ca50c63059.png" width="50%">   

* 연결 리스트를 이용하여 구현한 Symbol Table의 동작 방식이다.   
</br>

-----
## 3. 구현 방법 
**1) 노드의 구성**
<details>
    <summary><b>코드 보기</b></summary>
	
```java
class Node<K, V> {
	K key;
	V value;
	Node<K, V> next;

	public Node(K key, V value, Node<K, V> next) {
		this.key = key;
		this.value = value;
		this.next = next;
	}
}
```
</details>
	
ㆍ 노드는 키, 값, 다음 노드를 가리키는 참조로 구성되어 있다.   
</br>

**2) Symbol table의 기본 구성**
<details>
    <summary><b>코드 보기</b></summary>
	
```java
public class SequentialSearchST<K, V> {
	private Node<K, V> first;
	int N;
}
```
</details>
	
ㆍ 첫 번째 노드에 대한 참조 변수인 first가 있다.   
ㆍ 연결 리스트의 노드 수에 대한 변수인 N이 있다.   
</br>

**3) get( ) 메서드**
<details>
    <summary><b>코드 보기</b></summary>
	
```java
public V get(K key) {
	for (Node<K, V> x = first; x != null; x = x.next)
		if (key.equals(x.key))
			return x.value;

	return null;
}
```
</details>
	
ㆍ 연결 리스트를 처음부터 스캔한다.   
ㆍ 검색하고자 하는 키를 찾았으면 키에 대응하는 값을 리턴한다.   
ㆍ 찾지 못했으면 null을 리턴한다.   
</br>

**4) put( ) 메서드**
<details>
    <summary><b>코드 보기</b></summary>
	
```java
public void put(K key, V value) {
	for (Node<K, V> x = first; x != null; x = x.next)
		if (key.equals(x.key)) {
			x.value = value;
			return;
		}
	first = new Node<K, V>(key, value, first);
	N++;
}
```
</details>
	
ㆍ 연결 리스트를 처음부터 스캔한다.   
ㆍ 만약 해당 키가 있을 경우 값만 바꾼다.   
ㆍ 해당 키가 없을 경우 새로운 (키, 값) 쌍을 추가하고, 전체 노드의 수를 1 증가 시킨다.   
</br>

**5) delete( ) 메서드**
<details>
    <summary><b>코드 보기</b></summary>
	
```java
public void delete(K key) {
	if (key.equals(first.key)) { 
		first = first.next;
		N--;
		return;
	}

	for (Node<K, V> x = first; x.next != null; x = x.next) {
		if (key.equals(x.next.key)) {
			x.next = x.next.next;
			N--;
			return;
		}
	}
}	
```
</details>
	
ㆍ 첫번째 노드의 키가 삭제할 키와 일치한다면 첫번째 노드를 삭제한다.   
ㆍ 첫번째 노드를 삭제하지 않는 경우는 연결 리스트를 처음부터 스캔하고, 삭제할 노드를 찾는다.   
</br>

**6) keys( ) 메서드**
<details>
    <summary><b>코드 보기</b></summary>
	
```java
public Iterable<K> keys() {
	ArrayList<K> keyList = new ArrayList<K>(N);

	for (Node<K, V> x = first; x != null; x = x.next)
		keyList.add(x.key);
	return keyList;
}
```
</details>
	
ㆍ 연결 리스트의 Iterable을 반환하는 메서드이다.  
ㆍ 연결 리스트의 처음 부터 끝까지 순회하며, 키를 ArrayList에 추가한다.   
ㆍ 마지막에는 연결 리스트의 모든 키가 추가된 ArrayList를 반환한다.   
</br>

**7) contains( ) 메서드**
<details>
    <summary><b>코드 보기</b></summary>
	
```java
public boolean contains(K key) {
	return get(key) != null;
}
```
</details>
	
ㆍ get( ) 메서드의 반환값을 판단하여 키의 포함여부를 알아낸다.   
</br>

**8) isEmpty( ) 메서드**
<details>
    <summary><b>코드 보기</b></summary>
	
```java
public boolean isEmpty() {
	return N == 0;
}
```
</details>

ㆍ 연결 리스트의 노드 개수를 판단하여 빈 연결 리스트인지 알아낸다.   
</br>

**9) size( ) 메서드**
<details>
    <summary><b>코드 보기</b></summary>
	
```java
public int size() {
	return N;
}
```
</details>
	
ㆍ 연결 리스트의 노드 개수를 반환함으로써 연결 리스트의 크기를 알아낸다.   
</br>

-----
## 4. 참고 문헌 및 자료
* R.Sedgewick and K. Wayne, Algorithms (4th Ed.), Addison-Wesley.
* E. Horowitz, S. Sahni, S. Anderson-Freed, Fundamentals of Data Structures in C, Silicon Press, 2nd Edition.
* https://algs4.cs.princeton.edu/31elementary/   
</br>
