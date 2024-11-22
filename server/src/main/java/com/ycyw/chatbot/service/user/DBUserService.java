package com.ycyw.chatbot.service.user;

import com.ycyw.chatbot.dto.DBUserDTO;
import com.ycyw.chatbot.jwt.JWTService;
import com.ycyw.chatbot.model.DBUser;
import com.ycyw.chatbot.repository.DBUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DBUserService implements IDBUserService {

    @Autowired
    private DBUserRepository dbUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private ModelMapper modelMapper;

    public DBUserDTO login(final DBUserDTO user) throws UsernameNotFoundException {
        Optional<DBUser> dbUser = dbUserRepository.findByEmail(user.getEmail());
        if(dbUser.isPresent()) {
            String password = user.getPassword();
            if(!passwordEncoder.matches(password, dbUser.get().getPassword())){
                throw new BadCredentialsException("Mot de passe incorrect");
            }
            String token = jwtService.generateToken(dbUser.get().getEmail());
            DBUserDTO loggedUser = modelMapper.map(dbUser, DBUserDTO.class);
            loggedUser.setToken(token);
            return loggedUser;
        }
        else{
            throw new UsernameNotFoundException("Utilisateur introuvable");
        }
    }
}
