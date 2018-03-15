/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.altarix.game.memory;

import java.util.LinkedList;
import java.util.List;
import ru.altarix.game.card.Card;
import ru.altarix.game.gamer.Gamer;
import ru.altarix.game.match.Match;

/**
 *
 * @author chuprin
 */
public class MatchMemory {
    private List<Match> matchList = new LinkedList<>();

    public List<Match> getMatchList() {
        return matchList;
    }
    public void setMatchList(List<Match> matchList) {
        matchList.clear();
        this.matchList = matchList;
    }
    public void addMatchList(Match match) {
        matchList.add(match);
    }
    
    public void getAPI1(int numberMatch){
        if(numberMatch < matchList.size()){
            Match match = matchList.get(numberMatch);
            
            System.err.println("Пeчать раздачи карт №" + numberMatch);
            for(Gamer gamer: match.getGamerList()){
                System.err.println(gamer.getName());
                for(Card card: gamer.getCards()){
                    System.err.println("    ->" + card);
                }
            }
            
            System.err.println("");
            System.err.println("Прикуп");
            for(Card card: match.getOtherCard()){
                System.err.println(card);
            }
            
            System.err.println("");            
            System.err.println("Начинает ход:" + match.getTenderResult().getGamer());
            
            System.err.println("");
        }        
    }
    
    public void getAPI3(int numberMatch){
        if(numberMatch < matchList.size()){
            Match match = matchList.get(numberMatch);
            
            System.err.println("Пeчать заявки №" + numberMatch);
            System.err.println("Игра по формату:");
            System.err.println(match.getTenderResult().getAnswer() + match.getTenderResult().getCardType());
            System.err.println("Начинает ход:" + match.getTenderResult().getGamer());
            
            for(Gamer gamer: match.getGamerList()){
                if(gamer.equals(match.getTenderResult().getGamer()) == false){
                    if(gamer.isInGame()){
                        System.err.println(gamer + " вист");
                    } else {
                        System.err.println(gamer + " пас");
                    }
                        
                }
            }
            System.err.println("");
            
        }
    }
    
    public void getAPI4(int numberMatch){
        if(numberMatch < matchList.size()){
            Match match = matchList.get(numberMatch);
            
            if(match.getRoundMemory().size() == 0){
                System.err.println("Ход не состоялся, все пасс");
            }
            
            int i=1;
            for(RoundMemory rm: match.getRoundMemory()){
                System.err.println("Ход " + i++);
                
                System.err.println("Карты на столе:");
                for(Card card: rm.getCardList()){
                    System.err.println(card);
                }
                System.err.println("Выиграла карта:" + rm.getCardWin());
                System.err.println("Выиграл :" + rm.getGamerWin());
                System.err.println("");
            }
            
        }
    }
}
