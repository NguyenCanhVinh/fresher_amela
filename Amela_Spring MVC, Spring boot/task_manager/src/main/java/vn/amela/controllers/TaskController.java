package vn.amela.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.amela.domail.Task;
import vn.amela.service.TaskService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    /*@GetMapping("/")
    public String list(ModelMap model){

        List<Task> list = taskService.findAll();
        model.addAttribute("tasks", list);
        return "list";
    }*/

    @RequestMapping("list/paginated")
    public String search(ModelMap model, @RequestParam(name = "title", required = false) String title,
                         @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("title"));
        Page<Task> resultPage = null;
        List<Task> list = null;

        if (StringUtils.hasText(title)) {// Kiem tra gia tri cua name truyen ve co noi dung hay khong
            resultPage = taskService.findByTitleContaining(title, pageable);
            model.addAttribute("title", title);
        } else {
            resultPage = taskService.findAll(pageable);
        }

        int totalPages = resultPage.getTotalPages();
        if (totalPages > 0) {
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPages);

            if (totalPages > 5) {
                if (end == totalPages)
                    start = end - 5;
                else if (start == 1)
                    end = start + 5;
            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("TaskPage", resultPage);
        return "list";
    }


}
