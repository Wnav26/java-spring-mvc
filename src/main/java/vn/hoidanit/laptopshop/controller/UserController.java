package vn.hoidanit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @Controller
// public class UserController {
//     @RequestMapping("/")
//     public String getHomePage() {
//         return "hello controller";
//     }

// }

@RestController
public class UserController {
    @GetMapping("/")
    public String getHomePage() {
        return "hello controller";
    }

}
