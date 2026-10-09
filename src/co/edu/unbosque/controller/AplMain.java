package co.edu.unbosque.controller;

import co.edu.unbosque.view.View;

public class AplMain {
    public static void main(String[] args) {
        // creo la vista que va a mostrar el menú y pedir los datos
        View view = new View();
        // conecto la vista con el controlador que maneja las opciones
        Controller controller = new Controller(view);
        // arranco la carga de datos y el menú principal
        controller.iniciar();
    }
}
