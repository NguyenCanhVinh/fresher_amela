package vn.amela.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Controller
public class dataController {

    @InitBinder
    public  void initBinder(WebDataBinder binder){

        System.out.println("A binder for object: "+ binder.getObjectName());
    }
}
