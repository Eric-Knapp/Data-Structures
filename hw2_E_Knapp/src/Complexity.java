

public class Complexity {

	// method1 -- time complexity of O(n^2) 
	public static void method1(int n) {
		int i;
		int y;
		int c = 0;
		
		for(i = 0; i < n; ++i) {
			for(y = 0; y < n; ++y) {
				System.out.println("opertaions: " + c);
				++c; 
		}
	}
}
	// method2 -- time complexity of 0(n^3)
	public static void method2(int n) {
		int i;
		int x;
		int y;
		int c = 0;
		
		for (i = 0; i < n; ++i) {
			for (x = 0; x < n; ++x) {
				for (y = 0; y < n; ++y) {
					System.out.println("operations: " + c);
					++c;
				}
			}
		}
	}
	
	// method3 -- time complexity of 0(log n)
	public static void method3(int n) {
		int i;
		int c = 0;
		
		for (i = 1; i <= n; i *= 2) {
			System.out.println("operations: " + c);
			++c;
		}
	}
	
	// method4 -- time complexity of 0 (n log n)
	public static void method4(int n) {
		int i;
		int y;
		int c = 0;
		
		for (i = 0; i < n; ++i) {
			for (y = 1; y <= n; y *= 2) {
				System.out.println("operations: " + c);
				++c;
			}
		}
	}
	
	// method5 -- time complexity of 0(log log n)
	public static void method5(int n) {
		int i;
		int c = 0;
		
		for(i = 2; i < n; i = i * i) {
			System.out.println("operations: " + c);
			++c;
		}	
	}
	
	
	
	
	// method 6 -- time complexity of O(2^n)
	public static int method6(int n) {
		int i;
		int c = 0;
		
		for (i = 1; i <= Math.pow(2, n); ++i) {
			System.out.println("operations: " + c);
			++c;
		
		}
		return c;  // change to return c instead of n to correct this method
	}
	

	// testing methods with integers as input
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int number1 = 2;
		int number2 = 20;

		
		System.out.println("method 1 ------------------ \n");
		method1(number1);
		System.out.println();
		
		System.out.println("method 2 ------------------ \n");
		method2(number1);
		System.out.println();
		
		System.out.println("method 3 ------------------ \n");
		method3(number2);
		System.out.println();
		
		System.out.println("method 4 -------------------  \n");
		method4(number2);
		System.out.println();
		
		System.out.println("method 5 ------------------- \n");
	    method5(number2);
		System.out.println();
		
		System.out.println("method 6 -------------------\n");
	    method5(number2);
		System.out.println();
		
	}

}
