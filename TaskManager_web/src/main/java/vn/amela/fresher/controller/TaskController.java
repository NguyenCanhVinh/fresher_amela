package vn.amela.fresher.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.amela.fresher.entity.Task;
import vn.amela.fresher.entity.TaskStatus;
import vn.amela.fresher.model.TaskDto;
import vn.amela.fresher.model.TaskStatusDto;
import vn.amela.fresher.repository.TaskRepository;
import vn.amela.fresher.repository.TaskStatusRepository;
import vn.amela.fresher.service.TaskService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskStatusRepository taskStatusRepository;

    @RequestMapping("")
    public ModelAndView list(ModelMap model) {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Task> list = taskRepository.findAll(pageable);

        model.addAttribute("tasks", list);
        List<TaskStatus> listC = taskStatusRepository.findAll();
        model.addAttribute("taskStatus", listC);
        // set active front-end
        model.addAttribute("menuP", "menu");
        return new ModelAndView("admin/list", model);


    }

    @GetMapping("/page")
    public ModelAndView page(ModelMap model, @RequestParam("page") Optional<Integer> page,
                             @RequestParam(value = "title", required = false) String title,
                             @RequestParam("size") Optional<Integer> size,
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

        Optional<Task> opt = taskRepository.findById(task_id);
        if (opt.isPresent()) {
            taskRepository.delete(opt.get());
            model.addAttribute("message", "Xoá thành công!");
        } else {
            model.addAttribute("error", "Không thể xóa task này!");
        }
        return new ModelAndView("forward:/task", model);
    }


    /* @PostMapping("/saveOrUpdate")
     public ModelAndView saveOrUpdate(ModelMap model, TaskDto dto){

         Task entity= new Task();
         BeanUtils.copyProperties(dto,entity);
         taskRepository.save(entity);

         List<TaskStatus> listC = taskStatusRepository.findAll();
         model.addAttribute("taskStatus", listC);

         model.addAttribute("message", "Lưu task thanh cong");
         return new ModelAndView("forward:/task", model);

     }*/
    @GetMapping("/add")
    public String add(Task task, Model model) {
        model.addAttribute("task", task);
        List<TaskStatus> taskStatuses = taskStatusRepository.findAll();
        model.addAttribute("taskStatuses", taskStatuses);

        return "admin/addTask";
    }

    @PostMapping("/saveOrUpdate")
    public ModelAndView saveOrUpdate(ModelMap model,@Valid @ModelAttribute("product") TaskDto dto,
                                     BindingResult result) {
        /*model.addAttribute("task", task);*/
        /*List<TaskStatus> taskStatuses = taskStatusRepository.findAll();
        model.addAttribute("taskStatuses", taskStatuses);*/

        if (result.hasErrors()) {
            return new ModelAndView("admin/addTask");
        }

        Task entity= new Task();
        BeanUtils.copyProperties(dto, entity);

        TaskStatus taskStatus= new TaskStatus();
        taskStatus.setId(dto.getTaskStatus_id());
        entity.setTaskStatus(taskStatus);
        taskRepository.save(entity);

        model.addAttribute("message", "task is saved");
        return new ModelAndView("forward:/task", model);
    }

   @GetMapping("/edit/{task_id}")
    public ModelAndView edit(ModelMap model, @PathVariable(name = "task_id") Long task_id) {

        Optional<Task> opt = taskRepository.findById(task_id);
        TaskDto dto = new TaskDto();
        if (opt.isPresent()) {
            Task entity = opt.get();

            BeanUtils.copyProperties(entity, dto);
            List<TaskStatus> taskStatuses = taskStatusRepository.findAll();
            dto.setTaskStatus_id(entity.getTaskStatus().getId());
            dto.setIsEdit(true);
            return new ModelAndView( "admin/addTask", model);
        }

        model.addAttribute("message", "Sửa thành công!");
        return new ModelAndView("forward:/task", model);
    }

    @ModelAttribute("taskStatuses")
    public List<TaskStatusDto> getTaskStatus() {
        return taskStatusRepository.findAll().stream().map(item -> {
            TaskStatusDto dto = new TaskStatusDto();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/viewtask/{task_id}")
    public String viewProduct(@PathVariable long task_id, Model model){

        model.addAttribute("task",taskRepository.findById(task_id).get());
        return "admin/viewTask";
    } //view product Details





}
