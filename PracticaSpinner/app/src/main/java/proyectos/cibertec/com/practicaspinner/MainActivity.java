package proyectos.cibertec.com.practicaspinner;
import android.view.*;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    //1)Se crea el array tipo string
    final String [] datos =new String []{"Elemento1","Elemento2","Elemento3","Elemento4","Elemento5"};
    TextView lblMensaje;

    //2)Creamos un spinner
    private Spinner cmbOpciones;
// las listas despegables son los spinner
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //3)Se crea el adaptador con 3 parametros : 1)donde se creará el adaptador.2)La forma como se mostrara o el ID del layout, y el arreglo
        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,datos);
        //hacemos referencai al XML que deseemos usar
        //ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this, R.array.valores_array,android.R.layout.simple_spinner_item);
        lblMensaje=(TextView)findViewById(R.id.lblMensaje);
        cmbOpciones=(Spinner)findViewById(R.id.CmbOpciones);
        //para personalizar también el aspecto de cada elemento en dicha lista emergente tenemos el método setDropDownViewResource(ID_layout)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmbOpciones.setAdapter(adaptador);
        cmbOpciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
                     public void onItemSelected(AdapterView<?> parent,android.view.View v, int position, long id) {
                        lblMensaje.setText("Seleccionado: " + parent.getItemAtPosition(position));
                    }
            @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        lblMensaje.setText("");
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
