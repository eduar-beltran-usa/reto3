package com.usa.repository;

import com.usa.entities.Category;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer>{

     @Override
    List<Category> findAll();
}
