package tikape.runko;

import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.AnnosDao;
import tikape.runko.database.AnnosRaakaAineDao;
import tikape.runko.database.Database;
import tikape.runko.database.RaakaAineDao;
import tikape.runko.domain.Annos;
import tikape.runko.domain.AnnosRaakaAine;
import tikape.runko.domain.RaakaAine;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:db/smoothie.db");

        RaakaAineDao rad = new RaakaAineDao(database);
        AnnosDao and = new AnnosDao(database);
        AnnosRaakaAineDao andrad = new AnnosRaakaAineDao(database);

        Spark.get("/", (req, res) -> {
            List<Annos> annokset = and.findAll();

            HashMap map = new HashMap<>();
            map.put("smoothiet", annokset);

            // Sparkin dokkareissa suositeltu tapa suorittaa kutsu:
            // http://sparkjava.com/documentation#views-and-templates
            return new ThymeleafTemplateEngine().render(
                    new ModelAndView(map, "index")
            );
        });

        Spark.get("/ainekset", (req, res) -> {
            List<RaakaAine> aineet = rad.findAll();

            HashMap map = new HashMap<>();
            map.put("ainekset", aineet);

            return new ThymeleafTemplateEngine().render(
                    new ModelAndView(map, "ainekset")
            );
        });

        Spark.get("/smoothiet", (req, res) -> {
            List<RaakaAine> aineet = rad.findAll();
            List<Annos> smoothiet = and.findAll();

            HashMap map = new HashMap<>();
            map.put("ainekset", aineet);
            map.put("smoothiet", smoothiet);

            return new ThymeleafTemplateEngine().render(
                    new ModelAndView(map, "smoothiet")
            );
        });

        Spark.post("/smoothiet", (req, res) -> {
            int nimi = Integer.parseInt(req.queryParams("smoothie"));
            int raakaAine = Integer.parseInt(req.queryParams("raakaAine"));
            int jarjestys = Integer.parseInt(req.queryParams("jarjestys"));
            String maara = req.queryParams("maara");
            String ohje = req.queryParams("ohje");
            andrad.saveOrUpdate(new AnnosRaakaAine(nimi, raakaAine, null, jarjestys, maara, ohje));

            res.redirect("/smoothiet");
            return "";
        });
        
        Spark.post("/lisaaSmoothie", (req, res) -> {
            String nimi = req.queryParams("smoothie");
            and.saveOrUpdate(new Annos(-1, nimi));            
            res.redirect("/smoothiet");
            return "";
        });

        Spark.get("/smoothiet/:id", (req, res) -> {
            Annos smoothie = and.findOne(Integer.parseInt(req.params(":id")));
            List<AnnosRaakaAine> ainekset = andrad.findOneList(Integer.parseInt(req.params(":id")));
            HashMap map = new HashMap<>();
            map.put("smoothie", smoothie);
            map.put("ainekset", ainekset);
            return new ThymeleafTemplateEngine().render(
                    new ModelAndView(map, "smoothie")
            );
        });

        Spark.post("/ainekset", (req, res) -> {
            String nimi = req.queryParams("aine");
            System.out.println("aine: " + nimi);
            rad.saveOrUpdate(new RaakaAine(nimi));

            res.redirect("/ainekset");
            return "";
        });

        Spark.get("/ainekset/:id/poista", (req, res) -> {
            Integer id = Integer.parseInt(req.params(":id"));
            System.out.println(id);
            rad.delete(id);
            res.redirect("/ainekset");
            return "";
        });

    }
}
