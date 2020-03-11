package ru.lavrov.tm.command.general;

//public class JAXBJSONLoadCommand {
//    @Override
//    public @NotNull String getName() {
//        return "jaxbjsonload";
//    }
//
//    @Override
//    public @NotNull String getDescription() {
//        return "Load data from Json with JAX-B.";
//    }
//
//    @Override
//    public void execute() throws Exception {
//        System.out.println("--Load data from Json with JAX-B--");
//        final JAXBContext userContext = JAXBContext.newInstance(UserWrapper.class);
//        final Unmarshaller userUnmarshaller = userContext.createUnmarshaller();
//        userUnmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
//        userUnmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);
//        final UserWrapper userWrapper = (UserWrapper) userUnmarshaller.unmarshal(new File("src/main/file/users.json"));
//        for (final User user : userWrapper.getUserList()) serviceLocator.getUserService().merge(user);
//
//        final JAXBContext projectContext = JAXBContext.newInstance(ProjectWrapper.class);
//        final Unmarshaller projectUnmarshaller = projectContext.createUnmarshaller();
//        projectUnmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
//        projectUnmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);
//        final ProjectWrapper projectWrapper = (ProjectWrapper) projectUnmarshaller.unmarshal(new File("src/main/file/projects.json"));
//        for (final Project project : projectWrapper.getProjectList()) serviceLocator.getProjectService().merge(project);
//
//        final JAXBContext taskContext = JAXBContext.newInstance(TaskWrapper.class);
//        final Unmarshaller taskUnmarshaller = taskContext.createUnmarshaller();
//        taskUnmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
//        taskUnmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);
//        final TaskWrapper taskWrapper = (TaskWrapper) taskUnmarshaller.unmarshal(new File("src/main/file/tasks.json"));
//        for (final Task task : taskWrapper.getTaskList()) serviceLocator.getTaskService().merge(task);
//
//        System.out.println("[LOAD COMPLETED]");
//    }
//
//    @NotNull
//    @Override
//    public RoleType[] roles() {
//        return new RoleType[]{RoleType.ANONYMOUS_USER, RoleType.USER, RoleType.ADMIN};
//    }
//
//}
