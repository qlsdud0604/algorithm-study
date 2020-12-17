# 배열을 이용한 이진 검색
## 1. Symbol Table이란?
* (키, 값) 쌍이 모인 자료구조   
* 특정 키와 그 키에 해당되는 값의 쌍을 삽입할 수 있다.   
* 키가 주어질 때, 관련된 값을 검색할 수 있다.   

-----
## 2. 배열을 이용한 Symbol Table의 동작 방법 ##
<img src="https://user-images.githubusercontent.com/61148914/83480680-00689800-a4d7-11ea-861c-24610036cc2f.png" width="60%">   
ㆍ 배열을 이용하여 구현한 Symbol Table의 동작 방식이다.

-----
## 3. 구현 방법
**1) Symbol table의 기본 구성**
```
public class BinarySearchST<K extends Comparable<K>, V> {
	private static final int INIT_CAPACITY = 10;
	private K[] keys;
	private V[] values;
	private int N;
}
```
ㆍ 키를 저장하는 배열 K[], 값을 저장하는 배열 V[]가 있다.   
ㆍ (키, 값) 쌍의 개수에 대한 변수인 N이 있다.   

**2) search(K key) 메소드**
```
private int search(K key) {
	int low = 0;
	int high = N - 1;

	while (low <= high) {
		int middle = (high + low) / 2;
		int cmp = key.compareTo(keys[middle]);

		if (cmp < 0)
			high = middle - 1;
		else if (cmp > 0)
			low = middle + 1;
		else
			return middle;
	}
	return low;
}
```
ㆍ 이진 검색을 통해 특정 키의 위치를 반환하는 함수이다.   
ㆍ 키가 없을 경우 -1이 아닌 low값을 반환한다.   

**3) get(K key) 메소드**
```
public V get(K key) {
	if (isEmpty())
		return null;

	int i = search(key);

	if (i < N && key.compareTo(keys[i]) == 0)		
		return values[i];
	else
		return null;
}
```
ㆍ 이진 검색을 이용하여 특정 키의 위치를 찾고, 키가 있다면 그에 대응하는 값을 반환한다.   
ㆍ 키가 없을 경우에는 null을 반환한다.

**4) put(K key, V value) 메소드**
```
public void put(K key, V value) {
	int i = search(key);

	if (i < N && keys[i].compareTo(key) == 0) {
		alues[i] = value;
		return;
	}
	if (N == keys.length)
		resize(2 * keys.length);

	for (int j = N; j > i; j--) {
		keys[j] = keys[j - 1];
		values[j] = values[j - 1];
	}

	keys[i] = key;
	values[i] = value;
	N++;
}
```
ㆍ 이진 검색을 이용하여 특정 키의 위치를 찾고, 키가 있다면 값을 변경한다.   
ㆍ 키가 없다면 추가될 곳의 공간을 확보 한 후 (키, 값) 쌍을 삽입한다.   

**5) delete(K key) 메소드**
```
public void delete(K key) {
	if (isEmpty())
		return;

	int i = search(key);

	if (i == N || keys[i].compareTo(key) != 0)
		return;

	for (int j = i; j < N - 1; j++) {
		keys[j] = keys[j + 1];
		values[j] = values[j + 1];
	}

	N--;

	keys[N] = null;
	values[N] = null;

	if (N > INIT_CAPACITY && N == keys.length / 4)
		resize(keys.length / 2);

}
```  
ㆍ삭제 메소드 또한 이진 검색을 이용하여 키의 위치를 찾고 없다면 그냥 반환한다.    
ㆍ만약 키가 있다면, 뒤에 있는 원소들을 해당 키의 위치까지 한칸 씩 앞으로 옮긴다.   

**6) resize(int capacity) 메소드**
```
private void resize(int capacity) {
	K[] tempk = (K[]) new Comparable[capacity];
	V[] tempv = (V[]) new Object[capacity];

	for (int i = 0; i < N; i++) {
		tempk[i] = keys[i];
		tempv[i] = values[i];
	}

	keys = tempk;
	values = tempv;
}
```
ㆍ 배열의 크기를 동적으로 변경해 주는 메소드이다.   
  
-----
## 4. 참고 문헌 및 자료
* R.Sedgewick and K. Wayne, Algorithms (4th Ed.), Addison-Wesley.
* E. Horowitz, S. Sahni, S. Anderson-Freed, Fundamentals of Data Structures in C, Silicon Press, 2nd Edition.
* https://algs4.cs.princeton.edu/31elementary/
