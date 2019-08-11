package com.company.HyunseokKahngU1Capstone.controller;

import com.company.HyunseokKahngU1Capstone.exception.NotFoundException;
import com.company.HyunseokKahngU1Capstone.service.GameStoreServiceLayer;
import com.company.HyunseokKahngU1Capstone.viewmodel.GameViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {


    @Autowired
    GameStoreServiceLayer gameStoreServiceLayer;

    //MANAGER AND ADMIN ALLOWED
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameViewModel createGame(@RequestBody @Valid GameViewModel game){
        return gameStoreServiceLayer.saveGame(game);
    }

    //EVERYONE IS ALLOWED
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GameViewModel getGame(@PathVariable("id") int gameId){
        GameViewModel gameViewModel = gameStoreServiceLayer.findGameById(gameId);
        if(gameViewModel == null){
            throw new NotFoundException("Game could not be retrieved for id " + gameId);
        }
        return gameViewModel;
    }

    //ONLY ADMIN IS ALLOWED
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable("id") int gameId){
        gameStoreServiceLayer.removeGame(gameId);
    }


    //STAFF, MANAGER AND ADMIN ALLOWED
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@PathVariable("id") int gameId, @RequestBody @Valid GameViewModel gameViewModel){
        if(gameViewModel.getGameId() == 0){
            gameViewModel.setGameId(gameId);
        }

        if(gameId != gameViewModel.getGameId()){
            throw new IllegalArgumentException("Game ID on path must match the ID in the Game Object");
        }

        gameStoreServiceLayer.updateGame(gameViewModel);
    }

    //EVERYONE IS ALLOWED
    @GetMapping("/studio/{studio}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGameByStudio(@PathVariable("studio") String studio){
        List<GameViewModel> games = gameStoreServiceLayer.findGamesByStudio(studio);
        if(games != null && games.size() == 0){
            throw new NotFoundException("Game could not be retrieved by studio named " + studio);
        }
        return games;
    }

    //EVERYONE IS ALLOWED
    @GetMapping("/ersb/{ersb}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGameByErsbRating(@PathVariable("ersb") String ersbRating){
        List<GameViewModel> games = gameStoreServiceLayer.findGamesByErsb(ersbRating);
        if(games != null && games.size() == 0){
            throw new NotFoundException("Game could not be retrieved by ERSB Rating of " + ersbRating);
        }
        return games;
    }

    //EVERYONE IS ALLOWED
    @GetMapping("/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGameByTitle(@PathVariable("title") String title){
        List<GameViewModel> games = gameStoreServiceLayer.findGamesByTitle(title);
        if(games != null && games.size() == 0){
            throw new NotFoundException("Game could not be retrieved by the title of " + title);
        }
        return games;
    }

    @GetMapping(value="/allDone")
    public String allDone(){
        return "You have successfully logged out.";
    }
}
