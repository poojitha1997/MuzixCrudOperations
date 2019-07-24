package com.stackroute.MuzixCrudOperations.service;

import com.stackroute.MuzixCrudOperations.domain.Track;

import java.util.List;

public interface TrackService {
    public Track saveTrack(Track track);

    public void deleteTrack(int id);

    public List<Track> getAllTracks();

    public Track getTrackById(int id);

    public Track UpdateTrack(Track track);
    public List<Track> getTrackByName(String name);


}
