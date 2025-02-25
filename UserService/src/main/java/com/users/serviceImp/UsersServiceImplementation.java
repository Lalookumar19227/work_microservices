package com.users.serviceImp;

import com.users.entities.Users;
import com.users.UserRepo.UserRepo;
import com.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsersServiceImplementation implements UserService {

    @Override
    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }
//    @Autowired
//    private RestTemplate restTemplate;


    @Autowired
    private UserRepo userRepo;



    @Override
    public Users createUser(Users user) {
        String password = user.getPassword();
        String encode = new BCryptPasswordEncoder().encode(password);
        user.setPassword(encode);
        return userRepo.save(user);
    }



//    @Override
//    public Users getUsersByEmail(String email) {
//      return userRepo.getUsersByEmail(email);
//    }
//
//    @Override
//    public void deleteUsersById(Long id) {
//        userRepo.deleteById(id);
//    }
//
//    @Override
//    public String updateUsers(Long id, Users user) {
//        Optional<Users> byId = userRepo.findById(id);
//        if(byId.isPresent()){
//            Users user1 = byId.get();
//            user1.setEmail(user.getEmail());
//            user1.setPhone(user.getPhone());
//            user1.setFirstName(user.getFirstName());
//            user1.setLastName(user.getLastName());
//            user1.setPassword(user.getPassword());
//            userRepo.save(user1);
//        }
//        return "updated";
//    }
//
//    public Optional<Users> getUsersById(Long id) {
//        Optional<Users> byId = userRepo.findById(id);
//        if (byId.isPresent()) {
//            Users user = byId.get();
//            try {
//                String url = "http://FullFledgedOrder/orders/getUsersOrders/" + user.getId();
//                CustomerOrder[] customerOrdersArray = restTemplate.getForObject(url, CustomerOrder[].class);
//                List<CustomerOrder> customerOrders = Arrays.asList(customerOrdersArray);
//                user.setCustomerOrder(customerOrders);
//                String url2 = "http://FullFledgedProductPart/products/getUsersProducts/" + user.getId();
//                Product[] products = restTemplate.getForObject(url2,Product[].class);
//                List<Product> productsList = Arrays.asList(products);
//                user.setProducts(productsList);
//                return Optional.of(user);
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        return Optional.empty();
//    }
}
