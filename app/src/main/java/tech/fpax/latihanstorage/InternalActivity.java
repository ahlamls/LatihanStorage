package tech.fpax.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class InternalActivity extends AppCompatActivity  implements View.OnClickListener {

    public static final String FILENAME = "namafile.txt";
    Button buat_btn,ubah_btn,baca_btn,hapus_btn;
    TextView baca_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);
        buat_btn = findViewById(R.id.buat_btn);
        ubah_btn = findViewById(R.id.ubah_btn);
        baca_btn = findViewById(R.id.baca_btn);
        hapus_btn = findViewById(R.id.hapus_btn);
        baca_tv = findViewById(R.id.baca_tv);

        buat_btn.setOnClickListener(this);
        ubah_btn.setOnClickListener(this);
        hapus_btn.setOnClickListener(this);
        baca_btn.setOnClickListener(this);
    }

    void buatFile() {
        String isiFile = "Coba Isi Data File Text";
        File file = new File(getFilesDir(), FILENAME);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file,true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    void bacaFile() {
        File sdcard = getFilesDir();
        File file = new File(sdcard, FILENAME);

        if (file.exists()) {

            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            baca_tv.setText(text.toString());


        } else {
            baca_tv.setText("File Tidak ada");

        }
    }

    void ubahFile() {
        String ubah = "Update Isi Data File Text";
        File file = new File(getFilesDir(), FILENAME);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(ubah.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void hapusFile(){
        File file = new File(getFilesDir(), FILENAME);
        if(file.exists()) {
            file.delete();
        }
    }

    public void jalankanperintah(int id) {
        switch (id){
            case R.id.buat_btn:
                buatFile();
                break;
            case R.id.baca_btn:
                bacaFile();
                break;
            case R.id.hapus_btn:
                hapusFile();
                break;
            case R.id.ubah_btn:
                ubahFile();
                break;
        }
    }

    @Override
    public void onClick(View view) {
    jalankanperintah(view.getId());
    }

}
