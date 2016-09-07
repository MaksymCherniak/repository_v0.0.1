package searcher.dao.impl.PostgreSQL;

import org.apache.log4j.Logger;
import searcher.dao.repository.ContentRepository;
import searcher.dao.service.IContentService;
import searcher.entity.Content;
import searcher.mainClasses.ApplicationContext;

import java.util.List;

public class ContentImpl implements IContentService {
    private static Logger log = Logger.getLogger(ContentImpl.class.getName());
    private ContentRepository contentRepository = ApplicationContext.getCtx().getBean(ContentRepository.class);

    @Override
    public boolean addContent(Content content) {
        if (getByContentAndDate(content.getContent(), content.getCreationDate()) == null) {
            contentRepository.saveAndFlush(content);
            return true;
        }
        log.warn("Failed add to database.\n" + content.toString() + "\nAlready exist\n------------------------------------------------");
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
