package com.company.HyunseokKahngU1Capstone.dao;

import com.company.HyunseokKahngU1Capstone.model.TShirt;

import java.util.List;

public interface TShirtDao {

    TShirt addTShirt(TShirt tShirt);

    TShirt getTShirt(int id);

    List<TShirt> getAllTShirts();

    TShirt updateTShirt(TShirt tShirt);

    void deleteTShirt(int id);

    List<TShirt> getTShirtByColor(String color);

    List<TShirt> getTShirtBySize(String size);
}
