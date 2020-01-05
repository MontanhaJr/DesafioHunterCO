import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class ClientAllActivity extends JFrame {
	ArrayList<Registro> registro;
	ArrayList<Cliente> cliente = new ArrayList();
	private JPanel contentPane;
	
	JScrollPane scrollPane;
	JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientAllActivity frame = new ClientAllActivity();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientAllActivity() {
		setResizable(false);
		importarDadosJson();
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainActivity.class.getResource("/Imagens/favicon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 300);
		setTitle("Clientes que Rejeitaram Todos");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRejeitouTodos = new JLabel("jLabel");
		lblRejeitouTodos.setHorizontalAlignment(SwingConstants.CENTER);
		lblRejeitouTodos.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblRejeitouTodos.setBounds(10, 11, 424, 58);
		lblRejeitouTodos.setText("Clientes que rejeitaram todos:\r\n");
		contentPane.add(lblRejeitouTodos);
					
		verificarClientes();
	}
		
		private void verificarClientes() {
			
			String nomeCliente;
			int contador = 0;
			int contadorRej = 0;
			
			for (int i = 0; i < registro.size(); i++) {
				nomeCliente = registro.get(i).getClient_id();
				for (int j = 0; j < registro.size(); j++) {
					if(nomeCliente.equalsIgnoreCase(registro.get(j).getClient_id())) 
					{
						contador++;
						if(registro.get(j).getEventType().equalsIgnoreCase("CANDIDATE_SENT"))
						{
							contadorRej++;
						}
					}
				}
				
				if(contador == contadorRej)
				{
					Cliente cli = new Cliente();
					cli.setNome(nomeCliente);
					cli.setContador(contador);	
					this.cliente.add(cli);				
					contador = 0;	
					contadorRej = 0;
				} else {
					contador = 0;	
					contadorRej = 0;
				}
				
			}
			
			String nomeClienteRepetido;
			String textoFinal = "";
			int x=0;
			
			do {
				for (int i = 0; i < cliente.size(); i++) {
					nomeClienteRepetido = cliente.get(i).getNome();
					for (int j = i+1; j < cliente.size(); j++) {
						if (nomeClienteRepetido.equalsIgnoreCase(cliente.get(j).getNome())) {
							cliente.remove(cliente.get(j));
						}
					}	
				}
				x++;
			}while(x<2);
		
			for (int i = 0; i < cliente.size(); i++) {
				textoFinal = textoFinal+" - Cliente: "+cliente.get(i).getNome()+"\n   Candidaturas: "+cliente.get(i).getContador()+"\n\n";
			}
			
			textArea = new JTextArea();
			textArea.setFont(new Font("Microsoft YaHei", Font.PLAIN, 13));
			textArea.setEditable(false);
			textArea.setBounds(1, 1, 423, 168);
			textArea.setText(textoFinal);
			textArea.setSelectionStart(0);
			textArea.setSelectionEnd(0);
			contentPane.add(textArea);
			
			scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setBounds(10, 80, 424, 170);
			getContentPane().add(scrollPane);
			
		}
	
	private void importarDadosJson() {
		Gson gson = new Gson();
		try {
			String url = "https://hunterco-web-static.s3-us-west-2.amazonaws.com/docs/dados_desafio.json";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
//			int responseCode = con.getResponseCode();
//			System.out.println("\nEnviando requisição 'GET' para a url: "+url);
//			System.out.println("Código resposta: "+responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
		
			JSONArray myResponse = new JSONArray(response.toString());
			java.lang.reflect.Type collectionType = new TypeToken<ArrayList<Registro>>(){}.getType();
			this.registro = gson.fromJson(myResponse.toString(), collectionType);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		

    }
}
