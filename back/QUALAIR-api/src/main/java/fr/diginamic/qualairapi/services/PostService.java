package fr.diginamic.qualairapi.services;

import fr.diginamic.qualairapi.dtos.PostDto;
import fr.diginamic.qualairapi.entities.Post;

import java.util.List;

public interface PostService
    extends  SimpleEntityCreator<Post, PostDto>,
             SimpleEntityFinder<Post, PostDto>,
             SimpleEntityUpdater<Post, PostDto>,
             SimpleEntityDeleter<Post> {
    List<PostDto> findAllByForum(long idForum);
}
