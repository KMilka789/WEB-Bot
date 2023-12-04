package model;

public class OrderToRequest {
    public static OrderRequest toRequest(Order order) {
        String symbol = order.getSymbol().toString();
        String side = order.isBuy() ? "Buy" : "Sell";
        Double orderQty = order.getOrderQty();
        Double price = order.getPrice();
        String ordType = getType(order.getOrderType());
        Double stopPx = order.getStopPx();
        return new OrderRequest(symbol, side, orderQty, price, ordType, stopPx);
    }

    private static String getType(OrderType orderType) {
        switch (orderType) {
            case LIMIT:
                return "Limit";
            case MARKET:
                return "Market";
            case STOP_LIMIT:
                return "StopLimit";
            case STOP_MARKET:
                return "Stop";
            default:
                throw new IllegalArgumentException("Unsupported orderType");
        }
    }


}
