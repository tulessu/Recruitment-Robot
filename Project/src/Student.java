class Student {

	private double social;
	private double gpa;
	private double age;
	private double algorithm;
	private double y;
	private int index; // to save the default order of the student

	public Student(double social, double gpa, double age, double algorithm, double y, int index) {
		this.social = social;
		this.gpa = gpa;
		this.age = age;
		this.algorithm = algorithm;
		this.y = y;
		this.index = index;
	}

	@Override
	public String toString() {
		return String.format(
				this.social + " " + this.gpa + " " + this.age + " " + this.algorithm + " " + this.y + " " + this.index);
	}

	public double getEuclid(Student student) {
		double sum = Math.pow(this.social - student.social, 2.0) + Math.pow(this.gpa - student.gpa, 2.0)
				+ Math.pow(this.age - student.age, 2.0) + Math.pow(this.algorithm - student.algorithm, 2.0);

		return Math.pow(sum, 0.5);
	}

	public int getIndex() {
		return this.index;
	}

	public double getY() {
		return this.y;
	}

	
}