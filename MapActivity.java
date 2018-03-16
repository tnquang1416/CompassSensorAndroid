public class MapActivity extends AppCompatActivity {
    private CompassSensorListener m_compassSensorListener;
    private SensorManager m_sensorMng;
    private Sensor m_accelerometer;
    private Sensor m_magnetometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
		
        m_list = addressModify.getPlaceArrayList();
        m_manager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (m_sensorMng == null) {
            initSensor();
        }
        m_sensorMng.registerListener(m_compassSensorListener, m_accelerometer, SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI);
        m_sensorMng.registerListener(m_compassSensorListener, m_magnetometer, SensorManager.SENSOR_DELAY_NORMAL, SensorManager.SENSOR_DELAY_UI);
    }

    private void initSensor() {
        m_sensorMng = (SensorManager) getSystemService(SENSOR_SERVICE);
        m_accelerometer = m_sensorMng.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        m_magnetometer = m_sensorMng.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        m_compassSensorListener = new CompassSensorListener();
    }

    @Override
    protected void onPause() {
        super.onPause();
        m_sensorMng.unregisterListener(m_compassSensorListener);
    }
}
