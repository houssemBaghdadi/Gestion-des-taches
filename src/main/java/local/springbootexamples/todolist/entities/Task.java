package local.springbootexamples.todolist.entities;

import javax.persistence.*;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TASK_SEQUENCE")
	@SequenceGenerator(name="TASK_SEQUENCE", sequenceName="TASK_SEQUENCE", allocationSize=1)
	@Column(name = "TASKID")
	public Long   taskid;
	@Column(name = "NAME")
	public String name;
	@Column(name = "DESCRIPTION")
	public String description;
	@Column(name = "COMPLETION")
	public Long   completion;
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Task(Long taskid, String name, String description, Long completion) {
		super();
		this.taskid = taskid;
		this.name = name;
		this.description = description;
		this.completion = completion;
	}
	public Long getTaskId() {
		return taskid;
	}
	public void setTaskId(Long taskid) {
		this.taskid = taskid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getCompletion() {
		return completion;
	}
	public void setCompletion(Long completion) {
		this.completion = completion;
	}
	
	
	
}
