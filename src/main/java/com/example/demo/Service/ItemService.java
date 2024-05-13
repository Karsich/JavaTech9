package com.example.demo.Service;

import com.example.demo.Model.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    private final List<Item> shoppingList = new ArrayList<>();

    public List<Item> getAllItems() {
        return shoppingList;
    }

    public Item addItem(Item item) {
        shoppingList.add(item);
        return item;
    }

    public void deleteItem(Long id) {
        shoppingList.removeIf(item -> item.getId().equals(id));
    }

    public Item markAsPurchased(Long id) {
        Item item = shoppingList.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElseThrow();
        item.setPurchased(true);
        return item;
    }
}