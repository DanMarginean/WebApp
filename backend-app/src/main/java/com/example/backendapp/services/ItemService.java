package com.example.backendapp.services;

import com.example.backendapp.DTOs.ItemAddDTO;
import com.example.backendapp.DTOs.ItemCardDTO;
import com.example.backendapp.DTOs.ItemDetailsDTO;
import com.example.backendapp.DTOs.ItemFilterDTO;
import com.example.backendapp.Security.User;
import com.example.backendapp.config.JwtAuthenticationFilter;
import com.example.backendapp.entities.*;
import com.example.backendapp.repositories.*;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemFilterService itemFilterService;
    private final ImageRepository imageRepository;
    private final ImageService imageService;

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private UserRepository userRepository;
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
        List<OrderItem> orderItems = this.orderItemRepository.findAllByItem_Id(id);
        this.orderItemRepository.deleteAll(orderItems);
        List<Cart> carts = this.cartRepository.findAllByItem_Id(id);
        this.cartRepository.deleteAll(carts);
        List<OrderDetail> orderDetails = this.orderDetailRepository.findAll();
        orderDetails.forEach(orderDetail -> {
            List<OrderItem> orderItems1 = orderDetail.getOrderItems();
            for(int i = 0; i<orderItems1.size();i++){
                if(orderItems1.get(i).getItem().getId().equals(id)){
                    orderItems1.remove(i);
                }
            };
        });
        itemRepository.deleteById(id);
    }

    public void testRepos(UUID id ,String category,String generalCategory){
        Item item = this.itemRepository.findById(id).get();
        List<Item> items = this.itemRepository.findAllByCategory(category);
        List<Item> items1 = this.itemRepository.findAllByGeneralCategory(generalCategory);
        User user = this.userRepository.findByEmail(JwtAuthenticationFilter.userEmail).get();
        Cart cart = this.cartRepository.findByItemAndUser(item, user);
        List<Cart> cart1 = this.cartRepository.findAllByItem_Id(id);
        List<Cart> carts = this.cartRepository.findByUser(user);
        List<OrderDetail> orderDetail = this.orderDetailRepository.findAllByUserId(user.getId());
        List<OrderItem> orderItem = this.orderItemRepository.findAllByItem_Id(id);
        System.out.println("salut");

    }

    public void updateItem(UUID id, ItemAddDTO itemUpdate,MultipartFile file) throws IOException {
        Item item = (itemRepository.findById(id).get());
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
        if(file!=null){
            Image image = this.imageRepository.findFirstByFilePath(item.getImage().getFilePath());
            this.imageRepository.delete(image);
            item.setImage(imageService.uploadImageToFile(file)); //ToDo Find the image of this item erase it and ad a new one
        }
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

    public List<ItemCardDTO> getItemsByCategory(String category) {
        List<Item> items =this.itemRepository.findAllByCategory(category);
        items.stream().filter(item -> item.getCategory().equals(category));
        List<ItemCardDTO> itemCardDTOS = new ArrayList<>();
        for (Item item : items) {
            ItemCardDTO itemCardDTO = this.modelMapper.map(item, ItemCardDTO.class);
            try {

                itemCardDTO.setBytes(imageService.downloadImageFromFile(item.getImage().getFilePath()));
            }
            catch (NullPointerException e){
                itemCardDTOS.add(itemCardDTO);
                continue;

            }
            catch (IOException e) {
//               throw new RuntimeException(e);
                itemCardDTO.setImage(null);
                itemCardDTOS.add(itemCardDTO);
                continue;
            }


            itemCardDTOS.add(itemCardDTO);
        }
        return itemCardDTOS;

    }

    public List<ItemCardDTO> getItemsByGeneralCategory(String generalCategory){
        List<Item> items =this.itemRepository.findAllByGeneralCategory(generalCategory);
        items.stream().filter(item -> item.getCategory().equals(generalCategory));
        List<ItemCardDTO> itemCardDTOS = new ArrayList<>();
        for (Item item : items) {
            ItemCardDTO itemCardDTO = this.modelMapper.map(item, ItemCardDTO.class);
            try {
                itemCardDTO.setBytes(imageService.downloadImageFromFile(item.getImage().getFilePath()));
            }
            catch (NullPointerException e){
                itemCardDTOS.add(itemCardDTO);
                continue;

            }
            catch (IOException e) {
//                throw new RuntimeException(e);
                itemCardDTO.setImage(null);
                itemCardDTOS.add(itemCardDTO);
                continue;
            }
            itemCardDTOS.add(itemCardDTO);
        }
        return itemCardDTOS;
    }

    public Set<String> getMenuGeneralCategory(){
        List<Item>items = this.itemRepository.findAll();
        Set<String> generalCategories = new HashSet<>();
        items.forEach(item -> {
            generalCategories.add(item.getGeneralCategory());
        });
        return generalCategories;
    }
    public Set<String> getMenuCategory(String generalCategory){
        List<Item>items = this.itemRepository.findAll();
        Set<String> categories = new HashSet<>();
        items.forEach(item -> {
            if(item.getGeneralCategory().equals(generalCategory)){
                categories.add(item.getCategory());
            }
        });
        return categories;
    }
}
