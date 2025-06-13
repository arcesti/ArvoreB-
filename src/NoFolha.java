public class NoFolha extends No {
    private No prox;
    private No ant;

    public NoFolha() {
        super();
        this.prox = null;
        this.ant = null;
    }

    public NoFolha(int info, int pos) {
        super(info, pos);
        this.prox = null;
        this.ant = null;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }

    public No getAnt() {
        return ant;
    }

    public void setAnt(No ant) {
        this.ant = ant;
    }
}
