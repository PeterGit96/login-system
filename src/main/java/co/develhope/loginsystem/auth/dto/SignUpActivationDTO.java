package co.develhope.loginsystem.auth.dto;

public class SignUpActivationDTO {

    private String activationCode;

    public SignUpActivationDTO() { }

    public SignUpActivationDTO(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getActivationCode() {
        return activationCode;
    }

}
