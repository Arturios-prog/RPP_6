package com.example.androidlab6.data;

import com.example.androidlab6.myinterf.ChangedListenerData;
import com.example.androidlab6.cart.Cart;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class ItemData {
    int maxId = 0;
    private List<Item> items;
    private List<ChangedListenerData> listeners;
    private static final ItemData ourInstance = new ItemData();

    public static ItemData getInstance() {
        return ourInstance;
    }

    public List<Item> getAvailableItems() {
        List<Item> availableItems = new LinkedList<>();
        for(Item item : items) {
            if(item.getCount() > 0)
                availableItems.add(item);
        }
        return availableItems;
    }

    public void addDataChangedListener(ChangedListenerData listener) {
        listeners.add(listener);
    }

    public void addItem(Item newItem) {
        newItem.setId(maxId + 1);
        maxId +=1;
        items.add(newItem);
        listeners.forEach(new Consumer<ChangedListenerData>() {
            @Override
            public void accept(ChangedListenerData e) {
                e.notifyDataChanged();
            }
        });
    }

    public void deleteItem(int id) {
        for(Item item : items) {
            if(item.getId() == id)
                items.remove(item);
        }
        listeners.forEach(new Consumer<ChangedListenerData>() {
            @Override
            public void accept(ChangedListenerData e) {
                e.notifyDataChanged();
            }
        });
    }

    public void updateItem(Item updatedItem) {
        for(Item item :items) {
            if(item.getId() == updatedItem.getId()) {
                    items.set(items.indexOf(item), updatedItem);
                    Cart.getInstance().updateItem(updatedItem);
            }
        }
        listeners.forEach(new Consumer<ChangedListenerData>() {
            @Override
            public void accept(ChangedListenerData e) {
                e.notifyDataChanged();
            }
        });
    }

    public void removeListener(ChangedListenerData listener) {
        listeners.remove(listener);
    }

    public void clearListeners() {
        listeners.clear();
    }

    public List<Item> getItems() {
        return items;
    }

    public void performPurchase(Cart cart) {
        for(Item item : cart.getItemsArray()) {
            item.setCount(item.getCount() - cart.getCount(item));
        }
        listeners.forEach(new Consumer<ChangedListenerData>() {
            @Override
            public void accept(ChangedListenerData e) {
                e.notifyDataChanged();
            }
        });
    }
    private ItemData() {
        items = new LinkedList<>();
        listeners = new LinkedList<>();
        addItem(new Item(1,"Iphone XS", 90000, 5, "Айфон от компаннии Apple"));
        addItem(new Item(2,"Xiaomi Mi MAX 2", 13000, 25, "Китайский смартфон."));
        addItem(new Item(3,"Samsung galaxy S3", 8000, 0, "Телефон от Samsung."));
        addItem(new Item(4,"Nokia 3310", 100000, 1, "Легендарный телефон."));
        addItem(new Item(5,"IPad 3", 11000, 16, "Планшет от компании Apple."));
        addItem(new Item(6,"Nexus 5X", 9000, 7, "Смартфон."));
    }
}
