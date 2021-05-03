package hw5_E_Knapp;

import java.util.*;

public class Treap<E extends Comparable<E>>{
		
	/** 2.1 The Node Class: private static inner node of the Treap class */
		private static class Node<E extends Comparable<E>> {
			
			// public data fields
			public E data; 			// key for the search
			public int priority; 	// random heap priority
			public Node<E> left;
			public Node<E> right;
			
			// constructor with exception if data == null
			public Node(E data, int priority) {
				if (data == null) {
					throw new IllegalArgumentException("Data can't be null!");
				}
				
				this.data = data;
				this.priority = priority;
			}
			
			// rotate node right
			Node<E> rotateRight(){
				if (this.left != null) {
					Node<E> left = this.left;
					Node<E> leftR = left.right;
					left.right = this;
					left.right.left = leftR;
					
					return left;
				}
				return this;
			}
			
			// rotate node left
			Node<E> rotateLeft(){
				if(this.right != null) {
					Node<E> right = this.right;
					Node<E> rightL = right.left;
					right.left = this;
					right.left.right = rightL;
					
					return right;
				}
				return this;
			}
			
			// toString -- print out in correct format from assignment
			public String toString() {
				return "(key=" + data.toString() + ", priority=" + priority + ")";
			}
		}
		
		// private data fields -- root , random generator 
		private final Random priorityGenerator;
		private Node<E> root;
		
		
		// 2.2 The Treap Class -- Treap constructors 
		// creates empty Treap
		public Treap() {
			priorityGenerator = new Random();
		}
		
		// creates empty treap + initializes priorityGenrator using new random()
		public Treap(long seed) {
			priorityGenerator = new Random();	
		}
		
		
		// add constructor
		Boolean add(E key) {
			return add(key, priorityGenerator.nextInt());
		}
		
		
		/** 2.2.1 Add method */
		Boolean add(E key, int priority) {
			
			// created node_A as new node stores in root if root is null
			Node<E> node_A = new Node<>(key, priority);
	        if (root == null) {
	        	root = node_A;
	            return true;
	        }
	        // new arrayDeque created as stack , curr assigned to root
	        Deque<Node<E>> stack = new ArrayDeque<>();
	        Node<E> curr = root;
	        
	        while (true) {
	        	
	            if (curr.data.compareTo(key) == 0) {
	            	return false;
	            }
	            stack.push(curr);
	            
	            if (curr.data.compareTo(key) < 0) {
	                if (curr.right == null) {
	                    curr.right = node_A; // curr right assigned node_A if curr right is null
	                    break;
	                }
	                curr = curr.right; // curr shifted right
	            }
	            
	            else { // curr left assigned node_A if curr.left is null
	            	
	                if (curr.left == null) {
	                    curr.left = node_A;
	                    break;
	                }
	                
	                curr = curr.left;
	            }
	        }
	        
	        reheap(node_A, stack); // reheap method call
	        return true;
	    }
		
		/** helper method -- restore heap invariant */
		Boolean reheap(Node<E> node, Deque<Node<E>> stack) {
			
			while (!stack.isEmpty() && stack.peek().priority < node.priority) {
				Node<E> parent = stack.pop();
				
	            if (!child_isLeft(parent, node)) {
	                if (!stack.isEmpty()) {
	                    boolean child_isLeft = child_isLeft(stack.peek(), parent);
	                    parent = parent.rotateLeft();
	                    
	                    if (child_isLeft) {
	                        stack.peek().left = parent;
	                    }
	                    else {
	                    	stack.peek().right = parent;
	                    }
	                }
	                
	                else {
	                    parent = parent.rotateLeft();
	                }
	            }
	            
	            else {
	                if (!stack.isEmpty()) {
	                    boolean child_isLeft = child_isLeft(stack.peek(), parent);
	                    parent = parent.rotateRight();
	                    
	                    if (child_isLeft) {
	                        stack.peek().left = parent;
	                    }
	                    else {
	                        stack.peek().right = parent;
	                    } 
	                }
	                
	                else {
	                    parent = parent.rotateRight();
	                }
	            }
	            
	            node = parent;
	        }
	        
			if (stack.size() == 0){
	            root = node;
	        }
	        return true;
	    }			
		
		// helper method 
		Boolean child_isLeft(Node<E> parent, Node<E> child) {
			if (parent == null || parent.left == null) {
				return false;
			}
			
			return parent.left.data.compareTo(child.data) == 0;
		}
	

		/** 2.2.2 Delete Operation -- deletes node with the given key */
	    boolean delete(E key){
	    	
	        Node<E> curr = root;
	        Node<E> parent = null;
	        
	        // curr node not null --> compare to key
	        while (curr != null) {
	            if (curr.data.compareTo(key) == 0) {
	                break;
	            }
	            // if > 0 --> curr shift left
	            if (curr.data.compareTo(key) > 0) {
	                parent = curr;
	                curr = curr.left;
	            }
	            // else -- > curr shift right
	            else {
	                parent = curr;
	                curr = curr.right;
	            }
	        }
	        
	        if (curr == null) {
	            return false;
	            }
	        
	        if (curr.left == null && curr.right == null && parent == null) {
	            root = null;
	            return true;
	        }
	        
	        // curr node left and right null not null
	        while (curr.left != null || curr.right != null) {
	            if (curr.left == null) {
	                if (parent == null) {
	                    curr = curr.rotateLeft();
	                    root = curr;
	                    parent = curr;
	                }
	                else {
	                    if (child_isLeft(parent, curr)) {
	                        curr = curr.rotateLeft();
	                        parent.left = curr;
	                        parent = parent.left;
	                    }
	                    else {
	                        curr = curr.rotateLeft();
	                        parent.right = curr;
	                        parent = parent.right;
	                    }
	                }
	                curr = curr.left;
	            }
	            
	            else if (curr.right == null) {
	            	
	                if (parent == null) {
	                    curr = curr.rotateRight();
	                    root = curr;
	                    parent = curr;
	                }
	                
	                else {
	                    if (child_isLeft(parent, curr)) {
	                        curr = curr.rotateRight();
	                        parent.left = curr;
	                        parent = parent.left;
	                    }
	                    else {
	                        curr = curr.rotateRight();
	                        parent.right = curr;
	                        parent = parent.right;
	                    }
	                }
	                curr = curr.right;
	            }
	            // curr Node left priority is greater than curr Node right priority
	            else if (curr.left.priority > curr.right.priority) {
	                if (parent == null) {
	                    curr = curr.rotateRight();
	                    root = curr;
	                    parent = curr;
	                }
	                else {
	                    if (child_isLeft(parent, curr)){
	                        curr = curr.rotateRight();
	                        parent.left = curr;
	                        parent = parent.left;
	                    }
	                    else {
	                        curr = curr.rotateRight();
	                        parent.right = curr;
	                        parent = parent.right;
	                    }
	                }
	                curr = curr.right;
	            }
	            
	            else { 
	                if (parent == null) {
	                    curr = curr.rotateLeft();
	                    root = curr;
	                    parent = curr;
	                }
	                else {
	                    if (child_isLeft(parent, curr)) {
	                        curr = curr.rotateLeft();
	                        parent.left = curr;
	                        parent = parent.left;
	                    }
	                    else {
	                        curr = curr.rotateLeft();
	                        parent.right = curr;
	                        parent = parent.right;
	                    }
	                }
	                curr = curr.left;
	            }
	            
	        }
	        if (child_isLeft(parent,curr)) {
	            parent.left = null;
	        }
	        else {
	            parent.right = null;
	        }
	        
	        return true;
	    }
			
		/** 2.2.3 Find Operation -- finds node with the given key in the treap */
		private boolean find(Node<E> root, E key) {
			Node<E> curr = root;
			
			while (curr != null) {
				if (curr.data.compareTo(key) == 0) {
					return true;
				}
				if (curr.data.compareTo(key) > 0) {
					curr = curr.right;
				}
				else {
					curr = curr.right;
				}
			}
			return false;
		}
		
		public boolean find (E key) {
			return find(root, key);
		}
		
		/** 2.2.4 toString Operation
		 * carries out pre_String_Order traversal of the tree
		 * returns a representation of the nodes as a String
		 */
		public String toString() {
			StringBuilder string1 = new StringBuilder();
			pre_String_Order(root, string1, 0, " ");
			
			return string1.toString();
		}
		
		// helper method 
	    public void pre_String_Order (Node<E> root, StringBuilder string1, int tier, String previous) {
	    	
			if (root == null) {
				
				for (int i = 0; i < tier; ++i) 
					string1.append(previous);
					string1.append("null" + "\n");
					
					return;
					
					}
				
				for (int i = 0; i < tier; ++i) 
					string1.append(previous);
					string1.append(root.toString()).append("\n");
					pre_String_Order(root.left, string1, tier + 1, previous);
					pre_String_Order(root.right, string1, tier + 1, previous);
				}
	
		// All tests executed from Main
	    public static void main(String[] args) {
	
	    	Treap<Integer> test = new Treap<Integer>();
	    	
	    	// testing add method call
	    	System.out.println("Testing add method: \n");
	    	test.add(4,19);
	    	test.add(2,31);
	    	test.add(6,70);
	    	test.add(1,84);
	    	test.add(3,12);
	    	test.add(5,83);
	    	test.add(7,26);
	    	System.out.println(test.toString());
	    
	    	System.out.println("--------------------------------------------");
	    	System.out.println("Test for delete key 5: \n");
	    	test.delete(5); // test delete method call
	    	System.out.println(test.toString()); 
	    
	    	// Test for find method both existing and non existing keys
	    	System.out.println("--------------------------------------------");
	    	System.out.println("Test for find 7:");
	    	System.out.println(test.find(7));  // output for find test -- 7 should return true
	    	System.out.println();
	    	System.out.println("Test for find 9:");
	    	System.out.println(test.find(9));  // output for find test -- 9 should return false
	    	

	    	}
	    
	    }

/*
 * Eric Knapp
 * Homework 5: Treaps
 * 4/21/21
 */
