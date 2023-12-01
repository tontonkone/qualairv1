package fr.diginamic.qualairapi.controllers;

import fr.diginamic.qualairapi.dtos.PostDto;
import fr.diginamic.qualairapi.services.PostService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("posts")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    private final PostService postService;

    @GetMapping("thread/{idThread}")
    public List<PostDto> findAllByForum(@PathVariable long idThread) {
        logger.info("Requête reçue pour récupérer les messages du thread {}", idThread);
        return this.postService.findAllByForum(idThread);
    }

    /**
     * Crée un nouveau post.
     *
     * @param postDto Les informations du nouveau post à créer.
     * @return L'enregistrement {@link PostDto} représentant le post créé.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto create(@RequestBody PostDto postDto) {
        logger.info("Requête reçue pour créer un nouveau thread : {}", postDto);

        PostDto createdPost = postService.create(postDto);

        logger.info("Nouveau thread créé avec l'ID : {}", createdPost.id());
        return createdPost;
    }
}
