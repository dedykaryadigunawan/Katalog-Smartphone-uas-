package com.dedy.katalogsmartphone;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Date;

public class FormMenuSmartphoneActivity extends AppCompatActivity {

    Button btnSimpan;
    TextInputLayout tilSpesifikasi, tilBrand, tilmodel, texHarga;
    EditText edtTgl;
    Spinner spJenisSmatrphone;
    Date tanggalSmartphone;
    final String[] tipeFavorite = {Smartphone.Baru, Smartphone.Bekas};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_smartphone);
        inisialisasiView();
    }

    private void inisialisasiView() {
        btnSimpan = findViewById(R.id.btn_simpan);
        btnSimpan.setOnClickListener(view -> simpan());
        edtTgl = findViewById(R.id.edt_tgl);
        edtTgl.setOnClickListener(view -> pickDate());
        tilSpesifikasi = findViewById(R.id.tilSpesifikasi);
        texHarga = findViewById(R.id.til_harga);
        tilBrand = findViewById(R.id.til_brand);
        tilmodel = findViewById(R.id.til_model);
        spJenisSmatrphone = findViewById(R.id.spn_jenis);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                tipeFavorite
        );
        spJenisSmatrphone.setAdapter(adapter);
        spJenisSmatrphone.setSelection(0);
    }

    private void simpan() {
        if (isDataValid()) {
            Smartphone tr = new Smartphone();

            tr.setBrand(tilBrand.getEditText().getText().toString());
            tr.setJenis(spJenisSmatrphone.getSelectedItem().toString());
            tr.setModel(tilmodel.getEditText().getText().toString());
            tr.setHarga(texHarga.getEditText().getText().toString());
            tr.setSpesifikasi(tilSpesifikasi.getEditText().getText().toString());
            tr.setTanggal(tanggalSmartphone);
            SharedPreferenceUtility.addMenuRestoran(this, tr);
            Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();

            // Kembali ke layar sebelumnya setelah 500 ms (0.5 detik)
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 500);


        }
    }

    private boolean isDataValid() {
        if (tilSpesifikasi.getEditText().getText().toString().isEmpty()
                || tilBrand.getEditText().getText().toString().isEmpty()
                || tilmodel.getEditText().getText().toString().isEmpty()

        ) {
            Toast.makeText(this, "Lengkapi semua isian", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /*
        Dipanggil saat user ingin mengisi tanggal transaksi
        Menampilkan date picker dalam popup dialog
     */
    private void pickDate() {
        final Calendar c = Calendar.getInstance();
        int thn = c.get(Calendar.YEAR);
        int bln = c.get(Calendar.MONTH);
        int tgl = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (DatePickerDialog.OnDateSetListener) (view, yyyy, mm, dd) -> {
                    edtTgl.setText(dd + "-" + (mm + 1) + "-" + yyyy);
                    c.set(yyyy, mm, dd);
                    tanggalSmartphone = c.getTime();
                },
                thn, bln, tgl);
        datePickerDialog.show();
    }

}