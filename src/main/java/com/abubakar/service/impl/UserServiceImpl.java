package com.abubakar.service.impl;

import org.springframework.stereotype.Service;

import com.abubakar.config.JwtProvider;
import com.abubakar.model.User;
import com.abubakar.repository.AddressRepository;
import com.abubakar.repository.UserRepository;
import com.abubakar.service.UserService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final AddressRepository addressRepository;



    @Override
		public User findUserByJwtToken(String jwt) throws Exception {

    // JwtProvider is responsible for stripping the "Bearer " prefix (if present)
    String email = jwtProvider.getEmailFromJwtToken(jwt);

    return findUserByEmail(email);
}




    @Override
    public User findUserByEmail(String email) throws Exception {


        User user =
                userRepository.findByEmail(email);


        if (user == null) {
            throw new Exception(
                "User Not found with email - " + email
            );
        }


        return user;
    }



    @Override
    public User addAddress(
            User user,
            com.abubakar.model.Address address) {


        com.abubakar.model.Address savedAddress =
                addressRepository.save(address);


        user.getAddresses().add(savedAddress);


        return userRepository.save(user);
    }

}