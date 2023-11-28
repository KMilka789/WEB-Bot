package Karpova.model;

import lombok.Builder;
import lombok.Data;

// Класс, представляющий информацию о заказах.
@Data
@Builder
public class Order {
    private String orderId;
    private String origClOrdID;
    private Symbol symbol;
    private OrderType orderType;
    private boolean isBuy;
    private Double orderQty;
    private Double price;

    private Double stopPx;

    private OrderStatus orderStatus;
}
