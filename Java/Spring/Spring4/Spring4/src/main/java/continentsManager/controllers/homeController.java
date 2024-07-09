package continentsManager.controllers;

import continentsManager.errorHandling.NoSuchGeoEntityException;
import continentsManager.model.CountryRepository;
import continentsManager.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
public class homeController {

    @Autowired
    CountryRepository countryRepository;// = new CountryRepository();

    @RequestMapping(value = "/")
    public String home(Model model) {
        List<String> continents = countryRepository.getContinents();
        model.addAttribute("continents", continents);
        model.addAttribute("continentName", continents.get(0));
        return "home";
    }

    @RequestMapping(value = "/continent", method = RequestMethod.POST)
    public String submitContinent(@ModelAttribute("continentName") String continent, RedirectAttributes attr,
                                  Model model) {
        attr.addAttribute("continentName", continent);
        return "redirect:continent/{continentName}";
    }

    @RequestMapping(value = "continent/{continentName}")
    public String showContinent(Model model, @PathVariable("continentName") String continent)
            throws NoSuchGeoEntityException {
        if (countryRepository.getContinents().contains(continent)) {
            List<Country> countries = countryRepository.findByContinent(continent);
            model.addAttribute("continentCountries", countries);
            return "continent";
        } else {
            throw new NoSuchGeoEntityException("Continent not found!");
        }
    }

    @RequestMapping(value = "/country/{countryCode}")
    public String showCountry(Model model, @PathVariable("countryCode") String countryCode)
            throws NoSuchGeoEntityException {
        Country country = countryRepository.findByCode(countryCode);
        if (country != null) {
            model.addAttribute("country", country);
            return "country";
        } else {
            String msg = "Requested country not found!";
            model.addAttribute("errorText", msg);
            throw new NoSuchGeoEntityException("Country not found!");
            //return "error";
        }
    }

//    @RequestMapping(value = "/errors")
//    public String errorsRedirect(Model model, @ModelAttribute("errorText") String error) {
//        model.addAttribute("errorText", error);
//        return "error";
//    }


}
