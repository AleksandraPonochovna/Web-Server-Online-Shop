package service;

import model.Code;
import model.User;

import java.util.Optional;

public interface CodeService {

    void add(Code code);

    Optional<Code> getLastCodeForUser(User user);

}
