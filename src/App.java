import model.Produto;
import model.Venda;
import model.VoltarMenu;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) throws InterruptedException, IOException {

        int opcao = 0;
        double valor = 0;

        Scanner sc = new Scanner(System.in);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Produto> listaProduto = new ArrayList<>();
        List<Venda> listaVenda = new ArrayList<>();
        VoltarMenu.limpa();

        do {
            
            menu();
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    VoltarMenu.limpa();
                    System.out.println("\n********************\nCADASTRO DE PRODUTOS\n********************\n");
                    System.out.println("1 - Cadastrar produto");
                    System.out.println("2 - Cadastrar estoque produto");
                    System.out.print("Opção: ");
                    int opcao2 = sc.nextInt();
                    sc.nextLine();

                    if(opcao2 == 1){
                        
                        try {
                            VoltarMenu.limpa();
                            System.out.print("Informe o nome do produto: ");
                            String nomeProduto = sc.nextLine();
                            System.out.print("Informe o código do produto: ");
                            String codigo = sc.nextLine();
                            System.out.print("Informe o valor do produto: R$");
                            valor = sc.nextDouble();

                            int qtdProduto = 0;

                            listaProduto.add(new Produto(nomeProduto, codigo, valor, qtdProduto));
                            
                            System.out.println("\nProduto cadastrado com sucesso.");
                            sc.nextLine();     
                            VoltarMenu.voltarMenu(sc);
                        } catch (InputMismatchException e) {
                            System.out.println("\nO valor não pode conter letras, apenas números.");
                            sc.nextLine();     
                            VoltarMenu.voltarMenu(sc);
                        }
                        

                       
                    }else if(opcao2 == 2){
                        VoltarMenu.limpa();
                        System.out.print("Informe o código do produto que deseja estocar: ");
                        String codigoInformado = sc.nextLine();
                        System.out.println("");

                            List<Produto> listaInclusaoProdutos = listaProduto.stream()
                                .filter(p -> p.getCodigo().contains(codigoInformado)).collect(Collectors.toList());;

                                if (listaInclusaoProdutos.isEmpty()) {
                                    System.out.println("Produto não localizado.");
                                } else {
                                    System.out.print("Informe quantas unidades do produto serão incluídas no estoque: ");
                                    int estoque = sc.nextInt();
                                    Produto produto = listaInclusaoProdutos.get(0);
                                    
                                    produto.setQtdEstoque(produto.getQtdEstoque() + estoque);
                                    System.out.println("\nProdutos incluídos com sucesso.");
                                    sc.nextLine();
                                }
                                VoltarMenu.voltarMenu(sc);
                    }
                    
                    break;
                case 2:
                    VoltarMenu.limpa();
                    System.out.print("Informe o nome do produto que deseja consultar: ");
                    String nome = sc.nextLine();
                    
                    List<Produto> listaConsulta = listaProduto.stream()
                        .filter(p -> p.getNome().contains(nome)).collect(Collectors.toList());;

                        if(listaConsulta.isEmpty()){
                            System.out.println("\nProduto não encontrado.");
                        }else{
                            VoltarMenu.limpa();
                            for (Produto produto : listaConsulta) {
                                System.out.println(produto);
                                System.out.println("");
                            } 
                        }
                    VoltarMenu.voltarMenu(sc);
                    break;

                case 3:
                    VoltarMenu.limpa();
                    for (Produto produto : listaProduto) {
                        System.out.println(produto + "\n" );
                    }
                    DoubleSummaryStatistics resumo = listaProduto.stream()
                        .collect(Collectors.summarizingDouble(Produto::getValor));

                    System.out.printf("Maior Valor: %.2f - Menor Valor: %.2f - Valor Médio: %.2f", 
                        resumo.getMax(), resumo.getMin(),resumo.getAverage());

                    System.out.println("\n");
                    VoltarMenu.voltarMenu(sc);
                    break;

                case 4:
                    VoltarMenu.limpa();
                    try {
                        System.out.print("Informe a primeira data (dd/mm/aaaa): ");
                        String dataInicioStr = sc.nextLine();
                        System.out.print("Informe a segunda data (dd/mm/aaaa): ");
                        String dataFimStr = sc.nextLine();

                        double valorTotal = 0;
                        double valorMedio = 0;
                        int qtdVendas = 0;
                
                        LocalDate dataInicio = LocalDate.parse(dataInicioStr, df);
                        LocalDate dataFim = LocalDate.parse(dataFimStr, df);

                        for (Venda venda : listaVenda) {
                            if(venda.getDataVenda().compareTo(dataInicio) >= 0 && venda.getDataVenda().compareTo(dataFim) < 1) {
                                System.out.println(venda);

                                valorTotal += venda.getProdutoVendido().getValor() * venda.getQtdVendida();
                                valorMedio += valorTotal;
                                qtdVendas++;
                                System.out.println("ValorTotal: " + valorTotal);

                                
                            }
                        }
                        double mediaVendas = valorMedio / qtdVendas;
                        System.out.println("\nMédia Total das vendas: " + mediaVendas);

                        sc.nextLine();
                        VoltarMenu.voltarMenu(sc);
                    } catch (DateTimeParseException e) {
                        System.out.println("\nFormato de data inválido. Informe uma data no formato [dd/MM/yyyy].");
                        // sc.nextLine();
                        VoltarMenu.voltarMenu(sc);
                    }
                    
                    break;
                    
                case 5:
                    VoltarMenu.limpa();
                    System.out.print("Informe o código do produto que deseja vender: ");
                    String codigoVenda = sc.nextLine();

                    List<Produto> listapVendas = listaProduto.stream()
                        .filter(v -> v.getCodigo().equals(codigoVenda)).collect(Collectors.toList());;

                        if (listapVendas.isEmpty()) {
                            System.out.println("\nProduto não localizado.");
                        } else {

                            System.out.print("Informe a data da venda: [dd/mm/aaaa]: ");
                            String dataVendaString = sc.nextLine();
                            LocalDate dataVenda = LocalDate.parse(dataVendaString, df);

                            System.out.print("\nInforme quantas unidades foram vendidas: ");
                            int qtdVendida = sc.nextInt();
                            
                            Produto produto = listapVendas.get(0);
                            Venda venda = new Venda(dataVenda, produto, qtdVendida);

                            if(produto.getQtdEstoque() < qtdVendida){
                                System.out.println("\nQuantidade insuficiente.");
                                sc.nextLine();
                                VoltarMenu.voltarMenu(sc);
                                break;
                            }

                            listaVenda.add(venda);
                            produto.setQtdEstoque(produto.getQtdEstoque() - qtdVendida);

                            System.out.println("\nVenda realizada com sucesso.");
                        }
                    sc.nextLine();
                    VoltarMenu.voltarMenu(sc);
                    break;
                    
                default:
                    VoltarMenu.limpa();
                    System.out.println("\nCódigo inválido.");

                    VoltarMenu.voltarMenu(sc);
                    break;
            }
        } while (opcao != 0);
    }
    private static void menu(){
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Incluir produto");
            System.out.println("2 - Consultar produto");
            System.out.println("3 - Listagem de produtos");
            System.out.println("4 - Vendas por período - detalhado");
            System.out.println("5 - Realizar venda");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
    }
    
}