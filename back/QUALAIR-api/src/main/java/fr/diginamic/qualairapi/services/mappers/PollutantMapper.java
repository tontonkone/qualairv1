package fr.diginamic.qualairapi.services.mappers;

import fr.diginamic.qualairapi.entities.Bookmark;
import fr.diginamic.qualairapi.entities.Pollutant;
import fr.diginamic.qualairapi.dtos.PollutantDto;
import fr.diginamic.qualairapi.repositories.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PollutantMapper implements SimpleEntityMapper<Pollutant, PollutantDto> {
    private final BookmarkRepository bookmarkRepository;
    @Override
    public Pollutant dtoToEntity(PollutantDto dto) {
        Set<Bookmark> bookmarks = getRelatedBookmarksFromDto(dto);

        Pollutant pollutant = new Pollutant();

        pollutant.setId(dto.id());
        pollutant.setCode(dto.code());
        pollutant.setName(dto.name());
        pollutant.setBookmarks(bookmarks);

        return pollutant;
    }

    @Override
    public PollutantDto entityToDto(Pollutant pollutant) {
        return new PollutantDto(
                pollutant.getId(),
                pollutant.getCreationDate(),
                pollutant.getUpdateDate(),
                pollutant.getCode(),
                pollutant.getName(),
                pollutant.getBookmarks().stream()
                        .map(Bookmark::getId)
                        .collect(Collectors.toSet())
        );
    }

    @Override
    public void updateEntityWithDto(PollutantDto dto, Pollutant pollutant) {
        Set<Bookmark> bookmarks = getRelatedBookmarksFromDto(dto);

        if (null != dto.code()) {
            pollutant.setCode(dto.code());
        }
        if (null != dto.name()) {
            pollutant.setName(dto.name());
        }
        if (null != bookmarks) {
            pollutant.setBookmarks(bookmarks);
        }
    }

    private Set<Bookmark> getRelatedBookmarksFromDto(PollutantDto dto) {
        Set<Bookmark> bookmarks = null;
        if (null != dto.bookmarkIds()) {
            bookmarks = dto.bookmarkIds().stream()
                    .map(id -> bookmarkRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("No Bookmark with id " + id + " found.")))
                    .collect(Collectors.toSet());
        }
        return bookmarks;
    }
}
