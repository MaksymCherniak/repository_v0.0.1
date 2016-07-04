package Catalog.Entity.Lists;

import Catalog.Entity.Enum.GenreBook;

import java.util.*;

public class ListGenreBook {
    private static List<GenreBook> listOfGenres = new ArrayList<GenreBook>();

    static {
        listOfGenres = Arrays.asList(GenreBook.values());
        Collections.sort(listOfGenres, new Comparator<GenreBook>() {
            public int compare(GenreBook o1, GenreBook o2) {
                return o1.name().compareTo(o2.name());
            }
        });
    }

    public static List<GenreBook> getBookGenres() {
        return listOfGenres;
    }
}
