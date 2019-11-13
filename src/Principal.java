import java.util.*;

public class Principal {
	
	public static ArrayList<Objeto> llenarInventario(ArrayList<Objeto> candidatos, Inventario inventario) {
		// Desde este algoritmo se debe llamar a seleccionarCandidato() y a esCandidatoFactible()
		boolean[] visitados = new boolean[candidatos.size()];
		boolean esSol = false;
		ArrayList<Objeto> solucion = new ArrayList<>();
		for (int i = 0; i <visitados.length ; i++) {
			visitados[i]=false;
		}
		int i = 0;
		while (candidatos.size()!=0){
			Objeto mejor = seleccionarCandidato(candidatos);
			if (esCandidatoFactible(mejor,inventario)){
				solucion.add(mejor);
			}

		}
		return solucion;
	}
	
	private static Objeto seleccionarCandidato(ArrayList<Objeto> candidatos) {
		Objeto mejor = null;
		int i = 0;
		for (Objeto objeto: candidatos
			 ) {
			i++;
			if (mejor==null){
				mejor = objeto;
			}
			else if(((calcularValorMedio(calcularArea(mejor.getAlto(),mejor.getAncho()),mejor.getValor()))<= calcularValorMedio(calcularArea(objeto.getAlto(),objeto.getAncho()),objeto.getValor()))){
				mejor = objeto;
			}
		}
		candidatos.remove(mejor);
		return mejor;
	}
	
	private static boolean esCandidatoFactible(Objeto candidato, Inventario inventario) {
		int posXlibre=0;int posXfinal =0;
		int posYlibre=0;int posYfinal =0;
		boolean encontrado = false;boolean ocupado=false;
		for (int j = 0; j <inventario.getN() &&!encontrado; j++) {
			for (int i = 0; i < inventario.getN() &&!encontrado ; i++) {
			if (inventario.getCelda(i,j)==-1 && (i+candidato.getAncho())<=inventario.getN() &&j+candidato.getAlto()<=inventario.getN()){
				//inventario.setCelda(i,j,candidato.getValor());
				encontrado=true;
				posXlibre=i;
				posYlibre=j;
			}
			}
		}
		if (encontrado){
			for (int i = posXlibre; i<posXlibre+candidato.getAncho() ; i++) {
				for (int j = posYlibre; j < posYlibre+candidato.getAlto(); j++) {
					inventario.setCelda(i,j,candidato.getId());
				}
			}
		}
		//Rellenamos la array
		return encontrado;
	}
	private static int calcularArea(int alto,int ancho){
		return alto*ancho;
	}
	private static int calcularValorMedio(int area,int valor){
		return valor/area;
	}
}
