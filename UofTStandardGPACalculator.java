/*
 * UofT GPA Calculator
 * @author Julian Song
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class UofTStandardGPACalculator extends JFrame {
	private JFrame mainFrame = new JFrame("UofTGPACalculator");
	JPanel mainPanel = new JPanel();
	JPanel undergradPanel = new JPanel();
	JPanel gradPanel = new JPanel();
	JButton undergradButton = new JButton("Switch to Undergraduate");
	JButton gradButton = new JButton("Switch to Graduate");
	JButton undergradGpaButton = new JButton("Calculate as GPA Grade");
	JButton undergradPercentageButton = new JButton("Calculate as Percentage Grade");
	JButton gradGpaButton = new JButton("Calculate as GPA Grade");
	JButton gradPercentageButton = new JButton("Calculate as Percentage Grade");
	JButton addRow1 = new JButton("Add");
	JButton addRow2 = new JButton("Add");
	CardLayout cardLayout = new CardLayout();
	
	private JLabel courseName1 = new JLabel("Enter the name of the course:");
	private JTextField cName1 = new JTextField();
	private JLabel courseScore1 = new JLabel("Enter the score of the course:");
	private JTextField cScore1 = new JTextField();
	private JLabel courseWeight1 = new JLabel("Enter the weight of the course:");
	private JTextField cWeight1 = new JTextField();
	
	private JLabel courseName2 = new JLabel("Enter the name of the course:");
	private JTextField cName2 = new JTextField();
	private JLabel courseScore2 = new JLabel("Enter the score of the course:");
	private JTextField cScore2 = new JTextField();
	private JLabel courseWeight2 = new JLabel("Enter the weight of the course:");
	private JTextField cWeight2 = new JTextField();

	private JLabel messageIntro1 = new JLabel();
	private JLabel messageIntro2 = new JLabel();
	
	JLabel msg = new JLabel();
	JFrame error = new JFrame("Error");
	JFrame score = new JFrame("Score");
	private DefaultTableModel model; 
	private DefaultTableModel model2;
	private JTable table;
	private JTable table2;

	private StackData dt1;
	private StackData dt2;
	
	public UofTStandardGPACalculator() {
		dt1 = new StackData();
		dt2 = new StackData();
		dt2.setUndergrad(false);
		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Course Name");model.addColumn("Score");model.addColumn("Weight");
		model2 = new DefaultTableModel();
		table2 = new JTable(model2);
		model2.addColumn("Course Name");model2.addColumn("Score");model2.addColumn("Weight");
		
		table.setPreferredScrollableViewportSize(new Dimension(300, 300));
		table.setFillsViewportHeight(true);
		table2.setPreferredScrollableViewportSize(new Dimension(300, 300));
		table2.setFillsViewportHeight(true);
		JScrollPane sPane = new JScrollPane(table);
		JScrollPane sPane2 = new JScrollPane(table2);
		
		mainPanel.setLayout(cardLayout);

		cName1.setColumns(10);
		cName2.setColumns(10);
		cScore1.setColumns(10);
		cScore2.setColumns(10);
		cWeight1.setColumns(10);
		cWeight2.setColumns(10);
		
		undergradPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		gradPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//Adding buttons, intro, etc to undergradPanel
		undergradPanel.add(messageIntro1);
		messageIntro1.setText("UofT Undergraduate Calculator");
		undergradPanel.add(sPane);
		undergradPanel.add(courseName1);
		undergradPanel.add(cName1);
		undergradPanel.add(courseScore1);
		undergradPanel.add(cScore1);
		undergradPanel.add(courseWeight1);
		undergradPanel.add(cWeight1);
		undergradPanel.add(addRow1);
		undergradPanel.add(undergradGpaButton);
		undergradPanel.add(undergradPercentageButton);
		undergradPanel.setBackground(Color.ORANGE);
		undergradPanel.add(gradButton);
		
		//Adding buttons, intro, etc to gradPanel
		gradPanel.add(messageIntro2);
		messageIntro2.setText("UofT Graduate Calculator");
		gradPanel.add(sPane2);
		gradPanel.add(courseName2);
		gradPanel.add(cName2);
		gradPanel.add(courseScore2);
		gradPanel.add(cScore2);
		gradPanel.add(courseWeight2);
		gradPanel.add(cWeight2);
		gradPanel.add(addRow2);
		gradPanel.add(gradGpaButton);
		gradPanel.add(gradPercentageButton);
		gradPanel.setBackground(Color.GREEN);
		gradPanel.add(undergradButton);
		
		mainPanel.add(undergradPanel, "1");
		mainPanel.add(gradPanel, "2");
		
		// Show undergradPanel
		cardLayout.show(mainPanel, "1");
		
		// Listener for button to switch to undergradPanel when clicked
		undergradButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
					cardLayout.show(mainPanel,"1");
					
				}
			});
		
		// Listener for button to switch to gradPanel when clicked
		gradButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
					cardLayout.show(mainPanel,"2");
				
				}
		});
		
		// Listener for button to add course info(name, grade and weight) for undergrad when clicked
		addRow1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				String name;
				double grade;
				double weight;
				try{
					name = cName1.getText();
					grade = Double.parseDouble(cScore1.getText());
					weight = Double.parseDouble(cWeight1.getText());
					
					model.addRow(new Object[]{name, grade, weight});
					dt1.push(weight, grade);
				} catch (Exception exe){
					JOptionPane.showMessageDialog(error, "Invalid value or please enter correct input value");
				}
			}
		}
		);
		
		// Listener for button to add course info(name, grade and weight) for grad when clicked
		addRow2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				String name;
				double grade;
				double weight;
				try{
					name = cName2.getText();
					grade = Double.parseDouble(cScore2.getText());
					weight = Double.parseDouble(cWeight2.getText());
					
					model2.addRow(new Object[]{name, grade, weight});
					dt2.push(weight, grade);
				} catch (Exception exe){
					JOptionPane.showMessageDialog(error, "Invalid value or please enter correct input value");
				}
			}
		}
		);
		
		// Listener for button to Calculate undergradGpa
		undergradGpaButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					Calculate r = new Calculate(dt1);
					double n =r.undergradGPACal();
					
					JOptionPane.showMessageDialog(score, Double.toString(n));
				} catch(Exception ex){
					JOptionPane.showMessageDialog(error, "Invalid value or please enter correct input value");
				}
		}
		});
		
		// Listener for button to Calculate undergradPercentage
		undergradPercentageButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					Calculate r = new Calculate(dt1);
					double n =r.undergradPercentageCal();
					
					JOptionPane.showMessageDialog(score, Double.toString(n));
				} catch(Exception ex){
					JOptionPane.showMessageDialog(error, "Invalid value or please enter correct input value");
				}
		}
		});
		
		// Listener for button to Calculate gradGpa
		gradGpaButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					Calculate r = new Calculate(dt2);
					double n =r.gradGPACal();
					
					JOptionPane.showMessageDialog(score, Double.toString(n));
				} catch(Exception ex){
					JOptionPane.showMessageDialog(error, "Invalid value or please enter correct input value");
				}
		}
		});
		
		// Listener for button to Calculate gradPercentage
		gradPercentageButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					Calculate r = new Calculate(dt2);
					double n =r.gradPercentageCal();
					
					JOptionPane.showMessageDialog(score, Double.toString(n));
				} catch(Exception ex){
					JOptionPane.showMessageDialog(error, "Invalid value or please enter correct input value");
				}
		}
		});
		
		//setting up mainFrame
		mainFrame.setBounds(500, 200, 350, 650);
		mainFrame.getContentPane().add(mainPanel);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
	}
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable(){
			public void run(){
				try {
					new UofTStandardGPACalculator();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
	
	
	


