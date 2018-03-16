public class CompassSensorListener implements SensorEventListener {
    private float[] m_acceleratorReading;
    private float[] m_magnetorReading;

    public CompassSensorListener() {
        m_acceleratorReading = new float[3];
        m_magnetorReading = new float[3];
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            System.arraycopy(sensorEvent.values, 0, m_acceleratorReading, 0, m_acceleratorReading.length);
        if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            System.arraycopy(sensorEvent.values, 0, m_magnetorReading, 0, m_magnetorReading.length);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    /**
     * get degree from North to current
     *
     * @return
     */
    public double getOrientation() {
        float[] rotationMatrix = new float[9];
        float[] angles = new float[3];
        SensorManager.getRotationMatrix(rotationMatrix, null, m_acceleratorReading, m_magnetorReading);
        SensorManager.getOrientation(rotationMatrix, angles);

        return angles[0] * 180 / Math.PI;
    }
}
