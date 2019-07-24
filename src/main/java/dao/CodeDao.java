package dao;

import model.Code;
import model.User;

import java.util.Optional;

public interface CodeDao {

    void add(Code code);

    Optional<Code> getLastCodeForUser(User user);

}
