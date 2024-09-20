import java.util.Scanner;

public class VerificacaoCombo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Entrada dos serviços contratados pelo cliente
        System.out.println("Digite os serviços contratados pelo cliente, separados por vírgula:");
        String entradaServicos = scanner.nextLine().trim();
        
        // Separando os serviços contratados
        String[] servicos = entradaServicos.split(",");
        
        // Flags para verificar a presença de cada serviço
        boolean movel = false;
        boolean bandaLarga = false;
        boolean tv = false;
        
        // Verificando cada serviço na lista
        for (String servico : servicos) {
            if (servico.trim().equalsIgnoreCase("movel")) {
                movel = true;
            } else if (servico.trim().equalsIgnoreCase("banda larga")) {
                bandaLarga = true;
            } else if (servico.trim().equalsIgnoreCase("tv")) {
                tv = true;
            }
        }
        
        // Saída
        if (movel && bandaLarga && tv) {
            System.out.println("Combo Completo");
        } else {
            System.out.println("Combo Incompleto");
        }
        
        scanner.close();
    }
}
