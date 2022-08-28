package com.rajangautam.wallet.services;

import java.util.List;

import com.rajangautam.wallet.entities.Category;
import com.rajangautam.wallet.exceptions.EtException;

public interface ICategoryService {
    Category CreateCategory(Category category) throws EtException;

    Category UpdateCategory(Category category) throws EtException;

    List<Category> GetAllCategories() throws EtException;

    Category GetSingleCategory(String categoryId) throws EtException;

    boolean DeleteCategory(String categoryId) throws EtException;
}
