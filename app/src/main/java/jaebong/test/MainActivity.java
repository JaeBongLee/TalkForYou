package jaebong.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView imageViewGreeting;
    private ImageView imageViewTransportation;
    private ImageView imageViewAccommodation;
    private ImageView imageViewRestaurant;
    private ImageView imageViewMap;
    private ImageView imageViewShopping;
    private ImageView imageViewTour;
    private ImageView imageViewLogo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dao dao = new Dao(getApplicationContext());
        String jsonData = dao.getJsonTOString();
        dao.insertJsonData(jsonData);

        imageViewGreeting = (ImageView)findViewById(R.id.imageView_main_greeting);
        imageViewGreeting.setOnClickListener(this);

        imageViewTransportation = (ImageView)findViewById(R.id.imageView_main_transportation);
        imageViewTransportation.setOnClickListener(this);

        imageViewAccommodation = (ImageView)findViewById(R.id.imageView_main_accommodation);
        imageViewAccommodation.setOnClickListener(this);

        imageViewRestaurant = (ImageView)findViewById(R.id.imageView_main_restaurant);
        imageViewRestaurant.setOnClickListener(this);

        imageViewMap = (ImageView)findViewById(R.id.imageView_main_map);
        imageViewMap.setOnClickListener(this);

        imageViewShopping = (ImageView)findViewById(R.id.imageView_main_shopping);
        imageViewShopping.setOnClickListener(this);

        imageViewTour = (ImageView)findViewById(R.id.imageView_main_tour);
        imageViewTour.setOnClickListener(this);

        imageViewLogo = (ImageView)findViewById(R.id.imageView_main_logo);
        imageViewLogo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.imageView_main_greeting :
                Intent grertingIntent = new Intent(this,CardView.class);
                grertingIntent.putExtra("ViewData", "Greeting");
                startActivity(grertingIntent);
                break;
            case R.id.imageView_main_transportation :
                Intent transportationIntent = new Intent(this,CardView.class);
                transportationIntent.putExtra("ViewData", "Transportation");
                startActivity(transportationIntent);
                break;
            case R.id.imageView_main_accommodation :
                Intent accommodationIntent = new Intent(this,CardView.class);
                accommodationIntent.putExtra("ViewData", "Accommodation");
                startActivity(accommodationIntent);
                break;
            case R.id.imageView_main_restaurant :
                Intent restaurantIntent = new Intent(this,CardView.class);
                restaurantIntent.putExtra("ViewData", "Restaurant");
                startActivity(restaurantIntent);
                break;
            case R.id.imageView_main_map :
                Intent mapIntent = new Intent(this,CardView.class);
                mapIntent.putExtra("ViewData", "Map");
                startActivity(mapIntent);
                break;
            case R.id.imageView_main_tour :
                Intent tourIntent = new Intent(this,CardView.class);
                tourIntent.putExtra("ViewData", "Tour");
                startActivity(tourIntent);
                break;
            case R.id.imageView_main_shopping :
                Intent shoppingIntent = new Intent(this,CardView.class);
                shoppingIntent.putExtra("ViewData", "Shopping");
                startActivity(shoppingIntent);
                break;
            case R.id.imageView_main_logo :
                Intent descriptionIntent = new Intent(this,DescriptionActivity.class);
                startActivity(descriptionIntent);
        }
    }
}
