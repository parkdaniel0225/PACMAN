public class MyObject implements MyObjectADT {
	private int id;
	private int width;
	private int height;
	private String type;
	private Location pos;
	private BinarySearchTree tree;

	public MyObject(int id, int width, int height, String type, Location pos) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.type = type;
		this.pos = pos;
		tree = new BinarySearchTree();
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getType() {
		return type;
	}

	public int getId() {
		return id;
	}

	public Location getLocus() {
		return pos;
	}

	public void setLocus(Location value) {
		pos = value;
	}

	public void addPel(Pel pix) throws DuplicatedKeyException {
		BNode root = tree.getRoot();
		tree.put(root, pix);
	}

	public boolean intersects(MyObject otherObject) {
		BNode root = tree.getRoot();
		Location otherLocation = otherObject.pos;
		BinarySearchTree otherTree = otherObject.tree;
		BNode otherRoot = otherTree.getRoot();

		try {
			Pel current = tree.smallest(root);

			do {
				Location location = current.getLocus();
				Location location2 = new Location(location.getx() + pos.getx() - otherLocation.getx(), location.gety() + pos.gety() - otherLocation.gety());

				if (otherTree.get(otherRoot, location2) != null) return true;
				else current = tree.successor(root, location);
			} while (current != null);
		} catch (Exception e) {
		}

		return false;
	}

}
