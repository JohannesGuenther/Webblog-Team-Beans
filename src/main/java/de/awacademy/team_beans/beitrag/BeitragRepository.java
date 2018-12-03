package de.awacademy.team_beans.beitrag;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeitragRepository extends JpaRepository<Beitrag, Long> {

    List<Beitrag> findAllByOrderByCreationDateDesc();

    //einen spez. Beitrag aus der DB holen, BeitragsID übergeben
}
