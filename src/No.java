public class No {
    public static final int n = 5;
    private int[] vInfo;
    private int[] vPos;
    private int TL;

    public No () {
        this.vInfo = new int[n];
        this.vPos = new int[n];
        this.TL = 0;
    }

    public No(int info, int pos) {
        this();
        this.vInfo[0] = info;
        this.vPos[0] = pos;
        this.TL = 1;
    }

    public void remanejar(int pos) {
        for (int i = TL; i > pos ; i++) {
            vInfo[i] = vInfo[i - 1];
            vPos[i] = vPos[i - 1];
        }
    }

    public int getvInfo(int pos) {
        return vInfo[pos];
    }

    public void setvInfo(int info, int pos) {
        this.vInfo[pos] = info;
    }

    public int getvPos(int pos) {
        return vPos[pos];
    }

    public void setvPos(int posV, int pos) {
        this.vPos[pos] = posV;
    }

    public int getTL() {
        return TL;
    }

    public void setTL(int TL) {
        this.TL = TL;
    }
}
