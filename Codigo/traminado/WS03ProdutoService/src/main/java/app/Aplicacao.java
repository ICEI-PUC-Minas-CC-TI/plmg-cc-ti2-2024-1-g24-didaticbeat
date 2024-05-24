package app;

import static spark.Spark.*;
import service.ProdutoService;
import service.PerfilServise;
import service.LoginService;

public class Aplicacao {

    private static ProdutoService produtoService = new ProdutoService();
    private static PerfilServise perfilservise = new PerfilServise();
    private static LoginService login = new LoginService();

    public static void main(String[] args) {
        port(5432);

        staticFiles.location("/public");

        post("/produto/insert", (request, response) -> produtoService.insert(request, response));

        get("/produto/:id", (request, response) -> produtoService.get(request, response));

        get("/produto/list/:orderby", (request, response) -> produtoService.getAll(request, response));

        get("/produto/update/:id", (request, response) -> produtoService.getToUpdate(request, response));

        post("/produto/update/:id", (request, response) -> produtoService.update(request, response));
 
        get("/produto/delete/:id", (request, response) -> produtoService.delete(request, response));
        
        get("/index_editarperfil.html/:id", (request, response) -> perfilservise.get(request, response));
        
        post("/index_editarperfil.html/update/:id", (request, response) -> perfilservise.update(request, response));
        
        post("/index_editarperfil.html/password/:id", (request, response) -> login.pass(request, response));
        
        get("/forms.html/login", (request,response) -> login.login(request,response));
        
        get("/forms.html/register", (request,response) -> login.register(request,response));

    }
}