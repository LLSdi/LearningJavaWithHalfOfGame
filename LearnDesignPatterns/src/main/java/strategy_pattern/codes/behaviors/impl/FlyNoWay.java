package strategy_pattern.codes.behaviors.impl;

import strategy_pattern.codes.behaviors.inter.FlyBehavior;

/**
 * @author halfOfGame
 * @create 2020-05-22,22:21
 */
public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I can't fly");
    }
}
