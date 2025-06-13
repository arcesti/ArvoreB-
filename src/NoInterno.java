public class NoInterno extends No {
    private No[] vLig;

    public NoInterno () {
        super();
        vLig = new No[n+1];
    }

    public NoInterno(No lig) {
        this();
        vLig[0] = lig;
    }

    @Override
    public void remanejar(int pos) {
        super.remanejar(pos);
        for (int i = super.getTL() + 1; i > pos; i--) {
            vLig[i] = vLig[i - 1];
        }
    }

    public No getvLig(int pos) {
        return vLig[pos];
    }

    public void setvLig(int pos, No vLig) {
        this.vLig[pos] = vLig;
    }
}
