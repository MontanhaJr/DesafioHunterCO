
import java.awt.BorderLayout;
import java.awt.EventQueue;

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.awt.Choice;
import javax.swing.SwingConstants;
import javax.swing.JButton;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URLConnection;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.GsonBuilder;
import javax.swing.JTextArea;


public class ValuesActivity extends JFrame {
	ArrayList<Registro> registro;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ValuesActivity frame = new ValuesActivity();
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
	public ValuesActivity() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainActivity.class.getResource("/Imagens/favicon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Valores");
		setResizable(false);
		setBounds(100, 100, 300, 255);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		importarDadosJson();
		
		JLabel lblTotalFaturadoE = new JLabel("Total Faturado e Perdido");
		lblTotalFaturadoE.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTotalFaturadoE.setBounds(10, 5, 195, 20);
		contentPane.add(lblTotalFaturadoE);
		
		Choice choiceIn = new Choice();
		choiceIn.setBounds(68, 36, 90, 20);
		choiceIn.add("Janeiro");
		choiceIn.add("Fevereiro");
		choiceIn.add("Março");
		choiceIn.add("Abril");
		choiceIn.add("Maio");
		choiceIn.add("Junho");
		choiceIn.add("Julho");
		choiceIn.add("Agosto");
		choiceIn.add("Setembro");
		choiceIn.add("Outubro");
		choiceIn.add("Novembro");
		choiceIn.add("Dezembro");
		contentPane.add(choiceIn);
		
		Choice choiceFim = new Choice();
		choiceFim.setBounds(186, 36, 90, 20);
		choiceFim.add("Janeiro");
		choiceFim.add("Fevereiro");
		choiceFim.add("Março");
		choiceFim.add("Abril");
		choiceFim.add("Maio");
		choiceFim.add("Junho");
		choiceFim.add("Julho");
		choiceFim.add("Agosto");
		choiceFim.add("Setembro");
		choiceFim.add("Outubro");
		choiceFim.add("Novembro");
		choiceFim.add("Dezembro");
//		lblTotalFaturadoE.setText(choiceFim.getItem(choiceFim.getSelectedIndex()));
		contentPane.add(choiceFim);
		
		JLabel lblPeriodo = new JLabel("Per\u00EDodo:");
		lblPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPeriodo.setBounds(10, 36, 52, 20);
		contentPane.add(lblPeriodo);
		
		JLabel label = new JLabel("\u00E0");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(164, 36, 16, 20);
		
		contentPane.add(label);
		
		Choice choiceYear = new Choice();
		choiceYear.setBounds(218, 5, 58, 20);
		choiceYear.add("2018");
		contentPane.add(choiceYear);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 93, 266, 111);
		textArea.setFont(new Font("Yu Gothic Medium", Font.BOLD, 16));
		contentPane.add(textArea);
		
		JButton btnVisualizar = new JButton("Visualizar");
		btnVisualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				verificarGastos(choiceIn.getSelectedIndex(), choiceFim.getSelectedIndex());

			}

			private void verificarGastos(int inicio, int fim) {
				
				float valorBruto = 0;
				float valorLiquido = 0;
				float valorPerdido = 0;
				
				String periodo[] = new String[2];
				int mes = 0;
				
				for (int i = 0; i < registro.size(); i++) {
					periodo = registro.get(i).getPeriodo_cobranca().split("-");
					mes = Integer.parseInt(periodo[1]);
					
					if(mes >= inicio+1 && mes <= fim+1 || mes <= inicio+1 && mes >= fim+1)
					{
						valorLiquido += (float)registro.get(i).getValue();
						valorLiquido -= (float)registro.get(i).getLost_value();
						valorBruto += (float)registro.get(i).getValue();
						valorPerdido -= (float)registro.get(i).getLost_value();
					}
				}
				
				if (valorPerdido < 0)
				valorPerdido = valorPerdido*(-1);
				
				textArea.setText(String.format("Total: $%.2f \n"
						+ "Lucro Total: $%.2f \n"
						+ "Gasto Total: $%.2f", valorBruto, valorLiquido, valorPerdido));
			}
		});
		btnVisualizar.setBounds(10, 62, 264, 23);
		contentPane.add(btnVisualizar);
		
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
