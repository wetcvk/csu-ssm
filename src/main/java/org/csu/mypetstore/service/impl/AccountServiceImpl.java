package org.csu.mypetstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpSession;
import org.csu.mypetstore.entity.Account;
import org.csu.mypetstore.entity.Profile;
import org.csu.mypetstore.entity.Signon;
import org.csu.mypetstore.persistence.AccountMapper;
import org.csu.mypetstore.persistence.BannerDataMapper;
import org.csu.mypetstore.persistence.ProfileMapper;
import org.csu.mypetstore.persistence.SignonMapper;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.vo.AccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private SignonMapper signonMapper;

    @Autowired
    private ProfileMapper profileMapper;

    @Autowired
    private BannerDataMapper bannerDataMapper;

    @Override
    public AccountVO getAccount(String username) {
        AccountVO accountVO = new AccountVO();
        Account account = accountMapper.selectById(username);
        Signon signon = signonMapper.selectById(username);
        Profile profile = profileMapper.selectById(username);

        accountVO.setUsername(username);
        accountVO.setPassword(signon.getPassword());
        accountVO.setEmail(account.getEmail());
        accountVO.setFirstName(account.getFirstName());
        accountVO.setLastName(account.getLastName());
        accountVO.setStatus(accountVO.getStatus());
        accountVO.setAddress1(account.getAddress1());
        accountVO.setAddress2(account.getAddress2());
        accountVO.setCity(account.getCity());
        accountVO.setState(account.getState());
        accountVO.setZip(account.getZip());
        accountVO.setCountry(account.getCountry());
        accountVO.setPhone(account.getPhone());
        accountVO.setLanguagePreference(profile.getLanguage());
        accountVO.setFavouriteCategoryId(profile.getFav());
        accountVO.setListOption(profile.isListOption());
        accountVO.setBannerOption(profile.isBannerOption());

        return accountVO;
    }

    @Override
    public AccountVO getAccount(String username, String password) {
        AccountVO accountVO = new AccountVO();
        Account account = null;
//        Signon signon = signonMapper.selectById(username);

        Profile profile = profileMapper.selectById(username);

        QueryWrapper<Signon> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username).eq("password",password);
        List<Signon> signonList = signonMapper.selectList(queryWrapper);

        if(signonList!=null && !signonList.isEmpty()){
            account = accountMapper.selectById(username);
            accountVO.setPassword(signonList.get(0).getPassword());
            accountVO.setUsername(username);
            accountVO.setEmail(account.getEmail());
            accountVO.setFirstName(account.getFirstName());
            accountVO.setLastName(account.getLastName());
            accountVO.setStatus(accountVO.getStatus());
            accountVO.setAddress1(account.getAddress1());
            accountVO.setAddress2(account.getAddress2());
            accountVO.setCity(account.getCity());
            accountVO.setState(account.getState());
            accountVO.setZip(account.getZip());
            accountVO.setCountry(account.getCountry());
            accountVO.setPhone(account.getPhone());
            accountVO.setLanguagePreference(profile.getLanguage());
            accountVO.setFavouriteCategoryId(profile.getFav());
            accountVO.setListOption(profile.isListOption());
            accountVO.setBannerOption(profile.isBannerOption());
        }else{
            return null;
        }

//        accountVO.setEmail(account.getEmail());
//        accountVO.setFirstName(account.getFirstName());
//        accountVO.setLastName(account.getLastName());
//        accountVO.setStatus(accountVO.getStatus());
//        accountVO.setAddress1(account.getAddress1());
//        accountVO.setAddress2(account.getAddress2());
//        accountVO.setCity(account.getCity());
//        accountVO.setState(account.getState());
//        accountVO.setZip(account.getZip());
//        accountVO.setCountry(account.getCountry());
//        accountVO.setPhone(account.getPhone());

        return accountVO;
    }

    @Override
    public void insertAccount(AccountVO accountVO) {
        Account account=new Account();
        account.setUsername(accountVO.getUsername());
        account.setEmail(accountVO.getEmail());
        account.setFirstName(accountVO.getFirstName());
        account.setLastName(accountVO.getLastName());
        account.setAddress1(accountVO.getAddress1());
        account.setAddress2(accountVO.getAddress2());
        account.setStatus(accountVO.getStatus());
        account.setCity(accountVO.getCity());
        account.setState(accountVO.getState());
        account.setZip(accountVO.getZip());
        account.setCountry(accountVO.getCountry());
        account.setPhone(accountVO.getPhone());
        accountMapper.insert(account);

        Signon signon = new Signon();
        signon.setUsername(accountVO.getUsername());
        signon.setPassword(accountVO.getPassword());
        signonMapper.insert(signon);

        Profile profile = new Profile();
        profile.setUsername(accountVO.getUsername());
        profile.setLanguage(accountVO.getLanguagePreference());
        profile.setFav(accountVO.getFavouriteCategoryId());
        profile.setListOption(accountVO.isListOption());
        profile.setBannerOption(accountVO.isBannerOption());
        profileMapper.insert(profile);
    }

    @Override
    public void editAccount(AccountVO accountVO) {
        Account account=new Account();
        account.setUsername(accountVO.getUsername());
        account.setEmail(accountVO.getEmail());
        account.setFirstName(accountVO.getFirstName());
        account.setLastName(accountVO.getLastName());
        account.setAddress1(accountVO.getAddress1());
        account.setAddress2(accountVO.getAddress2());
        account.setStatus(accountVO.getStatus());
        account.setCity(accountVO.getCity());
        account.setState(accountVO.getState());
        account.setZip(accountVO.getZip());
        account.setCountry(accountVO.getCountry());
        account.setPhone(accountVO.getPhone());
        accountMapper.updateById(account);

        Signon signon = new Signon();
        signon.setUsername(accountVO.getUsername());
        signon.setPassword(accountVO.getPassword());
        signonMapper.updateById(signon);

        Profile profile = new Profile();
        profile.setUsername(accountVO.getUsername());
        profile.setLanguage(accountVO.getLanguagePreference());
        profile.setFav(accountVO.getFavouriteCategoryId());
        profile.setListOption(accountVO.isListOption());
        profile.setBannerOption(accountVO.isBannerOption());
        profileMapper.updateById(profile);
    }
}
