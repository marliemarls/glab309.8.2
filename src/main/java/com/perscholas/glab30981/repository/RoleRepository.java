package com.perscholas.glab30981.repository;
import com.perscholas.glab30981.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>
{

    Role findByName(String name);
}

