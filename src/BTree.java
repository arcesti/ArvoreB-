public class BTree {
    private No raiz;



    public void inserir(int info, int posArq) {
        if (raiz == null) {
            raiz = new NoFolha(info, 0);
        }
        else {
            No folha, pai;
            folha = buscarFolha(info);
            int pos = folha.buscarPos(info);
            folha.remanejar(pos);
            folha.setvInfo(info, pos);
            folha.setvPos(posArq, pos);
            folha.setTL(folha.getTL() + 1);
            if (folha.getTL() == No.n) {
                pai = buscarPai(folha, info);
                split(pai, folha);
            }
        }
    }

    public void split (No pai, No folha) {
        if (pai == folha) {
            if (folha instanceof NoFolha)
                raizFolha(folha);
            else
                raizInterna(pai, folha);
        }
        else
            if(folha instanceof NoFolha)
                raizNaoFolha(pai, folha);

    }

    // split de quando a raiz é nó interno
    public void raizInterna(No pai, No folha) {
        NoInterno novoPai = new NoInterno();
        NoInterno caixa1 = new NoInterno();
        NoInterno caixa2 = new NoInterno();
        int pos, i = 0, qtdCx = (int) Math.ceil(No.n/2.0) - 1;

        for (; i < qtdCx; i++) {
            caixa1.setvInfo(folha.getvInfo(i), i);
            caixa1.setvPos(folha.getvPos(i), i);
            caixa1.setvLig(i, ((NoInterno) folha).getvLig(i));
            caixa1.setTL(caixa1.getTL() + 1);
        }
        caixa1.setvLig(i, ((NoInterno) folha).getvLig(i));

        pos = i;
        novoPai.setvPos(folha.getvPos(i), 0);
        novoPai.setvInfo(folha.getvInfo(i++), 0);
        novoPai.setTL(1);

        int j = 0;
        for (; i < folha.getTL(); j++, i++) {
            caixa2.setvInfo(folha.getvInfo(i), j);
            caixa2.setvPos(folha.getvPos(i), j);
            caixa2.setvLig(j, ((NoInterno) folha).getvLig(i));
            caixa2.setTL(caixa2.getTL() + 1);
        }
        caixa2.setvLig(j, ((NoInterno) folha).getvLig(i));

        novoPai.setvLig(0, caixa1);
        novoPai.setvLig(1, caixa2);
        raiz = novoPai;
    }

    // a folha não é a raiz
    public void raizNaoFolha(No pai, No folha) {
        NoFolha caixa1 = new NoFolha(), caixa2 = new NoFolha();
        int i = 0;
        int qtdCx = (int) Math.ceil(((double) No.n - 1) / 2.0);

        for (; i < qtdCx; i++) {
            caixa1.setvInfo(folha.getvInfo(i), i);
            caixa1.setvPos(folha.getvPos(i), i);
            caixa1.setTL(caixa1.getTL() + 1);
        }

        for (int j = 0; i < folha.getTL(); i++, j++) {
            caixa2.setvInfo(folha.getvInfo(i), j);
            caixa2.setvPos(folha.getvPos(i), j);
            caixa2.setTL(caixa2.getTL() + 1);
        }

        caixa1.setProx(caixa2);
        caixa2.setAnt(caixa1);

        int pos = pai.buscarPos(caixa2.getvInfo(0));

        ((NoInterno) pai).remanejar(pos);
        pai.setvInfo(caixa2.getvInfo(0), pos);
        pai.setvPos(caixa2.getvPos(0), pos);
        pai.setTL(pai.getTL() + 1);
        ((NoInterno) pai).setvLig(pos, caixa1);
        ((NoInterno) pai).setvLig(pos + 1, caixa2);

        // ligação dos nós folhas
        if (pos-1 >= 0) {
            No aux = ((NoInterno) pai).getvLig(pos - 1);
            caixa1.setAnt(aux);
            ((NoFolha) aux).setProx(caixa1);
        }

        if(pos + 2 <= pai.getTL()) {
            No aux = ((NoInterno) ((NoInterno) pai).getvLig(pos + 2));
            caixa2.setProx(aux);
            ((NoFolha) aux).setAnt(caixa2);
        }

        if (pai.getTL() == 5){
            folha = pai;
            pai = buscarPai(folha, folha.getvInfo(0));
            split(pai, folha);
        }
    }

    // a raiz é a folha
    public void raizFolha (No folha) {
        NoFolha caixa1 = new NoFolha(), caixa2 = new NoFolha();
        NoInterno novo = new NoInterno();
        int qtdCx = (int) Math.ceil(((double) No.n - 1)/2.0);
        int i;

        for (i = 0; i < qtdCx; i++) {
            caixa1.setvInfo(folha.getvInfo(i), i);
            caixa1.setvPos(folha.getvPos(i), i);
            caixa1.setTL(caixa1.getTL() + 1);
        }

        for (int j = 0; i < folha.getTL(); i++, j++) {
            caixa2.setvInfo(folha.getvInfo(i), j);
            caixa2.setvPos(folha.getvPos(i), j);
            caixa2.setTL(caixa2.getTL() + 1);
        }



        caixa1.setProx(caixa2);
        caixa2.setAnt(caixa1);

        novo.setvInfo(caixa2.getvInfo(0), 0);
        novo.setvPos(caixa2.getvPos(0), 0);
        novo.setvLig(0, caixa1);
        novo.setvLig(1, caixa2);
        novo.setTL(1);
        raiz = novo;
    }

    public No buscarPai(No folha, int info) {
        No aux, pai;
        aux = pai = raiz;
        while (aux != null && aux != folha) {
            pai = aux;
            int pos = aux.buscarPos(info);
            if (aux instanceof NoInterno)
                aux = ((NoInterno) aux).getvLig(pos);
        }
        return pai;
    }

    public No buscarFolha(int info) {
        No aux = raiz;
        int pos;
        while (!(aux instanceof NoFolha)) {
            pos = aux.buscarPos(info);
            if (aux instanceof NoInterno)
                aux = ((NoInterno) aux).getvLig(pos);
        }
        return aux;
    }
    
    public void exibeInOrdem(No aux) {
        if(aux != null) {
            if(!(aux instanceof NoFolha)) {
                exibeInOrdem(((NoInterno) aux).getvLig(0));
                for (int i = 1; ((NoInterno) aux).getvLig(i) != null; i++) {
                    exibeInOrdem(((NoInterno) aux).getvLig(i));
                }
            }
            else {
                for (int i = 0; i < aux.getTL(); i++) {
                    System.out.print(aux.getvInfo(i) + " ");
                }
            }
        }
    }
    
    public void exibir() {
        No aux = raiz;
        exibeInOrdem(aux);
    }

    public void exibeFolha() {
        No aux = raiz;

        while(!(aux instanceof NoFolha))
            aux = ((NoInterno) aux).getvLig(0);

        while (aux != null) {
            for (int i = 0; i < aux.getTL(); i++) {
                System.out.print(aux.getvInfo(i) + " ");
            }
            aux = ((NoFolha) aux).getProx();
            System.out.print(" | ");
        }
    }
}
