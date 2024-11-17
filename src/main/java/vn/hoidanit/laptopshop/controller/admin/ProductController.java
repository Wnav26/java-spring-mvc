package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Product;

import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;

@Controller
public class ProductController {
    private final ProductService productService;
    private final UploadService uploadService;

    public ProductController(ProductService productService, UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }

    @GetMapping("/admin/product")
    public String getProductPage(Model model) {
        List<Product> prs = this.productService.fetchProducts();
        model.addAttribute("products", prs);
        return "admin/product/show";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String createProductPage(Model model, @ModelAttribute("newProduct") @Valid Product pr,
            BindingResult newBindingResult,
            @RequestParam("WNAVFile") MultipartFile file) {
        // validate
        if (newBindingResult.hasErrors()) {
            return "admin/product/create";
        }
        String image = this.uploadService.handleSaveUploadfile(file, "product");
        pr.setImage(image);
        this.productService.createProduct(pr);
        return "redirect:/admin/product";
    }

    @RequestMapping("/admin/product/{id}")
    public String getProductDetailPage(Model model, @PathVariable long id) {
        Product products = this.productService.getProductById(id).get();
        model.addAttribute("id", id);
        model.addAttribute("product", products);
        return "admin/product/detail";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String getDeleteProductPage(Model model, @PathVariable long id) {
        model.addAttribute("newProduct", new Product());
        model.addAttribute("id", id);
        return "admin/product/delete";
    }

    @PostMapping(value = "/admin/product/delete")
    public String deleteUpdateProduct(Model model, @ModelAttribute("newProduct") Product pr) {
        this.productService.deleteProductById(pr.getId());
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/update/{id}")
    public String getUpdateProductPage(Model model, @PathVariable long id) {
        Optional<Product> currentProducts = this.productService.getProductById(id);
        model.addAttribute("newProduct", currentProducts.get());
        return "admin/product/update";
    }

    @PostMapping(value = "/admin/product/update")
    public String handleUpdateProduct(@ModelAttribute("newProduct") @Valid Product pr,
            BindingResult newBindingResult,
            @RequestParam("WNAVFile") MultipartFile file) {
        if (newBindingResult.hasErrors()) {
            return "admin/user/update";
        }
        Product currentProducts = this.productService.getProductById(pr.getId()).get();

        if (currentProducts != null) {
            if (!file.isEmpty()) {
                String img = this.uploadService.handleSaveUploadfile(file, "product");
                currentProducts.setImage(img);
            }
            currentProducts.setName(pr.getName());
            currentProducts.setPrice(pr.getPrice());
            currentProducts.setDetailDesc(pr.getDetailDesc());
            currentProducts.setShortDesc(pr.getShortDesc());
            currentProducts.setQuantity(pr.getQuantity());
            currentProducts.setFactory(pr.getFactory());
            currentProducts.setTarget(pr.getTarget());

            this.productService.createProduct(currentProducts);
        }
        return "redirect:/admin/product";
    }
}
