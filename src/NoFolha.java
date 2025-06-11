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

}
