package local.springbootexamples.todolist.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import local.springbootexamples.todolist.entities.Task;
import local.springbootexamples.todolist.repositories.TaskRepository;

@Service
public class TaskService {

	@Autowired
	TaskRepository TaskRepository;	
	
	List<Task> TaskList;
	
	public TaskService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Page<Task> findPaginated(Pageable pageable) {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Task> list;
		TaskList = TaskRepository.findAll();
		if (TaskList.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, TaskList.size());
			list = TaskList.subList(startItem, toIndex);
		}

		Page<Task> TaskPage = new PageImpl<Task>(list, PageRequest.of(currentPage, pageSize), TaskList.size());

		return TaskPage;
	}
}