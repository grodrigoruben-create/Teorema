package com.example.teorema;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class MainActivity extends AppCompatActivity {

    Button Resolver;
    TextView Resultado;
    Spinner Opcion;
    ImageView imageView;
    EditText Dato1, Dato2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {

                    Insets systemBars =
                            insets.getInsets(
                                    WindowInsetsCompat.Type.systemBars()
                            );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );

        // Conectar los elementos del XML
        Resolver = findViewById(R.id.Resolver);
        Resultado = findViewById(R.id.Resultado);
        Opcion = findViewById(R.id.Opcion);
        imageView = findViewById(R.id.imageView);
        Dato1 = findViewById(R.id.Dato1);
        Dato2 = findViewById(R.id.Dato2);
        // Opciones del Spinner
        String[] opciones = {
                "Selecciona una opcion",
                "Cateto adyacente",
                "Cateto opuesto",
                "Hipotenusa"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        opciones
                );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        Opcion.setAdapter(adapter);

// Cambiar imagen y hints según la opción seleccionada
        Opcion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(
                    AdapterView<?> parent,
                    View view,
                    int position,
                    long id) {

                switch (position) {

                    case 0:
                        imageView.setImageResource(R.drawable.inicial);

                         Dato1.setHint("Dato1");
                         Dato2.setHint("Dato2");

                         break;

                    case 1: // Cateto adyacente

                        imageView.setImageResource(R.drawable.catetoadyacente);

                        Dato1.setHint("Hipotenusa");
                        Dato2.setHint("Cateto opuesto");

                        break;

                    case 2: // Cateto opuesto

                        imageView.setImageResource(R.drawable.catetoopuesto);

                        Dato1.setHint("Hipotenusa");
                        Dato2.setHint("Cateto adyacente");

                        break;

                    case 3: // Hipotenusa

                        imageView.setImageResource(R.drawable.hipotenusa);

                        Dato1.setHint("Cateto adyacente");
                        Dato2.setHint("Cateto opuesto");

                        break;
                }

                Dato1.setText("");
                Dato2.setText("");
                Resultado.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada
            }
        });

// Botón Resolver
        Resolver.setOnClickListener(this::resolverTeorema);
    }

    public void resolverTeorema(View view) {

        // Comprobar campos vacíos
        if (Dato1.getText().toString().isEmpty()
                || Dato2.getText().toString().isEmpty()) {

            Toast.makeText(
                    this,
                    "Capture ambos valores",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        double n1;
        double n2;

        // Convertir los valores a números
        try {

            n1 = Double.parseDouble(
                    Dato1.getText().toString()
            );

            n2 = Double.parseDouble(
                    Dato2.getText().toString()
            );

        } catch (NumberFormatException e) {

            Toast.makeText(
                    this,
                    "Ingrese solamente números",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        // Los valores deben ser positivos
        if (n1 <= 0 || n2 <= 0) {

            Toast.makeText(
                    this,
                    "Los valores deben ser mayores a 0",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        String opc = Opcion.getSelectedItem().toString();

        double resultado;

        // Calcular CATETO ADYACENTE
        if (opc.equals("Cateto adyacente")) {

            if (n1 <= n2) {

                Toast.makeText(
                        this,
                        "La hipotenusa debe ser mayor que el cateto",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            resultado = sqrt(
                    pow(n1, 2) - pow(n2, 2)
            );

            Resultado.setText("CA = " + resultado);
        }

        // Calcular CATETO OPUESTO
        else if (opc.equals("Cateto opuesto")) {

            if (n1 <= n2) {

                Toast.makeText(
                        this,
                        "La hipotenusa debe ser mayor que el cateto",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            resultado = sqrt(
                    pow(n1, 2) - pow(n2, 2)
            );

            Resultado.setText("CO = " + resultado);
        }

        // Calcular HIPOTENUSA
        else {

            resultado = sqrt(
                    pow(n1, 2) + pow(n2, 2)
            );

            Resultado.setText("H = " + resultado);
        }
    }
}
