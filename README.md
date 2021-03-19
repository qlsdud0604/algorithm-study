# 이진 검색 트리, AVL 트리
-----
## 1. 이진 검색 트리의 정의   
* 각 노드는 (키, 값)의 쌍을 저장하며, 서로 다른 노드의 키는 다르다.
* 왼쪽 서브트리에 저장된 노드의 키보다는 크다. 
* 오른쪽 서브트리에 저장된 노드의 키보다는 작다.

## 2. 이진 검색 트리의 장점
* 심볼 테이블을 정렬된 순서로 유지할 필요 없다.
* 새로 삽입되는 노드는 항상 리프 노드에 위치한다.

## 3. 이진 검색 트리의 단점
* 균형 트리를 보장하지 않는다.   
* 감색 시 많은 시간이 소요된다.   

## 4. 구현 방법
**1) 노드의 구성**
```java
class Node<K, V> {
	K key;
	V value;
	Node<K, V> left, right;

	int N;
	int aux;
	Node<K, V> parent;

	public Node(K key, V value) {
		this.key = key;
		this.value = value;
		this.N = 1;
	}

	public int getAux() {
		return this.aux;
	}

	public void setAux(int value) {
		this.aux = value;
	}
}
```
ㆍ 노드는 키, 값 그리고 자식에 대한 참조 변수인 left, right를 가지고 있다.   
ㆍ 자신과 자식 노드의 개수에 대한 변수인 N이 있다.   
ㆍ 노드의 높이를 알려주는 변수인 aux, 부모에 대한 참조 변수인 parent를 가지고 있다.   

**2) treeSearch(K key) 메소드**
```java
protected Node<K, V> treeSearch(K key) {
	Node<K, V> x = root;

	while (true) {
		int cmp = key.compareTo(x.key);

		if (cmp == 0)
			return x;

		else if (cmp < 0) {
			if (x.left == null)
				return x;
			else
				x = x.left;
		}

		else {
			if (x.right == null)
				return x;
			else
				x = x.right;
		}
	}
}
```
ㆍ 특정 키를 입력받아, 그 키를 갖는 노드 또는 순회의 마지막 노드를 반환하는 메소드이다.   
ㆍ 연산은 루트부터 시작되며, 입력받은 키보다 작을 경우 왼쪽으로 클 경우 오른쪽으로 이동하며 순회한다.   

**3) get(K key) 메소드**
```java
public V get(K key) {
	if (root == null)
		return null;

	Node<K, V> x = treeSearch(key);

	if (key.equals(x.key))
		return x.value;
	else
		return null;
}
```
ㆍ 특정 키가 트리에 있을 경우 키에 대응하는 값을 반환한다.   
ㆍ 키다 없을 경우 null을 반환한다.   

**4) put(K key, V value) 메소드**
```java
public void put(K key, V value) {
	if (root == null) {
		root = new Node<K, V>(key, value);
		return;
	}
	Node<K, V> x = treeSearch(key);

	int cmp = key.compareTo(x.key);

	if (cmp == 0)
		x.value = value;
	else {
		Node<K, V> newNode = new Node<K, V>(key, value);

		if (cmp < 0)
			x.left = newNode;
		else
			x.right = newNode;

		newNode.parent = x;

		rebalanceInsert(newNode);
	}
}
```
ㆍ 입력받은 키를 검색 후 있으면 값만 바꿔준다.   
ㆍ 없을 경우 새로운 노드를 추가한다.   

**5) resetSize(Node<K, V> x, int value) 메소드**
```java
protected void rebalanceInsert(Node<K, V> x) {
	resetSize(x.parent, 1);
	}

protected void rebalanceDelete(Node<K, V> p, Node<K, V> delete) {
	resetSize(p, -1);
}

private void resetSize(Node<K, V> x, int value) {
	for (; x != null; x = x.parent)
		x.N += value;
}
```
ㆍ 입력 받은 노드부터 로트 노드까지의 N변수를 1씩 증가시켜주는 메소드이다.   

**6) keys() 메소드**
```java
public Iterable<K> keys() {
	if (root == null)
		return null;

	ArrayList<K> keyList = new ArrayList<K>(size());
	inorder(root, keyList);
	return keyList;
}
```
ㆍ inorder 순회를 이용하여 트리의 모든 키를 ArrayList에 추가한다.   
ㆍ ArrayList를 반환 함으로써 키의 정렬된 리스트를 반환한다.   

**7) inorder(Node<K, V> x, ArrayList keyList) 메소드**
```java
private void inorder(Node<K, V> x, ArrayList<K> keyList) {
	if (x != null) {
		inorder(x.left, keyList);
		keyList.add(x.key);
		inorder(x.right, keyList);
	}
}
```
ㆍ 입력받은 노드부터 자식 노드까지의 모든 키값을 inorder 순회하여 ArrayList에 추가한다.   
ㆍ ArrayList를 반환 함으로써 정렬된 키를 반환한다.   

**8) delete(K key) 메소드**
```java
public void delete(K key) {
	if (root == null)
		return;

	Node<K, V> y, p;
	Node<K, V> x = treeSearch(key);

	if (!key.equals(x.key))
		return;

	if (x == root || isTwoNode(x)) {
		if (isLeaf(x)) { 
			root = null;
			return;
		} else if (!isTwoNode(x)) { 
			root = (x.right == null) ? x.left : x.right;
			root.parent = null;
			return;
		} else {
			y = min(x.right);
			x.key = y.key;
			x.value = y.value;

			p = y.parent;
			relink(p, y.right, y == p.left);
			rebalanceDelete(p, y);
		}
	} else {
		p = x.parent;
		if (x.right == null)
			relink(p, x.left, x == p.left);
		else if (x.left == null)
			relink(p, x.right, x == p.left);
		
		rebalanceDelete(p, x);
	}
}
```
ㆍ 삭제하고자 하는 키가 없는 경우 그냥 반환한다.   
ㆍ 삭제하려는 노드가 루트 노드이며 리프 노드인 경우 노드를 null로 만든다.   
ㆍ 그냥 루트 노드인 경우 자식 노드를 루트로 만든다.   
ㆍ 자식이 둘인 노드일 경우에는 inorder successor로 대체하며, 기존의 inorder successor 위치의 노드는 삭제한다.   
ㆍ 자식 노드의 수가 1이하이고 루트도 아닐 경우에는 자식 노드를 삭제할 노드의 부모의 자식으로 만든다.   

**9)relink(Node<K, V> parent, Node<K, V> child, boolean makeLeft) 메소드**
```java
protected void relink(Node<K, V> parent, Node<K, V> child, boolean makeLeft) {
	if (child != null)
		child.parent = parent;
	if (makeLeft)
		parent.left = child;
	else
		parent.right = child;
}
```
ㆍ 특정 노드의 삭제연산이 이루어질 경우에 호출되는 메소드이다.   
ㆍ 삭제할 노드의 자식이 존재한다면 자식을 삭제할 노드의 부모의 자식으로 만든다.   
ㆍ makeLeft가 참이라면 부모의 왼쪽 자식으로, 거짓이라면 오른쪽 자식으로 만든다.   

-----
## 1. AVL 트리의 정의   
* 균형 잡힌 높이의 이진 검색 트리이다.   
* 두 개의 자식 노드들간에 깊이 차이가 1이하인 트리이다.   

## 2. AVL 트리의 장점
* 트리의 검색 연산이 항상 O(logn)으로 균형 잡혀 있다.   
* 트리의 삭제 연산 또한 O(logn)으로 균형 잡혀 있다.   

## 3. AVL 트리의 단점
* 삽입 연산이 이루어질 때 트리의 재구성 시간이 소요된다.   
* 이진 검색 트리보다 구현의 난이도가 높다.   

## 4. 구현 방법
**1) relink(Node<K, V> parent, Node<K, V> child, boolean makeLeft) 메소드**
```java
protected void relink(Node<K, V> parent, Node<K, V> child, boolean makeLeft) {
	if (child != null)
		child.parent = parent;
	if (makeLeft)
		parent.left = child;
	else
		parent.right = child;
}
```
* 자식 노드를 부모 노드의 자식으로 연결시킨다.
* makeLeft가 참이라면 부모의 왼쪽 자식으로, 거짓이라면 오른쪽 자식으로 만든다.   

**2) rotate(Node<K, V> x) 메소드**
```java
protected void rotate(Node<K, V> x) {
	Node<K, V> y = x.parent;
	Node<K, V> z = y.parent;
	if (z == null) {
		root = x;
		x.parent = null;
	} else
		relink(z, x, y == z.left);

	if (x == y.left) {
		relink(y, x.right, true);
		relink(x, y, false);
	} else {
		relink(y, x.left, false);
		relink(x, y, true);
	}
}
```
* 재구성 하고자하는 트리의 모양이 LL, RR일 경우의 호출되는 메소드이다.      
* 트리의 모양을 LL과 RR의 경우일 때로 구분하여 relink() 메소드를 호출하여 트리를 재구성한다.   

**3) restructure(Node<K, V> x) 메소드**
```java
protected Node<K, V> restructure(Node<K, V> x) {
	Node<K, V> y = x.parent;
	Node<K, V> z = y.parent;
	if ((x == y.left) == (y == z.left)) {
		rotate(y);
		return y;
	} else {
		rotate(x);
		rotate(x);
		return x;
	}
}
```
* 트리의 모양이 LL 혹은 RR이면 rotate() 메소드를 호출하여 재구성한다.
* 트리의 모양이 LR 혹은 RL인 경우에는 rotate() 메소드로 LL 또는 RR의 형태로 바꾼다.   
* LL 또는 RR 형태의 트리를 다시한번 rotate() 메소드를 호출하여 트리를 재구성한다.   

**4) getHeight(Node<K, V> x) 메소드**
```java
private int getHeight(Node<K, V> x) {
	return (x == null) ? 0 : x.getAux();
}
```
* 특정 노드의 높이를 반환하는 메소드이다.   

**5) setHeight(Node<K, V> x, int height) 메소드**
```java
private void setHeight(Node<K, V> x, int height) {
	x.setAux(height);
}
```
* 특정 노드의 높이를 설정하는 메소드이다.   

**6) recomputeHeight(Node<K, V> x) 메소드**
```java
private void recomputeHeight(Node<K, V> x) {
	setHeight(x, 1 + Math.max(getHeight(x.left), getHeight(x.right)));
}
```
* 트리의 재구성이 이루어질 때 노드의 높이를 재계산 하는 메소드이다.   

**7) isBalanced(Node<K, V> x) 메소드**
```java
private boolean isBalanced(Node<K, V> x) {
	return Math.abs(getHeight(x.left) - getHeight(x.right)) <= 1;
}
```
* 특정 노드를 기준으로 자식 노드들이 균형을 이루는지를 확인하는 메소드이다.   

**8) tallerChild(Node<K, V> x) 메소드**
```java
private Node<K, V> tallerChild(Node<K, V> x) {
	if (getHeight(x.left) > getHeight(x.right))
		return x.left;
	if (getHeight(x.left) < getHeight(x.right))
		return x.right;
	if (x == root)
		return x.left;
	if (x == x.parent.left)
		return x.left;
	else
		return x.right;
}
```
* 특정 노드의 자식 노드 중 깊이가 깊은 자식 노드를 반환하는 메소드이다.   

**9) rebalance(Node<K, V> x) 메소드**
```java
private void rebalance(Node<K, V> x) {
	do {
		if (!isBalanced(x)) {
			x = restructure(tallerChild(tallerChild(x)));
			recomputeHeight(x.left);
			recomputeHeight(x.right);
			for (Node<K, V> p = x; p != null; p = p.parent)
				recomputeHeight(p);
		}
		x = x.parent;
	} while (x != null);
}
```
* rebalanceInsert() 메소드와 rebalanceDelete() 메소드에서 재구성을 위하여 호출되는 메소드이다.   
* 트리를 재구성하기 위해 호출되는 메소드이다.   

-----
# 참고 문헌 및 자료
* R.Sedgewick and K. Wayne, Algorithms (4th Ed.), Addison-Wesley.
* E. Horowitz, S. Sahni, S. Anderson-Freed, Fundamentals of Data Structures in C, Silicon Press,
2nd Edition.
