package taskmanager;

public class Main {

	public static void main(String[] args) {
		Task t1=new Task("1", "title", "description",Task.Status.NEW);
		System.out.println(t1);
	}

}
