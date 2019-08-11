package com.company.HyunseokKahngU1Capstone.service;

import com.company.HyunseokKahngU1Capstone.dao.*;
import com.company.HyunseokKahngU1Capstone.exception.InvalidItemTypeException;
import com.company.HyunseokKahngU1Capstone.exception.NotFoundException;
import com.company.HyunseokKahngU1Capstone.exception.QuantityValidationException;
import com.company.HyunseokKahngU1Capstone.model.*;
import com.company.HyunseokKahngU1Capstone.viewmodel.ConsoleViewModel;
import com.company.HyunseokKahngU1Capstone.viewmodel.GameViewModel;
import com.company.HyunseokKahngU1Capstone.viewmodel.InvoiceViewModel;
import com.company.HyunseokKahngU1Capstone.viewmodel.TShirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
public class GameStoreServiceLayer {

    ConsoleDao consoleDao;
    GameDao gameDao;
    TShirtDao tShirtDao;
    InvoiceDao invoiceDao;
    ProcessingFeeDao processingFeeDao;
    SalesTaxRateDao salesTaxRateDao;

    @Autowired
    public GameStoreServiceLayer (ConsoleDao consoleDao, GameDao gameDao, TShirtDao tShirtDao, InvoiceDao invoiceDao, ProcessingFeeDao processingFeeDao, SalesTaxRateDao salesTaxRateDao){
        this.consoleDao = consoleDao;
        this.gameDao = gameDao;
        this.tShirtDao = tShirtDao;
        this.invoiceDao = invoiceDao;
        this.processingFeeDao = processingFeeDao;
        this.salesTaxRateDao = salesTaxRateDao;
    }

    public ConsoleViewModel saveConsole(ConsoleViewModel consoleViewModel){
        Console console = new Console();
        console.setModel(consoleViewModel.getModel());
        console.setManufacturer(consoleViewModel.getManufacturer());
        console.setMemoryAmount(consoleViewModel.getMemoryAmount());
        console.setProcessor(consoleViewModel.getProcessor());
        console.setPrice(consoleViewModel.getPrice());
        console.setQuantity(consoleViewModel.getQuantity());

        console = consoleDao.addConsole(console);

        consoleViewModel.setConsoleId(console.getConsoleId());

        return consoleViewModel;
    }

    public ConsoleViewModel updateConsole(ConsoleViewModel consoleViewModel){
        Console console = new Console();
        console.setConsoleId(consoleViewModel.getConsoleId());
        console.setModel(consoleViewModel.getModel());
        console.setManufacturer(consoleViewModel.getManufacturer());
        console.setMemoryAmount(consoleViewModel.getMemoryAmount());
        console.setProcessor(consoleViewModel.getProcessor());
        console.setPrice(consoleViewModel.getPrice());
        console.setQuantity(consoleViewModel.getQuantity());

        consoleDao.updateConsole(console);

        return buildConsoleViewModel(console);
    }

    public ConsoleViewModel findConsoleById(int id){
        Console console = consoleDao.getConsole(id);
        if(console == null){
            return null;
        } else {
            return buildConsoleViewModel(console);
        }
    }

    public void removeConsole(int id){
        consoleDao.deleteConsole(id);
    }

    public List<ConsoleViewModel> findConsolesByManufacturer(String manufacturer){
        List<Console> consoles = consoleDao.getConsoleByManufacturer(manufacturer);
        List<ConsoleViewModel> consoleViewModels = new ArrayList<>();

        for(Console console : consoles){
            consoleViewModels.add(buildConsoleViewModel(console));
        }
        return consoleViewModels;
    }

    public GameViewModel saveGame(GameViewModel gameViewModel){
        Game game = new Game();
        game.setTitle(gameViewModel.getTitle());
        game.setErsbRating(gameViewModel.getErsbRating());
        game.setDescription(gameViewModel.getDescription());
        game.setPrice(gameViewModel.getPrice());
        game.setStudio(gameViewModel.getStudio());
        game.setQuantity(gameViewModel.getQuantity());

        game = gameDao.addGame(game);

        gameViewModel.setGameId(game.getGameId());

        return gameViewModel;
    }

    public GameViewModel updateGame(GameViewModel gameViewModel){
        Game game = new Game();
        game.setGameId(gameViewModel.getGameId());
        game.setTitle(gameViewModel.getTitle());
        game.setErsbRating(gameViewModel.getErsbRating());
        game.setDescription(gameViewModel.getDescription());
        game.setPrice(gameViewModel.getPrice());
        game.setStudio(gameViewModel.getStudio());
        game.setQuantity(gameViewModel.getQuantity());

        gameDao.updateGame(game);

        return buildGameViewModel(game);
    }

    public GameViewModel findGameById(int id){
        Game game = gameDao.getGame(id);
        if(game == null){
            return null;
        } else {
            return buildGameViewModel(game);
        }
    }

    public void removeGame(int id){
        gameDao.deleteGame(id);
    }

    public List<GameViewModel> findGamesByStudio(String studio){
        List<Game> games = gameDao.getGameByStudio(studio);
        List<GameViewModel> gameViewModels = new ArrayList<>();

        for(Game game : games){
            gameViewModels.add(buildGameViewModel(game));
        }
        return gameViewModels;
    }

    public List<GameViewModel> findGamesByErsb(String ersb){
        List<Game> games = gameDao.getGameByErsb(ersb);
        List<GameViewModel> gameViewModels = new ArrayList<>();

        for(Game game : games){
            gameViewModels.add(buildGameViewModel(game));
        }
        return gameViewModels;
    }

    public List<GameViewModel> findGamesByTitle(String title){
        List<Game> games = gameDao.getGameByTitle(title);
        List<GameViewModel> gameViewModels = new ArrayList<>();

        for(Game game : games){
            gameViewModels.add(buildGameViewModel(game));
        }
        return gameViewModels;
    }

    public TShirtViewModel saveTShirt(TShirtViewModel tShirtViewModel){
        TShirt tShirt = new TShirt();
        tShirt.setSize(tShirtViewModel.getSize());
        tShirt.setColor(tShirtViewModel.getColor());
        tShirt.setDescription(tShirtViewModel.getDescription());
        tShirt.setPrice(tShirtViewModel.getPrice());
        tShirt.setQuantity(tShirtViewModel.getQuantity());

        tShirt = tShirtDao.addTShirt(tShirt);

        tShirtViewModel.settShirtId(tShirt.gettShirtId());

        return tShirtViewModel;
    }

    public TShirtViewModel updateTShirt(TShirtViewModel tShirtViewModel){
        TShirt tShirt = new TShirt();
        tShirt.settShirtId(tShirtViewModel.gettShirtId());
        tShirt.setSize(tShirtViewModel.getSize());
        tShirt.setColor(tShirtViewModel.getColor());
        tShirt.setDescription(tShirtViewModel.getDescription());
        tShirt.setPrice(tShirtViewModel.getPrice());
        tShirt.setQuantity(tShirtViewModel.getQuantity());

        tShirtDao.updateTShirt(tShirt);

        return buildTShirtViewModel(tShirt);
    }

    public TShirtViewModel findTShirtById(int id){
        TShirt tShirt = tShirtDao.getTShirt(id);

        if(tShirt == null){
            return null;
        } else {
            return buildTShirtViewModel(tShirt);
        }
    }

    public void removeTShirt(int id){
        tShirtDao.deleteTShirt(id);
    }

    public List<TShirtViewModel> findTShirtsByColor(String color){
        List<TShirt> tShirts = tShirtDao.getTShirtByColor(color);
        List<TShirtViewModel> tShirtViewModels = new ArrayList<>();

        for(TShirt tShirt : tShirts){
            tShirtViewModels.add(buildTShirtViewModel(tShirt));
        }
        return tShirtViewModels;
    }

    public List<TShirtViewModel> findTShirtsBySize(String size){
        List<TShirt> tShirts = tShirtDao.getTShirtBySize(size);
        List<TShirtViewModel> tShirtViewModels = new ArrayList<>();

        for(TShirt tShirt : tShirts){
            tShirtViewModels.add(buildTShirtViewModel(tShirt));
        }
        return tShirtViewModels;
    }

    @Transactional
    public InvoiceViewModel saveInvoice(InvoiceViewModel invoiceViewModel) throws QuantityValidationException, InvalidItemTypeException{
        Invoice invoice = new Invoice();
        invoice.setName(invoiceViewModel.getName());
        invoice.setStreet(invoiceViewModel.getStreet());
        invoice.setCity(invoiceViewModel.getCity());
        invoice = setUpState(invoiceViewModel,invoice);
        invoice.setZipcode(invoiceViewModel.getZipcode());
        invoice = setUpItemType(invoiceViewModel,invoice);
        invoice = setUpItemId(invoiceViewModel,invoice);
        invoice.setQuantity(invoiceViewModel.getQuantity());
        invoice = calcSubtotal(invoiceViewModel,invoice);
        invoice = calcTax(invoiceViewModel,invoice);
        invoice = calcProcessingFee(invoiceViewModel,invoice);
        invoice = calcTotal(invoice);

        invoice = invoiceDao.addInvoice(invoice);
        invoiceViewModel.setInvoiceId(invoice.getInvoiceId());
        invoiceViewModel.setUnitPrice(invoice.getUnitPrice());
        invoiceViewModel.setSubtotal(invoice.getSubtotal());
        invoiceViewModel.setTax(invoice.getTax());
        invoiceViewModel.setProcessingFee(invoice.getProcessingFee());
        invoiceViewModel.setTotal(invoice.getTotal());
        return invoiceViewModel;
    }

    public InvoiceViewModel findInvoiceById(int id){
        Invoice invoice = invoiceDao.getInvoice(id);
        if(invoice == null){
            return null;
        } else {
            return buildInvoiceViewModel(invoice);
        }
    }

    private Invoice setUpItemType(InvoiceViewModel invoiceViewModel, Invoice invoice) throws InvalidItemTypeException {
        if(invoiceViewModel.getItemType().equals("Consoles")){
            invoice.setItemType(invoiceViewModel.getItemType());
        } else if(invoiceViewModel.getItemType().equals("Games")){
            invoice.setItemType(invoiceViewModel.getItemType());
        } else if(invoiceViewModel.getItemType().equals("T-Shirts")){
            invoice.setItemType(invoiceViewModel.getItemType());
        } else {
            throw new InvalidItemTypeException("You must match one of the following keywords exactly: Consoles / Games / T-Shirts");
        }
        return invoice;
    }

    private Invoice setUpItemId(InvoiceViewModel invoiceViewModel,Invoice invoice) throws NotFoundException{
        switch(invoiceViewModel.getItemType()){
            case "Consoles":
                Console console = consoleDao.getConsole(invoiceViewModel.getItemId());
                if(console == null){
                    throw new NotFoundException("Item you are looking for is not found in the inventory.");
                }else {
                    invoice.setItemId(invoiceViewModel.getItemId());
                }
                break;
            case "Games":
                Game game = gameDao.getGame(invoiceViewModel.getItemId());
                if(game == null){
                    throw new NotFoundException("Item you are looking for is not found in the inventory.");
                } else{
                    invoice.setItemId(invoiceViewModel.getItemId());
                }
                break;
            case "T-Shirts":
                TShirt tShirt = tShirtDao.getTShirt(invoiceViewModel.getItemId());
                if(tShirt == null){
                    throw new NotFoundException("Item you are looking for is not found in the inventory.");
                } else{
                    invoice.setItemId(invoiceViewModel.getItemId());
                }
                break;
        }
        return invoice;
    }

    private Invoice setUpState(InvoiceViewModel invoiceViewModel, Invoice invoice) throws NotFoundException {
        String state = invoiceViewModel.getState().toUpperCase();
        String[] stateList =
                {"AK","AL","AZ","AR","CA","CO","CT","DE","FL","GA","HI","ID","IL",
                        "IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO",
                        "MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR",
                        "PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI",
                        "WY"};
        Boolean flag = false;
        for(int i =0; i < stateList.length; i++){
            if(stateList[i].equals(state)) {
                invoice.setState(state);
                flag = true;
                break;
            }
        }
        if(flag == false){
            throw new NotFoundException("Please enter a valid state abbreviation.");
        }
        return invoice;
    }

    private Invoice calcTotal(Invoice invoice){
        BigDecimal total = invoice.getSubtotal().add(invoice.getTax()).add(invoice.getProcessingFee());
        invoice.setTotal(total);
        return invoice;
    }

    private Invoice calcProcessingFee(InvoiceViewModel invoiceViewModel, Invoice invoice){
        if(invoiceViewModel.getQuantity() > 10){
            BigDecimal additionalProcessingFee = new BigDecimal("15.49").setScale(2);
            ProcessingFee processingFee = processingFeeDao.getProcessingFee(invoiceViewModel.getItemType());
            BigDecimal finalProcessingFee = processingFee.getFee().add(additionalProcessingFee).setScale(2);
            invoice.setProcessingFee(finalProcessingFee);
        } else {
            ProcessingFee processingFee = processingFeeDao.getProcessingFee(invoiceViewModel.getItemType());
            invoice.setProcessingFee(processingFee.getFee());
        }
        return invoice;
    }

    private Invoice calcTax(InvoiceViewModel invoiceViewModel, Invoice invoice){
        SalesTaxRate getStateRate = salesTaxRateDao.getSalesTaxRate(invoiceViewModel.getState());
        BigDecimal taxRateFinal = (getStateRate.getRate().multiply(invoice.getSubtotal())).setScale(2, RoundingMode.HALF_UP);
        invoice.setTax(taxRateFinal);
        return invoice;
    }

    private Invoice calcSubtotal(InvoiceViewModel invoiceViewModel, Invoice invoice) throws QuantityValidationException{
        Console console = new Console();
        Game game = new Game();
        TShirt tShirt = new TShirt();
        BigDecimal orderQuantity = new BigDecimal(invoiceViewModel.getQuantity()).setScale(2);
        BigDecimal subTotal = null;
        int quantityUpdate = 0;

        switch(invoiceViewModel.getItemType()){
            case "Consoles":
                console = consoleDao.getConsole(invoice.getItemId());
                if(invoiceViewModel.getQuantity() <= console.getQuantity()){
                    quantityUpdate = console.getQuantity() - invoiceViewModel.getQuantity();
                    console.setQuantity(quantityUpdate);
                    consoleDao.updateConsole(console);
                } else {
                    throw new QuantityValidationException("There are not enough consoles in the inventory. Please enter less quantity");
                }
                invoice.setUnitPrice(console.getPrice());
                subTotal = console.getPrice().multiply(orderQuantity).setScale(2);
                invoice.setSubtotal(subTotal);
                break;
            case "Games":
                game = gameDao.getGame(invoice.getItemId());
                if(invoiceViewModel.getQuantity() <= game.getQuantity()){
                    quantityUpdate = game.getQuantity() - invoiceViewModel.getQuantity();
                    game.setQuantity(quantityUpdate);
                    gameDao.updateGame(game);
                } else {
                    throw new QuantityValidationException("There are not enough copies of the game in the inventory. Please enter less quantity");
                }
                invoice.setUnitPrice(game.getPrice());
                subTotal = (game.getPrice().multiply(orderQuantity)).setScale(2);
                invoice.setSubtotal(subTotal);
                break;
            case "T-Shirts":
                tShirt = tShirtDao.getTShirt(invoice.getItemId());
                if(invoiceViewModel.getQuantity() <= tShirt.getQuantity()){
                    quantityUpdate = tShirt.getQuantity() - invoiceViewModel.getQuantity();
                    tShirt.setQuantity(quantityUpdate);
                    tShirtDao.updateTShirt(tShirt);
                } else {
                    throw new QuantityValidationException("There are not enough T-Shirts in the inventory. Please enter less quantity");
                }
                invoice.setUnitPrice(tShirt.getPrice());
                subTotal = tShirt.getPrice().multiply(orderQuantity).setScale(2);
                invoice.setSubtotal(subTotal);
                break;
        }

        return invoice;
    }

    private ConsoleViewModel buildConsoleViewModel(Console console){
        ConsoleViewModel consoleViewModel = new ConsoleViewModel();
        consoleViewModel.setConsoleId(console.getConsoleId());
        consoleViewModel.setModel(console.getModel());
        consoleViewModel.setManufacturer(console.getManufacturer());
        consoleViewModel.setMemoryAmount(console.getMemoryAmount());
        consoleViewModel.setProcessor(console.getProcessor());
        consoleViewModel.setPrice(console.getPrice());
        consoleViewModel.setQuantity(console.getQuantity());

        return consoleViewModel;
    }

    private GameViewModel buildGameViewModel(Game game){
        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setGameId(game.getGameId());
        gameViewModel.setTitle(game.getTitle());
        gameViewModel.setErsbRating(game.getErsbRating());
        gameViewModel.setDescription(game.getDescription());
        gameViewModel.setPrice(game.getPrice());
        gameViewModel.setStudio(game.getStudio());
        gameViewModel.setQuantity(game.getQuantity());

        return gameViewModel;
    }

    private TShirtViewModel buildTShirtViewModel(TShirt tShirt){
        TShirtViewModel tShirtViewModel = new TShirtViewModel();
        tShirtViewModel.settShirtId(tShirt.gettShirtId());
        tShirtViewModel.setSize(tShirt.getSize());
        tShirtViewModel.setColor(tShirt.getColor());
        tShirtViewModel.setDescription(tShirt.getDescription());
        tShirtViewModel.setPrice(tShirt.getPrice());
        tShirtViewModel.setQuantity(tShirt.getQuantity());

        return tShirtViewModel;
    }

    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice){
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setInvoiceId(invoice.getInvoiceId());
        invoiceViewModel.setName(invoice.getName());
        invoiceViewModel.setStreet(invoice.getStreet());
        invoiceViewModel.setCity(invoice.getCity());
        invoiceViewModel.setState(invoice.getState());
        invoiceViewModel.setZipcode(invoice.getZipcode());
        invoiceViewModel.setItemType(invoice.getItemType());
        invoiceViewModel.setItemId(invoice.getItemId());
        invoiceViewModel.setUnitPrice(invoice.getUnitPrice());
        invoiceViewModel.setQuantity(invoice.getQuantity());
        invoiceViewModel.setSubtotal(invoice.getSubtotal());
        invoiceViewModel.setTax(invoice.getTax());
        invoiceViewModel.setProcessingFee(invoice.getProcessingFee());
        invoiceViewModel.setTotal(invoice.getTotal());

        return invoiceViewModel;
    }

}
