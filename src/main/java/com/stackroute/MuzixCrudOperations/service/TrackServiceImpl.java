package com.stackroute.MuzixCrudOperations.service;

import com.stackroute.MuzixCrudOperations.domain.Track;
import com.stackroute.MuzixCrudOperations.expections.TrackAlreadyExistsException;
import com.stackroute.MuzixCrudOperations.expections.TrackNotFoundException;
import com.stackroute.MuzixCrudOperations.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService
{
    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException
    {
        if (trackRepository.existsById(track.getId()))
        {
            throw new TrackAlreadyExistsException("Track already exists");
        }
        Track savedTrack=trackRepository.save(track);

        return savedTrack;
    }

    @Override
    public void deleteTrack(int id) throws TrackNotFoundException
    {
        if(!trackRepository.findById(id).isPresent())
        {
            throw new TrackNotFoundException("No track found with this id");
        }
        trackRepository.deleteById(id);

    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public Track getTrackById(int id) throws TrackNotFoundException {
        if(!trackRepository.findById(id).isPresent())
        {
            throw new TrackNotFoundException("No track is found by this id");
        }
        Track track=trackRepository.findById(id).get();
        return track;
    }

    @Override
    public Track UpdateTrack(Track track) {
        Track savedTrack=trackRepository.save(track);
        return savedTrack;
    }
    @Override
    public List<Track> getTrackByName(String name) throws TrackNotFoundException
    {
        List<Track> tracksByName = trackRepository.getTrackByName(name);
        if(tracksByName.isEmpty())
        {
            throw new TrackNotFoundException("no track is found by this name");
        }
        return tracksByName;
    }
}
