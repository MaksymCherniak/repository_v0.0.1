package Catalog.Entity.Lists;

import Catalog.Entity.Enum.GenreSound;

import java.util.*;

public class ListGenreSound {
    private static List<GenreSound> listOfGenres = new ArrayList<GenreSound>();

    static {
        listOfGenres = Arrays.asList(GenreSound.values());
        Collections.sort(listOfGenres, new Comparator<GenreSound>() {
            public int compare(GenreSound o1, GenreSound o2) {
                return o1.name().compareTo(o2.name());
            }
        });
    }

    public static List<GenreSound> getSoundGenres() {
        return listOfGenres;
    }
}
