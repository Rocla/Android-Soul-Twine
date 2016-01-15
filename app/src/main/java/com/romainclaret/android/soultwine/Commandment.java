package com.romainclaret.android.soultwine;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.os.StrictMode;
import android.widget.ToggleButton;

import com.badpx.particleandroid.PListParticleSystemHelper;
import com.badpx.particleandroid.ParticleSystem;
import com.badpx.particleandroid.widget.ParticleSystemView;

import java.util.Random;
import java.util.regex.PatternSyntaxException;

public class Commandment extends Activity implements SensorEventListener, View.OnTouchListener {

	String ipAddress;
    static int i = 0;
    static String prevCommand = "";

    private Thread thread;
    private ParticleSystem mParticleSystem;
    private int mIndex = 0;
	private float[] mMagneticFieldData = new float[3];
	private float[] mAccelerometerData = new float[3];
	private float[] rotationMatrix = new float[16];
	private float[] orientationValues = new float[4];
	private SensorManager sensorManager;

    public ToggleButton tbAcceleroMove;
    public ToggleButton tbSequences;
    public EditText etSequences;
    public Button btnRun;

    boolean debugMode = false;

    interface ParticleSystemCreator {
        ParticleSystem create(Resources resources);
    }

    static class PListCreator implements ParticleSystemCreator {
        final String plistPath;
        ParticleSystem particleSystem;

        public PListCreator(String plistPath) {
            this.plistPath = plistPath;
        }

        @Override
        public String toString() {
            return plistPath;
        }

        @Override
        public ParticleSystem create(Resources resources) {
            if (null == particleSystem) {
                particleSystem = PListParticleSystemHelper.create(resources, plistPath);
            }
            return particleSystem;
        }
    }

    private static final ParticleSystemCreator[] PARTICLE_INFOS = {
            new PListCreator("Sun.plist"),
    };
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_commandment);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		ipAddress = this.getIntent().getExtras().getString("ipaddress");

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_NORMAL);

        tbAcceleroMove = (ToggleButton) findViewById(R.id.toggleButton);
        tbSequences = (ToggleButton) findViewById(R.id.toggleButton2);
        etSequences = (EditText) findViewById(R.id.etSequences);
        btnRun = (Button) findViewById(R.id.btnRun);

        etSequences.setVisibility(View.GONE);
        btnRun.setVisibility(View.GONE);

        tbSequences.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etSequences.setVisibility(View.VISIBLE);
                    btnRun.setVisibility(View.VISIBLE);
                    etSequences.setText("");
                } else {
                    etSequences.setVisibility(View.GONE);
                    btnRun.setVisibility(View.GONE);
                }
            }
        });

        setupParticleSystem();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

    @Override
    public void onBackPressed(){
        sensorManager.unregisterListener(this);
        ParticleSystemView particleView = (ParticleSystemView) findViewById(com.romainclaret.android.soulkeeper.R.id.ps);
        particleView.clearParticleSystems();
        finish();
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }

    public void runSequencePressed(View view)
    {
        try {
            String[] orders = etSequences.getText().toString().split("\\s+");
            for (String order : orders) {
                if (order.equals("stand"))
                {
                    DroidPuppet.Stand(this);
                    Toast.makeText(getBaseContext(), "Order: " + order, Toast.LENGTH_LONG).show();
                }
                else if (order.equals("sit"))
                {
                    DroidPuppet.Sit(this);
                    Toast.makeText(getBaseContext(), "Order: " + order, Toast.LENGTH_LONG).show();
                }
                else if (order.equals("forward"))
                {
                    DroidPuppet.Forward();
                    Toast.makeText(getBaseContext(), "Order: " + order, Toast.LENGTH_LONG).show();
                }
                else if (order.equals("back"))
                {
                    DroidPuppet.Backward();
                    Toast.makeText(getBaseContext(), "Order: " + order, Toast.LENGTH_LONG).show();
                }
                else if (order.equals("left"))
                {
                    DroidPuppet.RotateLeft();
                    Toast.makeText(getBaseContext(), "Order: " + order, Toast.LENGTH_SHORT).show();
                }
                else if (order.equals("right"))
                {
                    DroidPuppet.RotateRight();
                    Toast.makeText(getBaseContext(), "Order: " + order, Toast.LENGTH_SHORT).show();
                }
                else if (order.equals(""))
                {

                }
                else
                {
                    Toast.makeText(getBaseContext(), "Unknown command: " + order, Toast.LENGTH_LONG).show();
                }
            }
        } catch (PatternSyntaxException ex) {
            Toast.makeText(getBaseContext(), "Cannot communicate with distant soul.", Toast.LENGTH_LONG).show();
        }
    }
    
	public void standPressed(View view)
    {
        if(tbSequences.isChecked())
        {
            if(!etSequences.getText().equals(""))
            {
                etSequences.setText(etSequences.getText() + " ");
            }
            etSequences.setText(etSequences.getText() + "stand");
        }
        else
        {
            DroidPuppet.Stand(this);
        }
	}
	
	public void sitPressed(View view)
    {
        if(tbSequences.isChecked())
        {
            if(!etSequences.getText().equals(""))
            {
                etSequences.setText(etSequences.getText() + " ");
            }
            etSequences.setText(etSequences.getText() + "sit");
        }
        else
        {
            DroidPuppet.Sit(this);
        }
	}
	
	public void forwardPressed(View view)
    {
        if(tbSequences.isChecked())
        {
            if(!etSequences.getText().equals(""))
            {
                etSequences.setText(etSequences.getText() + " ");
            }
            etSequences.setText(etSequences.getText() + "forward");
        }
        else
        {
            DroidPuppet.Forward();
        }
	}
	
	public void backwardPressed(View view)
    {
        if(tbSequences.isChecked())
        {
            if(!etSequences.getText().equals(""))
            {
                etSequences.setText(etSequences.getText() + " ");
            }
            etSequences.setText(etSequences.getText() + "back");
        }
        else
        {
            DroidPuppet.Backward();
        }
	}
	
	public void leftPressed(View view)
    {
        if(tbSequences.isChecked())
        {
            if(!etSequences.getText().equals(""))
            {
                etSequences.setText(etSequences.getText() + " ");
            }
            etSequences.setText(etSequences.getText() + "left");
        }
        else
        {
            DroidPuppet.RotateLeft();
        }
	}
	
	public void rightPressed(View view)
    {
        if(tbSequences.isChecked())
        {
            if(!etSequences.getText().equals(""))
            {
                etSequences.setText(etSequences.getText() + " ");
            }
            etSequences.setText(etSequences.getText() + "right");
        }
        else
        {
            DroidPuppet.RotateRight();
        }
	}

    public void onSensorChanged(SensorEvent event)
    {
        if(tbAcceleroMove.isChecked())
        {
            switch (event.sensor.getType ()){
                case Sensor.TYPE_ACCELEROMETER:
                    mAccelerometerData = event.values.clone();
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    mMagneticFieldData = event.values.clone();
                    extractOrientationValues();
                    break;
            }
        }
    }

    private void extractOrientationValues() {
        if (SensorManager.getRotationMatrix(rotationMatrix, null, mAccelerometerData, mMagneticFieldData))
        {
            SensorManager.getOrientation(rotationMatrix, orientationValues);

            float pitch = (float)Math.toDegrees(orientationValues[1]);
            float roll = (float)Math.toDegrees(orientationValues[2]);

            //convert to degrees
            pitch = (float) ((pitch + 360.0) % 360.0);
            roll = (float) ((roll + 360.0) % 360.0);
            checkOrder(pitch, roll);
        }
    }

    private void checkOrder(float pitch, float roll) {

        boolean isRotateRight = pitch >= 0 && pitch <= 10  && roll >= 20 && roll <= 70;
        boolean isRotateLeft = pitch >= 0 && pitch <= 10  && roll >= 260 && roll <= 315;
        boolean isRotateFor = pitch >= 0 && pitch <= 50  && roll >= 0 && roll <= 50;
        boolean isRotateBack = pitch >= 280 && pitch <= 320  && roll >= 170 && roll <= 200;

        if(isRotateRight)
        {
            if (prevCommand.equals("Right")){
                i++;
            }
            else {
                i = 1;
            }
            //doWhenRotateRight.run();
            prevCommand = "Right";
            if (i >= 10){
                DroidPuppet.RotateRight();
                System.out.println("Right");
                i = 0;
                Toast.makeText(getBaseContext(), "Order: Right", Toast.LENGTH_SHORT).show();
                if (debugMode)
                {
                    System.out.println("Pitch: " + pitch);
                    System.out.println("Roll: " + roll);
                }
            }
        }
        else if(isRotateLeft)
        {
            if (prevCommand.equals("Left")){
                i++;
            }
            else {
                i = 1;
            }
            prevCommand = "Left";
            if (i >= 10){
                DroidPuppet.RotateLeft();
                System.out.println("Left");
                i = 0;
                Toast.makeText(getBaseContext(), "Order: Left", Toast.LENGTH_SHORT).show();
                if (debugMode)
                {
                    System.out.println("Pitch: " + pitch);
                    System.out.println("Roll: " + roll);
                }
            }
        }
        else if(isRotateFor)
        {
            if (prevCommand.equals("Forward")){
                i++;
            }
            else {
                i = 1;
            }
            prevCommand = "Forward";
            if (i >= 15){
                DroidPuppet.Forward();
                System.out.println("Forward");
                i = 0;
                Toast.makeText(getBaseContext(), "Order: Forward", Toast.LENGTH_SHORT).show();
                if (debugMode)
                {
                    System.out.println("Pitch: " + pitch);
                    System.out.println("Roll: " + roll);
                }
            }
        }
        else if(isRotateBack)
        {
            if (prevCommand.equals("Backward")){
                i++;
            }
            else {
                i = 1;
            }
            prevCommand = "Backward";
            if (i >= 15){
                DroidPuppet.Backward();
                System.out.println("Backward");
                i = 0;
                Toast.makeText(getBaseContext(), "Order: Backward", Toast.LENGTH_SHORT).show();
                if (debugMode)
                {
                    System.out.println("Pitch: " + pitch);
                    System.out.println("Roll: " + roll);
                }
            }
        }
        else
        {
            if (prevCommand.equals("Still")){
                i++;
            }
            else {
                i = 1;
            }
            prevCommand = "Still";
            if (i >= 9){
                i = 0;
                Toast.makeText(getBaseContext(), "No order detected", Toast.LENGTH_SHORT).show();
                if (debugMode)
                {
                    System.out.println("Pitch: " + pitch);
                    System.out.println("Roll: " + roll);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ParticleSystemView particleView = (ParticleSystemView) findViewById(com.romainclaret.android.soulkeeper.R.id.ps);
        particleView.clearParticleSystems();
    }

    private void setupParticleSystem() {
        ParticleSystemView particleView = (ParticleSystemView) findViewById(com.romainclaret.android.soulkeeper.R.id.ps);

        int posX = getResources().getDisplayMetrics().widthPixels / 2;
        int posY = getResources().getDisplayMetrics().heightPixels / 2 - 400;

        if (null != mParticleSystem) {
            // Stop previous particle emitter if it exists.
            mParticleSystem.shutdown();
        }

        mParticleSystem = PARTICLE_INFOS[mIndex].create(getResources());
        if (null != mParticleSystem) {

            particleView.addParticleSystem(mParticleSystem);
            mParticleSystem.setPosition(posX, posY);
            mParticleSystem.startup();
            particleView.setOnTouchListener(this);
        }

        giveLife();
    }

    public void giveLife() {
        final int offsetY = -200;
        final int viewMaxX = getResources().getDisplayMetrics().widthPixels;
        final int viewMaxY = getResources().getDisplayMetrics().heightPixels;

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Integer travelToX = randInt(10, viewMaxX);
                    Integer travelToY = randInt(10, viewMaxY + offsetY);
                    Float currentPosX;
                    Float currentPosY;
                    int displacementDistance = 1;

                    do {
                        try {
                            Thread.currentThread();
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        currentPosX = mParticleSystem.getPositionX();
                        currentPosY = mParticleSystem.getPositionY();

                        if (travelToX < currentPosX && travelToY < currentPosY) {
                            mParticleSystem.setPosition(currentPosX - displacementDistance, currentPosY - displacementDistance);
                        } else if (travelToX < currentPosX && travelToY > currentPosY) {
                            mParticleSystem.setPosition(currentPosX - displacementDistance, currentPosY + displacementDistance);
                        } else if (travelToX > currentPosX && travelToY < currentPosY) {
                            mParticleSystem.setPosition(currentPosX + displacementDistance, currentPosY - displacementDistance);
                        } else if (travelToX > currentPosX && travelToY > currentPosY) {
                            mParticleSystem.setPosition(currentPosX + displacementDistance, currentPosY + displacementDistance);
                        } else if (travelToX.intValue() == currentPosX && travelToY < currentPosY) {
                            mParticleSystem.setPosition(currentPosX, currentPosY - displacementDistance);
                        } else if (travelToX.intValue() == currentPosX && travelToY > currentPosY) {
                            mParticleSystem.setPosition(currentPosX, currentPosY + displacementDistance);
                        } else if (travelToX > currentPosX && travelToY.intValue() == currentPosY) {
                            mParticleSystem.setPosition(currentPosX + displacementDistance, currentPosY);
                        } else if (travelToX < currentPosX && travelToY.intValue() == currentPosY) {
                            mParticleSystem.setPosition(currentPosX - displacementDistance, currentPosY);
                        }
                    }
                    while (currentPosX != travelToX.intValue() && currentPosY != travelToY.intValue());
                }
            }
        });
        thread.start();
    }

    public boolean onTouch(View v, MotionEvent event) {
        mParticleSystem.setPosition(event.getX(), event.getY());
        return true;
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}
