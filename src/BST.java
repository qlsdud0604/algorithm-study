import java.util.ArrayList;

class Node<K, V> {
	K key;
	V value;
	Node<K, V> left, right;

	int N; // number of nodes
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

public class BST<K extends Comparable<K>, V> {
	protected Node<K, V> root;

	public int size() {
		return (root != null) ? root.N : 0;
	}

	/** return the node with that key or the last node in the traversal */
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

	/** returns the value corresponding to key */
	public V get(K key) {
		if (root == null)
			return null;

		Node<K, V> x = treeSearch(key);

		if (key.equals(x.key))
			return x.value;
		else
			return null;
	}

	/** insert key, value pairs into the tree */
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

	/** increase the size of ancestor nodes by 1 */
	protected void rebalanceInsert(Node<K, V> x) {
		resetSize(x.parent, 1);
	}

	/** decrease the size of ancestor nodes by 1 */
	protected void rebalanceDelete(Node<K, V> p, Node<K, V> delete) {
		resetSize(p, -1);
	}

	private void resetSize(Node<K, V> x, int value) {
		for (; x != null; x = x.parent)
			x.N += value;
	}

	/** returns iterable for all keys */
	public Iterable<K> keys() {
		if (root == null)
			return null;

		ArrayList<K> keyList = new ArrayList<K>(size());
		inorder(root, keyList);
		return keyList;
	}

	/** add to list according to inorder */
	private void inorder(Node<K, V> x, ArrayList<K> keyList) {
		if (x != null) {
			inorder(x.left, keyList);
			keyList.add(x.key);
			inorder(x.right, keyList);
		}
	}

	/** delete a specific key from the array */
	public void delete(K key) {
		if (root == null)
			return;

		Node<K, V> y, p;
		Node<K, V> x = treeSearch(key);

		if (!key.equals(x.key))
			return;

		if (x == root || isTwoNode(x)) {
			if (isLeaf(x)) { // root is leaf node
				root = null;
				return;
			} else if (!isTwoNode(x)) { // root node with one child
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

	/** check the existence of a specific key */
	public boolean contains(K key) {
		return get(key) != null;
	}

	/** check if the array is empty */
	public boolean isEmpty() {
		return root == null;
	}

	/** check if the node is leaf node */
	protected boolean isLeaf(Node<K, V> x) {
		return x.left == null && x.right == null;
	}

	/** check if the node have two node */
	protected boolean isTwoNode(Node<K, V> x) {
		return x.left != null && x.right != null;
	}

	/** connect child node to parent node */
	protected void relink(Node<K, V> parent, Node<K, V> child, boolean makeLeft) {
		if (child != null)
			child.parent = parent;
		if (makeLeft)
			parent.left = child;
		else
			parent.right = child;
	}

	/** return the smallest node */
	public Node<K, V> min(Node<K, V> x) {
		while (x.left != null)
			x = x.left;

		return x;
	}

	/** return the biggest node */
	public Node<K, V> max(Node<K, V> x) {
		while (x.right != null)
			x = x.right;

		return x;
	}	
}