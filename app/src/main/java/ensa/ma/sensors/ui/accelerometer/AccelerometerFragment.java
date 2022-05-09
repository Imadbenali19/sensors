package ensa.ma.sensors.ui.accelerometer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.renderer.YAxisRenderer;

import java.util.ArrayList;
import java.util.Date;

import ensa.ma.sensors.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccelerometerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccelerometerFragment extends Fragment implements SensorEventListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private LineChart chart;
    private  SensorManager mSensorManager;
    private  Sensor mAccelSensor;
    static ArrayList<Entry> entries = new ArrayList<>();


    public AccelerometerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccelerometerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccelerometerFragment newInstance(String param1, String param2) {
        AccelerometerFragment fragment = new AccelerometerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mSensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
        mAccelSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_accelerometer, container, false);
        View root = inflater.inflate(R.layout.fragment_accelerometer, container, false);
        chart = (LineChart) root.findViewById(R.id.chart);
        return root;
    }

    private void addEntry(float value) {
        Date d = new Date();
        entries.add(new Entry(entries.size(), value));
        LineDataSet dataSet = new LineDataSet(entries, "Accelerations - Time series");
        LineData data = new LineData(dataSet);
        Log.d("size", entries.size()+"");
        XAxis xAxis = chart.getXAxis();
        chart.setData(data);
        chart.notifyDataSetChanged();
        //refresh
        chart.invalidate();
    }

    @Override
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelSensor, SensorManager.SENSOR_DELAY_NORMAL);
        entries.clear();
    }

    @Override
    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
        entries.clear();
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float X = event.values[0];
            float Y = event.values[1];
            float Z = event.values[2];
            double accleration = Math.sqrt((X * X) + (Y * Y) + (Z * Z));

            addEntry((float) accleration);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}