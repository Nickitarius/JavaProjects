package haircutter.controllers;

import haircutter.model.HaircutCase;
import haircutter.model.HaircutType;
import haircutter.service.HaircutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/haircuts")
public class HaircutController {

    @Autowired
    HaircutService haircutService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addTransaction(Model model) {
        HaircutCase hc = new HaircutCase();
        //airline.setFlightNumber(-1);
        List<HaircutType> l = haircutService.getAllHaircutTypes();
        model.addAttribute("haircutCase", hc);
        model.addAttribute("haircutTypes", l);
        model.addAttribute("options", haircutService.getAllOptions());
        model.addAttribute("masters", haircutService.getAllMasters());
        return "addTransaction";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveTransaction(@ModelAttribute("haircutCase") HaircutCase haircutCase,
                                  Model model) {
        haircutService.saveTransaction(haircutCase);
        model.addAttribute("haircutCase", haircutCase);
        // List<HaircutCase> hc = haircutService.getAllHaircutCases();
        //model.addAttribute("allTransactions", hc);
        //Integer sum = 0;
        /*for (HaircutCase c : hc) {
            sum += c.getTotalPrice();
        }*/
        //model.addAttribute("sum", sum);
        return "home";
    }

    @RequestMapping("/showTransactions")
    public String showTransactions(Model model) {
        List<HaircutCase> hc = haircutService.getAllHaircutCases();
        model.addAttribute("allTransactions", hc);
        Integer sum = 0;
        for (HaircutCase c : hc) {
            sum += c.getTotalPrice();
        }
        model.addAttribute("sum", sum);
        return "allTransactions";
    }
}
