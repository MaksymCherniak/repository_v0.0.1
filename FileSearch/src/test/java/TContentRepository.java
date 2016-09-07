import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import searcher.dao.repository.ContentRepository;
import searcher.entity.Content;
import searcher.mainClasses.ApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/testApplicationContext.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class
        , TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class TContentRepository {
    private Content content;

    private ContentRepository contentRepository = ApplicationContext.getCtxForTest().getBean(ContentRepository.class);

    @Before
    public void initialize() {
        content = new Content();
        content.setContent("content");
        content.setCreationDate("date");
        contentRepository.saveAndFlush(content);
    }

    @Test
    public void noUserEntriesFound() {
        content = contentRepository.getByContentAndDate("content", "date");
        assertNull("Content which isn't in database exist", content);
    }

    @Test
    public void userEntryFound() {
        content = contentRepository.getByContentAndDate("content", "date");
        assertNotNull("Content not found", content);
    }
}
