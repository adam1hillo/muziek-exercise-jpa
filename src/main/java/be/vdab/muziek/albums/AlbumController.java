package be.vdab.muziek.albums;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Stream;

@RestController
@RequestMapping("albums")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    Stream<AlbumBeknoptMetArtiestNaam> findAll() {
        return albumService.findAll()
                .stream()
                .map(AlbumBeknoptMetArtiestNaam::new);
    }
    @GetMapping("{id}")
    Detail findById(@PathVariable long id) {
        return albumService.findById(id)
                .map(Detail::new)
                .orElseThrow(AlbumNietGevondenException::new);
    }
    @GetMapping(params = "jaar")
    Stream<AlbumBeknoptMetArtiestNaam> findByJaar(int jaar) {
        return albumService.findByJaar(jaar)
                .stream()
                .map(AlbumBeknoptMetArtiestNaam::new);
    }
    @PatchMapping("{id}/score")
    void wijzigScore(@PathVariable long id,@RequestBody @Min(0) @Max(10) int score) {
        albumService.wijzigScore(id, score);
    }

    record AlbumBeknoptMetArtiestNaam(String naam, String artiestNaam, int jaar) {
        AlbumBeknoptMetArtiestNaam(Album album) {
            this(album.getNaam(), album.getArtiest().getNaam(), album.getJaar());
        }
    }
    record Detail(String naam, String artiestNaam, int jaar, String labelNaam, LocalTime tijd, Set<Track> tracks) {
        Detail(Album album) {
            this(album.getNaam(), album.getArtiest().getNaam(), album.getJaar(), album.getLabel().getNaam(),album.getTijd(), album.getTracks());
        }
    }
}
