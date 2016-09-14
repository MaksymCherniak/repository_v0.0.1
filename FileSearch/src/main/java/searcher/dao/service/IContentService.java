package searcher.dao.service;

import searcher.entity.Content;

import java.io.File;
import java.util.List;

public interface IContentService {
    boolean addContent(File file);

    List<Content> getAll();

    Content getContentById(long id);

    void removeContent(long id);

    Content getByContentAndDate(String content, String date);
}
