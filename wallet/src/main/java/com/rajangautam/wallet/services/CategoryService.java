package com.rajangautam.wallet.services;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajangautam.wallet.dao.ICategoryDao;
import com.rajangautam.wallet.entities.Category;
import com.rajangautam.wallet.exceptions.EtException;

@Service
@Transactional
@DynamicUpdate
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryDao categoryDao;

    @Override
    public Category CreateCategory(Category category) throws EtException {
        try {
            var createdCategory = categoryDao.save(category);
            return createdCategory;
        } catch (Exception e) {
            throw new EtException("Error: " + e.getMessage());
        }
    }

    @Override
    public Category UpdateCategory(Category category) throws EtException {
        try {
            var singleCategory = categoryDao.findByCategoryId(category.getCategoryId().toString());

            singleCategory.setTitle(category.getTitle());
            singleCategory.setDescription(category.getDescription());

            var updatedCategory = categoryDao.save(singleCategory);
            return updatedCategory;
        } catch (Exception e) {
            throw new EtException("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Category> GetAllCategories() throws EtException {
        try {
            var allCategories = categoryDao.findAll();
            return allCategories;
        } catch (Exception e) {
            throw new EtException("Error: " + e.getMessage());
        }
    }

    @Override
    public Category GetSingleCategory(String categoryId) throws EtException {
        try {
            var singleCategory = categoryDao.findById(categoryId).orElse(null);
            return singleCategory;
        } catch (Exception e) {
            throw new EtException("Error: " + e.getMessage());
        }
    }

    @Override
    public boolean DeleteCategory(String categoryId) throws EtException {
        try {
            categoryDao.deleteById(categoryId);
            return true;
        } catch (Exception e) {
            throw new EtException("Error: " + e.getMessage());
        }
    }
}
