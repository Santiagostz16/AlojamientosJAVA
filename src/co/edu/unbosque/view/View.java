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
	// muestro el menu
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

	// leo la opción del menu usando la validación de números enteros
	public int leerOpcion() {
		return leerEntero("Opción: ");
	}

	// recibo texto y quito espacios sobrantes al principio y al final
	public String leerTexto(String mensaje) {
		System.out.print(mensaje);
		return scanner.nextLine().trim();
	}

	// vuelvo a pedir el dato mientras el usuario lo deje vacío
	public String leerObligatorio(String mensaje) {
		String dato;
		do {
			dato = leerTexto(mensaje);
			if (dato.isEmpty())
				System.out.println("- Porfavor, añada un dato...");
		} while (dato.isEmpty());
		return dato;
	}

	// repito la lectura hasta que se ingrese un número entero
	public int leerEntero(String mensaje) {
		while (true) {
			try {
				return Integer.parseInt(leerTexto(mensaje));
			} catch (NumberFormatException e) {
				System.out.println("- Formato no valido, debe ser un número!");
			}
		}
	}

	// acepto solo cantidades enteras mayores que cero
	public int leerEnteroPositivo(String mensaje) {
		int valor;
		do {
			valor = leerEntero(mensaje);
			if (valor <= 0)
				System.out.println("- debe ser mayor que cero.");
		} while (valor <= 0);
		return valor;
	}

	// valido que el valor ingresado sea un número decimal positivo
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

	// convierto la fecha ingresada y pido otra si no tiene formato valido
	public LocalDate leerFecha(String mensaje) {
		while (true) {
			try {
				return LocalDate.parse(leerTexto(mensaje));
			} catch (DateTimeParseException e) {
				System.out.println("Use AAAA-MM-DD.");
			}
		}
	}

	// traduzco la opción elegida al tipo de alojamiento que usa el sistema
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

	// muestro los alojamientos o aviso cuando la lista no tiene registros
	public void mostrarAlojamientos(List<Alojamiento> lista) {
		if (lista.isEmpty()) {
			System.out.println("No hay alojamientos.");
			return;
		}
		for (Alojamiento a : lista)
			System.out.println(a);
	}

	// presento los campos principales del alojamiento seleccionado
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

	// muestro los huespedes registrados o aviso si todavía no hay
	public void mostrarHuespedes(List<Huesped> lista) {
		if (lista.isEmpty()) {
			System.out.println("No hay huespedes.");
			return;
		}
		for (Huesped h : lista)
			System.out.println(h);
	}

	// muestro las reservas registradas o aviso si todavía no hay
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
