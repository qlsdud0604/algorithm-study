
public class AVL<K extends Comparable<K>, V> extends BST<K, V> {

	/** return height of the node */
	private int getHeight(Node<K, V> x) {
		return (x == null) ? 0 : x.getAux();
	}

	/** set height of the node */
	private void setHeight(Node<K, V> x, int height) {
		x.setAux(height);
	}

	/** recompute the height */
	private void recomputeHeight(Node<K, V> x) {
		setHeight(x, 1 + Math.max(getHeight(x.left), getHeight(x.right)));
	}

	/** check if the node is balanced */
	private boolean isBalanced(Node<K, V> x) {
		return Math.abs(getHeight(x.left) - getHeight(x.right)) <= 1;
	}

	/** return taller child */
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

	@Override
	protected void rebalanceInsert(Node<K, V> x) {
		setHeight(x, 1);
		for (Node<K, V> p = x.parent; p != null; p = p.parent) {
			x.N += 1;
			recomputeHeight(p);
		}
		rebalance(x.parent);
	}

	@Override
	protected void rebalanceDelete(Node<K, V> p, Node<K, V> deleted) {
		for (Node<K, V> x = p; x != null; x = x.parent) {
			x.N -= 1;
			recomputeHeight(x);
		}
		if (p != null)
			rebalance(p);
	}
	
	/** rebalance the node */
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

	/** link child to parent */
	protected void relink(Node<K, V> parent, Node<K, V> child, boolean makeLeft) {
		if (child != null)
			child.parent = parent;
		if (makeLeft)
			parent.left = child;
		else
			parent.right = child;
	}

	/** method for RR, LL */
	protected void rotate(Node<K, V> x) {
		Node<K, V> y = x.parent;
		Node<K, V> z = y.parent;
		if (z == null) {
			root = x;
			x.parent = null;
		} else
			relink(z, x, y == z.left);

		if (x == y.left) { // LL
			relink(y, x.right, true);
			relink(x, y, false);
		} else { // RR
			relink(y, x.left, false);
			relink(x, y, true);
		}
	}

	/** method for RL, LR */
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
}