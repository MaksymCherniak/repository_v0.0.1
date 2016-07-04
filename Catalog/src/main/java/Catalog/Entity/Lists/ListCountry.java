package Catalog.Entity.Lists;

import java.util.*;

public class ListCountry {
    private static List<String> listOfCountries = new ArrayList<String>();

    static {
        String[] locales = Locale.getISOCountries();
        for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);
            listOfCountries.add(obj.getDisplayCountry(Locale.ENGLISH));
        }
        Collections.sort(listOfCountries, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

    public static List<String> getCountries() {
        return listOfCountries;
    }
}
