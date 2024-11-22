package com.ycyw.chatbot.service.user;

import com.ycyw.chatbot.dto.DBUserDTO;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface IDBUserService {
    DBUserDTO login(final DBUserDTO user) throws UsernameNotFoundException;
}
