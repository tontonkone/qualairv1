package fr.diginamic.qualairapi.services.mappers;

import fr.diginamic.qualairapi.dtos.PostDto;
import fr.diginamic.qualairapi.entities.Post;
import fr.diginamic.qualairapi.entities.Thread;
import fr.diginamic.qualairapi.entities.User;
import fr.diginamic.qualairapi.repositories.ThreadRepository;
import fr.diginamic.qualairapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostMapper implements SimpleEntityMapper<Post, PostDto> {
    private final UserRepository userRepository;
    private final ThreadRepository threadRepository;

    @Override
    public Post dtoToEntity(PostDto dto) {
        User author = this.userRepository.findById(dto.author().id())
                .orElseThrow(() -> new RuntimeException("User with id " + dto.author().id() + " not found"));
        Thread thread = this.threadRepository.findById(dto.threadId())
                .orElseThrow(() -> new RuntimeException("Thread with id " + dto.threadId() + " not found"));

        Post post = new Post();

        post.setAuthor(author);
        post.setThread(thread);
        post.setContent(dto.content());

        return post;
    }

    @Override
    public PostDto entityToDto(Post post) {
        return new PostDto(
                post.getId(),
                post.getCreationDate(),
                post.getUpdateDate(),
                new PostDto.UserDto(
                        post.getAuthor().getId(),
                        post.getAuthor().getFirstName(),
                        post.getAuthor().getLastName(),
                        post.getAuthor().getEmail()
                ),
                post.getThread().getId(),
                post.getContent()
        );
    }

    @Override
    public void updateEntityWithDto(PostDto dto, Post post) {
        if (null != dto.content()) {
            post.setContent(dto.content());
        }
    }
}
