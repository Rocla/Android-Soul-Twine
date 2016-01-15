package com.romainclaret.android.soultwine;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.ActivityNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.os.StrictMode;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.badpx.particleandroid.PListParticleSystemHelper;
import com.badpx.particleandroid.ParticleSystem;
import com.badpx.particleandroid.widget.ParticleSystemView;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.alicebot.ab.AIMLProcessor;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.Graphmaster;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.PCAIMLProcessorExtension;

public class MainActivity extends Activity implements View.OnTouchListener {

    Button btnConnect;
    EditText txtIPAddress;
    String ipString;

    public Bot bot;
    public static Chat chat;
    public TextView tvOutput;
    public ScrollView svOutput;
    public EditText etInput;
    public Button btnParse;
    public TextToSpeech speech;

    private Thread thread;
    private ParticleSystem mParticleSystem;
    private int mIndex = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.romainclaret.android.soultwine/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.romainclaret.android.soultwine/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

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
            new PListCreator("Galaxy.plist"),
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setupParticleSystem();

        txtIPAddress = (EditText) findViewById(R.id.txtIPAddress);
        btnConnect = (Button) findViewById(R.id.btnConnect);

        btnConnect.setOnClickListener(btnConnectPressedListener);

        speech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    speech.setLanguage(Locale.US);
                }
            }
        });

        tvOutput = (TextView) findViewById(R.id.tvOutput);
        svOutput = (ScrollView) findViewById(R.id.scrollView1);
        etInput = (EditText) findViewById(R.id.etInput);
        btnParse = (Button) findViewById(R.id.btnParse);
        btnParse.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                tvOutput.setText(tvOutput.getText().toString() + "\nHuman: " + etInput.getText().toString());
                String response = chat.multisentenceRespond(etInput.getText().toString());
                if (response.contains("<oob>")) {
                    response = response.replace("<oob>", "");
                    response = response.replace("</oob>", "");

                    if (response.contains("<dial>")) {
                        response = response.replace("<dial>", "");
                        response = response.replace("</dial>", ",");

                        try {
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse("tel:" + response.split(",")[0]));
                            startActivity(callIntent);
                        } catch (ActivityNotFoundException activityException) {
                            Log.e("Calling a Phone Number", "Call failed", activityException);
                        }

                        response = response.split(",")[1];
                    }
                }
                speech.speak(response, TextToSpeech.QUEUE_FLUSH, null);
                tvOutput.append("\nRobot: " + response + "\n");
                etInput.setText("");
                svOutput.post(new Runnable() {
                    public void run() {
                        svOutput.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        });

        AssetManager assets = getResources().getAssets();
        File brainDir = new File(Environment.getExternalStorageDirectory().toString() + "/brains/bots/pyschlink");

        if (brainDir.exists()) {
            loadAI(assets, brainDir);
        } else {
            brainDir.mkdirs();
            loadAI(assets, brainDir);
        }

        MagicStrings.root_path = Environment.getExternalStorageDirectory().toString() + "/brains";
        System.out.println("Working Directory = " + MagicStrings.root_path);
        AIMLProcessor.extension = new PCAIMLProcessorExtension();
        bot = new Bot("pyschlink", MagicStrings.root_path, "chat");
        chat = new Chat(bot);

        String init_request = "Hello.";
        String init_response = chat.multisentenceRespond(init_request);
        speech.speak(init_response, TextToSpeech.QUEUE_FLUSH, null);
        tvOutput.append("Robot: " + init_response + "\n");
        mainFunction();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void loadAI(AssetManager assets, File brainDir) {
        try {
            for (String dir : assets.list("pyschlink")) {
                File subdir = new File(brainDir.getPath() + "/" + dir);
                if (!subdir.exists()) {
                    subdir.mkdirs();
                }
                for (String file : assets.list("pyschlink/" + dir)) {
                    File folder = new File(brainDir.getPath() + "/" + dir + "/" + file);
                    if (folder.exists()) {
                        continue;
                    }
                    InputStream in;
                    OutputStream out;
                    in = assets.open("pyschlink/" + dir + "/" + file);
                    out = new FileOutputStream(brainDir.getPath() + "/" + dir + "/" + file);
                    copyFile(in, out);
                    in.close();
                    out.flush();
                    out.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void sendSoul() {
        Intent intent = new Intent(MainActivity.this, Commandment.class);
        intent.putExtra("ipaddress", ipString);
        startActivity(intent);
    }

    private void connectSuccessPopup() {
        final AlertDialog alertDialogConnect = new AlertDialog.Builder(MainActivity.this).create();
        alertDialogConnect.setTitle("Correct IP");
        alertDialogConnect.setMessage("Soul transfer in progress");
        alertDialogConnect.setButton("Thank\'s", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                DroidPuppet.setName(ipString);
                sendSoul();
            }
        });
        alertDialogConnect.show();
    }

    private void connectUnsuccessPopup() {
        final AlertDialog alertDialogConnect = new AlertDialog.Builder(MainActivity.this).create();
        alertDialogConnect.setTitle("Connection Unsuccessful");
        alertDialogConnect.setMessage("Connection was unsuccessful. Retry connection or go back?");
        alertDialogConnect.setButton("Go Back", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialogConnect.cancel();
            }
        });
        alertDialogConnect.setButton2("Retry", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialogConnect.show();
    }

    private void incorrectIPPopup() {
        final AlertDialog alertDialogConnect = new AlertDialog.Builder(MainActivity.this).create();
        alertDialogConnect.setTitle("Invalid IP");
        alertDialogConnect.setMessage("The IP address you entered is invalid. Please enter a correct IP and try to connect again.");
        alertDialogConnect.setButton("Go Back", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialogConnect.cancel();
            }
        });
        alertDialogConnect.show();
    }

    private OnClickListener btnConnectPressedListener = new OnClickListener() {
        public void onClick(View v) {
            Pattern pattern = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
            ipString = txtIPAddress.getText().toString();
            Matcher matcher = pattern.matcher(ipString);
            if (matcher.matches()) {
                connectSuccessPopup();
                txtIPAddress.setText("");
            } else if (ipString.equals("Nope")) {
                connectUnsuccessPopup();
                txtIPAddress.setText("");
            } else {
                incorrectIPPopup();
                txtIPAddress.setText("");
            }
        }
    };

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    public static void mainFunction() {
        MagicBooleans.trace_mode = false;
        Graphmaster.enableShortCuts = true;
    }

    protected void onPause() {
        super.onPause();
        chat.bot.writeAIMLFiles();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ParticleSystemView particleView = (ParticleSystemView) findViewById(com.romainclaret.android.soulkeeper.R.id.ps);
        particleView.clearParticleSystems();
    }

    private void setupParticleSystem() {
        ParticleSystemView particleView = (ParticleSystemView) findViewById(com.romainclaret.android.soulkeeper.R.id.ps);

        int posX = (getResources().getDisplayMetrics().widthPixels - 20) / 2;
        int posY = (getResources().getDisplayMetrics().heightPixels - 470) / 2;

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
        final int offsetY = -470;
        final int viewMaxX = getResources().getDisplayMetrics().widthPixels - 20;
        final int viewMaxY = getResources().getDisplayMetrics().heightPixels;

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Integer travelToX = randInt(5, viewMaxX);
                    Integer travelToY = randInt(5, viewMaxY + offsetY);
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
        System.out.println("X: " + event.getX());
        System.out.println("Y: " + event.getY());
        return true;
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}
