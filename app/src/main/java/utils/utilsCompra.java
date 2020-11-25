package utils;

abstract public class utilsCompra {
    private static String novaCompra;
    private static int ultimaVenda;
    private static double totalCompra;
    private static double troco;
    private static int idCompraSelecionada;
    private static int idVendaSelecionada;
    private static int idPedidoRealizado;
    private static int idDetalhesPedido;


    public static String getNovaCompra() {
        return novaCompra;
    }

    public static void setNovaCompra(String novaCompra) {
        utilsCompra.novaCompra = novaCompra;
    }

    public static int getUltimaVenda() {
        return ultimaVenda;
    }

    public static void setUltimaVenda(int ultimaVenda) {
        utilsCompra.ultimaVenda = ultimaVenda;
    }

    public static double getTotalCompra() {
        return totalCompra;
    }

    public static void setTotalCompra(double totalCompra) {
        utilsCompra.totalCompra = totalCompra;
    }

    public static double getTroco() {
        return troco;
    }

    public static void setTroco(double troco) {
        utilsCompra.troco = troco;
    }

    public static int getIdCompraSelecionada() {
        return idCompraSelecionada;
    }

    public static void setIdCompraSelecionada(int idCompraSelecionada) {
        utilsCompra.idCompraSelecionada = idCompraSelecionada;
    }

    public static int getIdVendaSelecionada() {
        return idVendaSelecionada;
    }

    public static void setIdVendaSelecionada(int idVendaSelecionada) {
        utilsCompra.idVendaSelecionada = idVendaSelecionada;
    }

    public static int getIdPedidoRealizado() {
        return idPedidoRealizado;
    }

    public static void setIdPedidoRealizado(int idPedidoRealizado) {
        utilsCompra.idPedidoRealizado = idPedidoRealizado;
    }

    public static int getIdDetalhesPedido() {
        return idDetalhesPedido;
    }

    public static void setIdDetalhesPedido(int idDetalhesPedido) {
        utilsCompra.idDetalhesPedido = idDetalhesPedido;
    }
}
