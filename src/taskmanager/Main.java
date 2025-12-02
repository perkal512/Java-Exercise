package taskmanager;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		TaskRepository repo = new TaskRepository("tasks.json");
		TaskService service = new TaskService(repo);

		while (true) {
			
			System.out.println("\n--- TODO LIST MENU ---");
			System.out.println("1. Add task");
			System.out.println("2. Update task");
			System.out.println("3. Delete task");
			System.out.println("4. List all");
			System.out.println("5. Get by ID");
			System.out.println("6. Mark as DONE");
			System.out.println("7. Search");
			System.out.println("8. Sort by status");
			System.out.println("9. Exit");
			System.out.print("Choose: ");

			int choice = Integer.parseInt(sc.nextLine());
			int uid;
			Task existing;
			switch (choice) {
//			ADD TASK
			case 1:
				System.out.print("Enter task ID: ");
				uid = Integer.parseInt(sc.nextLine());
				existing = repo.getById(uid);
				while (existing != null) {
					System.out.println("ID already exists. Please enter a different ID.");
					uid = Integer.parseInt(sc.nextLine());
					existing = repo.getById(uid);
				}
				System.out.print("Please enter task title: ");
				String title = sc.nextLine();
				System.out.print("Please enter task description: ");
				String desc = sc.nextLine();
				repo.add(new Task(uid, title, desc, Task.Status.NEW));
				break;

//			UPDATE TASK				
			case 2:
				System.out.print("Enter ID to update: ");
				uid = Integer.parseInt(sc.nextLine());
				existing = repo.getById(uid);
				if (existing != null) {
					System.out.print("New title: ");
					existing.setTitle(sc.nextLine());
					System.out.print("New description: ");
					existing.setDescription(sc.nextLine());
					repo.update(existing);
					System.out.println("Task update successfully!");
				} else {
					System.out.println("Task ID not found.");
				}
				break;

//			DELETE TASK				
			case 3:
				System.out.print("Enter ID to delete: ");
				uid = Integer.parseInt(sc.nextLine());
				existing = repo.getById(uid);
				if (existing != null) {
					repo.delete(uid);
				} else {
					System.out.println("Task ID not found.");
				}
				break;

//			GET LIST TASK				
			case 4:
				List<Task> list = repo.listAll();
				if (!list.isEmpty()) {
					list.forEach(System.out::println);
				} else {
					System.out.println("No tasks found.");
				}
				break;

//			GET TASK BY ID				
			case 5:
				System.out.print("Enter ID: ");
				uid = Integer.parseInt(sc.nextLine());
				existing = repo.getById(uid);
				if (existing != null) {
					System.out.println(existing);
				} else {
					System.out.println("Task ID not found.");
				}
				break;

//				MARK DONE TASK				
			case 6:
				System.out.print("Enter ID to mark done: ");
				uid = Integer.parseInt(sc.nextLine());
				existing = repo.getById(uid);
				if (existing != null) {
					service.markDone(uid);
				} else {
					System.out.println("Task ID not found.");
				}
				break;

//			SEARCH TASK BY TEXT
			case 7:
				System.out.print("Search text: ");
				String text = sc.nextLine();
				List<Task> result = service.search(text);
				if (result.isEmpty()) {
					System.out.println("No tasks found.");
				} else {
					result.forEach(System.out::println);
				}
				break;

//			SORT TASKS BY STATUS
			case 8:
				List<Task> sorted = service.listSortedByStatus();
				if (sorted.isEmpty()) {
					System.out.println("No tasks found.");
				}
				sorted.forEach(System.out::println);
				break;

			case 9:
				System.out.println("Exiting application. Goodby!");
				sc.close();
				return;

			default:
				System.out.println("Invalid choice");
			}
		}
		
	}
}
