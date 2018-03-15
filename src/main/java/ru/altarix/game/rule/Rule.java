/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.altarix.game.rule;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import ru.altarix.game.card.Card;
import ru.altarix.game.card.SimpleCard;
import ru.altarix.game.forecast.Forecast;
import ru.altarix.game.gamer.Gamer;
import ru.altarix.game.gamer.SimpleGamer;
import ru.altarix.game.match.Match;
import ru.altarix.game.memory.RoundMemory;

/**
 *
 * @author chuprin
 */
public class Rule {
    private int countCardsInHand = 10;
    private int countCardsInGame = 32;
    private int countCardsInDischarge = 2;
    private int gamerCount = 3;
    private int gameRound = 5;    
    
    private List<String> cardNameList = Arrays.asList("7","8","9","10","Валет","Дама","Король","Туз");
    private List<String> cardTypeList = Arrays.asList("Пики","Крести","Буби","Черви");

    public int getCountCardsInHand() {
        return countCardsInHand;
    }
    public void setCountCardsInHand(int countCardsInHand) {
        this.countCardsInHand = countCardsInHand;
    }

    public int getCountCardsInGame() {
        return countCardsInGame;
    }
    public void setCountCardsInGame(int countCardsInGame) {
        this.countCardsInGame = countCardsInGame;
    }

    public int getCountCardsInDischarge() {
        return countCardsInDischarge;
    }
    public void setCountCardsInDischarge(int countCardsInDischarge) {
        this.countCardsInDischarge = countCardsInDischarge;
    }   

    public int getGamerCount() {
        return gamerCount;
    }
    public void setGamerCount(int gamerCount) {
        this.gamerCount = gamerCount;
    }
    
    public Card getEmptyCard(){
        return new SimpleCard();
    }
    public List<Gamer> getGamerList(){
        List<Gamer> gamerList = new LinkedList<>();
        for(int i=0;i<getGamerCount(); i++){
            gamerList.add(new SimpleGamer("Игрок №" + i));            
        }        
        return gamerList;
    }
        
    public int getGameRound() {
        return gameRound;
    }
    public void setGameRound(int gameRound) {
        this.gameRound = gameRound;
    }    
    
    public List<String> getCardNameList(){        
        return cardNameList;
    }
    public List<String> getCardTypeList(){        
        return cardTypeList;
    }
    public int getWightCard(String name){
        return cardNameList.indexOf(name);
    }
    public List<Integer> getWightCardList(){
        List<Integer> wightList = new LinkedList<>();
        Integer number = 0;
        for(String name: cardNameList){
            wightList.add(number++);
        }
        return wightList;
    }
    
    public int getWisp(int answer ){
        int contract = 0;
        
        switch(answer){
            case 6:{
                contract = 4 / (gamerCount-1);
                break;
            }
            case 7:{
                contract = 2 / (gamerCount-1);
                break;
            }
            case 8:{
                contract = 1;
                break;
            }
            case 9:{
                contract = 1;
                break;
            }
            case 10:{
                contract = 1;
                break;
            }
        }
        
        return contract;
    }
    
    public int isCompare(Forecast one, Forecast two){        
        if(one.getAnswer() > two.getAnswer()){
            return 1;
        }
        if(one.getAnswer() < two.getAnswer()){
            return -1;
        }
        
        int oneRateType = getCardTypeList().indexOf(one.getCardType());
        int twoRateType = getCardTypeList().indexOf(two.getCardType());
        
        if(oneRateType > twoRateType){
            return 1;
        }
        if(oneRateType < twoRateType){
            return -1;
        }
        
        return 0;
    }
        
    public int isStrongerCard(Card one, Card two){
        if(one.isSuperCard()){
            if(two.isSuperCard()){
                int oneRate = getCardNameList().indexOf(one.getName());
                int twoRate = getCardNameList().indexOf(two.getName());

                if(oneRate > twoRate){
                    return 1;
                }
                return -1;
            }
        }

        if(one.isSuperCard()){
            return 1;
        }
        if(two.isSuperCard()){
            return -1;
        }

        if(one.getType().equals(two.getType())){
            int oneRate = getCardNameList().indexOf(one.getName());
            int twoRate = getCardNameList().indexOf(two.getName());

            if(oneRate > twoRate){
                return 1;
            }
            return -1;
        }

        return 0;
    }

    public void setScore(Match match){
        
        for(RoundMemory rm: match.getRoundMemory()){
            rm.getGamerWin();
        }
        
        
        
    }
}
