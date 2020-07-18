package eaj.tads.projeto2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eaj.tads.projeto2.models.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{
    
    List<Users> findByEmail(String email);
    Users findByEmailAndPassword(String email, String password);

}