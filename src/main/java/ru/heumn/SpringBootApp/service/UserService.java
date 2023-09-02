package ru.heumn.SpringBootApp.service;

import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.heumn.SpringBootApp.domain.RegisUser;
import ru.heumn.SpringBootApp.domain.User;
import ru.heumn.SpringBootApp.repos.RegisUserRepo;
import ru.heumn.SpringBootApp.repos.UserRepo;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private MailSender mailSender;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RegisUserRepo regisUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public Boolean addUser(RegisUser regisUser){
        RegisUser userFromDb = regisUserRepo.findByUsername(regisUser.getUsername());

        if(userFromDb != null)
        {
            return false;
        }
        regisUser.setActivationCode(UUID.randomUUID().toString());

        regisUserRepo.save(regisUser);

        if(!StringUtils.isEmptyOrWhitespaceOnly(regisUser.getEmail()))
        {
            String message = String.format(
                    "Your Code Activation: http://localhost:8080/activate/%s",
                    regisUser.getActivationCode()
            );

            mailSender.send(regisUser.getEmail(), "Activation Code", message);
        }
        return true;
    }

    public boolean activateUser(String code) {

        RegisUser regisUser = regisUserRepo.findByActivationCode(code);

        if(regisUser == null) {
            return false;
        }

        User user = new User();
        user.setPassword(regisUser.getPassword());
        user.setUsername(regisUser.getUsername());
        user.setActive(true);

        userRepo.save(user);

        return true;
    }
}
