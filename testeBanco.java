import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
//import java.util.GreagorianCalendar;
import java.text.SimpleDateFormat;

public class testeBanco {

	File f = new File("./entradas.txt");

	public static void main(String[] args) {
		testeBanco c = new testeBanco();
		c.lerEntradas();
	}

	public boolean lerEntradas() {
		if (!f.exists()) {
			System.out.println("Arquivo não encontrado");
			return false;
		}
		try {
			FileReader fileReader = new FileReader(f);
		    BufferedReader reader = new BufferedReader(fileReader);
		    String data = null;
		    ArrayList<String> linhas = new ArrayList<String>();
		    while((data = reader.readLine()) != null) {
			    if (data != null) {
			    	
			    	if (checarEntradas(data)) {
				        linhas.add(data);
			    	}
			    }
		    }
			System.out.println("Numero de entradas validas: " + linhas.size());
		    fileReader.close();
		    reader.close();
		}

		catch(FileNotFoundException ex) {
			System.out.println("Arquivo não encontrado");
			System.out.println("Recriando o Arquivo");
			tratandoFileNotFoundException();
		}

		catch(IOException ex) {
			System.out.println("O programa parou de funcionar, tente reiniciar");
		}
		
		return true;
	}
	public boolean checarEntradas(String entrada) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE,00);
		calendar.set(Calendar.SECOND, 00);

		String linha = entrada;
		linha = linha.replace("[", "");
		String[] linhaSplit = linha.split("]");
		String data = linhaSplit[0].split(" ")[0];
		String horario = linhaSplit[0].split(" ")[1];
		String validade = linhaSplit[1].replace(" - ", "");

        calendar.set(Integer.parseInt(data.split("-")[0]), Integer.parseInt(data.split("-")[1]), Integer.parseInt(data.split("-")[2]));
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        Date abertura = calendar.getTime();
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        Date fechamento = calendar.getTime();
			
		int ano = Integer.parseInt(data.split("-")[0]);
		int mes = Integer.parseInt(data.split("-")[1]);
		int dia = Integer.parseInt(data.split("-")[2]);
		int hora = Integer.parseInt(horario.split(":")[0]);
		int min = Integer.parseInt(horario.split(":")[1]);
		int sec = Integer.parseInt(horario.split(":")[2]);
			
		calendar.set(ano, mes, dia, hora, min, sec);
		Date date = calendar.getTime();
		long aber = abertura.getTime();
		Long fec = fechamento.getTime();
		Long time = date.getTime();
		if (aber < time && fec > time && validade.equals("Abertura da Porta OK")) {
		    System.out.println(entrada + " --> " + "Valida");	
		    return true;
		}
		else {
			System.out.println(entrada + " --> " + "inValida");
			return false;
		}
	}


	private void tratandoFileNotFoundException() {
		if (!f.exists()) {
			try {
			    f.createNewFile();
		    } catch (IOException e) {
			    e.printStackTrace();
			    System.out.println("Não foi possivel recriar o Arquivo 'entradas.txt'");
		    }
			System.out.println("Arquivo Criado");
		}
	}
}