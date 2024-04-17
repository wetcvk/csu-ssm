package org.csu.mypetstore.service;

import org.csu.mypetstore.entity.Order;
import org.csu.mypetstore.entity.Sequence;
import org.csu.mypetstore.vo.LineItemVO;
import org.csu.mypetstore.vo.OrderVO;
import org.csu.mypetstore.vo.SequenceVO;

import java.util.List;

public interface OrderService {

    public List<OrderVO> getOrdersByUsername(String username);

    public Order getOrder(int orderId);

    public void insertOrder(Order order);

    public void insertOrderStatus(Order order);

    public Sequence getSequence(String name);

    public void updateSequence(String name);

    public List<LineItemVO> getLineItemsByOrderId(int orderId);

}
