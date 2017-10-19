package tikape.runko;

import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.Database;
import tikape.runko.database.RaakaAineDao;
import tikape.runko.domain.RaakaAine;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:db/smoothie.db");

        RaakaAineDao rad = new RaakaAineDao (database);
        AnnosDao and = new AnnosDao(database);
        
        get("/", (req, res) -> {
            List<Annos> annokset = and.findAll();
            
            HashMap map = new HashMap<>();
            map.put("smoothiet", annokset);
            
            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());
        
        get("/raaka-aine", (req, res) -> {
            List<RaakaAine> aineet = rad.findAll();
            
            HashMap map = new HashMap<>();
            map.put("ainekset", aineet);
            
            return new ModelAndView(map, "ainekset");
        }, new ThymeleafTemplateEngine());

        post("/raaka-aine", (req, res) -> {
            String nimi = req.queryParams("aine");
            System.out.println("aine: " + nimi);
            rad.save(new RaakaAine(nimi));
            
            res.redirect("/raaka-aine");
            return "OK";
        });

    }
}
