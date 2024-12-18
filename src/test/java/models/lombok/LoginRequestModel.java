package models.lombok;

import lombok.Data;

@Data
public class LoginRequestModel {
    String email, password;
}