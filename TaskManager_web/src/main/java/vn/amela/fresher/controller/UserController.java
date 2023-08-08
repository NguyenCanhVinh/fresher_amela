package vn.amela.fresher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.amela.fresher.entity.User;
import vn.amela.fresher.model.UserDto;
import vn.amela.fresher.repository.UserRepository;
import vn.amela.fresher.service.UserService;

import javax.validation.Valid;


@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/register")
    public String showRegistrationForm(@ModelAttribute UserDto userDto, ModelMap model) {
        model.addAttribute("userDto",userDto);;

        return "admin/signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(ModelMap model, @Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result, RedirectAttributes ra) {
        if (userDto.getPassword()!= null && userDto.getRpassword() !=null){
            if (!userDto.getPassword().equals(userDto.getRpassword())){
                result.addError(new FieldError("userDto", "rpassword",
                        "Password chưa chính xác"));
            }
        }
        if (result.hasErrors()){
            return "admin/signup_form";
        }
        ra.addAttribute("message","Sucess, Bạn đăng ký thành công");
        userService.register(userDto);
        return "admin/login";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "admin/login";
    }
}
