package fr.diginamic.qualairapi.services.mappers;

import fr.diginamic.qualairapi.dtos.ThreadDto;
import fr.diginamic.qualairapi.entities.Thread;
import org.springframework.stereotype.Service;

@Service
public class ThreadMapper implements SimpleEntityMapper<Thread, ThreadDto> {
    @Override
    public Thread dtoToEntity(ThreadDto dto) {
        Thread thread = new Thread();

        thread.setTitle(dto.title());

        return thread;
    }

    @Override
    public ThreadDto entityToDto(Thread thread) {
        return new ThreadDto(
                thread.getId(),
                thread.getCreationDate(),
                thread.getUpdateDate(),
                thread.getTitle()
        );
    }

    @Override
    public void updateEntityWithDto(ThreadDto dto, Thread thread) {
        if (null != dto.title()) {
            thread.setTitle(dto.title());
        }
    }
}
