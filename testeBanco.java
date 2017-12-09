import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

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
				    linhas.add(data);
			    }
		    }
		    checarEntradas(linhas);
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

	public void checarEntradas(ArrayList<String> entradas) {
		Calendar calendar = Calendar.getInstance();

		for (int i = 0; i < entradas.size(); i++) {
			String linha = entradas.get(i);
			linha = linha.replace("[", "");
			String[] linhaSplit = linha.split("]");
			String data = linhaSplit[0].split(" ")[0];
			String horario = linhaSplit[0].split(" ")[1];
			String validade = linhaSplit[1];

			
			String ddd = data.split("-")[0]+ "-" + data.split("-")[1] + "-" + data.split("-")[2]+ "-" + horario.split(":")[0]+ "-" + horario.split(":")[1]+ "-" + horario.split(":")[2];
			calendar.set(Integer.parseInt(data.split("-")[0]), Integer.parseInt(data.split("-")[1]), Integer.parseInt(data.split("-")[2]), Integer.parseInt(horario.split(":")[0]), Integer.parseInt(horario.split(":")[1]), Integer.parseInt(horario.split(":")[2]));
			System.out.println(calendar.getTimeInMillis());
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