package com.yassir.bitbox.controllers;

import com.yassir.bitbox.Services.Item.DefaultItemService;
import com.yassir.bitbox.dto.item.ItemDTO;
import com.yassir.bitbox.dto.item.PriceReductionDTO;
import com.yassir.bitbox.dto.item.SupplierDTO;
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

    @GetMapping()
    public List<ItemDTO> getAllItems(@RequestParam String state,@RequestParam String supplier){
        return itemService.getItems(state, supplier);
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
    @DeleteMapping("/delete/{code}")
    public ResponseEntity<String> deleteItem(@PathVariable Long code){
        try{
            itemService.delete(code);
            return new ResponseEntity<>("Deleted item with code: "+code+" successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("An exception has occurred during the delete process: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update/{code}")
    public ResponseEntity<String> updateItem(@PathVariable Long code,@RequestBody ItemDTO itemDTO){
        try{
            itemService.update(code, itemDTO);
            return new ResponseEntity<>("Updated item with code: "+code+" successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("An exception has occurred during the update process: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //--------------------------------------------------------------------------------------------
    //                      ADDERS FOR ITEMS            ++Might need to be in other restcontroller
    //--------------------------------------------------------------------------------------------

    @PostMapping("/suppliers/{code}")
    public ResponseEntity<String> addSupplier(@RequestBody SupplierDTO supplierDTO, @PathVariable Long code){
        try{
            itemService.addSupplier(code,supplierDTO);
            return new ResponseEntity<>("Added", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Something went wrong with the request and the supplier was unable to be Added to item with code: "+code+" ERROR:"+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/discount/{code}")
    public ResponseEntity<String> addDiscount(@RequestBody PriceReductionDTO priceReductionDTO, @PathVariable Long code){
        try{
            itemService.addDiscount(code,priceReductionDTO);
            return new ResponseEntity<>("Added", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Something went wrong with the request and the discount was unable to be Added to item with code: "+code+" ERROR:"+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
