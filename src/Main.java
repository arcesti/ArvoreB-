import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        BTree nova = new BTree();
//        List<Integer> numeros = new ArrayList<>();
//
//        for (int i = 0; i < 100; i++) {
//            numeros.add(i);
//        }
//
//        Collections.shuffle(numeros);
//
//        for (int i = 0; i < 100; i++) {
//            int val = numeros.get(i);
//            nova.inserir(val, i);
//        }

        nova.inserir(1,1);
        nova.inserir(2,2);
        nova.inserir(8,3);
        nova.inserir(9,4);
        nova.inserir(10,5);
        nova.inserir(3,6);
        nova.inserir(4,7);
        nova.inserir(5,8);
        nova.inserir(11,9);
        nova.inserir(12,10);
        nova.inserir(6,11);
        nova.inserir(7,12);
        nova.inserir(13,13);
        nova.inserir(14,14);
        nova.exibeFolha();
        nova.inserir(0, 15);
        nova.inserir(-1, 16);
        nova.inserir(-2, 17);
        System.out.println();
        nova.excluir(8);
        nova.exibeFolha();
        System.out.println("\n-------");
        nova.exibir();

    }
}