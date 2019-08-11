package com.company.HyunseokKahngU1Capstone.controller;


import com.company.HyunseokKahngU1Capstone.exception.NotFoundException;
import com.company.HyunseokKahngU1Capstone.service.GameStoreServiceLayer;
import com.company.HyunseokKahngU1Capstone.viewmodel.GameViewModel;
import com.company.HyunseokKahngU1Capstone.viewmodel.TShirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tShirt")
public class TShirtController {


    @Autowired
    GameStoreServiceLayer gameStoreServiceLayer;

    //MANAGER AND ADMIN ALLOWED
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TShirtViewModel createTShirt(@RequestBody @Valid TShirtViewModel tShirt){
        return gameStoreServiceLayer.saveTShirt(tShirt);
    }

    //EVERYONE IS ALLOWED
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TShirtViewModel getTShirt(@PathVariable("id") int tShirtId){
        TShirtViewModel tShirtViewModel = gameStoreServiceLayer.findTShirtById(tShirtId);
        if(tShirtViewModel == null){
            throw new NotFoundException("T-Shirt could not be retrieved for id " + tShirtId);
        }
        return tShirtViewModel;
    }

    //ADMIN IS ALLOWED
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTShirt(@PathVariable("id") int tShirtId){
        gameStoreServiceLayer.removeTShirt(tShirtId);
    }

    //STAFF, MANAGER, ADMIN ALLOWED
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTShirt(@PathVariable("id") int tShirtId, @RequestBody @Valid TShirtViewModel tShirtViewModel){
        if(tShirtViewModel.gettShirtId() == 0){
            tShirtViewModel.settShirtId(tShirtId);
        }

        if(tShirtId != tShirtViewModel.gettShirtId()){
            throw new IllegalArgumentException("T-Shirt ID on path must match the ID in the T-Shirt Object");
        }

        gameStoreServiceLayer.updateTShirt(tShirtViewModel);
    }

    //EVERYONE IS ALLOWED
    @GetMapping("/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> getTShirtByColor(@PathVariable("color") String color){
        List<TShirtViewModel> tShirts = gameStoreServiceLayer.findTShirtsByColor(color);
        if(tShirts != null && tShirts.size() == 0){
            throw new NotFoundException("T-Shirt could not be retrieved by color " + color);
        }
        return tShirts;
    }

    //EVERYONE IS ALLOWED
    @GetMapping("/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> getTShirtBySize(@PathVariable("size") String size){
        List<TShirtViewModel> tShirts = gameStoreServiceLayer.findTShirtsBySize(size);
        if(tShirts != null && tShirts.size() == 0){
            throw new NotFoundException("T-Shirt could not be retrieved by size: " + size);
        }
        return tShirts;
    }
    @GetMapping(value="/allDone")
    public String allDone(){
        return "You have successfully logged out.";
    }
}
