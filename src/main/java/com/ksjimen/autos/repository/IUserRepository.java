package com.ksjimen.autos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksjimen.autos.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUsername(String username);
}
