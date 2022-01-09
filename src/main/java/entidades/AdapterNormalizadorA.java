package entidades;

public class AdapterNormalizadorA implements INormalizadorDeImagenes{
    private NormalizadorX normalizadorAdaptado;
    private int height;
    private int width;
    private int calidadMinima;
    private int calidadMaxima;

    public String normalizar(String stringANormalizar) {return null;}

    //////////////  Getters y setters   ////////////

    public NormalizadorX getNormalizadorAdaptado() {
        return normalizadorAdaptado;
    }

    public void setNormalizadorAdaptado(NormalizadorX normalizadorAdaptado) {
        this.normalizadorAdaptado = normalizadorAdaptado;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getCalidadMinima() {
        return calidadMinima;
    }

    public void setCalidadMinima(int calidadMinima) {
        this.calidadMinima = calidadMinima;
    }

    public int getCalidadMaxima() {
        return calidadMaxima;
    }

    public void setCalidadMaxima(int calidadMaxima) {
        this.calidadMaxima = calidadMaxima;
    }

    @Override
    public String normalizar(String aNormalizar, int width, int height) {
        return null;
    }
}