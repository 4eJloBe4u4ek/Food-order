package restaurant;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<FoodItem> items;

    public Menu() {
        this.items = new ArrayList<>();
        // Здесь можно добавить блюда в меню
        items.add(new FoodItem("Бургер", 5.99));
        items.add(new FoodItem("Пицца", 8.99));
        items.add(new FoodItem("Кола", 4));
        // Добавьте другие блюда по вашему усмотрению
    }

    public List<FoodItem> getMenuItems() {
        return items;
    }
}
