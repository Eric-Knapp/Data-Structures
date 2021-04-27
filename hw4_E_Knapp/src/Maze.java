

import java.util.*;
/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    
    // COMPLETE HERE FOR PROBLEM 1
    public boolean findMazePath(int x, int y) {
    	
    	//if current coordinate out of the grid.. returns false
    	if (x < 0 || y < 0){
    		return false;
    	}
    	else if (x >= maze.getNCols()) {
    		return false;
    	}
    	else if (y >= maze.getNRows()) {
    		return false;
    	}
    	
    	//if point current coordinate not part of maze path.. return false
    	else if (!maze.getColor(x, y).equals(NON_BACKGROUND)) {
    		return false;
    	}
    	
    	//if current coordinate == exit, return true
    	else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
    		maze.recolor(x, y, PATH);
    		return true;
    	}
    	
    	//if current coordinate didn't make it to exit.. set current point to PATH
    	else {
    		maze.recolor(x, y, PATH); 
    		
    		//if coordinates neighbor is exit coordinate -- return true
    		if (findMazePath(x - 1, y) || findMazePath(x + 1, y)) {
    			return true;
    		}
    		if (findMazePath(x, y + 1) || findMazePath(x, y - 1)) {
    			return true;
    		}
    	
    		//else -- recolor current  point to TEMPORARY
    	else {
    		maze.recolor(x, y, TEMPORARY);
    		return false;
    		}   		
    	}	
    }
    
   //helper method
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace){

    	//if x, y are out of bounds the method will terminate
    	if (x < 0) {  
    		return;
    	}
        if (y < 0) { 
        	return;
    	}
    	if (x > maze.getNCols() - 1) {
    		return;
    	}
    	if (y > maze.getNRows() - 1 ) {
    		return;
    	}
    	// if x, y != to red, the method will terminate
    	if ((!maze.getColor(x, y).equals(NON_BACKGROUND))){ //recolor to NON_BACKGROUND
    		return;
    	}
    	
    	//if exit coordinate found - push exit coordinate to trace and add to result
    	else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
    		trace.push(new PairInt(x, y)); 
    		
    		//paths - ArrayList currentCells is result of all paths possible
    		ArrayList<PairInt> currentCells = new ArrayList<>(trace); //
    		
    		
    		result.add(currentCells);
    		trace.pop(); 
    		maze.recolor(x, y, NON_BACKGROUND); 
    		
    		return;
    		} 
    	
    	//recursion if coordinate is not exit coordinate
    	else {
    		
    		trace.push(new PairInt(x, y));
    		
    		maze.recolor(x, y, PATH);
    		
    		// try both paths -- then, recolor to NON_BACKGROUND

    		findMazePathStackBased(x + 1, y, result, trace);
    		
    		findMazePathStackBased(x - 1, y, result, trace);
    		
    		findMazePathStackBased(x, y + 1, result, trace);
    		
    		findMazePathStackBased(x, y - 1, result, trace);
    		
   
    		maze.recolor(x, y, NON_BACKGROUND);
    		trace.pop();
    		
    		
    		return;
    		}
    	}
    
    
    // ADD METHOD FOR PROBLEM 2 HERE
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
    	ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
    	Stack<PairInt> trace = new Stack<>();
    	findMazePathStackBased(0, 0, result, trace);
    	
    	return result;
    	}
    	

    // ADD METHOD FOR PROBLEM 3 HERE
    public ArrayList<PairInt> findMazePathMin(int x, int y) {
    	int z = 0;
    	int[] c;
    	int minimum;
    	int i;
    	
    	//paths - ArrayList paths is result of all paths possible
    	ArrayList<ArrayList<PairInt>> paths;
    	paths = findAllMazePaths(x, y);
    	
    	c = new int[paths.size()]; // new ArrayList in which size = size of paths
    	
    
    	for (i = 0; i < paths.size(); i++) {
    		c[i] = paths.get(i).size();
    		}
    	
    	minimum = c[0];
    	
    	 
    	for (i = 1; i < c.length; i++) { // loop to find smallest count
    		if (c[i] < minimum) {
    			minimum = c[i];
    			z = i;
    			}
    		}
    	
    	
    	return paths.get(z); // return the shortest path

}
    
    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/

// Eric Knapp
// 4.16.2021
// hw4: Recursion 
