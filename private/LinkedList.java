import java.util.ArrayList;

public class LinkedList{
	private Node head;

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}
	
	public Node insert(String key, int value){
		
		Node f = find(key);
		
		if(f != null){
			f.setValue(value);
		}else{
			Node p = new Node();
			p.setKey(key);
			p.setValue(value);
			if(getHead() == null)
				setHead(p);
			else
				getLast().setNext(p);
		}
		return head;
	}
	
	public Node increase(String key, int incBy){
		Node f = find(key);
		
		if(f != null){
			f.setValue(f.getValue() + incBy);
		}else{
			Node p = new Node();
			p.setKey(key);
			p.setValue(incBy);
			if(getHead() == null)
				setHead(p);
			else
				getLast().setNext(p);
		}
		return head;
	}
	
	public Node find(String key){
		Node t = getHead();
		while(t!=null){
			if(t.getKey().equals(key))
				return t;
			t=t.getNext();
		}
		return null;
	}
	
	public Node getLast(){
		Node t = getHead();
		while(t!= null){
			if(t.getNext() == null)
				return t;
			t = t.getNext();
		}
		return null;
	}
	
	
	public int size(){
		int n = 0;
		Node t = getHead();
		while(t!=null){
			t = t.getNext();
			n++;
		}
		return n;
	}
	
	public void printKeyVal(){
		Node t = getHead();
		while(t!=null){
			System.out.print("[" + t.getKey() + ", " + t.getValue()+"] ");
			t = t.getNext();
		}
		System.out.println();
	}
	
	public ArrayList<String> getKeys(){
		ArrayList<String> keys = new ArrayList<String>();
		Node t = getHead();
		while(t!=null){
			keys.add(t.getKey());
			t = t.getNext();
		}
		return keys;
	}
	
	public void delete(String key){
		Node t = getHead();
		Node prev = null;
		while(t!= null){
			if(t.getKey().equals(key))
				break;
			prev = t;
			t = t.getNext();
		}
		if(t != null){
			if(prev == null)
				setHead(t.getNext());
			else
				prev.setNext(t.getNext());
		}
	}
}