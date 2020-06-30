import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class SendMessageWhatsApp {
	public static void main(String[] args){ try {
		URL url = new URL("https://api.gupshup.io/sm/api/v1/msg?apikey=c8c8ede8111349a2c9e0156b72dd468b&channel=whatsapp&source=917834811114&destination=917057958566&message=Hello");
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.connect();
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		StringBuffer buffer = new StringBuffer(); while ((line = rd.readLine()) != null){
			buffer.append(line).append("\n");
		}
		System.out.println(buffer.toString());
		rd.close();
		conn.disconnect();
	}
	catch(Exception e){ 
		e.printStackTrace(); 
	}
	}
} 
/*https://api.gupshup.io/sm/api/v1/msg \
-H 'Cache-Control: no-cache' \
-H 'Content-Type: application/x-www-form-urlencoded' \
-H 'apikey: c8c8ede8111349a2c9e0156b72dd468b' \
-H 'cache-control: no-cache' \
-d 'channel=whatsapp&source=917834811114&destination=&message='*/