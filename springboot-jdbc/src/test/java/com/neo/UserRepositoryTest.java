package com.neo;

import com.example.JdbcApplication;
import com.example.model.User;
import com.example.respository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JdbcApplication.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate primaryJdbcTemplate;


    @Autowired
    private JdbcTemplate secondaryJdbcTemplate;


    @Test
    public void testSave(){

        User user = new User("denzel","123456",28);
        userRepository.save(user,primaryJdbcTemplate);
//        User user2 = new User("bart","777888",25);
//        userRepository.save(user2,secondaryJdbcTemplate);

    }

    @Test
    public void testUpdate(){
        User user = new User("jacky","666666",27);
        user.setId(2);
        userRepository.update(user,primaryJdbcTemplate);
    }

    @Test
    public void testDelete(){
        userRepository.delete(2,primaryJdbcTemplate);
    }


    @Test
    public void testFindAll(){
        List<User> list = userRepository.findAll(primaryJdbcTemplate);
        for (User u:list
             ) {
            System.out.println("*******user : "+u.toString());
        }
    }

    @Test
    public void testFindById(){
       User u =  userRepository.findById(1,primaryJdbcTemplate);
        System.out.println("*********user : "+u.toString());
    }

}
