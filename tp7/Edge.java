/**
 * TP 9: codage d'un graphe - classe Edge
 */

public class Edge {
    private int src;
    private int dst;
    private String lab;
    private boolean isDir;

    /**
     * Constructeur
     */
    public Edge(int src, int dst, String lab, boolean b) {
        this.setSrc(src);
        this.setDst(dst);
        this.setLab(lab);
        this.setDir(b);
    }

    /* Méthodes d'acces et d'affectation */

    public boolean isDir() {
        return isDir;
    }

    public void setDir(boolean isDir) {
        this.isDir = isDir;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public int getDst() {
        return dst;
    }

    public void setDst(int dst) {
        this.dst = dst;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    /**
     * Affiche l’arc avec son étiquette
     */
    public String toString() {
        if (isDir)
            return String.format("%s -> %s [label = %s]", src, dst, lab);
        else
            return String.format("%s -- %s [label = %s]", src, dst, lab);
    }

    /**
     * Le main teste l’implémentation
     */
    public static void main(String[] args) {
    }
}
