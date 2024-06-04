package be.vdab.muziek.albums;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("artiesten")
public class ArtiestController {
    private final ArtiestService artiestService;

    public ArtiestController(ArtiestService artiestService) {
        this.artiestService = artiestService;
    }
    @GetMapping("{id}/albums")
    Stream<AlbumBeknopt> findAlbumsVanArtiest(@PathVariable long id) {
        return artiestService.findById(id)
                .orElseThrow(ArtiestNietGevondenException::new)
                .getAlbums()
                .stream()
                .map(AlbumBeknopt::new);
    }

    record AlbumBeknopt(String naam, int jaar) {
        AlbumBeknopt(Album album) {
            this(album.getNaam(), album.getJaar());
        }
    }
}
