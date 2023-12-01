package fr.diginamic.qualairapi.services;

import fr.diginamic.qualairapi.entities.Thread;
import fr.diginamic.qualairapi.dtos.ThreadDto;

public interface ThreadService
        extends SimpleEntityCreator<Thread, ThreadDto>,
                SimpleEntityFinder<Thread, ThreadDto>,
                SimpleEntityUpdater<Thread, ThreadDto>,
                SimpleEntityDeleter<Thread> {
}
