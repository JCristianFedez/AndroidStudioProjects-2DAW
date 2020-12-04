package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /**
     * Contendra el TextView en el que
     * estan las operaciones realizadas
     */
    private TextView tvOperacion;

    /**
     * Contendra el TextView en el que
     * esta el resultado de la operacion,
     * y los numeros que se vayan introduciendo
     */
    private TextView tvResultado;
    private Button btnPorciento;
    private Button btnCE;
    private Button btnC;
    private ImageButton btnDelete;
    private Button btnDerivada;
    private Button btnPow;
    private Button btnSqrt;
    private Button btnDivi;
    private Button btnMulti;
    private Button btnResta;
    private Button btnSuma;
    private Button btnCambiarSimbolo;
    private Button btnComa;
    private Button btnIgual;
    private Button btnN9;
    private Button btnN8;
    private Button btnN7;
    private Button btnN6;
    private Button btnN5;
    private Button btnN4;
    private Button btnN3;
    private Button btnN2;
    private Button btnN1;
    private Button btnN0;
    /**
     * <code>false</code> se pueden poner comas
     * <code>true</code> no se pueden poner comas
     */
    private boolean coma;

    /**
     * <code>false</code> se pueden poner operadores
     * <code>true</code> no se pueden poner operadores
     */
    private boolean operador;

    /**
     * <code>true</code> indico que se acaba de pulsar igual
     * <code>false</code> el igual no ha sido pulsado en la anterior accion
     */
    private boolean igualPulsado;

    /**
     * <code>true</code> ha habido una division imposible Ej 0/5
     * <code>false</code> no se ha habido una division imposible
     */
    private boolean divEntre0;

    /**
     * Contiene la ultima operacion realizada, es decir el ultimo boton
     * de operacion (Suma,Resta,Multi,Divi) que se ha pulsado
     */
    private String lastOperation;

    /**
     * Contiene el resultado de la cuenta anterior echa,
     * sirve para hacer cuentas sobre el resultado de otras cuentas
     */
    private String lastResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coma = false;
        operador = false;
        lastResult = "";
        lastOperation = "";
        igualPulsado = false;
        divEntre0 = false;

        tvOperacion = (TextView) findViewById(R.id.tvOperacion);
        tvResultado = (TextView) findViewById(R.id.tvResultado);

        btnCE = (Button) findViewById(R.id.btnCE);
        btnC = (Button) findViewById(R.id.btnC);
        btnDelete = (ImageButton) findViewById(R.id.btnDelete);

        btnPorciento = (Button) findViewById(R.id.btnPorciento);
        btnDerivada = (Button) findViewById(R.id.btnDerivada);
        btnPow = (Button) findViewById(R.id.btnPow);
        btnSqrt = (Button) findViewById(R.id.btnSqrt);
        btnDivi = (Button) findViewById(R.id.btnDivi);
        btnMulti = (Button) findViewById(R.id.btnMulti);
        btnResta = (Button) findViewById(R.id.btnResta);
        btnSuma = (Button) findViewById(R.id.btnSuma);
        btnIgual = (Button) findViewById(R.id.btnIgual);
        btnCambiarSimbolo = (Button) findViewById(R.id.btnCambiarSimbolo);
        btnComa = (Button) findViewById(R.id.btnComa);

        btnN9 = (Button) findViewById(R.id.btnN9);
        btnN8 = (Button) findViewById(R.id.btnN8);
        btnN7 = (Button) findViewById(R.id.btnN7);
        btnN6 = (Button) findViewById(R.id.btnN6);
        btnN5 = (Button) findViewById(R.id.btnN5);
        btnN4 = (Button) findViewById(R.id.btnN4);
        btnN3 = (Button) findViewById(R.id.btnN3);
        btnN2 = (Button) findViewById(R.id.btnN2);
        btnN1 = (Button) findViewById(R.id.btnN1);
        btnN0 = (Button) findViewById(R.id.btnN0);

        /**
         * Resetea el tvOperador y los boleanos coma y false
         */
        btnCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Establezco el resultado en 0
                tvResultado.setText("0");

                coma = false;
                operador = false;

            }
        });
        /**
         * Resetea el tvOperador y el tvResultado y los boleanos coma y false
         */
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOperacion.setText("");
                tvResultado.setText("0");
                lastResult = "";
                lastOperation = "";
                coma = false;
                operador = false;
                igualPulsado = false;
            }
        });
        /**
         * Elimina el ultimo valor de tvOperacion
         */
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aux = tvResultado.getText().toString();
                //Me aseguro de solo borrar minetras haya numeros
                if (aux.length() > 0) {

                    //Si borro un simbolo vuelvo a activar la coma
                    if (aux.substring(aux.length() - 1).matches("[.,]")) {
                        coma = false;
                    }

                    //Si ha abido algun fallo, solo queda un simbolo o numero
                    if ((aux.length() == 2 && aux.substring(0, 1).equals("-"))
                            || aux.length() == 1
                            || aux.equals("NaN")
                            || aux.equals("Infinity")) {
                        aux = "0";

                        //Si no se cumple lo anterior quito el ultimo caracter
                    } else {
                        aux = aux.substring(0, aux.length() - 1);
                    }
                    tvResultado.setText(aux);
                }
            }
        });


        btnPorciento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular("Porciento");
            }
        });
        btnDerivada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular("Derivada");
            }
        });
        btnPow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular("Pow");
            }
        });
        btnSqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular("Sqrt");
            }
        });
        btnDivi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular("Divi");
            }
        });
        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular("Multi");
            }
        });
        btnResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular("Resta");
            }
        });
        btnSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular("Suma");
            }
        });
        btnIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular("Igual");
            }
        });
        btnCambiarSimbolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular("CambiarSimbolo");
            }
        });
        btnComa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular("Coma");
            }
        });


        btnN0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular("0");
            }
        });
        btnN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular("1");
            }
        });
        btnN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular("2");
            }
        });
        btnN3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular("3");
            }
        });
        btnN4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular("4");
            }
        });
        btnN5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular("5");
            }
        });
        btnN6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular("6");
            }
        });
        btnN7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular("7");
            }
        });
        btnN8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular("8");
            }
        });
        btnN9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular("9");
            }
        });

    }

    public void calcular(String str) {
        //Si despues de pulsar el igual se pulsa otro boton limpia la interfaz de la calculadora
        if (igualPulsado && !str.matches("[0-9]")) {
            tvResultado.setText(lastResult);
            lastResult = "";
            tvOperacion.setText("");
            igualPulsado = false;
            lastOperation = "";
        }

        if (divEntre0) {
            tvResultado.setText("0");
            divEntre0 = false;
            tvOperacion.setText("");
            lastResult = "";
            lastOperation = "";
            operador = false;
            coma = false;
            igualPulsado = false;
        }


        String resultado = tvResultado.getText().toString();
        String operacion = tvOperacion.getText().toString();

        //Me aseguro de no añadir varios ceros a la izquierda y que el primer caracter
        //no sea un operador
        if (resultado.equals("0") && str.matches("[0-9]")) {
            tvResultado.setText(str);
            operador=false;
        } else {//Se empieza a usar la calculadora
            //Variable numerica auxiliar sobre las que se realizaran las operaciones
            double aux;

            //Segun que boton hayas usado
            switch (str) {
                case "Porciento":
                    //Si es la primera cuenta que se realiza
                    if (!lastResult.equals("")) {
                        aux = Double.parseDouble(tvResultado.getText().toString().replaceAll(",", "."));
                        aux /= 100;
                        tvResultado.setText(String.valueOf(aux));
                    }
                    operador = false;
                    coma = false;

                    break;
                case "Derivada":
                    aux = Double.parseDouble(tvResultado.getText().toString().replaceAll(",", "."));

                    //Si se realiza una division entre 0 activo divEntre0 para indicar que ha
                    //habido un fallo
                    divEntre0 = (aux == 0) ? true : false;
                    aux = 1 / aux;
                    tvResultado.setText(String.valueOf(aux));
                    operador = false;
                    coma = false;
                    break;
                case "Pow":
                    aux = Double.parseDouble(tvResultado.getText().toString().replaceAll(",", "."));
                    aux = Math.pow(aux, 2);
                    tvResultado.setText(String.valueOf(aux));
                    operador = false;
                    coma = false;
                    break;
                case "Sqrt":
                    aux = Double.parseDouble(tvResultado.getText().toString().replaceAll(",", "."));
                    aux = Math.sqrt(aux);
                    tvResultado.setText(String.valueOf(aux));
                    operador = false;
                    coma = false;
                    break;
                case "Divi":
                    if (!operador) {

                        //Si es la primera operacion que se realiza, es decir, la primera cuenta
                        //realizada
                        if (operacion.equals("")) {
                            tvOperacion.setText(resultado + "÷");

                            //Si ya se ha realizado una operacion se acumula el historial
                        } else {
                            tvOperacion.setText(operacion + resultado + "÷");
                        }

                        //Si no hay cuentas anteriores echas se coje el numero introducido
                        //antes de introducir el operador
                        if (lastResult.equals("")) {

                            //Servira para hacer la cuenta mas adelante
                            lastResult = resultado;
                        } else {
                            double aux1 = Double.parseDouble(lastResult.replaceAll(",", "."));
                            double aux2 = Double.parseDouble(tvResultado.getText().toString().replaceAll(",", "."));

                            //Este switch sirve para la funcion que traen las calculadoras de
                            //de concatenar operaciones, un ejemplo:
                            //si tenemos la operacion de 2x3 cuando pulsemos otro operador se realizara
                            //la cuenta
                            switch (lastOperation) {
                                case "Resta":
                                    lastResult = String.valueOf(aux1 - aux2);
                                    break;
                                case "Suma":
                                    lastResult = String.valueOf(aux1 + aux2);
                                    break;
                                case "Multi":
                                    lastResult = String.valueOf(aux1 * aux2);
                                    break;
                                default:
                                    divEntre0 = (aux2 == 0) ? true : false;
                                    lastResult = String.valueOf(aux1 / aux2);
                                    break;
                            }
                            tvResultado.setText(lastResult);
                        }

                        //Establezco cual es la proxima operacion a realizar
                        lastOperation = "Divi";
                        operador = true;
                        coma = false;
                    }
                    break;
                case "Multi":
                    if (!operador) {

                        //Si es la primera operacion que se realiza, es decir, la primera cuenta
                        //realizada
                        if (operacion.equals("")) {
                            tvOperacion.setText(resultado + "x");

                            //Si ya se ha realizado una operacion se acumula el historial
                        } else {
                            tvOperacion.setText(operacion + resultado + "x");
                        }

                        //Si no hay cuentas anteriores echas se coje el numero introducido
                        //antes de introducir el operador
                        if (lastResult.equals("")) {

                            //Servira para hacer la cuenta mas adelante
                            lastResult = resultado;
                        } else {
                            double aux1 = Double.parseDouble(lastResult.replaceAll(",", "."));
                            double aux2 = Double.parseDouble(tvResultado.getText().toString().replaceAll(",", "."));

                            //Este switch sirve para la funcion que traen las calculadoras de
                            //de concatenar operaciones, un ejemplo:
                            //si tenemos la operacion de 2x3 cuando pulsemos otro operador se realizara
                            //la cuenta
                            switch (lastOperation) {
                                case "Resta":
                                    lastResult = String.valueOf(aux1 - aux2);
                                    break;
                                case "Suma":
                                    lastResult = String.valueOf(aux1 + aux2);

                                    break;
                                case "Divi":
                                    divEntre0 = (aux2 == 0) ? true : false;
                                    lastResult = String.valueOf(aux1 / aux2);
                                    break;
                                default:
                                    lastResult = String.valueOf(aux1 * aux2);
                                    break;
                            }
                            tvResultado.setText(lastResult);
                        }

                        //Establezco cual es la proxima operacion a realizar
                        lastOperation = "Multi";
                        operador = true;
                        coma = false;
                    }
                    break;
                case "Resta":
                    if (!operador) {

                        //Si es la primera operacion que se realiza, es decir, la primera cuenta
                        //realizada
                        if (operacion.equals("")) {
                            tvOperacion.setText(resultado + "-");

                            //Si ya se ha realizado una operacion se acumula el historial
                        } else {
                            tvOperacion.setText(operacion + resultado + "-");
                        }

                        //Si no hay cuentas anteriores echas se coje el numero introducido
                        //antes de introducir el operador
                        if (lastResult.equals("")) {

                            //Servira para hacer la cuenta mas adelante
                            lastResult = resultado;
                        } else {
                            double aux1 = Double.parseDouble(lastResult.replaceAll(",", "."));
                            double aux2 = Double.parseDouble(tvResultado.getText().toString().replaceAll(",", "."));

                            //Este switch sirve para la funcion que traen las calculadoras de
                            //de concatenar operaciones, un ejemplo:
                            //si tenemos la operacion de 2x3 cuando pulsemos otro operador se realizara
                            //la cuenta
                            switch (lastOperation) {
                                case "Suma":
                                    lastResult = String.valueOf(aux1 + aux2);
                                    break;
                                case "Multi":
                                    lastResult = String.valueOf(aux1 * aux2);

                                    break;
                                case "Divi":
                                    divEntre0 = (aux2 == 0) ? true : false;
                                    lastResult = String.valueOf(aux1 / aux2);
                                    break;
                                default:
                                    lastResult = String.valueOf(aux1 - aux2);
                                    break;
                            }
                            tvResultado.setText(lastResult);
                        }

                        //Establezco cual es la proxima operacion a realizar
                        lastOperation = "Resta";
                        operador = true;
                        coma = false;
                    }
                    break;
                case "Suma":
                    if (!operador) {

                        //Si es la primera operacion que se realiza, es decir, la primera cuenta
                        //realizada
                        if (operacion.equals("")) {
                            tvOperacion.setText(resultado + "+");

                            //Si ya se ha realizado una operacion se acumula el historial
                        } else {
                            tvOperacion.setText(operacion + resultado + "+");
                        }

                        //Si no hay cuentas anteriores echas se coje el numero introducido
                        //antes de introducir el operador
                        if (lastResult.equals("")) {

                            //Servira para hacer la cuenta mas adelante
                            lastResult = resultado;
                        } else {
                            double aux1 = Double.parseDouble(lastResult.replaceAll(",", "."));
                            double aux2 = Double.parseDouble(tvResultado.getText().toString().replaceAll(",", "."));

                            //Este switch sirve para la funcion que traen las calculadoras de
                            //de concatenar operaciones, un ejemplo:
                            //si tenemos la operacion de 2x3 cuando pulsemos otro operador se realizara
                            //la cuenta
                            switch (lastOperation) {
                                case "Resta":
                                    lastResult = String.valueOf(aux1 - aux2);
                                    break;
                                case "Multi":
                                    lastResult = String.valueOf(aux1 * aux2);

                                    break;
                                case "Divi":
                                    divEntre0 = (aux2 == 0) ? true : false;
                                    lastResult = String.valueOf(aux1 / aux2);
                                    break;
                                default:
                                    lastResult = String.valueOf(aux1 + aux2);
                                    break;
                            }
                            tvResultado.setText(lastResult);
                        }

                        //Establezco cual es la proxima operacion a realizar
                        lastOperation = "Suma";
                        operador = true;
                        coma = false;
                    }
                    break;
                case "Igual":

                    //Si es la primera operacion que se realiza, es decir, la primera cuenta
                    //realizada
                    if (operacion.equals("")) {
                        tvOperacion.setText(resultado + "=");
                    } else {

                        //Si ya se ha realizado una operacion se acumula el historial
                        tvOperacion.setText(operacion + resultado + "=");
                    }

                    //Si no hay cuentas anteriores echas se coje el numero introducido
                    //antes de introducir el operador
                    if (lastResult.equals("")) {

                        //Servira para hacer la cuenta mas adelante
                        lastResult = resultado;
                    } else {
                        double aux1 = Double.parseDouble(lastResult.replaceAll(",", "."));
                        double aux2 = Double.parseDouble(tvResultado.getText().toString().replaceAll(",", "."));

                        //Este switch sirve para la funcion que traen las calculadoras de
                        //de concatenar operaciones, un ejemplo:
                        //si tenemos la operacion de 2x3 cuando pulsemos otro operador se realizara
                        //la cuenta
                        switch (lastOperation) {
                            case "Divi":
                                divEntre0 = (aux2 == 0) ? true : false;
                                lastResult = String.valueOf(aux1 / aux2);
                                break;
                            case "Multi":
                                lastResult = String.valueOf(aux1 * aux2);
                                break;
                            case "Resta":
                                lastResult = String.valueOf(aux1 - aux2);
                                break;
                            case "Suma":
                                lastResult = String.valueOf(aux1 + aux2);
                                break;
                        }
                    }
                    tvResultado.setText(lastResult);
                    coma = false;
                    operador = false;

                    //Indico que se ha pulsado el boton igual
                    igualPulsado = true;
                    break;
                case "CambiarSimbolo":

                    //Si es un numero positivo le añado un -
                    if (Integer.valueOf(resultado) > 0) {
                        tvResultado.setText("-" + resultado);
                    } else if (Integer.valueOf(resultado) != 0) {

                        //Si es un numero negativo le quito el -
                        tvResultado.setText(resultado.substring(1));
                    }
                    break;
                case "Coma":
                    if (!coma) {

                        //si se ha introducido algun operador justo un momento antes o es el
                        //primero de introducir en la calculadora
                        if (operador) {
                            tvResultado.setText("0" + ",");
                        } else {
                            tvResultado.setText(resultado + ",");
                        }
                        coma = true;
                        operador = false;
                    }
                    break;

                default://Se entra aqui cuando se pulsa un numero

                    //Si se ha pulsado el igual se resetea la calculadora, para que cuando pulses un
                    //numero se borre el resultado anterior
                    if (igualPulsado) {
                        tvOperacion.setText("");
                        resultado = "";
                        lastOperation = "";
                        coma = false;
                        igualPulsado = false;
                    }

                    //si se ha introducido algun operador justo un momento antes o es el
                    //primero en introducirse en la calculadora
                    if (operador) {

                        tvResultado.setText(str);

                    } else {//Si hay hay algun numero antes
                        tvResultado.setText(resultado + str);
                    }
                    operador = false;

                    break;
            }
        }
        //Si divido entre 0 se resetea la calculadora entera
        if (divEntre0) {


        }
    }
}