import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    public LocalTime openingTime;
    public LocalTime closingTime;
    private String name;
    private String location;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {

        boolean isOpen = false;

        if (getCurrentTime().equals(openingTime) || getCurrentTime().isAfter(openingTime) && getCurrentTime().isBefore(closingTime)) {
            isOpen = true;
        }
        return isOpen;

        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
    }

    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    public List<Item> getMenu() {
        return menu;
        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE
    }

    private Item findItemByName(String itemName) {
        for (Item item : menu) {
            if (item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name, price);
        menu.add(newItem);
    }

    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }

    public void displayDetails() {
        System.out.println("Restaurant:" + name + "\n"
                + "Location:" + location + "\n"
                + "Opening time:" + openingTime + "\n"
                + "Closing time:" + closingTime + "\n"
                + "Menu:" + "\n" + getMenu());

    }

    public String getName() {
        return name;
    }

    //Part 3
    public int calculateOrderCost(List<String> itemNames) {
        int totalCost =0;
        for (int i=0;i<itemNames.size();i++){
            Item item = findItemByName(itemNames.get(i));
            totalCost = totalCost + item.getPrice();
        }
        return totalCost;
    }

}
