package Catalog.Entity.Lists;

import Catalog.Entity.Enum.GenreFilm;

import java.util.*;

public class ListGenreFilm {
    private static List<GenreFilm> listOfGenres = new ArrayList<GenreFilm>();

    static {
        listOfGenres = Arrays.asList(GenreFilm.values());
        Collections.sort(listOfGenres, new Comparator<GenreFilm>() {
            public int compare(GenreFilm o1, GenreFilm o2) {
                return o1.name().compareTo(o2.name());
            }
        });
    }

    public static List<GenreFilm> getFilmGenres() {
        return listOfGenres;
    }
}
