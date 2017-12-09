import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class testeBanco {
	public static void main(String[] args) {
		
	}

	public boolean leEntradas() {
		File f = new File("./entradas.txt");
		if (!f.existis()) {
			System.out.println("Arquivo não encontrado");
			return false;
		}
		
	}

	public void checarEntradas() {

	}
}