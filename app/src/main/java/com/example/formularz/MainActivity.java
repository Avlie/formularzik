package com.example.formularz;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText Imie;
    private EditText Nazwisko;
    private EditText Email;
    private EditText Password;
    private Button Register;
    private TextView Wiadomosc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Imie = findViewById(R.id.Eimie);
        Nazwisko = findViewById(R.id.Enazwisko);
        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Epassword);
        Register = findViewById(R.id.BattonR);
        Wiadomosc = findViewById(R.id.Wiadomosc);

        Register.setOnClickListener(v -> validateForm());
    }

    private void validateForm() {
        String firstName = Imie.getText().toString().trim();
        String lastName = Nazwisko.getText().toString().trim();
        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Wiadomosc.setText("Uzupełnij wszystkie pola");
            return;
        }

        if (!email.contains("@") || !email.contains(".")) {
            Wiadomosc.setText("Podaj poprawny adres email");
            return;
        }

        List<String> missingRequirements = new ArrayList<>();

        if (password.length() < 8) {
            missingRequirements.add("co najmniej 8 znaków");
        }
        if (!password.matches(".*[A-Z].*")) {
            missingRequirements.add("dużą literę");
        }
        if (!password.matches(".*[a-z].*")) {
            missingRequirements.add("małą literę");
        }
        if (!password.matches(".*[^a-zA-Z0-9].*")) {
            missingRequirements.add("znak specjalny");
        }

        if (!missingRequirements.isEmpty()) {
            String missingStr = String.join(", ", missingRequirements);
            Wiadomosc.setText("Hasło musi zawierać: " + missingStr);
            return;
        }

        Wiadomosc.setText("Dane są poprawne");
    }
}