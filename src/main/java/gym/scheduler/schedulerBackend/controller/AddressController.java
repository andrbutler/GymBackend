/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.scheduler.schedulerBackend.controller;

import gym.scheduler.schedulerBackend.model.entity.Address;
import gym.scheduler.schedulerBackend.model.entity.User;
import gym.scheduler.schedulerBackend.model.repository.AddressRepository;
import gym.scheduler.schedulerBackend.model.repository.UserRepository;
import gym.scheduler.schedulerBackend.payload.AddressRequest;
import gym.scheduler.schedulerBackend.payload.ApiResponse;
import gym.scheduler.schedulerBackend.security.CurrentUser;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author andrb
 */
@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public List<Address> retrieveAllAddresses() {
        return addressRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @GetMapping("/{id}")
    public Address retrieveById(@PathVariable(name = "id") int id) {
        return addressRepository.findByAddressId(id);
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping("/update")
    public ResponseEntity<?> updateAddress(@RequestBody AddressRequest request) {
        int id = userRepository.findByUserId(request.getUserId()).getAddressId();
        Address a = addressRepository.findByAddressId(id);;
        a.setAddress1(request.getAddress1());
        a.setAddress2(request.getAddress2());
        a.setCityName(request.getCityName());
        a.setStateName(request.getStateName());
        a.setCountryName(request.getCountryName());
        a.setPostCode(request.getPostCode());
        Address result = addressRepository.save(a);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{addressId}")
                .buildAndExpand(result.getAddressId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "address updated successfully"));
    }
}
