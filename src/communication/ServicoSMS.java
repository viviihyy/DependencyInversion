package communication;

public class ServicoSMS implements Comunicador {
    @Override
    public void enviar(String mensagem) {
        System.out.println("ENVIANDO SMS: " + mensagem);
    }
}
