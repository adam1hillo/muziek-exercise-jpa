package be.vdab.muziek.albums;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    private long id;
    private String naam;
    private int jaar;
    private long barcode;
    private int score;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "artiestId")
    private Artiest artiest;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "labelId")
    private Label label;
    @ElementCollection
    @CollectionTable(name = "tracks",
    joinColumns = @JoinColumn(name = "albumId"))
    @OrderBy("naam")
    private Set<Track> tracks = new LinkedHashSet<>();

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public int getJaar() {
        return jaar;
    }

    public long getBarcode() {
        return barcode;
    }

    public int getScore() {
        return score;
    }

    public Artiest getArtiest() {
        return artiest;
    }

    public Label getLabel() {
        return label;
    }

    public Set<Track> getTracks() {
        return Collections.unmodifiableSet(tracks);
    }

    public LocalTime getTijd() {
        return tracks.stream()
                .map(Track::getTijd)
                .reduce(LocalTime.MIN, (albumTijd, trackTijd) -> albumTijd.plusHours(trackTijd.getHour()).plusMinutes(trackTijd.getMinute()).plusSeconds(trackTijd.getSecond()));
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Album album && album.barcode==barcode;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(barcode);
    }
}
