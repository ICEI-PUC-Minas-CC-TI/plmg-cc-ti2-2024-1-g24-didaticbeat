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
import java.nio.file.Path;
import java.nio.file.Paths;

public class PerfilServise{
	
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID = 1;
	private final int FORM_ORDERBY_NAME = 2;
	private final int FORM_ORDERBY_EMAIL = 3;
	
	public PerfilServise() {
		makeForm();
	}
	
	public void makeForm() {
		makeForm(FORM_DETAIL, new Produto(), FORM_ORDERBY_ID);
	}

	public void makeForm(int orderBy) {
		makeForm(FORM_DETAIL, new Produto(), orderBy);
	}

	public void makeForm(int tipo, Produto produto, int orderBy) {
		Path resourcePath = Paths.get("src", "main", "resources", "public");

        Path filePath = resourcePath.resolve("index_editarperfil.html");
		String nomeArquivo = "" + filePath;
		form = "";
		try {
			Scanner entrada = new Scanner(new File(nomeArquivo));
			while (entrada.hasNext()) {
				form += (entrada.nextLine() + "\n");
			}
			entrada.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(tipo);
		if(tipo == FORM_DETAIL) {
			form = form.replaceAll("%id%", String.valueOf(produto.getId()));
			form = form.replaceAll("%nomeCompleto%", produto.getNomeCompleto());
			form = form.replaceAll("%descricao%", produto.getDescricao());
			form = form.replaceAll("%email%", produto.getEmail());
			form = form.replaceAll("%instrumento%", produto.getInstrumento());
			form = form.replaceAll("%dataNascimento%", produto.getDataNascimento().toString());
		}
	}
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		Produto produto = (Produto) produtoDAO.get(id);
		
		if (produto != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, produto, FORM_ORDERBY_ID);
		} else {
			response.status(404); // 404 Not found
			String resp = "Produto " + id + " não encontrado.";
			makeForm();
			form.replaceFirst("%ID%", resp);
		}
		return form;
	}
	
	public Object update(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		Produto produto = produtoDAO.get(id);
		String resp = "";

		if (produto != null) {
			produto.setNomeCompleto(request.queryParams("nomeCompleto"));
			produto.setDataNascimento(LocalDate.parse(request.queryParams("dataNascimento")));
			produto.setDescricao(request.queryParams("descricao"));
			produto.setInstrumento(request.queryParams("instrumento"));
			produto.setEmail(request.queryParams("email"));
			produtoDAO.update(produto);
			response.status(200); // success
			resp = "Produto (ID " + produto.getId() + ") atualizado!";
		} else {
			response.status(404); // 404 Not found
			resp = "Produto (ID \" + produto.getId() + \") não encontrado!";
		}
		response.redirect("/index_editarperfil.html/" + id);
		makeForm();
		return form;
	}
		
}