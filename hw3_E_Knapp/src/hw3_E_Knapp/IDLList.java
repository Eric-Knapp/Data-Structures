package hw3_E_Knapp;

import java.util.*;

public class IDLList<E> { // outter class
	
	private static class Node<E>{ // inner class node holding private inner class variables
		
		private E data;
		private Node<E> next;
		private Node<E> prev;
		
		// constructor -- elem
		private Node(E elem) {
			this.data = elem;
		}
		// constructor -- contains elem, prev, next
		public Node(E elem, Node<E> prev, Node<E> next) {
			
			this.data = elem;
			this.prev = prev;
			this.next = next;
		}	
	}
	
	// private data fields for outer class
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	// public list constructor
	public IDLList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
		
		this.indices = new ArrayList<>();
		}

	// method adds element at specified index
	public boolean add(int index, E elem) {
		
		if ((index < 0) || (index >= indices.size())) {
			throw new NullPointerException("Index non-existent, please review index count"); // throw exception if array is out of bounds
		} 
		
		if (index == 0) {
			add(elem);
			return true;
		}
		
		else {
			
			Node<E> node1 = indices.get(index);
			Node<E> newElement = new Node<>(elem);
			
			newElement.next = node1;
			newElement.prev = node1.prev;
			
			node1.prev.next = newElement;
			node1.prev = newElement;
			++size;
			
			indices.add(index, newElement);
			
			
			return true;
		}
	}
	
	// add elem as 1st num of the list
	public boolean add(E elem) {
		
		if (head == null) {
			head = new Node<>(elem);
			tail = head;
			++size;
			
			indices.add(0, head);
			
			
			return true;
		}
		
		else { 
			
			// if not null... insert 1st elem before the head in the list
			Node<E> elem1 = new Node<>(elem);
			
			elem1.next = head;
			head.prev = elem1;
			head = elem1;
			++size;
			
			indices.add(0, elem1);
			
			return true;
		}
	}
	
	// method appends int to the end of the list
	public boolean append(E elem) {
		
		if (head == null) {
			add(elem);
			return true;
		} 
		// if element is not null, new element is created and added to the tail end of the list
		Node<E> lastElement = new Node<>(elem);
		
		tail.next = lastElement;
		lastElement.prev = tail;
		tail = lastElement;
		++size;
		
		indices.add(lastElement); 
		return true;
	}
	
	//return the element at position index from the head node
	public E get(int index) {
		return indices.get(index).data;
	}
	
	public E getHead() {
		return head.data; // return head object
	}
	
	public E getLast() {
		return tail.data; // return tail object
	}
	
	public int size() {
		return indices.size(); // return list size
	}
	
	public E remove() { // remove the first element within the list
		
		if (head == null) {
			return null;
		}
		
		Node<E> temporary = head;
		
		head = head.next;
		head.prev = null;
		--size;
		
		indices.remove(temporary);
		return temporary.data;
	}
	
	public E removeLast() { // removes the tail node of the list
		
		if (tail == null) {
			return null;
		}
		
		Node<E> temporary = tail;
		
		tail = tail.prev;
		tail.next = null;
		--size;
		
		indices.remove(temporary);
		return temporary.data;
	}
	
	// method removes object from a specified index within the list
	public E removeAt(int index) {
		// throws exception if trying to remove a number from the list in which the index does not exist
		if ((index < 0) || (index >= indices.size())){
			throw new NullPointerException("Index is non-existent, cannot remove at an index that doesn't exist");
		}
		if (index == 0) {
			return remove();
		}
		if (index == indices.size() - 1) {
			return removeLast();
		}
		
		Node<E> deleteNode = indices.get(index);
		
		deleteNode.prev.next = deleteNode.next;
		deleteNode.next.prev = deleteNode.prev;
		
		--size;
		
		indices.remove(deleteNode);
		return deleteNode.data;
	}
	
	public boolean remove(E elem) {
		int i;
		
		for(i = 0; i < indices.size(); ++i) {
			if (indices.get(i).data == elem) {
				removeAt(i);
				return true;
			}
		}
		return false;
	}
	
	public String toString() { // prints the list in String format
		Node Head = head;
		String S = "";
		
		while (Head != null) {
			S = S + " " + Head.data.toString();
			Head = Head.next;
		}
		
		return S;
	}
	
	
}
	
	



