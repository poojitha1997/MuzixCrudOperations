package com.stackroute.MuzixCrudOperations.controller;

import com.stackroute.MuzixCrudOperations.domain.Track;
import com.stackroute.MuzixCrudOperations.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class TrackController
{
    private TrackService trackService;

    ResponseEntity responseEntity;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }
    //saving the track
    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) {
        try {
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);//http status is created as succesfully created

        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }

        return responseEntity;
    }
    //retrieving all the tracks
    @GetMapping("track")
    public ResponseEntity<?> getAllTracks()
    {
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
    }
    //retrieving the tracks by id
    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable(value = "id") Integer id)//id is taken as input and track is retrieved by the id 
    {
        return new ResponseEntity<Track>(trackService.getTrackById(id),HttpStatus.OK);
    }
    //deleting the track by id
    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable(value="id") Integer id)
    {
        trackService.deleteTrack(id);
        responseEntity=new ResponseEntity<String>("Deleted",HttpStatus.FORBIDDEN);
        return responseEntity;
    }
    //updating the track
    @PutMapping("track")
    public ResponseEntity<?> updateTrack(@RequestBody Track track)  {
        trackService.saveTrack(track);
        responseEntity = new ResponseEntity<String>("Updated Successfully", HttpStatus.OK);
        return responseEntity;
    }

}
