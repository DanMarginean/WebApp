package com.example.backendapp.services;

import com.example.backendapp.config.ModelMapperConfig;
import com.example.backendapp.entities.Item;
import com.example.backendapp.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ModelMapperConfig modelMapper;

    public ItemService(
            ItemRepository itemRepository,
            ModelMapperConfig modelMapper
    ) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items;
    }

    public Item getItemById(UUID uuid){

        return itemRepository.findById(uuid).get();
    }

    public void deleteAllItems() {
        itemRepository.deleteAll();
    }

    public void deleteItemById(UUID id) {
        itemRepository.deleteById(id);
    }
//    public Item updateItem(Item item){
//        List<Item> items = itemRepository.findAll();
//        items.stream().forEach(itemfind ->
//                itemfind.equals(item));
//    }
}
