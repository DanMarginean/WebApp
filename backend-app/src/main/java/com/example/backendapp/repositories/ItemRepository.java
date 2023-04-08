package com.example.backendapp.repositories;

import com.example.backendapp.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID>, JpaSpecificationExecutor {
    public List<Item> findAllByCategory(String category);
    public List<Item> findAllByGeneralCategory(String generalCategory);
}
