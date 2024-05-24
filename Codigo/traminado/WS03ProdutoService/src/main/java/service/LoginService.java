package service;

import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import dao.ProdutoDAO;
import model.Produto;
import spark.Request;
import spark.Response;
import spark.Session;

import java.nio.file.Path;
import java.nio.file.Paths;
import service.Encrypt;

public class LoginService{
	
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_NAME = 2;
	private final int FORM_ORDERBY_EMAIL = 3;
	
	public LoginService() {
		
	}
	
	public String register(Request request, Response response) {
		String login = request.queryParams("login");
		String password = request.queryParams("password");
		String name = request.queryParams("name");
		Produto produto = produtoDAO.getByEmail(login);
		if(produto != null) {
			System.out.println("iago==ogai");
			response.redirect("/forms.html");
			return "Conta ja existente";
		}
		else{
				Produto pd = new Produto();
				pd.setEmail(login);
				pd.setPassword(password);
				pd.setNomeCompleto(name);
				pd.setDescricao("");
	            pd.setInstrumento("");
				pd.setDataNascimento(null);
				System.out.println(pd.getDataNascimento());
				produtoDAO.insert(pd);
				pd = produtoDAO.getByEmail(login);
				Session session = request.session(true);
				session.attribute("key", 1);
				session.attribute("usuario", login);
				session.attribute("gerenciador", true);
				response.redirect("/index_editarperfil.html/" + pd.getId());
				return "Usuario criado com sucesso";
		}
	}
	
	public String login(Request request, Response response) {
		String resp ="";
		String login = request.queryParams("login");
		String password = request.queryParams("password");
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		
		try {
			if (Auth(login, password)) {
				System.out.println("fffffffffffffffffffffffffffffffffffffff");
				Produto produto = produtoDAO.getByEmail(login);
				if (produto == null){
					produto = produtoDAO.getByName(login);
				}
				Session session = request.session(true);
				session.attribute("key", 1);
				session.attribute("usuario", login);
				session.attribute("gerenciador", true);
				response.redirect("/index_editarperfil.html/" + produto.getId());
				
				resp = "Bem vindo(a)!";
			}
			else {
				System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
				response.redirect("/forms.html");
				resp = "Usuario e/ou senha inválidos!";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resp;
	}
	
	public Boolean Auth(String login, String password) throws Exception {
		
		Encrypt ec = new Encrypt();
		
		Produto produto = produtoDAO.getByEmail(login);
		if (produto == null){
			produto = produtoDAO.getByName(login);
		}
		System.out.println(produto.getNomeCompleto());
		String resp = "";
		
		String encryptedPass = ec.encrypt(password);
		System.out.println("p1:" + encryptedPass + "- p2:" + produto.getPassword());
		if(encryptedPass.equals(produto.getPassword())) {
			return true;
		}
		return false;
	}

	
	public String pass(Request request, Response response) {
		String resp = "";
		int id = Integer.parseInt(request.params(":id"));
		Produto produto = produtoDAO.get(id);
		
		String Cupassword = request.queryParams("currentPassword");
		String Newpassword = request.queryParams("newPassword");
		String Compassword = request.queryParams("confirmPassword");
		try {
			if(Auth(produto.getEmail(), Cupassword)) {
				if(Newpassword.equals(Compassword)) {
					
					produto.setPassword(Newpassword);
					produtoDAO.update(produto);
				}else {
					resp = "Senhas Diferentes";
				}
			}else {
				resp = "Senha incorreta";
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		response.redirect("/index_editarperfil.html/" + produto.getId());
		return resp;
	}
}