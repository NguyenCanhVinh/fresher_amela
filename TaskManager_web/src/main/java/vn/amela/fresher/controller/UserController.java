package vn.amela.fresher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import vn.amela.fresher.entity.User;
import vn.amela.fresher.repository.UserRepository;

import javax.validation.Valid;


@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "admin/signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(@Valid @ModelAttribute("user") User user,  BindingResult result) {
        if (result.hasErrors()){
            return "admin/signup_form";
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);

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
