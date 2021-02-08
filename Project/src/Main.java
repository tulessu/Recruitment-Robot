import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Main extends JFrame implements ActionListener {

	private JLabel lblSoc, lblGPA, lblAge, lblAlg, lblKNN, lblInfo;
	private JTextField txtSoc, txtGPA, txtAge, txtAlg, txtKNN;
	private JButton btnScore;

	public Main() {
		setLayout(null);
		setSize(950, 500);
		setLocation(500, 100);

		lblSoc = new JLabel("Enter social skill:");
		lblSoc.setLocation(100, 65);
		lblSoc.setSize(150, 50);
		add(lblSoc);

		lblAlg = new JLabel("Enter algorithm skill:");
		lblAlg.setLocation(250, 65);
		lblAlg.setSize(150, 50);
		add(lblAlg);

		lblGPA = new JLabel("Enter GPA:");
		lblGPA.setLocation(410, 65);
		lblGPA.setSize(100, 50);
		add(lblGPA);

		lblAge = new JLabel("Enter age:");
		lblAge.setLocation(530, 65);
		lblAge.setSize(100, 50);
		add(lblAge);

		lblKNN = new JLabel("Enter k value for KNN algorithm:");
		lblKNN.setLocation(660, 65);
		lblKNN.setSize(200, 50);
		add(lblKNN);

		lblInfo = new JLabel("");
		lblInfo.setLocation(100, 300);
		lblInfo.setSize(250, 50);
		add(lblInfo);

		txtSoc = new JTextField();
		txtSoc.setLocation(100, 100);
		txtSoc.setSize(100, 30);
		add(txtSoc);

		txtAlg = new JTextField();
		txtAlg.setLocation(250, 100);
		txtAlg.setSize(120, 30);
		add(txtAlg);

		txtGPA = new JTextField();
		txtGPA.setLocation(410, 100);
		txtGPA.setSize(80, 30);
		add(txtGPA);

		txtAge = new JTextField();
		txtAge.setLocation(530, 100);
		txtAge.setSize(80, 30);
		add(txtAge);

		txtKNN = new JTextField();
		txtKNN.setLocation(660, 100);
		txtKNN.setSize(130, 30);
		add(txtKNN);

		btnScore = new JButton("Score");
		btnScore.setLocation(390, 200);
		btnScore.setSize(150, 50);
		add(btnScore);

		setVisible(true);

	}

	public static void main(String args[]) {
		new Main();

		String csvFile = "data.csv";// place the data.csv above 'src' folder
		BufferedReader br = null;
		int rowCount = 0;
		String line = "";
		String[] lineValues = new String[5]; // array for values in each row
		Student[] inputArray = null; // student array

		try {
			br = new BufferedReader(new FileReader(csvFile));

			// get the total number of rows.
			while ((line = br.readLine()) != null) {
				rowCount++;
			}

			inputArray = new Student[rowCount - 1];
			rowCount = 0;
			br = new BufferedReader(new FileReader(csvFile));

			while ((line = br.readLine()) != null) {
				if (rowCount > 0) {
					lineValues = line.split(",");

					inputArray[rowCount - 1] = new Student(Double.parseDouble(lineValues[0]),
							Double.parseDouble(lineValues[1]), Double.parseDouble(lineValues[2]),
							Double.parseDouble(lineValues[3]), Double.parseDouble(lineValues[4]), rowCount - 1); // default
																													// order
																													// of
																													// the
																													// current
																													// student
																													// in
																													// the
																													// array.

				}
				rowCount++;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		// read input from the user
		Scanner sc = new Scanner(System.in);

		double[] x = new double[4];
		int K = 0;
		System.out.print("Enter social: ");
		x[0] = sc.nextDouble();
		System.out.print("Enter algorithm: ");
		x[1] = sc.nextDouble();
		System.out.print("Enter gpa: ");
		x[2] = sc.nextDouble();
		System.out.print("Enter age: ");
		x[3] = sc.nextDouble();

		System.out.print("Enter K for NN: ");
		K = sc.nextInt();

		System.out.println("");

		// create new student for comparison
		Student inputStudent = new Student(x[0], x[1], x[2], x[3], -1, -1);
		QuickSortStudent ob = new QuickSortStudent();
		Student[] sortedStudents = ob.sortStudents(inputArray, inputStudent);

		// count the number of sums for each 0 and 1 for predicting
		int[] totalSum = new int[2];
		for (int i = 0; i < K; ++i) {
			if (sortedStudents[i].getY() > 0)
				totalSum[1] += 1;
			else
				totalSum[0] += 1;
		}

		System.out.println("Predicted Nearest number of samples \nwith output 0: " + totalSum[0] + "\nwith output 1:  "
				+ totalSum[1]);

		System.out.println("");

		if (totalSum[0] > totalSum[1])
			System.out.println("0 is assigned for the new Student");
		else if (totalSum[1] > totalSum[0])
			System.out.println("1 is assigned for the new Student");
		else
			System.out.println(" equal number of 0 and 1 are predicted for the new Student");

		System.out.println("");
		System.out.println("Shortest Distances for the Input: ");

		for (int i = 0; i < K; ++i) {
			System.out.print(String.format("%.3f ", sortedStudents[i].getEuclid(inputStudent)) + " ");
		}
		System.out.println();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource().equals(btnScore)) {

			double[] x = new double[4];
			int K = 0;

			double socSkill = Double.parseDouble(txtSoc.getText());
			x[0] = socSkill;
			double algSkill = Double.parseDouble(txtAlg.getText());
			x[1] = algSkill;
			double gpaSkill = Double.parseDouble(txtGPA.getText());
			x[2] = gpaSkill;
			double ageValue = Double.parseDouble(txtAge.getText());
			x[3] = ageValue;
			int KNNVal = Integer.parseInt(txtKNN.getText());
			K = KNNVal;

			txtSoc.setText("");
			txtAge.setText("");
			txtGPA.setText("");
			txtAlg.setText("");
			txtKNN.setText("");

		}

	}

}
