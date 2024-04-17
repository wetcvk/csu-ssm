package org.csu.mypetstore.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.csu.mypetstore.vo.ItemVO;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.vo.AccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("getCart")
    public String getCartView(HttpSession session,Model model ){
        AccountVO account = (AccountVO)session.getAttribute("account");
        if(account!=null){
            ArrayList<org.csu.mypetstore.vo.ItemVO> itemlist =(ArrayList<org.csu.mypetstore.vo.ItemVO>) session.getAttribute("ItemList");
            if(itemlist==null){
                itemlist= new ArrayList<>();
                session.setAttribute("ItemList",itemlist);
            }else{
                session.setAttribute("ItemList",itemlist);
            }
            model.addAttribute("itemList",itemlist);
            double prices=0;
            for(org.csu.mypetstore.vo.ItemVO item:itemlist){
                double price =item.getListPrice().doubleValue();
                prices+=price;
            }
            session.setAttribute("totalnumber",prices);
        }
        if(account!=null)
            System.out.println("拿到了account");
        return "/cart/cart";
    }

    @GetMapping("cart")
    public String CartForm(@RequestParam("itemId")String itemId, HttpSession session, Model model){
        ArrayList<ItemVO> itemlist =(ArrayList<ItemVO>) session.getAttribute("ItemList");
        if(itemlist==null){
            itemlist= new ArrayList<>();
            org.csu.mypetstore.vo.ItemVO item =catalogService.getItem(itemId);
            item.setQuantity(1);
            itemlist.add(item);
            session.setAttribute("ItemList",itemlist);
        }else{
            org.csu.mypetstore.vo.ItemVO itemVO = catalogService.getItem(itemId);
            itemVO.setQuantity(1);
            itemlist.add(itemVO);
            session.setAttribute("ItemList",itemlist);
        }
        model.addAttribute("itemList",itemlist);
        double prices=0;
        for(ItemVO itemVO:itemlist){
            double price =itemVO.getListPrice().doubleValue();
            prices+=price;
        }
        session.setAttribute("totalnumber",prices);
        return "/cart/cart";
    }

    @PostMapping("cartData")
    public String getData(){
        //发送表单进行更新购物车数据
        return "/cart/cart";
    }

    @GetMapping("cartDataRemove")
    public void removeData(@RequestParam("removeId") String removeId, HttpSession session, HttpServletResponse response) {
        //在session中找到对应item，将其删除，重新覆盖
        try {
            PrintWriter pw = response.getWriter();
            response.setCharacterEncoding("UTF-8");
            String json = null;
            try {//没有异常则返回
                double prices = 0;
                List ItemList = (ArrayList) session.getAttribute("ItemList");
                for (int i = 0; i < ItemList.size(); i++) {
                    ItemVO item = (ItemVO) ItemList.get(i);
                    if (item.getItemId().equals(removeId)) {
                        ItemList.remove(item);
                    }else {
                        prices += item.getListPrice().doubleValue()*item.getQuantity();
                    }
                }
                session.setAttribute("ItemList", ItemList);
                session.setAttribute("totalnumber",prices);
                json ="{'RemoveResult':'true','totalnumber':"+prices+"}";
            } catch (Exception ex) {
                json = "{'RemoveResult':'false'}";
            }
            pw.print(json);
            pw.flush();
            pw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @GetMapping("cartDataChangeNumber")
    public void ChangeItemNumber(@RequestParam("ChangeNumberItemId") String ChangeNumberItemId,
                                 @RequestParam("Number") String Number,
                                 HttpSession session,HttpServletResponse response){
        try {
            PrintWriter pw = response.getWriter();
            response.setCharacterEncoding("UTF-8");
            String json = null;
            try {//没有异常则返回
                double prices = 0;
                double listPrice = 0;
                List ItemList = (ArrayList) session.getAttribute("ItemList");
                for (int i = 0;i<ItemList.size();i++) {
                    ItemVO item = (ItemVO) ItemList.get(i);
                    if (item.getItemId().equals(ChangeNumberItemId)) {
                        item.setQuantity(Integer.valueOf(Number.trim()));//改变数目
                        BigDecimal intBig = new BigDecimal(item.getQuantity() * item.getListPrice().doubleValue());
                        listPrice = intBig.doubleValue();
                    }
                    prices += (item.getListPrice().doubleValue() * item.getQuantity());
                }
                session.setAttribute("ItemList", ItemList);
                json ="{'ChangeNumberResult':'success','totalnumber':"+prices+",'listPrice':"+listPrice+"}";
            } catch (Exception ex) {
                json ="{'ChangeNumberResult':'fail'}";
                ex.printStackTrace();
            }
            pw.print(json);
            pw.flush();
            pw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
