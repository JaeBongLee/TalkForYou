package jaebong.test;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class CardView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);


        String dataInfo = getIntent().getStringExtra("ViewData");
        Log.i("test","dataInfo - " + dataInfo);

        Dao dao = new Dao(getApplicationContext());

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(dataInfo.equals("Greeting")) {
            GreetingFragment greetingFragment = new GreetingFragment();
            fragmentTransaction.add(R.id.fragmentContainer, greetingFragment);
        }else if(dataInfo.equals("Transportation")) {
            TransportationFragment transportationFragment = new TransportationFragment();
            fragmentTransaction.add(R.id.fragmentContainer, transportationFragment);
        }else if(dataInfo.equals("Accommodation")) {
            AccommodationFragment accommodationFragment = new AccommodationFragment();
            fragmentTransaction.add(R.id.fragmentContainer, accommodationFragment);
        }else if(dataInfo.equals("Restaurant")){
            RestaurantFragment restaurantFragment = new RestaurantFragment();
            fragmentTransaction.add(R.id.fragmentContainer, restaurantFragment);
        }else if(dataInfo.equals("Shopping")){
            ShoppingFragment shoppingFragment = new ShoppingFragment();
            fragmentTransaction.add(R.id.fragmentContainer, shoppingFragment);
        }else if (dataInfo.equals("Map")){
            MapFragment mapFragment = new MapFragment();
            fragmentTransaction.add(R.id.fragmentContainer,mapFragment);
        }else if (dataInfo.equals("Tour")){
            TourFragment tourFragment = new TourFragment();
            fragmentTransaction.add(R.id.fragmentContainer,tourFragment);
        }
        fragmentTransaction.commit();
    }


}
