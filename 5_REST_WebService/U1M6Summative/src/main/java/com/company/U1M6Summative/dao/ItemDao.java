package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.model.Item;

import java.util.List;

public interface ItemDao {

    //CRUD Methods
    //Create - add a new item
    Item addItem(Item item);

    //Read - Get an item by its ID
    Item getItem(Integer itemId);

    //Read - Get a list of ALL items
    List<Item> getAllItems();

    //Update - Update the entry of an item
    Item updateItem(Item item);

    //Delete - Delete an item given an item ID
    boolean deleteItem(Integer itemId);
}
