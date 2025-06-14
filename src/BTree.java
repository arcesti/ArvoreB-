public class BTree {
    private No raiz;



    public void excluir (int info) {
        No folha = buscarFolhaExclusao(info), pai;
        int pos;
        if (raiz == folha) {
            pos = raiz.buscarPos(info);
            raiz.remanejarExclusao(pos);
            raiz.setTL(raiz.getTL() - 1);
            if (raiz.getTL() == 0)
                raiz = null;
        }
        else {
            pos = folha.buscarPos(info);
            pai = buscarPai(folha, info);
            // é a primeira info da folha: pos==0(exclui primeira info da folha) | pai.getvLig(0) != folha (primeira info da folha esta no pai, entao tenho que substituir)
            if (pos == 0 && ((NoInterno) pai).getvLig(0) != folha) {
                folha.remanejarExclusao(pos);
                folha.setTL(folha.getTL() - 1);
                int posPai = pai.buscarPos(info);
                pai.setvInfo(folha.getvInfo(0), posPai);
            }
            else {
                folha.remanejarExclusao(pos);
                folha.setTL(folha.getTL() - 1);
                if (pos == 0) {
                    removerInfoNoInterno(info, folha.getvInfo(pos));
                }
            }
            int qtdMin = (int) Math.ceil(No.n/2.0) - 1;
            if (folha.getTL() < qtdMin && folha != raiz) {
                redistribuirConcatenar(folha);
            }
        }
    }

    public void removerInfoNoInterno(int infoAnt, int infoNova) {
        No aux = raiz;
        int pos = aux.buscarPos(infoAnt);
        while (aux.getvInfo(pos) != infoAnt) {
            aux = ((NoInterno) aux).getvLig(pos);
            pos = aux.buscarPos(infoAnt);
        }
        aux.setvInfo(infoNova, pos);
    }

    public void redistribuirConcatenar(No folha) {
        No pai = buscarPai(folha, folha.getvInfo(0)), irmaE, irmaD;
        int pos, posPai = pai.buscarPosExclusao(folha.getvInfo(0));
        if (posPai > 0)
            irmaE = ((NoInterno) pai).getvLig(posPai - 1);
        else
            irmaE = null;
        if (posPai < pai.getTL())
            irmaD = ((NoInterno) pai).getvLig(posPai + 1);
        else
            irmaD = null;

        int qtdMin = (int) Math.ceil(No.n / 2.0) - 1;

        if (folha instanceof NoFolha) {
            // redistribuir folha com irmã da direita
            if (irmaD != null && irmaD.getTL() > qtdMin) {
                pos = pai.buscarPos(irmaD.getvInfo(1));
                folha.setvInfo(pai.getvInfo(posPai), folha.getTL());
                folha.setvPos(pai.getvPos(posPai), folha.getTL());
                folha.setTL(folha.getTL() + 1);
                pai.setvInfo(irmaD.getvInfo(1), posPai);
                pai.setvPos(irmaD.getvPos(1), posPai);
                irmaD.remanejarExclusao(0);
                irmaD.setTL(irmaD.getTL() - 1);
            } else {
                // redistribuir com a irmã da esquerda
                if (irmaE != null && irmaE.getTL() > qtdMin) {
                    folha.remanejar(0);
                    folha.setvInfo(irmaE.getvInfo(irmaE.getTL() - 1), 0);
                    folha.setvPos(irmaE.getvPos(irmaE.getTL() - 1), 0);
                    folha.setTL(folha.getTL() + 1);
                    pai.setvInfo(irmaE.getvInfo(irmaE.getTL() - 1), posPai - 1);
                    pai.setvPos(irmaE.getvPos(irmaE.getTL() - 1), posPai - 1);
                    irmaE.remanejarExclusao(irmaE.getTL() - 1);
                    irmaE.setTL(irmaE.getTL() - 1);
                } else {
                    // concatenação com a irmã da direita
                    if (irmaD != null) {
                        int j = folha.getTL();
                        for (int i = 0; i < irmaD.getTL(); i++, j++) {
                            folha.setvInfo(irmaD.getvInfo(i), j);
                            folha.setvPos(irmaD.getvPos(i), j);
                            folha.setTL(folha.getTL() + 1);
                        }
                        ((NoInterno) pai).remanejarExclusao(posPai);
                        pai.setTL(pai.getTL() - 1);
                        ((NoInterno) pai).setvLig(posPai, folha);
                        No aux = ((NoFolha) irmaD).getProx();
                        ((NoFolha) folha).setProx(aux);
                        ((NoFolha) aux).setAnt(folha);
                    }
                    // concatenação com a irmã da esquerda
                    else {
                        int j = irmaE.getTL();
                        for (int i = 0; i < folha.getTL(); i++) {
                            irmaE.setvInfo(folha.getvInfo(i), j);
                            irmaE.setvPos(folha.getvPos(i), j);
                            irmaE.setTL(irmaE.getTL() + 1);
                        }
                        ((NoInterno) pai).remanejarExclusao(posPai);
                        pai.setTL(pai.getTL() - 1);
                        ((NoFolha) irmaE).setProx(((NoFolha) folha).getProx());
                        //No aux = ((NoFolha) )
                    }
                }
            }
        }
        // Redistribuir e concatenar com NoInterno
        else {
            // redistribuir com irma da direita
            if (irmaD != null && irmaD.getTL() > qtdMin) {

            }
            else {
                // redistribuir com irma da esquerda
                if (irmaE != null && irmaE.getTL() > qtdMin) {

                }
                else {
                    // concatenar com irma da direita
                    if (irmaD != null) {

                    }
                    // concatenar com irma da esquerda
                    else {

                    }
                }
            }
        }
    }


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
            else
                paiInternoFolhaInterna(pai, folha);

    }

    // split quando pai != folha e o pai é NoInterno e a folha é NoInterno
    public void paiInternoFolhaInterna(No pai, No folha) {
        NoInterno caixa1 = new NoInterno();
        NoInterno caixa2 = new NoInterno();
        int pos, i = 0, qtdCx = (int) Math.ceil(No.n / 2.0) - 1;

        for (; i < qtdCx; i++) {
            caixa1.setvInfo(folha.getvInfo(i), i);
            caixa1.setvPos(folha.getvPos(i), i);
            caixa1.setvLig(i, ((NoInterno) folha).getvLig(i));
            caixa1.setTL(caixa1.getTL() + 1);
        }
        caixa1.setvLig(i, ((NoInterno) folha).getvLig(i));

        pos = pai.buscarPos(folha.getvInfo(i));
        ((NoInterno) pai).remanejar(pos);
        pai.setvInfo(folha.getvInfo(i), pos);
        pai.setvPos(folha.getvPos(i), pos);
        pai.setTL(pai.getTL() + 1);
        i++;
        int j = 0;
        for (; i < folha.getTL(); i++, j++) {
            caixa2.setvInfo(folha.getvInfo(i), j);
            caixa2.setvPos(folha.getvPos(i), j);
            caixa2.setvLig(j, ((NoInterno) folha).getvLig(i));
            caixa2.setTL(caixa2.getTL() + 1);
        }
        caixa2.setvLig(j, ((NoInterno) folha).getvLig(i));
        ((NoInterno) pai).setvLig(pos, caixa1);
        ((NoInterno) pai).setvLig(pos + 1, caixa2);

        if (pai.getTL() == No.n) {
            folha = pai;
            pai = buscarPai(folha, folha.getvInfo(0));
            split(pai, folha);
        }
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

        No aux = ((NoFolha) folha).getAnt();
        if (aux!=null) {
            ((NoFolha) aux).setProx(caixa1);
            caixa1.setAnt(aux);
        }

        aux = ((NoFolha) folha).getProx();
        if(aux!=null) {
            ((NoFolha) aux).setAnt(caixa2);
            caixa2.setProx(aux);
        }

        if (pai.getTL() == No.n){
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
            int pos = aux.buscarPosExclusao(info);
            if (aux instanceof NoInterno)
                aux = ((NoInterno) aux).getvLig(pos);
        }
        return pai;
    }

    public No buscarFolhaExclusao(int info) {
        No aux = raiz;
        int pos;
        while (!(aux instanceof NoFolha)) {
            pos = aux.buscarPosExclusao(info);
            if (aux instanceof NoInterno)
                aux = ((NoInterno) aux).getvLig(pos);
        }
        return aux;
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
            System.out.print(" |  ");
        }
    }
}
