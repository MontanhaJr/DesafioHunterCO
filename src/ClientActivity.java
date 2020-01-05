import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
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

public class ClientActivity extends JFrame {
	ArrayList<Registro> registro;
	ArrayList<Cliente> cliente = new ArrayList();
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientActivity frame = new ClientActivity();
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
	public ClientActivity() {
		setResizable(false);
		importarDadosJson();
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainActivity.class.getResource("/Imagens/favicon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Cliente com mais Rejeições");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		verificarClientes();
	}
		
		private void verificarClientes() {
			
			String nomeCliente;
			int contador = 0;
			for (int i = 0; i < registro.size(); i++) {
				nomeCliente = registro.get(i).getClient_id();
				for (int j = 0; j < registro.size(); j++) {
					if(nomeCliente.equalsIgnoreCase(registro.get(j).getClient_id()) && registro.get(j).getEventType().equalsIgnoreCase("CANDIDATE_SENT")) 
					{
						contador++;
					}
				}
				Cliente cli = new Cliente();
				cli.setNome(nomeCliente);
				cli.setContador(contador);
				this.cliente.add(cli);
				contador = 0;
			}
			
			int maiorNum = 0;
			String maiorNome = "";
			
			for (int i = 0; i < cliente.size(); i++) {
				if(this.cliente.get(i).getContador() > maiorNum)
				{
					maiorNum = this.cliente.get(i).getContador();
					maiorNome = this.cliente.get(i).getNome();
				}
				
			}
			
			JLabel lblMaisRejeitou = new JLabel("jLabel");
			lblMaisRejeitou.setHorizontalAlignment(SwingConstants.CENTER);
			lblMaisRejeitou.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblMaisRejeitou.setBounds(10, 11, 424, 58);
			lblMaisRejeitou.setText("Cliente que mais rejeitou:\r\n");
			contentPane.add(lblMaisRejeitou);
			
			JLabel lblNumero = new JLabel("New label");
			lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
			lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNumero.setBounds(10, 143, 424, 107);
			lblNumero.setText("Número de rejeições: "+maiorNum);
			contentPane.add(lblNumero);
			
			JLabel lblName = new JLabel("New label");
			lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
			lblName.setBounds(10, 74, 424, 58);
			lblName.setText(maiorNome);
			contentPane.add(lblName);
			
			
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
