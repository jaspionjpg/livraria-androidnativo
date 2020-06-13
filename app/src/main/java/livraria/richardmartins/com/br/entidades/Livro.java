package livraria.richardmartins.com.br.entidades;

import java.util.Date;

public class Livro {

    private Long codigo;
    private String ISBN;
    private String titulo;
    private String subTitulo;
    private String edicao;
    private String autor;
    private Integer paginas;
    private Integer anoPublicacao;
    private String nomeEditora;
    private String categoria;
    private byte[] imagem;
    private Date dataCadastro;

    public Livro(){
    }

    public Livro(Long codigo, String ISBN, String titulo, String subTitulo, String edicao, String autor, Integer paginas, Integer anoPublicacao, String nomeEditora, String categoria, Date dataCadastro) {
        this.codigo = codigo;
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.subTitulo = subTitulo;
        this.edicao = edicao;
        this.autor = autor;
        this.paginas = paginas;
        this.anoPublicacao = anoPublicacao;
        this.nomeEditora = nomeEditora;
        this.categoria = categoria;
        this.dataCadastro = dataCadastro;

    }

    public Livro(Long codigo, String ISBN, String titulo, String subTitulo, String edicao, String autor, Integer paginas, Integer anoPublicacao, String nomeEditora, String categoria, byte[] imagem, Date dataCadastro) {
        this.codigo = codigo;
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.subTitulo = subTitulo;
        this.edicao = edicao;
        this.autor = autor;
        this.paginas = paginas;
        this.anoPublicacao = anoPublicacao;
        this.nomeEditora = nomeEditora;
        this.categoria = categoria;
        this.imagem = imagem;
        this.dataCadastro = dataCadastro;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getNomeEditora() {
        return nomeEditora;
    }

    public void setNomeEditora(String nomeEditora) {
        this.nomeEditora = nomeEditora;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

}
