package ru.mirea.msv.mireaproject;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class Sensors extends Fragment {
    private TextView sensor1;
    private TextView sensor2;
    private TextView sensor3;
    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private Sensor gravitySensor;
    private Sensor gyroscopeSensor;
    private SensorEventListener listener;
    private Uri imageUri;
    private static final int REQUEST_CODE_PERMISSION = 100;
    private ImageView cameraResult;
    private static final int CAMERA_REQUEST = 0;
    private static boolean permissionsGranted = false;
    private static boolean isPlaying = false;
    private static boolean isRecording = false;
    private Activity activity;
    private MediaRecorder mediaRecorder;
    private File audioFile;


    public Sensors(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        int cameraPermissionStatus =
                ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
        int storagePermissionStatus = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int micPermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO);
        if (cameraPermissionStatus == PackageManager.PERMISSION_GRANTED && storagePermissionStatus == PackageManager.PERMISSION_GRANTED && micPermission == PackageManager.PERMISSION_GRANTED) {
            permissionsGranted = true;
        } else {
            requestPermissions(new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO},
                    REQUEST_CODE_PERMISSION);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listener);
    }
    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(listener, accelerometerSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(listener, gyroscopeSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(listener, gravitySensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_service, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sensorManager =
                (SensorManager)(activity.getSystemService(Context.SENSOR_SERVICE));
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sensor1 = view.findViewById(R.id.sensor1);
        sensor2 = view.findViewById(R.id.sensor2);
        sensor3 = view.findViewById(R.id.sensor3);
        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                switch (event.sensor.getType()){
                    case (Sensor.TYPE_ACCELEROMETER):
                    {
                        sensor1.setText(new String("\tAzimuth: " + event.values[0]
                                + "\n\tPitch: " + event.values[1]
                                + "\n\tRoll: " + event.values[2]));
                        break;
                    }
                    case (Sensor.TYPE_GYROSCOPE):
                    {
                        sensor3.setText(new String("\tX: " + event.values[0]
                                + "\n\tY: " + event.values[1]
                                + "\n\tZ: " + event.values[2]));
                        break;
                    }
                    case (Sensor.TYPE_GRAVITY):
                    {
                        sensor2.setText(new String("\tX: " + event.values[0]
                                + "\n\tY: " + event.values[1]
                                + "\n\tZ: " + event.values[2]));
                        break;
                    }
                    default: break;
                }

            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };

        try {
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state) ||
                    Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
                if (audioFile == null) {
                    audioFile = new File(activity.getExternalFilesDir(
                            Environment.DIRECTORY_MUSIC), "mirea.3gp");
                }
            }
        } catch (Exception e){
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
            view.findViewById(R.id.recordAudioButton).setEnabled(false);
            view.findViewById(R.id.playAudioButton).setEnabled(false);
        }
        mediaRecorder = new MediaRecorder();

        view.findViewById(R.id.cameraButton).setOnClickListener((view2)->{
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (cameraIntent.resolveActivity(activity.getPackageManager()) != null && isPermissionsGranted())
            {
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                    Toast.makeText(activity, photoFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(activity, "createImageFile() error", Toast.LENGTH_SHORT).show();
                }
                String authorities = activity.getApplicationContext().getPackageName() + ".fileprovider";
                imageUri = FileProvider.getUriForFile(activity, authorities, photoFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else {
                if(!isPermissionsGranted())
                    Toast.makeText(activity, "Permissions not granted", Toast.LENGTH_SHORT).show();
                if(cameraIntent.resolveActivity(activity.getPackageManager()) == null)
                    Toast.makeText(activity, "resolveActivity() null", Toast.LENGTH_SHORT).show();
            }
        });
        cameraResult = view.findViewById(R.id.cameraResult);

        view.findViewById(R.id.recordAudioButton).setOnClickListener((view2)->{
            if (!isRecording()) {
                try {
                    startRecording();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                isRecording = true;

            }else{
                stopRecording();
                isRecording = false;
            }
        });

        view.findViewById(R.id.playAudioButton).setOnClickListener((view2)->{
            if (audioFile != null && !isPlaying()) {
                Intent service = new Intent(activity, PlayerService.class);
                service.putExtra("a", audioFile.getAbsolutePath());
                activity.startService(service);
                Toast.makeText(activity, "Playing audio...", Toast.LENGTH_SHORT).show();
                isPlaying = true;
            }
            else {
                activity.stopService(new Intent(activity, PlayerService.class));
                Toast.makeText(activity, "Stopped", Toast.LENGTH_SHORT).show();
                isPlaying = false;
            }
        });
    }

    public File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "IMAGE_" + timeStamp + "_";
        File storageDirectory =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDirectory);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            cameraResult.setImageURI(imageUri);
        }
        else
        {
            Toast.makeText(activity, "onActivityResult() error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permissionsGranted = true;
                Toast.makeText(activity, "Permission granted", Toast.LENGTH_SHORT).show();
            } else {
                permissionsGranted = false;
                Toast.makeText(activity, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startRecording() throws IOException {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setOutputFile(audioFile.getAbsolutePath());
            mediaRecorder.prepare();
            mediaRecorder.start();
            Toast.makeText(activity, "Recording started!\n" + audioFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        }
    }

    private void stopRecording() {
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            Toast.makeText(activity, "You are not recording right now!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isRecording() { return isRecording; }
    public boolean isPlaying()
    {return isPlaying;}
    public boolean isPermissionsGranted()
    {return permissionsGranted;}
}
