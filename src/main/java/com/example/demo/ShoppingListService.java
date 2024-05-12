package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingListService {
    private final List<ShoppingItem> shoppingList = new ArrayList<>();

    public List<ShoppingItem> getAllItems() {
        return shoppingList;
    }

    public ShoppingItem addItem(ShoppingItem item) {
        shoppingList.add(item);
        return item;
    }

    public void deleteItem(Long id) {
        shoppingList.removeIf(item -> item.getId().equals(id));
    }

    public ShoppingItem markAsPurchased(Long id) {
        ShoppingItem item = shoppingList.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow();
        item.setPurchased(true);
        return item;
    }
}