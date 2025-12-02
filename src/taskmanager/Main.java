package taskmanager;

import java.util.*;

import taskmanager.Task.Status;

public class Main {
 	private static final Scanner sc = new Scanner(System.in);
 	private static final TaskRepository repo = new TaskRepository("tasks.json");
	public static void main(String[] args) {
		
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

			switch (choice) {
			case 1:
				addTask();
//				System.out.print("ID to update: ");
//				int uid = Integer.parseInt(sc.nextLine());
//				Task existing = repo.getById(uid);
//				if (existing != null) {
//					System.out.print("ID existing, please enter another ID: ");
//				} else {
//					System.out.print("Please enter task title: ");
//					String title = sc.nextLine();
//					System.out.print("Please enter task description: ");
//					String desc = sc.nextLine();
//					repo.add(new Task(0, title, desc, Task.Status.NEW));
//				}
				break;

			case 2:
				System.out.print("ID to update: ");
				int uid = Integer.parseInt(sc.nextLine());
				Task existing = repo.getById(uid);
				if (existing != null) {
					System.out.print("New title: ");
					existing.setTitle(sc.nextLine());
					System.out.print("New description: ");
					existing.setDescription(sc.nextLine());
//                        System.out.println("Set Status: ");
					repo.update(existing);
				} else {
					System.out.println("Task id doesn't exist");
				}
				break;

			case 3:
				System.out.print("ID to delete: ");
				uid = Integer.parseInt(sc.nextLine());
				existing = repo.getById(uid);
				if (existing != null) {
					repo.delete(uid);
				} else {
					System.out.println("Task id doesn't exist");
				}
				break;

			case 4:
				List<Task> list = repo.listAll();
				if (!list.isEmpty()) {
					list.forEach(System.out::println);
				} else {
					System.out.println("There is no tasks in json file");
				}
				break;

			case 5:
				System.out.print("ID: ");
				System.out.println(repo.getById(Integer.parseInt(sc.nextLine())));
				break;
			case 6:
				System.out.print("ID to mark done: ");
				uid = Integer.parseInt(sc.nextLine());
				existing = repo.getById(uid);
				if (existing != null) {
					service.markDone(uid);
				} else {
					System.out.println("ID dosn't exist");
				}
				break;

			case 7:
//				לבדוק האם יותר יפה שאם לא מצא TEXT שיציד הודעה למשתמש לא נמצא
				System.out.print("Search text: ");
				service.search(sc.nextLine()).forEach(System.out::println);
				break;

			case 8:
				service.listSortedByStatus().forEach(System.out::println);
				break;

			case 9:
				return;

			default:
				System.out.println("Invalid choice");
			}
		}
	}

	private static void addTask() {
		int id;

		// Step 1: Ask for ID until it's NOT already in use
		while (true) {
			System.out.print("Enter task ID: ");
			id = Integer.parseInt(sc.nextLine());
//			func taskExists
			if (repo.getById(id) != null) {
				System.out.println("ID already exists. Please enter a different ID.");
			} else {
				break; // ID is free → continue
			}
		}

		// Step 2: Collect title & description
		System.out.print("Enter task title: ");
		String title = sc.nextLine();

		System.out.print("Enter task description: ");
		String description = sc.nextLine();

		// Step 3: Create and save the task
		Task newTask = new Task(id, title, description, Status.NEW);
		repo.add(newTask);

		System.out.println("Task added successfully!");
	}
}
