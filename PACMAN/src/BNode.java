public class BNode {
	private Pel value;
	private BNode left;
	private BNode right;
	private BNode parent;

	public BNode(Pel value, BNode left, BNode right, BNode parent) {
		this.value = value;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}

	public BNode() {
		this.value = null;
		this.left = null;
		this.right = null;
		this.parent = null;
	}

	public BNode parent() {
		return parent;
	}

	public void setParent(BNode newParent) {
		parent = newParent;
	}

	public void setLeftChild(BNode p) {
		left = p;
	}

	public void setRightChild(BNode p) {
		right = p;
	}

	public void setContent(Pel value) {
		this.value = value;
	}

	public boolean isLeaf() {
		return value == null && left == null && right == null;
	}

	public Pel getData() {
		return value;
	}

	public BNode leftChild() {
		return left;
	}

	public BNode rightChild() {
		return right;
	}
}
