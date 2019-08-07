package com.stackroute.MuzixCrudOperations.controller;

import com.stackroute.MuzixCrudOperations.domain.Track;
import com.stackroute.MuzixCrudOperations.service.TrackService;
import org.springframework.data.jpa.repository.Query;
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
    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) {
        try {
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);

        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }

        return responseEntity;
    }
    @GetMapping("track")
    public ResponseEntity<?> getAllTracks()
    {
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
    }
        //retrieving the tracks by id
    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable(value = "id") Integer id)
    {
        return new ResponseEntity<Track>(trackService.getTrackById(id),HttpStatus.OK);
    }
        //deleting the track by id
    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteuserById(@PathVariable(value="id") Integer id)
    {
        ResponseEntity responseEntity;
        trackService.deleteTrack(id);
        responseEntity=new ResponseEntity<String>("Deleted",HttpStatus.FORBIDDEN);
        return responseEntity;
    }
    
    //updating the track
    @PutMapping("track")
    public ResponseEntity<?> updateUser(@RequestBody Track track)  {
        ResponseEntity responseEntity;
        trackService.saveTrack(track);
        responseEntity = new ResponseEntity<String>("Updated Successfully", HttpStatus.OK);
        return responseEntity;
    }
    @GetMapping("trackForName/{name}")
    @Query("from Track where name=?1")
    public ResponseEntity<?> getAllTracksByName(@PathVariable(value="name") String name)
    {
        return new ResponseEntity<List<Track>>(trackService.getTrackByName(name),HttpStatus.OK);
    }

}
