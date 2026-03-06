package service;

import communication.Comunicador;

public class RecuperadorDeSenha {

    private final Comunicador comunicador;
    public RecuperadorDeSenha(Comunicador comunicador) {
        this.comunicador = comunicador;
    }

    public void recuperar(String email) {
        String link = "http://techstore.com/reset?token=123";
        comunicador.enviar("Clique no link para redefinir sua senha: " + link);
    }
}
