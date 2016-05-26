

public class Membresias {
	private static double PI = Math.PI;
	public static final int ERROR = -1;
	public static double funcionGamma(double u,double alpha,double beta){  //TrapezoidalAbiertaDerecha
		if(u<alpha) return 0;
		if(alpha<=u && u<=beta) return (u-alpha)/(beta-alpha);
		if(u>beta) return 1;
		return ERROR;
	}
	public static double funcionL(double u,double alpha,double beta){ //TrapezoidalAbiertaIzquierda
		if(u<alpha) return 1;
		if(alpha<=u && u<=beta) return (beta-u)/(beta-alpha);
		if(u>beta) return 0;
		return ERROR;
	}
	public static double funcionLambda(double u,double alpha, double beta, double gamma){ //Pico
		if(u<alpha || u>gamma) return 0;
		if(alpha<=u && u<=beta) return (u-alpha)/(beta-alpha);
		if(beta<=u && u<=gamma) return (gamma-u)/(gamma-beta);
		return ERROR;
	}
	public static double funcionPi(double u,double alpha, double beta, double gamma, double delta){ //Trapezoidal
		if(u<alpha || u>delta) return 0;
		if(alpha<=u && u<=beta) return (u-alpha)/(beta-alpha);
		if(beta<=u && u<=gamma) return 1;
		if(gamma<u && u<=delta) return (delta-u)/(delta-gamma);
		return ERROR;
	}
	public static double funcionS(double u, double alpha, double beta){
		if(u<alpha) return 0;
		if(alpha<=u && u<=beta) return 0.5*(1+Math.cos(((u-beta)/(beta-alpha))*PI));
		if(u>beta) return 1;
		return ERROR;
	}
	public static double funcionZ(double u, double alpha, double beta){
		if(u<alpha) return 1;
		if(alpha<=u && u<=beta) return 0.5*(1+Math.cos(((u-alpha)/(beta-alpha))*PI));
		if(u>beta) return 0;
		return ERROR;
	}
	public static double funcionSoftLambda(double u, double alpha, double beta,double gamma){
		if(u<alpha || u>gamma) return 0;
		if(alpha<=u && u<=beta) return 0.5*(1+Math.cos(((u-beta)/(beta-alpha))*PI));
		if(beta<=u && u<=gamma) return 0.5*(1+Math.cos(((u-beta)/(gamma-beta))*PI));
		return ERROR;
	}
	public static double funcionSoftPi(double u, double alpha, double beta,double gamma,double delta){
		if(u<alpha || u>delta) return 0;
		if(alpha<=u && u<=beta) return 0.5*(1+Math.cos(((u-beta)/(beta-alpha))*PI));
		if(beta<=u && u<=gamma) return 1;
		if(gamma<=u && u<=delta) return 0.5*(1+Math.cos(((u-gamma)/(delta-gamma))*PI));
		return ERROR;
	}
	
}
