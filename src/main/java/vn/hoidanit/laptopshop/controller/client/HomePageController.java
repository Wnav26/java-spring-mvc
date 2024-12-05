package vn.hoidanit.laptopshop.controller.client;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.User.UpdateGroup;
import vn.hoidanit.laptopshop.domain.dto.RegisterDTO;
import vn.hoidanit.laptopshop.service.OrderService;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomePageController {
    private final ProductService productService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final OrderService orderService;
    private final UploadService uploadService;

    public HomePageController(
            ProductService productService,
            UserService userService,
            PasswordEncoder passwordEncoder,
            OrderService orderService, UploadService uploadService) {
        this.productService = productService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.orderService = orderService;
        this.uploadService = uploadService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Product> products = this.productService.fetchProducts();
        model.addAttribute("products", products);
        return "client/homepage/show";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("registerUser") @Valid RegisterDTO registerDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "client/auth/register";
        }
        User user = this.userService.registerDTOtoUser(registerDTO);
        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName("USER"));
        this.userService.handleSaveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "client/auth/login";
    }

    @GetMapping("/access-deny")
    public String getDenyPage(Model model) {
        return "client/auth/deny";
    }

    @GetMapping("/order-history")
    public String getOrderHistoryPage(Model model, HttpServletRequest request) {
        User currentUser = new User();// null
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        currentUser.setId(id);

        List<Order> orders = this.orderService.fetchOrderByUser(currentUser);
        model.addAttribute("orders", orders);

        return "client/cart/order-history";
    }

    @GetMapping("/user-detail")
    public String getUserDetailPage(Model model, HttpServletRequest request) {
        User currentUser = new User();// null
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        currentUser.setId(id);
        User user = this.userService.getUserById(currentUser.getId());
        model.addAttribute("user", user);

        return "client/homepage/userdetail";
    }

    @GetMapping("/user-detail/update-infor")
    public String getUpdateInforPage(Model model, HttpServletRequest request) {
        User currentUser = new User();// null
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        currentUser.setId(id);
        User user = this.userService.getUserById(currentUser.getId());
        model.addAttribute("user", user);
        return "client/homepage/updateuser";
    }

    @PostMapping("/user-detail/update-infor")
    public String postUpdateInfor(@ModelAttribute("newUser") @Validated(UpdateGroup.class) User wnav,
            BindingResult newBindingResult,
            @RequestParam("WNAVFile") MultipartFile file, HttpServletRequest request) {
        List<FieldError> errors = newBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(">>>>>" + error.getField() + " - " + error.getDefaultMessage());
        }
        if (newBindingResult.hasErrors()) {
            return "/user-detail/update-infor";
        }
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        User currentUser = this.userService.getUserById(id);

        if (currentUser != null) {
            // Cập nhật avatar nếu người dùng tải lên file
            if (!file.isEmpty()) {
                String img = this.uploadService.handleSaveUploadfile(file, "avatar");
                currentUser.setAvatar(img);
                session.setAttribute("avatar", img);
            }

            // Cập nhật các thông tin cá nhân khác
            currentUser.setAddress(wnav.getAddress());
            currentUser.setFullName(wnav.getFullName());
            currentUser.setPhone(wnav.getPhone());
            session.setAttribute("fullName", wnav.getFullName());
            // Lưu thông tin đã cập nhật
            this.userService.handleSaveUser(currentUser);

        }

        // Điều hướng về trang chi tiết người dùng sau khi cập nhật
        return "redirect:/user-detail";
    }

}
