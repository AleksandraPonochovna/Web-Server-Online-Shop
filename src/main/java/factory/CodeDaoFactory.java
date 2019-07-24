package factory;

import dao.CodeDao;
import dao.impl.CodeDaoImpl;

public class CodeDaoFactory {

    private static CodeDao instance;

    private CodeDaoFactory() {
    }

    public static CodeDao getCodeDao() {
        if (instance == null) {
            instance = new CodeDaoImpl();
        }
        return instance;
    }

}
