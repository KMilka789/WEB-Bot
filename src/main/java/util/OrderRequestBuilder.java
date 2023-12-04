package util;

import com.google.gson.Gson;
import model.Order;
import model.OrderRequest;
import model.OrderToRequest;

// улитарный класс выполняющий преобразование обьекта Order в Json строку
public class OrderRequestBuilder {
    public static final Gson gson = new Gson();

    public static String buildOrderRequest(Order order) {
        if (order == null) {
            return "";
        } else {
            OrderRequest orderRequest = OrderToRequest.toRequest(order);
            return gson.toJson(orderRequest);
        }
    }
}
