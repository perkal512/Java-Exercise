package taskmanager;

import java.util.*;
import java.util.stream.Collectors;

public class TaskService {
	
	private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public void markDone(int id) {
        Task task = repository.getById(id);
        if (task != null) {
            task.setStatus(Task.Status.DONE);
            repository.update(task);
        }
    }

    public List<Task> search(String text) {
    	String lower = text.toLowerCase();
        return repository.listAll().stream()
                .filter(t -> t.getTitle().toLowerCase().contains(lower)
                        || t.getDescription().toLowerCase().contains(lower))
                .collect(Collectors.toList());
    }
    

    public List<Task> listSortedByStatus() {
        return repository.listAll().stream()
                .sorted(Comparator.comparing(Task::getStatus))
                .collect(Collectors.toList());
    }

}
