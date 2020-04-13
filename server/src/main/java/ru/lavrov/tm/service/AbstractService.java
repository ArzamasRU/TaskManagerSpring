package ru.lavrov.tm.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.lavrov.tm.bootstrap.Bootstrap;

public abstract class AbstractService {

    @Autowired
    protected Bootstrap bootstrap;
}
