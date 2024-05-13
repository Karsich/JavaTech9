package com.example.demo.Controller;

import com.example.demo.Model.Item;
import com.example.demo.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/api/shopping-list")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @PostMapping("/api/shopping-list")
    public Item addItem(@RequestBody Item item) {
        return itemService.addItem(item);
    }

    @DeleteMapping("/api/shopping-list/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }

    @PatchMapping("/api/shopping-list/{id}")
    public Item markAsPurchased(@PathVariable Long id) {
        return itemService.markAsPurchased(id);
    }
}