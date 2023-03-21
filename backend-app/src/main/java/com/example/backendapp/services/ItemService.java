package com.example.backendapp.services;

import com.example.backendapp.DTOs.ItemAddDTO;
import com.example.backendapp.DTOs.ItemCardDTO;
import com.example.backendapp.DTOs.ItemFilterDTO;
import com.example.backendapp.entities.Image;
import com.example.backendapp.entities.Item;
import com.example.backendapp.repositories.ImageRepository;
import com.example.backendapp.repositories.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemFilterService itemFilterService;
    private final ImageRepository imageRepository;
    private final ImageService imageService;
    public  final ModelMapper modelMapper = new ModelMapper();

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

    public Item saveItem(ItemAddDTO itemAddDTO,MultipartFile file) throws IOException {
//        String filePath = PATH + file.getOriginalFilename();
//        Image image = Image.builder()
//                .name(file.getOriginalFilename())
//                .type(file.getContentType())
//                .filePath(filePath)
//                .build();
//        file.transferTo(new File(filePath));
//        itemAddDTO.setFilePath(imageService.uploadImageToFile(file));


        Item item =this.modelMapper.map(itemAddDTO,Item.class);
        item.setDateOfAdd(new Date());
//        item.setFilePath(imageService.uploadImageToFile(file));'
        item.setImage(imageService.uploadImageToFile(file));
         itemRepository.save(item);
         return item;
    }

    public List<Item> getAllItems() {
//        List<Item> items = this.itemRepository.findAll();

        return itemRepository.findAll();
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
    public void updateItem(UUID id,ItemAddDTO itemUpdate){
        Item item = (itemRepository.findById(id).get());
//       item.stream().map(item1 -> this.modelMapper.map(item1,ItemAddDTO.class));
//            List<Item> items = itemRepository.findAll();
//            items.stream().map(item -> {
//                        if (item.getId().equals(id)) {
//                            this.modelMapper.map(itemUpdate, Item.class);
//                            this.itemRepository.save(item);
//                            System.out.println(item);
//                        }
//                        return item;
//                    }
//            );
          item.setName(itemUpdate.getName());
          item.setBrand(itemUpdate.getBrand());
          item.setSerialNumber(itemUpdate.getSerialNumber());
          item.setPrice(itemUpdate.getPrice());
          item.setCategory(itemUpdate.getCategory());
          item.setDescriere(itemUpdate.getDescriere());
itemRepository.save(item);

    }
    public List<ItemCardDTO> getAllItemCards() throws IOException {
//        List<ItemCardDTO> itemCardDTOList=this.itemRepository.findAll().stream()
//                .map(item -> this.modelMapper.map(item, ItemCardDTO.class))
//                .collect(Collectors.toList());
//        itemCardDTOList.forEach(itemCardDTO -> {
//                    try {
//                        byte[] imageBytes = imageService.downloadImageFromFile(itemCardDTO.getImage().getFilePath());
//                        for (byte bit:imageBytes.length){
//                            itemCardDTO.getImage().getImageBytes().a
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//        );
//       itemCardDTOList.forEach(itemCardDTO -> {
//            if(itemCardDTO.getImage()!=null) {
//                try {
//                    itemCardDTO.getImage().setImageBytes(imageService.downloadImageFromFile(itemCardDTO.getImage().getFilePath()));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    itemCardDTO.setImage(null);
//                    continue;
//                }
//            }
//        });
        List<Item> items = itemRepository.findAll();
        List<ItemCardDTO> itemCardDTOList = new ArrayList<>();
        for(Item item:items){
            ItemCardDTO itemCardDTO = modelMapper.map(item,ItemCardDTO.class);
//            System.out.println(itemCardDTO+"sal");
//            byte[] araay = imageService.downloadImageFromFile("C:/Users/digid/OneDrive/Desktop/imagePath/AQ1.2.jpeg");
            try {
                System.out.println("aicea");
                System.out.println(item.getImage().getFilePath());

//                System.out.println(Base64.getEncoder().encodeToString(imageService.downloadImageFromFile(item.getFilePath())));

                itemCardDTO.setBytes(imageService.downloadImageFromFile(item.getImage().getFilePath()));
//                System.out.println(itemCardDTO+"altcv");
            } catch (Exception e){

                itemCardDTO.setImage(null);
                itemCardDTOList.add(itemCardDTO);
                continue;
            }
            itemCardDTOList.add(itemCardDTO);

        }
//        System.out.println(items);
//        System.out.println("apoi");
        System.out.println(itemCardDTOList);
        return itemCardDTOList;
    }

    public List<ItemCardDTO> findItemsBySpecs(ItemFilterDTO filterDTO){
        List<Item> items = itemRepository.findAll(itemFilterService.getFilters(filterDTO));
        return items.stream().map(item -> this.modelMapper.map(item,ItemCardDTO.class))
                .collect(Collectors.toList());
    }
}
