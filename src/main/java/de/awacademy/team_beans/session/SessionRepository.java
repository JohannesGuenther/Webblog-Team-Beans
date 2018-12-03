package de.awacademy.team_beans.session;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, String> {//unsere id ist hier keine long mehr, sondern ein String, weil wir eine UUID nutzen wollen
}
