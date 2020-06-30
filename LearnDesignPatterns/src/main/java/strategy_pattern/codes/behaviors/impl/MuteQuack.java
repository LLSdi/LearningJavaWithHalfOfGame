package strategy_pattern.codes.behaviors.impl;

import strategy_pattern.codes.behaviors.QuackBehavior;

/**
 * @author halfOfGame
 * @create 2020-05-22,22:23
 */
public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("<< Silence >>");
    }
}
