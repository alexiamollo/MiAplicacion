package com.example.nodeterministas;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.nodeterministas.Activities.ListaAplicacionesInstaladas;

import java.util.ArrayList;
import java.util.List;

public class BloquearAplicaciones extends AppCompatActivity implements View.OnClickListener{

    Button enviarDatosBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloquear_aplicaciones);

        enviarDatosBtn = (Button)findViewById(R.id.enviarDatos_btn);
        enviarDatosBtn.setOnClickListener(this);

        //get a list of installed apps.
        final PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        List<String> ListaApps= new ArrayList<String>();
        for (ApplicationInfo packageInfo : packages)
        {
            if (packageInfo.sourceDir.startsWith("/data/app/") &&
                    packageInfo.packageName.toLowerCase().contains("widget")==false &&
                    packageInfo.packageName.equals("com.example.nodeterministas")==false)
            {
                ListaApps.add(String.valueOf(pm.getApplicationLabel(packageInfo)));
            }
        }


        ArrayList<FuenteAplicacion> lista =  new ArrayList<>();
        for(int i=0; i<ListaApps.size(); i++){
            switch (ListaApps.get(i)){
                case "Facebook":
                    lista.add(new FuenteAplicacion("Facebook", R.drawable.facebook_logo));
                    break;
                case "WhatsApp":
                    lista.add(new FuenteAplicacion("WhatsApp", R.drawable.whatsapp));
                    break;
                case "Twitter":
                    lista.add(new FuenteAplicacion("Twitter", R.drawable.twitter));
                    break;
                case "Instagram":
                    lista.add(new FuenteAplicacion("Instagram", R.drawable.instagram));
                    break;
                default:
                    break;
            }
        }

        RecyclerView contenedor = (RecyclerView) findViewById(R.id.contenedor);
        contenedor.setHasFixedSize(false);

        LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);

        contenedor.setAdapter(new AdaptadorRecyclerAplicaciones(lista));
        contenedor.setLayoutManager(layout);

        ViewHolder

    }

    public RecyclerView.ViewHolder sacarViewPosicion(RecyclerView recyclerView, int pos){
        return recyclerView.findViewHolderForAdapterPosition(pos);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.enviarDatos_btn){
            //Intent intent = new Intent(enviarDatosBtn.getContext(), Temporizador.class);
            Intent intent = new Intent(enviarDatosBtn.getContext(), ListaAplicacionesInstaladas.class);
            startActivity(intent);
        }
    }
}
