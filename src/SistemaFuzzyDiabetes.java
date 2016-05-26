import java.util.ArrayList;

public class SistemaFuzzyDiabetes {
	private static String [] conjuntosEdad={"Niño","Adolescente","Joven","Adulto","3ra Edad"};
	private static String [] conjuntosGlucosa={"Baja","Ideal","Alta"};
	private static String [] conjuntosAminoacidos={"Bajo","Ideal","Alto"};
	private static String [] conjuntosIMC={"Bajo","Ideal","Sobrepeso","Obesidad"};
	private static String [] conjuntosCondicionDiabetico={"No diabético","Pre-diabético","Diabético"};
	private static double [] nivsMemEdad=new double[5];
	private static double [] nivsMemGlucosa=new double[3];
	private static double [] nivsMemAminoacidos=new double[3];
	private static double [] nivsMemIMC=new double[4];
	private static double [] nivsMemCondicionDiabetico=new double[3];
	private static ArrayList<String> condiciones = new ArrayList<>();
	
	public static void inicializar(){
		for(int i=0,j=conjuntosCondicionDiabetico.length;i<j;i++)
			condiciones.add(conjuntosCondicionDiabetico[i]);
	}
	private static int posNivMemMay(double [] nivsMem){
		int posMay=0;
		for(int i=0;i<nivsMem.length;i++)
			if (nivsMem[i]>nivsMem[posMay]) posMay=i;
		return posMay;
	}
	public static void muestraNivMemMay(String msg, double [] nivsMem){    
		System.out.print(msg+": [");
		for(int i=0;i<nivsMem.length;i++)
			System.out.print(nivsMem[i]+(i+1==nivsMem.length?"]":", "));
		System.out.println();
	}
	private static void prodMembsEdad(double datoNitidoEdad){
		nivsMemEdad[0]=Membresias.funcionZ(datoNitidoEdad, 11, 12);
		nivsMemEdad[1]=Membresias.funcionSoftPi(datoNitidoEdad, 11, 12, 19, 20);
		nivsMemEdad[2]=Membresias.funcionSoftPi(datoNitidoEdad, 19, 20, 25, 26);
		nivsMemEdad[3]=Membresias.funcionSoftPi(datoNitidoEdad, 25, 26, 59, 60);
		nivsMemEdad[4]=Membresias.funcionS(datoNitidoEdad, 59, 60);
		muestraNivMemMay(("Membresías Edad="+datoNitidoEdad),nivsMemEdad);
	}
	private static void prodMembsGlucosa(double datoNitidoGlucosa){   
		nivsMemGlucosa[0]=Membresias.funcionZ(datoNitidoGlucosa, 70, 115);
		nivsMemGlucosa[1]=Membresias.funcionSoftLambda(datoNitidoGlucosa, 70, 115, 180);
		nivsMemGlucosa[2]=Membresias.funcionS(datoNitidoGlucosa, 115, 180);
		muestraNivMemMay(("Membresías Glucosa="+datoNitidoGlucosa),nivsMemGlucosa);
	}   
	private static void prodMembsAminoacidos(double datoNitidoAminoacidos){
		nivsMemAminoacidos[0]=Membresias.funcionZ(datoNitidoAminoacidos, 50, 80);
		nivsMemAminoacidos[1]=Membresias.funcionSoftLambda(datoNitidoAminoacidos, 50, 80, 120);
		nivsMemAminoacidos[2]=Membresias.funcionS(datoNitidoAminoacidos, 80, 120);
		muestraNivMemMay(("Membresías Aminoácidos="+datoNitidoAminoacidos),nivsMemAminoacidos);
	}
	private static void prodMembsIMC(double datoNitidoimc){
		nivsMemIMC[0]=Membresias.funcionZ(datoNitidoimc, 18.4, 20);
		nivsMemIMC[1]=Membresias.funcionSoftPi(datoNitidoimc, 18.4, 20, 23, 25);
		nivsMemIMC[2]=Membresias.funcionSoftPi(datoNitidoimc, 23, 25, 27, 30);
		nivsMemIMC[3]=Membresias.funcionS(datoNitidoimc, 27, 30);
		muestraNivMemMay(("Membresías IMC="+datoNitidoimc),nivsMemIMC);
	}
	public static String fuzzificarEdad(double datoNitidoEdad){
		String conjunto="";
		prodMembsEdad(datoNitidoEdad);
		conjunto=conjuntosEdad[posNivMemMay(nivsMemEdad)];
		return conjunto;
	}
	public static String fuzzificarGlucosa(double datoNitidoGlucosa){
		String conjunto="";   
		prodMembsGlucosa(datoNitidoGlucosa);
		conjunto=conjuntosGlucosa[posNivMemMay(nivsMemGlucosa)];
		return conjunto;
	}
	public static String fuzzificarAminoacidos(double datoNitidoAminoacidos){
		String conjunto=""; 
		prodMembsAminoacidos(datoNitidoAminoacidos);
		conjunto=conjuntosAminoacidos[posNivMemMay(nivsMemAminoacidos)];
		return conjunto;
	}
	public static String fuzzificarIMC(double datoNitidoIMC){
		String conjunto=""; 
		prodMembsIMC(datoNitidoIMC);
		conjunto=conjuntosIMC[posNivMemMay(nivsMemIMC)];
		return conjunto;
	}
	public static String inferirCondicionDifusaCualitativo(String edadDifuso,
			String glucosaDifusa,String aminoacidosDifuso, String imcDifuso){
		String condicionDifusa="";
		if (imcDifuso.equals("Bajo") & edadDifuso.equals("Niño") & (glucosaDifusa.equals("Baja") |
				aminoacidosDifuso.equals("Bajo"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Bajo") & edadDifuso.equals("Adolescente") & (glucosaDifusa.equals("Baja") |
				aminoacidosDifuso.equals("Bajo"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Bajo") & edadDifuso.equals("Joven") & (glucosaDifusa.equals("Baja") |
				aminoacidosDifuso.equals("Bajo"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Bajo") & edadDifuso.equals("Adulto") & (glucosaDifusa.equals("Baja") |
				aminoacidosDifuso.equals("Bajo"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Bajo") & edadDifuso.equals("3ra Edad") & (glucosaDifusa.equals("Baja") |
				aminoacidosDifuso.equals("Bajo"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Bajo") & edadDifuso.equals("Niño") & (glucosaDifusa.equals("Ideal") |
				aminoacidosDifuso.equals("Ideal"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Bajo") & edadDifuso.equals("Adolescente") & (glucosaDifusa.equals("Ideal") |
				aminoacidosDifuso.equals("Ideal"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Bajo") & edadDifuso.equals("Joven") & (glucosaDifusa.equals("Ideal") |
				aminoacidosDifuso.equals("Ideal"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Bajo") & edadDifuso.equals("Adulto") & (glucosaDifusa.equals("Ideal") |
				aminoacidosDifuso.equals("Ideal"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Bajo") & edadDifuso.equals("3ra Edad") & (glucosaDifusa.equals("Ideal") |
				aminoacidosDifuso.equals("Ideal"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Bajo") & edadDifuso.equals("Niño") & (glucosaDifusa.equals("Alta") |
				aminoacidosDifuso.equals("Alto"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Bajo") & edadDifuso.equals("Adolescente") & (glucosaDifusa.equals("Alta") |
				aminoacidosDifuso.equals("Alto"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Bajo") & edadDifuso.equals("Joven") & (glucosaDifusa.equals("Alta") |
				aminoacidosDifuso.equals("Alto"))) condicionDifusa=conjuntosCondicionDiabetico[1];
		else if(imcDifuso.equals("Bajo") & edadDifuso.equals("Adulto") & (glucosaDifusa.equals("Alta") |
				aminoacidosDifuso.equals("Alto"))) condicionDifusa=conjuntosCondicionDiabetico[1];
		else if(imcDifuso.equals("Bajo") & edadDifuso.equals("3ra Edad") & (glucosaDifusa.equals("Alta") |
				aminoacidosDifuso.equals("Alto"))) condicionDifusa=conjuntosCondicionDiabetico[1];
		else if(imcDifuso.equals("Ideal") & edadDifuso.equals("Niño") & (glucosaDifusa.equals("Baja") |
				aminoacidosDifuso.equals("Bajo"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Ideal") & edadDifuso.equals("Adolescente") & (glucosaDifusa.equals("Baja") |
				aminoacidosDifuso.equals("Bajo"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Ideal") & edadDifuso.equals("Joven") & (glucosaDifusa.equals("Baja") |
				aminoacidosDifuso.equals("Bajo"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Ideal") & edadDifuso.equals("Adulto") & (glucosaDifusa.equals("Baja") |
				aminoacidosDifuso.equals("Bajo"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Ideal") & edadDifuso.equals("3ra Edad") & (glucosaDifusa.equals("Baja") |
				aminoacidosDifuso.equals("Bajo"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Ideal") & edadDifuso.equals("Niño") & (glucosaDifusa.equals("Ideal") |
				aminoacidosDifuso.equals("Ideal"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Ideal") & edadDifuso.equals("Adolescente") & (glucosaDifusa.equals("Ideal") |
				aminoacidosDifuso.equals("Ideal"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Ideal") & edadDifuso.equals("Joven") & (glucosaDifusa.equals("Ideal") |
				aminoacidosDifuso.equals("Ideal"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Ideal") & edadDifuso.equals("Adulto") & (glucosaDifusa.equals("Ideal") |
				aminoacidosDifuso.equals("Ideal"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Ideal") & edadDifuso.equals("3ra Edad") & (glucosaDifusa.equals("Ideal") |
				aminoacidosDifuso.equals("Ideal"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Ideal") & edadDifuso.equals("Niño") & (glucosaDifusa.equals("Alta") |
				aminoacidosDifuso.equals("Alto"))) condicionDifusa=conjuntosCondicionDiabetico[1];
		else if(imcDifuso.equals("Ideal") & edadDifuso.equals("Adolescente") & (glucosaDifusa.equals("Alta") |
				aminoacidosDifuso.equals("Alto"))) condicionDifusa=conjuntosCondicionDiabetico[1];
		else if(imcDifuso.equals("Ideal") & edadDifuso.equals("Joven") & (glucosaDifusa.equals("Alta") |
				aminoacidosDifuso.equals("Alto"))) condicionDifusa=conjuntosCondicionDiabetico[1];
		else if(imcDifuso.equals("Ideal") & edadDifuso.equals("Adulto") & (glucosaDifusa.equals("Alta") |
				aminoacidosDifuso.equals("Alto"))) condicionDifusa=conjuntosCondicionDiabetico[2];
		else if(imcDifuso.equals("Ideal") & edadDifuso.equals("3ra Edad") & (glucosaDifusa.equals("Alta") |
				aminoacidosDifuso.equals("Alto"))) condicionDifusa=conjuntosCondicionDiabetico[2];
		else if(imcDifuso.equals("Sobrepeso") & edadDifuso.equals("Niño") & (glucosaDifusa.equals("Baja") |
				aminoacidosDifuso.equals("Bajo"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Sobrepeso") & edadDifuso.equals("Adolescente") & (glucosaDifusa.equals("Baja") |
				aminoacidosDifuso.equals("Bajo"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Sobrepeso") & edadDifuso.equals("Joven") & (glucosaDifusa.equals("Baja") |
				aminoacidosDifuso.equals("Bajo"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Sobrepeso") & edadDifuso.equals("Adulto") & (glucosaDifusa.equals("Baja") |
				aminoacidosDifuso.equals("Bajo"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Sobrepeso") & edadDifuso.equals("3ra Edad") & (glucosaDifusa.equals("Baja") |
				aminoacidosDifuso.equals("Bajo"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Sobrepeso") & edadDifuso.equals("Niño") & (glucosaDifusa.equals("Ideal") |
				aminoacidosDifuso.equals("Ideal"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Sobrepeso") & edadDifuso.equals("Adolescente") & (glucosaDifusa.equals("Ideal") |
				aminoacidosDifuso.equals("Ideal"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Sobrepeso") & edadDifuso.equals("Joven") & (glucosaDifusa.equals("Ideal") |
				aminoacidosDifuso.equals("Ideal"))) condicionDifusa=conjuntosCondicionDiabetico[1];
		else if(imcDifuso.equals("Sobrepeso") & edadDifuso.equals("Adulto") & (glucosaDifusa.equals("Ideal") |
				aminoacidosDifuso.equals("Ideal"))) condicionDifusa=conjuntosCondicionDiabetico[1];
		else if(imcDifuso.equals("Sobrepeso") & edadDifuso.equals("3ra Edad") & (glucosaDifusa.equals("Ideal") |
				aminoacidosDifuso.equals("Ideal"))) condicionDifusa=conjuntosCondicionDiabetico[1];
		else if(imcDifuso.equals("Sobrepeso") & edadDifuso.equals("Niño") & (glucosaDifusa.equals("Alta") |
				aminoacidosDifuso.equals("Alto"))) condicionDifusa=conjuntosCondicionDiabetico[2];
		else if(imcDifuso.equals("Sobrepeso") & edadDifuso.equals("Adolescente") & (glucosaDifusa.equals("Alta") |
				aminoacidosDifuso.equals("Alto"))) condicionDifusa=conjuntosCondicionDiabetico[2];
		else if(imcDifuso.equals("Sobrepeso") & edadDifuso.equals("Joven") & (glucosaDifusa.equals("Alta") |
				aminoacidosDifuso.equals("Alto"))) condicionDifusa=conjuntosCondicionDiabetico[2];
		else if(imcDifuso.equals("Sobrepeso") & edadDifuso.equals("Adulto") & (glucosaDifusa.equals("Alta") |
				aminoacidosDifuso.equals("Alto"))) condicionDifusa=conjuntosCondicionDiabetico[2];
		else if(imcDifuso.equals("Sobrepeso") & edadDifuso.equals("3ra Edad") & (glucosaDifusa.equals("Alta") |
				aminoacidosDifuso.equals("Alto"))) condicionDifusa=conjuntosCondicionDiabetico[2];
		else if(imcDifuso.equals("Obesidad") & edadDifuso.equals("Niño") & (glucosaDifusa.equals("Baja") |
				aminoacidosDifuso.equals("Bajo"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Obesidad") & edadDifuso.equals("Adolescente") & (glucosaDifusa.equals("Baja") |
				aminoacidosDifuso.equals("Bajo"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Obesidad") & edadDifuso.equals("Joven") & (glucosaDifusa.equals("Baja") |
				aminoacidosDifuso.equals("Bajo"))) condicionDifusa=conjuntosCondicionDiabetico[0];
		else if(imcDifuso.equals("Obesidad") & edadDifuso.equals("Adulto") & (glucosaDifusa.equals("Baja") |
				aminoacidosDifuso.equals("Bajo"))) condicionDifusa=conjuntosCondicionDiabetico[1];
		else if(imcDifuso.equals("Obesidad") & edadDifuso.equals("3ra Edad") & (glucosaDifusa.equals("Baja") |
				aminoacidosDifuso.equals("Bajo"))) condicionDifusa=conjuntosCondicionDiabetico[1];
		else if(imcDifuso.equals("Obesidad") & edadDifuso.equals("Niño") & (glucosaDifusa.equals("Ideal") |
				aminoacidosDifuso.equals("Ideal"))) condicionDifusa=conjuntosCondicionDiabetico[1];
		else if(imcDifuso.equals("Obesidad") & edadDifuso.equals("Adolescente") & (glucosaDifusa.equals("Ideal") |
				aminoacidosDifuso.equals("Ideal"))) condicionDifusa=conjuntosCondicionDiabetico[1];
		else if(imcDifuso.equals("Obesidad") & edadDifuso.equals("Joven") & (glucosaDifusa.equals("Ideal") |
				aminoacidosDifuso.equals("Ideal"))) condicionDifusa=conjuntosCondicionDiabetico[1];
		else if(imcDifuso.equals("Obesidad") & edadDifuso.equals("Adulto") & (glucosaDifusa.equals("Ideal") |
				aminoacidosDifuso.equals("Ideal"))) condicionDifusa=conjuntosCondicionDiabetico[1];
		else if(imcDifuso.equals("Obesidad") & edadDifuso.equals("3ra Edad") & (glucosaDifusa.equals("Ideal") |
				aminoacidosDifuso.equals("Ideal"))) condicionDifusa=conjuntosCondicionDiabetico[1];
		else if(imcDifuso.equals("Obesidad") & edadDifuso.equals("Niño") & (glucosaDifusa.equals("Alta") |
				aminoacidosDifuso.equals("Alto"))) condicionDifusa=conjuntosCondicionDiabetico[2];
		else if(imcDifuso.equals("Obesidad") & edadDifuso.equals("Adolescente") & (glucosaDifusa.equals("Alta") |
				aminoacidosDifuso.equals("Alto"))) condicionDifusa=conjuntosCondicionDiabetico[2];
		else if(imcDifuso.equals("Obesidad") & edadDifuso.equals("Joven") & (glucosaDifusa.equals("Alta") |
				aminoacidosDifuso.equals("Alto"))) condicionDifusa=conjuntosCondicionDiabetico[2];
		else if(imcDifuso.equals("Obesidad") & edadDifuso.equals("Adulto") & (glucosaDifusa.equals("Alta") |
				aminoacidosDifuso.equals("Alto"))) condicionDifusa=conjuntosCondicionDiabetico[2];
		else if(imcDifuso.equals("Obesidad") & edadDifuso.equals("3ra Edad") & (glucosaDifusa.equals("Alta") |
				aminoacidosDifuso.equals("Alto"))) condicionDifusa=conjuntosCondicionDiabetico[2];
		return condicionDifusa;
	}
	private static double min(double a,double b){ return a<b?a:b; }
	private static double max(double a,double b){ return a>b?a:b; }
	public static double inferirCondicionDifusaCuantitativo(String edadDifuso,
			String glucosaDifusa,String aminoacidosDifuso, String imcDifuso){        
		double nivMemEdad,nivMemGlucosa,nivMemAminoacidos,nivMemIMC,nivMemCondicion;
		String condicion;
		nivMemEdad=nivsMemEdad[posNivMemMay(nivsMemEdad)];
		nivMemGlucosa=nivsMemGlucosa[posNivMemMay(nivsMemGlucosa)];
		nivMemAminoacidos=nivsMemAminoacidos[posNivMemMay(nivsMemAminoacidos)];
		nivMemIMC=nivsMemIMC[posNivMemMay(nivsMemIMC)];
		nivMemCondicion=min(min(max(nivMemGlucosa,nivMemAminoacidos),nivMemIMC),nivMemEdad);
		condicion=inferirCondicionDifusaCualitativo(edadDifuso, glucosaDifusa, aminoacidosDifuso, imcDifuso);
		nivsMemCondicionDiabetico[condiciones.indexOf(condicion)]=nivMemCondicion;
		return nivsMemCondicionDiabetico[posNivMemMay(nivsMemCondicionDiabetico)];
	}
	public static double desfuzzificar(String condicionDifusa,double nivMemCondicion){
		switch(condicionDifusa){
		case "No diabético":return nivMemCondicion*33;
		case "Pre-diabético":return nivMemCondicion*66;
		case "Diabético":return nivMemCondicion*100;
		}
		return 0;
	}
	public static double[] getNivsMemCondicionDiabetico() {
		return nivsMemCondicionDiabetico;
	}
}
