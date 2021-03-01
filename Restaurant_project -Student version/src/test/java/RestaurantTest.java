import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE


    public void addRestaurantAndMenus() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
        //WRITE UNIT TEST CASE HERE
        addRestaurantAndMenus();
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        LocalTime timeDuringOpenHours = LocalTime.parse("14:59:00");
        Mockito.spy(when(spyRestaurant.getCurrentTime()).thenReturn(timeDuringOpenHours));
        spyRestaurant.openingTime = LocalTime.parse("12:00:00");
        spyRestaurant.closingTime = LocalTime.parse("23:59:00");
        assertTrue(spyRestaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        //WRITE UNIT TEST CASE HERE
        addRestaurantAndMenus();
        Restaurant spyRestaurant = Mockito.spy(restaurant);
        LocalTime timeDuringOpenHours = LocalTime.parse("14:59:00");
        Mockito.spy(when(spyRestaurant.getCurrentTime()).thenReturn(timeDuringOpenHours));
        spyRestaurant.openingTime = LocalTime.parse("18:00:00");
        spyRestaurant.closingTime = LocalTime.parse("23:59:00");
        assertFalse(spyRestaurant.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {

        addRestaurantAndMenus();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        addRestaurantAndMenus();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        addRestaurantAndMenus();

        assertThrows(itemNotFoundException.class,
                () -> restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


  /* //Part 3
   @Test
   public void order_items_from_menu_and_calculate_order_cost(){
       //WRITE UNIT TEST CASE HERE
       addRestaurantAndMenus();
       List<String> itemNames = Arrays.asList("Sweet corn soup","Vegetable lasagne");
       assertEquals(388,restaurant.calculateOrderCost(itemNames));

   }*/
}