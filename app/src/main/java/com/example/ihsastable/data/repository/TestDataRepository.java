package com.example.ihsastable.data.repository;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Arrays;
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
                if(!task.getResult().isEmpty()){
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
                    docData.put("Id", 2);
                    docData.put("Name", "Butter");
                    docData.put("Description", "A very horse");
                    docData.put("GPA", 4.0);
                    docData.put("Provider", "Jim2");
                    docData.put("Classes", Arrays.asList("two", "three"));
                    docData.put("CarryingWeight", 77.0);
                    docData.put("IsSpur", true);

                    Map<String, Object> docData2 = new HashMap<>();
                    docData.put("Id", 3);
                    docData.put("Name", "Mumphler");
                    docData.put("Description", "A very horsey horse");
                    docData.put("GPA", 4.0);
                    docData.put("Provider", "Jr. Jim");
                    docData.put("Classes", Arrays.asList("two", "9", "three"));
                    docData.put("CarryingWeight", 79);
                    docData.put("IsSpur", true);

                    cr.add(docData);
                    cr.add(docData1);
                    cr.add(docData2);
                }
            }
        });








    }
}
