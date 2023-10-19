package com.example.assignment2

import java.io.Serializable
import java.lang.invoke.MethodHandleInfo

class OrderItems (var info:String, var flavor:String, var size:String, var date:String, var cost:String, var toppings:String, var fudge:String) : Serializable{

    override fun toString(): String {
        return """Order $info
 Flavor: $flavor
 Size: $size
 Date: $date
 Cost: $cost
 Toppings: $toppings
 Hot Fudge: $fudge
"""
    }
}