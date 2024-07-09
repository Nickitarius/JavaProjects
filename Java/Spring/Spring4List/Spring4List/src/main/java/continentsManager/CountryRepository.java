package continentsManager;

import continentsManager.model.Country;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Repository
public class CountryRepository {

    private List<Country> source;

    public CountryRepository() {
        this.source = new LinkedList(Arrays.asList(
                new Country[]{
                        new Country("NLD", "Netherlands", "Europe", 41526.00, 15864000),
                        new Country("ALB", "Albania", "Europe", 28748.00, 3401200),
                        new Country("AND", "Andorra", "Europe", 468.00, 78000),
                        new Country("AFG", "Afghanistan", "Asia", 652090.00, 22720000),
                        new Country("ARE", "United Arab Emirates", "Asia", 83600.00, 2441000),
                        new Country("ARM", "Armenia", "Asia", 29800.00, 3520000),
                        new Country("ANT", "Netherlands Antilles", "North America", 800.00, 217000),
                        new Country("AIA", "Anguilla", "North America", 96.00, 8000),
                        new Country("ATG", "Antigua and Barbuda", "North America", 442.00, 68000),
                        new Country("DZA", "Algeria", "Africa", 2381741.00, 31471000),
                        new Country("AGO", "Angola", "Africa", 1246700.00, 12878000),
                        new Country("BEN", "Benin", "Africa", 112622.00, 6097000),
                        new Country("ASM", "American Samoa", "Oceania", 199.00, 68000),
                        new Country("AUS", "Australia", "Oceania", 7741220.00, 18886000),
                        new Country("COK", "Cook Islands", "Oceania", 236.00, 20000),
                        new Country("ARG", "Argentina", "South America", 2780400.00, 37032000),
                        new Country("BOL", "Bolivia", "South America", 1098581.00, 8329000),
                        new Country("BRA", "Brazil", "South America", 8547403.00, 170115000),
                        new Country("CHL", "Chile", "South America", 756626.00, 15211000),
                        new Country("ECU", "Ecuador", "South America", 283561.00, 12646000),
                        new Country("FLK", "Falkland Islands", "South America", 12173.00, 2000),
                        new Country("GUY", "Guyana", "South America", 214969.00, 861000),
                        new Country("COL", "Colombia", "South America", 1138914.00, 42321000),
                        new Country("PRY", "Paraguay", "South America", 406752.00, 5496000),
                        new Country("PER", "Peru", "South America", 1285216.00, 25662000),
                        new Country("GUF", "French Guiana", "South America", 90000.00, 181000),
                        new Country("SUR", "Suriname", "South America", 163265.00, 417000),
                        new Country("URY", "Uruguay", "South America", 175016.00, 3337000),
                        new Country("VEN", "Venezuela", "South America", 912050.00, 24170000)
                }));
    }


    public List<String> getContinents() {
        return new LinkedList(Arrays.asList(
                new String[]{
                        "Asia", "Europe", "North America", "Africa", "Oceania", "Antarctica", "South America"
                }));

    }

    public List<Country> findByContinent(String name) {
        List<Country> result = new LinkedList<Country>();
        for (Country c : this.source) {
            if (c.getContinent().equals(name)) {
                result.add(c);
            }
        }
        return result;
    }

    public Country findByCode(String code) {
        for (Country c : this.source) {
            if (c.getCode().equals(code))
                return c;
        }
        return null;
    }

    public Country findByName(String name) {
        for (Country c : this.source) {
            if (c.getName().equals(name))
                return c;
        }
        return null;
    }

}