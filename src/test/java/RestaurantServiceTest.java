import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;

    //REFACTOR ALL THE REPEATED LINES OF CODE
    public void newly_added_restaurants() {
        restaurant= service.addRestaurant("Amelie's cafe", "chennai", LocalTime.parse("09:30:00"), LocalTime.parse("21:30:00"));
        service.addRestaurant("Bombay Cafe", "mumbai", LocalTime.parse("12:30:00"), LocalTime.parse("18:00:00"));
        restaurant.addToMenu("Death By Chocolate", 450);
        restaurant.addToMenu("Double Patty Hamburger", 650);
    }


    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
        newly_added_restaurants();
        assertEquals("Bombay Cafe", service.findRestaurantByName("Bombay Cafe").getName());
    }
    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
        newly_added_restaurants();
        assertThrows(restaurantNotFoundException.class, () -> service.findRestaurantByName("Wilbur's cafe"));

        //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>

    }
        //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        @Test
        public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1 () throws restaurantNotFoundException
        {
            newly_added_restaurants();
            int initialNumberOfRestaurants = service.getRestaurants().size();
            service.removeRestaurant("Amelie's cafe");
            assertEquals(initialNumberOfRestaurants - 1, service.getRestaurants().size());
        }

        @Test
        public void removing_restaurant_that_does_not_exist_should_throw_exception () throws restaurantNotFoundException
        {
            newly_added_restaurants();
            assertThrows(restaurantNotFoundException.class, () -> service.removeRestaurant("Pantry d'or"));
        }

        @Test
        public void add_restaurant_should_increase_list_of_restaurants_size_by_1 () {
            newly_added_restaurants();
            int initialNumberOfRestaurants = service.getRestaurants().size();
            service.addRestaurant("Pumpkin Tales", "Chennai", LocalTime.parse("12:00:00"), LocalTime.parse("23:00:00"));
            assertEquals(initialNumberOfRestaurants + 1, service.getRestaurants().size());
        }
        //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
    }