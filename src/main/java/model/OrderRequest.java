package model;

import lombok.RequiredArgsConstructor;

// переделать убрать статику
@RequiredArgsConstructor
public class OrderRequest {
    // создавать этот ордер request на основе тех полей которые даны в апи
    // отсюда будут парситься данные в формате гсон
    private final String symbol;
    private final String side;
    private final Double orderQty;
    private final Double price;
    private final String ordType;
    private final Double stopPx;

    // в отдельный утилитный класс
//    public static OrderRequest toRequest(Order order) {
//        String symbol = order.getSymbol().toString();
//        String side = order.isBuy() ? "Buy" : "Sell";
//        Double orderQty = order.getOrderQty();
//        Double price = order.getPrice();
//        String ordType = getType(order.getOrderType());
//        Double stopPx = order.getStopPx();
//        return new OrderRequest(symbol, side, orderQty, price, ordType, stopPx);
//    }
//
//    private static String getType(OrderType orderType) {
//        switch (orderType) {
//            case LIMIT:
//                return "Limit";
//            case MARKET:
//                return "Market";
//            case STOP_LIMIT:
//                return "StopLimit";
//            case STOP_MARKET:
//                return "Stop";
//            default:
//                throw new IllegalStateException("Unsupported orderType");
//
//        }

    }

