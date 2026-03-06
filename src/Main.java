import communication.ServicoEmail;
import communication.ServicoSMS;
import service.RecuperadorDeSenha;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== RECUPERAÇÃO POR EMAIL =====");

        RecuperadorDeSenha recuperadorEmail = new RecuperadorDeSenha(new ServicoEmail());
        recuperadorEmail.recuperar("gabrielli@gmail.com");

        System.out.println("\n===== RECUPERAÇÃO POR SMS =====");

        RecuperadorDeSenha recuperadorSMS = new RecuperadorDeSenha(new ServicoSMS());
        recuperadorSMS.recuperar("gabrielli@gmail.com");

    }
}