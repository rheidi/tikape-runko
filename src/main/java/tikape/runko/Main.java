package tikape.runko;

import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.Spark;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.AnnosDao;
import tikape.runko.database.Dao;
import tikape.runko.database.Database;
import tikape.runko.database.RaakaAineDao;
import tikape.runko.domain.Annos;
import tikape.runko.domain.RaakaAine;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:db/smoothie.db");

        RaakaAineDao rad = new RaakaAineDao(database);
        Dao and = new AnnosDao(database);

        Spark.get("/", (req, res) -> {
            List<Annos> annokset = and.findAll();

            HashMap map = new HashMap<>();
            map.put("smoothiet", annokset);

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        Spark.get("/ainekset", (req, res) -> {
            List<RaakaAine> aineet = rad.findAll();

            HashMap map = new HashMap<>();
            map.put("ainekset", aineet);

            return new ModelAndView(map, "ainekset");
        }, new ThymeleafTemplateEngine());

        Spark.get("/smoothiet", (req, res) -> { //TÄMÄ tarvitsee vielä korjaamista.
            List<RaakaAine> aineet = rad.findAll();

            HashMap map = new HashMap<>();
            map.put("ainekset", aineet);

            return new ModelAndView(map, "smoothiet");
        }, new ThymeleafTemplateEngine());

        Spark.post("/ainekset", (req, res) -> {
            String nimi = req.queryParams("aine");
            System.out.println("aine: " + nimi);
            rad.save(new RaakaAine(nimi));

            res.redirect("/ainekset");
            return "OK";
        });

        Spark.get("/ainekset/:id", (req, res) -> {
            Integer id = Integer.parseInt(req.params(":id"));
            System.out.println(id);
            rad.delete(id); //<--- Tarvitsee dao toiminnallisuuden
            res.redirect("/ainekset");
            return "meh";
        });

    }
}
