package ru.lavrov.tm.command.general;

//public class JacksonJsonSaveCommand {
//    @NotNull
//    @Override
//    public String getName() {
//        return "jacksonjsonsave";
//    }
//
//    @NotNull
//    @Override
//    public String getDescription() {
//        return "Save data to Json with Jackson.";
//    }
//
//    @Override
//    public void execute() throws Exception {
//        System.out.println("--Save data to Json with Jackson--");
//        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.writeValue(new File("src/main/file/usersJackson.json"), serviceLocator.getUserService().findAll());
//        objectMapper.writeValue((new File("src/main/file/projectsJackson.json")), serviceLocator.getProjectService().findAll());
//        objectMapper.writeValue((new File("src/main/file/tasksJackson.json")), serviceLocator.getTaskService().findAll());
//        System.out.println("[SAVED]");
//    }
//
//    @NotNull
//    @Override
//    public RoleType[] roles() {
//        return new RoleType[]{RoleType.ANONYMOUS_USER, RoleType.USER, RoleType.ADMIN};
//    }
//}
