package com.example.backendapp.controllers;

import com.example.backendapp.entities.Item;
import com.example.backendapp.services.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService=itemService;
    }

@GetMapping()
    public List<Item> getAllItems(){return itemService.getAllItems();}

@GetMapping("/items/{id}")
public Item getItemById(@PathVariable UUID id){return itemService.getItemById(id); }

@PostMapping("/items")
    public Item createItem(@RequestBody Item item){
        return itemService.saveItem(item);
    }


    @DeleteMapping("/delete")
    public void deleteAllItems() {
        itemService.deleteAllItems();
    }
@DeleteMapping("/delete/{id}")
public void deleteItemById(@PathVariable UUID id){itemService.deleteItemById(id);}
}





