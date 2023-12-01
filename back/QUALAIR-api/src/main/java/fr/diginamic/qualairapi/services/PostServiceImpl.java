package fr.diginamic.qualairapi.services;

import fr.diginamic.qualairapi.dtos.PostDto;
import fr.diginamic.qualairapi.repositories.PostRepository;
import fr.diginamic.qualairapi.services.mappers.PostMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository repository;
    private final PostMapper mapper;

    @Override
    public List<PostDto> findAllByForum(long idForum) {
        return repository.findByThread_IdOrderByIdAsc(idForum).stream()
                .map(mapper::entityToDto)
                .toList();
    }
}
