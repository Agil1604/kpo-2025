package hse.kpo.interfaces;

import hse.kpo.domains.Car;
import hse.kpo.domains.Catamaran;

public abstract class AbstractFactory {
    public abstract Car createHandCar();
    public abstract Catamaran createCatamaran();

}
