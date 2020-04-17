package ru.lavrov.tm;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import ru.lavrov.tm.context.AppContext;
import ru.lavrov.tm.context.WebContext;
import ru.lavrov.tm.context.WebFreeMarkerContext;

public class AppServerInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { AppContext.class};
    }
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebFreeMarkerContext.class };
    }
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}