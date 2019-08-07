package com.stackroute.MuzixCrudOperations.service;

import com.stackroute.MuzixCrudOperations.domain.Track;
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
    public Track saveTrack(Track track) {
        Track savedTrack=trackRepository.save(track);
        return savedTrack;
    }

    @Override
    public String deleteTrack(int id) {
        trackRepository.deleteById(id);
        return "deleted";

    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public Track getTrackById(int id) {
        Track track=trackRepository.findById(id).get();
        return track;
    }

    @Override
    public Track UpdateTrack(Track track) {
        Track savedTrack=trackRepository.save(track);
        return savedTrack;
    }
    @Override
    public List<Track> getTrackByName(String name) {
        return trackRepository.getTrackByName(name);
    }
}
