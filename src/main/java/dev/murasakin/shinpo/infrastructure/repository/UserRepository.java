package dev.murasakin.shinpo.infrastructure.repository;

import dev.murasakin.shinpo.infrastructure.table.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserTable, Long> {
    UserTable findByUsername(String username);
    void deleteByUsername(String username);
}
