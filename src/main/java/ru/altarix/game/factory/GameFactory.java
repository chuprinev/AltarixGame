/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.altarix.game.factory;

import java.util.List;
import ru.altarix.game.gamer.Gamer;
import ru.altarix.game.match.Match;
import ru.altarix.game.rule.Rule;

/**
 *
 * @author chuprin
 */
public abstract class GameFactory {
    public Match getMatch(String type, Rule rule, List<Gamer> gamer){
        Match match = createMatch(type);
        
        match.setRule(rule);
        match.getPreparation(gamer);
        match.getDistribution();
        match.getBuyIn();
        match.getTender();
        match.getDischarge();
        match.getContract();
        match.getGame();
        match.getResult();                
        
        return match;
    }
    
    public abstract Match createMatch(String type);    
    
}
