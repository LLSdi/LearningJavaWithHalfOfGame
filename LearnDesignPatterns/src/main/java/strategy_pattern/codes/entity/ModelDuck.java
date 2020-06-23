package strategy_pattern.codes.entity;

import strategy_pattern.codes.behaviors.impl.FlyNoWay;
import strategy_pattern.codes.behaviors.impl.Quack;
import strategy_pattern.codes.entity.Duck;

/**
 * @author halfOfGame
 * @create 2020-05-22,22:39
 */
public class ModelDuck extends Duck {
    public ModelDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack();
    }

    public void display() {
        System.out.println("I'm a model duck");
    }
}
