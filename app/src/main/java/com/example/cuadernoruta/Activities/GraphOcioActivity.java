package com.example.cuadernoruta.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.graphics.Color;
import android.os.Bundle;

import com.example.cuadernoruta.BBDD.AppDataBase;
import com.example.cuadernoruta.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraphOcioActivity extends AppCompatActivity {

    //referencio la base de datos porque la usare para obtener datos que mostrare en la grafica
    //y la inicializo luego en el oncreate
    AppDataBase db;
    //necesito un arraylist donde guardare los datos que obtenga para luego pasarlos a la grafica
    ArrayList barEntriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_ocio);

        //inicializo la bbdd
        db = Room.databaseBuilder(this,AppDataBase.class,"paginasDb").allowMainThreadQueries().build();

        //referenciamos la vista
        BarChart barChart = findViewById(R.id.idbarchartocio);
        //llamo al metodo del que obtendre los datos antes de rellenar las barras de la grafica
        getBarChartEntries();
        //creo un bar data set
        BarDataSet barDataSet = new BarDataSet(barEntriesList,"1.Atracciones 2.Otros");
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
        barChart.animateY(3000);


    }

    private void getBarChartEntries() {
        //obtengo los totales de cada categoria que vaya a mostrar en la grafica
        Float totalatracc = db.paginaDao().gettotalatracciones();
        Float totalotrosocio = db.paginaDao().gettotalotrosocio();
        //inicializo el array y le añado los datos que necesito
        barEntriesList = new ArrayList();
        //añado los datos pasandole los que he obtenido de la bbdd
        barEntriesList.add(new BarEntry(1f,totalatracc));
        barEntriesList.add(new BarEntry(2f,totalotrosocio));


    }

}