package com.example.backendapp.services;

import com.example.backendapp.DTOs.OrderDTO;
import com.example.backendapp.DTOs.OrderItemDTO;
import com.example.backendapp.DTOs.UserDTO;
import com.example.backendapp.Security.User;
import com.example.backendapp.config.JwtAuthenticationFilter;
import com.example.backendapp.entities.Cart;
import com.example.backendapp.entities.Item;
import com.example.backendapp.entities.OrderDetail;
import com.example.backendapp.entities.OrderItem;
import com.example.backendapp.repositories.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.xml.transform.dom.DOMSource;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public final ModelMapper modelMapper = new ModelMapper();

    public OrderDetailService(OrderDetailRepository orderRepository, CartRepository cartRepository, UserRepository userRepository, ItemRepository itemRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.orderItemRepository = orderItemRepository;

    }

    public OrderDetail placeOrder(OrderDTO orderDTO) {
        OrderDetail orderDetail = this.modelMapper.map(orderDTO, OrderDetail.class);
        User user = this.userRepository.findByEmail(JwtAuthenticationFilter.userEmail).get();
        double totalPrice = 0;

        List<OrderItem> orderItems = new ArrayList<>();
        List<Cart> cartItems = this.cartRepository.findByUser(user);
        cartItems.forEach(cart -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(cart.getItem());
            orderItem.setUser(user);
            orderItem.setQuantity(cart.getQuantity());
            orderItems.add(orderItem);
            this.orderItemRepository.save(orderItem);
            Item item = this.itemRepository.findById(cart.getItem().getId()).get();
            item.setQuantity(item.getQuantity() - cart.getQuantity());

        });
        totalPrice = cartItems.stream().mapToDouble(cart -> cart.getQuantity() * (cart.getItem().getPrice() - ((Double.valueOf(cart.getItem().getPercentSale()) / 100) * cart.getItem().getPrice()))).sum();
        if (totalPrice > 150) {
            orderDetail.setShipping(0.0);
        } else {
            orderDetail.setShipping(15.0); //need to remove if i put on frontend too
        }
        totalPrice += orderDetail.getShipping();
        orderDetail.setOrderItems(orderItems);
        orderDetail.setOrderAmount(totalPrice);
        orderDetail.setOrderDate(new Date());
        orderDetail.setUser(user);
//        for(OrderItem orderItem1 : orderDetail.getOrderItems()){
//            orderItem1.getOrderDetail().add(orderDetail);
//        }
       OrderDetail orderDetail1= this.orderRepository.save(orderDetail);
        this.cartRepository.deleteAllByUser(user);

        return orderDetail1;
    }

    public void deleteAllByUser(){
        List<Cart> carts = this.cartRepository.findByUser(this.userRepository.findByEmail(JwtAuthenticationFilter.userEmail).get());
        this.cartRepository.deleteAllByUser(this.userRepository.findByEmail(JwtAuthenticationFilter.userEmail).get());
    }

    public List<OrderItemDTO> checkout() { // TODO: 4/4/2023 move to order item service
        User user = this.userRepository.findByEmail(JwtAuthenticationFilter.userEmail).get();
        List<Cart> carts = this.cartRepository.findByUser(user);
        List<OrderItemDTO> orderItems = new ArrayList<>();
        carts.forEach(cart -> {
            OrderItemDTO orderItem = new OrderItemDTO();
            UserDTO userDTO = new UserDTO();
            userDTO.setOrderEmail(user.getEmail());
            userDTO.setOrderLastName(user.getLastname());
            userDTO.setOrderFirstName(user.getFirstname());
            orderItem.setItem(cart.getItem());
            orderItem.setUserDTO(userDTO);// adaug dto pentru user
            orderItem.setQuantity(cart.getQuantity());
            orderItems.add(orderItem);
        });
        return orderItems;
    }

    // TODO: 4/4/2023 GetAllOrders
    public List<OrderDetail> getAllOrders() {
        return this.orderRepository.findAll();
    }

    public List<OrderDetail> getAllOrdersByUser() {
        User user = this.userRepository.findByEmail(JwtAuthenticationFilter.userEmail).get();
        return this.orderRepository.findAllByUserId(user.getId());

    }

    public ByteArrayOutputStream generateInvoice(OrderDetail orderDetail) {
        try {
            InputStream template = getClass().getResourceAsStream("C:/Users/danma/Desktop/ImagePath/InvoiceDoc/INVOICe.docx");
            XWPFDocument document = new XWPFDocument(template);
            Map<String, String> data = new HashMap<>();
            data.put("email", orderDetail.getOrderEmail());
            data.put("firstname", orderDetail.getOrderFirstName());
            data.put("lastname", orderDetail.getOrderLastName());
            data.put("adress", orderDetail.getOrderAdress());
            data.put("contactNumber", orderDetail.getContactNumber());

            double total = 0;
            double shippingCost = 0;

            for (int i = 0; i < orderDetail.getOrderItems().size(); i++) {
                data.put("Name_" + i, orderDetail.getOrderItems().get(i).getItem().getName());
                data.put("Brand_" + i, orderDetail.getOrderItems().get(i).getItem().getBrand());
                data.put("SN_" + i, orderDetail.getOrderItems().get(i).getItem().getSerialNumber());
                data.put("Price_" + i, "" + orderDetail.getOrderItems().get(i).getItem().getPrice());
                data.put("Quantity_" + i, orderDetail.getOrderItems().get(i).getQuantity().toString());
                total += orderDetail.getOrderItems().get(i).getQuantity() *
                        (orderDetail.getOrderItems().get(i).getItem().getPrice() -
                                ((Double.valueOf(orderDetail.getOrderItems().get(i).getItem().getPercentSale()) / 100) *
                                        orderDetail.getOrderItems().get(i).getItem().getPrice()));
                data.put("FinalPrice_" + i, String.valueOf(total));

            }
            if (total >= 150) {
                shippingCost = 15;
            } else {
                shippingCost = 0;
            }
            data.put("shippingCost_", String.valueOf(shippingCost));
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                for (XWPFRun run : paragraph.getRuns()) {
                    String text = run.getText(0);
                    if (text != null) {
                        for (Map.Entry<String, String> entry : data.entrySet()) {
                            text = text.replace("${" + entry.getKey() + "}", entry.getValue());

                        }
                        run.setText(text, 0);
                    }
                }
            }

            for (XWPFTable table : document.getTables()) {
                for (int i = 1; i < table.getRows().size(); i++) {
                    XWPFTableRow row = table.getRow(i);
                    for (int j = 0; j < row.getTableCells().size(); j++) {
                        XWPFTableCell cell = row.getCell(j);
                        String text = cell.getText();
                        if (text != null) {
                            for (Map.Entry<String, String> entry : data.entrySet()) {
                                text = text.replace("${" + entry.getKey() + "}", entry.getValue());
                            }
                            cell.removeParagraph(0);
                            XWPFParagraph paragraph = cell.addParagraph();
                            paragraph.setAlignment(ParagraphAlignment.CENTER);
                            paragraph.setVerticalAlignment(TextAlignment.CENTER);
                            XWPFRun run = paragraph.createRun();
                            run.setText(text);

                        }
                    }
                }
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(new com.itextpdf.text.Document(), baos);
            writer.setInitialLeading(12.5f);
            writer.setCloseStream(false);
            writer.open();
            document.write(baos);
            return baos;

        } catch (Exception e) {
            return null;
        }
    }
}


