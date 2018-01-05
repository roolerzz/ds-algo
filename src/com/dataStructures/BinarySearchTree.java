package com.dataStructures;

import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;

// extends comparable added to say that only comparable keys could be added into the data structure as our implementation 
	// depends on the compareTo method. If that is not enforced, data structure operations might not work correctly.
public class BinarySearchTree<Key extends Comparable<Key>,Value>{

	private Node root;
	
	private class Node{
		Key key;
		Value val;
		Node left,right;
		// size of the subtree rooted at that node.
		int size;
		
		public Node(Key key, Value val, int size) {
			this.key = key;
			this.val = val;
			this.size = size;
		}
	}
	
	public void put(Key k, Value v) {
		if(k==null) throw new IllegalArgumentException("Argument to put cannot be null.");
		if(v == null) {
			delete(k);
			return;
		}
		root = put(root,k,v);
	}
	
	private Node put(Node x, Key k, Value v) {
		if(x==null) return new Node(k,v,1);
		int cmp = k.compareTo(x.key);
		if(cmp<0) x.left = put(x.left,k,v);
		else if(cmp >0) x.right =  put (x.right,k,v);
		else x.val = v;
		x.size = 1 + size(x.left) + size(x.right);
		return x;
	}
	
	public int size() { 
		return size(root);
	}
	
	public int size(Node x) { 
		if(null == x) { return 0;}
		return 1 + x.size;		
	}
	
	public boolean isEmpty() { return size()==0;}
	
	public Value get(Key k) {
		/*Node node = root;
		//Iterative Solution.
		while(node !=null)
		{
			int cmp = key.compareTo(node.key);
			if(cmp < 0) node = node.left;
			else if (cmp >0) node = node.right;
			else return node.val;
		}
		//return get(key,root);
		return null;*/
		
		//recursive solution
		return get(root,k);
	}
	
	private Value get(Node x, Key k) {
		if(null == k) throw new IllegalArgumentException("Key is null");
		if (x == null) return null;
		int cmp = k.compareTo(x.key);
		if (cmp < 0) return get(x.left, k);
		else if (cmp > 0) return get(x.right, k);
		else return x.val;
	}
	
	public boolean contains(Key k) {
		if(null == k) throw new IllegalArgumentException("Key is null");
		return get(k)!= null ;
	}
	
	public int rank(Key k) {
		if(null == k) throw new IllegalArgumentException("Key is null");
		return rank(root,k);
		
	}
	// Number of keys in the subtree x less than k.
	private int rank(Node x, Key k) {
		if (x == null) return 0; 
		int cmp = k.compareTo(x.key);
		if(cmp<0) return rank(x.left,k);
		else if (cmp >0) return 1 + size(x.left) + rank(x.right,k);
		else return size(x.left);
 	}

	// Find the Kth largest key. Kth order statistics.
	public Key select(int k) {
		if (k < 0 || k >= size()) {
			throw new IllegalArgumentException("argument to select() is invalid: " + k);
		}
		Node x = select(root, k);
		return x.key;
	}
	
	private Node select(Node x, int k) {
		if(x==null) return null;
		int t = size(x.left);
		if(t>k) return select(x.left,k);
		else if (t<k) return select(x.right,k-t-1);
		else return x;
	}

	public void delete(Key key) {}
	
	public Iterable<Key> iterator(){
		 if (isEmpty()) return new PriorityQueue<Key>();
		Queue<Key> queue = new PriorityQueue<>();
		Node x = root;
		while(x!=null) {
			if(x.left == null) 
			{
				queue.add(x.key);
				x=x.right;
			}
			x = x.left;
		}
		
		
		
		
		return queue;
		
	}
	
	public Key min() {
		// go left.
		if(isEmpty()) throw new NoSuchElementException("Cannot find minimum on an empty tree.");
		return min(root).key;
	}
	
	private Node min(Node x) {
		if (x.left == null) return x; 
		 else return min(x.left); 
	}
	
	public Key max() {
		// go right.
		if (isEmpty())
			throw new NoSuchElementException("Cannot find minimum on an empty tree.");
		return max(root).key;
	}
	private Node max(Node x) {
		if(x.right == null) return x;
		else return max(x.right);
		
	}
	public Key floor(Key k) {return null;}
	
	public Key ceiling(Key k) { return null;}
	
	
}
