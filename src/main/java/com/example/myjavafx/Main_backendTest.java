package com.example.myjavafx;

import com.example.myjavafx.core.api.OrderApi;
import com.example.myjavafx.core.api.OrderApiImpl;
import com.example.myjavafx.core.models.enums.OrderStatus;
import com.example.myjavafx.core.models.enums.PizzaType;
import com.example.myjavafx.core.models.enums.Topping;
import com.example.myjavafx.core.models.PizzaRecord;

import java.io.IOException;
import java.util.*;

public class Main_backendTest {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        OrderApi api = new OrderApiImpl();

        boolean isRunning = true;
        while (isRunning) {
            prompt();
            final int input = scanner.nextInt();
            switch(input) {
                case 1:
                    generateOrders(api);
                    break;
                case 2:
                    removeOrderById(api, scanner);
                    break;
                case 3:
                    getOrderStatus(api, scanner);
                    break;
                case 4:
                    setOrderStatus(api, scanner);
                    break;
                case 5:
                    getPizzaRecords(api, scanner);
                    break;
                default:
                    isRunning = false;
            }
        }
        System.out.println("Exiting...");
        System.exit(0);


//        OrderApi orderApi = new OrderApiImpl();
//
//        final String orderOneId = UUID.randomUUID().toString();
//        String orderId = String.valueOf(000);
//        final List<PizzaRecord> pizzaRecords = Arrays.asList(
//                new PizzaRecord(orderOneId, PizzaType.PEPPERONI, Arrays.asList(Topping.MUSHROOMS, Topping.OLIVES)),
//                new PizzaRecord(orderOneId, PizzaType.CHEESE, Arrays.asList(Topping.OLIVES, Topping.ONIONS))
//        );
//
//        orderApi.addOrder(orderOneId, pizzaRecords);
//        orderApi.setOrderStatus("96b7b080-c50d-4d2b-a01e-4193ee95bf5d", OrderStatus.COOKING);
//        orderApi.setOrderStatus("9aa6348b-989a-4c1f-bfdf-5d1143e51475", OrderStatus.READY);
//        orderApi.removeOrder("9aa6348b-989a-4c1f-bfdf-5d1143e51475");
//        orderApi.removeOrder("8e1a593d-dc9f-4e49-a149-feabaaacac65");
//
//        List<PizzaRecord> pizzas = orderApi.getPizzaRecordsByOrderId("96b7b080-c50d-4d2b-a01e-4193ee95bf5d");
//        System.out.println("Order: 96b7b080-c50d-4d2b-a01e-4193ee95bf5d");
//        System.out.println("Status: "+orderApi.getOrderStatus("96b7b080-c50d-4d2b-a01e-4193ee95bf5d"));
//        for(int p = 0; p < pizzas.size(); p++) {
//            final PizzaRecord pizza = pizzas.get(p);
//            orderId = String.valueOf(p);
//            System.out.println("Pizza-"+p);
//            System.out.println("\tType: "+ pizza.getType());
//            System.out.println("\tToppings:");
//            for (Topping topping : pizza.getToppings()) {
//                System.out.println("\t\t+"+ topping);
//            }
//        }
    }

    public static void prompt() {
        System.out.println("Enter an option and press enter\n" +
                "\t1 to add random orders\n" +
                "\t2 to remove order by id\n" +
                "\t3 to get order status\n" +
                "\t4 to set order status\n" +
                "\t5 to get pizza records by order id\n" +
                "\t6 to exit application\n");
    }

    public static void generateOrders(OrderApi api) throws IOException {
        Random randomType = new Random();
        Random randomTopping = new Random();
        for (int i = 1; i <= 10; i++) {
            String orderId = String.format("%04d", i);
            PizzaType pizzaType = PizzaType.values()[randomType.nextInt(PizzaType.values().length)];
            List<Topping> pizzaToppings = List.of(Topping.values()[randomTopping.nextInt(Topping.values().length)]);
            PizzaRecord pizzaRecord = new PizzaRecord(orderId, pizzaType, pizzaToppings);
            api.addOrder(orderId, List.of(pizzaRecord));
        }
    }

    public static String getId(Scanner scanner) {
        return scanner.nextLine();
    }

    public static void removeOrderById(OrderApi api, Scanner scanner) throws IOException {
        System.out.println("Enter order ID to remove: ");
        api.removeOrder(getId(scanner));
    }

    public static void getOrderStatus(OrderApi api, Scanner scanner) throws Exception {
        System.out.println("Enter order ID to get status: ");
        System.out.println("Status: "+api.getOrderStatus(getId(scanner)));
    }

    public static void setOrderStatus(OrderApi api, Scanner scanner) throws IOException {
        System.out.println("Enter order ID to set status: ");
        String orderId = getId(scanner);
        System.out.println("Enter order status to set: ");
        String orderStatus = getId(scanner);
        api.setOrderStatus(orderId, OrderStatus.valueOf(orderStatus));
    }

    public static void getPizzaRecords(OrderApi api, Scanner scanner) {
        String id = getId(scanner);
    }
}
