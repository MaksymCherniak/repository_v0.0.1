package searcher.mainClasses;

import org.springframework.context.support.GenericXmlApplicationContext;

public class ApplicationContext {
    private static GenericXmlApplicationContext ctx;

    static {
        ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/applicationContext.xml");
        ctx.refresh();
    }

    public static GenericXmlApplicationContext getCtx() {
        return ctx;
    }

    public static GenericXmlApplicationContext getCtxForTest() {
        ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/testApplicationContext.xml");
        ctx.refresh();
        return ctx;
    }
}
