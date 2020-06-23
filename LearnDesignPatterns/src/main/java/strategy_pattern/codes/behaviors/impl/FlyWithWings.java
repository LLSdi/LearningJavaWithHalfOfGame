package strategy_pattern.codes.behaviors.impl;

import strategy_pattern.codes.behaviors.inter.FlyBehavior;

/**
 * @author halfOfGame
 * @create 2020-05-22,22:20
 */
public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I'm flying!!");
    }
}
