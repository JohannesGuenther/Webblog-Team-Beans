package de.awacademy.team_beans.kommentar;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KommentarRepository extends JpaRepository <Kommentar, Long> {

    /**
     * Findet alle Kommentare und sortiert sie aufsteigend nach Erstellungsdatum (mit ältestem zuerst)
     * @return Liste der sortierten Kommentare
     */
    List<Kommentar> findAllByOrderByErstellungsDatum();
}
