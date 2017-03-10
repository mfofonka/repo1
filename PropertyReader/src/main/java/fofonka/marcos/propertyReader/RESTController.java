package fofonka.marcos.propertyReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTController {
	
	private static final String CONFIG_PROPERTIES_PATH = "/bea/track25000/config.properties";
	Properties prop = new Properties();
	{
		try (FileReader flReader = new FileReader(CONFIG_PROPERTIES_PATH)) {
			prop.load(flReader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/refreshProperties")
	public String refreshProperties() {
		String ret = "";
		try (FileReader flReader = new FileReader(CONFIG_PROPERTIES_PATH)) {
			prop.load(flReader);
			Enumeration<Object> keys = prop.keys();
			while (keys.hasMoreElements()) {
			    String key = (String)keys.nextElement();
			    String value = (String)prop.get(key);
			    ret += (key + ": " + value+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
}
