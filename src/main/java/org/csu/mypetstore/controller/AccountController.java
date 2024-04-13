package org.csu.mypetstore.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.csu.mypetstore.entity.Account;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.vo.AccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("signon")//进入登录页面
    public String signon(){
        return "account/signon";
    }

    @RequestMapping("login")//点击登录
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model, HttpSession session){
        AccountVO accountVO = accountService.getAccount(username,password);

        if(accountVO!=null){
            session.setAttribute("account",accountVO);
            session.setAttribute("authenticated",true);
            return "catalog/main";
        }else{
            model.addAttribute("signOnMsg" , "用户名或密码错误");
            session.setAttribute("authenticated",false);
            return "account/signon";
        }
    }

    @RequestMapping("signoff")//登出
    public String signOff(HttpSession session){
        session.setAttribute("account",null);
        session.setAttribute("authenticated",false);
        return "catalog/main";
    }

    @RequestMapping("newAccountForm")//进入注册页面
    public String newAccountForm(Model model , HttpSession session){
        AccountVO accountVO = null;
        model.addAttribute("account",null);
        session.setAttribute("account",accountVO);
        return "account/newAccount";
    }

    @PostMapping("newAccount")//注册
    public String newAccount(HttpServletRequest req, Model model, HttpSession session){
        String msg=null;
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String address1 = req.getParameter("address1");
        String address2 = req.getParameter("address2");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String zip = req.getParameter("zip");
        String country = req.getParameter("country");
        String languagePreference = req.getParameter("languagePreference");
        String favouriteCategoryId = req.getParameter("favouriteCategoryId");
        String listOption = req.getParameter("listOption");
        String bannerOption = req.getParameter("bannerOption");

        String repeat=req.getParameter("repeatedPassword");

        if(username==null||password==null){
            msg="用户名或密码不能为空";
            model.addAttribute("message",msg);
        }else{
            model.addAttribute("message",msg);
        }
        AccountVO test=accountService.getAccount(username);
        if(test!=null){
            msg="该用户名已存在";
            model.addAttribute("message",msg);
            return "account/newAccount";
        }else if(!(repeat.equals(password))) {
            msg = "请正确地重复密码";
            model.addAttribute("message", msg);
            return "account/newAccount";
        }else{
            AccountVO account1=new AccountVO();
            account1.setUsername(username);
            account1.setPassword(password);
            account1.setFirstName(firstName);
            account1.setLastName(lastName);
            account1.setEmail(email);
            account1.setPhone(phone);
            account1.setAddress1(address1);
            account1.setAddress2(address2);
            account1.setCity(city);
            account1.setState(state);
            account1.setZip(zip);
            account1.setCountry(country);
            account1.setLanguagePreference(languagePreference);
            account1.setFavouriteCategoryId(favouriteCategoryId);
            account1.setListOption(Boolean.parseBoolean(listOption));
            account1.setBannerOption(Boolean.parseBoolean(bannerOption));

            accountService.insertAccount(account1);

            model.addAttribute("account",account1);
            session.setAttribute("account",account1);

            return "catalog/main";
        }
    }

    @RequestMapping("editAccountForm")//进入编辑页面
    public String editForm(HttpSession session,Model model){
        AccountVO account=(AccountVO) session.getAttribute("account");
        model.addAttribute("account",account);
        return "account/editAccountForm";
    }

    @PostMapping("editAccount")//编辑账号信息
    public String editAccount(HttpServletRequest req, Model model, HttpSession session){
        AccountVO account=(AccountVO) session.getAttribute("account");
        model.addAttribute("account",account);
        accountService.editAccount(account);
        return "catalog/main";
    }
}
