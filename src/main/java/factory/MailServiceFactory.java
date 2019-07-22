package factory;

import service.MailService;
import service.impl.MailServiceImpl;

public class MailServiceFactory {

    private static MailService instance;

    private MailServiceFactory() {
    }

    public static MailService getMailService() {
        if (instance == null) {
            instance = new MailServiceImpl();
        }
        return instance;
    }
}
