package utils;

abstract public class telaLogada {
    private static int numeroDaTela;

    public static int getNumeroDaTela() {
        return numeroDaTela;
    }

    public static void setNumeroDaTela(int numeroDaTela) {
        telaLogada.numeroDaTela = numeroDaTela;
    }
}
