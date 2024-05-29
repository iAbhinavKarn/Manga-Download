package com.abhinav.mangadownloader.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAddRequest {

    private String username;

    private String password;

    private String email;

    private String phone;

    private String telegramId;

    private String whatsAppId;

    private String firstName;

    private String lastName;
}
