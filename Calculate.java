/*
 * UofT GPA Calculator
 * @author Julian Song
 */


public class Calculate {
	private StackData data;
	public Calculate(StackData data){
		this.data = data;
	}
	
	public double undergradGPACal(){
		double totalGPA = data.getTotalGPA();
		double totalWeight = data.getTotalWgt();
		double mark = totalGPA /(totalWeight*2);
		return mark;
	}
	
	public double undergradPercentageCal(){
		double totalGPA = data.getTotalMark();
		double totalWeight = data.getTotalWgt();
		double mark = totalGPA /(totalWeight*2);
		return mark;
	}
	public double gradGPACal(){
		double totalGPA = data.getTotalGPA();
		double totalWeight = data.getTotalWgt();
		double mark = totalGPA /(totalWeight*2);
		return mark;
	}
	
	public double gradPercentageCal(){
		double totalGPA = data.getTotalMark();
		double totalWeight = data.getTotalWgt();
		double mark = totalGPA /(totalWeight*2);
		return mark;
	}
}
