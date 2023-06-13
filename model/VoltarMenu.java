package model;

import java.io.IOException;
import java.util.Scanner;

public class VoltarMenu {
    
    public static void voltarMenu(Scanner sc) throws InterruptedException, IOException {
        System.out.println("Pressione ENTER para voltar ao menu.");
        sc.nextLine();


        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");

        System.out.flush();
    }
    
    public static void limpa(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

