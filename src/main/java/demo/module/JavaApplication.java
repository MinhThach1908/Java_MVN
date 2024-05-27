package demo.module;

import demo.module.user.User;
import demo.module.user.UserRepository;

import java.time.LocalDate;

public class JavaApplication {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();

        User user = new User();
        user.setUsername("quanghuy");
        user.setPassword("123");
        user.setFullName("Quang Huy");
        user.setIdentityCard("012312312");
        user.setPhone("091321");
        user.setDob(LocalDate.now());
        user.setStatus(1);

        userRepository.save2(user);
        userRepository.findAll();
    }
}
