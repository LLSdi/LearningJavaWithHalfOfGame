package strategy_pattern.codes.entity;

import strategy_pattern.codes.behaviors.impl.FlyWithWings;
import strategy_pattern.codes.behaviors.impl.Quack;

/**
 * @author halfOfGame
 * @create 2020-05-22,22:42
 */
public class RedHeadDuck extends Duck {

    public RedHeadDuck() {
        flyBehavior = new FlyWithWings();
        quackBehavior = new Quack();
    }

    public void display() {
        System.out.println("I'm a real Red Headed duck");
    }
}
