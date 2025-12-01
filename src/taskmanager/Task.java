package taskmanager;

public class Task {
	
	public enum Status {
		NEW, IN_PROGRESS, DONE
	}
	
	private int id;
	private String title;
	private String description;
	private Status status;
	
	public Task(int id,String title, String description, Status status) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
	}
	
	public Task(String json) {
		json = json.replace("{", "").replace("}", "");
		String[] parts = json.split(",");

		int id = -1;
		String title = "", description = "";
		Status status = Status.NEW;

		for (String part : parts) {
			String[] kv = part.split(":");
			String key = kv[0].replace("\"", "").trim();
			String value = kv[1].replace("\"", "").trim();

			switch (key) {
			case "id":
				id = Integer.parseInt(value);
				this.id=id;
				break;
			case "title":
				title = value;
				this.title=title;
				break;
			case "description":
				description = value;
				this.description=description;
				break;
			case "status":
				this.status=status;
				status = Status.valueOf(value);
				break;
			}
		}

	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", status=" + status + "]";
	}
	
	// convert task object to json string
	public String toJsonString() {
		return String.format("{\"id\":%d,\"title\":\"%s\",\"description\":\"%s\",\"status\":\"%s\"}", id, title,
				description, status);
	}

}
