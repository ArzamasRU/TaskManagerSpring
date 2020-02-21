package ru.lavrov.tm;

import ru.lavrov.tm.entity.Project;
import ru.lavrov.tm.entity.Task;
import ru.lavrov.tm.service.ProjectService;
import ru.lavrov.tm.service.TaskService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Application {
    public static void main(String[] args) throws Exception {
        Application app = new Application();
        app.start();
    }

    public void start() throws Exception {
        new Bootstrap().run();
    }
}