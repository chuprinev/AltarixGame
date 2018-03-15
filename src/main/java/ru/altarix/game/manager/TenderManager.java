/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.altarix.game.manager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import ru.altarix.game.card.Card;
import ru.altarix.game.gamer.Gamer;
import ru.altarix.game.forecast.Forecast;
import ru.altarix.game.match.SimpleMatch;
import ru.altarix.game.rule.Rule;

/**
 *
 * @author chuprin
 */
public class TenderManager {
    private static TenderManager manager = null;    
    private TenderManager(){
    }    
    public static TenderManager getManager(){
        if(manager == null){
            manager = new TenderManager();
        }        
        return manager;
    }
    
    public void setTender(SimpleMatch match){
        for(Gamer gamer: match.getGamerList()){            
            Forecast max = setGamerTender(gamer, match.getRule());
            gamer.setForecast(max);
        }
    }
        public Forecast setGamerTender(Gamer gamer, Rule rule){
            Map<String, List<Card>> cardTypeMap = getCardTypeMap(gamer, rule);            
            List<Forecast> forecastMatch = getForecast(cardTypeMap, rule);            
            Forecast max = getForecastMax(forecastMatch);
            
            return max;
        }
            private Map<String, List<Card>> getCardTypeMap(Gamer gamer, Rule rule){
                Map<String, List<Card>> cardTypeMap = new HashMap<>();
                
                for(String type: rule.getCardTypeList()){
                    List<Card> cardList = new LinkedList<>();
                    for(Card card: gamer.getCards()){
                        if(type.equals(card.getType())){
                            cardList.add(card);
                        }
                    }

                    if(cardList.isEmpty() == false){
                        cardTypeMap.put(type, cardList);
                    }
                }                
                return cardTypeMap;
            }
            private List<Forecast> getForecast(Map<String, List<Card>> cardTypeMap, Rule rule){
                List<Forecast> forecastMatch = new LinkedList<>();            
                
                for(String type: rule.getCardTypeList()){
                    List<Card> cardTypeList = cardTypeMap.get(type);
                    if(cardTypeList == null){
                        continue;
                    }

                    List<Integer> cardWeightList = new LinkedList<>(rule.getWightCardList());                
                    for(Card card: cardTypeList){
                        Iterator<Integer> it = cardWeightList.iterator();
                        while(it.hasNext()){
                            if(it.next().equals(card.getWight())){
                                it.remove();
                            }
                        }
                    }

                    Integer cardLose = 0;
                    for(Card card: cardTypeList){
                        Iterator<Integer> it = cardWeightList.iterator();
                        while(it.hasNext()){
                            if(card.getWight() < it.next()){
                                cardLose++;
                                it.remove();
                                break;
                            }
                        }
                    }

                    int cardWin = cardTypeList.size()-cardLose;
                    int superCardLost = cardTypeList.size() * rule.getGamerCount();
                    if(superCardLost > rule.getCardNameList().size()){
                        superCardLost = rule.getCardNameList().size();
                    }
                    int superCardInGame = rule.getCardNameList().size() - superCardLost;

                    forecastMatch.add(new Forecast(type, cardWin, superCardInGame));
                }
                
                return forecastMatch;
            }
            
        private Forecast getForecastMax(List<Forecast> forecastMatch){
            Forecast max = new Forecast();
            Integer cardWin = 0;
            
            for(Forecast forecast: forecastMatch){
                if(max.getCardWin() < forecast.getCardWin()){
                    max = forecast;
                }
                cardWin = cardWin + forecast.getCardWin();            
            }            

            cardWin = cardWin - max.getSuperCardInGame();            
            max.setCardWin(cardWin);

            return max;
        }

    public Forecast getTenderResult(SimpleMatch match){
        Forecast forecastMax = new Forecast();
        
        for(Gamer gamer: match.getGamerList()){
            Forecast forecast = gamer.getForecast();
            
            if(match.getRule().isCompare(forecast, forecastMax) > 0){
                forecastMax = forecast;
                forecastMax.setGamer(gamer);
            }
        }
        return forecastMax;
    }

}

