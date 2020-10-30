import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LebOro2 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

		//Generar los ficheros CSV
		Path equipos = Paths.get("D:\\PRUEBAS\\equipos.csv");
		Path jugadores = Paths.get("D:\\PRUEBAS\\jugadores.csv");

		BufferedWriter bwE = Files.newBufferedWriter(equipos);
		BufferedWriter bwJ = Files.newBufferedWriter(jugadores);

		//Leer la información del fichero XML
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		FileInputStream fis = new FileInputStream("D:\\PRUEBAS\\leboro.xml");
		Document documento = db.parse(fis);

		NodeList codequipo = documento.getElementsByTagName("codequipo");
		NodeList nombreEq = documento.getElementsByTagName("nombreEq");
		NodeList Ciudad = documento.getElementsByTagName("Ciudad");
		NodeList anho = documento.getElementsByTagName("anho");
		NodeList codjugador = documento.getElementsByTagName("codjugador");
		NodeList nombreJu = documento.getElementsByTagName("nombreJu");
		NodeList posicion = documento.getElementsByTagName("posicion");
		NodeList codequipoJu = documento.getElementsByTagName("codequipoJu");

		//Mostrar el nombre del equipo con código 200
		for (int i = 0; i < codequipo.getLength(); i++) {
			if (codequipo.item(i).getTextContent().equals("200")) {
				System.out.println(nombreEq.item(i).getTextContent());
			}

			bwE.write(codequipo.item(i).getTextContent() + ";" + nombreEq.item(i).getTextContent() + ";" + 
					Ciudad.item(i).getTextContent() + ";" + anho.item(i).getTextContent() + "\n"); 

		}

		//Nombre del jugador con código 50 y posición del jugador con código 40
		for (int i = 0; i < codjugador.getLength(); i++) {
			if (codjugador.item(i).getTextContent().equals("50")) {
				System.out.println(nombreJu.item(i).getTextContent());
			}
			else if (codjugador.item(i).getTextContent().equals("40")) {
				System.out.println(posicion.item(i).getTextContent());
			}

			bwJ.write(codjugador.item(i).getTextContent() + ";" + nombreJu.item(i).getTextContent() + ";" + 
					posicion.item(i).getTextContent() + ";" + codequipoJu.item(i).getTextContent() + "\n"); 

		}

		bwE.flush();
		bwJ.flush();

		bwE.close();
		bwJ.close();

	}

}
