/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.altarix.game.manager;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import ru.altarix.game.card.Card;
import ru.altarix.game.gamer.Gamer;
import ru.altarix.game.match.Match;
import ru.altarix.game.match.SimpleMatch;
import ru.altarix.game.memory.RoundMemory;
import ru.altarix.game.rule.Rule;

/**
 *
 * @author chuprin
 */
public class GameManager {
    private static GameManager manager = null;    
    private GameManager(){
    }    
    public static GameManager getManager(){
        if(manager == null){
            manager = new GameManager();
        }        
        return manager;
    }
    
    
    public void getStart(SimpleMatch match){        
        CardManager.getManager().setSuperTypeCard(match);        
        
        int wispContract = match.getRule().getWisp(match.getTenderResult().getAnswer());
        
        for(Gamer gamer: match.getGamerList()){
            if(match.getTenderResult().getGamer().isInGame()){
                if(gamer.isPrimary() == false){                    
                    if(gamer.getForecast().getAnswer() < wispContract){
                        gamer.setInGame(false);
                    }
                }
            } else {
                gamer.setInGame(false);
            }                
        }
        
        Gamer gamerPrimary = null;
        List<Gamer> gamerPlay = new LinkedList<>();
        List<Gamer> gamerPass = new LinkedList<>();
        for(Gamer gamer: match.getGamerList()){
            if(gamer.isInGame()){
                if(gamer.isPrimary()){
                    gamerPrimary = gamer;
                }
                gamerPlay.add(gamer);
            } else {
                gamerPass.add(gamer);
            }
        }
        
        
        if(gamerPrimary == null){
            return;
        }
        // REFACTORING: тут ведущий игрок победил        
        if(gamerPass.size() == match.getRule().getGamerCount()){            
            return;
        }
        
        List<RoundMemory> roundMemory  = getRound(gamerPlay, gamerPrimary, match.getRule());
        Collections.reverse(roundMemory);
        int num = 1;
        for(RoundMemory rm: roundMemory){
            rm.setRound(num++);
        }
        match.setRoundMemory(roundMemory);
        
    }
        private List<RoundMemory> getRound(List<Gamer> gamerList, Gamer startGamer, Rule rule){                       
            Card card = CardManager.getManager().getStrongestCard(startGamer.getCards(), rule, false);            
            if(card == null){
                return new LinkedList<>();
            }
            card.setGamer(startGamer);
            card.setDischarge(true);
            //System.err.println("    -> " + startGamer.getName() + " card open   " + card);
            
            List<Card> cardInGameList = new LinkedList<>();
            cardInGameList.add(card);
            
            for(Gamer gamer: gamerList){
                if(gamer.equals(startGamer) == false){
                    Card cardAnswer = CardManager.getManager().getStrongestCard(gamer.getCards(), card, rule);
                    if(cardAnswer == null){
                        cardAnswer = CardManager.getManager().getWeakestCard(gamer.getCards(), card.getType(), rule);
                    }
                    if(cardAnswer == null){
                        cardAnswer = CardManager.getManager().getWeakestCard(gamer.getCards(), rule);
                    }
                    cardAnswer.setGamer(gamer);
                    cardAnswer.setDischarge(true);
                    cardInGameList.add(cardAnswer);
                    //System.err.println("    -> " + gamer.getName() + " card answer " + cardAnswer);
                }
            }
            Card cardWineer = CardManager.getManager().getStrongestCard(cardInGameList, rule, true);
            //System.err.println("win " + cardWineer.getGamer().getName());
            
            cardWineer.getGamer().addScoreRound();
            
            RoundMemory rm = new RoundMemory();
            rm.setGamerList(new LinkedList<>(gamerList));
            rm.setCardList(new LinkedList<>(cardInGameList));
            rm.setCardWin(cardWineer);
            rm.setGamerWin(cardWineer.getGamer());
            
            List<RoundMemory> roundMemoryList = getRound(gamerList, cardWineer.getGamer(), rule);
            roundMemoryList.add(rm);
            
            return roundMemoryList;
        }
            
    public void getMatchResult(Match match){
        match.getRoundMemory();
        
        int tender = match.getTenderResult().getAnswer();
        int wisp = match.getRule().getWisp(tender);
        
        for(Gamer gamer: match.getGamerList()){
            if(gamer.isInGame()){
                if(gamer.isPrimary()){
                    if(gamer.getScoreRound() >= tender){
                        gamer.addScoreGame(3);
                    }
                } else {
                    if(gamer.getScoreRound() >= wisp){
                        gamer.addScoreGame(1);
                    }
                }
            }   
        }
    }
    
    public Gamer getGamerWinner(List<Gamer> gamerList){
        Gamer gamerWin = null;
        for(Gamer gamer: gamerList){
            if(gamerWin == null){
                gamerWin = gamer;
            }
            
            if(gamerWin.getScoreGame() < gamer.getScoreGame()){
                gamerWin = gamer;
            }            
            //System.err.println(gamer + " getScoreGame = " + gamer.getScoreGame());
        }
        
        if(gamerWin.getScoreGame() == 0){
            gamerWin = null;
        }
        
        return gamerWin;
    }
        
}
