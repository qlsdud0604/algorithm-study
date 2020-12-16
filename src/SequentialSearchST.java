import java.util.ArrayList;

/** information about node */
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

public class SequentialSearchST<K, V> {
	private Node<K, V> first; // first node
	int N; // number of nodes

	/** returns the value corresponding to key */
	public V get(K key) {
		for (Node<K, V> x = first; x != null; x = x.next)
			if (key.equals(x.key))
				return x.value; // search hit

		return null; // search miss
	}

	/** insert key, value pairs into the linked list */
	public void put(K key, V value) {
		for (Node<K, V> x = first; x != null; x = x.next)
			if (key.equals(x.key)) { // change value only if key is present
				x.value = value;
				return;
			}
		first = new Node<K, V>(key, value, first); // if there is no key, add a node in front
		N++;
	}

	/** delete a specific key from the linked list */
	public void delete(K key) {
		if (key.equals(first.key)) { // delete the first node
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

	/** returns iterable for all keys */
	public Iterable<K> keys() {
		ArrayList<K> keyList = new ArrayList<K>(N);

		for (Node<K, V> x = first; x != null; x = x.next)
			keyList.add(x.key);
		return keyList;
	}

	/** check the existence of a specific key */
	public boolean contains(K key) {
		return get(key) != null;
	}

	/** check if the linked list is empty */
	public boolean isEmpty() {
		return N == 0;
	}

	/** returns the number of keys */
	public int size() {
		return N;
	}

}