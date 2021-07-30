import java.util.ArrayList;

public class BinarySearchST<K extends Comparable<K>, V> {
	private static final int INIT_CAPACITY = 10;
	private K[] keys; // array of key
	private V[] values; // array of value
	private int N;

	public BinarySearchST() {
		keys = (K[]) new Comparable[INIT_CAPACITY];
		values = (V[]) new Object[INIT_CAPACITY];
	}

	public BinarySearchST(int capacity) {
		keys = (K[]) new Comparable[capacity];
		values = (V[]) new Object[capacity];
	}

	/** check the existence of a specific key */
	public boolean contains(K key) {
		return get(key) != null;
	}

	/** check if the array is empty */
	public boolean isEmpty() {
		return N == 0;
	}

	/** returns the number of keys */
	public int size() {
		return N;
	}

	/** change the size of an array */
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

	/** returns the location of a specific key */
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

	/** returns the value corresponding to key */
	public V get(K key) {
		if (isEmpty())
			return null;

		int i = search(key);

		if (i < N && key.compareTo(keys[i]) == 0)
			return values[i];
		else
			return null;
	}

	/** insert key, value pairs into the array */
	public void put(K key, V value) {
		int i = search(key);

		if (i < N && keys[i].compareTo(key) == 0) {
			values[i] = value;
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

	/** delete a specific key from the array */
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

	/** returns iterable for all keys */
	public Iterable<K> keys() {
		ArrayList<K> keyList = new ArrayList<K>(N);

		for (int i = 0; i < N; i++)
			keyList.add(keys[i]);

		return keyList;
	}
}
