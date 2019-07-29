package factory.hibernate;

import dao.CodeDao;
import dao.impl.hibernate.CodeDaoHibernateImpl;

public class CodeDaoHibernateFactory {

    private static CodeDao instance;

    private CodeDaoHibernateFactory() {
    }

    public static CodeDao getCodeDao() {
        if (instance == null) {
            instance = new CodeDaoHibernateImpl();
        }
        return instance;
    }

}
