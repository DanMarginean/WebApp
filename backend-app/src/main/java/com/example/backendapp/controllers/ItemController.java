package com.example.backendapp.controllers;

import com.example.backendapp.DTOs.ItemAddDTO;
import com.example.backendapp.DTOs.ItemCardDTO;
import com.example.backendapp.DTOs.ItemDetailsDTO;
import com.example.backendapp.DTOs.ItemFilterDTO;
import com.example.backendapp.entities.Item;
import com.example.backendapp.services.ItemService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class ItemController {
    private final ItemService itemService;


    public ItemController(ItemService itemService) {
        this.itemService = itemService;

    }

    @GetMapping()
    public List<Item> getAll() {
        return itemService.getAllItems();
    }

    @GetMapping("/items/{id}")

    public ItemAddDTO getItemById(@PathVariable UUID id) {
        return itemService.getItemById(id);
    }

    @GetMapping("/cards")
    public List<ItemCardDTO> getAllItemCards() throws IOException {
        return itemService.getAllItemCards();
    }

    @GetMapping("/itemDetails/{id}")
    public ItemDetailsDTO getItemDetails(@PathVariable(name = "id") UUID id) throws IOException {
        return this.itemService.getItemDetails(id);
    }

    @PostMapping("/items")
    public Item createItem(@RequestPart("item") ItemAddDTO item, @RequestPart("image") MultipartFile file) throws IOException {

        return itemService.saveItem(item, file);
    }


    @DeleteMapping("/delete")
    public void deleteAllItems() {
        itemService.deleteAllItems();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteItemById(@PathVariable UUID id) {
        itemService.deleteItemById(id);
    }

    @PutMapping("/update/{id}")
    public void updateItemById(@PathVariable UUID id, @RequestBody ItemAddDTO item) {
        this.itemService.updateItem(id, item);
    }

    @PostMapping("/search")
    public List<ItemCardDTO> getAllBySpecs(@RequestBody ItemFilterDTO itemFilterDTO) {
        return itemService.findItemsBySpecs(itemFilterDTO);
    }
}







