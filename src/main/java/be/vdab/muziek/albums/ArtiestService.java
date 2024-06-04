package be.vdab.muziek.albums;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ArtiestService {
    private final ArtietstRepository artietstRepository;

    public ArtiestService(ArtietstRepository artietstRepository) {
        this.artietstRepository = artietstRepository;
    }
    Optional<Artiest> findById(long id) {
        return artietstRepository.findById(id);
    }
}
