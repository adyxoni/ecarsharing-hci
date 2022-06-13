package com.example.hci;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.hci.databinding.ActivityMapsBinding;
import com.google.android.material.navigation.NavigationView;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView newTitle, newBattery, newType, newTransmission, newUPrice, newPpm;
    private Button newReserve, newReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //menu hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng main = new LatLng(43.85695303208923, 18.41086689056517); // Sarajevo
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(main, 14);
        googleMap.animateCamera(cameraUpdate);
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        // Available cars
        LatLng carCentar = new LatLng(43.85713642947065, 18.413854242708);
        mMap.addMarker(new MarkerOptions().position(carCentar).title("Audi Sportback TSFI e - 89%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_car_24)));

        LatLng carAlta = new LatLng(43.85637138889927, 18.40518801752314);
        mMap.addMarker(new MarkerOptions().position(carAlta).title("Smart EQ fortwo - 68%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_car_24)));

        LatLng carKampus = new LatLng(43.85667872632243, 18.397552866262508);
        mMap.addMarker(new MarkerOptions().position(carKampus).title("BMW i3 - 44%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_car_24)));

        LatLng carEuropa = new LatLng(43.85833946134087, 18.427602299347832);
        mMap.addMarker(new MarkerOptions().position(carEuropa).title("Porsche Taycan - 92%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_car_24)));

        LatLng carCarsija = new LatLng(43.85840540139939, 18.43120789501732);
        mMap.addMarker(new MarkerOptions().position(carCarsija).title("Toyota Yaris Hybrid - 87%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_car_24)));

        LatLng carPirpa = new LatLng(43.85590664408206, 18.42178025719004);
        mMap.addMarker(new MarkerOptions().position(carPirpa).title("Smart EQ fortwo - 26%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_car_24)));

        LatLng carBistrik = new LatLng(43.856357680717544, 18.429715753096954);
        mMap.addMarker(new MarkerOptions().position(carBistrik).title("Mercedes eSprinter - 50%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_suv_24)));

        LatLng carVisnjik = new LatLng(43.86327685832064, 18.415926976916776);
        mMap.addMarker(new MarkerOptions().position(carVisnjik).title("Smart EQ fortwo - 100%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_car_24)));

        LatLng carMrvica = new LatLng(43.85335523965101, 18.371639484602134);
        mMap.addMarker(new MarkerOptions().position(carMrvica).title("Mercedes A250e - 96%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_car_24)));

        LatLng carAp = new LatLng(43.84324387567678, 18.343454616606067);
        mMap.addMarker(new MarkerOptions().position(carAp).title("Volkswagen Golf GTE - 21%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_car_24)));

        LatLng carIlidza = new LatLng(43.829145673821074, 18.312789888078086);
        mMap.addMarker(new MarkerOptions().position(carIlidza).title("Smart EQ forfour - 66%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_car_24)));

        LatLng carDobrinja = new LatLng(43.82804623491695, 18.349396077939854);
        mMap.addMarker(new MarkerOptions().position(carDobrinja).title("Smart EQ fortwo - 41%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_car_24)));

        LatLng carAerodrom = new LatLng(43.82674669634334, 18.337243459244903);
        mMap.addMarker(new MarkerOptions().position(carAerodrom).title("Mercedes eSprinter - 99%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_suv_24)));

        LatLng carButik = new LatLng(43.846298395753735, 18.361546659841213);
        mMap.addMarker(new MarkerOptions().position(carButik).title("BMW i3 - 88%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_car_24)));

        LatLng carNas = new LatLng(43.840894387513025, 18.341273136899268);
        mMap.addMarker(new MarkerOptions().position(carNas).title("Volkswagen Golf GTE - 100%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_car_24)));

        LatLng carSunnyland = new LatLng(43.84169370845235, 18.414768937106974);
        mMap.addMarker(new MarkerOptions().position(carSunnyland).title("Fiat 500e - 44%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_car_24)));

        LatLng carZagrebacka = new LatLng(43.84938343425843, 18.399889853047966);
        mMap.addMarker(new MarkerOptions().position(carZagrebacka).title("Toyota Yaris Hybrid - 97%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_car_24)));

        LatLng carGrbavica = new LatLng(43.85042841515804, 18.387585264775343);
        mMap.addMarker(new MarkerOptions().position(carGrbavica).title("Smart EQ fortwo - 61%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_car_24)));

        LatLng carStadion = new LatLng(43.84736298316146, 18.386313649798687);
        mMap.addMarker(new MarkerOptions().position(carStadion).title("Fiat 500e - 19%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_car_24)));

        LatLng carKosevo = new LatLng(43.87300749416719, 18.41098706094178);
        mMap.addMarker(new MarkerOptions().position(carKosevo).title("Fiat 500e - 90%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_car_24)));

        LatLng carBosmal = new LatLng(43.84701302078718, 18.373703054257962);
        mMap.addMarker(new MarkerOptions().position(carBosmal).title("Fiat 500e - 91%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_car_24)));

        LatLng carMahala = new LatLng(43.852059988165664, 18.378051051009237);
        mMap.addMarker(new MarkerOptions().position(carMahala).title("Toyota Yaris Hybrid - 93%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_car_24)));

        LatLng carBingo = new LatLng(43.84994094343582, 18.35361984162154);
        mMap.addMarker(new MarkerOptions().position(carBingo).title("Tesla S - 44%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_car_24)));

        LatLng carMiljacka = new LatLng(43.85064452110831, 18.35614557586279);
        mMap.addMarker(new MarkerOptions().position(carMiljacka).title("Toyota Yaris Hybrid - 81%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_car_24)));

        LatLng carPionirska = new LatLng(43.87661940265441, 18.412625162250087);
        mMap.addMarker(new MarkerOptions().position(carPionirska).title("Toyota Yaris Hybrid - 73%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_car_24)));

        LatLng carSip = new LatLng(43.87912143151535, 18.40103061641724);
        mMap.addMarker(new MarkerOptions().position(carSip).title("Fiat 500e - 33%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_car_24)));

        LatLng carMuzej = new LatLng(43.85527850807203, 18.40223304607259);
        mMap.addMarker(new MarkerOptions().position(carMuzej).title("Mercedes A200e - 38%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_car_24)));

        LatLng carPotok = new LatLng(43.85547021645282, 18.36502659879918);
        mMap.addMarker(new MarkerOptions().position(carPotok).title("Smart fortwo - 95%").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_electric_car_24)));

        //Charging stations
        LatLng stationIbis = new LatLng(43.84852638811545, 18.349722026476847);
        mMap.addMarker(new MarkerOptions().position(stationIbis).title("Charging Station - Ibis").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_ev_station_24)));

        LatLng stationPino = new LatLng(43.837689516461744, 18.453525025219687);
        mMap.addMarker(new MarkerOptions().position(stationPino).title("Charging Station - Pino").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_ev_station_24)));

        LatLng stationEuropa = new LatLng(43.85833592148974, 18.427598713449278);
        mMap.addMarker(new MarkerOptions().position(stationEuropa).title("Charging Station - Europa").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_ev_station_24)));

        LatLng stationMalak = new LatLng(43.82351485019781, 18.30886678614208);
        mMap.addMarker(new MarkerOptions().position(stationMalak).title("Charging Station - Malak").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_ev_station_24)));

        LatLng stationBristol = new LatLng(43.8528113886625, 18.389631154648402);
        mMap.addMarker(new MarkerOptions().position(stationBristol).title("Charging Station - Bristol").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_ev_station_24)));

        LatLng stationSwiss = new LatLng(43.85512737041441, 18.407046773063943);
        mMap.addMarker(new MarkerOptions().position(stationSwiss).title("Charging Station - Swiss").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_ev_station_24)));

        LatLng stationSaraj = new LatLng(43.85942486551326, 18.439383124918418);
        mMap.addMarker(new MarkerOptions().position(stationSaraj).title("Charging Station - Saraj").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_ev_station_24)));

        LatLng stationAvaz = new LatLng(43.86095972876291, 18.401761888311974);
        mMap.addMarker(new MarkerOptions().position(stationAvaz).title("Charging Station - Avaz").icon(bitmapDescriptorFromVector(getApplicationContext(),R.drawable.ic_baseline_ev_station_24)));

        //any marker opens
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                createNewPopup();
                return false;
            }
        });
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId){
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0,0,vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){

        switch(menuItem.getItemId()){
            case R.id.nav_subscriptions:
                Intent intent = new Intent(MapsActivity.this, SubscriptionsActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_history:
                Toast.makeText(this, "No rental history :(", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_signout:
                Intent i = new Intent(MapsActivity.this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.nav_help:
                Toast.makeText(this, "We don't provide help :P", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_scanUnlock:
                Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(openCamera, 100);
                break;
            case R.id.nav_settings:
                Toast.makeText(this, "Settings to be added", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_rate:
                Toast.makeText(this, "Thank you for rating us!", Toast.LENGTH_SHORT).show();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap photo = (Bitmap) data.getExtras().get("data");
    }

    //menu variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    public void createNewPopup(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View infoPopup = getLayoutInflater().inflate(R.layout.popup, null);
        newTitle = (TextView) infoPopup.findViewById(R.id.title);
        newBattery = (TextView) infoPopup.findViewById(R.id.battery);
        newType = (TextView) infoPopup.findViewById(R.id.type);
        newTransmission = (TextView) infoPopup.findViewById(R.id.transmission);
        newUPrice = (TextView) infoPopup.findViewById(R.id.uprice);
        newPpm = (TextView) infoPopup.findViewById(R.id.ppm);

        newReserve = (Button) infoPopup.findViewById(R.id.reserve);
        newReturn = (Button) infoPopup.findViewById(R.id.returnbutton);

        dialogBuilder.setView(infoPopup);
        dialog = dialogBuilder.create();
        dialog.show();

        newReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MapsActivity.this, "Temporarily unavailable!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        newReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}