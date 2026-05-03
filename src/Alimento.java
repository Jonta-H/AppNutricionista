public class Alimento {

    private String nome;
    private double calorias;
    private double carboidratos;
    private double proteinas;
    private double gorduras;
    private double fibras;
    private double porcaoReferencia;

    public Alimento() {
    }

    public Alimento(String nome, double calorias, double carboidratos,
                    double proteinas, double gorduras, double fibras,
                    double porcaoReferencia) {
        this.nome = nome;
        this.calorias = calorias;
        this.carboidratos = carboidratos;
        this.proteinas = proteinas;
        this.gorduras = gorduras;
        this.fibras = fibras;
        this.porcaoReferencia = porcaoReferencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String n) {
        this.nome = n;
    }

    public double getCalorias() {
        return calorias;
    }

    public void setCalorias(double c) {
        this.calorias = c;
    }

    public double getCarboidratos() {
        return carboidratos;
    }

    public void setCarboidratos(double v) {
        this.carboidratos = v;
    }

    public double getProteinas() {
        return proteinas;
    }

    public void setProteinas(double v) {
        this.proteinas = v;
    }

    public double getGorduras() {
        return gorduras;
    }

    public void setGorduras(double v) {
        this.gorduras = v;
    }

    public double getFibras() {
        return fibras;
    }

    public void setFibras(double v) {
        this.fibras = v;
    }

    public double getPorcaoReferencia() {
        return porcaoReferencia;
    }

    public void setPorcaoReferencia(double v) {
        this.porcaoReferencia = v;
    }
}
