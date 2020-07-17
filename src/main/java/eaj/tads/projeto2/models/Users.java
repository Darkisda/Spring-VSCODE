package eaj.tads.projeto2.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Users {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String nome;

    String email;

    String password;
}