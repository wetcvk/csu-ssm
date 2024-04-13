package org.csu.mypetstore.service;

import jakarta.servlet.http.HttpSession;
import org.csu.mypetstore.vo.AccountVO;

public interface AccountService {

    public AccountVO getAccount(String username);

    public AccountVO getAccount(String username,String password);

    void insertAccount(AccountVO accountVO);

    void editAccount(AccountVO accountVO);
}
