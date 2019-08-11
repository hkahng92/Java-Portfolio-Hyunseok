package com.company.HyunseokKahngU1Capstone.dao;

import com.company.HyunseokKahngU1Capstone.model.Game;

import java.util.List;

public interface GameDao {

    Game addGame(Game game);

    Game getGame(int id);

    List<Game> getAllGames();

    Game updateGame(Game game);

    void deleteGame(int id);

    List<Game> getGameByStudio(String studio);

    List<Game> getGameByErsb(String ersb);

    List<Game> getGameByTitle(String title);
}
