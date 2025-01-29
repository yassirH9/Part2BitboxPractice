package com.yassir.bitbox.controllers;

import com.yassir.bitbox.Services.Item.DefaultItemService;
import com.yassir.bitbox.dto.item.ItemDTO;
import com.yassir.bitbox.dto.item.PriceReductionDTO;
import com.yassir.bitbox.dto.item.SupplierDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemRestController {
    @Autowired
    private DefaultItemService itemService;

    private static final Logger logger = LoggerFactory.getLogger(ItemRestController.class);

    @GetMapping("/")
    public List<ItemDTO> getAllItems(){
        return itemService.getItems();
    }
    @GetMapping("/{code}")
    public ResponseEntity<ItemDTO> getItem(@PathVariable Long code){
        ItemDTO item = itemService.getItemByCode(code);
        if(item!=null){
            return new ResponseEntity<>(item, HttpStatus.OK);
        }else{
            return new  ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/save")
    public ResponseEntity<String> saveItem(@RequestBody ItemDTO itemDTO){
        try{
            itemService.saveItem(itemDTO);
            return new ResponseEntity<>("saved", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Something went wrong with the request and the item was unable to be saved: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/suppliers/{code}")
    public ResponseEntity<String> addSupplier(@RequestBody SupplierDTO supplierDTO, @PathVariable Long code){
        try{
            itemService.addSupplier(code,supplierDTO);
            return new ResponseEntity<>("added", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Something went wrong with the request and the supplier was unable to be added to item with code: "+code, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/discount/{itemCode}")
    public ResponseEntity<String> addDiscount(@RequestBody PriceReductionDTO priceReductionDTO, @PathVariable Long code){
        try{
            itemService.addDiscount(code,priceReductionDTO);
            return new ResponseEntity<>("added", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Something went wrong with the request and the discount was unable to be added to item with code: "+code, HttpStatus.BAD_REQUEST);
        }
    }
}
