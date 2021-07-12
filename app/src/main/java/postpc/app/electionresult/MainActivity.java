package postpc.app.electionresult;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pieChart = findViewById(R.id.pie_chart_main_activity);
        setupPieChart();
        loadDataToChart();
    }

    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true); // make it look like pie (hole inside the circle)
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(14);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("תוצאות הבחירות האחרונות");
        pieChart.setCenterTextSize(22);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
    }

    private void loadDataToChart() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(0.3f, "הליכוד"));
        entries.add(new PieEntry(0.275f, "כחול לבן"));
        entries.add(new PieEntry(0.125f, "הרשימה המשותפת"));
        entries.add(new PieEntry(0.075f, "ש\"ס"));
        entries.add(new PieEntry(0.05833333f, "יהדות התורה"));
        entries.add(new PieEntry(0.05833333f, "העבודה-גשר-מרצ"));
        entries.add(new PieEntry(0.05833333f, "ישראל ביתנו"));
        entries.add(new PieEntry(0.05f, "ימינה"));

        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "מפלגות");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();
        // adding nice animation
        pieChart.animateY(1000, Easing.EaseInOutQuad);
    }
}