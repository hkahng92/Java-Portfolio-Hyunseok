package com.company.HyunseokKahngU1Capstone.controller;


import com.company.HyunseokKahngU1Capstone.exception.NotFoundException;
import com.company.HyunseokKahngU1Capstone.service.GameStoreServiceLayer;
import com.company.HyunseokKahngU1Capstone.viewmodel.ConsoleViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/console")
public class ConsoleController {

    @Autowired
    GameStoreServiceLayer gameStoreServiceLayer;

    //MANAGER AND ADMIN ALLOWED
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsoleViewModel createConsole(@RequestBody @Valid ConsoleViewModel console){
        return gameStoreServiceLayer.saveConsole(console);
    }

    //ALL USERS ARE ABLE TO DO THE SEARCHES - AUTHORIZED OR NOT
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ConsoleViewModel getConsole(@PathVariable("id") int consoleId){
        ConsoleViewModel consoleViewModel = gameStoreServiceLayer.findConsoleById(consoleId);
        if(consoleViewModel == null){
            throw new NotFoundException("Console could not be retrieved for id " + consoleId);
        }
        return consoleViewModel;
    }

    //ADMIN ALLOWED
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable("id") int consoleId){
        gameStoreServiceLayer.removeConsole(consoleId);
    }

    //STAFF, MANAGER, ADMIN ALLOWED
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@PathVariable("id") int consoleId, @RequestBody @Valid ConsoleViewModel consoleViewModel){
        if(consoleViewModel.getConsoleId() == 0){
            consoleViewModel.setConsoleId(consoleId);
        }
        if(consoleId != consoleViewModel.getConsoleId()){
            throw new IllegalArgumentException("Console ID on path must match the ID in the Console Object");
        }
        gameStoreServiceLayer.updateConsole(consoleViewModel);
    }

    //ALL USERS ARE ABLE TO DO THE SEARCHES - AUTHORIZED OR NOT
    @GetMapping("/manufacturer/{manufacturer}")
    @ResponseStatus(HttpStatus.OK)
    public List<ConsoleViewModel> getConsoleByManufacturer(@PathVariable("manufacturer") String manufacturer){
        List<ConsoleViewModel> consoles = gameStoreServiceLayer.findConsolesByManufacturer(manufacturer);
        if(consoles != null && consoles.size() == 0){
            throw new NotFoundException("Console could not be retrieved by manufacturer " + manufacturer);
        }
        return consoles;
    }


    @GetMapping(value="/allDone")
    public String allDone(){
        return "You have successfully logged out.";
    }
}
