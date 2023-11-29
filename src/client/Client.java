package client;

import restaurant.FoodItem;
import restaurant.Menu;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            // Получаем меню от сервера
            Menu menu = (Menu) inputStream.readObject();
            List<FoodItem> menuItems = menu.getMenuItems();
            System.out.println("Меню:");
            for (FoodItem item : menuItems) {
                System.out.println(item.getName() + ": $" + item.getPrice());
            }

            // Создаем заказ (пример)
            // Пользователь может ввести свой заказ и адрес доставки
            // ...

            // Отправляем заказ серверу
            // ...

            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
