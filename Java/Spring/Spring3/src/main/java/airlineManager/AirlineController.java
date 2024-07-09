package airlineManager;

import airlineManager.controller.AirlineService;
import airlineManager.model.Airline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/airlines")
public class AirlineController {

    @Autowired
    AirlineService airlineService;

    @RequestMapping("/{flightNumber}")
    public String showAirline(@PathVariable("flightNumber") Integer flightNumber,
                              Model model) {
        Airline airline = airlineService.find(flightNumber);
        if (airline != null) {
            LocalDateTime time = airlineService.getTimeToClosestFlight(airline);
            model.addAttribute("airline", airline);
            model.addAttribute("timeLeft", time);
            return "airline";
        } else {
            return "error";
        }
    }

    @RequestMapping("/findall")
    public String findAll(Model model) {
        List<Airline> airlines = airlineService.findAll();
        model.addAttribute("airlines", airlines);
        return "allAirlines";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addAirline(Model model) {
        Airline airline = new Airline();
        //airline.setFlightNumber(-1);
        model.addAttribute("airline", airline);
        return "add_airline";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveAirline(
            @Valid @ModelAttribute("airline") Airline airline,
            Errors errors, Model model, RedirectAttributes attr)
        /*throws Exception */ {
        if (errors.hasErrors()) {
            model.addAttribute("airline", airline);
            return "add_airline";
        } else {
            airlineService.save(airline);
            attr.addFlashAttribute("airline", airline);
            return "redirect:/airlines/airline";
        }
    }

    @RequestMapping("/airline")
    public String showNewAirline(@ModelAttribute("airline") Airline airline,
                                 Model model) {
        if (airline.getFlightNumber() != null) {
            LocalDateTime time = airlineService.getTimeToClosestFlight(airline);
            model.addAttribute("timeLeft", time);
            model.addAttribute("airline", airline);
            return "airline";
        } else {
            return "error";
        }
    }
}
