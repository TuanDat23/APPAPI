package com.example.appapi;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView txtIdCuoi, txtTinNhan;
    private Button btnLayIdCuoi, btnLayTinMoi;
    private Handler handler = new Handler();
    private int idCuoi = 0; // Lưu last_id hiện tại

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtIdCuoi = findViewById(R.id.tvLastId);
        txtTinNhan = findViewById(R.id.tvMessage);
        btnLayIdCuoi = findViewById(R.id.btnGetLastId);
        btnLayTinMoi = findViewById(R.id.btnGetNextId);

        btnLayIdCuoi.setOnClickListener(v -> layIdCuoi());
        btnLayTinMoi.setOnClickListener(v -> layTinNhan(idCuoi + 1));

        // Lặp lại mỗi 30 giây
        handler.postDelayed(capNhatTuDong, 30000);
    }

    private final Runnable capNhatTuDong = new Runnable() {
        @Override
        public void run() {
            layIdCuoi();
            handler.postDelayed(this, 30000);
        }
    };

    private void layIdCuoi() {
        new Thread(() -> {
            try {
                URL url = new URL("https://57kmt.duckdns.org/android/api.aspx?action=last_id");
                HttpURLConnection ketNoi = (HttpURLConnection) url.openConnection();
                ketNoi.setRequestMethod("GET");

                BufferedReader doc = new BufferedReader(new InputStreamReader(ketNoi.getInputStream()));
                String phanHoi = doc.readLine();
                doc.close();

                JSONObject json = new JSONObject(phanHoi);
                int idMoi = json.getInt("last_id");

                runOnUiThread(() -> {
                    txtIdCuoi.setText("ID cuối: " + idMoi);
                    if (idMoi > idCuoi) {
                        idCuoi = idMoi;
                        layTinNhan(idCuoi);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void layTinNhan(int id) {
        new Thread(() -> {
            try {
                URL url = new URL("https://57kmt.duckdns.org/android/api.aspx?action=get_id&id=" + id);
                HttpURLConnection ketNoi = (HttpURLConnection) url.openConnection();
                ketNoi.setRequestMethod("GET");

                BufferedReader doc = new BufferedReader(new InputStreamReader(ketNoi.getInputStream()));
                String phanHoi = doc.readLine();
                doc.close();

                JSONObject json = new JSONObject(phanHoi);
                String tinNhan = json.getString("message");

                runOnUiThread(() -> {
                    txtTinNhan.setText("Tin nhắn: " + tinNhan);
                    phatAmThanh();
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void phatAmThanh() {
        MediaPlayer amThanh = MediaPlayer.create(this, R.raw.choanh);
        amThanh.start();
    }
}
