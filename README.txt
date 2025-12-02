Project Overview
----------------

Description:
This is a Java application to manage a Todo List. It implements full CRUD operations on tasks and provides additional functionalities like searching, marking tasks as DONE, and sorting by status. Tasks are persisted in a JSON file (tasks.json).

Note:
The application safely handles invalid task entries in tasks.json.
Empty files or improperly formatted data will not crash the program.


Project Structure
-----------------
src/taskmanager/
    Main.java - console UI menu
    Task.java - Task model (Id, Title, Description, Status enum)
    TaskRepository.java - Handles CRUD operations on tasks.json
    TaskService.java - Implements additional logic: markDone, search, sort

data/
    tasks.json - Stores task data in JSON format

src/design/
    Library.md - Design REST API for library
    OrdersAndPayments.md - Design REST API for Orders + Payments system 

src/algorithms/
	Main.java - console UI 
    IncreasingSubsequences.java - Solution for strictly increasing subarrays 

Requirements
------------
- Java JDK 8 or higher
- Eclipse IDE or any Java IDE
- No external libraries required

Running the Application
-----------------------
1. Open the project in your IDE.
2. Ensure `data/tasks.json` exists; the program will create it if missing.
3. Run the `Main.java` class.
4. Use the console menu to perform task operations:
   - Option 1: Add a task (unique numeric ID required)
   - Option 2: Update a task
   - Option 3: Delete a task
   - Option 4: List all tasks
   - Option 5: Get task by ID
   - Option 6: Mark task as DONE
   - Option 7: Search tasks by text
   - Option 8: Sort tasks by status
   - Option 9: Exit application
5. Invalid numeric input is handled with try-catch to prevent crashes.

Notes
-----
- All code follows clean OOP principles with separation of concerns:
  - TaskRepository handles data persistence
  - TaskService handles business logic
  - Main handles user interaction
- Tasks are stored persistently in `data/tasks.json`.
- The project is organized into packages and folders for clarity and maintainability.

