import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import searcher.dao.service.IContentService;
import searcher.entity.Content;
import searcher.mainClasses.ApplicationContext;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/testApplicationContext.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class
        , TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class TContentService {
    private Content content;

    private IContentService iContentService = ApplicationContext.getCtxForTest().getBean("IContentService", IContentService.class);

    @Before
    public void initialize() {
        content = new Content();
        content.setContent("content");
        content.setCreationDate("25.01.2001");
    }

    @After
    public void deleteUser() {
        iContentService.removeContent(content.getId());
    }

    @Test
    public void addDuplicatedContentIsImpossible_contentDoesNotExistAfterDelete() {
        iContentService.addContent(content);

        assertFalse("Duplicated content added", iContentService.addContent(content));

        iContentService.removeContent(content.getId());

        assertNull("Content exists after remove", iContentService.getContentById(content.getId()));
    }

    @Test
    public void whenAddTheContentExists_getContentById() {
        assertNull("Content exists before added", iContentService.getContentById(content.getId()));

        iContentService.addContent(content);

        assertNotNull("Content does not exist after added", iContentService.getContentById(content.getId()));

        iContentService.removeContent(content.getId());
    }
}
