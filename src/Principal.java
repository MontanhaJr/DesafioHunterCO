import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class Principal {
	
	public static void main(String[] args) {
  
		importarDadosJson();
	}

	private static void importarDadosJson() {
		Gson gson = new Gson();
		try {
			String url = "https://hunterco-web-static.s3-us-west-2.amazonaws.com/docs/dados_desafio.json";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			
			int responseCode = con.getResponseCode();
//			System.out.println("\nEnviando requisição 'GET' para a url: "+url);
//			System.out.println("Código resposta: "+responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
		
			JSONArray myResponse = new JSONArray(response.toString());
			Type collectionType = new TypeToken<ArrayList<Registro>>(){}.getType();
			ArrayList<Registro> pessoa = gson.fromJson(myResponse.toString(), collectionType);
			
			float valorBruto = 0;
			float valorLiquido = 0;
			float valorPerdido = 0;
			
			for (int i = 0; i < pessoa.size(); i++) {
				valorLiquido += (float)pessoa.get(i).getValue();
				valorLiquido -= (float)pessoa.get(i).getLost_value();
				valorBruto += (float)pessoa.get(i).getValue();
				valorPerdido -= (float)pessoa.get(i).getLost_value();
			}
//			
			System.out.println("Total: $"+valorBruto);
			System.out.println("Lucro Total: $"+valorLiquido);
			System.out.println("Gasto Total: $"+valorPerdido);

			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		

    }
	
}
