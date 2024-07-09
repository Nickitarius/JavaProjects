package delo.controllers;


import delo.PeriodWrapper;
import delo.TagWrapper;
import delo.model.Tag;
import delo.model.Zadacha;
import delo.service.ZadachaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.time.Period;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    ZadachaService zadachaService;

    @RequestMapping(value = "/getZadachasByPeriod", method = RequestMethod.GET)
    public String findByPeriod(Model model) {
        List<Zadacha> zadachas = new LinkedList<Zadacha>();
        PeriodWrapper period = new PeriodWrapper("all");
        zadachas = zadachaService.getAllZadachas();
        model.addAttribute("zadachas", zadachas);
        model.addAttribute("period", period);
        return "getZadachasByPeriod";
    }

    @RequestMapping(value = "/getZadachasByPeriod", method = RequestMethod.POST)
    public String findByPeriod(Model model, @ModelAttribute("period") PeriodWrapper period) {
        List<Zadacha> zadachas = new LinkedList<Zadacha>();
        switch (period.getPeriod()) {
            case "all":
                zadachas = zadachaService.getAllZadachas();
                break;
            case "day":
                zadachas = zadachaService.getZadachasToday();
                break;
            case "week":
                zadachas = zadachaService.getZadachasWeek();
                break;
            case "month":
                zadachas = zadachaService.getZadachasMonth();
                break;
            case "year":
                zadachas = zadachaService.getZadachasYear();
                break;
            default:
                zadachas = zadachaService.getAllZadachas();
        }
        model.addAttribute("zadachas", zadachas);
        model.addAttribute("period", period);
        return "getZadachasByPeriod";
    }

    @RequestMapping(value = "getZadachasByTag", method = RequestMethod.GET)
    public String findByTag(Model model) {
        List<Tag> tagsFromDB = zadachaService.getAllTags();
        List<Zadacha> zadachas;
        TagWrapper tag = new TagWrapper(tagsFromDB.get(0));
        zadachas = zadachaService.getZadachasByTag(tag.getTag());
        model.addAttribute("zadachas", zadachas);
        model.addAttribute("tag", tag);
        model.addAttribute("tagsFromDB", tagsFromDB);
        return "getZadachasByTag";
    }

    @RequestMapping(value = "getZadachasByTag", method = RequestMethod.POST)
    public String findByTag(Model model, @ModelAttribute("tag") TagWrapper tag) {
        List<Tag> tagsFromDB = zadachaService.getAllTags();
        List<Zadacha> zadachas;
        zadachas = zadachaService.getZadachasByTag(tag.getTag());
        model.addAttribute("zadachas", zadachas);
        model.addAttribute("tag", tag);
        model.addAttribute("tagsFromDB", tagsFromDB);
        return "getZadachasByTag";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addTask(Model model, @ModelAttribute("zadacha") Zadacha zadacha) {
        List<Tag> tagsFromDB = zadachaService.getAllTags();
        model.addAttribute("zadacha", zadacha);
        model.addAttribute("tagsFromDB", tagsFromDB);
        return "addZadacha";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, params = "action=save")
    public String saveTask(
            @Valid @ModelAttribute("zadacha") Zadacha zadacha,
            Errors errors, Model model) {
        if (errors.hasErrors()) {
            List<Tag> tagsFromDB = zadachaService.getAllTags();
            model.addAttribute("zadacha", zadacha);
            model.addAttribute("tagsFromDB", tagsFromDB);
            return "addZadacha";
        } else {
            zadachaService.saveTransaction(zadacha);
            List<Zadacha> zadachas = zadachaService.getAllZadachas();
            model.addAttribute("zadachas", zadachas);
            return "home";
        }
    }

}
