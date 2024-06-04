package be.vdab.muziek.albums;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    List<Album> findAll() {
        return albumRepository.findAll(Sort.by("naam"));
    }
    Optional<Album> findById(long id) {
        return albumRepository.findById(id);
    }
    List<Album> findByJaar(int jaar) {
        return albumRepository.findByJaarOrderByNaam(jaar);
    }
    @Transactional(readOnly = false)
    void wijzigScore(long id, int score) {
        Album album = albumRepository.findByIdAndLock(id)
                .orElseThrow(AlbumNietGevondenException::new);
        album.setScore(score);
    }
}
