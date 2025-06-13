import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        BTree nova = new BTree();
        nova.inserir(1, 1);
        nova.inserir(2, 2);
        nova.inserir(16, 3);
        nova.inserir(17, 4);
        nova.inserir(18, 5);
        nova.inserir(19, 6);
        nova.inserir(3, 7);
        nova.inserir(4, 8);
        nova.inserir(12, 9);
        nova.inserir(13, 10);
        nova.inserir(14, 11);
        nova.inserir(5, 12);
        nova.inserir(6, 13);
        nova.inserir(7, 14);
        nova.inserir(8, 15);
        nova.inserir(9, 16);
        nova.inserir(10, 17);
        nova.inserir(11, 18);
//        nova.inserir(15, 19);
//        nova.inserir(20, 20);
//        nova.inserir(21, 21);
//        List<Integer> numeros = new ArrayList<>();
//
//// Preenche a lista com números de 0 a 1999
//        for (int i = 0; i < 500; i++) {
//            numeros.add(i);
//        }
//
//// Embaralha a lista para garantir aleatoriedade
//        Collections.shuffle(numeros);
//
//// Insere os valores sem repetição
//        for (int i = 0; i < 100; i++) {
//            int val = numeros.get(i);
//            nova.inserir(val, i);
//        }
        nova.exibeFolha();
    }
}