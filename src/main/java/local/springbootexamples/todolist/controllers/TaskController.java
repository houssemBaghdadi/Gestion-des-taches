package local.springbootexamples.todolist.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import local.springbootexamples.todolist.entities.Task;
import local.springbootexamples.todolist.repositories.TaskRepository;
import local.springbootexamples.todolist.services.TaskService;

@Controller
public class TaskController {
	@Autowired
	TaskRepository TaskRepository;
	List<Task> TaskList;
	Task Task;
	@Autowired
    TaskService TaskService;

	@RequestMapping("/tasks/viewAll")
	public String ViewAllTasks(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(3);
		Page<Task> TaskPage = TaskService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
		model.addAttribute("TaskPage", TaskPage);

		int totalPages = TaskPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		return "main";
	}

	@RequestMapping("/tasks/update/{taskid}")
	public ModelAndView showTaskUpdateForm(@PathVariable Long taskid) {
		ModelAndView mav = new ModelAndView("edit");
		Task = TaskRepository.findByTaskid(taskid);
		mav.addObject("Task", Task);
		return mav;
	}

	@RequestMapping("/tasks/delete/{taskid}")
	public String deleteTask(@ModelAttribute("Task") Task Task, @PathVariable(name = "taskid") Long taskid, RedirectAttributes redirectAttributes) {
		Task.taskid = taskid;
		TaskRepository.delete(Task);	
		redirectAttributes.addFlashAttribute("message2", "Tâche supprimée correctement");
		redirectAttributes.addFlashAttribute("alertClass2", "alert-success");
		return "redirect:/tasks/viewAll?size=3&page=1#";
	}

	@RequestMapping("/tasks/add")
	public String showTaskAddForm(Model model) {
		Task NewTask = new Task();
	    model.addAttribute("Task", NewTask);
		return "add";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/tasks/update/{taskid}")
	public String updateTask(@ModelAttribute("Task") Task Task, @PathVariable(name = "taskid") Long taskid, RedirectAttributes redirectAttributes) {
		Task.taskid = taskid;
		TaskRepository.save(Task);		
		redirectAttributes.addFlashAttribute("message", "Modification effectuée avec succés");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/tasks/viewAll?size=3&page=1#";

		
	}
	@RequestMapping(method = RequestMethod.POST, value = "/tasks/add")
	public String addTask(@ModelAttribute("Task") Task Task, RedirectAttributes redirectAttributes) {
		TaskRepository.save(Task);	
		redirectAttributes.addFlashAttribute("message1", "Tâche ajoutée avec succés");
		redirectAttributes.addFlashAttribute("alertClass1", "alert-success");
		return "redirect:/tasks/viewAll?size=3&page=1#";

	}
}
