package livraria.richardmartins.com.br.utilitarios;

import java.util.ArrayList;
import java.util.List;

public final class Constants {

    //SQL'S
    public static final String GET_LOGIN_USUARIO = "SELECT * FROM usuario WHERE login = ? AND senha = ?";
    public static final String GET_USUARIO_BY_LOGIN = "SELECT * FROM usuario WHERE login = ?";
    public static final String GET_ALL_LIVROS = "SELECT * FROM livro";
    public static final String GET_ALL_LIVROS_BY_TITULO = "SELECT * FROM livro WHERE titulo LIKE ?";
    public static final String GET_ALL_LIVROS_BY_ID = "SELECT * FROM livro WHERE codigo = ?";
    public static final String GET_ALL_LIVROS_BY_CATEGORIA = "SELECT * FROM livro WHERE categoria LIKE ?";
    public static final String GET_ALL_LIVROS_BY_TITULO_OR_AUTOR = "SELECT * FROM livro WHERE titulo LIKE ? OR autor LIKE ?";

    public static final String INSERT_LIVRO = "INSERT INTO livro (ISBN, titulo, subTitulo, edicao, autor, paginas, anoPublicacao, nomeEditora, categoria, imagem, dataCadastro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_SENHA_USUARIO = "UPDATE usuario SET senha = ? WHERE login = ?";
    public static final String UPDATE_LIVO = "UPDATE livro SET titulo = ?, subTitulo = ?, edicao = ?, autor = ?, paginas = ?, anoPublicacao = ?, nomeEditora = ?, categoria = ?, imagem = ?, dataCadastro = ? WHERE codigo = ?";
    public static final String DELETE_LIVRO = "DELETE FROM livro WHERE titulo = ?";

    public static List<String> getCategorias(){
        List<String> categories = new ArrayList<>();
        categories.add("Selecione uma categoria");
        categories.add("Auto ajuda");
        categories.add("Aventura");
        categories.add("Infanto-Juvenil");
        categories.add("Infantis");
        categories.add("Jogos");
        categories.add("Manuais");
        categories.add("Didaticos");
        categories.add("Poesia");
        categories.add("Politica");
        categories.add("Cientifico");
        categories.add("Historia");
        return categories;
    }

    public static List<String> getCategorias(String outra){
        List<String> lista = getCategorias();
        for(int i = 0 ; i < lista.size();i++){
            if(lista.get(i).equals(outra)){
                String a = lista.get(i);
                lista.remove(i);
                lista.add(0, a);
                break;
            }
        }
        return  lista;
    }
 }
