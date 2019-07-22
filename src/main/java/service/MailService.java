package service;

import model.Order;

public interface MailService {

    void sendOneTimeCode(Order order);

}
