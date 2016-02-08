package BookingTool.Model.LocalModel;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Created by Max on 08.02.2016.
 */
public class ContextInit {
    private static GenericXmlApplicationContext ctx;

    static {
        ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/springConfig.xml");
        ctx.refresh();
    }

    private ContextInit() {
    }

    public static GenericXmlApplicationContext getContext() {
        return ctx;
    }
}
