package utils;

import android.widget.ImageView;

abstract public class utilsProduto {

    private static int idProdSelecionado;
    private static int idTipoProd;
    private static String nomeAdicionais;
    private static String nomeAdicionaisText;
    private static String nomeTipoProd;
    private static ImageView imgDoProduto;

    public static int getIdProdSelecionado() {
        return idProdSelecionado;
    }

    public static void setIdProdSelecionado(int idProdSelecionado) {
        utilsProduto.idProdSelecionado = idProdSelecionado;
    }

    public static int getIdTipoProd() {
        return idTipoProd;
    }

    public static void setIdTipoProd(int idTipoProd) {
        utilsProduto.idTipoProd = idTipoProd;
    }

    public static String getNomeAdicionais() {
        return nomeAdicionais;
    }

    public static void setNomeAdicionais(String nomeAdicionais) {
        utilsProduto.nomeAdicionais = nomeAdicionais;
    }

    public static String getNomeAdicionaisText() {
        return nomeAdicionaisText;
    }

    public static void setNomeAdicionaisText(String nomeAdicionaisText) {
        utilsProduto.nomeAdicionaisText = nomeAdicionaisText;
    }

    public static String getNomeTipoProd() {
        return nomeTipoProd;
    }

    public static void setNomeTipoProd(String nomeTipoProd) {
        utilsProduto.nomeTipoProd = nomeTipoProd;
    }

    public static ImageView getImgDoProduto() {
        return imgDoProduto;
    }

    public static void setImgDoProduto(ImageView imgDoProduto) {
        utilsProduto.imgDoProduto = imgDoProduto;
    }
}
