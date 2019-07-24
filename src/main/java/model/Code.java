package model;

public class Code {

    private Long id;
    private String code;
    private Long idUser;

    public Code(Long id, String code, Long idUser) {
        this.id = id;
        this.code = code;
        this.idUser = idUser;
    }

    public Code(String code, Long idUser) {
        this.code = code;
        this.idUser = idUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Code(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
