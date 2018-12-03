package de.awacademy.team_beans.version;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VersionsRepository extends JpaRepository<Version, Long> {

    List<Version> findAllByOrderByAenderungsDatumDesc();

}
