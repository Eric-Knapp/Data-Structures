package hw3_E_Knapp;

public class IDLList_Test {

	public static void main(String[] args) {
		
		IDLList list1 = new IDLList();
		
		// create list by adding numbers with and without indexes
		list1.add(12);
		System.out.println(list1.toString());
		System.out.println();
		
		list1.add(0, 6);
		System.out.println(list1.toString());
		System.out.println();
		
		list1.add(1, 10);
		System.out.println("List1: " + list1.toString());
		System.out.println();
		// testing append to insert 30 at eng of the list
		list1.append(35);
		System.out.println("Append 35 to list1: " + list1);
		System.out.println();
		
		//get position of 2 in the list
		System.out.println("position 2 in list = " + list1.get(2));
		System.out.println();
		
		//get head in the list
		System.out.println("head in list = " + list1.getHead());
		System.out.println();
		
		//get tail in the list
		System.out.println("tail in list = " + list1.getLast());
		System.out.println();
		
		//size test - size correct == 4
		System.out.println("size should be 4 -- correct? --> " + list1.size());
		System.out.println();
		
		//remove first and last in the list
		
		System.out.println("Remove first and last in the list: \n");
		System.out.println(list1.toString());
		list1.remove();
		System.out.println(list1.toString());
		System.out.println();
		System.out.println(list1.toString());
		list1.removeLast();
		System.out.println(list1.toString());
		System.out.println();
		
		System.out.println("remove at place 1 in the list -- should remove 10: \n");
		System.out.println(list1.toString());
		list1.removeAt(1);
		System.out.println(list1.toString());
		System.out.println();
		
	
		System.out.println("remove 2 from the list: \n");
		list1.add(0,3);
		list1.add(1,2);
		
		// list is 3 2 8
		System.out.println(list1.toString());
		list1.remove(2);
		System.out.println(list1.toString());
		
//		System.out.println();
//		System.out.println("test index out of bounds: ");
//		list1.add(5,8);
		
		System.out.println();
		System.out.println("check removeAt exception:");
		
		list1.removeAt(12);
		
		
		
		
		
	
		

	}

}
