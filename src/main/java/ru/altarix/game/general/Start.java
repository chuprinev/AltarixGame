/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.altarix.game.general;

import java.util.List;
import ru.altarix.game.factory.GameFactory;
import ru.altarix.game.factory.SimplePreferanceFactory;
import ru.altarix.game.gamer.Gamer;
import ru.altarix.game.manager.GameManager;
import ru.altarix.game.memory.MatchMemory;
import ru.altarix.game.rule.Rule;

/**
 *
 * @author chuprin
 */
public class Start {
    
    public static void main(String[] arg){
        Rule rule = new Rule();
        List<Gamer> gamerList = rule.getGamerList();        
        
        GameFactory factory = new SimplePreferanceFactory();        
        MatchMemory matchMemory = new MatchMemory();
        for(int i=0;i<rule.getGameRound(); i++){
            matchMemory.addMatchList(factory.getMatch("Simple", rule, gamerList));
        }
        Gamer gamerWin = GameManager.getManager().getGamerWinner(gamerList);        
        
        matchMemory.getAPI1(1);
        matchMemory.getAPI3(1);
        matchMemory.getAPI4(1);        
        
    }
}
