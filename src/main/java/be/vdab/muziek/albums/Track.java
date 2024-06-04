package be.vdab.muziek.albums;

import jakarta.persistence.Embeddable;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Objects;

@Embeddable
class Track {
    private String naam;
    private LocalTime tijd;


    @Override
    public boolean equals(Object object) {
       return object instanceof Track track && track.naam.equalsIgnoreCase(naam);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(naam.toLowerCase());
    }

    public String getNaam() {
        return naam;
    }

    public LocalTime getTijd() {
        return tijd;
    }
}
