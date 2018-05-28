package proyectos.mechatronicteam.unam.fi.misgastos;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class AddGastoIngreso extends AppCompatActivity {

    TextView textView;
    RadioGroup radioGroup;
    RadioButton radioButton;
    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    Calendar dateTime= Calendar.getInstance();
    private TextView text;
    private ImageButton btn_date;
    private ImageButton btn_time;
    Spinner lista;
    String[] categorias={"Gasolina","Comida","Regalos","Cine","Transporte"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gasto_ingreso);



        radioGroup =  findViewById(R.id.radioGroup);
        textView = findViewById(R.id.final_selecction);
        Button buttonApply = findViewById(R.id.save);
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId= radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                textView.setText(radioButton.getText());


            }
        });



        lista=(Spinner)findViewById(R.id.lista_categoria);
        ArrayAdapter<String> adaptador= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,categorias);
        lista.setAdapter(adaptador);
        text = (TextView) findViewById(R.id.txt_DateTime);
        btn_date = (ImageButton) findViewById(R.id.btn_datePicker);
        btn_time = (ImageButton) findViewById(R.id.btn_timePicker);

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDate();
            }
        });

        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateTime();
            }
        });

        updateTextLabel();
    }

    public void checkButton(View v){
        int radioId= radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this , "Selected Radio Button"+  radioButton.getText(), Toast.LENGTH_SHORT).show();

    }
    private void updateDate(){
        new DatePickerDialog(this,d,dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }
    private void updateTime(){
        new TimePickerDialog(this,t,dateTime.get(Calendar.HOUR_OF_DAY),dateTime.get(Calendar.MINUTE),true).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dateTime.set(Calendar.YEAR, year);
            dateTime.set(Calendar.MONTH, month);
            dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateTextLabel();

        }
    };
    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);
            updateTextLabel();

        }
    };

    private void updateTextLabel(){
        text.setText(formatDateTime.format(dateTime.getTime()));
    }
}
