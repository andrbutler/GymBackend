/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.scheduler.schedulerBackend.util;
 import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 *
 * @author andrb
 */
public class PasswordEncoder {
 
    public String generatePassword(String raw) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(raw);
         
        return encodedPassword;
    }
 
}

