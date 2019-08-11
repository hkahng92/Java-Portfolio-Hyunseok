package com.example.U1M4SummativeKahngHyunseok.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PoolOfStrings {

    public static List<Quote> quoteList(){
        List<Quote> quotePool = new ArrayList<>();
        Quote quote1 = new Quote("George Halas","Nobody who ever gave his best regretted it.");
        Quote quote2 = new Quote("Babe Ruth","It’s hard to beat a person who never gives up.");
        Quote quote3 = new Quote("Michael Jordan","I’ve failed over and over and over again in my life. And that is why I succeed.");
        Quote quote4 = new Quote("Vince Lombardi","It’s not whether you get knocked down; it’s whether you get up.");
        Quote quote5 = new Quote("Derek Jeter","There may be people that have more talent than you, but there’s no excuse for anyone to work harder than you do.");
        Quote quote6 = new Quote("Lou Holtz","Without self-discipline, success is impossible, period.");
        Quote quote7 = new Quote("John Wooden","Make each day your masterpiece.");
        Quote quote8 = new Quote("Joe Namath","If you aren’t going all the way, why go at all?");
        Quote quote9 = new Quote("Jack Dempsey","A champion is someone who gets up when he can’t.");
        Quote quote10 = new Quote("Shaquille O’Neal","I’d like to thank everyone who voted for me. And the one guy who didn’t vote for me, thank you, too.");
        Quote quote11 = new Quote("Muhammad Ali","He who is not courageous enough to take risks will accomplish nothing in life.");

        quotePool.add(quote1);
        quotePool.add(quote2);
        quotePool.add(quote3);
        quotePool.add(quote4);
        quotePool.add(quote5);
        quotePool.add(quote6);
        quotePool.add(quote7);
        quotePool.add(quote8);
        quotePool.add(quote9);
        quotePool.add(quote10);
        quotePool.add(quote11);

        return quotePool;
    }

    public static List<Word> wordList(){
        List<Word> wordPool = new ArrayList<>();
        Word word1 = new Word("Bumfuzzle","To confuse someone.");
        Word word2 = new Word("Lollygag","To dawdle or be overly slow.");
        Word word3 = new Word("Cattywampus","Disorganized, when something is not arranged correctly.");
        Word word4 = new Word("Gardyloo","A warning shouted before water or waste is thrown from above.");
        Word word5 = new Word("Billingsgate","Rough language filled with profanity.");
        Word word6 = new Word("Malarkey","Nonsense.");
        Word word7 = new Word("Snickersnee","A large knife.");
        Word word8 = new Word("Nincompoop","A stupid, useless person.");
        Word word9 = new Word("Canoodle","To cuddle or get close to.");
        Word word10 = new Word("Gonzo","Far out journalism.");
        Word word11 = new Word("Cantankerous","Overly argumentative or uncooperative.");

        wordPool.add(word1);
        wordPool.add(word2);
        wordPool.add(word3);
        wordPool.add(word4);
        wordPool.add(word5);
        wordPool.add(word6);
        wordPool.add(word7);
        wordPool.add(word8);
        wordPool.add(word9);
        wordPool.add(word10);
        wordPool.add(word11);

        return wordPool;
    }


    public static String magicBall(){
        List<String> answerPool = new ArrayList<>();
        answerPool.add("It is certain.");
        answerPool.add("Don't count on it.");
        answerPool.add("Yes - definitely.");
        answerPool.add("My sources say no.");
        answerPool.add("Cannot predict now.");
        answerPool.add("Very doubtful.");
        answerPool.add("Outlook good.");
        answerPool.add("Concentrate and ask again.");

        Random random = new Random();
        int rand = random.nextInt(8);

        return answerPool.get(rand);
    }
}
