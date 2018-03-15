/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.altarix.game.match;

import java.util.List;
import ru.altarix.game.card.Card;
import ru.altarix.game.forecast.Forecast;
import ru.altarix.game.gamer.Gamer;
import ru.altarix.game.memory.RoundMemory;
import ru.altarix.game.rule.Rule;

/**
 *
 * @author chuprin
 */
public interface Match {    
    public void getPreparation(List<Gamer> gamers);
    public void getDistribution();
    public void getBuyIn();
    public void getTender();
    public void getDischarge();
    public void getContract();
    public void getGame();
    public void getResult();    
    
    public List<Gamer> getGamerList();
    public void setGamerList(List<Gamer> gamerList);
    
    public List<Card> getOtherCard();
    public void setOtherCard(List<Card> otherCard);
    
    public Rule getRule();
    public void setRule(Rule rule);
    
    public void setTenderResult(Forecast tenderResult);
    public Forecast getTenderResult();
    
    public List<RoundMemory> getRoundMemory();
    public void setRoundMemory(List<RoundMemory> roundMemory);
    
}