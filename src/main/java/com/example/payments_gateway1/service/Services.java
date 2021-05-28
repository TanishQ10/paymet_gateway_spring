package com.example.payments_gateway1.service;

import com.example.payments_gateway1.Enums.Roles;
import com.example.payments_gateway1.Enums.TxnStatus;
import com.example.payments_gateway1.entity.*;
import com.example.payments_gateway1.repository.LoginRepository;
import com.example.payments_gateway1.repository.MerchantRepository;
import com.example.payments_gateway1.repository.OrdersRepository;
import com.example.payments_gateway1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;



@SuppressWarnings("ALL")
@Service

public class Services {
    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    private int addLogin(Login login) {
        Login log1 = loginRepository.findByUsername(login.getUsername()).orElse(null);
        if (log1 != null) {
            return 0;
        }
        Login log = loginRepository.save(login);
        return log.getId();
    }

    @Transactional
    public String registerUser(CollectData collectData) {

        if (userRepository.findByUsername(collectData.getUsername()).orElse(null) != null) {
            return "Username is already taken!";
        }
        if (userRepository.findByEmail(collectData.getEmail()).orElse(null) != null) {
            return "Email is already registered!";
        }
        Login log = new Login();
        log.setUsername(collectData.getUsername());
        log.setRole(Roles.USER);
        log.setPassword(passwordEncoder.encode(collectData.getPassword()));
        int id = addLogin(log);
        if (id == 0) {
            return "Already Exists!";
        }
        UserData userData = new UserData();

        userData.setUser_id(id);
        userData.setUsername(collectData.getUsername());
        userData.setName(collectData.getName());
        userData.setEmail(collectData.getEmail());
        userData.setWallet_money(0);
        userData.setPAN(collectData.getPAN());
        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        userData.setCreated_on(date);
        userData.setUpdated_on(date);
        userData.set_active(false);
        userRepository.save(userData);
        return "User registered with id: " + userData.getUser_id() + " and username: " + userData.getUsername();
    }


    public String userSetActive(String username) {
        UserData userData = userRepository.findByUsername(username).orElse(null);
        if (userData == null) {
            return "Error, User does not exixt!";
        }
        userData.set_active(true);
        userRepository.save(userData);

        return "Activated user with username: " + username;
    }

    @Transactional
    public String registerMerchant(CollectData collectData) {
        if (merchantRepository.findByUsername(collectData.getUsername()).orElse(null) != null) {
            return "Username is already taken!";
        }
        if (merchantRepository.findByEmail(collectData.getEmail()).orElse(null) != null) {
            return "Email is already registered!";
        }
        Login log1 = new Login();
        log1.setUsername(collectData.getUsername());
        log1.setRole(Roles.MERCHANT);
        log1.setPassword(passwordEncoder.encode(collectData.getPassword()));
        int id = addLogin(log1);
        if (id == 0) {
            return "Already Exist!";
        }
        MerchantData merchantData = new MerchantData();
        merchantData.setEmail(collectData.getEmail());
        merchantData.setUser_id(id);
        merchantData.setName(collectData.getName());
        merchantData.setUsername(collectData.getUsername());
        merchantData.setGSTIN(collectData.getGSTIN());
        merchantData.setWallet_money(0);
        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        merchantData.setCreated_on(date);
        merchantData.setUpdated_on(date);
        merchantData.set_accepting(false);
        merchantRepository.save(merchantData);
        return "Registered Merchant with username: " + merchantData.getUsername() + " and id: " + merchantData.getUser_id();
    }

    public String merchantSetAccepting(String username) {
        MerchantData merchantData = merchantRepository.findByUsername(username).orElse(null);
        if (merchantData == null) {
            return "Error!";
        }
        merchantData.set_accepting(true);
        merchantRepository.save(merchantData);
        return "Ready to accept payment with username: " + username;
    }

    @Transactional
    public String addMoney(String username, float money) {
        UserData existing = userRepository.findByUsername(username).orElse(null);
        if (existing == null) {
            return "Not a valid user!";
        }
        if (!existing.is_active()) {
            return "User is not actived!";
        }
        existing.setPAN(existing.getPAN());
        existing.setEmail(existing.getEmail());
        existing.setName(existing.getName());
        existing.setUsername(existing.getUsername());
        existing.setWallet_money(existing.getWallet_money() + money);
        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        existing.setUpdated_on(date);

        userRepository.save(existing);
        return "Money added to use : " + existing.getUsername() + " total amount now is: " + existing.getWallet_money();
    }

    @Transactional
    public String initiateTxn(Orders order, Authentication authentication) {

        Login login = loginRepository.findByUsername(authentication.getName()).orElse(null);
        int userId = order.getUserId();
        int merchantId = order.getMerchantId();

        if(login.getRole()==Roles.MERCHANT){
            merchantId = login.getId();
        }
        else if(login.getRole()==Roles.USER){
            userId = login.getId();
        }

        UserData userData = userRepository.findById(userId).orElse(null);
        MerchantData merchantData = merchantRepository.findById(merchantId).orElse(null);

        if (userData == null || merchantData == null) {
            return "Invalid User or Merchant";
        }

        if (!userData.is_active()) {
            return "User is not active";
        }

        if (!merchantData.is_accepting()) {
            return "Merchant is not accepting payments";
        }

        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        order.setCreated_on(date);
        order.setUpdated_on(date);

        if (userData != null && (userData.getWallet_money() < order.getAmount())) {
            order.setStatus(TxnStatus.FAILED);
            Orders order1 = ordersRepository.save(order);
            return "Amount is not sufficient in user Wallet, Txn id : " + order1.getTxnId();
        }

        order.setStatus(TxnStatus.INITIATED);
        Orders order1 = ordersRepository.save(order);
        return "Order Initiated with Txn id " + order1.getTxnId();

    }

    public String getStatus(Integer id) {
        Orders order = ordersRepository.findById(id).orElse(null);
        if (order == null) {
            return null;
        }
        return order.getStatus().toString();

    }

}
