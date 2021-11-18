package com.alex.serurity.security;// package com.alex.serurity.security;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Component;
//
// @Component
// public class DefaultPasswordEncoder implements PasswordEncoder {
//
//     @Autowired
//     private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//     public DefaultPasswordEncoder() {
//         this(-1);
//     }
//
//     public DefaultPasswordEncoder(int strength) {
//
//     }
//
//     @Override
//     public String encode(CharSequence rawPassword) {
//         return bCryptPasswordEncoder.encode(rawPassword);
//     }
//
//     @Override
//     public boolean matches(CharSequence rawPassword, String encodedPassword) {
//         return encodedPassword.equals(bCryptPasswordEncoder.encode(rawPassword));
//     }
// }