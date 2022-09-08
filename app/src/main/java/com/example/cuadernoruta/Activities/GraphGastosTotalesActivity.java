package com.example.cuadernoruta.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cuadernoruta.BBDD.AppDataBase;
import com.example.cuadernoruta.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraphGastosTotalesActivity extends AppCompatActivity {

    //referencio la base de datos porque la usare para obtener datos que mostrare en la grafica
    //y la inicializo luego en el oncreate
    AppDataBase db;
    //necesito un arraylist donde guardare los datos que obtenga para luego pasarlos a la grafica
    ArrayList pieEntriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos_totales);

        //para poner la flecha atras en el toolbar
        //al final sobreescribo el metodo onOptionsItemSelected
        /*
         * para que la flecha sea blanca añado un item al style de themes
         * con colorControlNormal*/
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //inicializo la bbdd
        db = Room.databaseBuilder(this,AppDataBase.class,"paginasDb").allowMainThreadQueries().build();
        //obtengo y pongo los km totales que no iran en la grafica
        Float kmtotales = db.paginaDao().gettotalkm();
        TextView etkm = findViewById(R.id.tv_totalkm);
        etkm.setText(kmtotales.toString() + " Km");


        PieChart pieChart = findViewById(R.id.PieChart);
        getPieChartEntries();

        PieDataSet pieDataSet = new PieDataSet(pieEntriesList,"Gastos Totales");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(11f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("TOTALES");
        pieChart.animate();
    }

    private void getPieChartEntries() {
        //obtengo los totales de cada categoria que vaya a mostrar en la grafica
        Float totalfinal = db.paginaDao().gettotal();
        Float totalgasolina = db.paginaDao().gettotalgasolina();
        Float totalpeajes = db.paginaDao().gettotalpeajes();
        Float totalpernocta = db.paginaDao().gettotalpernocta();
        Float totalsuper = db.paginaDao().gettotalsuper();
        Float totalrtes = db.paginaDao().gettotalrtes();
        Float totalotroscompras = db.paginaDao().gettotalotroscompras();
        Float totalatracc = db.paginaDao().gettotalatracciones();
        Float totalotrosocio = db.paginaDao().gettotalotrosocio();
        //inicializo el array y le añado los datos que necesito
        pieEntriesList = new ArrayList();
        //añado los datos pasandole los que he obtenido de la bbdd
        pieEntriesList.add(new PieEntry(totalfinal,"TOTAL"));
        pieEntriesList.add(new PieEntry(totalgasolina,"Gas"));
        pieEntriesList.add(new PieEntry(totalpeajes,"Peajes"));
        pieEntriesList.add(new PieEntry(totalpernocta,"Pernocta"));
        pieEntriesList.add(new PieEntry(totalsuper,"Super"));
        pieEntriesList.add(new PieEntry(totalrtes,"Rtes"));
        pieEntriesList.add(new PieEntry(totalotroscompras,"Compras"));
        pieEntriesList.add(new PieEntry(totalatracc,"Atracc"));
        pieEntriesList.add(new PieEntry(totalotrosocio,"Ocio"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // handle arrow click here
        if (item.getItemId() == android.R.id.home)
            finish(); // close this activity and return to preview activity (if there is any)

        return super.onOptionsItemSelected(item);
    }
}