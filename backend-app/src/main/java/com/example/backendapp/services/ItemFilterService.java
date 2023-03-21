package com.example.backendapp.services;

import com.example.backendapp.DTOs.ItemFilterDTO;
import com.example.backendapp.entities.Item;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
//import java.util.function.Predicate;
import javax.persistence.criteria.Predicate;
@Component
public class ItemFilterService {

    public Specification<Item> getFilters(ItemFilterDTO itemFilterDTO){
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            List<Predicate> searchPredicates = new ArrayList<>();

            if((itemFilterDTO.getBrand() != null) && !(itemFilterDTO.getBrand().isEmpty())){
                predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("brand")),itemFilterDTO.getBrand().toLowerCase()));
            }

            if((itemFilterDTO.getName() != null) && !(itemFilterDTO.getName().isEmpty())){
                predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("name")),itemFilterDTO.getName().toLowerCase()));
            }

            if((itemFilterDTO.getPrice() != 0)){
                predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("price")),itemFilterDTO.getPrice()));
            }

            if((itemFilterDTO.getSearchString() != null) && !(itemFilterDTO.getSearchString().isEmpty())){
//                searchPredicates.add(
//                        criteriaBuilder.like(criteriaBuilder.lower(root.get("general_category")),itemFilterDTO.getSearchString().toLowerCase()+"%")
//                );
                searchPredicates.add(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("category")),itemFilterDTO.getSearchString().toLowerCase()+"%")
                );
                searchPredicates.add(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("serialNumber")),itemFilterDTO.getSearchString().toLowerCase()+"%")
                );
            }

            Predicate searchPredicate = criteriaBuilder.or(searchPredicates.toArray(searchPredicates.toArray(new Predicate[0])));
            Predicate filterPredicate = criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));

            if(searchPredicates.isEmpty()) return filterPredicate;
            return criteriaBuilder.and(filterPredicate,searchPredicate);
        });

    }
}
