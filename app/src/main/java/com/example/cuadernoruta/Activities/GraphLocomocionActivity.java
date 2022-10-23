package com.example.cuadernoruta.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cuadernoruta.BBDD.AppDataBase;
import com.example.cuadernoruta.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraphLocomocionActivity extends AppCompatActivity {

    //referencio la base de datos porque la usare para obtener datos que mostrare en la grafica
    //y la inicializo luego en el oncreate
    AppDataBase db;
    //necesito un arraylist donde guardare los datos que obtenga para luego pasarlos a la grafica
    ArrayList barEntriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_locomocion);

        //para poner la flecha atras en el toolbar
        //al final sobreescribo el metodo onOptionsItemSelected
        /*
         * para que la flecha sea blanca añado un item al style de themes
         * con colorControlNormal*/
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //recojo el viaje que me viene de la primera activity
        int viaje = getIntent().getExtras().getInt("num");
        String nombreviaje = getIntent().getExtras().getString("nomviaje");
        //Toast.makeText(this, "Recibo " + viaje, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Recibo " + nombreviaje, Toast.LENGTH_SHORT).show();

        //inicializo la bbdd
        db = Room.databaseBuilder(this,AppDataBase.class,"paginasDb").allowMainThreadQueries().build();

        //referenciamos la vista
        BarChart barChart = findViewById(R.id.idbarchart);
        //llamo al metodo del que obtendre los datos antes de rellenar las barras de la grafica
        getBarChartEntries(nombreviaje); //llamo a la funcion y le paso el numero de viaje para filtrar por viaje
        //creo un bar data set
        BarDataSet barDataSet = new BarDataSet(barEntriesList,"1.Km 2.Gasolina 3.Peajes 4.Pernoctas");
        //necesito un bardata y pasarle el bardataset
        BarData barData = new BarData(barDataSet);
        //ponemos los datos del bardata en la vista del BarChart
        barChart.setData(barData);

        //personalizamos un poco la grafica
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        barChart.getDescription().setText("Totales locomoción");
        barChart.setFitBars(true);
        barChart.animateY(2000);


    }

    private void getBarChartEntries(String viaje) {
        //obtengo los totales de cada categoria que vaya a mostrar en la grafica
        Float totalkm = db.paginaDao().gettotalkm(viaje);
        Float totalgasolina = db.paginaDao().gettotalgasolina(viaje);
        Float totalpeajes = db.paginaDao().gettotalpeajes(viaje);
        Float totalpernocta = db.paginaDao().gettotalpernocta(viaje);
        //inicializo el array y le añado los datos que necesito
        barEntriesList = new ArrayList();
        //añado los datos pasandole los que he obtenido de la bbdd
        barEntriesList.add(new BarEntry(1f,totalkm));
        barEntriesList.add(new BarEntry(2f,totalgasolina));
        barEntriesList.add(new BarEntry(3f,totalpeajes));
        barEntriesList.add(new BarEntry(4f,totalpernocta));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // handle arrow click here
        if (item.getItemId() == android.R.id.home)
            finish(); // close this activity and return to preview activity (if there is any)

        return super.onOptionsItemSelected(item);
    }

}