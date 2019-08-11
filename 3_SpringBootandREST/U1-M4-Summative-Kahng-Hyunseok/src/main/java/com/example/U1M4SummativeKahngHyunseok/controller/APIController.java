package com.example.U1M4SummativeKahngHyunseok.controller;

import com.example.U1M4SummativeKahngHyunseok.model.Answer;
import com.example.U1M4SummativeKahngHyunseok.model.PoolOfStrings;
import com.example.U1M4SummativeKahngHyunseok.model.Quote;
import com.example.U1M4SummativeKahngHyunseok.model.Word;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Random;

@RestController
public class APIController {
    Random random = new Random();

    //getting the QUOTE of the day
    @RequestMapping(value = "/quote", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Quote quoteOfTheDay(){
        int randnum = random.nextInt(11);
        return PoolOfStrings.quoteList().get(randnum);
    }
    //getting the WORD of the day
    @RequestMapping(value = "/word", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Word wordOfTheDay(){
        int randnum = random.nextInt(11);
        return PoolOfStrings.wordList().get(randnum);
    }
    //8 Ball Magic
    @RequestMapping(value = "/magic", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Answer magicBall(@RequestBody @Valid Answer question){
        question.setAnswer(PoolOfStrings.magicBall());
        return question;
    }




}
