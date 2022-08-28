package com.rajangautam.wallet.controllers;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.rajangautam.wallet.entities.Category;
import com.rajangautam.wallet.entities.User;
import com.rajangautam.wallet.responses.Response;
import com.rajangautam.wallet.services.ICategoryService;

@RestController
@RequestMapping("/api/categories/")
public class CategoryController {
    @Autowired
    private ICategoryService service;

    @PostMapping("create")
    public Response<Category> CreateCategory(@RequestBody Category category, HttpServletRequest request) {
        try {
            category.setCategoryId(UUID.randomUUID().toString());
            category.setUser(new User((String) request.getAttribute("id")));

            var createdCategory = service.CreateCategory(category);
            var success = new Response<Category>(HttpStatus.CREATED, true, "Category Created", createdCategory);
            return success;

        } catch (Exception e) {
            var error = new Response<Category>(HttpStatus.INTERNAL_SERVER_ERROR, false, "Error: " + e.getMessage(),
                    null);
            return error;
        }
    }

    @PutMapping("update/{categoryId}")
    public Response<Category> UpdateCategory(@RequestBody Category category, @PathVariable String categoryId,
            HttpServletRequest request) {
        try {
            category.setCategoryId(categoryId);
            var updatedCategory = service.UpdateCategory(category);
            var success = new Response<Category>(HttpStatus.OK, true, "Category Updated", updatedCategory);
            return success;

        } catch (Exception e) {
            var error = new Response<Category>(HttpStatus.INTERNAL_SERVER_ERROR, false, "Error: " + e.getMessage(),
                    null);
            return error;
        }
    }

    @GetMapping("get-all")
    public Response<List<Category>> GetAllCategory(HttpServletRequest request) {
        try {
            var allCategories = service.GetAllCategories();
            var success = new Response<List<Category>>(HttpStatus.OK, true, "Categories Fetched", allCategories);
            return success;

        } catch (Exception e) {
            var error = new Response<List<Category>>(HttpStatus.INTERNAL_SERVER_ERROR, false,
                    "Error: " + e.getMessage(),
                    null);
            return error;
        }
    }

    @GetMapping("get-one/{categoryId}")
    public Response<Category> GetAllCategory(HttpServletRequest request, @PathVariable String categoryId) {
        try {
            var singleCategory = service.GetSingleCategory(categoryId);
            var success = new Response<Category>(HttpStatus.OK, true, "Category Fetched", singleCategory);
            return success;

        } catch (Exception e) {
            var error = new Response<Category>(HttpStatus.INTERNAL_SERVER_ERROR, false,
                    "Error: " + e.getMessage(),
                    null);
            return error;
        }
    }

    @DeleteMapping("delete-one/{categoryId}")
    public Response<Boolean> DeleteCategory(HttpServletRequest request, @PathVariable String categoryId) {
        try {
            var singleCategory = service.DeleteCategory(categoryId);
            var success = new Response<Boolean>(HttpStatus.OK, true, "Category Deleted", singleCategory);
            return success;

        } catch (Exception e) {
            var error = new Response<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR, false,
                    "Error: " + e.getMessage(),
                    null);
            return error;
        }
    }
}
