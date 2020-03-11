package ru.lavrov.tm.command.general;

//public class JacksonXMLLoadCommand {
//    @NotNull
//    @Override
//    public String getName() {
//        return "jacksonxmlload";
//    }
//
//    @NotNull
//    @Override
//    public String getDescription() {
//        return "Load data from XML with Jackson.";
//    }
//
//    @NotNull
//    @Override
//    public void execute() throws Exception {
//        System.out.println("--Load data from XML with Jackson--");
//        final JacksonXmlModule module = new JacksonXmlModule();
//        module.setDefaultUseWrapper(false);
//        final XmlMapper xmlMapper = new XmlMapper(module);
//        final Iterator<User> userIterator = xmlMapper.readerFor(User.class).readValues(new File("src/main/file/usersJackson.xml"));
//        while (userIterator.hasNext()) serviceLocator.getUserService().merge(userIterator.next());
//
//        final Iterator<Project> projectIterator = xmlMapper.readerFor(Project.class).readValues(new File("src/main/file/projectsJackson.xml"));
//        while (projectIterator.hasNext()) serviceLocator.getProjectService().merge(projectIterator.next());
//
//        final Iterator<Task> taskIterator = xmlMapper.readerFor(Task.class).readValues(new File("src/main/file/tasksJackson.xml"));
//        while (taskIterator.hasNext()) serviceLocator.getTaskService().merge(taskIterator.next());
//
//        System.out.println("[LOAD COMPLETED]");
//    }
//
//    @NotNull
//    @Override
//    public RoleType[] roles() {
//        return new RoleType[]{RoleType.ANONYMOUS_USER, RoleType.USER, RoleType.ADMIN};
//    }
//}
