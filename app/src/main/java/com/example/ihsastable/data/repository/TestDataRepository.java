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
import java.util.Calendar;
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

                    batch.commit();
                }
            }
        });
    }
    public void addTestEvents(){
        CollectionReference cr = this.db.collection("Event");
        cr.whereEqualTo("Id", 1).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Calendar cal = Calendar.getInstance();
                if(task.getResult().isEmpty()) {
                    Map<String, Object> docData = new HashMap<>();
                    docData.put("Id", 1);
                    docData.put("Location", "Missouri");
                    docData.put("EventName", "Charleston ton");
                    cal.set(2022, 12, 24);
                    docData.put("EventTime", cal.getTime());
                    docData.put("Zone", 1);
                    docData.put("EventClassesViewModel", Arrays.asList(1, 4, 3, 9));

                    Map<String, Object> docData1 = new HashMap<>();
                    docData1.put("Id", 2);
                    docData1.put("Location", "Missouri2");
                    docData1.put("EventName", "Charlyboob");
                    cal.set(2023, 6, 21);
                    docData1.put("EventTime", cal.getTime());
                    docData1.put("Zone", 1);
                    docData1.put("EventClassesViewModel", Arrays.asList(1, 9, 8, 8, 7));

                    Map<String, Object> docData2 = new HashMap<>();
                    docData2.put("Id", 3);
                    docData2.put("Location", "Missouri3");
                    docData2.put("EventName", "Charb Blarb");
                    cal.set(2024, 8, 21);
                    docData2.put("EventTime", cal.getTime());
                    docData2.put("Zone", 2);
                    docData2.put("EventClassesViewModel", Arrays.asList(1, 4, 7, 2));

                    WriteBatch batch = db.batch();
                    batch.set(cr.document(), docData);
                    batch.set(cr.document(), docData1);
                    batch.set(cr.document(), docData2);

                    batch.commit();
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
                    docData.put("Riders", Arrays.asList(111,3333, 8, 9, 4));
                    docData.put("Horses", Arrays.asList(1,2,3, 7, 2, 7));

                    Map<String, Object> docData1 = new HashMap<>();
                    docData1.put("Id", 2);
                    docData1.put("ClassName", "Class 2");
                    docData1.put("Pattern", "Pattern4");
                    docData1.put("Riders", Arrays.asList(1,222,3, 8, 5));
                    docData1.put("Horses", Arrays.asList(1,2,3, 6));

                    Map<String, Object> docData2 = new HashMap<>();
                    docData2.put("Id", 3);
                    docData2.put("ClassName", "Class 3");
                    docData2.put("Pattern", "Pattern6");
                    docData2.put("Riders", Arrays.asList(111,2,3));
                    docData2.put("Horses", Arrays.asList(1,2,3));

                    WriteBatch batch = db.batch();
                    batch.set(cr.document(), docData);
                    batch.set(cr.document(), docData1);
                    batch.set(cr.document(), docData2);

                    batch.commit();
                }
            }
        });
    }
    public void addTestRiders(){
        CollectionReference cr = this.db.collection("Rider");
        cr.whereEqualTo("Id", 111).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.getResult().isEmpty()){
                    Map<String, Object> docData = new HashMap<>();
                    docData.put("Id", 111);
                    docData.put("UserName", "TheRider");
                    docData.put("FirstName", "Limbo");
                    docData.put("LastName", "Jimbo");
                    docData.put("Role", "rider");
                    docData.put("RoleId", 2);

                    docData.put("Height", 5.8);
                    docData.put("Weight", 120);
                    docData.put("Points", 20);
                    docData.put("Position", 1);
                    docData.put("AvgPointsPerRide", 50);
                    docData.put("ManagedBy", 1);
                    docData.put("Class", "Unknown");
                    docData.put("PlaysFor", 7);
                    docData.put("IsWeightRider", true);
                    docData.put("IsHeightRider", false);


                    Map<String, Object> docData1 = new HashMap<>();
                    docData1.put("Id", 222);
                    docData1.put("UserName", "TheBigGuy");
                    docData1.put("FirstName", "John");
                    docData1.put("LastName", "Handlebar");
                    docData1.put("Role", "rider");
                    docData1.put("RoleId", 2);

                    docData1.put("Height", 9.8);
                    docData1.put("Weight", 120);
                    docData1.put("Points", 37);
                    docData1.put("Position", 3);
                    docData1.put("AvgPointsPerRide", 200);
                    docData1.put("ManagedBy", 1);
                    docData1.put("Class", "Unknown");
                    docData1.put("PlaysFor", 7);
                    docData1.put("IsWeightRider", true);
                    docData1.put("IsHeightRider", false);


                    Map<String, Object> docData2 = new HashMap<>();
                    docData2.put("Id", 333);
                    docData2.put("UserName", "Ghosty");
                    docData2.put("FirstName", "Jack");
                    docData2.put("LastName", "Black");
                    docData2.put("Role", "rider");
                    docData2.put("RoleId", 2);

                    docData2.put("Height", 4.8);
                    docData2.put("Weight", 110);
                    docData2.put("Points", 14);
                    docData2.put("Position", 9);
                    docData2.put("AvgPointsPerRide", 30);
                    docData2.put("ManagedBy", 2);
                    docData2.put("Class", "Unknown");
                    docData2.put("PlaysFor", 7);
                    docData2.put("IsWeightRider", true);
                    docData2.put("IsHeightRider", true);


                    Map<String, Object> docData4 = new HashMap<>();
                    docData4.put("Id", 444);
                    docData4.put("UserName", "StarPlatnum");
                    docData4.put("FirstName", "George");
                    docData4.put("LastName", "Butler");
                    docData4.put("Role", "rider");
                    docData4.put("RoleId", 2);

                    docData4.put("Height", 5.9);
                    docData4.put("Weight", 129);
                    docData4.put("Points", 10);
                    docData4.put("Position", 6);
                    docData4.put("AvgPointsPerRide", 25);
                    docData4.put("ManagedBy", 7);
                    docData4.put("Class", "Unknown");
                    docData4.put("PlaysFor", 9);
                    docData4.put("IsWeightRider", false);
                    docData4.put("IsHeightRider", false);


                    WriteBatch batch = db.batch();
                    batch.set(cr.document(), docData);
                    batch.set(cr.document(), docData1);
                    batch.set(cr.document(), docData2);
                    batch.set(cr.document(), docData4);

                    batch.commit();
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
