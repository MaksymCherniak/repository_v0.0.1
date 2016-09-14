package searcher.dao.impl.PostgreSQL;

import org.apache.log4j.Logger;
import searcher.dao.repository.ContentRepository;
import searcher.dao.service.IContentService;
import searcher.entity.Content;
import searcher.entity.ContentListWrapper;
import searcher.mainClasses.ApplicationContext;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class ContentImpl implements IContentService {
    private static Logger log = Logger.getLogger(ContentImpl.class.getName());
    private ContentRepository contentRepository = ApplicationContext.getCtx().getBean(ContentRepository.class);

    @Override
    public boolean addContent(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(ContentListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            ContentListWrapper wrapper = (ContentListWrapper) um.unmarshal(file);

            List<Content> contentList = wrapper.getContents();

            if (contentList != null) {
                System.out.println("------------------------------------------------\n" + file.getAbsoluteFile().toString() + " found\n");
                for (Content content : contentList) {
                    if (getByContentAndDate(content.getContent(), content.getCreationDate()) == null) {
                        contentRepository.saveAndFlush(content);
                        log.info("\n" + content.toString() + "\nAdded to database\n------------------------------------------------");
                    } else {
                        log.warn("Failed add to database.\n" + content.toString() + "\nAlready exist\n------------------------------------------------");
                    }
                }
                return true;
            }
        } catch (Exception e) {
            log.warn("Error. Could not load data from file:\n" + file.getPath());
        }

        return false;
    }

    @Override
    public List<Content> getAll() {
        return contentRepository.findAll();
    }

    @Override
    public Content getContentById(long id) {
        return contentRepository.getOne(id);
    }

    @Override
    public void removeContent(long id) {
        contentRepository.delete(id);
    }

    @Override
    public Content getByContentAndDate(String content, String date) {
        return contentRepository.getByContentAndDate(content, date);
    }
}
