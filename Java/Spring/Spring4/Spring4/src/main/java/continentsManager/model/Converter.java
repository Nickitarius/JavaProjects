package continentsManager.model;

public class Converter implements org.springframework.core.convert.converter.Converter<String, Country> {
    @Override
    public Country convert(String countryCode) {
        CountryRepository cr = new CountryRepository();
        Country country = cr.findByCode(countryCode);
        return country;
    }
}
