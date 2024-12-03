package com.example.monprofil

import kotlinx.coroutines.flow.MutableStateFlow

open class Piece(val largeur: Float, val longueur: Float, val nom: String){
    fun surface(): Int {
        var result = 0;
        result = (longueur * largeur).toInt();

        return result;
    }
}

class Cuisine(largeur: Float, longueur: Float, nom: String) : Piece(largeur, longueur, nom){

}

class Salon(largeur: Float, longueur: Float, nom: String) : Piece(largeur, longueur, nom){


}

val list = MutableStateFlow<List<Cuisine>>(emptyList())

val list2 = MutableStateFlow<List<Salon>>(emptyList())

fun parcours(){

}