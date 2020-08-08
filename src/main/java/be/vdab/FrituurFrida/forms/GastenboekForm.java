package be.vdab.FrituurFrida.forms;

import org.springframework.format.annotation.DateTimeFormat;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
public class GastenboekForm extends HttpServlet {
    @NotNull
    private final String name;
    @NotNull
    private final String bericht;
    /*@DateTimeFormat(pattern = "####-##-##")
    private final Date date;*/

    public GastenboekForm(@NotNull String name, @NotNull String bericht) {
        this.name = name;
        this.bericht = bericht;
    }

    public String getName() {
        return name;
    }

    public String getBericht() {
        return bericht;
    }
}
