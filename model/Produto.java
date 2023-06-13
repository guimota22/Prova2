package model;

public class Produto {
    //código, nome, valor e  quantidade em estoque
    private String nome;
    private String codigo;
    private int qtdEstoque;
    private double valor;

    @Override
    public String toString() {
        return "Nome: " + nome + " - Código: " + codigo + " - qtdEstoque: " + qtdEstoque + " - Valor: R$" + valor;
    }
    public Produto(String nome, String codigo, double valor, int qtdEstoque){
        this.nome = nome;
        this.codigo = codigo;
        this.valor = valor;
        this.qtdEstoque = qtdEstoque;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public int getQtdEstoque() {
        return qtdEstoque;
    }
    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }
}
