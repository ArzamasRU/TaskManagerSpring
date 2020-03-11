package ru.lavrov.tm.command.general;

//public class JAXBJSONSaveCommand {
//    @NotNull
//    @Override
//    public String getName() {
//        return "jaxbjsonsave";
//    }
//
//    @NotNull
//    @Override
//    public String getDescription() {
//        return "Save data to Json with JAX-B.";
//    }
//
//    @Override
//    public void execute() throws Exception {
//        System.out.println("--Save data to Json with JAX-B--");
//        final JAXBContext userContext = JAXBContext.newInstance(UserWrapper.class);
//        final Marshaller userMarshaller = userContext.createMarshaller();
//        final UserWrapper userWrapper = new UserWrapper();
//        userWrapper.setUserList(serviceLocator.getUserService().findAll());
//        userMarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
//        userMarshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
//        userMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        userMarshaller.marshal(userWrapper, new File("src/main/file/users.json"));
//
//        final JAXBContext projectContext = JAXBContext.newInstance(ProjectWrapper.class);
//        final Marshaller projectMarshaller = projectContext.createMarshaller();
//        final ProjectWrapper projectWrapper = new ProjectWrapper();
//        projectWrapper.setProjectList(serviceLocator.getProjectService().findAll());
//        projectMarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
//        projectMarshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
//        projectMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        projectMarshaller.marshal(projectWrapper, new File("src/main/file/projects.json"));
//
//        final JAXBContext taskContext = JAXBContext.newInstance(TaskWrapper.class);
//        final Marshaller taskMarshaller = taskContext.createMarshaller();
//        final TaskWrapper taskWrapper = new TaskWrapper();
//        taskWrapper.setTaskList(serviceLocator.getTaskService().findAll());
//        taskMarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
//        taskMarshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
//        taskMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        taskMarshaller.marshal(taskWrapper, new File("src/main/file/tasks.json"));
//
//        System.out.println("[SAVED]");
//    }
//
//    @NotNull
//    @Override
//    public RoleType[] roles() {
//        return new RoleType[]{RoleType.ANONYMOUS_USER, RoleType.USER, RoleType.ADMIN};
//    }
//}
