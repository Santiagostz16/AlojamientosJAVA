package co.edu.unbosque.controller;

import co.edu.unbosque.view.View;

public class AplMain {
    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        controller.iniciar();
    }
}
