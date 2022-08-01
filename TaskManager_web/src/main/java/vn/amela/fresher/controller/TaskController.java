package vn.amela.fresher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.amela.fresher.entity.Task;
import vn.amela.fresher.entity.TaskStatus;
import vn.amela.fresher.repository.TaskRepository;
import vn.amela.fresher.repository.TaskStatusRepository;
import vn.amela.fresher.service.TaskService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskStatusRepository taskStatusRepository;

    @RequestMapping("")
    public ModelAndView list(ModelMap model){
        Pageable pageable = PageRequest.of(0, 5);
        Page<Task> list = taskRepository.findAll(pageable);

        model.addAttribute("tasks", list);
        List<TaskStatus> listC = taskStatusRepository.findAll();
        model.addAttribute("taskStatus", listC);
        // set active front-end
        model.addAttribute("menuP", "menu");
        return new ModelAndView("admin/list", model);


    }

    @RequestMapping("/page")
    public ModelAndView page(ModelMap model, @RequestParam("page") Optional<Integer> page,
                             @RequestParam(value = "title", required = false) String title, @RequestParam("size") Optional<Integer> size,
                              @RequestParam("brand") Optional<Long> brandPage) {

        int currentPage = page.orElse(0);
        int pageSize = size.orElse(5);

        if (title.equalsIgnoreCase("null")) {
            title = "";
        }
        Long brand = brandPage.orElse(0L);

        Pageable pageable = PageRequest.of(currentPage, pageSize);


        Page<Task> list = null;

        if (brand == 0) {
            list = taskRepository.findByTitleContaining(title, pageable);
        } else {
            list = taskRepository.findAllTaskByTaskstatusId(brand, pageable);
        }

        model.addAttribute("brand", brand);
        model.addAttribute("tasks", list);
        model.addAttribute("title", title);

        List<TaskStatus> listC = taskStatusRepository.findAll();
        model.addAttribute("taskStatus", listC);

        return new ModelAndView("admin/list", model);
    }

    @RequestMapping("/search")
    public ModelAndView search(ModelMap model, @RequestParam("title") String title,
                               @RequestParam("size") Optional<Integer> size) {
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(0, pageSize);

        Page<Task> list = taskRepository.findByTitleContaining(title, pageable);

        model.addAttribute("title", title);
        model.addAttribute("tasks", list);

        List<TaskStatus> listC = taskStatusRepository.findAll();
        model.addAttribute("taskStatus", listC);

        return new ModelAndView("admin/list", model);
    }

    @GetMapping("/delete/{task_id}")
    public ModelAndView delete(@PathVariable("task_id") Long task_id, ModelMap model) {

        Optional<Task> opt=taskRepository.findById(task_id);
        if (opt.isPresent()){

            taskRepository.delete(opt.get());
            model.addAttribute("message", "Xoá thành công!");
        }
        else {
            model.addAttribute("error", "Không thể task này!");
        }
        return new ModelAndView("forward:/task", model);
    }
}
