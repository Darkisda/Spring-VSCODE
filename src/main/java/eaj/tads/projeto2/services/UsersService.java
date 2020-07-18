package eaj.tads.projeto2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eaj.tads.projeto2.models.Users;
import eaj.tads.projeto2.repository.UsersRepository;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public void save(Users users) {
        usersRepository.save(users);
    }

    public Users get(Long id) {
        return usersRepository.getOne(id);
    }

    public void delete(Long id) {
        usersRepository.deleteById(id);
    }

    public Long count() {
        return usersRepository.count();
    }

    public boolean exist(Long id) {
        return usersRepository.existsById(id);
    }

    public Users login(String email, String password) {
        return usersRepository.findByEmailAndPassword(email, password);
    }
}