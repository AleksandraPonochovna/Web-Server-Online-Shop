package factory;

import service.CodeService;
import service.impl.CodeServiceImpl;

public class CodeServiceFactory {

    private static CodeService instance;

    private CodeServiceFactory() {
    }

    public static CodeService getCodeService() {
        if (instance == null) {
            instance = new CodeServiceImpl();
        }
        return instance;
    }

}
