
public class Cliente {
	private String nome;
	private int contador;
	
	public Cliente(String nome, int contador) {
		super();
		this.nome = nome;
		this.contador = contador;
	}
	
	public Cliente() {
		
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getContador() {
		return contador;
	}
	public void setContador(int contador) {
		this.contador = contador;
	}
}
