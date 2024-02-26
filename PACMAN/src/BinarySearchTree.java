public class BinarySearchTree implements BinarySearchTreeADT {
	private BNode root;

	public BinarySearchTree() {
		root = new BNode();
	}

	public Pel get(BNode r, Location key) {
		Pel result = null;
		BNode current = r;

		while (!current.isLeaf()) {
			int compare = key.compareTo(current.getData().getLocus());

			if (compare < 0) {
				current = current.leftChild();
			} else if (compare > 0) {
				current = current.rightChild();
			} else {
				result = current.getData();
				break;
			}
		}

		return result;
	}

	public void put(BNode r, Pel newData) throws DuplicatedKeyException
	{
		BNode newNode = new BNode();
		newNode.setContent(newData);
		BNode newNodeLeft = new BNode();
		newNodeLeft.setParent(newNode);
		newNode.setLeftChild(newNodeLeft);
		BNode newNodeRight = new BNode();
		newNodeRight.setParent(newNode);
		newNode.setRightChild(newNodeRight);

		BNode current = r;

		if (current.isLeaf()) {
			root = newNode;
		} else {
			while (true) {
				int compare = newData.getLocus().compareTo(current.getData().getLocus());

				if (compare < 0) {
					BNode left = current.leftChild();

					if (!left.isLeaf()) {
						current = left;
					} else {
						current.setLeftChild(newNode);
						newNode.setParent(current);
						break;
					}
				} else if (compare > 0) {
					BNode right = current.rightChild();

					if (!right.isLeaf()) {
						current = right;
					} else {
						current.setRightChild(newNode);
						newNode.setParent(current);
						break;
					}
				} else {
					throw new DuplicatedKeyException("Duplicated key: " + newData);
				}
			}
		}
	}

	public void remove(BNode r, Location key) throws InexistentKeyException {
		int child = 0; // root: 0; left: -1; right: 1
		BNode current = r;

		while (!current.isLeaf()) {
			int compare = key.compareTo(current.getData().getLocus());

			if (compare < 0) {
				child = -1;
				current = current.leftChild();
			} else if (compare > 0) {
				child = 1;
				current = current.rightChild();
			} else {
				break;
			}
		}

		if (!current.isLeaf()) {
			BNode left = current.leftChild();
			BNode right = current.rightChild();
			BNode replacement;

			if (left.isLeaf()) { // case (a)
				replacement = right;
			} else if (right.isLeaf()) { // case (b)
				replacement = left;
			} else if (right.leftChild().isLeaf()) { // case (c)
				right.setLeftChild(left);
				left.setParent(right);
				replacement = right;
			} else { // case (d)
				BNode parent2 = right;
				BNode current2 = right.leftChild();

				while (!current2.leftChild().isLeaf()) {
					parent2 = current2;
					current2 = current2.leftChild();
				}

				parent2.setLeftChild(current2.rightChild());
				current2.rightChild().setParent(parent2);
				current2.setRightChild(right);
				right.setParent(current2);
				current2.setLeftChild(left);
				left.setParent(current2);
				replacement = current2;
			}

			// replacement
			BNode parent = current.parent();

			if (child == -1) parent.setLeftChild(replacement);
			else if (child == 1) parent.setRightChild(replacement);
			else root = replacement;

			replacement.setParent(parent);
		} else {
			throw new InexistentKeyException("Inexistent key: " + key);
		}
	}

	public Pel successor(BNode r, Location key) {
		BNode current = r;

		while (!current.isLeaf()) {
			int compare = key.compareTo(current.getData().getLocus());

			if (compare < 0) current = current.leftChild();
			else if (compare > 0) current = current.rightChild();
			else break;
		}

		if (!current.isLeaf() && !current.rightChild().isLeaf()) {
			BNode p = current.rightChild();

			while (!p.leftChild().isLeaf()) p = p.leftChild();

			return p.getData();
		} else {
			BNode p = current.parent();
			BNode ch = current;

			while (!p.isLeaf() && ch == p.rightChild()) {
				ch = p;
				p = p.parent();
			}

			return !p.isLeaf() ? p.getData() : null;
		}
	}

	public Pel predecessor(BNode r, Location key)
	{
		BNode current = r;

		while (!current.isLeaf()) {
			int compare = key.compareTo(current.getData().getLocus());

			if (compare < 0) current = current.leftChild();
			else if (compare > 0) current = current.rightChild();
			else break;
		}

		if (!current.isLeaf() && !current.leftChild().isLeaf()) {
			BNode p = current.leftChild();

			while (!p.rightChild().isLeaf()) p = p.rightChild();

			return p.getData();
		} else {
			BNode p = current.parent();
			BNode ch = current;

			while (!p.isLeaf() && ch == p.leftChild()) {
				ch = p;
				p = p.parent();
			}

			return !p.isLeaf() ? p.getData() : null;
		}
	}

	public Pel smallest(BNode r) throws EmptyTreeException {
		BNode result;
		BNode current = r;

		if (!current.isLeaf()) {
			do {
				result = current;
				current = current.leftChild();
			} while (!current.isLeaf());
		} else {
			throw new EmptyTreeException("Empty tree");
		}

		return result.getData();
	}

	public Pel largest(BNode r) throws EmptyTreeException {
		BNode result;
		BNode current = r;

		if (!current.isLeaf()) {
			do {
				result = current;
				current = current.rightChild();
			} while (!current.isLeaf());
		} else {
			throw new EmptyTreeException("Empty tree");
		}

		return result.getData();
	}

	public BNode getRoot() {
		return root;
	}

}
