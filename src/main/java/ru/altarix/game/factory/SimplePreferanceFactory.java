/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.altarix.game.factory;

import ru.altarix.game.match.Match;
import ru.altarix.game.match.SimpleMatch;

/**
 *
 * @author chuprin
 */
public class SimplePreferanceFactory extends GameFactory{

    @Override
    public Match createMatch(String type) {
        Match match = null;
        
        if(type.equals("Simple")){
            match = new SimpleMatch();
        }
        
        return match;
    }    
}
