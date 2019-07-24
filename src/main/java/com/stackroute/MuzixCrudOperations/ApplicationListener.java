package com.stackroute.MuzixCrudOperations;

import com.stackroute.MuzixCrudOperations.domain.Track;
import com.stackroute.MuzixCrudOperations.expections.TrackAlreadyExistsException;
import com.stackroute.MuzixCrudOperations.repository.TrackRepository;
import com.stackroute.MuzixCrudOperations.service.TrackService;
import com.stackroute.MuzixCrudOperations.service.TrackServiceImpl;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListener implements org.springframework.context.ApplicationListener<ContextRefreshedEvent>
{


    TrackService trackService;

    public ApplicationListener(TrackService trackService) {
        this.trackService = trackService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {
        Track track = new Track(8,"grf","fv");
        try {
            trackService.saveTrack(track);
        } catch (TrackAlreadyExistsException e) {
            e.printStackTrace();
        }



    }

}
