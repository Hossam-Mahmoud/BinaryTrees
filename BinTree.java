


public class BinTree {
	private Node root;
	private int size;
	private Node pointer;
	
	
	public BinTree(int head){
		root = new Node(head, null, null, null);
		size =1;
		pointer = root;
	}
	
	public void insert(int v){
		if(v < pointer.getValue()){
			if(pointer.hasLeft()){
				pointer = pointer.getLeft();
				this.insert(v);
			}else{
				Node tmp = new Node();
				tmp.setValue(v);
				tmp.setParent(pointer);
				pointer.setLeft(tmp);
				pointer = root;
				size++;
			}
		}else{
			if(pointer.hasRight()){
				pointer = pointer.getRight();
				this.insert(v);
			}else{
				Node tmp = new Node();
				tmp.setValue(v);
				tmp.setParent(pointer);
				pointer.setRight(tmp);
				pointer = root;
				size++;
			}
		}
			
	}
	
	
	private Node search(int v){
		if(v == pointer.getValue()){
			Node tmp = pointer;
			pointer = root;
			return tmp;
		}else if(v < pointer.getValue() && pointer.hasLeft()){
			pointer = pointer.getLeft();
			return this.search(v);
		}else if(v> pointer.getValue() && pointer.hasRight()){
			pointer = pointer.getRight();
			return this.search(v);
		}else{
			return null;
		}
	}
	
	private void removeLast(Node found){
		if (found.getParent() != null){
			Node parent = found.getParent();
		
		
			if((!found.hasLeft()) && (!found.hasRight())){
				if(parent.hasLeft() && parent.getLeft().getValue() == found.getValue())
					parent.setLeft(null);
				else 
					parent.setRight(null);
				
			}
			
			else if((found.hasLeft()) && (!found.hasRight())){
				if(parent.hasLeft() && parent.getLeft().getValue() == found.getValue())
					parent.setLeft(found.getLeft());
				else 
					parent.setRight(found.getLeft());
			}
			
			else if((!found.hasLeft()) && (found.hasRight())){
				if(parent.hasLeft() && parent.getLeft().getValue() == found.getValue())
					parent.setLeft(found.getRight());
				else 
					parent.setRight(found.getRight());
			}
		}else{
			if(found.hasLeft()){
				root = found.getLeft();
				root.setParent(null);
			}else{
				root = found.getRight();
				root.setParent(null);
			}
		}
		size--;
	}
	
	public void remove(int v) throws Exception{
		Node found = this.search(v);
		if(found != null){
			if(found.hasLeft() && found.hasRight()){
				Node tmp = found.getRight();
				while(tmp.hasLeft())
					tmp = tmp.getLeft();
				found.setValue(tmp.getValue());
				removeLast(tmp);
			}
			else
				removeLast(found);
		}
		else 
			throw new Exception("No such element.");
		
	}
	
	public boolean searchFor(int v){
		return (search(v) != null);
	}
	
	private String traversePreOrder(Node n){
		
		if((!n.hasLeft()) && (!n.hasRight())){
			return ""+n.getValue();
		}
		else if(n.hasLeft() && n.hasRight()){
			return ""+n.getValue()+" "+traversePreOrder(n.getLeft())+" "+traversePreOrder(n.getRight());
		}
		else if(n.hasRight()){
			return ""+n.getValue()+" "+traversePreOrder(n.getRight());
		}
		else if(n.hasLeft()){
			return ""+n.getValue()+" "+traversePreOrder(n.getLeft());
		}else
			return "";
	}
	
	private String traverseInOrder(Node n){
		if((!n.hasLeft()) && (!n.hasRight())){
			return ""+n.getValue();
		}
		else if(n.hasLeft() && n.hasRight()){
			return ""+traverseInOrder(n.getLeft())+" "+n.getValue()+" "+traverseInOrder(n.getRight());
		}
		else if(n.hasLeft())
			return ""+traverseInOrder(n.getLeft())+" "+n.getValue();
		else if(n.hasRight())
			return ""+n.getValue()+" "+traverseInOrder(n.getRight());
		else
			return "";
	}

	private String traversePostOrder(Node n){
		if((!n.hasLeft()) && (!n.hasRight())){
			return ""+n.getValue();
		}
		else if(n.hasLeft() && n.hasRight()){
			return ""+traversePostOrder(n.getLeft())+" "+traversePostOrder(n.getRight())+" "+n.getValue();
		}
		else if(n.hasLeft())
			return ""+traversePostOrder(n.getLeft())+" "+n.getValue();
		else if(n.hasRight())
			return ""+traversePostOrder(n.getRight())+" "+n.getValue();
		else 
			return "";
	}
	
	public String traverse(int i){
		if(i == 1)
			return traversePreOrder(root);
		else if(i == 2)
			return traverseInOrder(root);
		else if(i == 3)
			return traversePostOrder(root);
		else
			return "";
	}
	
	
	public static void main(String[] args) throws Exception{
		BinTree tree = new BinTree(10);
		tree.insert(100);
		tree.insert(5);
		tree.insert(4);
		tree.insert(6);
		tree.insert(90);
		tree.insert(110);
		tree.insert(80);
		System.out.println(tree.traverse(3));
		tree.remove(10);
		System.out.println(tree.traverse(3));
	}
}
