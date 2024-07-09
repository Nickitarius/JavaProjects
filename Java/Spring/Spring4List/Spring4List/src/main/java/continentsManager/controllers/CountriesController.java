package continentsManager.controllers;

import continentsManager.CountryRepository;
import continentsManager.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/countries")
public class CountriesController {

    @Autowired
    CountryRepository countryRepository;// = new CountryRepository();

    @RequestMapping("continents")
    public String findAllContinents(Model model) {
        List<String> continents = countryRepository.getContinents();
        model.addAttribute("continents", continents);
        model.addAttribute("continent");
        return "home";
    }

    @RequestMapping("/{continentName}")
    public String showContinent(@PathVariable("continentName") String continentName,
                                Model model) {
        //String continent = countryRepository.getContinents().;
        if (continentName != null) {
            model.addAttribute("continent", continentName);
            return "continent";
        } else {
            return "error";
        }
    }

    @RequestMapping("/continent")
    public String findCountriesOnContinent(Model model,
                                           @PathVariable("continentName") String continentName) {
        List<Country> countries = countryRepository.findByContinent(continentName);
        if (countries != null) {
            model.addAttribute("countries", countries);
            return "continent";
        } else {
            return "error";
        }
    }

//    @RequestMapping("/{code}")
//    public String showAirline(@PathVariable("code") String code,
//                              Model model) {
//        Country country = countryRepository.findByCode(code);
//        if (country != null) {
//            model.addAttribute("country", country);
//            return "country";
//        } else {
//            return "error";
//        }
//    }


}
