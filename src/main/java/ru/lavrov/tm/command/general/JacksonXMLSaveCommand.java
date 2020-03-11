package ru.lavrov.tm.command.general;

//public class JacksonXMLSaveCommand {
//    @NotNull
//    @Override
//    public String getName() {
//        return "jacksonxmlsave";
//    }
//
//    @NotNull
//    @Override
//    public String getDescription() {
//        return "Save data to XML with Jackson.";
//    }
//
//    @Override
//    public void execute() throws Exception {
//        System.out.println("--Save data to XML with Jackson--");
//        final XmlMapper mapper = new XmlMapper();
//        mapper.writeValue((new File("src/main/file/usersJackson.xml")), serviceLocator.getUserService().findAll());
//        mapper.writeValue((new File("src/main/file/projectsJackson.xml")), serviceLocator.getProjectService().findAll());
//        mapper.writeValue((new File("src/main/file/tasksJackson.xml")), serviceLocator.getTaskService().findAll());
//        System.out.println("[SAVED]");
//    }
//
//    @NotNull
//    @Override
//    public RoleType[] roles() {
//        return new RoleType[]{RoleType.ANONYMOUS_USER, RoleType.USER, RoleType.ADMIN};
//    }
//
//}
