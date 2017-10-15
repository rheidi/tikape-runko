package tikape.runko;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.Database;
import tikape.runko.database.OpiskelijaDao;
import tikape.runko.database.RaakaAineDao;
import tikape.runko.domain.RaakaAine;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:reseptitietokanta.db");
        database.init();

        RaakaAineDao rad = new RaakaAineDao (database);
        
        get("/raaka-aine", (req, res) -> {
            List<RaakaAine> aineet = rad.findAll();
            
            return new ModelAndView(aineet, "lisaa-raaka-aine");
        }, new ThymeleafTemplateEngine());

        

//        OpiskelijaDao opiskelijaDao = new OpiskelijaDao(database);
//
//        get("/", (req, res) -> {
//            HashMap map = new HashMap<>();
//            map.put("viesti", "tervehdys");
//
//            return new ModelAndView(map, "index");
//        }, new ThymeleafTemplateEngine());
//
//        get("/opiskelijat", (req, res) -> {
//            HashMap map = new HashMap<>();
//            map.put("opiskelijat", opiskelijaDao.findAll());
//
//            return new ModelAndView(map, "opiskelijat");
//        }, new ThymeleafTemplateEngine());
//
//        get("/opiskelijat/:id", (req, res) -> {
//            HashMap map = new HashMap<>();
//            map.put("opiskelija", opiskelijaDao.findOne(Integer.parseInt(req.params("id"))));
//
//            return new ModelAndView(map, "opiskelija");
//        }, new ThymeleafTemplateEngine());
    }
}
