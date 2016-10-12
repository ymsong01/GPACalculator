/*
 * UofT GPA Calculator
 * @author Julian Song
 */

public interface StackADT{
	public void push(double element, double e2);
	public double pop();
	public double peek();
	public boolean isEmpty();
	public int size();
	public String toString();
}
