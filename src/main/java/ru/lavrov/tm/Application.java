package ru.lavrov.tm;

import ru.lavrov.tm.bootstrap.Bootstrap;

public class Application {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        new Bootstrap().start();
    }
}