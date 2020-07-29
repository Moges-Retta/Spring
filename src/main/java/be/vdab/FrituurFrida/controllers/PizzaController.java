package be.vdab.FrituurFrida.controllers;

import be.vdab.FrituurFrida.domain.Pizza;
import be.vdab.FrituurFrida.exceptions.KoersClientException;
import be.vdab.FrituurFrida.services.EuroService;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("pizzas")
public class PizzaController {
    //private final String[] pizzas = {"Prosciutto", "Margherita", "Calzone"};
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final EuroService euroService;
    public PizzaController(EuroService euroService) {
        this.euroService = euroService;
    }
    private final Pizza[] pizzas = {
            new Pizza(1, "Prosciutto", BigDecimal.valueOf(4), true),
            new Pizza(2, "Margherita", BigDecimal.valueOf(5), false),
            new Pizza(3, "Calzone", BigDecimal.valueOf(4), false)};

    @GetMapping
    public ModelAndView pizzas() {
        return new ModelAndView("pizzas", "pizzas", pizzas);
    }
    @GetMapping("{id}")
    public ModelAndView pizza(@PathVariable long id) {
        var modelAndView = new ModelAndView("pizza");
        Arrays.stream(pizzas).filter(pizza -> pizza.getId() == id).findFirst()
                .ifPresent(pizza -> {
                    modelAndView.addObject("pizza", pizza);
                    try {
                        modelAndView.addObject(
                                "inDollar", euroService.naarDollar(pizza.getPrijs()));
                    } catch (KoersClientException ex) {
                        logger.error(new Throwable("Kan dollar koers niet lezen"), ex);
                    }
                });
        return modelAndView;
    }
    private List<BigDecimal> uniekePrijzen() {
        return Arrays.stream(pizzas).map(Pizza::getPrijs)
                .distinct().sorted().collect(Collectors.toList());
    }
    @GetMapping("prijzen")
    public ModelAndView prijzen() {
        return new ModelAndView("prijzen", "prijzen", uniekePrijzen());
    }
    private List<Pizza> pizzasMetPrijs(BigDecimal prijs) {
        return Arrays.stream(pizzas)
                .filter(pizza -> pizza.getPrijs().compareTo(prijs) == 0)
                .collect(Collectors.toList());
    }
    @GetMapping("prijzen/{prijs}")
    public ModelAndView pizzasMetEenPrijs(@PathVariable BigDecimal prijs) {
        var modelAndView = new ModelAndView("prijzen","pizzas",pizzasMetPrijs(prijs));
        modelAndView.addObject("prijzen", uniekePrijzen());
        return modelAndView;
        //return new ModelAndView("prijzen", "pizzas", pizzasMetPrijs(prijs));
    }
}
