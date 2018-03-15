/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.altarix.game.match;

import java.util.LinkedList;
import java.util.List;
import ru.altarix.game.card.Card;
import ru.altarix.game.gamer.Gamer;
import ru.altarix.game.forecast.Forecast;
import ru.altarix.game.manager.CardManager;
import ru.altarix.game.manager.GameManager;
import ru.altarix.game.manager.TenderManager;
import ru.altarix.game.memory.RoundMemory;
import ru.altarix.game.rule.Rule;

/**
 *
 * @author chuprin
 */
public class SimpleMatch implements Match{
    private Rule rule = new Rule();
    private List<Gamer> gamerList;
    private List<Card> otherCard;
    private Forecast tenderResult;
    
    private List<RoundMemory> roundMemory = new LinkedList<>();
    
    public SimpleMatch(){
        super();
    }
    
    public void getPreparation(List<Gamer> gamers){
        gamerList = gamers;
        
        for(Gamer gamer: gamerList){            
            gamer.setInGame(true);
            gamer.setPrimary(false);
            gamer.setStartStep(false);
            gamer.setScoreRound(0);
            gamer.setForecast(new Forecast());
        }
    }
    public void getDistribution(){
        CardManager.getManager().setCards(this);
    }
    public void getBuyIn(){
        TenderManager.getManager().setTender(this);
    }
    public void getTender(){
        tenderResult = TenderManager.getManager().getTenderResult(this);
        tenderResult.getGamer().setPrimary(true);
        tenderResult.getGamer().setStartStep(true);
        
        //System.err.println("tenderResult.getAnswer() = " + tenderResult.getAnswer());
        int wispContract = getRule().getWisp(tenderResult.getAnswer());
        if(wispContract == 0){
            tenderResult.getGamer().setInGame(false);
        }        
    }
    public void getDischarge(){
        CardManager.getManager().addCardGamer(tenderResult.getGamer(), otherCard);
        CardManager.getManager().getGamerCardDischarge(tenderResult.getGamer(), rule);
    }
    public void getContract(){
        TenderManager.getManager().setGamerTender(tenderResult.getGamer(), rule);
    }
    public void getGame(){
        GameManager.getManager().getStart(this);
    }
    public void getResult(){
        GameManager.getManager().getMatchResult(this);
    }    
    
    
    
    
    public List<Gamer> getGamerList() {
        return gamerList;
    }
    public void setGamerList(List<Gamer> gamerList) {
        this.gamerList = gamerList;
    }

    public List<Card> getOtherCard() {
        return otherCard;
    }
    public void setOtherCard(List<Card> otherCard) {
        this.otherCard = otherCard;
    }

    public Rule getRule() {
        return rule;
    }
    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public void setTenderResult(Forecast tenderResult) {
        this.tenderResult = tenderResult;
    }
    public Forecast getTenderResult() {
        return tenderResult;
    }

    public List<RoundMemory> getRoundMemory() {
        return roundMemory;
    }
    public void setRoundMemory(List<RoundMemory> roundMemory) {
        this.roundMemory = roundMemory;
    }
    
    
    
    
}
