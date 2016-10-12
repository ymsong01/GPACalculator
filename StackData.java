/*
 * UofT GPA Calculator
 * @author Julian Song
 */


public class StackData implements StackADT{
	private final int StackCapacity = 50;
	private double[] stackData;
	private int size = 0;
	private double totalGPA = 0;
	private double totalWgt = 0;
	private double totalMark = 0;
	private boolean isUndergrad = true;
	
	public StackData(){
		stackData = new double[StackCapacity];
	
	}
	
	public StackData(int num){
		stackData = new double[num];
	}
	
	public double peek() throws EmptyStackDataException{
		if (isEmpty()){
			throw new EmptyStackDataException();
		}
		return stackData[size -1];
	}
	
	public boolean isEmpty(){
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public int size(){
		return size;
	}
	
	public double getTotalWgt(){
		return totalWgt;
	}
	public double getTotalMark(){
		return totalMark;
	}
	public double getTotalGPA(){
		return totalGPA;
	}
	public double pop() throws EmptyStackDataException{
		if (isEmpty()){
			throw new EmptyStackDataException();
		}
		double poppedItem = stackData[size -1];
		size--;
		return poppedItem;
		
	}
	
	public void push(double wgt, double mark){
		totalWgt+= wgt;
		double gpa;
		if (isUndergrad()){
			if (mark < 50){
				gpa = 0;
			} else if ((50<= mark)&& (mark <=52)){
				gpa = 0.7;
			}else if ((53<= mark)&& (mark <=56)){
				gpa = 1;
			}else if ((57<= mark)&& (mark <=59)){
				gpa = 1.3;
			}else if ((60<= mark)&& (mark <=62)){
				gpa = 1.7;
			}else if ((63<= mark)&& (mark <=66)){
				gpa = 2;
			}else if ((67<= mark)&& (mark <=69)){
				gpa = 2.3;
			}else if ((70<= mark)&& (mark <=72)){
				gpa = 2.7;
			}else if ((73<= mark)&& (mark <=76)){
				gpa = 3;
			}else if ((77<= mark)&& (mark <=79)){
				gpa = 3.3;
			}else if ((80<= mark)&& (mark <=84)){
				gpa = 3.7;
			}else if ((85<= mark)&& (mark <=89)){
				gpa = 4;
			}else {
				gpa = 4;
			}
			gpa = gpa * wgt*2;
			totalGPA+=gpa;
		}else {
			if (mark < 70){
				gpa = 0;
			
			}else if ((70<= mark)&& (mark <=72)){
				gpa = 2.7;
			}else if ((73<= mark)&& (mark <=76)){
				gpa = 3;
			}else if ((77<= mark)&& (mark <=79)){
				gpa = 3.3;
			}else if ((80<= mark)&& (mark <=84)){
				gpa = 3.7;
			}else if ((85<= mark)&& (mark <=89)){
				gpa = 4;
			}else {
				gpa = 4;
			}
			gpa = gpa * wgt*2;
			totalGPA+=gpa;
		}
		mark = mark * wgt*2;
		totalMark+=mark;
		
		if (size == StackCapacity){
			double[] newOne = new double[stackData.length+1];
			for (int i=0; i<stackData.length;i++) {
				newOne[i] = stackData[i];
			}
			stackData = newOne;
		}
		stackData[size] = mark;
		size++;
	}

	public boolean isUndergrad() {
		return isUndergrad;
	}

	public void setUndergrad(boolean isUndergrad) {
		this.isUndergrad = isUndergrad;
	}	
}
	

