package co.edu.unbosque.model;

// permite calcular el valor según las reglas de cada tipo de alojamiento
public interface CalculableReserva {
    // devuelve el total de la estdia para la cantidad de noches indicada
    double calcularValorReserva(int noches);
}
