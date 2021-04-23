package com.ashish.avonbitscrscalc.model.managers

object PointsManager {

    var spouseCommonLaw = false
    var spouseCommonLawComeWithMe = false

    //List of points
    var points = arrayListOf(
            0, //1
            0, //2
            0, //3
            0, //4
            0, //5
            0, //6
            0, //7
            0, //8
            0, //9
            0, //11
            0, //12
            0, //13
            0 //ADS
    )

    //Lambda to restart the points
    val restart = { points = arrayListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) }

    //Lambda to add Points
    val addPoints = { question: Int, point: List<Int> -> points[question - 1] += point[if (spouseCommonLaw) 1 else 0] }

    //Lambda to plus the pints and get a result
    val getResult = {
        var counter = 0

        if (points[13] > 600) points[13] = 600

        for (i in points) counter += i
        counter
    }

}