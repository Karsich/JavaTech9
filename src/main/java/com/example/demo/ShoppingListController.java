package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shopping-list")
public class ShoppingListController {
    private final ShoppingListService shoppingListService;

    @Autowired
    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @GetMapping
    public List<ShoppingItem> getAllItems() {
        return shoppingListService.getAllItems();
    }

    @PostMapping
    public ShoppingItem addItem(@RequestBody ShoppingItem item) {
        return shoppingListService.addItem(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        shoppingListService.deleteItem(id);
    }

    @PatchMapping("/{id}")
    public ShoppingItem markAsPurchased(@PathVariable Long id) {
        return shoppingListService.markAsPurchased(id);
    }
}