package ra.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.com.model.Category;
import ra.com.service.CategoryService;

import java.util.List;

@Controller
@RequestMapping("/categoryController")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/findAll")
    public String findAllCategory(Model model) {
        List<Category> listCategory = categoryService.findAll();
        model.addAttribute("listCategory", listCategory);
        return "category";
    }

    @GetMapping("/initCreate")
    public String initCreateCategory(Model model) {
        Category catalog = new Category();
        model.addAttribute("catalog", catalog);
        return "newCategory";
    }

    @PostMapping("/create")
    public String createCategory(Category catalog) {
        boolean result = categoryService.create(catalog);
        if (result) {
            return "redirect:findAll";
        } else {
            return "error";
        }
    }

    @GetMapping("/initUpdate")
    public String initUpdateCategory(Model model, int catalogId) {
        Category catalog = categoryService.findById(catalogId);
        model.addAttribute("catalog", catalog);
        return "updateCategory";
    }

    @PostMapping("/update")
    public String updateCategory(Category catalog) {
        boolean result = categoryService.update(catalog);
        if (result) {
            return "redirect:findAll";
        } else {
            return "error";
        }
    }

    @GetMapping("/delete")
    public String deleteCategory(int catalogId) {
        boolean result = categoryService.delete(catalogId);
        if (result) {
            return "redirect:findAll";
        } else {
            return "error";
        }
    }
}
