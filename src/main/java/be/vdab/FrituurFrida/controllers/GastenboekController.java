package be.vdab.FrituurFrida.controllers;

import be.vdab.FrituurFrida.domain.Gastenboek;
import be.vdab.FrituurFrida.forms.GastenboekForm;
import be.vdab.FrituurFrida.services.GastenboekService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedList;

@Controller
@RequestMapping("gastenboek")
public class GastenboekController {
    private LinkedList<Gastenboek> entries = new LinkedList<>();
    private final GastenboekService gastenboekService;
    public GastenboekController(GastenboekService gastenboekService) {
        this.gastenboekService = gastenboekService;
    }

    @GetMapping
    public ModelAndView gastenboek(){
        return new ModelAndView("gastenboek","entries",gastenboekService.findAll())
                .addObject(new Gastenboek(0,"","",Date.valueOf(LocalDate.now())));
    }

    @PostMapping("toevoegen")
    public String toevoegen(Gastenboek gastenboek){
        gastenboek.setDate(Date.valueOf(LocalDate.now()));
        gastenboekService.create(gastenboek);
        return "redirect:/gastenboek";
    }
    @PostMapping("verwijderen")
    public String delete(long[] id) {
        if (id != null) {
            gastenboekService.delete(id);
        }
        return "redirect:/gastenboek";
    }
}
