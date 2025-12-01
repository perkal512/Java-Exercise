package taskmanager;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
        TaskRepository repo = new TaskRepository("tasks.json");
//        TaskService service = new TaskService(repo);

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
//                    System.out.print("Please enter task id : "); 
////                    להמיר את הSTRING לINT
//                    int id = Integer.parseInt(sc.nextLine());
                    System.out.print("Please enter task title: ");
                    String title = sc.nextLine();
                    System.out.print("Please enter task description: ");
                    String desc = sc.nextLine();
                   // שולחת ID=0 בהמשך יהיה עידכון ל-ID לפי הבדיקה שיהיה ייחודי
                    repo.add(new Task(0, title, desc, Task.Status.NEW));
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
                        System.out.println("Set Status: ");
//                        לבדוק האם לתת כאן לעדכן את הSTATUS או שזה יקרה במוד העיסקי
//                        existing.setStatus(sc.nextLine());
                        repo.update(existing);
                    } else {
                        System.out.println("Task id doesn't exist");
                    }
                    break;

                case 3:
                //	האם לבדוק כאן אם הID קיים או שאין צורך?
                //האם זה תקין להשתמש במשתנים מלמעלה?
                    System.out.print("ID to delete: ");
                    uid = Integer.parseInt(sc.nextLine());
                    existing = repo.getById(uid);
                    if (existing != null) {
                    	repo.delete(uid);
                    }
                    else {
                        System.out.println("Task id doesn't exist");
                    }
                    break;

                case 4:
                    List list = repo.listAll();
                    if(!list.isEmpty()) {
                    	list.forEach(System.out::println);
                    }
                    else {
                    	System.out.println("There is no tasks in json file");
                    }                                   
                    break;

                case 5:
//					האם יותר יפה לבדוק האם הID קיים ולהדפיס אם לא?                	
                    System.out.print("ID: ");
                    System.out.println(repo.getById(Integer.parseInt(sc.nextLine())));
                    break;

              

             

                case 9:
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}

