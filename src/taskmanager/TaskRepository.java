package taskmanager;

import java.util.*;
import java.io.*;

import taskmanager.Task.Status;

public class TaskRepository {
	// ConcurrentHashMap allows safe concurrent access for reads and writes
	private Map<Integer, Task> tasks = new HashMap<>();
	private final File file;
	private int maxId = 0; // automatic unique ID

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
		return new ArrayList<>(tasks.values());
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

			List<String> objects = new ArrayList<>();
			int brace = 0;
			int start = -1;
			for (int i = 0; i < json.length(); i++) {
				char c = json.charAt(i);
				if (c == '{') {
					if (brace == 0)
						start = i;
					brace++;
				} else if (c == '}') {
					brace--;
					if (brace == 0 && start != -1) {
						objects.add(json.substring(start, i + 1));
						start = -1;
					}
				}
			}
			for (String objJson : objects) {
				Task task = fromJsonTask(objJson);
				int id = task.getId();
				if (id > 0) {
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

	private Task fromJsonTask(String json) {
		json = json.trim();
		if (json.startsWith("{"))
			json = json.substring(1);
		if (json.endsWith("}"))
			json = json.substring(0, json.length() - 1);

		int id = -1;
		String title = "", description = "";
		Status status = Status.NEW;

		String[] parts = json.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
		for (String part : parts) {
			String[] kv = part.split(":", 2);
			if (kv.length < 2)
				continue;
			String key = kv[0].replace("\"", "").trim();
			String value = kv[1].trim();
			if (value.startsWith("\"") && value.endsWith("\"") && value.length() >= 2) {
				value = value.substring(1, value.length() - 1);
			}

			switch (key) {
			case "id":
				id = Integer.parseInt(value);
				break;
			case "title":
				title = value;
				break;
			case "description":
				description = value;
				break;
			case "status":
				status = Status.valueOf(value);
				break;
			}
		}
		return new Task(id, title, description, status);
	}
}