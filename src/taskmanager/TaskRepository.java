package taskmanager;

import java.util.*;
import java.io.*;

public class TaskRepository {
	private Map<Integer, Task> tasks = new HashMap<>();
	private final File file;
	private int maxId = 0; // for automatic ID

	public TaskRepository(String filePath) {
		file = new File(filePath);
		if (file.exists()) {
			loadTasks();
		} else {
//			if the file dosn't exist we save empty list.
			saveToFile();
		}
	}

	public void add(Task task) {
		task.setId(++maxId); // unique ID
		tasks.put(task.getId(), task);
		saveToFile();
	}

	public void update(Task task) {
		tasks.put(task.getId(), task);
		saveToFile();
	}

	public void delete(int id) {
		tasks.remove(id);
		saveToFile();
	}

	public Task getById(int id) {
		return tasks.get(id);
	}

	public ArrayList<Task> listAll() {
		return new ArrayList<Task>(tasks.values());
	}

	private void loadTasks() {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null)
				sb.append(line);
			String json = sb.toString().trim();

			if (json.isEmpty() || json.equals("[]"))
				return;

			json = json.substring(1, json.length() - 1);
			String[] taskJsons = json.split("},");

			for (int i = 0; i < taskJsons.length; i++) {
				String t = taskJsons[i];
				if (!t.endsWith("}"))
					t += "}";
				Task task = new Task(t);
				int id = task.getId();
				if (id != -1) {
					tasks.put(id, task);
					if (id > maxId)
						maxId = id;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void saveToFile() {
		try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
			pw.print("[");

			int index = 0;
			for (Task t : tasks.values()) {
				pw.print(t.toJsonString());
				if (index < tasks.size() - 1)
					pw.print(",");
				index++;
			}

			pw.print("]");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}