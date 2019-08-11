package com.company.HyunseokKahngU1Capstone.dao;

import com.company.HyunseokKahngU1Capstone.model.Console;

import java.util.List;

public interface ConsoleDao {

    Console addConsole(Console console);

    Console getConsole(int id);

    List<Console> getAllConsoles();

    Console updateConsole(Console console);

    void deleteConsole(int id);

    List<Console> getConsoleByManufacturer(String manufacturer);
}
