package practica1;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Josué
 */
public class Menu {

    Scanner scan = new Scanner(System.in);
    int n = 1;
    String[][] tablap = new String[n][4];

    public Menu() {
        tablap[0][0] = "nombre";
        tablap[0][1] = "\tCarnet";
        tablap[0][2] = "\tpuntaje";
        tablap[0][3] = "\tMovimientos";

        int opc;
        try {
            do {
                System.out.println("**************");
                System.out.println("*Bienvenido***");
                System.out.println("*1. Jugar*****");
                System.out.println("*2. Listado***");
                System.out.println("*3. Salir*****");
                System.out.println("**************");
                System.out.print("Ingrese la opcion que desea realizar: ");
                opc = scan.nextInt();
                switch (opc) {
                    case 1:
                        opcion_1();

                        break;
                    case 2:
                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < 4; j++) {
                                System.out.print(tablap[i][j]);
                            }
                            System.out.println("");
                        }
                        break;
                    case 3:
                        System.out.println("***Adios***");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Solo se permiten opciones del 1-3");
                }
            } while (opc > 0 || opc < 3);
        } catch (InputMismatchException e) {
            System.out.println("Debe ingresar un valor numerico");
            Menu m = new Menu();

        }

    }

    private void opcion_1() {
        int fila, columna, carnet, rfil, rcol, puntaje = 10, contador = 0, n = 1,
                contdm = 0,//contador de cuantas veces puede multiplicar
                contd/*contador simbolo 10 pts */,
                contr/*contador reductor*/,
                contq/*contador simbolo 15 pts */,
                contm/*contador multiplicador */;
        String nombre, tablero[][], mov = "",
                pacman = "[v]", p10 = "[@]", p15 = "[$]", q10 = "[#]", mul = "[+]";
        boolean m1 = false, m2 = false, m3 = false;

        Random ran = new Random();
        System.out.print("\nIngrese nombre: ");
        nombre = scan.next();
        System.out.print("\nIngrese carnet: ");
        carnet = scan.nextInt();
        System.out.print("\nIngrese cantidad de filas: ");
        fila = scan.nextInt();
        if (fila < 7) {
            System.out.println("ingrese un numero mayor o igual a 7");
            scan.nextInt();
        }
        System.out.print("\nIngrese cantidad de columnas: ");
        columna = scan.nextInt();
        if (columna < 7) {
            System.out.println("ingrese un numero mayor o igual a 7");
            scan.nextInt();
        }
        tablero = new String[fila][columna];
        rfil = ran.nextInt(fila);
        rcol = ran.nextInt(columna);
        System.out.println(nombre + " " + carnet + " " + puntaje);

        contd = ran.nextInt((int) (((fila * columna) * 0.3) - 8)) + 8;//contador para simbolo 10pts
        contr = ran.nextInt((int) (((fila * columna) * 0.25) - 8)) + 8;//contador para simbolo -10pts
        contq = ran.nextInt((int) (((fila * columna) * 0.2) - 6)) + 6;//contador para simbolo 15pts
        contm = ran.nextInt(6 - 3) + 3;
        for (int i = 0; i < contd; i++) {
            for (int j = 0; j < contd; j++) {
                tablero[ran.nextInt(fila)][ran.nextInt(columna)] = p10;
            }
        }
        for (int i = 0; i < contr; i++) {
            for (int j = 0; j < contr; j++) {
                tablero[ran.nextInt(fila)][ran.nextInt(columna)] = q10;
            }
        }
        for (int i = 0; i < contq; i++) {
            for (int j = 0; j < contq; j++) {
                tablero[ran.nextInt(fila)][ran.nextInt(columna)] = p15;
            }
        }
        for (int i = 0; i < contm; i++) {
            for (int j = 0; j < contm; j++) {
                tablero[ran.nextInt(fila)][ran.nextInt(columna)] = mul;
            }
        }

        do {
            for (int i = 0; i < fila; i++) {
                for (int j = 0; j < columna; j++) {
                    if (tablero[i][j] == null) {
                        tablero[i][j] = "[ ]";
                    }
                }
            }

            for (int i = 0; i < fila; i++) {
                for (int j = 0; j < columna; j++) {
                    tablero[rfil][rcol] = pacman;

                    System.out.print(tablero[i][j]);
                }
                System.out.println("");
            }

            System.out.print("puntaje: "+puntaje);
            System.out.print("\tx"+contdm);
            System.out.println("que movimiento desea realizar: ");
            mov = scan.next();

            switch (mov) {
                case "a":
                    rcol = rcol - 1;
                    if (rcol < 0 && rfil == (fila - 1) / 2) {
                        rcol = columna - 1;
                    }
                    if (rcol < 0 && rfil != (fila - 1) / 2) {
                        rcol = 0;
                    }
                    if (tablero[rfil][rcol] == p10) {
                        if (m1 == false && m2 == false && m3 == false) {
                            puntaje = puntaje + 10;
                        } else if (m2 = true) {
                            puntaje = puntaje + (10 * contdm);
                            m1 = false;
                            contdm = 0;
                        } else if (m3 = true) {
                            puntaje = puntaje + (10 * contdm);
                            contdm = 0;
                        } else {
                            puntaje = puntaje + (10 * contdm);
                            contdm = 0;

                        }
                    } else if (tablero[rfil][rcol] == p15) {
                        if (m1 = true) {
                            puntaje = puntaje + (15 * contdm);
                            contdm = 0;
                        } else if (m2 = true) {
                            puntaje = puntaje + (15 * contdm);
                            contdm = 0;
                        } else if (m3 = true) {
                            puntaje = puntaje + (15 * contdm);
                            contdm = 0;
                        } else if (m1 == false && m2 == false && m3 == false) {
                            puntaje = puntaje + 15;
                        }
                    } else if (tablero[rfil][rcol] == q10) {
                        if (m1 = true) {
                            puntaje = puntaje - (10 * contdm);
                            contdm = 0;
                        } else if (m2 = true) {
                            puntaje = puntaje - (10 * contdm);
                            contdm = 0;
                        } else if (m3 = true) {
                            puntaje = puntaje - (10 * contdm);
                            contdm = 0;
                        } else if (m1 == false && m2 == false && m3 == false) {
                            puntaje = puntaje - 10;
                        }
                    } else if (tablero[rfil][rcol] == mul) {

                        if (m1 = true) {
                            m2 = true;
                            m1 = false;
                            contdm = contdm + 2;
                        } else if (m2 = true) {
                            m3 = true;
                            m2 = false;
                            contdm = contdm + 2;
                        } else {
                            m1 = true;
                            contdm = contdm + 2;
                        }
                    }

                    for (int i = 0; i < fila; i++) {
                        for (int j = 0; j < columna; j++) {
                            tablero[rfil][rcol + 1] = null;
                        }
                    }

                    contador = contador + 1;
                    if (puntaje == 100 || puntaje > 100) {
                        System.out.println("Has llegado a los 100pts");
                        mov = "m";
                    } else if (puntaje == 0 || puntaje < 0) {
                        System.out.println("Has perdido tu puntaje es 0pts");
                        mov = "m";
                    }
                    break;

                case "s":
                    rfil = rfil + 1;
                    if (rfil > (fila - 1) && rcol == (columna - 1) / 2) {
                        rfil = 0;
                    }
                    if (rfil > (fila - 1) && rcol != (fila - 1) / 2) {
                        rfil = rfil;
                    }
                    if (tablero[rfil][rcol] == p10) {
                        if (m1 == false && m2 == false && m3 == false) {
                            puntaje = puntaje + 10;
                        } else if (m2 = true) {
                            puntaje = puntaje + (10 * contdm);
                            contdm = 0;
                        } else if (m3 = true) {
                            puntaje = puntaje + (10 * contdm);
                            contdm = 0;
                        } else if (m1 = true) {
                            puntaje = puntaje + (10 * contdm);
                            contdm = 0;
                        }
                    } else if (tablero[rfil][rcol] == p15) {
                        if (m1 == false && m2 == false && m3 == false) {
                            puntaje = puntaje + 15;
                        } else if (m2 = true) {
                            puntaje = puntaje + (15 * contdm);
                            contdm = 0;
                        } else if (m3 = true) {
                            puntaje = puntaje + (15 * contdm);
                            contdm = 0;
                        } else {

                            puntaje = puntaje + (15 * contdm);
                            contdm = 0;
                        }
                    } else if (tablero[rfil][rcol] == q10) {
                        if (m1 == false && m2 == false && m3 == false) {
                            puntaje = puntaje - 10;
                        } else if (m2 = true) {
                            puntaje = puntaje - (10 * contdm);
                            contdm = 0;
                        } else if (m3 = true) {
                            puntaje = puntaje - (10 * contdm);
                            contdm = 0;
                        } else {

                            puntaje = puntaje - (10 * contdm);
                            contdm = 0;
                        }
                    } else if (tablero[rfil][rcol] == mul) {

                        if (m1 = true) {
                            m2 = true;
                            contdm = contdm + 2;
                        } else if (m2 = true) {
                            m3 = true;
                            contdm = contdm + 2;
                        } else {
                            m1 = true;
                            contdm = contdm + 2;
                        }
                    }

                    for (int i = 0; i < fila; i++) {
                        for (int j = 0; j < columna; j++) {
                            tablero[rfil - 1][rcol] = null;
                        }
                    }

                    contador = contador + 1;
                    if (puntaje == 100 || puntaje > 100) {
                        System.out.println("Has llegado a los 100pts");
                        mov = "m";
                    } else if (puntaje == 0 || puntaje < 0) {
                        System.out.println("Has perdido tu puntaje es 0pts");
                        mov = "m";
                    }
                    break;
                case "d":
                    rcol = rcol + 1;
                    if (rcol > (columna - 1) && rfil == (fila - 1) / 2) {
                        rcol = 0;
                    }
                    if (rcol > (columna - 1) && rfil != (fila - 1) / 2) {
                        rcol = columna - 1;
                    }
                    if (tablero[rfil][rcol] == p10) {
                        if (m1 = true) {
                            puntaje = puntaje + (10 * contdm);
                            contdm = 0;
                        } else if (m2 = true) {
                            puntaje = puntaje + (10 * contdm);
                            contdm = 0;
                        } else if (m3 = true) {
                            puntaje = puntaje + (10 * contdm);
                            contdm = 0;
                        } else {
                            puntaje = puntaje + 10;
                        }
                    } else if (tablero[rfil][rcol] == p15) {
                        if (m1 = true) {
                            puntaje = puntaje + (15 * contdm);
                            contdm = 0;
                        } else if (m2 = true) {
                            puntaje = puntaje + (15 * contdm);
                            contdm = 0;
                        } else if (m3 = true) {
                            puntaje = puntaje + (15 * contdm);
                            contdm = 0;
                        } else {
                            puntaje = puntaje + 15;
                        }
                    } else if (tablero[rfil][rcol] == q10) {
                        if (m1 = true) {
                            puntaje = puntaje - (10 * contdm);
                            contdm = 0;
                        } else if (m2 = true) {
                            puntaje = puntaje - (10 * contdm);
                            contdm = 0;
                        } else if (m3 = true) {
                            puntaje = puntaje - (10 * contdm);
                            contdm = 0;
                        } else {
                            puntaje = puntaje - 10;
                        }
                    } else if (tablero[rfil][rcol] == mul) {

                        if (m1 = true) {
                            m2 = true;
                            contdm = contdm + 2;
                        } else if (m2 = true) {
                            m3 = true;
                            contdm = contdm + 2;
                        } else {
                            m1 = true;
                            contdm = contdm + 2;
                        }
                    }

                    for (int i = 0; i < fila; i++) {
                        for (int j = 0; j < columna; j++) {
                            tablero[rfil][rcol - 1] = null;
                        }
                    }

                    contador = contador + 1;
                    if (puntaje == 100 || puntaje > 100) {
                        System.out.println("Has llegado a los 100pts");
                        mov = "m";
                    } else if (puntaje == 0 || puntaje < 0) {
                        System.out.println("Has perdido tu puntaje es 0pts");
                        mov = "m";
                    }
                    break;
                case "w":
                    rfil = rfil - 1;
                    if (rfil < 0 && rcol == (columna - 1) / 2) {
                        rfil = (fila - 1);
                    }
                    if (rfil < 0 && rcol != (fila - 1) / 2) {
                        rfil = rfil;
                    }
                    if (tablero[rfil][rcol] == p10) {
                        if (m1 = true) {
                            puntaje = puntaje + (10 * contdm);
                            contdm = 0;
                        } else if (m2 = true) {
                            puntaje = puntaje + (10 * contdm);
                            contdm = 0;
                        } else if (m3 = true) {
                            puntaje = puntaje + (10 * contdm);
                            contdm = 0;
                        } else {
                            puntaje = puntaje + 10;
                        }
                    } else if (tablero[rfil][rcol] == p15) {
                        if (m1 = true) {
                            puntaje = puntaje + (15 * contdm);
                            contdm = 0;
                        } else if (m2 = true) {
                            puntaje = puntaje + (15 * contdm);
                            contdm = 0;
                        } else if (m3 = true) {
                            puntaje = puntaje + (15 * contdm);
                            contdm = 0;
                        } else {
                            puntaje = puntaje + 15;
                        }
                    } else if (tablero[rfil][rcol] == q10) {
                        if (m1 = true) {
                            puntaje = puntaje - (10 * contdm);
                            contdm = 0;
                        } else if (m2 = true) {
                            puntaje = puntaje - (10 * contdm);
                            contdm = 0;
                        } else if (m3 = true) {
                            puntaje = puntaje - (10 * contdm);
                            contdm = 0;
                        } else {
                            puntaje = puntaje - 10;
                        }
                    } else if (tablero[rfil][rcol] == mul) {

                        if (m1 = true) {
                            m2 = true;
                            contdm = contdm + 2;
                        } else if (m2 = true) {
                            m3 = true;
                            contdm = contdm + 2;
                        } else {
                            m1 = true;
                            contdm = contdm + 2;
                        }
                    }

                    for (int i = 0; i < fila; i++) {
                        for (int j = 0; j < columna; j++) {
                            tablero[rfil + 1][rcol] = null;
                        }
                    }

                    contador = contador + 1;
                    if (puntaje == 100 || puntaje > 100) {
                        System.out.println("Has llegado a los 100pts");
                        mov = "m";
                    } else if (puntaje == 0 || puntaje < 0) {
                        System.out.println("Has perdido tu puntaje es 0pts");
                        mov = "m";
                    }
                    break;
                case "m":
                    /*this.n = (this.n + 1);
                    for (int i = 1; i <= tablap.length; i++) {
                        for (int j = 0; j < 4; j++) {
                            this.tablap[i][0] = nombre;
                            this.tablap[i][1] = carnet + "";
                            this.tablap[i][2] = puntaje + "";
                            this.tablap[i][3] = contador + "";
                        }

                    }*/

                    mov = "m";

                    break;

                default:
                    System.out.println("Solo se permiten teclas a, s, w, d y m");
            }

        } while (mov != "m");
    }

}
