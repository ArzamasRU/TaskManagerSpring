package ru.lavrov.tm.command.general;

//public class JacksonJsonLoadCommand {
//    @NotNull
//    @Override
//    public String getName() {
//        return "jacksonjsonload";
//    }
//
//    @NotNull
//    @Override
//    public String getDescription() {
//        return "Load data from Json with Jackson.";
//    }
//
//    @Override
//    public void execute() throws Exception {
//        System.out.println("--Load data from Json with Jackson--");
//        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
//        @NotNull final Iterator<User> userIterator = objectMapper.readerFor(User.class).readValues(new File("src/main/file/usersJackson.json"));
//        while (userIterator.hasNext()) serviceLocator.getUserService().merge(userIterator.next());
//
//        @NotNull final Iterator<Project> projectIterator = objectMapper.readerFor(Project.class).readValues(new File("src/main/file/projectsJackson.json"));
//        while (projectIterator.hasNext()) serviceLocator.getProjectService().merge(projectIterator.next());
//
//        @NotNull final Iterator<Task> taskIterator = objectMapper.readerFor(Task.class).readValues(new File("src/main/file/tasksJackson.json"));
//        while (taskIterator.hasNext()) serviceLocator.getTaskService().merge(taskIterator.next());
//        System.out.println("[LOAD COMPLETED]");
//    }
//
//    @NotNull
//    @Override
//    public RoleType[] roles() {
//        return new RoleType[]{RoleType.ANONYMOUS_USER, RoleType.USER, RoleType.ADMIN};
//    }
//}
