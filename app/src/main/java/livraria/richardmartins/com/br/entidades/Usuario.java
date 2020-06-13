package livraria.richardmartins.com.br.entidades;

public class Usuario {

    private Long codigo;
    private String login;
    private String senha;

    public Usuario(){
    }

    public Usuario(Long codigo, String login, String senha) {
        this.codigo = codigo;
        this.login = login;
        this.senha = senha;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String login(){
        return null;
    }
}
