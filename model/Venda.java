package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Venda {
    //data da venda, o produto  vendido e a quantidade vendida
    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private LocalDate dataVenda;
    private Produto produtoVendido;
    private int qtdVendida;

    @Override
    public String toString() {
        return "\nData da Venda -- " + dataVenda.format(df) + "\nProduto -- " + produtoVendido + "" + "\nqtdVendida: " + qtdVendida;
    }
    public Venda(LocalDate dataVenda, Produto produtoVendido, int qtdVendida) {
        this.dataVenda = dataVenda;
        this.produtoVendido = produtoVendido;
        this.qtdVendida = qtdVendida;
    }
    public LocalDate getDataVenda() {
        return dataVenda;
    }
    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }
    public Produto getProdutoVendido() {
        return produtoVendido;
    }
    public void setProdutoVendido(Produto produtoVendido) {
        this.produtoVendido = produtoVendido;
    }
    public int getQtdVendida() {
        return qtdVendida;
    }
    public void setQtdVendida(int qtdVendida) {
        this.qtdVendida = qtdVendida;
    }
}
