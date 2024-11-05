package vn.hoidanit.laptopshop.controller.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class UserController {
    private final UserService userService;
    private final UploadService uploadService;

    public UserController(UserService userService, UploadService uploadService, ServletContext servletContext) {
        this.userService = userService;
        this.uploadService = uploadService;

    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> arrUsers = this.userService.getAllUsersByEmail("nvan0330@gmail.com");
        System.out.println(arrUsers);

        model.addAttribute("eric", "test");
        model.addAttribute("hoidanit", "from controller with model");
        return "hello";
    }

    @GetMapping("/admin/user/create") // GET
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping("/admin/user/create")
    public String createUserPage(Model model, @ModelAttribute("newUser") User wnav,
            @RequestParam("WNAVFile") MultipartFile file) {

        String avatar = this.uploadService.handleSaveUploadfile(file, "avatar");
        // this.userService.handleSaveUser(wnav);
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users1", users);
        return "admin/user/show";
    }

    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User users = this.userService.getUserById(id);
        model.addAttribute("id", id);
        model.addAttribute("user", users);
        return "admin/user/detail";
    }

    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        User currentUsers = this.userService.getUserById(id);
        model.addAttribute("newUser", currentUsers);
        return "admin/user/update";
    }

    @PostMapping(value = "/admin/user/update")
    public String postUpdateUser(Model model, @ModelAttribute("newUser") User wnav) {
        User currentUsers = this.userService.getUserById(wnav.getId());
        if (currentUsers != null) {
            currentUsers.setAddress(wnav.getAddress());
            currentUsers.setFullName(wnav.getFullName());
            currentUsers.setPhone(wnav.getPhone());
            this.userService.handleSaveUser(currentUsers);
        }
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id) {
        // User user = new User();
        // user.setId(id);
        model.addAttribute("newUser", new User());
        model.addAttribute("id", id);
        return "admin/user/delete";
    }

    @PostMapping(value = "/admin/user/delete")
    public String deleteUpdateUser(Model model, @ModelAttribute("newUser") User wnav) {
        this.userService.deleteUserById(wnav.getId());
        return "redirect:/admin/user";
    }
}
