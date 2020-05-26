package local.springbootexamples.todolist.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import local.springbootexamples.todolist.entities.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
	
	List<Task> findAll();
	Task       findByTaskid(Long taskid);


}
