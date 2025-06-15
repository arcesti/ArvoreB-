import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        BTree nova = new BTree();

        // Inserindo, exibindo e apagando arvore do 0 ao 500:
        for (int i = 0; i < 500; i++) {
            nova.inserir(i, i);
        }

        System.out.println("Exibindo inOrdem do 0 a 499:");
        nova.exibir();
        System.out.println();
        System.out.println("\nExibindo as folhas do 0 a 499:");
        nova.exibeFolha();
        System.out.println();

        System.out.println("\nExcluindo do 0 a 499: \n");
        for (int i = 0; i < 500; i++) {
            nova.excluir(i);
        }

        System.out.println("\nTentando exibir inOrdem após exclusão:");
        nova.exibir();

        System.out.println("\nTentando exibir folhas após exclusão:");
        nova.exibeFolha();

        System.out.println("-----------------");

        List<Integer> numeros = new ArrayList<>();

        System.out.println("\nInserindo numeros aleatorios do 0 a 399 na arvore");
        for (int i = 0; i < 400; i++) {
            numeros.add(i);
        }

        Collections.shuffle(numeros);
        for (int i = 0; i < 400; i++) {
            int val = numeros.get(i);
            nova.inserir(val, i);
        }

        System.out.println("\nExibindo numeros aleatorios inOrdem:");
        nova.exibir();
        System.out.println();

        System.out.println("\nExibindo numeros aleatorios nas folhas:");
        nova.exibeFolha();
        System.out.println();
        System.out.println("\nExluindo valores aleatorios das posicoes 150 a 200 da lista de numeros aleatorios:");
        for (int i = 150; i < 200; i++) {
            int val = numeros.get(i);
            System.out.println("Apagando: " + val);
            nova.excluir(val);
        }
        System.out.println("\n Exibindo nós folhas após exclusão: ");
        nova.exibeFolha();
        System.out.println();
        System.out.println("\n Exibindo inOrdem após exclusão: ");
        nova.exibir();
    }
}