package communication;

public class ServicoEmail implements Comunicador {
    @Override
    public void enviar(String mensagem) {
        System.out.println("ENVIANDO EMAIL SMTP: " + mensagem);
    }
}
