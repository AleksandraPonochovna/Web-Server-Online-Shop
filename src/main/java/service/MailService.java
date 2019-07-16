package service;

import model.Code;

public interface MailService {

    void sendOneTimeCode(Code code);

}
