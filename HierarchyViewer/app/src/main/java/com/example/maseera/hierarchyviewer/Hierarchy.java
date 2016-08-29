package com.example.maseera.hierarchyviewer;

import java.io.Serializable;

/**
 * Created by maseera on 14/8/16.
 */
public class Hierarchy implements Serializable {
    String position;
    String reportsToPosition;
    String nameOfIncumbent;

    Hierarchy(String position,String reportsToPosition,String nameOfIncumbent)
    {
        this.position=position;
        this.reportsToPosition=reportsToPosition;
        this.nameOfIncumbent=nameOfIncumbent;
    }
}
