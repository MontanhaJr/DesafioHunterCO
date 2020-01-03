import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;

public class CandidateActivity extends JFrame {
	ArrayList<Registro> pessoa;
	ArrayList<Candidato> candidato = new ArrayList();
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CandidateActivity frame = new CandidateActivity();
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
	public CandidateActivity() {
		importarDadosJson();
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainActivity.class.getResource("/Imagens/favicon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Candidato com mais rejeições");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		verificarRejeicao();
	}
	
	private void verificarRejeicao() {
		String nome;
		int contador = 0;
		for (int i = 0; i < pessoa.size(); i++) {
			nome = pessoa.get(i).getCandidate();
			for (int j = 0; j < pessoa.size(); j++) {
				if (nome.equals(pessoa.get(j).getCandidate())) {
					contador++;
				}
			}
			Candidato cand = new Candidato();
			cand.setNome(nome);
			cand.setContador(contador);
			this.candidato.add(cand); 
			contador = 0;
		}
		
		int maiorNum = 0;
		String maiorNome = "";
		for (int i = 0; i < candidato.size(); i++) {
			if(this.candidato.get(i).getContador() > maiorNum)
			{
				maiorNum = this.candidato.get(i).getContador();
				maiorNome = this.candidato.get(i).getNome();
			}
			
		}
//		System.out.println("Candidato mais rejeitado: "+maiorNome);
//		System.out.println("Número de rejeições: "+maiorNum);
		
		JLabel lblNome = new JLabel("jLabel");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome.setBounds(10, 11, 414, 107);
		lblNome.setText("Candidato mais rejeitado: "+maiorNome);
		contentPane.add(lblNome);
		
		JLabel lblNumero = new JLabel("New label");
		lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNumero.setBounds(10, 143, 414, 107);
		lblNumero.setText("Número de rejeições: "+maiorNum);
		contentPane.add(lblNumero);
		
		
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
			this.pessoa = gson.fromJson(myResponse.toString(), collectionType);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
}
