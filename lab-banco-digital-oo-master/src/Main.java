
public class Main {

	public static void main(String[] args) {
		Cliente misa = new Cliente();
		misa.setNome("Misa");
		
		Conta cc = new ContaCorrente(misa);
		Conta poupanca = new ContaPoupanca(misa);

		cc.depositar(3000);
		cc.transferir(1500, poupanca);
		
		cc.imprimirExtrato();
		poupanca.imprimirExtrato();
	}

}
