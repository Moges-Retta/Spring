package be.vdab.FrituurFrida.domain;

import be.vdab.FrituurFrida.repositories.PizzasRepository;
import be.vdab.FrituurFrida.repositories.PropertiesSausRepository;

import java.io.IOException;
import java.sql.SQLException;
public class Main {

    public static void main(String[] args) throws IOException {
        var repository = new PizzasRepository();
        try {
            System.out.print(repository.selectPizzas());
            System.out.println(" PIZZAS.");
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
        /*PropertiesSausRepository propertiesSausRepository = new PropertiesSausRepository();
        propertiesSausRepository.findAll().forEach(System.out::println);*/
    }
}
