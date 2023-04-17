package com.example.ihsastable.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.ihsastable.data.model.Event;
import com.example.ihsastable.data.model.EventClass;
import com.example.ihsastable.data.model.Horse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDataRepository {
    private FirebaseFirestore db;
    public TestDataRepository(){
        this.db = FirebaseFirestore.getInstance();
    }

    public void addTestHorses(){
        //Id: int
//        Name: String
//        Description: String
//        GPA: Float
//        Provider: String
//        Classes: List[String]
//        CarryingHeight: Float
//        Carrying Weight: Float
//        IsSpur: Boolean
        CollectionReference cr = db.collection("Horse");

        cr.whereEqualTo("Id", 1).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.getResult().isEmpty()){
                    Map<String, Object> docData = new HashMap<>();
                    docData.put("Id", 1);
                    docData.put("Name", "Butterschotch");
                    docData.put("Description", "A very good horse");
                    docData.put("GPA", 7.8);
                    docData.put("Provider", "Jim");
                    docData.put("Classes", Arrays.asList("One", "two", "three"));
                    docData.put("CarryingWeight", 7.0);
                    docData.put("IsSpur", false);

                    Map<String, Object> docData1 = new HashMap<>();
                    docData1.put("Id", 2);
                    docData1.put("Name", "Butter");
                    docData1.put("Description", "A very horse");
                    docData1.put("GPA", 4.0);
                    docData1.put("Provider", "Jim2");
                    docData1.put("Classes", Arrays.asList("two", "three"));
                    docData1.put("CarryingWeight", 77.0);
                    docData1.put("IsSpur", true);

                    Map<String, Object> docData2 = new HashMap<>();
                    docData2.put("Id", 3);
                    docData2.put("Name", "Mumphler");
                    docData2.put("Description", "A very horsey horse");
                    docData2.put("GPA", 4.0);
                    docData2.put("Provider", "Jr. Jim");
                    docData2.put("Classes", Arrays.asList("two", "9", "three"));
                    docData2.put("CarryingWeight", 79);
                    docData2.put("IsSpur", true);

                    WriteBatch batch = db.batch();
                    batch.set(cr.document(), docData);
                    batch.set(cr.document(), docData1);
                    batch.set(cr.document(), docData2);

                    batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            HorseRepository hr = new HorseRepository();
                            EventClass testEC = new EventClass(1, "butts", "Pattern2", Arrays.asList(1, 4, 7), Arrays.asList(1,2, 3));
                            if(testEC != null){
                                hr.fetchHorsesFromEventClass(testEC);
                            }
                            else{
                                Log.e("error", "what");
                            }
                        }
                    });
                }
                else{
                    HorseRepository hr = new HorseRepository();
                    EventClass testEC = new EventClass(1, "butts", "Pattern2", Arrays.asList(1, 4, 7), Arrays.asList(1,2, 3));
                    hr.fetchHorsesFromEventClass(testEC);
                }
            }
        });
    }
    public void addTestEvents(){
        CollectionReference cr = this.db.collection("Event");
        cr.whereEqualTo("Id", 1).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.getResult().isEmpty()){
                    Map<String, Object> docData = new HashMap<>();
                    docData.put("Id", 1);
                    docData.put("Location", "Missouri");
                    docData.put("EventName", "Charleston ton");
                    docData.put("EventTime", new Date());
                    docData.put("Zone", 1);
                    docData.put("EventClasses", Arrays.asList(1,4,3,9));

                    Map<String, Object> docData1 = new HashMap<>();
                    docData1.put("Id", 2);
                    docData1.put("Location", "Missouri2");
                    docData1.put("EventName", "Charlyboob");
                    docData1.put("EventTime", new Date());
                    docData1.put("Zone", 1);
                    docData1.put("EventClasses", Arrays.asList(1,9,8,8,7));

                    Map<String, Object> docData2 = new HashMap<>();
                    docData2.put("Id", 3);
                    docData2.put("Location", "Missouri3");
                    docData2.put("EventName", "Charb Blarb");
                    docData2.put("EventTime", new Date());
                    docData2.put("Zone", 2);
                    docData2.put("EventClasses", Arrays.asList(1,4,7,2));

                    WriteBatch batch = db.batch();
                    batch.set(cr.document(), docData);
                    batch.set(cr.document(), docData1);
                    batch.set(cr.document(), docData2);

                    batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            EventRepository er = new EventRepository();
                            er.fetchEventsAfterDate(new Date(-700000));
                        }
                    });
                }
                else{
                    EventRepository er = new EventRepository();
                    er.fetchEventsAfterDate(new Date(-700000));
                }
            }
        });
    }
    public void addTestEventClasses(){
        CollectionReference cr = this.db.collection("EventClass");
        cr.whereEqualTo("Id", 1).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.getResult().isEmpty()){
                    Map<String, Object> docData = new HashMap<>();
                    docData.put("Id", 1);
                    docData.put("ClassName", "Class 1");
                    docData.put("Pattern", "Pattern2");
                    docData.put("Riders", Arrays.asList(1,3, 8, 9, 4));
                    docData.put("Horses", Arrays.asList(1,2,3, 7, 2, 7));

                    Map<String, Object> docData1 = new HashMap<>();
                    docData1.put("Id", 2);
                    docData1.put("ClassName", "Class 2");
                    docData1.put("Pattern", "Pattern4");
                    docData1.put("Riders", Arrays.asList(1,2,3, 8, 5));
                    docData1.put("Horses", Arrays.asList(1,2,3, 6));

                    Map<String, Object> docData2 = new HashMap<>();
                    docData2.put("Id", 3);
                    docData2.put("ClassName", "Class 3");
                    docData2.put("Pattern", "Pattern6");
                    docData2.put("Riders", Arrays.asList(1,2,3));
                    docData2.put("Horses", Arrays.asList(1,2,3));

                    WriteBatch batch = db.batch();
                    batch.set(cr.document(), docData);
                    batch.set(cr.document(), docData1);
                    batch.set(cr.document(), docData2);

                    batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            EventClassRepository ecr = new EventClassRepository();
                            //ecr.FetchEventClassesFromEvent(new EventRepository().getEvents().get(0));
                        }
                    });
                }
                else{
                    EventClassRepository ecr = new EventClassRepository();
                    //ecr.FetchEventClassesFromEvent(new EventRepository().getEvents().get(0));
                }
            }
        });
    }
}
//school
//Id: int
//        SchoolName: String
//        StateCode: String
//
//        Latitude: Double
//        Longitude: Double
//        Region: int
//        Zone: int
//        NumRiders: int
//        IsAnchorSchool: Boolean

//Rider
//RiderId: int
//        Height: Float (optional)
//        Weight: Float (optional)
//        Points: Int
//        Position: Int
//        Average Points per ride: Float
//        ManagedBy: CoachId (database id)
//        Class: Int (Id lookup)
//        PlaysFor: SchoolId (database id)
//        IsWeightRider: bool
//        IsHeightRider: bool
//+
//User: (basic generic, applied to all logged-in users)
//        Id: int
//        Username: String
//        FirstName: String
//        LastName: String
//        Role: Int (enum)
//        RoleId: Id -> diff collection

//Horse
//Horse:
//        Id: int
//        Name: String
//        Description: String
//        GPA: Float
//        Provider: String
//        Classes: List[String]
//        CarryingHeight: Float
//        Carrying Weight: Float
//        IsSpur: Boolean
