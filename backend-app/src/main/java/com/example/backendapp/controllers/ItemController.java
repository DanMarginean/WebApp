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
import java.util.Set;
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

    @GetMapping("/category/{category}")
    List<ItemCardDTO> itemsByCategory(@PathVariable(name = "category") String category) {
        return this.itemService.getItemsByCategory(category);
    }

    @GetMapping("/generalCategory/{generalCategory}")
    List<ItemCardDTO> itemsByGeneralCategory(@PathVariable(name = "generalCategory") String generalCategory) {
        return this.itemService.getItemsByGeneralCategory(generalCategory);
    }

    @GetMapping("/menuGeneralCategory")
    Set<String> getMenuGeneralCategory(){
        return this.itemService.getMenuGeneralCategory();
    }

    @GetMapping("/menuCategory/{generalCategory}")
    Set<String> getMenuCategory(@PathVariable(name = "generalCategory") String generalCategory){
        return this.itemService.getMenuCategory(generalCategory);
    }

    @PostMapping("/items")
    public Item createItem(@RequestPart("item") ItemAddDTO item,@RequestPart("image") MultipartFile file) throws IOException { //@RequestPart("image") MultipartFile file

        return itemService.saveItem(item,file);//change from Item si ItemAddDTO
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
    public void updateItemById(@PathVariable UUID id, @RequestPart("item") ItemAddDTO item,@RequestPart("image") MultipartFile file) throws IOException{
        this.itemService.updateItem(id, item,file);
    }

    @PostMapping("/search")
    public List<ItemCardDTO> getAllBySpecs(@RequestBody ItemFilterDTO itemFilterDTO) {
        return itemService.findItemsBySpecs(itemFilterDTO);
    }

}







