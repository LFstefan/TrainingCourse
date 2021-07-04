public class Singleton{

	private class Student{
		String name;
		int age;
		private Student(){}
	}

	System.out.println("method 1");
	public static Student student = new Student();
	public Student getStudentBean(){
		return student;
	}

	System.out.println("method 2");
	public Student student;
	public Student getStudentBean(){
		if (student == null) {
			student = new Student();
		}
		return student;
	}

	System.out.println("method 3");
	public Student student;
	public Student synchronized getStudentBean(){
		if (student == null) {
			student = new Student();
		}
		return student;
	}

	System.out.println("method 4");
	public Student student;
	public Student getStudentBean(){
		if (student == null) {
			synchronized(Singleton.class)
			if (student == null) {
				student = new Student();
			}	
		}
		return student;
	}

	System.out.println("method 5");
	enum Student{
		INSTANCE;

		String name;
		int age;

		Student(){}
	}

}