package co.edu.unbosque.view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import co.edu.unbosque.model.Alojamiento;
import co.edu.unbosque.model.Huesped;
import co.edu.unbosque.model.Reserva;

public class View {
	private Scanner scanner = new Scanner(System.in);

	public void mostrarMenu() {
		System.out.println("SISTEMA DE ALOJAMIENTOS");
		System.out.println("1. Consultar alojamientos");
		System.out.println("2. Buscar alojamientos");
		System.out.println("3. Consultar detalle");
		System.out.println("4. Registrar huesped");
		System.out.println("5. Crear reserva");
		System.out.println("6. Consultar reservas");
		System.out.println("7. Cancelar reserva");
		System.out.println("8. Consultar huespedes");
		System.out.println("9. Reportes");
		System.out.println("0. Salir");
	}

	public int leerOpcion() {
		return leerEntero("Opción: ");
	}

	public String leerTexto(String mensaje) {
		System.out.print(mensaje);
		return scanner.nextLine().trim();
	}

	public String leerObligatorio(String mensaje) {
		String dato;
		do {
			dato = leerTexto(mensaje);
			if (dato.isEmpty())
				System.out.println("- Porfavor, añada un dato...");
		} while (dato.isEmpty());
		return dato;
	}

	public int leerEntero(String mensaje) {
		while (true) {
			try {
				return Integer.parseInt(leerTexto(mensaje));
			} catch (NumberFormatException e) {
				System.out.println("- Formato no valido, debe ser un número!");
			}
		}
	}

	public int leerEnteroPositivo(String mensaje) {
		int valor;
		do {
			valor = leerEntero(mensaje);
			if (valor <= 0)
				System.out.println("- debe ser mayor que cero.");
		} while (valor <= 0);
		return valor;
	}

	public double leerDoublePositivo(String mensaje) {
		while (true) {
			try {
				double valor = Double.parseDouble(leerTexto(mensaje));
				if (valor > 0)
					return valor;
			} catch (NumberFormatException e) {
				System.out.println("- debe ingresar un numero valido.");
			}
			System.out.println("- el valor debe ser mayor que cero.");
		}
	}

	public LocalDate leerFecha(String mensaje) {
		while (true) {
			try {
				return LocalDate.parse(leerTexto(mensaje));
			} catch (DateTimeParseException e) {
				System.out.println("Use AAAA-MM-DD.");
			}
		}
	}

	public String leerTipo() {
		while (true) {
			System.out.println("1. Apartamento");
			System.out.println("2. Casa");
			System.out.println("3. Cabaña");
			int opcion = leerEntero("Tipo: ");
			if (opcion == 1)
				return "Apartamento";
			if (opcion == 2)
				return "Casa";
			if (opcion == 3)
				return "Cabaña";
			System.out.println("Tipo no valido.");
		}
	}

	public void mostrarAlojamientos(List<Alojamiento> lista) {
		if (lista.isEmpty()) {
			System.out.println("No hay alojamientos.");
			return;
		}
		for (Alojamiento a : lista)
			System.out.println(a);
	}

	public void mostrarDetalle(Alojamiento a) {
		if (a == null) {
			System.out.println("Alojamiento no encontrado.");
			return;
		}
		System.out.println("ID: " + a.getId());
		System.out.println("Nombre: " + a.getNombre());
		System.out.println("Ciudad: " + a.getCiudad());
		System.out.println("Tipo: " + a.getTipo());
		System.out.println("Ubicacion: " + a.getUbicacion());
		System.out.println("Capacidad: " + a.getCapacidad());
		System.out.println("Precio por noche: $" + a.getPrecioNoche());
		System.out.println("Estado: " + (a.isActivo() ? "ACTIVO" : "INACTIVO"));
		System.out.println("Descripcion: " + a.getDescripcion());
	}

	public void mostrarHuespedes(List<Huesped> lista) {
		if (lista.isEmpty()) {
			System.out.println("No hay huespedes.");
			return;
		}
		for (Huesped h : lista)
			System.out.println(h);
	}

	public void mostrarReservas(List<Reserva> lista) {
		if (lista.isEmpty()) {
			System.out.println("No hay reservas.");
			return;
		}
		for (Reserva r : lista)
			System.out.println(r);
	}

	public void mensaje(String mensaje) {
		System.out.println(mensaje);
	}

	public void cerrar() {
		scanner.close();
	}
}
