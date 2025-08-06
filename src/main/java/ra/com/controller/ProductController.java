package ra.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.com.dto.request.ProductRequestCreateDTO;
import ra.com.model.Category;
import ra.com.model.Product;
import ra.com.service.CategoryService;
import ra.com.service.ProductService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/productController")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/findAll")
    public String findAllProduct(Model model) {
        List<Product> listProducts = productService.findAll();
        model.addAttribute("listProducts", listProducts);
        return "products";
    }

    @GetMapping("/initCreate")
    public String initCreateProduct(Model model) {
        ProductRequestCreateDTO productDto = new ProductRequestCreateDTO();
        List<Category> listCategories = categoryService.findAll();
        model.addAttribute("productDto", productDto);
        model.addAttribute("listCategories", listCategories);
        return "newProduct";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute(name = "productDto") ProductRequestCreateDTO productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "newProduct";
        }
        boolean result = productService.create(productDto);
        if (result) {
            return "redirect:findAll";
        }
        return "error";
    }
}
