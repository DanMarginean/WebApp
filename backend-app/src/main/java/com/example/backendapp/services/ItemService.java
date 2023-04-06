package com.example.backendapp.services;

import com.example.backendapp.DTOs.ItemAddDTO;
import com.example.backendapp.DTOs.ItemCardDTO;
import com.example.backendapp.DTOs.ItemDetailsDTO;
import com.example.backendapp.DTOs.ItemFilterDTO;
import com.example.backendapp.entities.Item;
import com.example.backendapp.repositories.ImageRepository;
import com.example.backendapp.repositories.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemFilterService itemFilterService;
    private final ImageRepository imageRepository;
    private final ImageService imageService;
    public final ModelMapper modelMapper = new ModelMapper();

    private final String PATH = "C:/Users/digid/OneDrive/Desktop/imagePath/";

    @Autowired
    public ItemService(
            ItemRepository itemRepository,
            ItemFilterService itemFilterService,
            ImageService imageService,
            ImageRepository imageRepository
    ) {
        this.itemRepository = itemRepository;
        this.itemFilterService = itemFilterService;
        this.imageService = imageService;
        this.imageRepository = imageRepository;
    }

    public Item saveItem(ItemAddDTO itemAddDTO, MultipartFile file) throws IOException {

        Item item = this.modelMapper.map(itemAddDTO, Item.class);
        item.setDateOfAdd(new Date());
        item.setImage(imageService.uploadImageToFile(file));
        itemRepository.save(item);
        return item;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public ItemAddDTO getItemById(UUID uuid) { //ToDo new DTO ITEM DETAILS DTO WITH PATH

        Item item = itemRepository.findById(uuid).get();
        ItemAddDTO itemAddDTO = this.modelMapper.map(item, ItemAddDTO.class);
        return itemAddDTO;
    }

    public ItemDetailsDTO getItemDetails(UUID id) throws IOException {
        Item item = itemRepository.findById(id).get();
        ItemDetailsDTO itemDetailsDTO = this.modelMapper.map(item, ItemDetailsDTO.class);
        itemDetailsDTO.setBytes(imageService.downloadImageFromFile(item.getImage().getFilePath()));
        return itemDetailsDTO;
    }

    public void deleteAllItems() {
        itemRepository.deleteAll();
    }

    public void deleteItemById(UUID id) {
        itemRepository.deleteById(id);
    }

    public void updateItem(UUID id, ItemAddDTO itemUpdate) {
        Item item = (itemRepository.findById(id).get());
//        Item item = new Item();
//        item.setId(id);
        item.setName(itemUpdate.getName());
        item.setBrand(itemUpdate.getBrand());
        item.setSerialNumber(itemUpdate.getSerialNumber());
        item.setPrice(itemUpdate.getPrice());
        item.setDateOfAdd(item.getDateOfAdd());
        item.setQuantity(itemUpdate.getQuantity());
        item.setPercentSale(itemUpdate.getPercentSale());
        item.setCategory(itemUpdate.getCategory());
        item.setGeneralCategory(itemUpdate.getGeneralCategory());
        item.setDescriere(itemUpdate.getDescriere());
        itemRepository.save(item);

    }

    public List<ItemCardDTO> getAllItemCards() throws IOException {

        List<Item> items = itemRepository.findAll();
        List<ItemCardDTO> itemCardDTOList = new ArrayList<>();
        for (Item item : items) {
            ItemCardDTO itemCardDTO = modelMapper.map(item, ItemCardDTO.class);

            try {
                itemCardDTO.setBytes(imageService.downloadImageFromFile(item.getImage().getFilePath()));

            } catch (Exception e) {

                itemCardDTO.setImage(null);
                itemCardDTOList.add(itemCardDTO);
                continue;
            }
            itemCardDTOList.add(itemCardDTO);

        }

        return itemCardDTOList;
    }

    public List<ItemCardDTO> findItemsBySpecs(ItemFilterDTO filterDTO) {
        List<Item> items = itemRepository.findAll(itemFilterService.getFilters(filterDTO));
        return items.stream().map(item -> this.modelMapper.map(item, ItemCardDTO.class))
                .collect(Collectors.toList());
    }
}
